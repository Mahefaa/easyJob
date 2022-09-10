package com.finalproject.easyjob.controller.mapper;

import com.finalproject.easyjob.model.Offer;
import com.finalproject.easyjob.model.rest.RestOffer;
import com.finalproject.easyjob.security.CustomAuthenticationProvider;
import com.finalproject.easyjob.service.DomainService;
import com.finalproject.easyjob.service.UserService;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OfferMapper {
  private final DomainService domainService;
  private final UserService userService;

  public Offer toDomain(RestOffer restOffer) {
    return Offer.builder()
        .id(restOffer.getId())
        .domain(domainService.getByName(restOffer.getDomainName()))
        .creationInstant(Instant.now(Clock.system(ZoneId.of("GMT+3"))))
        .mission(restOffer.getMission())
        .ref(restOffer.getRef())
        .position(restOffer.getPosition())
        .profile(restOffer.getProfile())
        .sender(userService.getByEmail(restOffer.getSenderEmail()))
        .build();
  }

  public RestOffer toRest(Offer offer) {
    return RestOffer.builder()
        .id(offer.getId())
        .domainName(offer.getDomain().getName())
        .creationInstant(offer.getCreationInstant())
        .mission(offer.getMission())
        .ref(offer.getRef())
        .position(offer.getPosition())
        .profile(offer.getProfile())
        .senderEmail(offer.getSender().getEmail())
        .build();
  }
}
