package com.finalproject.easyjob.controller;

import com.finalproject.easyjob.controller.mapper.ApplianceMapper;
import com.finalproject.easyjob.controller.mapper.ApplianceStatusMapper;
import com.finalproject.easyjob.model.BoundedPageSize;
import com.finalproject.easyjob.model.PageFromOne;
import com.finalproject.easyjob.model.rest.RestAppliance;
import com.finalproject.easyjob.model.rest.RestStatus;
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
  private final ApplianceStatusMapper statusMapper;

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
  public RestAppliance createOrUpdateAppliance(@PathVariable("userId") int id,
                                               @RequestBody RestAppliance restAppliance) {
    return mapper.toRest(service.save(mapper.toDomain(restAppliance), id));
  }

  @GetMapping("/users/{userId}/appliances/{id}")
  public RestAppliance getApplianceByUser(@PathVariable("userId") int userId,
                                          @PathVariable("id") int id) {
    return mapper.toRest(service.getByUserIdAndId(userId, id));
  }


  @GetMapping("/users/{userId}/offers/{offerId}/appliances")
  public List<RestAppliance> getAppliancesByOffer(
      @RequestParam PageFromOne page,
      @RequestParam BoundedPageSize pageSize,
      @PathVariable("userId") int userId,
      @PathVariable("offerId") int offerId,
      @RequestParam(required = false, defaultValue = "") String domainName,
      @RequestParam(required = false, defaultValue = "") String status
  ) {
    return service.getAllByUserAndOffer(page, pageSize, userId, offerId, domainName, status)
        .stream()
        .map(mapper::toRest).toList();
  }

  @GetMapping("/users/{userId}/offers/{offerId}/appliances/{id}")
  public RestAppliance getApplianceByUserAndOffer(@PathVariable("userId") int userId,
                                                  @PathVariable("offerId") int offerId,
                                                  @PathVariable int id) {
    return mapper.toRest(service.getByUserIdAndOfferIdAndId(userId, offerId, id));
  }

  @PutMapping("/users/{userId}/offers/{offerId}/appliances/{id}/act")
  public RestAppliance updateApplianceState(
      @PathVariable("userId") int userId,
      @PathVariable("offerId") int offerId,
      @PathVariable int id,
      @RequestBody RestStatus restStatus) {
    return mapper.toRest(
        service.updateStatus(userId, offerId, id, statusMapper.toDomain(restStatus)));
  }

  /*
  actually entity relation should be :
    one CANDIDATE has list of appliances
    one RECRUITER has list of Offers
    one Offer has list of Appliances

    but I keep appliances together in a table because i think it's useful to have a list of all appliances
    and user information

    also because those user information are public so there's no need to hide them I Guess ...
   */

}
