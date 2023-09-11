package com.codepup.twitter.controller;

import com.codepup.twitter.model.AuthenticationRequest;
import com.codepup.twitter.model.AuthenticationResponse;
import com.codepup.twitter.service.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class AuthenticationController {
    private static final Logger authLogger = LoggerFactory.getLogger("authLogger");

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
            authLogger.info("Authentication successful for user: {}", authenticationRequest.getUsername());
        } catch (BadCredentialsException e) {
            authLogger.warn("Authentication failed for user: {}", authenticationRequest.getUsername());
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = new User(authenticationRequest.getUsername(),
                authenticationRequest.getPassword(), new ArrayList<>());  // In a real-world scenario, you'll fetch the user details from your user service

        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
