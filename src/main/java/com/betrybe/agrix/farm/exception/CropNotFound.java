package com.betrybe.agrix.farm.exception;

/**
 * CropNotFound - Classe de erro customizada para Crop.
 */

public class CropNotFound extends RuntimeException {
  /**
   * Constructor passando uma messangem de erro padrão.
   */
  public CropNotFound() {
    super("Plantação não encontrada!");
  }
}
