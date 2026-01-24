package br.edu.uepb.eventualize.configuracao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Configuracao {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable()) 
	        .authorizeHttpRequests(auth -> auth
	        	    .requestMatchers("/api/auth/cadastro").permitAll()
	        	    .requestMatchers("/api/auth/login").permitAll()
	        	    .anyRequest().authenticated()
	        );

	    return http.build();
	}
	
    @Bean
    PasswordEncoder codificadorSenha() {
        return new BCryptPasswordEncoder();
    }
}
