package com.a303.memberms.domain.member.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class MemberConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        //default rounds: 10
        return new BCryptPasswordEncoder(BCryptVersion.$2A, 10);
    }
}
