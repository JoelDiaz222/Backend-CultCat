package com.cultcat.backend.config;

import com.cultcat.backend.model.Usuari;
import com.cultcat.backend.repository.UsuariRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UsuariRepository usuariRepository;

    public JwtAuthenticationFilter(UsuariRepository usuariRepository) {
        this.usuariRepository = usuariRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            String googleId = JwtTokenUtil.getGoogleIdFromToken(token);

            if (googleId != null && JwtTokenUtil.validateToken(token, googleId)) {
                Optional<Usuari> usuariOptional = usuariRepository.findByGoogleId(googleId);

                if (usuariOptional.isPresent()) {
                    Usuari usuari = usuariOptional.get();
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            usuari, null, usuari.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
