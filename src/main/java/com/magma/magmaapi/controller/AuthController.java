package com.magma.magmaapi.controller;

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

import com.magma.magmaapi.model.ERole;
import com.magma.magmaapi.model.Role;
import com.magma.magmaapi.model.UserDetailsImpl;
import com.magma.magmaapi.model.Users;
import com.magma.magmaapi.payload.request.LoginRequest;
import com.magma.magmaapi.payload.request.SignupRequest;
import com.magma.magmaapi.payload.response.MessageResponse;
import com.magma.magmaapi.payload.response.UserInfoResponse;
import com.magma.magmaapi.repository.UsersRepository;
import com.magma.magmaapi.security.JwtUtils;

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
        new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
        .body(new UserInfoResponse(
            userDetails.getUsername(),
            userDetails.getEmail(),
            roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByEmail(signUpRequest.email())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }
    Set<String> strRoles = signUpRequest.roles();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = new Role(ERole.ROLE_USER);
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin":
            Role adminRole = new Role(ERole.ROLE_ADMIN);
            roles.add(adminRole);
            break;
          default:
            Role userRole = new Role(ERole.ROLE_USER);
            roles.add(userRole);
        }
      });
    }

    Users newUser = new Users(signUpRequest.username(),
        signUpRequest.email(),
        encoder.encode(signUpRequest.password()), roles);
    userRepository.save(newUser);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}
