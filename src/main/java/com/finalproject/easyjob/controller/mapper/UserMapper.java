package com.finalproject.easyjob.controller.mapper;

import com.finalproject.easyjob.model.User;
import com.finalproject.easyjob.model.rest.RestUser;
import com.finalproject.easyjob.security.model.Role;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
  public User toDomainAdmin(RestUser restUser) {
    return User.builder()
        .enabled(restUser.isEnabled())
        .role(Role.ADMIN)
        .joinedInstant(Instant.now(Clock.system(ZoneId.of("EUROPE/MOSCOU"))))
        .email(restUser.getEmail())
        .password(restUser.getPassword())
        .build();
  }

  public User toDomainCandidate(RestUser restUser) {
    return User.builder()
        .enabled(restUser.isEnabled())
        .role(Role.CANDIDATE)
        .joinedInstant(Instant.now(Clock.system(ZoneId.of("EUROPE/MOSCOU"))))
        .email(restUser.getEmail())
        .password(restUser.getPassword())
        .build();
  }

  public User toDomainRecruiter(RestUser restUser) {
    return User.builder()
        .enabled(restUser.isEnabled())
        .role(Role.RECRUITER)
        .joinedInstant(Instant.now(Clock.system(ZoneId.of("EUROPE/MOSCOW"))))
        .email(restUser.getEmail())
        .password(restUser.getPassword())
        .build();
  }

  public User toDomain(RestUser restUser) {
    return User.builder()
        .id(restUser.getId())
        .enabled(restUser.isEnabled())
        .role(restUser.getRole())
        .joinedInstant(Instant.now(Clock.system(ZoneId.of("EUROPE/MOSCOW"))))
        .email(restUser.getEmail())
        .password(restUser.getPassword())
        .build();
  }

  public RestUser toRest(User user) {
    return RestUser.builder()
        .id(user.getId())
        .enabled(user.getEnabled())
        .role(user.getRole())
        .joinedInstant(user.getJoinedInstant())
        .email(user.getEmail())
        .password(user.getPassword())
        .build();
  }
}
