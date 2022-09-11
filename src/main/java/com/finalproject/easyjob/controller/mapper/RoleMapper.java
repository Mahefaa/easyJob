package com.finalproject.easyjob.controller.mapper;

import com.finalproject.easyjob.model.rest.RestRole;
import com.finalproject.easyjob.security.model.Role;
import org.springframework.stereotype.Component;

//one day Role might be stored in our database instead of being an  ENUM
@Component
public class RoleMapper {
  public Role toDomain(RestRole role){
    return role.getRole();
  }
}
