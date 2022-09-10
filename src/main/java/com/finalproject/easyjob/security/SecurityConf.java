package com.finalproject.easyjob.security;

import com.finalproject.easyjob.security.model.Role;
import com.finalproject.easyjob.service.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
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
        //users
        .antMatchers(HttpMethod.POST, "/users/admins").hasRole(Role.ADMIN.getRole())
        .antMatchers(HttpMethod.POST, "/users/candidates").permitAll()
        .antMatchers(HttpMethod.POST, "/users/recruiters").permitAll()
        .antMatchers(HttpMethod.PUT, "/users/*").authenticated()
        .antMatchers(HttpMethod.GET, "/users").authenticated()
        .antMatchers(HttpMethod.GET, "/users/*").authenticated()
        //TODO self matcher on put /users/*

        //offers
        .antMatchers(HttpMethod.GET, "/offers").permitAll()
        .antMatchers(HttpMethod.PUT, "/users/*/offers")
        .hasAnyRole(Role.ADMIN.getRole(), Role.RECRUITER.getRole())
        //TODO self matcher on put /users/*/offers
        .antMatchers(HttpMethod.GET, "/offers/*").permitAll()
        .antMatchers(HttpMethod.GET, "/users/*/offers/*/close")
        .hasAnyRole(Role.ADMIN.getRole(), Role.RECRUITER.getRole())

        //domains
        .antMatchers(HttpMethod.GET, "/domains").permitAll()
        .antMatchers(HttpMethod.PUT, "/domains").hasRole(Role.ADMIN.getRole())

        //user appliances
        .antMatchers(HttpMethod.GET, "/users/*/appliances").authenticated()
        //TODO get appliances self Matcher
        .antMatchers(HttpMethod.GET, "/users/*/appliances/*").authenticated()

        //offer Appliances
        .antMatchers(HttpMethod.GET, "/offers/*/appliances")
        .hasAnyRole(Role.ADMIN.getRole(), Role.RECRUITER.getRole())
        .antMatchers(HttpMethod.GET, "/offers/*/appliances/*")
        .hasAnyRole(Role.ADMIN.getRole(), Role.CANDIDATE.getRole())

        .antMatchers("/**").denyAll()
        .and()
        .formLogin()
        .and()
        .logout().permitAll().and()
        .httpBasic();
  }
}
