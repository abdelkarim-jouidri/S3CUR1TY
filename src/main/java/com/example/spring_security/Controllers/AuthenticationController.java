package com.example.spring_security.Controllers;

import com.example.spring_security.Models.User;
import com.example.spring_security.Services.AuthenticationService;
import com.example.spring_security.dtos.LoginRequestDTO;
import com.example.spring_security.dtos.LoginResponseDTO;
import com.example.spring_security.dtos.RegistrationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public User regiserUser(@RequestBody RegistrationDTO registrationDTO){
        return authenticationService.registerUser(registrationDTO.getUsername(), registrationDTO.getPassword());
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO body){
        System.out.println(body);
        return authenticationService.login(body.getUsername(), body.getPassword());
    }
}
