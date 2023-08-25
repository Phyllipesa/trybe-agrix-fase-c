package com.betrybe.agrix.farm.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * SecurityConfiguration - Implementa uma cadeia de filtros para autenticações.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  /**
   * securityFilterChain - filtros para authenticates
   *
   * @param httpSecurity para definir a cadeia de filtros.
   * @return uma instância de SecurityFilterChain.
   * @throws Exception
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .build();
  }

  /**
   * authenticationManager
   *
   * @param authenticationConfiguration authenticationConfiguration.
   * @return uma instancia de AuthenticationManager para o controlador AuthenticationController.
   * @throws Exception
   */
  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  /**
   * passwordEncoder
   *
   * @return O PasswordEncoder irá retornar uma instância do encoder
   * utilizado para gerar o hash da senha que está no JSON e comparar
   * o resultado com o valor armazenado no banco de dados.
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
