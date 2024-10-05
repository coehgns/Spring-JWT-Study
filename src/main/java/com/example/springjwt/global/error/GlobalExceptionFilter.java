package com.example.springjwt.global.error;

import com.example.springjwt.global.error.exception.BusinessException;
import com.example.springjwt.global.error.exception.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class GlobalExceptionFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (BusinessException e) {
            ErrorCode errorCode = e.getErrorCode();
            writeErrorResponse(response, errorCode.getStatusCode(), ErrorResponse.of(errorCode, e.getMessage()));
        }
    }

    private void writeErrorResponse(
            HttpServletResponse httpServletResponse,
            int statusCode,
            ErrorResponse errorResponse
    ) throws IOException {
        httpServletResponse.setStatus(statusCode);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        objectMapper.writeValue(httpServletResponse.getWriter(), errorResponse);
    }
}
