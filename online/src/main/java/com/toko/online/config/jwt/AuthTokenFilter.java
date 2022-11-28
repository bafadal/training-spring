package com.toko.online.config.jwt;

import com.toko.online.service.UsersService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthTokenFilter extends OncePerRequestFilter {

  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private UsersService usersService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
      try {
            String token = parseJwt(request);

            if (token != null && jwtUtils.validateJwtToken(token)) {
                String username = jwtUtils.getUsernameFromJwt(token);
                if (SecurityContextHolder.getContext().getAuthentication() != null){
                  String ROLE_PREFIX = "ROLE_";
                  UserDetails userDetails = usersService.loadUserByUsername(username);
                  UsernamePasswordAuthenticationToken authenticationToken = new
                      UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                  authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                  SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                  System.out.println(SecurityContextHolder.getContext().getAuthentication());
                }
            }
      }catch (Exception e) {
        e.printStackTrace();
      }

      filterChain.doFilter(request, response);
  }

  private String parseJwt(HttpServletRequest request) {
    String headerAuth = request.getHeader("Authorization");

    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
      return headerAuth.substring(7, headerAuth.length());
    }

    return null;
  }
}
