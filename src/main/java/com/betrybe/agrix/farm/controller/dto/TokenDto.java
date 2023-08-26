package com.betrybe.agrix.farm.controller.dto;

/**
 * TokenDto.
 *
 * @param token recebe um token.
 */
public record TokenDto(String token) {

  /**
   * TokenDto - token dto.
   *
   * @param token token.
   * @return token no formato json.
   */
  public static TokenDto tokenToResponse(String token) {
    return new TokenDto(
        token
    );
  }
}
