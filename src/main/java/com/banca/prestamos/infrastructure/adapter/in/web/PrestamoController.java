package com.banca.prestamos.infrastructure.adapter.in.web;

import com.banca.prestamos.application.port.in.PrestamoUseCase;
import com.banca.prestamos.domain.model.Prestamo;
import com.banca.prestamos.domain.model.Solicitante;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/prestamos")
public class PrestamoController {

    private final PrestamoUseCase prestamoUseCase;

    public PrestamoController(PrestamoUseCase prestamoUseCase) {
        this.prestamoUseCase = prestamoUseCase;
    }

    @PostMapping
    public ResponseEntity<PrestamoResponse> crearSolicitudPrestamo(
            @Valid @RequestBody SolicitudPrestamoRequest request) {
        Solicitante solicitante = Solicitante.crearSolicitante(
                request.nombre(),
                request.apellido(),
                request.tipoDocumento(),
                request.numeroDocumento(),
                request.email(),
                request.telefono()
        );

        Prestamo prestamo = prestamoUseCase.crearSolicitudPrestamo(
                solicitante,
                request.monto(),
                request.plazoMeses(),
                LocalDate.now()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapToResponse(prestamo));
    }

    @PostMapping("/{id}/aprobar")
    public ResponseEntity<PrestamoResponse> aprobarPrestamo(
            @PathVariable UUID id,
            @Valid @RequestBody AprobacionRequest request) {
        Prestamo prestamo = prestamoUseCase.aprobarPrestamo(
                id,
                LocalDate.now(),
                request.tasaInteres()
        );
        return ResponseEntity.ok(mapToResponse(prestamo));
    }

    @PostMapping("/{id}/rechazar")
    public ResponseEntity<PrestamoResponse> rechazarPrestamo(
            @PathVariable UUID id,
            @Valid @RequestBody RechazoRequest request) {
        Prestamo prestamo = prestamoUseCase.rechazarPrestamo(id, request.motivo());
        return ResponseEntity.ok(mapToResponse(prestamo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestamoResponse> obtenerDetallesPrestamo(@PathVariable UUID id) {
        Prestamo prestamo = prestamoUseCase.obtenerDetallesPrestamo(id);
        return ResponseEntity.ok(mapToResponse(prestamo));
    }

    public ResponseEntity<Prestamo> crearSolicitud(Solicitante solicitante, BigDecimal monto,
            int plazoMeses, LocalDate fechaSolicitud) {
        Prestamo prestamo = prestamoUseCase.crearSolicitudPrestamo(
                solicitante, monto, plazoMeses, fechaSolicitud);
        return ResponseEntity.status(HttpStatus.CREATED).body(prestamo);
    }

    public ResponseEntity<Prestamo> aprobarPrestamo(String id, LocalDate fechaAprobacion,
            BigDecimal tasaInteres) {
        Prestamo prestamo = prestamoUseCase.aprobarPrestamo(
                UUID.fromString(id), fechaAprobacion, tasaInteres);
        return ResponseEntity.ok(prestamo);
    }

    public ResponseEntity<Prestamo> rechazarPrestamo(String id, String motivo) {
        Prestamo prestamo = prestamoUseCase.rechazarPrestamo(UUID.fromString(id), motivo);
        return ResponseEntity.ok(prestamo);
    }

    public ResponseEntity<Prestamo> obtenerPrestamo(String id) {
        Prestamo prestamo = prestamoUseCase.obtenerDetallesPrestamo(UUID.fromString(id));
        if (prestamo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(prestamo);
    }

    private PrestamoResponse mapToResponse(Prestamo prestamo) {
        return new PrestamoResponse(
                prestamo.getId(),
                prestamo.getSolicitante().getNombre(),
                prestamo.getSolicitante().getApellido(),
                prestamo.getMonto(),
                prestamo.getPlazoMeses(),
                prestamo.getFechaSolicitud().toString(),
                prestamo.getEstado().name(),
                prestamo.getFechaAprobacion() != null ? prestamo.getFechaAprobacion().toString() : null,
                prestamo.getInteresTasa() != null ? prestamo.getInteresTasa().toString() : null,
                prestamo.getMotivoRechazo()
        );
    }

    public record SolicitudPrestamoRequest(
            @NotBlank String nombre,
            @NotBlank String apellido,
            @NotBlank String tipoDocumento,
            @NotBlank String numeroDocumento,
            @NotBlank @Email String email,
            @NotBlank String telefono,
            @NotNull @Positive BigDecimal monto,
            @NotNull @Positive Integer plazoMeses
    ) {}

    public record AprobacionRequest(@NotNull @Positive BigDecimal tasaInteres) {}

    public record RechazoRequest(@NotBlank String motivo) {}

    public record PrestamoResponse(
            UUID id,
            String nombre,
            String apellido,
            BigDecimal monto,
            int plazoMeses,
            String fechaSolicitud,
            String estado,
            String fechaAprobacion,
            String interesTasa,
            String motivoRechazo
    ) {}
}