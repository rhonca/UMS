package com.honca.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("", "/").permitAll()
                        .requestMatchers("/subjects/**").hasAnyRole("ADMIN")
                        .requestMatchers("/teachers/**").hasAnyRole("ADMIN", "TEACHER")
                        .requestMatchers("/students/**").hasAnyRole("ADMIN", "TEACHER", "STUDENT")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());
        return http.build();
    }

    @Bean
    public PasswordEncoder getBcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager get() {
        UserDetails admin = User.withUsername("admin")
                .password(getBcryptPasswordEncoder().encode("admin123"))
                .roles("ADMIN")
                .build();
        UserDetails teacher = User.withUsername("teacher")
                .password(getBcryptPasswordEncoder().encode("teacher123"))
                .roles("TEACHER")
                .build();
        UserDetails student = User.withUsername("student")
                .password(getBcryptPasswordEncoder().encode("student123"))
                .roles("STUDENT")
                .build();
        return new InMemoryUserDetailsManager(admin,teacher,student);

    }


}
