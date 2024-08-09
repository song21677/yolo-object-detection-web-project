package com.e2on.assignment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
        .antMatchers("/home", "/login-page", "/sign-up-page", "/sign-up", "/login-check").permitAll()
        .anyRequest().authenticated();

        http
        .formLogin()
            .loginPage("/login-page")
            .loginProcessingUrl("/login")
            .defaultSuccessUrl("/home")
            .usernameParameter("loginId") // default는 username
//            .passwordParameter("") // default password
         .and()
            .logout()
         .and()
            .csrf().disable();

        return http.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
