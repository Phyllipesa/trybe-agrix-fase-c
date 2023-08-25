package com.betrybe.agrix.farm.controller.dto;

import com.betrybe.agrix.farm.model.entity.Crop;
import java.time.LocalDate;

/**
 * Criando uma CropDto.
 *
 * @param id da Crop.
 * @param name nome do alimento que foi plantado.
 * @param plantedArea area plantada.
 * @param plantedData data do plantio.
 * @param harvestData data da colheita.
 * @param farmId id da farm.

 */
public record CropDto(
    Long id,
    String name,
    Double plantedArea,
    LocalDate plantedDate,
    LocalDate harvestDate,
    Long farmId
) {

  /**
   * cropEntityToDto.
   * Recebe uma Crop vinda do DB e passa as informações relevantes para o CropDto.
   */
  public static CropDto cropEntityToDto(Crop crop) {
    return new CropDto(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getPlantedDate(),
        crop.getHarvestDate(),
        crop.getFarm().getId()
    );
  }
}
