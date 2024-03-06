package dev.cluuny.jwtauthdemo.controllers.advice;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.Collections;
import java.util.Map;

@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, String>> badCredentialsExceptionHandler(BadCredentialsException exception) {
        return ResponseEntity.status(HttpStatusCode.valueOf(401))
                .body(Collections.singletonMap("error", exception.getMessage()));
    }
}

