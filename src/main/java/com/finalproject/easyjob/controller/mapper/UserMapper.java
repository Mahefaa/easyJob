package com.finalproject.easyjob.controller.mapper;

import com.finalproject.easyjob.model.User;
import com.finalproject.easyjob.model.rest.RestUser;
import com.finalproject.easyjob.security.model.Role;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {
  private static final BCryptPasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

  private String encryptPassword(String password) {
    return PASSWORD_ENCODER.encode(password);
  }

  public User toDomain(RestUser restUser, Role role) {
    return User.builder()
        .id(restUser.getId())
        .enabled(true)
        .role(role)
        .joinedInstant(Instant.now(Clock.system(ZoneId.of("GMT+3"))))
        .email(restUser.getEmail())
        .password(encryptPassword(restUser.getPassword()))
        .build();
  }

  public User toDomain(RestUser restUser) {
    return User.builder()
        .enabled(true)
        .password(encryptPassword(restUser.getPassword()))
        .joinedInstant(Instant.now(Clock.system(ZoneId.of("GMT+3"))))
        .email(restUser.getEmail())
        .build();
  }

  public RestUser toRest(User user) {
    return RestUser.builder()
        .id(user.getId())
        .password(user.getPassword())
        .joinedInstant(user.getJoinedInstant())
        .email(user.getEmail())
        .build();
  }

}
