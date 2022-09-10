package com.finalproject.easyjob.service;

import com.finalproject.easyjob.model.User;
import com.finalproject.easyjob.repository.UserRepository;
import com.finalproject.easyjob.security.model.Principal;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
  private UserRepository userRepository;

  @Override
  public Principal loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByEmail(username);
    return new Principal(
        user.orElseThrow(() -> new UsernameNotFoundException(username + " not found.")));
  }
}
