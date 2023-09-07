package com.betrybe.agrix.farm.security.filters;

import com.betrybe.agrix.farm.service.PersonService;
import com.betrybe.agrix.farm.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *  SecurityFilter - Filtro para validação de token.
 */
@Component
public class SecurityFilter extends OncePerRequestFilter {
  private  final TokenService tokenService;
  private  final PersonService personService;

  /**
   * Recebe um bean do tipo tokenService por injeção de dependência.
   * Recebe um bean do tipo personService por injeção de dependência.
   *
   * @param tokenService tokenService.
   * @param personService personService.
   */
  @Autowired
  public SecurityFilter(TokenService tokenService, PersonService personService) {
    this.tokenService = tokenService;
    this.personService = personService;
  }

  /**
   * doFilterInternal.
   *
   * @param request request.
   * @param response response.
   * @param filterChain filtro de permissão de rotas.
   * @throws ServletException ServletException.
   * @throws IOException IOException.
   * @throws AccessDeniedException caso Role não seja permitida na rota especificada.
   */
  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain
  ) throws ServletException, IOException, AccessDeniedException {

    String token = recoveryToken(request);

    if (token != null) {
      String subject = tokenService.validateToken(token);

      UserDetails userDetails = personService.loadUserByUsername(subject);

      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
          userDetails, null, userDetails.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filterChain.doFilter(request, response);
  }

  private String recoveryToken(HttpServletRequest request) {
    String authHeader = request.getHeader("Authorization");

    if (authHeader == null) {
      return null;
    }

    return authHeader.replace("Bearer ", "");
  }
}
