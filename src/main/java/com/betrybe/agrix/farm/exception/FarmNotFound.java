package com.betrybe.agrix.farm.exception;

/**
 * FarmNotFound - Classe de erro customizada para Farm.
 */

public class FarmNotFound extends RuntimeException {
  /**
   * Constructor passando uma messangem de erro padrão.
   */
  public FarmNotFound() {
    super("Fazenda não encontrada!");
  }
}
