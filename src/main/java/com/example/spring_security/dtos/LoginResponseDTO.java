package com.example.spring_security.dtos;

import com.example.spring_security.Models.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponseDTO {
    private User user;
    private String token;
}
