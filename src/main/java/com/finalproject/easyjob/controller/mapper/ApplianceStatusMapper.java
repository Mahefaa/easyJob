package com.finalproject.easyjob.controller.mapper;

import com.finalproject.easyjob.model.Appliance;
import com.finalproject.easyjob.model.rest.RestStatus;
import org.springframework.stereotype.Component;

@Component
public class ApplianceStatusMapper {
  public Appliance.Status toDomain(RestStatus status){
    return status.getStatus();
  }
}
