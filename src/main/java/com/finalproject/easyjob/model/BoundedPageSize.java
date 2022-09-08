package com.finalproject.easyjob.model;

import lombok.Getter;

public class BoundedPageSize {

  public static final int MAX_SIZE = 500;
  @Getter
  private final int value;

  public BoundedPageSize(int value) {
    if (value > MAX_SIZE) {
      throw new RuntimeException("page size must be <" + MAX_SIZE);
    }
    this.value = value;
  }
}
