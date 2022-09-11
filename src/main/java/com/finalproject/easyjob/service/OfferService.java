package com.finalproject.easyjob.service;

import com.finalproject.easyjob.model.BoundedPageSize;
import com.finalproject.easyjob.model.Offer;
import com.finalproject.easyjob.model.PageFromOne;
import com.finalproject.easyjob.model.validator.OfferValidator;
import com.finalproject.easyjob.repository.OfferRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OfferService {
  private final OfferRepository repository;
  private final OfferValidator validator;

  public List<Offer> getByCriteria(PageFromOne page,
                                   BoundedPageSize pageSize,
                                   String filter) {
    Pageable pageable = PageRequest.of(page.getValue() - 1, pageSize.getValue(),
        Sort.by(Sort.Direction.ASC, "creationInstant"));
    return repository.findAllByCriteria(pageable, filter);
  }

  @Transactional
  public Offer closeOffer(int id) {
    Offer toUpdate = getById(id);
    toUpdate.setStatus(Offer.Status.TAKEN);
    //alertOfferCandidates(id);
    return toUpdate;
  }
  /*
  this creates a loop and springboot application does
  public void alertOfferCandidates(int offerId) {
    applianceService
        .getByOfferId(offerId)
        .stream()
        .map(Appliance::getUser)
        .filter(User::getEnabled)
        .map(User::getId)
        .forEach((id) -> messageService.saveMessage(id, "offer with ID " + offerId + " has been closed."));
  }*/

  @Transactional
  public List<Offer> saveAll(List<Offer> offers) {
    validator.accept(offers);
    return repository.saveAll(offers);
  }

  public Offer getById(int id) {
    return repository.findById(id).orElseThrow(() -> new RuntimeException("Offer not found"));
  }
}
