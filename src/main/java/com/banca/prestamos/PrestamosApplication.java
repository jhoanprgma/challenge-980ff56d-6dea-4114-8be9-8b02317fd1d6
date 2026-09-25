package com.banca.prestamos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import java.time.Duration;

/**
 * Clase principal de la aplicación Spring Boot para el servicio de préstamos personales.
 * Configura los componentes centrales y activa las características necesarias para:
 * - Arquitectura hexagonal con aislamiento de dominio
 * - Integración con sistemas externos con resiliencia
 * - Ejecución asíncrona para manejar carga alta
 */
@SpringBootApplication
@EnableConfigurationProperties
@EnableAsync
@ComponentScan(basePackages = {"com.banca.prestamos"})
public class PrestamosApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrestamosApplication.class, args);
    }

    /**
     * Configura un RestTemplate con timeout para llamadas a servicios externos.
     * @return RestTemplate configurado con timeout de conexión y lectura.
     */
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setConnectTimeout(Duration.ofMillis(1000));
        restTemplate.setReadTimeout(Duration.ofMillis(2000));
        return restTemplate;
    }

    /**
     * Configuración del CircuitBreaker para manejar fallos en el servicio de riesgo.
     * @return CircuitBreakerConfig con umbrales de fallo y tiempos de espera.
     */
    @Bean
    public CircuitBreakerConfig circuitBreakerConfig() {
        return CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(10))
                .permittedNumberOfCallsInHalfOpenState(3)
                .slidingWindowSize(10)
                .build();
    }

    /**
     * Configuración del TimeLimiter para manejar timeouts en llamadas a servicios externos.
     * @return TimeLimiterConfig con timeout de 2 segundos.
     */
    @Bean
    public TimeLimiterConfig timeLimiterConfig() {
        return TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofSeconds(2))
                .build();
    }
}