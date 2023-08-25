package com.betrybe.agrix.farm.service;

import com.betrybe.agrix.farm.exception.CropNotFound;
import com.betrybe.agrix.farm.exception.FertilizerNotFound;
import com.betrybe.agrix.farm.model.entity.Crop;
import com.betrybe.agrix.farm.model.entity.Fertilizer;
import com.betrybe.agrix.farm.model.repository.CropRepository;
import com.betrybe.agrix.farm.model.repository.FertilizerRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Crop Service.
 */

@Service
public class CropService {

  @Autowired
  private final CropRepository cropRepository;
  private final FertilizerRepository fertilizerRepository;

  /**
   * Constructor = faz a injeção de dependencia na services especificadas.
   *
   * @param cropRepository farm repository
   */
  public CropService(CropRepository cropRepository, FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * getAllCrops retorna todas CROPS registradas no DB.
   *
   * @return Uma lista de crop.
   */
  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }

  /**
   * getCropById busca por uma farm com o ID especificado.
   *
   * @param id O id da crop a ser procurada.
   * @return crop com o ID especificado.
   */
  public Crop getCropById(Long id) {
    Optional<Crop> optionalCrop = cropRepository.findById(id);

    if (optionalCrop.isEmpty()) {
      throw new CropNotFound();
    }

    return optionalCrop.get();
  }

  /**
   * cropsByHarvestDate busca por todas as Crops em que estejam
   * entre o periodo de plantação START e END.
   *  OBS: Lista deve incluir datas que sejam iguais à de início ou à de fim.
   *
   * @param start data de inicio da busca.
   * @param end data de termino da busca.
   * @return Lista de crops com as datas especificadas.
   */
  public List<Crop> cropsByHarvestDate(
      LocalDate start,
      LocalDate end
  ) {
    return cropRepository.findByHarvestDate(start, end);
  }

  /**
   * cropAndFertilizerAssociation associação entre
   * as tabelas Crop e Fertilizer no DB.
   *
   * @param cropId ID da crop.
   * @param fertilizerId ID da fertilizante.
   */
  public void cropAndFertilizerAssociation(Long cropId, Long fertilizerId) {
    Optional<Crop> optionalCrop = cropRepository.findById(cropId);
    Optional<Fertilizer> optionalFertilizer = fertilizerRepository.findById(fertilizerId);

    if (optionalCrop.isEmpty()) {
      throw new CropNotFound();
    }

    if (optionalFertilizer.isEmpty()) {
      throw new FertilizerNotFound();
    }

    Crop crop = optionalCrop.get();
    Fertilizer fertilizer = optionalFertilizer.get();

    crop.getFertilizers().add(fertilizer);
    cropRepository.save(crop);
  }
}
