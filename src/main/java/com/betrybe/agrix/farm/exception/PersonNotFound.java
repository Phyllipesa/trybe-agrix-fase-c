package com.betrybe.agrix.farm.exception;

/**
 * Exception for when a person is not found.
 */
public class PersonNotFound extends RuntimeException {

  public PersonNotFound() {
    super("Pessoa não encontrada!");
  }

}
