package com.finalproject.easyjob.Unit.Validator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import com.finalproject.easyjob.model.User;
import com.finalproject.easyjob.model.validator.UserValidator;
import org.junit.jupiter.api.Test;

public class UserValidatorTest {
  private final UserValidator validator = new UserValidator();

  @Test
  void validator_validate_valid_ok() {
    assertThrows(RuntimeException.class, () -> validator.accept(
        User.builder().build()
    ));


  }

  @Test
  void validator_validate_singlefield_invalid_ko() {
    assertThrows(RuntimeException.class, () -> validator.accept(
        User.builder()
            .email(null)
            .password("valid_password")
            .build())
        ,"email is missing. "
    );
  }

  @Test
  void validator_validate_allfields_invalid() {
    assertThrows(RuntimeException.class, () -> validator.accept(
        User.builder().build()
    ),"");
  }
}
