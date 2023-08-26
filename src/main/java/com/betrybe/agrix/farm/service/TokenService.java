package com.betrybe.agrix.farm.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.betrybe.agrix.farm.model.entity.Person;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * TokenService.
 */

@Service
public class TokenService {
  
  @Value("api.security.token.secret")
  private String secret;

  /**
   * generateToken - Gerador de token.
   *
   * @param person dados da pesso.
   * @return um token.
   */
  public String generateToken(Person person) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.create()
        .withIssuer("agrix")
        .withSubject(person.getUsername())
        .withExpiresAt(generateExpirationDate())
        .sign(algorithm);
  }

  /**
   * generateExpirationDate - Gera um tempo de vida para o token.
   *
   * @return retorna a hora atual com o acrecimo de 2h.
   */
  private Instant generateExpirationDate() {
    return LocalDateTime.now()
        .plusHours(2)
        .toInstant(ZoneOffset.of("-03:00"));
  }
}
