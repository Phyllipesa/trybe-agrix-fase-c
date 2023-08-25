package com.betrybe.agrix.farm.exception;

/**
 * FertilizerNotFound - Classe de erro customizada para Fertilizante.
 */

public class FertilizerNotFound extends RuntimeException {

  /**
   * Constructor passando uma messangem de erro padrão.
   */
  public FertilizerNotFound() {
    super("Fertilizante não encontrado!");
  }
}
