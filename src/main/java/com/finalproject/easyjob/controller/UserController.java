package com.finalproject.easyjob.controller;

import com.finalproject.easyjob.controller.mapper.UserMapper;
import com.finalproject.easyjob.model.BoundedPageSize;
import com.finalproject.easyjob.model.PageFromOne;
import com.finalproject.easyjob.model.rest.RestRole;
import com.finalproject.easyjob.model.rest.RestUser;
import com.finalproject.easyjob.security.model.Role;
import com.finalproject.easyjob.service.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin
public class UserController {
  private final UserService service;
  private final UserMapper mapper;

  @GetMapping("/users")
  public List<RestUser> getUsers(@RequestParam PageFromOne page,
                                 @RequestParam BoundedPageSize pageSize,
                                 @RequestParam(required = false, defaultValue = "") String mail,
                                 @RequestParam(required = false, defaultValue = "") String role) {
    return service.getAllByCriteria(page, pageSize, mail, role).stream().map(mapper::toRest)
        .toList();
  }

  @GetMapping("/users/{id}")
  public RestUser getUserById(@PathVariable int id) {
    return mapper.toRest(service.getById(id));
  }

  @PutMapping("/users/{id}")
  public RestUser updateUser(@PathVariable int id, @RequestBody RestUser restUser) {
    return mapper.toRest(service.updateUser(id, mapper.toDomain(restUser)));
  }

  @PostMapping("/users/admins")
  public RestUser createAdmin(@RequestBody RestUser restUser) {
    return mapper.toRest(service.createUser(mapper.toDomain(restUser, Role.ADMIN)));
  }

  @PostMapping("/users/candidates")
  public RestUser createCandidate(@RequestBody RestUser restUser) {
    return mapper.toRest(service.createUser(mapper.toDomain(restUser, Role.CANDIDATE)));
  }

  @PostMapping("/users/recruiters")
  public RestUser createRecruiter(@RequestBody RestUser restUser) {
    return mapper.toRest(service.createUser(mapper.toDomain(restUser, Role.RECRUITER)));
  }


}
