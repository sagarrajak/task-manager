package com.taskmanager.taskmanager.config;

import com.taskmanager.taskmanager.services.impl.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !StringUtils.startsWithIgnoreCase(authorizationHeader, "Bearer ")) {
            throw new BadCredentialsException("Please provide token");
        }

        var authorization = Optional.ofNullable(request.getHeader("Authorization"));
        String token = authorization.get().substring(7);

        if (!jwtService.isValidSignature(token) || jwtService.isTokenExpired(token)) {
            throw new BadCredentialsException("Invalid token");
        }

        String username = jwtService.extractUsername(token);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails,null,  userDetails.getAuthorities()
        );
        SecurityContext emptyContext = SecurityContextHolder.createEmptyContext();
        emptyContext.setAuthentication(authentication);
        SecurityContextHolder.setContext(emptyContext);
        filterChain.doFilter(request, response);
    }
}
