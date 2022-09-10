package com.finalproject.easyjob.controller.mapper;

import com.finalproject.easyjob.model.Domain;
import com.finalproject.easyjob.model.User;
import com.finalproject.easyjob.model.rest.RestUser;
import com.finalproject.easyjob.security.model.Role;
import com.finalproject.easyjob.service.DomainService;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {
  private final DomainService domainService;

  public User toDomain(RestUser restUser, Role role) {
    return User.builder()
        .id(restUser.getId())
        .enabled(restUser.isEnabled())
        .interests(stringToDomain(restUser.getInterests()))
        .role(role)
        .joinedInstant(Instant.now(Clock.system(ZoneId.of("GMT+3"))))
        .email(restUser.getEmail())
        .password(restUser.getPassword())
        .build();
  }

  public User toDomain(RestUser restUser) {
    return User.builder()
        .enabled(restUser.isEnabled())
        .interests(stringToDomain(restUser.getInterests()))
        .password(restUser.getPassword())
        .role(restUser.getRole())
        .joinedInstant(Instant.now(Clock.system(ZoneId.of("GMT+3"))))
        .email(restUser.getEmail())
        .build();
  }

  public RestUser toRest(User user) {
    return RestUser.builder()
        .id(user.getId())
        .enabled(user.getEnabled())
        .interests(domainToString(user.getInterests()))
        .role(user.getRole())
        .joinedInstant(user.getJoinedInstant())
        .email(user.getEmail())
        .build();
  }

  private Set<Domain> stringToDomain(List<String> domainNames) {
    if (domainNames == null) {
      return null;
    }
    return domainNames.stream().map(domainService::getByName).collect(Collectors.toSet());
  }

  private List<String> domainToString(Set<Domain> domains) {
    if (domains == null) {
      return null;
    }
    return List.of("lol");
  }
}
