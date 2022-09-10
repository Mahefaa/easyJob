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
public class RestMessage {
  private String email;
  private String otherEmail;
  private String content;
  private Instant creationInstant;
}
