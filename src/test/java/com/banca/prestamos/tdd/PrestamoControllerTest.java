package com.banca.prestamos.tdd;


import com.banca.prestamos.domain.model.EstadoPrestamo;
import com.banca.prestamos.application.port.in.PrestamoUseCase;
import com.banca.prestamos.domain.model.Prestamo;
import com.banca.prestamos.domain.model.Solicitante;
import com.banca.prestamos.infrastructure.adapter.in.web.PrestamoController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas TDD del PrestamoController")
class PrestamoControllerTest {

    @Mock
    private PrestamoUseCase prestamoUseCase;

    @InjectMocks
    private PrestamoController prestamoController;

    private UUID prestamoId;
    private Solicitante solicitanteValido;
    private Prestamo prestamoPendiente;
    private Prestamo prestamoAprobado;

    @BeforeEach
    void setUp() {
        prestamoId = UUID.randomUUID();
        solicitanteValido = Solicitante.crearSolicitante(
            "Juan",
            "Pérez",
            "DNI",
            "12345678",
            "juan.perez@email.com",
            "+5491134567890"
        );
    }

    @Test
    @DisplayName("Crear solicitud de préstamo retorna código 201 cuando es exitoso")
    void testCrearSolicitudPrestamo_Retorna201_CuandoExitoso() {
        // Given
        BigDecimal monto = new BigDecimal("50000");
        int plazoMeses = 12;
        LocalDate fechaSolicitud = LocalDate.now();
        
       Prestamo prestamoCreado = Prestamo.crearSolicitud(solicitanteValido, monto, plazoMeses, fechaSolicitud);
        when(prestamoUseCase.crearSolicitudPrestamo(any(Solicitante.class), eq(monto), eq(plazoMeses), eq(fechaSolicitud)))
            .thenReturn(prestamoCreado);

        // When
        ResponseEntity<Prestamo> response = prestamoController.crearSolicitud(
            solicitanteValido, monto, plazoMeses, fechaSolicitud
        );

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(monto, response.getBody().getMonto());
        assertEquals(plazoMeses, response.getBody().getPlazoMeses());
    }

    @Test
    @DisplayName("Aprobar préstamo retorna código 200 cuando es exitoso")
    void testAprobarPrestamo_Retorna200_CuandoExitoso() {
        // Given
        BigDecimal tasaInteres = new BigDecimal("0.25");
        LocalDate fechaAprobacion = LocalDate.now();
        
        prestamoAprobado = Prestamo.crearSolicitud(solicitanteValido, new BigDecimal("50000"), 12, LocalDate.now())
            .aprobar(fechaAprobacion, tasaInteres);
        
        when(prestamoUseCase.aprobarPrestamo(eq(prestamoId), eq(fechaAprobacion), eq(tasaInteres)))
            .thenReturn(prestamoAprobado);

        // When
        ResponseEntity<Prestamo> response = prestamoController.aprobarPrestamo(
            prestamoId.toString(), fechaAprobacion, tasaInteres
        );

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(Prestamo.EstadoPrestamo.APROBADO, response.getBody().getEstado());
    }

    @Test
    @DisplayName("Rechazar préstamo retorna código 200 cuando es exitoso")
    void testRechazarPrestamo_Retorna200_CuandoExitoso() {
        // Given
        String motivoRechazo = "Perfil de riesgo no autorizado";
        LocalDate fechaRechazo = LocalDate.now();
        
        prestamoPendiente = Prestamo.crearSolicitud(solicitanteValido, new BigDecimal("50000"), 12, LocalDate.now());
        Prestamo prestamoRechazado = prestamoPendiente.rechazar(motivoRechazo);
        
        when(prestamoUseCase.rechazarPrestamo(eq(prestamoId), eq(motivoRechazo)))
            .thenReturn(prestamoRechazado);

        // When
        ResponseEntity<Prestamo> response = prestamoController.rechazarPrestamo(
            prestamoId.toString(), motivoRechazo
        );

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(Prestamo.EstadoPrestamo.RECHAZADO, response.getBody().getEstado());
    }

    @Test
    @DisplayName("Obtener detalles del préstamo retorna código 200 cuando existe")
    void testObtenerDetallesPrestamo_Retorna200_CuandoExiste() {
        // Given
        prestamoPendiente = Prestamo.crearSolicitud(solicitanteValido, new BigDecimal("50000"), 12, LocalDate.now());
        when(prestamoUseCase.obtenerDetallesPrestamo(eq(prestamoId))).thenReturn(prestamoPendiente);

        // When
        ResponseEntity<Prestamo> response = prestamoController.obtenerPrestamo(prestamoId.toString());

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(prestamoId, response.getBody().getId());
    }

    @Test
    @DisplayName("Obtener detalles del préstamo retorna código 404 cuando no existe")
    void testObtenerDetallesPrestamo_Retorna404_CuandoNoExiste() {
        // Given
        UUID prestamoIdInexistente = UUID.randomUUID();
        when(prestamoUseCase.obtenerDetallesPrestamo(eq(prestamoIdInexistente)))
            .thenReturn(null);

        // When
        ResponseEntity<Prestamo> response = prestamoController.obtenerPrestamo(prestamoIdInexistente.toString());

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}