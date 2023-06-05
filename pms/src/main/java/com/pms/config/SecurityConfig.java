package com.pms.config;

import com.pms.filter.JwtFilter;
import com.pms.util.CustomPasswordEncoder;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  @Autowired
  private CustomPasswordEncoder customPasswordEncoder;

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private JwtFilter jwtFilter;

//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth.userDetailsService(userDetailsService).passwordEncoder(customPasswordEncoder.getPasswordEncoder());
//  }


  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() throws Exception {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(this.userDetailsService);
    provider.setPasswordEncoder(customPasswordEncoder.getPasswordEncoder());
    return provider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http = http.csrf().disable().cors().disable();

    http = http.sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                            .and();

    http = http.exceptionHandling()
                    .authenticationEntryPoint((request, response, exception) -> {
                      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
                    }).and();

    http.authenticationProvider(daoAuthenticationProvider());

    http
            .authorizeHttpRequests((auth) -> auth
                    .requestMatchers("/api/auth/**")
                    .permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin();

    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

//            .httpBasic(withDefaults());
    return http.build();
  }

}
