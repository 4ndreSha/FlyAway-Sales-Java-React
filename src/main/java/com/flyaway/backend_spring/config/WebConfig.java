package com.flyaway.backend_spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Все эндпоинты
                .allowedOrigins("http://localhost:3000") // URL вашего React-приложения
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Разрешенные методы
                .allowedHeaders("*") // Все заголовки
                .allowCredentials(true) // Разрешить куки/авторизацию
                .maxAge(3600); // Время кеширования CORS-префлайт запросов
    }
}