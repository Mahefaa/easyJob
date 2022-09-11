package com.finalproject.easyjob.security;

import com.finalproject.easyjob.security.model.Role;
import com.finalproject.easyjob.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@Slf4j
public class SecurityConf extends WebSecurityConfigurerAdapter {
  private CustomAuthenticationProvider authProvider;
  private CustomUserDetailsService userDetailsService;

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authProvider)
        .userDetailsService(userDetailsService);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .cors()
        .and()

        .authorizeRequests()

        //security
        .antMatchers(HttpMethod.GET, "/ping").permitAll()
        .antMatchers(HttpMethod.GET, "/whoami").authenticated()

        //domains
        .antMatchers(HttpMethod.GET, "/domains").permitAll()
        .antMatchers(HttpMethod.PUT, "/domains").hasRole(Role.ADMIN.getRole())

        //offers
        .antMatchers(HttpMethod.GET, "/offers/*").permitAll()
        .antMatchers(HttpMethod.GET, "/offers").permitAll()
        .antMatchers(HttpMethod.GET, "/users/*/offers/*/close")
        .hasAnyRole(Role.ADMIN.getRole(), Role.RECRUITER.getRole())
        .requestMatchers(new SelfMatcher(HttpMethod.PUT, "/users/*/offers"))
        .hasAnyRole(Role.ADMIN.getRole(), Role.RECRUITER.getRole())

        //offer Appliances
        .antMatchers(HttpMethod.PUT, "/users/*/offers/*/appliances/*/act")
        .hasAnyRole(Role.ADMIN.getRole(), Role.RECRUITER.getRole())
        .antMatchers(HttpMethod.GET, "/users/*/offers/*/appliances")
        .hasAnyRole(Role.ADMIN.getRole(), Role.RECRUITER.getRole())
        .antMatchers(HttpMethod.GET, "/users/*/offers/*/appliances/*")
        .hasAnyRole(Role.ADMIN.getRole(), Role.CANDIDATE.getRole())

        //user appliances
        .requestMatchers(new SelfMatcher(HttpMethod.GET, "/users/*/appliances")).authenticated()
        .requestMatchers(new SelfMatcher(HttpMethod.PUT, "/users/*/appliances")).authenticated()
        .requestMatchers(new SelfMatcher(HttpMethod.GET, "/users/*/appliances/*")).authenticated()

        //users
        .requestMatchers(new SelfMatcher(HttpMethod.PUT, "/users/*")).authenticated()
        .antMatchers(HttpMethod.POST, "/users/admins").hasRole(Role.ADMIN.getRole())
        .antMatchers(HttpMethod.POST, "/users/candidates").permitAll()
        .antMatchers(HttpMethod.POST, "/users/recruiters").permitAll()
        .antMatchers(HttpMethod.GET, "/users").authenticated()
        .antMatchers(HttpMethod.PUT, "/users/*/roles").hasRole(Role.ADMIN.getRole())
        .requestMatchers(new SelfMatcher(HttpMethod.GET, "/users/*/messages")).authenticated()
        .antMatchers(HttpMethod.GET, "/users/*").authenticated()

        //deny other requests
        .antMatchers("/**").denyAll()
        .and()
        .formLogin()
        .and()
        .logout().permitAll().and()
        .httpBasic();
  }
}
