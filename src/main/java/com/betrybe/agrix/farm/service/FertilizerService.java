package com.betrybe.agrix.farm.service;

import com.betrybe.agrix.farm.exception.FertilizerNotFound;
import com.betrybe.agrix.farm.model.entity.Fertilizer;
import com.betrybe.agrix.farm.model.repository.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Fertilizer Service.
 */

@Service
public class FertilizerService {

  @Autowired
  private final FertilizerRepository fertilizerRepository;

  /**
   * Constructor = faz a injeção de dependencia na services especificadas.
   *
   * @param fertilizerRepository fertilizer repository
   */

  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * insertFertilizer insere um novo fertilizer no DB.
   *
   * @param fertilizer informações do fertilizer a serem inseridas.
   * @return dados da fertilizer inserida.
   */
  public Fertilizer insertFertilizer(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  /**
   * getAllFertilizers retorna todos fertilizers registradas no DB.
   *
   * @return Uma lista de fertilizers.
   */
  public List<Fertilizer> getAllFertilizers() {
    return fertilizerRepository.findAll();
  }

  /**
   * getFertilizersById busca por um fertilizer com o ID especificado.
   *
   * @param id O id do fertilizer a ser procurado.
   * @return fertilizer com o ID especificado.
   */
  public Fertilizer getFertilizerById(Long id) {
    Optional<Fertilizer> optionalFertilizer = fertilizerRepository.findById(id);

    if (optionalFertilizer.isEmpty()) {
      throw new FertilizerNotFound();
    }

    return optionalFertilizer.get();
  }
}
