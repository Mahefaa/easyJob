package com.finalproject.easyjob.Unit.Validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.finalproject.easyjob.model.Appliance;
import com.finalproject.easyjob.model.validator.ApplianceValidator;
import org.junit.jupiter.api.Test;

public class ApplianceValidatorTest {
  private final ApplianceValidator validator = new ApplianceValidator();
  @Test
  void validator_validate_status_update_ok(){
    assertDoesNotThrow(()->validator.verifyStatusUpdate(Appliance.Status.REJECTED));
    assertDoesNotThrow(()->validator.verifyStatusUpdate(Appliance.Status.APPROVED));
  }
  @Test
  void validator_validate_invalid_status_update_ko(){
    assertThrows(RuntimeException.class,() -> validator.verifyStatusUpdate(Appliance.Status.ONGOING),"Status cannot get back to ONGOING.");
  }
}
