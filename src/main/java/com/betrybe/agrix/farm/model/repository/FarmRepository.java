package com.betrybe.agrix.farm.model.repository;

import com.betrybe.agrix.farm.model.entity.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * FarmRepository repository to service.
 */
public interface FarmRepository extends JpaRepository<Farm, Long> {
}
