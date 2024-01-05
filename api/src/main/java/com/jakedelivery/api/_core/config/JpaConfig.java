package com.jakedelivery.api._core.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.jakedelivery.db")
@EnableJpaRepositories(basePackages = "com.jakedelivery.db")
@Configuration
public class JpaConfig {
}
