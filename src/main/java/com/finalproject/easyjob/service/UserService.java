package com.finalproject.easyjob.service;

import com.finalproject.easyjob.model.BoundedPageSize;
import com.finalproject.easyjob.model.PageFromOne;
import com.finalproject.easyjob.model.User;
import com.finalproject.easyjob.model.validator.UserValidator;
import com.finalproject.easyjob.repository.UserRepository;
import com.finalproject.easyjob.security.model.Role;
import java.util.List;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
  private final UserRepository repository;
  private final UserValidator validator;

  public List<User> getAllByCriteria(PageFromOne page, BoundedPageSize pageSize, String email,
                                     String role) {
    Pageable pageable = PageRequest.of(page.getValue() - 1, pageSize.getValue(), Sort.by(
        Sort.Direction.ASC, "joinedInstant"));
    if (role.isBlank() || role.isEmpty()) {
      return repository.findAllByEmailContainingIgnoreCase(pageable, email);
    }
    return repository.findAllByEmailContainingIgnoreCaseAndRole(pageable, email,
        Role.valueOf(role));
  }

  public User getByEmail(String email) {
    return repository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
  }

  @Transactional
  public User createUser(User user) {
    validator.accept(user);
    return repository.save(user);
  }

  public User getById(int id) {
    return repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
  }

  @Transactional
  public User updateUser(int id, User user) {
    validator.accept(user);
    User toUpdate = getById(id);
    toUpdate.setEmail(user.getEmail());
    toUpdate.setRole(user.getRole());
    toUpdate.setEnabled(user.getEnabled());
    toUpdate.setPassword(user.getPassword());
    return repository.save(toUpdate);
  }
}
