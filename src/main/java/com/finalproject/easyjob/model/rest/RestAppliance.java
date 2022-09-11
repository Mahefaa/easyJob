package com.finalproject.easyjob.model.rest;

import com.finalproject.easyjob.model.Appliance;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestAppliance {
  private int id;
  private int offerId;
  private String offerRef;
  private String userEmail;
  private Appliance.Status status;
  private Instant creationInstant;
}
