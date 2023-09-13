package com.magma.magmaapi.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.magma.magmaapi.model.ERole;
import com.magma.magmaapi.model.Role;

@Component
public class RoleConverter implements Converter<String, Role> {

  @Override
  public Role convert(String source) {
    ERole enumRoleName = ERole.valueOf(source);
    return new Role(enumRoleName);
  }
}
