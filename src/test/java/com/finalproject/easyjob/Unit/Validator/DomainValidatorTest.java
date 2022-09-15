package com.finalproject.easyjob.Unit.Validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.finalproject.easyjob.model.Domain;
import com.finalproject.easyjob.model.validator.DomainValidator;
import org.junit.jupiter.api.Test;

public class DomainValidatorTest {
  private final DomainValidator validator = new DomainValidator();

  @Test
  void validator_validate_valid_ok() {
    assertDoesNotThrow(() -> validator.accept(
        Domain
            .builder()
            .name("valid_name")
            .build()));

  }

  @Test
  void validator_validate_allfields_invalid_ko() {
    assertThrows(RuntimeException.class, () -> validator.accept(
        Domain.builder().build()
    ), "name is missing");
    assertThrows(RuntimeException.class, () -> validator.accept(
        Domain.builder()
            .name("")
            .build()
    ), "name is missing");
    assertThrows(RuntimeException.class, () -> validator.accept(
        Domain.builder()
            .name(" ".repeat(100))
            .build()
    ), "name is missing");
  }
}
