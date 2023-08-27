package com.betrybe.agrix.farm.controller;

import com.betrybe.agrix.farm.controller.dto.CropDto;
import com.betrybe.agrix.farm.controller.dto.FertilizerDto;
import com.betrybe.agrix.farm.model.entity.Crop;
import com.betrybe.agrix.farm.model.entity.Fertilizer;
import com.betrybe.agrix.farm.service.CropService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Crop Controller.
 */

@RestController
@RequestMapping(value = "/crops")
public class CropController {

  private final CropService cropService;

  /**
   * Recebe um bean do tipo CropService por injeção de dependência.
   *
   * @param cropService service.
   */
  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * getAllCrops = Lista todas as crops registradas no DB.
   *
   * @return HTTP status 200 e a lista de cropDto.
   */
  @GetMapping()
  @Secured({"ADMIN", "MANAGER"})
  public ResponseEntity<List<CropDto>> getAllCrops() {
    List<Crop> allCrops = cropService.getAllCrops();
    List<CropDto> cropsDtoList = allCrops.stream()
        .map(crop -> CropDto.cropEntityToDto(crop))
        .toList();

    return ResponseEntity.ok(cropsDtoList);
  }

  /**
   * getCropById.
   *
   * @param id = ID da crop a ser buscada.
   * @return HTTP status.OK 200 e cropDto.
   */
  @GetMapping("/{id}")
  @Secured({"ADMIN", "MANAGER"})
  public ResponseEntity<CropDto> getCropById(@PathVariable Long id) {
    Crop crop = cropService.getCropById(id);
    CropDto cropDto = CropDto.cropEntityToDto(crop);
    return ResponseEntity.ok(cropDto);
  }

  /**
   * getCropByDate.
   *
   * @param start = data em que a plantação começou.
   * @param end = data em que a plantação terminou.
   * @return HTTP status.OK 200 e cropDto.
   */
  @GetMapping("/search")
  public ResponseEntity<List<CropDto>> cropsByHarvestDate(
      @RequestParam LocalDate start,
      @RequestParam LocalDate end
  ) {
    List<Crop> allCrops = cropService.cropsByHarvestDate(start, end);
    List<CropDto> cropsDtoList = allCrops.stream()
        .map(CropDto::cropEntityToDto)
        .toList();
    return ResponseEntity.ok(cropsDtoList);
  }

  /**
   * cropAndFertilizerAssociation cria uma associação entre
   * as tabelas Crop e Fertilizer no DB.
   *
   * @param cropId ID da crop.
   * @param fertilizerId ID da fertilizante.
   * @return retorna HTTP status.CREATED com a mensagem
   * @mensagem Fertilizante e plantação associados com sucesso!.
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> cropAndFertilizerAssociation(
        @PathVariable Long cropId,
        @PathVariable Long fertilizerId
  ) {
    cropService.cropAndFertilizerAssociation(cropId, fertilizerId);
    return ResponseEntity.status(HttpStatus.CREATED)
            .body("Fertilizante e plantação associados com sucesso!");
  }

  /**
   * getFertilizerByCropId = Lista todas fertilizantes
   * registrados no DB referentes a "cropId" informada.
   *
   * @param cropId = ID da crop informada.
   * @return HTTP status.OK 200 e a lista das fertilizerDto.
   */
  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity<List<FertilizerDto>> getFertilizerByCropId(@PathVariable Long cropId) {
    Crop crop = cropService.getCropById(cropId);

    List<Fertilizer> allfertilizers = crop.getFertilizers();
    List<FertilizerDto> fertilizerDtos = allfertilizers.stream()
            .map(FertilizerDto::fertilizerEntityToDto)
            .toList();

    return ResponseEntity.ok(fertilizerDtos);
  }
}
