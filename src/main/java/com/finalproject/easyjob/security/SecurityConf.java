package com.finalproject.easyjob.security;

import com.finalproject.easyjob.security.model.Role;
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

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authProvider);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/users/admins").hasRole(Role.ADMIN.getRole())
        .antMatchers(HttpMethod.POST, "/users/candidates").permitAll()
        .antMatchers(HttpMethod.POST, "/users/recruiters").permitAll()
        //.requestMatchers(new SelfMatcher(HttpMethod.PUT, "/users/*")).authenticated()
        .antMatchers(HttpMethod.PUT,"/users/*").hasRole(Role.ADMIN.getRole())
        //get offers as a recruiter should let you see only ur written offers
        //get offers as candidate lets you see everything
        .antMatchers(HttpMethod.GET).authenticated()
        .antMatchers(HttpMethod.PUT, "/offers").hasRole(Role.ADMIN.getRole())
        .antMatchers(HttpMethod.PUT, "/domains").hasRole(Role.ADMIN.getRole())
        .antMatchers("/**").denyAll()
        .and()
        .formLogin()
        .and()
        .httpBasic();
  }
}
