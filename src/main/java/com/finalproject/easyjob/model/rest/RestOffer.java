package com.finalproject.easyjob.model.rest;

import com.finalproject.easyjob.model.Status;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RestOffer {
  private int id;
  private String ref;
  private Status status;
  private String domainName;
  private String position;
  private String mission;
  private String profile;
  private Instant creationInstant;
  private String senderEmail;
}
