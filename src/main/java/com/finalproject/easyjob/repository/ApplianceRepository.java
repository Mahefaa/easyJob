package com.finalproject.easyjob.repository;

import com.finalproject.easyjob.model.Appliance;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplianceRepository extends JpaRepository<Appliance, Integer> {

  Optional<Appliance> findByUser_IdAndId(int userId, int id);
  Optional<Appliance> findByOffer_IdAndId(int userId, int id);

  List<Appliance> findAllByUser_IdAndOffer_Domain_NameContainingIgnoreCase(Pageable pageable,
                                                                           int userId,
                                                                           String domain);

  List<Appliance> findAllByUser_IdAndOffer_Domain_NameContainingIgnoreCaseAndStatus(
      Pageable pageable,
      int userId,
      String domain,
      Appliance.Status status);

  List<Appliance> findAllByOffer_IdAndOffer_Domain_NameContainingIgnoreCase(Pageable pageable,
                                                                            int offerId,
                                                                            String domain);

  List<Appliance> findAllByOffer_IdAndOffer_Domain_NameContainingIgnoreCaseAndStatus(
      Pageable pageable,
      int offerId,
      String domain,
      Appliance.Status status);
}
