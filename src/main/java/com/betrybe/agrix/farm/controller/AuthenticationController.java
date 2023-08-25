package com.betrybe.agrix.farm.controller;

import com.betrybe.agrix.farm.controller.dto.AuthenticationDto;
import com.betrybe.agrix.farm.model.entity.Person;
import com.betrybe.agrix.farm.service.PersonService;
import com.betrybe.agrix.farm.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Authentication Controller.
 */

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

  private final AuthenticationManager authenticationManager;

  private final PersonService personService;

  private final TokenService tokenService;

  /**
   * Recebe um bean do tipo authenticationManager e personService por injeção de dependência.
   *
   * @param authenticationManager service.
   * @param personService service.
   * @param tokenService service.
   */

  @Autowired
  public AuthenticationController(AuthenticationManager authenticationManager, PersonService personService, TokenService tokenService) {
    this.authenticationManager = authenticationManager;
    this.personService = personService;
    this.tokenService = tokenService;
  }

  /**
   * Login - loga o usuário.
   *
   * @param authenticationDto padroniza as informações vindas do body.
   * @return um token.
   */
  @PostMapping
  public ResponseEntity<String> login(@RequestBody AuthenticationDto authenticationDto) {
    UsernamePasswordAuthenticationToken usernamePassword =
        new UsernamePasswordAuthenticationToken(
            authenticationDto.username(),
            authenticationDto.password()
        );

    Authentication auth = authenticationManager.authenticate(usernamePassword);
    Person person = (Person) auth.getPrincipal();
    String token = tokenService.generateToken(person);

    return ResponseEntity.status(HttpStatus.OK).body(token);

  }
}
