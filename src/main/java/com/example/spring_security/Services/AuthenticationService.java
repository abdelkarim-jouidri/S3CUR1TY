package com.example.spring_security.Services;

import com.example.spring_security.Models.Role;
import com.example.spring_security.Models.User;
import com.example.spring_security.Repositories.RoleRepository;
import com.example.spring_security.Repositories.UserRepository;
import com.example.spring_security.dtos.FailedLoginResponseDTO;
import com.example.spring_security.dtos.SuccessfulLoginResponseDTO;
import com.example.spring_security.dtos.UserDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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
    private final ModelMapper modelMapper;

    public Object login(String username, String password){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(authentication);
            User user = userRepository.findByUserName(username).get();
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            return SuccessfulLoginResponseDTO.
                    builder().
                    user(userDTO).
                    token(token).
                    build();

        }catch (AuthenticationException e){
            return FailedLoginResponseDTO.builder().message("Incorrect credentials !!").build();
        }
    }
    public UserDTO registerUser(String username, String password){
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("user").get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        User builtUser = User.builder().
                userName(username).
                password(encodedPassword).
                authorities(authorities).
                build();
        UserDTO userDTO = modelMapper.map(userRepository.save(builtUser), UserDTO.class);
        return userDTO;
    }

}
