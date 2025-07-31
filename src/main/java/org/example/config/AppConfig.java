package org.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 애플리케이션 관련 설정을 담당하는 클래스
 * 여러 설정 파일들을 하나로 모아서 관리합니다.
 */
@Configuration
@ComponentScan(basePackages = {"org.example"})
@EnableJpaRepositories(basePackages = {"org.example.repository"})
@EnableTransactionManagement
@Import({WebConfig.class})
public class AppConfig {
    // 여기에 필요한 빈 설정을 추가할 수 있습니다.
}