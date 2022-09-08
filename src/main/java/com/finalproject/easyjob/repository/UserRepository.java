package com.finalproject.easyjob.repository;

import com.finalproject.easyjob.model.User;
import com.finalproject.easyjob.security.model.Role;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByEmail(String email);

  List<User> findAllByEmailContainingIgnoreCase(Pageable pageable, String email);
  List<User> findAllByEmailContainingIgnoreCaseAndRole(Pageable pageable, String email, Role role);
}
