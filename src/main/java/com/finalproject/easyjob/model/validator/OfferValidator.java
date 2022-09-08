/*
package com.finalproject.easyjob.model.validator;

import com.finalproject.easyjob.model.Offer;
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
public class OfferValidator implements Consumer<Offer> {
  private final Validator validator;

  public void accept(List<Offer> offers) {
    offers.forEach(this);
  }

  @Override
  public void accept(Offer offer) {
    Set<ConstraintViolation<Offer>> violations = validator.validate(offer);
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
