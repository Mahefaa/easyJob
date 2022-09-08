package com.finalproject.easyjob.security;

import com.finalproject.easyjob.model.User;
import com.finalproject.easyjob.repository.UserRepository;
import com.finalproject.easyjob.security.model.Principal;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * This class provides a different for authentication which Spring Security uses
 */
@AllArgsConstructor
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
  private UserRepository repository;

  public static Principal getPrincipal() {
    SecurityContext context = SecurityContextHolder.getContext();
    Authentication authentication = context.getAuthentication();
    return (Principal) authentication.getPrincipal();
  }

  /**
   * The authenticate method is where you define how to authenticate.
   * Here, it is a simple comparison, but you can add more filters and mechanisms
   * Like a BCrypt hash for example, and you would match the password with the hash in your database
   *
   * @param authentication
   * @return
   * @throws BadCredentialsException
   */
  @Override
  public Authentication authenticate(Authentication authentication) throws BadCredentialsException {
    String email = authentication.getName();
    String password = authentication.getCredentials().toString();

    User user = repository.findByEmail(email)
        .orElse(null);

    if (user != null && user.getPassword().equals(password)) {
      return new UsernamePasswordAuthenticationToken(email, password,
          Arrays.asList(user.getRole()));
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
