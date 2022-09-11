package com.finalproject.easyjob.model.rest;

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
  private int offerIdentifier;
  private String offerRef;
  private String userEmail;
  private Instant creationInstant;
}
