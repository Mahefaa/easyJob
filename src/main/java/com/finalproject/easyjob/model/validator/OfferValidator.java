package com.finalproject.easyjob.model.validator;

import com.finalproject.easyjob.model.Offer;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OfferValidator {

  public void accept(List<Offer> offers) {
    offers.forEach(this::accept);
  }

  public void accept(Offer offer) {
    List<String> exceptions = new ArrayList<>();
    if (offer.getProfile() == null) {
      exceptions.add("profile is missing");
    }
    if (offer.getRef() == null) {
      exceptions.add("ref is missing");
    }
    if (offer.getPosition() == null) {
      exceptions.add("position is missing");
    }
    if (!exceptions.isEmpty()) {
      String constraintMessages = String.join(". ", exceptions);
      throw new RuntimeException(constraintMessages);
    }
  }
}
