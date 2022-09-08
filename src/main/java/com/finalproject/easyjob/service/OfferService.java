package com.finalproject.easyjob.service;

import com.finalproject.easyjob.model.BoundedPageSize;
import com.finalproject.easyjob.model.Offer;
import com.finalproject.easyjob.model.PageFromOne;
import com.finalproject.easyjob.repository.OfferRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OfferService {
  private final OfferRepository repository;
  //private final OfferValidator validator;

  public List<Offer> getByCriteria(PageFromOne page, BoundedPageSize pageSize, String status,
                                   String senderEmail,
                                   String domainName,
                                   String offerRef,
                                   String offerProfile,
                                   String offerPosition,
                                   String offerMission) {
    Pageable pageable = PageRequest.of(page.getValue() - 1, pageSize.getValue(),
        Sort.by(Sort.Direction.ASC, "creationInstant"));
    return repository.findAllByCriteria(pageable, status, senderEmail, domainName, offerRef,
        offerProfile, offerPosition, offerMission);
  }

  public List<Offer> saveAll(List<Offer> offers) {
    //validator.accept(offers);
    return repository.saveAll(offers);
  }

  public Offer getByRef(String ref) {
    return repository.findByRef(ref).orElseThrow(() -> new RuntimeException("Offer not found"));
  }
}
