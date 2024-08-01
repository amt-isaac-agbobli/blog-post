package com.isaac.blogpost.jwt.filter;

import com.isaac.blogpost.entity.User;
import com.isaac.blogpost.jwt.JwtService;
import com.isaac.blogpost.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.stream.Stream;

@Component
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String authorizationHeader = request.getHeader("Authorization");
            if (authorizationHeader == null || authorizationHeader.isBlank() || !authorizationHeader.startsWith("Bearer ")) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            String token = authorizationHeader.replace("Bearer ", "").trim();

            String email = jwtService.extractSubject(token);

            User authenticatedUser = userRepository.findByEmail(email)
                    .orElseThrow();

            Authentication auth = new UsernamePasswordAuthenticationToken(authenticatedUser,
                    null, authenticatedUser.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(auth);

            filterChain.doFilter(request, response);
        } catch (IOException e) {
            logger.error("An error occurred: ", e);
        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String url = request.getRequestURI();
        return Stream.of(excludedUrls).anyMatch(url::startsWith);
    }

    private static final String[] excludedUrls = {
            "/api/v1/auth",
            "/v3/api-docs",
            "/swagger-ui",
    };
}