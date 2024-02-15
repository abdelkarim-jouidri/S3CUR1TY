package com.example.spring_security.Services;

import com.example.spring_security.Models.Role;
import com.example.spring_security.Models.User;
import com.example.spring_security.Repositories.RoleRepository;
import com.example.spring_security.Repositories.UserRepository;
import com.example.spring_security.dtos.LoginResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public LoginResponseDTO login(String username, String password){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(authentication);
            return LoginResponseDTO.
                    builder().
                    user(userRepository.findByUserName(username).get()).
                    token(token).
                    build();

        }catch (AuthenticationException e){
            e.printStackTrace();
            return LoginResponseDTO.builder().token("").user(null).build();
        }
    }
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
