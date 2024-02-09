package com.example.spring_security.Models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "role_id")
    private Integer roleId;

    private String authority;


    @Override
    public String getAuthority() {
        return this.authority;
    }
}
