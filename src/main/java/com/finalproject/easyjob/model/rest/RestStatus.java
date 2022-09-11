package com.finalproject.easyjob.model.rest;

import com.finalproject.easyjob.model.Appliance;
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
public class RestStatus {
  private Appliance.Status status;
}
