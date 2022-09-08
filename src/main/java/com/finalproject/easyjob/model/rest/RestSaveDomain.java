package com.finalproject.easyjob.model.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RestSaveDomain {
  private int id;
  private String name;
}
