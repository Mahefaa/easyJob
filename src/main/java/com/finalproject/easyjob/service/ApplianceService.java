package com.finalproject.easyjob.service;

import com.finalproject.easyjob.model.Appliance;
import com.finalproject.easyjob.model.BoundedPageSize;
import com.finalproject.easyjob.model.PageFromOne;
import com.finalproject.easyjob.repository.ApplianceRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApplianceService {
  private final ApplianceRepository repository;
  private final UserService userService;
  private final MessageService messageService;

  public List<Appliance> getAllByUser(PageFromOne page,
                                      BoundedPageSize pageSize,
                                      int userId,
                                      String domain,
                                      String status
  ) {
    Pageable pageable = PageRequest.of(page.getValue() - 1, pageSize.getValue(), Sort.by(
        Sort.Direction.DESC, "creationInstant", "status"));
    if (status.isBlank() || status.isEmpty()) {
      return repository.findAllByUser_IdAndOffer_Domain_NameContainingIgnoreCase(pageable, userId,
          domain);
    }
    return repository.findAllByUser_IdAndOffer_Domain_NameContainingIgnoreCaseAndStatus(pageable,
        userId,
        domain,
        Appliance.Status.valueOf(status));
  }

  public List<Appliance> getAllByUserAndOffer(PageFromOne page,
                                              BoundedPageSize pageSize,
                                              int userId,
                                              int offerId,
                                              String domain,
                                              String status
  ) {
    Pageable pageable = PageRequest.of(page.getValue() - 1, pageSize.getValue(), Sort.by(
        Sort.Direction.DESC, "creationInstant", "status"));
    if (status.isBlank() || status.isEmpty()) {
      return repository.findAllByUser_IdAndOffer_IdAndOffer_Domain_NameContainingIgnoreCase(
          pageable, userId, offerId,
          domain);
    }
    return repository.findAllByUser_IdAndOffer_IdAndOffer_Domain_NameContainingIgnoreCaseAndStatus(pageable,
        userId,
        offerId,
        domain,
        Appliance.Status.valueOf(status));
  }

  public Appliance save(Appliance appliance, int userId) {
    appliance.setUser(userService.getById(userId));
    return repository.save(appliance);
  }

  @Transactional
  public Appliance updateStatus(int userId, int offerId, int id, Appliance.Status status) {
    Appliance toUpdate = getByUserIdAndOfferIdAndId(userId, offerId, id);
    if (status.equals(Appliance.Status.APPROVED) || status.equals(Appliance.Status.REJECTED)) {
      toUpdate.setStatus(status);
      messageService.saveMessage(id, toUpdate.toString());
    }
    return toUpdate;
  }

  public Appliance getById(int id) {
    return repository.findById(id).orElseThrow(() -> new RuntimeException("Appliance Not Found"));
  }

  public Appliance getByUserIdAndId(int userId, int id) {
    return repository.findByUser_IdAndId(userId, id)
        .orElseThrow(() -> new RuntimeException("Appliance Not Found"));
  }

  public Appliance getByUserIdAndOfferIdAndId(int userId, int offerId, int id) {
    return repository.findByUser_IdAndOffer_IdAndId(userId, offerId, id)
        .orElseThrow(() -> new RuntimeException("Appliance Not Found"));
  }
}
