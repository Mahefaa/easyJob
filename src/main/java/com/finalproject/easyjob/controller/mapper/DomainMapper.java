package com.finalproject.easyjob.controller.mapper;

import com.finalproject.easyjob.model.BoundedPageSize;
import com.finalproject.easyjob.model.Domain;
import com.finalproject.easyjob.model.Offer;
import com.finalproject.easyjob.model.PageFromOne;
import com.finalproject.easyjob.model.rest.RestDomain;
import com.finalproject.easyjob.model.rest.RestSaveDomain;
import com.finalproject.easyjob.service.OfferService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DomainMapper {
  private final OfferService offerService;

  public Domain toDomain(RestSaveDomain saveDomain) {
    return Domain.builder()
        .id(saveDomain.getId())
        .name(saveDomain.getName())
        .build();
  }

  public RestDomain toRest(Domain domain) {
    int availOffersNb = offerService.getByCriteria(new PageFromOne(1),
        new BoundedPageSize(500), Offer.Status.AVAILABLE.name()).size();
    return RestDomain.builder()
        .id(domain.getId())
        .name(domain.getName())
        .availableJobOffers(availOffersNb)
        .build();
  }
}
