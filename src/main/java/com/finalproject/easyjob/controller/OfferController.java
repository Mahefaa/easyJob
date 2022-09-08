package com.finalproject.easyjob.controller;

import com.finalproject.easyjob.controller.mapper.OfferMapper;
import com.finalproject.easyjob.model.BoundedPageSize;
import com.finalproject.easyjob.model.PageFromOne;
import com.finalproject.easyjob.model.rest.RestOffer;
import com.finalproject.easyjob.service.OfferService;
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
public class OfferController {
  private final OfferService service;
  private final OfferMapper mapper;

  @GetMapping("/offers")
  public List<RestOffer> getOffers(@RequestParam PageFromOne page,
                                   @RequestParam BoundedPageSize pageSize,
                                   @RequestParam(required = false, defaultValue = "AVAILABLE")
                                   String status,
                                   @RequestParam(required = false, defaultValue = "")
                                   String senderEmail,
                                   @RequestParam(required = false, defaultValue = "")
                                   String domainName,
                                   @RequestParam(required = false, defaultValue = "")
                                   String offerRef,
                                   @RequestParam(required = false, defaultValue = "")
                                   String offerProfile,
                                   @RequestParam(required = false, defaultValue = "")
                                   String offerPosition,
                                   @RequestParam(required = false, defaultValue = "")
                                   String offerMission
  ) {
    return service.getByCriteria(page, pageSize, status, senderEmail, domainName, offerRef,
        offerProfile, offerPosition, offerMission).stream().map(mapper::toRest).toList();
  }

  @PutMapping("/offers")
  public List<RestOffer> createOrUpdateOffers(@RequestBody List<RestOffer> restOffers) {
    return service.saveAll(restOffers.stream().map(mapper::toDomain).toList()).stream()
        .map(mapper::toRest).toList();
  }

  @GetMapping("/offers/{ref}")
  public RestOffer getOfferByRef(@PathVariable String ref) {
    return mapper.toRest(service.getByRef(ref));
  }
}
