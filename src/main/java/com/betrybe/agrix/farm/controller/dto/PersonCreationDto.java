package com.betrybe.agrix.farm.controller.dto;

import com.betrybe.agrix.farm.model.entity.Person;
import com.betrybe.agrix.farm.util.Role;

/**
 * PersonCreationDto - padroniza as informações do body.
 *
 * @param username farm id
 * @param password farm name
 * @param role farm size
 */
public record PersonCreationDto(String username, String password, Role role) {

  /**
   * PersonCreationDtoToEntiti - padroniza as informações vindas do body para a entiti.
   *
   * @param personCreationDto informações vindas do body.
   */

  public static Person personCreationDtoToEntiti(PersonCreationDto personCreationDto) {
    Person newPerson = new Person();

    newPerson.setUsername(personCreationDto.username);
    newPerson.setPassword(personCreationDto.password);
    newPerson.setRole(personCreationDto.role);
    return newPerson;
  }
}
