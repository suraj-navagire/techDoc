package com.example.jwtpoc.auth;

import com.example.jwtpoc.auth.dto.AuthResponse;
import com.example.jwtpoc.auth.dto.LoginRequest;
import com.example.jwtpoc.auth.dto.RegisterRequest;
import com.example.jwtpoc.security.JwtService;
import com.example.jwtpoc.user.AppUser;
import com.example.jwtpoc.user.AppUserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Handles public authentication APIs: register and login.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(
            AppUserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    /**
     * Registers a new user, stores a BCrypt password hash, and returns a JWT.
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        if (userRepository.existsByEmailIgnoreCase(request.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already registered");
        }

        AppUser savedUser = userRepository.save(
                request.name(),
                request.email().toLowerCase(),
                passwordEncoder.encode(request.password()));
        String accessToken = jwtService.createToken(savedUser);

        return AuthResponse.from(savedUser, accessToken, jwtService.expirationMinutes());
    }

    /**
     * Authenticates email/password and returns a new JWT when credentials are valid.
     */
    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        } catch (BadCredentialsException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        }

        AppUser user = userRepository.findByEmailIgnoreCase(request.email())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid email or password"));
        String accessToken = jwtService.createToken(user);

        return AuthResponse.from(user, accessToken, jwtService.expirationMinutes());
    }

}
