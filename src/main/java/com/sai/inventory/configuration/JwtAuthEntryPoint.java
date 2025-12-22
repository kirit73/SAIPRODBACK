package com.sai.inventory.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sai.inventory.util.ResponseBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if (response.getStatus() == 200) {
            response.setStatus(401);
            ResponseEntity<Object> res = ResponseBuilder.getErrorResponse(authException.getMessage());
            OutputStream outputStream = response.getOutputStream();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(outputStream, res.getBody());
            outputStream.flush();
        } else {
            response.setStatus(response.getStatus());
            if (response.getStatus() != 400) {
                ResponseEntity<Object> res = ResponseBuilder.getErrorResponse();
                OutputStream outputStream = response.getOutputStream();
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.writeValue(outputStream, res.getBody());
                outputStream.flush();
            }
        }
    }
}
