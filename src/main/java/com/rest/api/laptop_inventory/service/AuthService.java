package com.rest.api.laptop_inventory.service;


import com.rest.api.laptop_inventory.dto.AuthenticationResponse;
import com.rest.api.laptop_inventory.dto.LoginRequestDto;
import com.rest.api.laptop_inventory.dto.RegisterRequestDto;
import com.rest.api.laptop_inventory.model.User;
import com.rest.api.laptop_inventory.repository.UserRepository;
import com.rest.api.laptop_inventory.security.JWTProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final AuthenticationManager authenticationManager;

    private JWTProvider jwtProvider;

    @Transactional
    public void signUp(RegisterRequestDto registerRequest) {
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setUserName(registerRequest.getUserName());
        user.setCreatedDate(Instant.now());

        userRepository.save(user);

        log.info("User signed up successfully!");

    }

    public AuthenticationResponse login(LoginRequestDto loginRequestDto) {
       Authentication authenticate = authenticationManager
               .authenticate(new UsernamePasswordAuthenticationToken(
                       loginRequestDto.getUsername(),
                       loginRequestDto.getPassword()));

       SecurityContextHolder.getContext().setAuthentication(authenticate);

        String token = jwtProvider.generateToken(authenticate);

        return new AuthenticationResponse(loginRequestDto.getUsername(), token);
    }
}
