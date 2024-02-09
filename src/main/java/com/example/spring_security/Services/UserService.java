package com.example.spring_security.Services;

import com.example.spring_security.Models.Role;
import com.example.spring_security.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("in the user details service");
        if (!username.equals("ethan")) throw new UsernameNotFoundException("User not found");
        Set<Role> roles = new HashSet<>();
        roles.add(Role.builder().authority("user").build());
        return User.builder().userName("ethan").password(passwordEncoder.encode("password")).build();
    }
}
