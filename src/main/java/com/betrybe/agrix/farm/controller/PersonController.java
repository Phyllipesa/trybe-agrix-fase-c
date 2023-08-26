package com.betrybe.agrix.farm.controller;

import com.betrybe.agrix.farm.controller.dto.PersonCreationDto;
import com.betrybe.agrix.farm.controller.dto.PersonDto;
import com.betrybe.agrix.farm.model.entity.Person;
import com.betrybe.agrix.farm.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Person Controller.
 */

@RestController
@RequestMapping(value = "/persons")
public class PersonController {

  private final PersonService personService;

  /**
   * Recebe um bean do tipo PersonService por injeção de dependência.
   *
   * @param personService service.
   */

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * registerPerson = Registra uma pessoa no DB.
   *
   * @param personCreationDto informações da pessoa.
   * @return HTTP status.CREATED 201 e um redistrictedPersonDto.
   */

  @PostMapping()
  public ResponseEntity<PersonDto> registerPerson(
      @RequestBody PersonCreationDto personCreationDto
  ) {

    UserDetails userDetails = personService.loadUserByUsername(personCreationDto.username());

    if (userDetails != null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    Person person = PersonCreationDto.personCreationDtoToEntiti(personCreationDto);
    Person newPerson = personService.create(person);
    PersonDto redistrictedPersonDto = PersonDto.personEntityToDto(newPerson);

    return ResponseEntity.status(HttpStatus.CREATED).body(redistrictedPersonDto);
  }
}
