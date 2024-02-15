package com.example.spring_security.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class LoginRequestDTO {
    private String username;
    private String password;
}
