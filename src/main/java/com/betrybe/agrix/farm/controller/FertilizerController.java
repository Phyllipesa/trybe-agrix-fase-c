package com.betrybe.agrix.farm.controller;

import com.betrybe.agrix.farm.controller.dto.FertilizerDto;
import com.betrybe.agrix.farm.model.entity.Fertilizer;
import com.betrybe.agrix.farm.service.FertilizerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Fertilizer Controller.
 */

@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {

  private final FertilizerService fertilizerService;

  /**
   * Recebe um bean do tipo FertilizerService por injeção de dependência.
   *
   * @param fertilizerService service.
   */
  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * createFertilizer = Cria uma novo fertilizer.
   *
   * @param fertilizer informações do fertilizer.
   * @return HTTP status.CREATED 201 e um fertilizerDto.
   */
  @PostMapping()
  public ResponseEntity<Fertilizer> createFertilizer(@RequestBody Fertilizer fertilizer) {
    Fertilizer newFertilizer = fertilizerService.insertFertilizer(fertilizer);
    return ResponseEntity.status(HttpStatus.CREATED).body(newFertilizer);
  }

  /**
   * getAllFertilizers = Lista todos as fertilizers registrados no DB.
   *
   * @return HTTP status.OK 200 e a lista de fertilizerDto.
   */
  @GetMapping()
  @Secured("ADMIN")
  public ResponseEntity<List<FertilizerDto>> getAllFertilizers() {
    List<Fertilizer> allFertilizers = fertilizerService.getAllFertilizers();
    List<FertilizerDto> fertilizerDtoList = allFertilizers.stream()
            .map(FertilizerDto::fertilizerEntityToDto)
            .toList();

    return ResponseEntity.ok(fertilizerDtoList);
  }

  /**
   * getFertilizerById.
   *
   * @param fertilizerId = ID da fertilizer a ser buscada.
   * @return HTTP status.OK 200 e fertilizerDto.
   */
  @GetMapping("/{fertilizerId}")
  @Secured("ADMIN")
  public ResponseEntity<FertilizerDto> getFertilizerById(@PathVariable Long fertilizerId) {
    Fertilizer fertilizer = fertilizerService.getFertilizerById(fertilizerId);
    FertilizerDto fertilizerDto = FertilizerDto.fertilizerEntityToDto(fertilizer);
    return ResponseEntity.ok(fertilizerDto);
  }

}
