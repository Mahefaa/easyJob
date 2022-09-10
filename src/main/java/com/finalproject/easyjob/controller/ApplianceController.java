package com.finalproject.easyjob.controller;

import com.finalproject.easyjob.controller.mapper.ApplianceMapper;
import com.finalproject.easyjob.model.Appliance;
import com.finalproject.easyjob.model.BoundedPageSize;
import com.finalproject.easyjob.model.PageFromOne;
import com.finalproject.easyjob.model.rest.RestAppliance;
import com.finalproject.easyjob.service.ApplianceService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin
public class ApplianceController {
  private final ApplianceService service;
  private final ApplianceMapper mapper;

  @GetMapping("/users/{userId}/appliances")
  public List<RestAppliance> getAppliancesByUser(
      @RequestParam PageFromOne page,
      @RequestParam BoundedPageSize pageSize,
      @PathVariable("userId") int id,
      @RequestParam(required = false, defaultValue = "") String domainName,
      @RequestParam(required = false, defaultValue = "") String status
  ) {
    return service.getAllByUser(page, pageSize, id, domainName, status).stream().map(mapper::toRest)
        .toList();
  }

  @PutMapping("/users/{userId}/appliances")
  public RestAppliance createOrUpdateAppliance(RestAppliance restAppliance) {
    return mapper.toRest(service.save(mapper.toDomain(restAppliance)));
  }

  @GetMapping("/users/{userId}/appliances/{id}")
  public RestAppliance getApplianceByUser(@PathVariable int userId, @PathVariable("id") int id) {
    return mapper.toRest(service.getByUserIdAndId(userId, id));
  }


  @GetMapping("/offers/{offerId}/appliances")
  public List<RestAppliance> getAppliancesByOffer(
      @RequestParam PageFromOne page,
      @RequestParam BoundedPageSize pageSize,
      @PathVariable("offerId") int id,
      @RequestParam(required = false, defaultValue = "") String domainName,
      @RequestParam(required = false, defaultValue = "") String status
  ) {
    return service.getAllByOffer(page, pageSize, id, domainName, status).stream()
        .map(mapper::toRest).toList();
  }

  @GetMapping("/offers/{offerId}/appliances/{id}")
  public RestAppliance getApplianceByOffer(@PathVariable("offerId") int offerId,
                                           @PathVariable("id") int id) {
    return mapper.toRest(service.getByOfferIdAndId(offerId, id));
  }

  @PutMapping("/offers/appliances/{applianceId}/act")
  public RestAppliance updateApplianceState(@PathVariable(name = "applianceId") int id,
                                            @RequestBody String newStatus) {
    return mapper.toRest(service.updateStatus(id, Appliance.Status.valueOf(newStatus)));
  }
}
