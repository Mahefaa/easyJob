package com.finalproject.easyjob.controller.mapper;

import com.finalproject.easyjob.model.Appliance;
import com.finalproject.easyjob.model.rest.RestAppliance;
import com.finalproject.easyjob.service.OfferService;
import com.finalproject.easyjob.service.UserService;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ApplianceMapper {
  private final UserService userService;
  private final OfferService offerService;

  public RestAppliance toRest(Appliance appliance) {
    return RestAppliance.builder()
        .id(appliance.getId())
        .creationInstant(appliance.getCreationInstant())
        .offerId(appliance.getOffer().getId())
        .userEmail(appliance.getUser().getEmail())
        .build();
  }

  public Appliance toDomain(RestAppliance restAppliance) {
    return Appliance.builder()
        .creationInstant(Instant.now(Clock.system(ZoneId.of("GMT+3"))))
        .id(restAppliance.getId())
        .status(Appliance.Status.ONGOING)
        .user(userService.getByEmail(restAppliance.getUserEmail()))
        .offer(offerService.getById(restAppliance.getOfferId()))
        .build();
  }
}
