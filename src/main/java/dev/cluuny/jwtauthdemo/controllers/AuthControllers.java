package dev.cluuny.jwtauthdemo.controllers;

import dev.cluuny.jwtauthdemo.model.exceptions.BadCredentialsException;
import dev.cluuny.jwtauthdemo.model.exceptions.UserNotFoundException;
import dev.cluuny.jwtauthdemo.model.http.AuthResponse;
import dev.cluuny.jwtauthdemo.model.http.LoginRequest;
import dev.cluuny.jwtauthdemo.model.http.RegisterRequest;
import dev.cluuny.jwtauthdemo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthControllers {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest loginRequest
    ) throws UserNotFoundException, BadCredentialsException {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegisterRequest registerRequest
    ) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }
}
