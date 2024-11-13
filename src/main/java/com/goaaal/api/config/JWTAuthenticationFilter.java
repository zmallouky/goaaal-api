package com.goaaal.api.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Génère la clé de signature

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        // Vérifie que le token commence par "Bearer "
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7); // Supprime "Bearer " pour obtenir le token JWT
        try {
            // Parse et valide le token
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.getSubject();
            String userId = claims.get("userId", String.class);

            // Crée l'authentification et la met dans le contexte de sécurité
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    email, null, null);
            SecurityContextHolder.getContext().setAuthentication(auth);

            // Ajoute des attributs pour une utilisation ultérieure
            request.setAttribute("userData", new UserData(email, userId));

        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication required");
            return;
        }

        filterChain.doFilter(request, response);
    }

    // Classe interne pour stocker les informations d'utilisateur
    public static class UserData {
        private final String email;
        private final String userId;

        public UserData(String email, String userId) {
            this.email = email;
            this.userId = userId;
        }

        public String getEmail() {
            return email;
        }

        public String getUserId() {
            return userId;
        }
    }
}
