/*
package com.finalproject.easyjob.model.validator;

import com.finalproject.easyjob.model.Domain;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DomainValidator implements Consumer<Domain> {
  private final Validator validator;

  public void accept(List<Domain> domains) {
    domains.forEach(this);
  }

  @Override
  public void accept(Domain domain) {
    Set<ConstraintViolation<Domain>> violations = validator.validate(domain);
    if (!violations.isEmpty()) {
      String constraintMessages = violations
          .stream()
          .map(ConstraintViolation::getMessage)
          .collect(Collectors.joining(". "));
      throw new RuntimeException(constraintMessages);
    }
  }
}
*/
