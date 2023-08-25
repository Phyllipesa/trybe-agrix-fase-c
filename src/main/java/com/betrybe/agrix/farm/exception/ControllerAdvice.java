package com.betrybe.agrix.farm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ControllerAdvice - Faz o gerenciamento de erros da aplicação.
 */
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

  /**
   * FarmNotFound - Tratamento de erro caso farm não encontrada.
   */
  @ExceptionHandler(FarmNotFound.class)
  public ResponseEntity<String> handlerFarmNotFound(FarmNotFound error) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(error.getMessage());
  }

  /**
   * CropNotFound - Tratamento de erro caso crop não encontrada.
   */
  @ExceptionHandler(CropNotFound.class)
  public ResponseEntity<String> handlerCropNotFound(CropNotFound error) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(error.getMessage());
  }

  /**
   * FertilizerNotFound - Tratamento de erro caso fertilizante não encontrado.
   */
  @ExceptionHandler(FertilizerNotFound.class)
  public ResponseEntity<String> handlerFertilizerNotFound(FertilizerNotFound error) {
    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(error.getMessage());
  }

  /**
   * Exception - Tratamento de erro interno do servidor.
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handlerException(Exception error) {
    System.out.println(error.getMessage());
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(error.getMessage());
  }
}
