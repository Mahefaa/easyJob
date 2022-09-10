package com.finalproject.easyjob.model.rest;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RestAppliance {
  private int id;
  private Instant creationInstant;
  private int offerId;
  private String userEmail;
}
