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

  Optional<Appliance> findByUser_IdAndOffer_IdAndId(int userId, int offerId, int id);

  List<Appliance> findAllByUser_IdAndOffer_Domain_NameContainingIgnoreCase(Pageable pageable,
                                                                           int userId,
                                                                           String domain);

  List<Appliance> findAllByUser_IdAndOffer_Domain_NameContainingIgnoreCaseAndStatus(
      Pageable pageable,
      int userId,
      String domain,
      Appliance.Status status);

  List<Appliance> findAllByUser_IdAndOffer_IdAndOffer_Domain_NameContainingIgnoreCase(
      Pageable pageable,
      int userId,
      int offerId,
      String domain);

  List<Appliance> findAllByUser_IdAndOffer_IdAndOffer_Domain_NameContainingIgnoreCaseAndStatus(
      Pageable pageable,
      int userId,
      int offerId,
      String domain,
      Appliance.Status status);
  List<Appliance> findAllByOffer_Id(int offerId);
}
