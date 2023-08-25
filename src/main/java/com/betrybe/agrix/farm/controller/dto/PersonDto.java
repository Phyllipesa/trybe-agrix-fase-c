package com.betrybe.agrix.farm.controller.dto;

import com.betrybe.agrix.farm.model.entity.Person;
import com.betrybe.agrix.farm.util.Role;

/**
 * Cria um person dto.
 *
 * @param id person id
 * @param username person name
 * @param role person size
 */
public record PersonDto(Long id, String username, Role role) {

  /**
   * personEntityToDto - Padroniza as informações vindas da entiti para o PersonDto.
   *
   * @param person informações vindas da entiti.
   */

  public static PersonDto personEntityToDto(Person person) {
    return new PersonDto(
        person.getId(),
        person.getUsername(),
        person.getRole());
  }
}
