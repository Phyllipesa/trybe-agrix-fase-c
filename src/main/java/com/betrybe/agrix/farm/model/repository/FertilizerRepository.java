package com.betrybe.agrix.farm.model.repository;

import com.betrybe.agrix.farm.model.entity.Fertilizer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * FertilizerRepository repository to service.
 */
public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {
}
