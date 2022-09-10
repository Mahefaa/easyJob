package com.finalproject.easyjob.controller.mapper;

import com.finalproject.easyjob.model.User;
import com.finalproject.easyjob.model.rest.RestUser;
import com.finalproject.easyjob.security.model.Role;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {
  public User toDomain(RestUser restUser, Role role) {
    return User.builder()
        .id(restUser.getId())
        .enabled(true)
        .role(role)
        .joinedInstant(Instant.now(Clock.system(ZoneId.of("GMT+3"))))
        .email(restUser.getEmail())
        .password(restUser.getPassword())
        .build();
  }

  public User toDomain(RestUser restUser) {
    return User.builder()
        .enabled(true)
        .password(restUser.getPassword())
        .role(restUser.getRole())
        .joinedInstant(Instant.now(Clock.system(ZoneId.of("GMT+3"))))
        .email(restUser.getEmail())
        .build();
  }

  public RestUser toRest(User user) {
    return RestUser.builder()
        .id(user.getId())
        .password(user.getPassword())
        .role(user.getRole())
        .joinedInstant(user.getJoinedInstant())
        .email(user.getEmail())
        .build();
  }

}
