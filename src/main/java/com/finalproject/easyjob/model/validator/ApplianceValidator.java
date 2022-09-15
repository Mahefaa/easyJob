package com.finalproject.easyjob.model.validator;

import com.finalproject.easyjob.model.Appliance;
import org.springframework.stereotype.Component;

@Component
public class ApplianceValidator {
  public void verifyStatusUpdate(Appliance.Status status) {
    if (!status.equals(Appliance.Status.APPROVED) && !status.equals(Appliance.Status.REJECTED)) {
      throw new RuntimeException("Status cannot get back to ONGOING.");
    }
  }
}
