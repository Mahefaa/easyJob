package com.finalproject.easyjob.model.rest;

import com.finalproject.easyjob.security.model.Role;
import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RestUser {
  private int id;
  private String email;
  private String password;
  private List<String> interests;
  private Instant joinedInstant;
  private Role role;
  private boolean enabled;
}
