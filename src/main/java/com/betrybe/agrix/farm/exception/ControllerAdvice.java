package com.betrybe.agrix.farm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ControllerAdvice - Faz o gerenciamento de erros da aplicação.
 */
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

  /**
   * FarmNotFound - Tratamento de erro caso farm não encontrada.
   */
  @ExceptionHandler(FarmNotFound.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<String> handlerFarmNotFound(FarmNotFound error) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(error.getMessage());
  }

  /**
   * CropNotFound - Tratamento de erro caso crop não encontrada.
   */
  @ExceptionHandler(CropNotFound.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<String> handlerCropNotFound(CropNotFound error) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(error.getMessage());
  }

  /**
   * FertilizerNotFound - Tratamento de erro caso fertilizante não encontrado.
   */
  @ExceptionHandler(FertilizerNotFound.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<String> handlerFertilizerNotFound(FertilizerNotFound error) {
    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(error.getMessage());
  }

  /**
   * PersonNotFoundException - Tratamento de erro caso person não encontrada.
   */
  @ExceptionHandler(PersonNotFound.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<String> handlerPersonNotFound(PersonNotFound error) {
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(error.getMessage());
  }

  /**
   * Forbidden - Tratamento de erro caso credenciais invalidas.
   */
  @ExceptionHandler({InternalAuthenticationServiceException.class,
      BadCredentialsException.class,
      AccessDeniedException.class
  })
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public ResponseEntity<String> handlerForbidden(Exception e) {
    return ResponseEntity
        .status(HttpStatus.FORBIDDEN)
        .body(e.getMessage());
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
