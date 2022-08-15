package com.jaymansmann.books.controller.auth;

import java.net.URI;

import javax.validation.Valid;

/**
 * @author Jay
 *
 */
import com.jaymansmann.books.db.entity.auth.User;
import com.jaymansmann.books.dto.*;
import com.jaymansmann.books.dto.auth.JwtAuthenticationResponse;
import com.jaymansmann.books.dto.auth.LoginRequest;
import com.jaymansmann.books.dto.auth.SignUpRequest;
import com.jaymansmann.books.dto.auth.UserDTO;
import com.jaymansmann.books.security.JwtTokenProvider;
import com.jaymansmann.books.security.UserPrincipal;
import com.jaymansmann.books.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/users")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtTokenProvider tokenProvider;


    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }
    @PostMapping
    public ResponseEntity<ApiResponse> signup(@Valid @RequestBody SignUpRequest signUpRequest) {
        UserDTO result = this.authService.signUp(signUpRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id, Authentication authentication) {
        this.authService.deleteById(id, (UserPrincipal) authentication.getPrincipal());
    }
}
