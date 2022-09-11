package com.finalproject.easyjob.security;

import com.finalproject.easyjob.model.User;
import com.finalproject.easyjob.repository.UserRepository;
import com.finalproject.easyjob.security.model.Principal;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * This class provides a different way for authentication which Spring Security uses
 */
@AllArgsConstructor
@Component
public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
  private static final BCryptPasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
  private UserRepository repository;

  public static Principal getPrincipal() {
    SecurityContext context = SecurityContextHolder.getContext();
    Authentication authentication = context.getAuthentication();
    return (Principal) authentication.getPrincipal();
  }

  @Override
  protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                UsernamePasswordAuthenticationToken authentication)
      throws AuthenticationException {

  }

  @Override
  protected UserDetails retrieveUser(String username,
                                     UsernamePasswordAuthenticationToken authentication)
      throws AuthenticationException {
    String email = authentication.getName();
    String password = authentication.getCredentials().toString();

    User user = repository.findByEmail(email)
        .orElse(null);

    if (user != null && PASSWORD_ENCODER.matches(password, user.getPassword())) {
      return new Principal(user);
    } else {
      throw new BadCredentialsException(
          "Invalid credentials, please check your password and username.");
    }
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
