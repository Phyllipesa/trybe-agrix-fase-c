package com.betrybe.agrix.farm.controller.dto;

/**
 * Cria um Authentication dto.
 *
 * @param username username
 * @param password password
 */
public record AuthenticationDto(String username, String password) {
}
