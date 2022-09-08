package com.finalproject.easyjob.repository;

import com.finalproject.easyjob.model.Domain;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainRepository extends JpaRepository<Domain, Integer> {
  List<Domain> findAllByNameContainingIgnoreCase(Pageable pageable, String name);

  Optional<Domain> findByName(String name);
}
