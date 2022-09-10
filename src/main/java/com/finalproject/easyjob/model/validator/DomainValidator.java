package com.finalproject.easyjob.model.validator;

import com.finalproject.easyjob.model.Domain;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DomainValidator {
  public void accept(List<Domain> domains) {
    domains.forEach(this::accept);
  }

  public void accept(Domain domain) {
    StringBuilder exceptionMessageBuilder = new StringBuilder();
    String name = domain.getName();
    if (name == null || name.isEmpty() || name.isBlank()) {
      exceptionMessageBuilder.append("name is missing");
    }
    if (!exceptionMessageBuilder.isEmpty()) {
      throw new RuntimeException(exceptionMessageBuilder.toString());
    }
  }

}
