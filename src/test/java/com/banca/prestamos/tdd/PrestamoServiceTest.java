package com.banca.prestamos.tdd;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import com.banca.prestamos.domain.model.Prestamo;
import com.banca.prestamos.domain.model.Solicitante;
import com.banca.prestamos.application.service.PrestamoService;
import com.banca.prestamos.infrastructure.adapter.out.persistence.PrestamoRepository;
import com.banca.prestamos.infrastructure.adapter.out.external.RiesgoClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas TDD del Servicio de Préstamos")
@org.junit.jupiter.api.Disabled("Superficie de práctica - implementar las pruebas según el ciclo TDD")
class PrestamoServiceTest {

    @Mock
    private PrestamoRepository prestamoRepository;

    @Mock
    private RiesgoClient riesgoClient;

    @InjectMocks
    private PrestamoService prestamoService;

    private Solicitante solicitanteValido;
    private Prestamo prestamoExistente;
    private UUID prestamoIdValido;

    @BeforeEach
    void setUp() {
        prestamoIdValido = UUID.randomUUID();
        solicitanteValido = Solicitante.crearSolicitante(
            "TEST_NOMBRE",
            "TEST_APELLIDO",
            "DNI",
            "12345678",
            "test@test.com",
            "123456789"
        );

        prestamoExistente = Prestamo.crearSolicitud(
            solicitanteValido,
            new BigDecimal("50000.00"),
            12,
            LocalDate.now()
        );
    }

    @Test
    @DisplayName("Crear solicitud de préstamo - caso exitoso")
    void testCrearSolicitudPrestamo_CasoExitoso() {
        when(prestamoRepository.save(any(Prestamo.class))).thenAnswer(invocation -> {
            Prestamo p = invocation.getArgument(0);
            return p;
        });

        Prestamo resultado = prestamoService.crearSolicitudPrestamo(
            solicitanteValido,
            new BigDecimal("50000.00"),
            12,
            LocalDate.now()
        );

        assertNotNull(resultado, "El préstamo no debe ser null");
        assertEquals(Prestamo.EstadoPrestamo.PENDIENTE, resultado.getEstado(),
            "El estado debe ser SOLICITADO");
        assertEquals(new BigDecimal("50000.00"), resultado.getMonto(),
            "El monto debe coincidir");
        assertEquals(12, resultado.getPlazoMeses(),
            "El plazo debe coincidir");

        verify(prestamoRepository, times(1)).save(any(Prestamo.class));
    }

