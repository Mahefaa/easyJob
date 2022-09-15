package com.finalproject.easyjob.Unit.Validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.finalproject.easyjob.model.Offer;
import com.finalproject.easyjob.model.User;
import com.finalproject.easyjob.model.validator.OfferValidator;
import com.finalproject.easyjob.security.model.Role;
import java.util.List;
import org.junit.jupiter.api.Test;

public class OfferValidatorTest {
  private final OfferValidator validator = new OfferValidator();

  @Test
  void validator_validate_valid_ok() {
    assertDoesNotThrow(()->validator.accept(
        Offer
            .builder()
            .ref("valid_ref")
            .mission("valid_mission")
            .status(Offer.Status.AVAILABLE)
            .location("valid_location")
            .position("valid_position")
            .sender(User.builder()
                .role(Role.ADMIN)
                .build())
            .profile("valid_profile")
            .build()
    ));
  }

  @Test
  void validator_validate_singlefield_invalid_ko() {
    assertThrows(RuntimeException.class,() -> validator.accept(
        Offer
            .builder()
            .ref("valid_ref")
            .mission("valid_mission")
            .status(Offer.Status.AVAILABLE)
            .location("valid_location")
            .position("valid_position")
            .sender(User.builder()
                .role(Role.ADMIN)
                .build())
            .profile(null)
            .build()
    ),"profile is missing. ");

  }

  @Test
  void validator_validate_allfields_invalid() {
    assertThrows(RuntimeException.class,() -> validator.accept(
        Offer.builder().build()
    ), String.join(". ",List.of("ref is missing","profile is missing","position is missing","unauthorized")));

  }
}
