package com.finalproject.easyjob.repository;

import com.finalproject.easyjob.model.Offer;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {
  @Query("select o from Offer o where upper(o.status) like upper(:filter) or o.sender.email like lower(concat('%',:filter,'%')) or lower(o.domain.name) like lower(concat('%',:filter,'%')) or lower(o.position) like lower(concat('%',:filter,'%')) or lower(o.mission) like lower(concat('%',:filter,'%')) or lower(o.profile) like lower(concat('%',:filter,'%'))")
  List<Offer> findAllByCriteria(Pageable pageable, @Param("filter") String filter);

  /*
  filter supposed to reduce above query's length
  @Query("select o from Offer o where lower(concat('%',:filter,'%')) in (upper(o.status),o.sender.email,lower(o.domain.name),lower(o.position),lower(o.mission),lower(o.profile))")
  */
}
