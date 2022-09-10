package com.finalproject.easyjob.repository;

import com.finalproject.easyjob.model.Offer;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
  @Query("select o from Offer o where upper(o.status) = upper(:status) and lower(o.sender.email) like lower(concat('%',:email,'%')) and lower(o.domain.name) like lower(concat('%',:domain,'%')) and lower(o.ref) like lower(concat('%',:ref,'%')) and lower(o.profile) like lower(concat('%',:profile,'%')) and lower(o.position) like lower(concat('%',:position,'%')) and o.mission like :mission")
  List<Offer> findAllByCriteria(Pageable pageable,
                                @Param("status") String status,
                                @Param("email") String senderEmail,
                                @Param("domain") String domainName,
                                @Param("ref") String offerRef,
                                @Param("profile") String offerProfile,
                                @Param("position") String offerPosition,
                                @Param("mission") String offerMission
  );

  Optional<Offer> findByRef(String ref);
}
