package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * 보안 필터 체인 설정
     * 모든 페이지에 대한 접근을 허용하도록 설정
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()  // 모든 요청에 대해 접근 허용
                )
                .csrf().disable()  // CSRF 보호 비활성화 (Ajax 사용 시 편리)
                .formLogin().disable()  // 기본 로그인 폼 비활성화
                .httpBasic().disable();  // HTTP Basic 인증 비활성화

        return http.build();
    }

    /**
     * 비밀번호 암호화를 위한 BCrypt 인코더
     * 게시글 비밀번호 암호화에 사용
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}