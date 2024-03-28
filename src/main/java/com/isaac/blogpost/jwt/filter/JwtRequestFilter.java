package com.isaac.blogpost.jwt.filter;

import com.isaac.blogpost.entity.User;
import com.isaac.blogpost.exception.HttpException;
import com.isaac.blogpost.jwt.JwtService;
import com.isaac.blogpost.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
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

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String authorizationHeader = request.getHeader("Authorization");
            if (authorizationHeader == null || authorizationHeader.isBlank() || !authorizationHeader.startsWith("Bearer ")) {
                response.sendError(401, "Authorization header is missing");
                return;
            }
            String token = authorizationHeader.replace("Bearer ", "").trim();

            String email = jwtService.extractSubject(token);

            User authenticatedUser = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Authentication auth = new UsernamePasswordAuthenticationToken(authenticatedUser,
                    null, authenticatedUser.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(auth);

            filterChain.doFilter(request, response);
        } catch (HttpException e) {
            response.sendError(e.getStatusCode(), e.getMessage());
            throw new HttpException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
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
