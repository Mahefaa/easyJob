package com.finalproject.easyjob.converter;

import com.finalproject.easyjob.model.BoundedPageSize;
import org.springframework.core.convert.converter.Converter;

public class PageSizeConverter implements Converter<String, BoundedPageSize> {

  @Override
  public BoundedPageSize convert(String source) {
    return new BoundedPageSize(Integer.parseInt(source));
  }
}
