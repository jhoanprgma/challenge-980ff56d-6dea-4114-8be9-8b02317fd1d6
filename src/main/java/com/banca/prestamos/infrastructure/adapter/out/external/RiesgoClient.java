package com.banca.prestamos.infrastructure.adapter.out.external;



import com.banca.prestamos.domain.model.Monto;
import com.banca.prestamos.domain.model.Solicitante;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Component
public class RiesgoClient {

    private static final Logger logger = LoggerFactory.getLogger(RiesgoClient.class);

    private final RestTemplate restTemplate;
    private final String servicioRiesgoUrl;

    public RiesgoClient(
            RestTemplate restTemplate,
            @Value("${servicio.riesgo.url:http://localhost:8081/api/v1/riesgo}") String servicioRiesgoUrl) {
        this.restTemplate = restTemplate;
        this.servicioRiesgoUrl = servicioRiesgoUrl;
    }

    @CircuitBreaker(name = "riesgoService", fallbackMethod = "evaluarRiesgoFallback")
    @Retry(name = "riesgoServiceRetry")
    public EvaluacionRiesgoResponse evaluarRiesgo(UUID solicitanteId, BigDecimal monto) {
        logger.info("Evaluando riesgo para solicitante: {} con monto: {}", solicitanteId, monto);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = Map.of(
                "solicitanteId", solicitanteId.toString(),
                "monto", monto
        );

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            EvaluacionRiesgoResponse response = restTemplate.postForObject(
                    servicioRiesgoUrl + "/evaluar",
                    request,
                    EvaluacionRiesgoResponse.class
            );

            if (response == null) {
                logger.warn("Respuesta nula del servicio de riesgo para solicitante: {}", solicitanteId);
                return crearRespuestaDefault();
            }

            logger.info("Evaluación de riesgo completada. Nivel: {}, Score: {}",
                    response.nivelRiesgo(), response.score());
            return response;

        } catch (Exception e) {
            logger.error("Error al evaluar riesgo para solicitante: {}. Error: {}",
                    solicitanteId, e.getMessage());
            throw new RuntimeException("Error en servicio de evaluación de riesgo", e);
        }
    }

    private EvaluacionRiesgoResponse evaluarRiesgoFallback(UUID solicitanteId, BigDecimal monto, Throwable t) {
        logger.warn("Fallback activado para evaluacion de riesgo. Solicitante: {}, Monto: {}, Error: {}",
                solicitanteId, monto, t.getMessage());
        return crearRespuestaDefault();
    }

    private EvaluacionRiesgoResponse crearRespuestaDefault() {
        return new EvaluacionRiesgoResponse("MEDIO", 50, true, "Evaluación por defecto - servicio no disponible");
    }

    public boolean esAprobable(EvaluacionRiesgoResponse evaluacion) {
        return evaluacion.aprobable() &&
                (evaluacion.nivelRiesgo().equals("BAJO") ||
                 (evaluacion.nivelRiesgo().equals("MEDIO") && evaluacion.score() >= 60));
    }

    public record EvaluacionRiesgoResponse(
            String nivelRiesgo,
            Integer score,
            boolean aprobable,
            String mensaje
    ) {}
}