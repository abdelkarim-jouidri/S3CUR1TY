package com.example.spring_security.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SuccessfulLoginResponseDTO {
    private UserDTO user;
    private String token;
}
