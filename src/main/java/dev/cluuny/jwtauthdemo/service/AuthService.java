package dev.cluuny.jwtauthdemo.service;

import dev.cluuny.jwtauthdemo.model.Role;
import dev.cluuny.jwtauthdemo.model.exceptions.BadCredentialsException;
import dev.cluuny.jwtauthdemo.model.exceptions.UserNotFoundException;
import dev.cluuny.jwtauthdemo.model.http.AuthResponse;
import dev.cluuny.jwtauthdemo.model.http.LoginRequest;
import dev.cluuny.jwtauthdemo.model.http.RegisterRequest;
import dev.cluuny.jwtauthdemo.persistence.entity.UserEntity;
import dev.cluuny.jwtauthdemo.persistence.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserJpaRepository userJpaRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest loginRequest) throws UserNotFoundException, BadCredentialsException {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        if (auth.isAuthenticated()) {
            UserEntity userEntity = userJpaRepository.findUserEntityByUsername(loginRequest.getUsername())
                    .orElseThrow(UserNotFoundException::new);
            return AuthResponse.builder()
                    .token(jwtService.getToken(userEntity))
                    .build();
        }
        throw new BadCredentialsException();
    }

    public AuthResponse register(RegisterRequest registerRequest) {
        UserEntity user = UserEntity.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .country(registerRequest.getCountry())
                .role(Role.GUEST)
                .build();
        userJpaRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
