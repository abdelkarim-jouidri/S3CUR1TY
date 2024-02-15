package com.example.spring_security.dtos;

import com.example.spring_security.Models.Role;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class UserDTO {
    private String userName;
    private Set<Role> authorities;
}
