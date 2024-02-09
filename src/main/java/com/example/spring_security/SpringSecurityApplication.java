package com.example.spring_security;

import com.example.spring_security.Models.Role;
import com.example.spring_security.Models.User;
import com.example.spring_security.Repositories.RoleRepository;
import com.example.spring_security.Repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder){
		return args -> {
			if(roleRepository.findByAuthority("admin").isPresent()) return;
			Role adminRole = roleRepository.save(Role.builder().authority("admin").build());
			Set<Role> roles= new HashSet<>();
			roles.add(adminRole);
			User admin = User.builder().userName("admin").password("pa$$word").authorities(roles).build();
			userRepository.save(admin);

		};
	}

}
