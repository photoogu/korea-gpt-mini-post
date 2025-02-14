package com.korit.mini_post.security.filter;

import com.korit.mini_post.repository.UserRepository;
import com.korit.mini_post.security.jwt.JwtProvider;
import com.korit.mini_post.security.principal.PrincipalUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter implements Filter {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String authorization = request.getHeader("Authorization");

        if(jwtProvider.validateToken(authorization)) {
            setJwtAuthentication(authorization);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void setJwtAuthentication(String bearerToken) {
        Claims claims = jwtProvider.parseToken(bearerToken);
        if(claims == null) {
            throw new JwtException("Invalid JWT token");
        }
        int userId = Integer.parseInt(claims.get("userId").toString());
        userRepository.findById(userId).ifPresent(user -> {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            PrincipalUser principalUser = new PrincipalUser(user);
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalUser, principalUser.getPassword(), principalUser.getAuthorities());
            securityContext.setAuthentication(authentication);
        });
    }
}
