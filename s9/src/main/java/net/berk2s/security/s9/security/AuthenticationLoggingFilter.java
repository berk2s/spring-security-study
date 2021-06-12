package net.berk2s.security.s9.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AuthenticationLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String requestId = httpServletRequest.getHeader("Request-Id");

        log.info("Successfully authenticated request [Request-Id: {}]", requestId);

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
