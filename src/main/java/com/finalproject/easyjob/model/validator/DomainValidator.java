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
    if (domain.getName() == null) {
      exceptionMessageBuilder.append("domain name is missing");
    }
    if (!exceptionMessageBuilder.isEmpty()) {
      throw new RuntimeException(exceptionMessageBuilder.toString());
    }
  }

}
