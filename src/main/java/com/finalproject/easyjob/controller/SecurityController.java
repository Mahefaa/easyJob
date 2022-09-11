package com.finalproject.easyjob.controller;

import com.finalproject.easyjob.controller.mapper.UserMapper;
import com.finalproject.easyjob.model.rest.RestUser;
import com.finalproject.easyjob.security.model.Principal;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@AllArgsConstructor
public class SecurityController {
  private final UserMapper userMapper;

  @GetMapping("/ping")
  public String ping() {
    return "pong";
  }

  @GetMapping("/whoami")
  public RestUser whoami(@AuthenticationPrincipal Principal principal) {
    return userMapper.toRest(principal.getUser());
  }
}
