package com.finalproject.easyjob.security;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@AllArgsConstructor
public class SelfMatcher implements RequestMatcher {

  private static final Pattern SELFABLE_URI_PATTERN =
      // /resourceType/id/...
      Pattern.compile("/[^/]+/(?<id>[^/]+)(/.*)?");
  private final HttpMethod method;
  private final String antPattern;

  @Override
  public boolean matches(HttpServletRequest request) {
    AntPathRequestMatcher antMatcher = new AntPathRequestMatcher(antPattern, method.toString());
    if (!antMatcher.matches(request)) {
      return false;
    }
    return Objects.equals(getSelfId(request),
        String.valueOf(CustomAuthenticationProvider.getPrincipal().getUserId()));
  }

  private String getSelfId(HttpServletRequest request) {
    Matcher uriMatcher = SELFABLE_URI_PATTERN.matcher(request.getRequestURI());
    return uriMatcher.find() ? uriMatcher.group("id") : null;
  }
}
