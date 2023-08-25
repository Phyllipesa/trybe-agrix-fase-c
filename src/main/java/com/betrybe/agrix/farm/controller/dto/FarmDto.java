package com.betrybe.agrix.farm.controller.dto;

import com.betrybe.agrix.farm.model.entity.Farm;

/**
 * Cria um Farm dto.
 *
 * @param id farm id
 * @param name farm name
 * @param size farm size
 */
public record FarmDto(Long id, String name, Double size) {

  /**
   * Metodo para criação do DTO.
   *
   * @param farm from entity.
   */
  public static FarmDto farmEntityToDto(Farm farm) {
    return new FarmDto(
        farm.getId(),
        farm.getName(),
        farm.getSize());
  }
}
