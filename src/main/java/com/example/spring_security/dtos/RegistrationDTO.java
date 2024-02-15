package com.example.spring_security.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RegistrationDTO {
    private String username;
    private String password;
}
