package com.betrybe.agrix.farm.security;

import java.nio.file.AccessDeniedException;

import com.betrybe.agrix.farm.security.filters.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SecurityConfiguration - Implementa uma cadeia de filtros para autenticações.
 */
@Configuration
@EnableWebSecurity()
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

  private final SecurityFilter securityFilter;

  /**
   * Recebe um bean do tipo securityFilter por injeção de dependência.
   *
   * @param securityFilter Filtro para validação de token.
   */
  @Autowired
  public SecurityConfiguration(SecurityFilter securityFilter) {
    this.securityFilter = securityFilter;
  }

  /**
   * securityFilterChain - filtros para authenticates.
   *
   * @param httpSecurity para definir a cadeia de filtros.
   * @return uma instância de SecurityFilterChain.
   * @throws AccessDeniedException AccessDeniedException.
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers(HttpMethod.POST, "/persons").permitAll()
            .requestMatchers(HttpMethod.POST, "auth/login").permitAll()
            .anyRequest().authenticated()
        )
        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }

  /**
   * authenticationManager.
   *
   * @param authenticationConfiguration authenticationConfiguration.
   * @return uma instancia de AuthenticationManager para o controlador AuthenticationController.
   * @throws Exception exception.
   */
  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception  {
    return authenticationConfiguration.getAuthenticationManager();
  }

  /**
  * passwordEncoder.
  *
  * @return O PasswordEncoder irá retornar uma instância do encoder
  *     utilizado para gerar o hash da senha que está no JSON e comparar
  *     o resultado com o valor armazenado no banco de dados.
  */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