    @Test
    @DisplayName("Aprobar préstamo - caso exitoso")
    void testAprobarPrestamo_CasoExitoso() {
        when(prestamoRepository.findById(prestamoIdValido)).thenReturn(Optional.of(prestamoExistente));
        when(prestamoRepository.save(any(Prestamo.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Prestamo resultado = prestamoService.aprobarPrestamo(
            prestamoIdValido,
            LocalDate.now(),
            new BigDecimal("0.12")
        );

        assertNotNull(resultado, "El préstamo aprobado no debe ser null");
        assertEquals(Prestamo.EstadoPrestamo.APROBADO, resultado.getEstado(),
            "El estado debe ser APROBADO");
        assertNotNull(resultado.getFechaAprobacion(),
            "La fecha de aprobación debe estar establecida");

        verify(prestamoRepository, times(1)).findById(prestamoIdValido);
        verify(prestamoRepository, times(1)).save(any(Prestamo.class));
    }

    @Test
    @DisplayName("Rechazar préstamo - caso exitoso")
    void testRechazarPrestamo_CasoExitoso() {
        String motivoRechazo = "Riesgo crediticio elevado";

        when(prestamoRepository.findById(prestamoIdValido)).thenReturn(Optional.of(prestamoExistente));
        when(prestamoRepository.save(any(Prestamo.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Prestamo resultado = prestamoService.rechazarPrestamo(
            prestamoIdValido,
            motivoRechazo
        );

        assertNotNull(resultado, "El préstamo rechazado no debe ser null");
        assertEquals(Prestamo.EstadoPrestamo.RECHAZADO, resultado.getEstado(),
            "El estado debe ser RECHAZADO");
        assertEquals(motivoRechazo, resultado.getMotivoRechazo(),
            "El motivo de rechazo debe coincidir");

        verify(prestamoRepository, times(1)).findById(prestamoIdValido);
        verify(prestamoRepository, times(1)).save(any(Prestamo.class));
    }

    @Test
    @DisplayName("Obtener detalles de préstamo existente")
    void testObtenerDetallesPrestamo_Existente() {
        when(prestamoRepository.findById(prestamoIdValido)).thenReturn(Optional.of(prestamoExistente));

        Prestamo resultado = prestamoService.obtenerDetallesPrestamo(prestamoIdValido);

        assertNotNull(resultado, "El préstamo encontrado no debe ser null");
        assertEquals(prestamoIdValido, resultado.getId(),
            "El ID debe coincidir");

        verify(prestamoRepository, times(1)).findById(prestamoIdValido);
    }

    @Test
    @DisplayName("Obtener detalles de préstamo inexistente debe lanzar excepción")
    void testObtenerDetallesPrestamo_Inexistente() {
        UUID idInexistente = UUID.randomUUID();
        when(prestamoRepository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            prestamoService.obtenerDetallesPrestamo(idInexistente);
        }, "Debe lanzar excepción cuando el préstamo no existe");

        verify(prestamoRepository, times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("Aprobar préstamo inexistente debe lanzar excepción")
    void testAprobarPrestamo_Inexistente() {
        UUID idInexistente = UUID.randomUUID();
        when(prestamoRepository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            prestamoService.aprobarPrestamo(idInexistente, LocalDate.now(), new BigDecimal("0.10"));
        }, "Debe lanzar excepción cuando el préstamo no existe");

        verify(prestamoRepository, times(1)).findById(idInexistente);
        verify(prestamoRepository, never()).save(any(Prestamo.class));
    }

    @Test
    @DisplayName("Rechazar préstamo inexistente debe lanzar excepción")
    void testRechazarPrestamo_Inexistente() {
        UUID idInexistente = UUID.randomUUID();
        when(prestamoRepository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            prestamoService.rechazarPrestamo(idInexistente, "Motivo de prueba");
        }, "Debe lanzar excepción cuando el préstamo no existe");

        verify(prestamoRepository, times(1)).findById(idInexistente);
        verify(prestamoRepository, never()).save(any(Prestamo.class));
    }

    @Test
    @DisplayName("Crear solicitud con monto cero debe fallar")
    void testCrearSolicitudPrestamo_MontoCero() {
        assertThrows(IllegalArgumentException.class, () -> {
            prestamoService.crearSolicitudPrestamo(
                solicitanteValido,
                BigDecimal.ZERO,
                12,
                LocalDate.now()
            );
        }, "No debe permitir monto cero");
    }

    @Test
    @DisplayName("Crear solicitud con plazo cero debe fallar")
    void testCrearSolicitudPrestamo_PlazoCero() {
        assertThrows(IllegalArgumentException.class, () -> {
            prestamoService.crearSolicitudPrestamo(
                solicitanteValido,
                new BigDecimal("50000.00"),
                0,
                LocalDate.now()
            );
        }, "No debe permitir plazo cero");
    }

    @Test
    @DisplayName("Aprobar préstamo ya aprobado debe fallar")
    void testAprobarPrestamo_YAaprobado() {
        Prestamo prestamoYaAprobado = prestamoExistente.aprobar(LocalDate.now(), new BigDecimal("0.10"));
        when(prestamoRepository.findById(prestamoIdValido)).thenReturn(Optional.of(prestamoYaAprobado));

        assertThrows(IllegalStateException.class, () -> {
            prestamoService.aprobarPrestamo(prestamoIdValido, LocalDate.now(), new BigDecimal("0.12"));
        }, "No debe permitir aprobar un préstamo ya aprobado");
    }

    @Test
    @DisplayName("Rechazar préstamo ya rechazado debe fallar")
    void testRechazarPrestamo_YARechazado() {
        Prestamo prestamoYaRechazado = prestamoExistente.rechazar("Motivo original");
        when(prestamoRepository.findById(prestamoIdValido)).thenReturn(Optional.of(prestamoYaRechazado));

        assertThrows(IllegalStateException.class, () -> {
            prestamoService.rechazarPrestamo(prestamoIdValido, "Nuevo motivo");
        }, "No debe permitir rechazar un préstamo ya rechazado");
    }
}