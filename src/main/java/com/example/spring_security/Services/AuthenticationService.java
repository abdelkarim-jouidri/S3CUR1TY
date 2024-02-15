package com.example.spring_security.Services;

import com.example.spring_security.Models.Role;
import com.example.spring_security.Models.User;
import com.example.spring_security.Repositories.RoleRepository;
import com.example.spring_security.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(String username, String password){
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("user").get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        User builtUser = User.builder().
                userName(username).
                password(encodedPassword).
                authorities(authorities).
                build();
        return userRepository.save(builtUser);
    }

}
