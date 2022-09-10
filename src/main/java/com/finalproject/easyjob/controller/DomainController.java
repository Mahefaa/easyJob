package com.finalproject.easyjob.controller;

import com.finalproject.easyjob.controller.mapper.DomainMapper;
import com.finalproject.easyjob.model.BoundedPageSize;
import com.finalproject.easyjob.model.PageFromOne;
import com.finalproject.easyjob.model.rest.RestDomain;
import com.finalproject.easyjob.model.rest.RestSaveDomain;
import com.finalproject.easyjob.service.DomainService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@AllArgsConstructor
public class DomainController {
  private final DomainService service;
  private final DomainMapper mapper;

  @GetMapping("/domains")
  public List<RestDomain> getDomainsByName(@RequestParam PageFromOne page,
                                           @RequestParam BoundedPageSize pageSize,
                                           @RequestParam(required = false, defaultValue = "")
                                           String name) {
    return service.getAllByName(page, pageSize, name).stream().map(mapper::toRest).toList();
  }

  @PutMapping("/domains")
  public List<RestDomain> createOrUpdateDomains(@RequestBody List<RestSaveDomain> restSaveDomains) {
    return service.saveDomains(restSaveDomains.stream().map(mapper::toDomain).toList()).stream()
        .map(mapper::toRest).toList();
  }
}
