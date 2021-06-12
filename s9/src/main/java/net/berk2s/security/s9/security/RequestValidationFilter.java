package net.berk2s.security.s9.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class RequestValidationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String requestId = httpServletRequest.getHeader("Request-Id");

        if(requestId == null || requestId.isBlank()) {
            log.warn("The request has not valid a Request-Id");
            httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }
}
