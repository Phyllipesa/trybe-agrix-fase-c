package com.betrybe.agrix.farm.controller.dto;

import com.betrybe.agrix.farm.model.entity.Fertilizer;

/**
 * Cria um Fertilizer dto.
 *
 * @param id id do fertilizer
 * @param name nome do fertilizer
 * @param brand marca do fertilizer
 * @param composition composição do fertilizer
 */
public record FertilizerDto(Long id, String name, String brand, String composition) {

  /**
   * Metodo para criação do DTO.
   *
   * @param fertilizer from entity.
   */
  public static FertilizerDto fertilizerEntityToDto(Fertilizer fertilizer) {
    return new FertilizerDto(
        fertilizer.getId(),
        fertilizer.getName(),
        fertilizer.getBrand(),
        fertilizer.getComposition()
    );

  }
}
