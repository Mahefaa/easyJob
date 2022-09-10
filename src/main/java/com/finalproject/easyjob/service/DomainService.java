package com.finalproject.easyjob.service;

import com.finalproject.easyjob.model.BoundedPageSize;
import com.finalproject.easyjob.model.Domain;
import com.finalproject.easyjob.model.PageFromOne;
import com.finalproject.easyjob.model.validator.DomainValidator;
import com.finalproject.easyjob.repository.DomainRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DomainService {
  private final DomainRepository repository;
  private final DomainValidator validator;

  public List<Domain> getAllByName(PageFromOne page, BoundedPageSize pageSize, String name) {
    Pageable pageable = PageRequest.of(page.getValue() - 1, pageSize.getValue(),
        Sort.by(Sort.Direction.ASC, "name"));
    return repository.findAllByNameContainingIgnoreCase(pageable, name);
  }

  @Transactional
  public List<Domain> saveDomains(List<Domain> domains) {
    validator.accept(domains);
    return repository.saveAll(domains);
  }

  public Domain getByName(String name) {
    return repository.findByName(name).orElseThrow(() -> new RuntimeException("Domain Not Found"));
  }

  public Domain getById(int id) {
    return repository.findById(id).orElseThrow(() -> new RuntimeException("Domain Not Found"));
  }
}
