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
                                   String filter
  ) {
    return service.getByCriteria(page, pageSize, filter).stream().map(mapper::toRest).toList();
  }


  @GetMapping("/offers/{id}")
  public RestOffer getOfferById(@PathVariable int id) {
    return mapper.toRest(service.getById(id));
  }

  @PutMapping("/users/{userId}/offers")
  public List<RestOffer> createOrUpdateOffers(@PathVariable("userId") int id,
                                              @RequestBody List<RestOffer> restOffers) {
    return service.saveAll(restOffers.stream().map(o -> mapper.toDomain(o, id)).toList()).stream()
        .map(mapper::toRest).toList();
  }

  @GetMapping("/users/{userId}/offers/{id}/close")
  public RestOffer closeOffer(@PathVariable int id) {
    return mapper.toRest(service.closeOffer(id));
  }
}