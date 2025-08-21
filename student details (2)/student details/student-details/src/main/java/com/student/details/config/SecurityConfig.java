/*package com.student.details.config;

import com.student.details.services.StudentService;
import org.apache.catalina.filters.HttpHeaderSecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Primary;


@Configuration
@EnableWebSecurity
public class SecurityConfig {



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            return http
            .authorizeHttpRequests(auth -> auth
    .anyRequest().permitAll()
    )
            .formLogin(form -> form
                            .loginPage("/login")
                            .loginProcessingUrl("/login")
                            .defaultSuccessUrl("/", true)
                            .failureUrl("/login?error=true")
                            .permitAll()
            )
            .logout(auth -> auth
                    .logoutSuccessUrl("/")
                    .permitAll()
            )
            .build();

    }



    @Bean
    @Primary
    public PasswordEncoder passwordEncoder1() {
        return new BCryptPasswordEncoder();
    }


}*/

