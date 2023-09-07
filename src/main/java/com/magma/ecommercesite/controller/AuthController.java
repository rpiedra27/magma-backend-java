package com.magma.ecommercesite.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magma.ecommercesite.security.JwtUtils;
import com.magma.ecommercesite.model.Role;
import com.magma.ecommercesite.model.UserRole;
import com.magma.ecommercesite.model.Users;
import com.magma.ecommercesite.payload.request.LoginRequest;
import com.magma.ecommercesite.payload.request.SignupRequest;
import com.magma.ecommercesite.payload.response.MessageResponse;
import com.magma.ecommercesite.payload.response.UserInfoResponse;
import com.magma.ecommercesite.repository.UsersRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {
  private AuthenticationManager authenticationManager;
  private UsersRepository userRepository;
  private PasswordEncoder encoder;
  private JwtUtils jwtUtils;

  public AuthController(AuthenticationManager authenticationManager, UsersRepository userRepository,
      PasswordEncoder encoder, JwtUtils jwtUtils) {
    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
    this.encoder = encoder;
    this.jwtUtils = jwtUtils;
  }

  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    Users userDetails = (Users) authentication.getPrincipal();
    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
        .body(new UserInfoResponse(
            userDetails.getUsername(),
            userDetails.email(),
            roles));

  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }
    Set<String> strRoles = signUpRequest.getRoles();
    Set<UserRole> roles = new HashSet<>();

    if (strRoles == null) {
      Role role = new Role("USER");
      UserRole userRole = new UserRole(role);
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Role adminRole = new Role("ADMIN");
            UserRole adminUserRole = new UserRole(adminRole);
            roles.add(adminUserRole);
            break;
          default:
            Role userRole = new Role("USER");
            UserRole userUserRole = new UserRole(userRole);
            roles.add(userUserRole);
        }
      });
    }

    Users newUser = new Users(signUpRequest.getUsername(),
        signUpRequest.getEmail(),
        encoder.encode(signUpRequest.getPassword()), roles);
    userRepository.save(newUser);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}
