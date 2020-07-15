package com.rest.api.laptop_inventory.controller;



import com.rest.api.laptop_inventory.dto.AuthenticationResponse;
import com.rest.api.laptop_inventory.dto.LoginRequestDto;
import com.rest.api.laptop_inventory.dto.RegisterRequestDto;
import com.rest.api.laptop_inventory.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody RegisterRequestDto registerRequest) {

        authService.signUp(registerRequest);
        return new ResponseEntity<String>("User signed up successfully!", HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequestDto loginRequestDto) {
        return authService.login(loginRequestDto);
    }
}
