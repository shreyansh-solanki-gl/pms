package com.pms.filter;

import com.pms.repository.UserRepository;
import com.pms.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JwtUtil jwtUtil;


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (!StringUtils.hasText(header) || (StringUtils.hasText(header) && !header.startsWith("Bearer "))) {
      filterChain.doFilter(request, response);
      return;
    }

    // Authorization -> ([Bearer], [asdf.kjhaskfjh.q94wlkjfs.dklf0253.asd234])
    final String token = header.split(" ")[1].trim();

    // Get user identity and set it on the spring security context
    UserDetails userDetails = userRepository
            .findByUsername(jwtUtil.getUsernameFromToken(token))
            .orElse(null);

    // Get jwt token and validate
//    assert userDetails != null;
    if (!jwtUtil.validateToken(token, userDetails)) {
      filterChain.doFilter(request, response);
      return;
    }

    UsernamePasswordAuthenticationToken
            authentication = new UsernamePasswordAuthenticationToken(
            userDetails, null,
            userDetails == null ?
                    List.of() : userDetails.getAuthorities()
    );

    authentication.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request)
    );

    // authentication -> user is now is valid!
    SecurityContextHolder.getContext().setAuthentication(authentication);
    filterChain.doFilter(request, response);
  }
}
