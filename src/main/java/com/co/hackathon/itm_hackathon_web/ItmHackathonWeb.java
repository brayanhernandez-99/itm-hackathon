package com.co.hackathon.itm_hackathon_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ItmHackathonWeb extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ItmHackathonWeb.class, args);
	}

	// Configuración global de CORS para permitir peticiones desde el frontend
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**") // Aplica a todas las rutas
						.allowedOrigins("http://localhost:4200") // Cambia según el frontend (ejemplo: Angular en puerto 4200)
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
						.allowedHeaders("*");
			}
		};
	}
}
