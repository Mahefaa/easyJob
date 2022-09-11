package com.finalproject.easyjob.controller;

import com.finalproject.easyjob.controller.mapper.RoleMapper;
import com.finalproject.easyjob.controller.mapper.UserMapper;
import com.finalproject.easyjob.model.rest.RestRole;
import com.finalproject.easyjob.model.rest.RestUser;
import com.finalproject.easyjob.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@AllArgsConstructor
public class RoleController {
  private final RoleMapper mapper;
  private final UserMapper userMapper;

  private final UserService userService;

  @PutMapping("/users/{id}/roles")
  public RestUser updateRole(@PathVariable int id, @RequestBody RestRole role) {
    return userMapper.toRest(userService.updateUserRole(id, mapper.toDomain(role)));
  }
}
