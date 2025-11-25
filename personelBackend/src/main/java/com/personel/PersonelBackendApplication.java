package com.personel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main Application Class for Personnel Management System
 * 
 * @author Senior FullStack Java Developer
 * @version 1.0.0
 * @since 2025-11-06
 */
@SpringBootApplication
@EnableJpaAuditing
@EnableCaching
@EnableAsync
@EnableScheduling
public class PersonelBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonelBackendApplication.class, args);
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("║   PERSONEL MANAGEMENT SYSTEM STARTED SUCCESSFULLY            ║");
        System.out.println("║   Swagger UI: http://localhost:8080/api/v1/swagger-ui.html  ║");
        System.out.println("║   API Docs: http://localhost:8080/api/v1/api-docs           ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
    }
}
