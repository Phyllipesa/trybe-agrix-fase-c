package com.betrybe.agrix.farm.service;

import com.betrybe.agrix.farm.exception.FarmNotFound;
import com.betrybe.agrix.farm.model.entity.Crop;
import com.betrybe.agrix.farm.model.entity.Farm;
import com.betrybe.agrix.farm.model.repository.CropRepository;
import com.betrybe.agrix.farm.model.repository.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Farm Service.
 */
@Service
public class FarmService {
  @Autowired
  private final FarmRepository farmRepository;
  private final CropRepository cropRepository;

  /**
   * Constructor = faz a injeção de dependencia na services especificadas.
   *
   * @param farmRepository farm repository
   */

  public FarmService(
      FarmRepository farmRepository,
      CropRepository cropRepository
  ) {
    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;
  }

  /**
   * insertFarm insere uma nova farm no DB.
   *
   * @param farm informações a serem inseridas.
   * @return retorna os dados da farm inserida.
   */
  public Farm insertFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  /**
   * getAllFarms retorna todas farms registradas no DB.
   *
   * @return Uma lista de farm.
   */
  public List<Farm> getAllFarms() {
    return farmRepository.findAll();
  }

  /**
   * getFarmById busca por uma farm com o ID especificado.
   *
   * @param id O id da farm a ser procurada.
   * @return farm com o ID especificado.
   */
  public Farm getFarmById(Long id) {
    Optional<Farm> optionalFarm = farmRepository.findById(id);

    if (optionalFarm.isEmpty()) {
      throw new FarmNotFound();
    }

    return optionalFarm.get();
  }

  /**
   * insertFarm insere uma nova farm no DB.
   *
   * @param farmId ID da farm a ser procurada.
   * @param crop informações a serem inseridas.
   * @return retorna os dados da crop inserida.
   */
  public Crop insertCrop(Long farmId, Crop crop) {
    Optional<Farm> optionalFarm = farmRepository.findById(farmId);

    if (optionalFarm.isEmpty()) {
      throw new FarmNotFound();
    }

    crop.setFarm(optionalFarm.get());
    Crop newCrop = cropRepository.save(crop);

    return newCrop;
  }
}
