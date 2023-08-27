package com.betrybe.agrix.farm.controller;

import com.betrybe.agrix.farm.controller.dto.CropDto;
import com.betrybe.agrix.farm.controller.dto.FarmDto;
import com.betrybe.agrix.farm.model.entity.Crop;
import com.betrybe.agrix.farm.model.entity.Farm;
import com.betrybe.agrix.farm.service.FarmService;
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
 * Farm Controller.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {

  private final FarmService farmService;

  /**
   * Recebe um bean do tipo FarmService por injeção de dependência.
   *
   * @param farmService service.
   */
  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * createFarm = Cria uma nova farm.
   *
   * @param farm informações da farm.
   * @return HTTP status.CREATED 201 e um farmDto.
   */
  @PostMapping()
  public ResponseEntity<Farm> createFarm(@RequestBody Farm farm) {
    Farm newFarm = farmService.insertFarm(farm);
    return ResponseEntity.status(HttpStatus.CREATED).body(newFarm);
  }

  /**
   * getAllFarms = Lista todas as farms registradas no DB.
   *
   * @return HTTP status.OK 200 e a lista de farmsDto.
   */
  @GetMapping()
  @Secured({"ADMIN", "MANAGER", "USER"})
  public ResponseEntity<List<FarmDto>> getAllFarms() {
    List<Farm> allFarms = farmService.getAllFarms();
    List<FarmDto> farmsDtoList = allFarms.stream()
        .map(FarmDto::farmEntityToDto)
        .toList();

    return ResponseEntity.ok(farmsDtoList);
  }

  /**
   * getFarmById.
   *
   * @param id = ID da farm a ser buscada.
   * @return HTTP status.OK 200 e farmDto.
   */
  @GetMapping("/{id}")
  @Secured({"ADMIN", "MANAGER", "USER"})
  public ResponseEntity<FarmDto> getFarmById(@PathVariable Long id) {
    Farm farm = farmService.getFarmById(id);
    FarmDto farmDto = FarmDto.farmEntityToDto(farm);
    return ResponseEntity.ok(farmDto);
  }

  /**
   * createCrop insere uma nova crop no DB.
   *
   * @param farmId ID da farm a ser procurada.
   * @param crop informações a serem inseridas.
   * @return retorna HTTP status.CREATED e os dados da crop inserida.
   */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDto> createCrop(@PathVariable Long farmId, @RequestBody Crop crop) {
    Crop newCrop = farmService.insertCrop(farmId, crop);
    CropDto novo = CropDto.cropEntityToDto(newCrop);
    return ResponseEntity.status(HttpStatus.CREATED).body(novo);
  }

  /**
   * getCropsByFarmId = Lista todas as Crops registradas no DB referentes a "farmId" informada.
   *
   * @param farmId = ID da farm informada.
   * @return HTTP status.OK 200 e a lista das CropsDto.
   */
  @GetMapping("/{farmId}/crops")
  public ResponseEntity<List<CropDto>> getCropsByFarmId(@PathVariable Long farmId) {
    Farm farm = farmService.getFarmById(farmId);

    List<Crop> allCrops = farm.getCrops();
    List<CropDto> cropsDto = allCrops.stream()
        .map(CropDto::cropEntityToDto)
        .toList();

    return ResponseEntity.ok(cropsDto);
  }
}
