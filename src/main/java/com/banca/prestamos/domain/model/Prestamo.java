package com.banca.prestamos.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * Agregado raíz del dominio de préstamos.
 * Representa un préstamo personal con todas sus invariantes protegidas.
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Prestamo {

    @NotNull
    private UUID id;

    @NotNull
    @Setter(AccessLevel.PRIVATE)
    private Solicitante solicitante;

    @NotNull
    @DecimalMin(value = "100.00", message = "El monto mínimo es 100")
    private BigDecimal monto;

    @Positive(message = "El plazo debe ser positivo")
    private int plazoMeses;

    @NotNull
    private LocalDate fechaSolicitud;

    @NotNull
    @Setter(AccessLevel.PRIVATE)
    private EstadoPrestamo estado;

    private LocalDate fechaAprobacion;

    @DecimalMin(value = "0.00", inclusive = false, message = "La tasa de interés debe ser positiva")
    private BigDecimal interesTasa;

    @Size(max = 255, message = "El motivo no puede exceder 255 caracteres")
    private String motivoRechazo;

    /**
     * Crea una nueva solicitud de préstamo.
     * @param solicitante Datos del solicitante
     * @param monto Monto solicitado
     * @param plazoMeses Plazo en meses
     * @param fechaSolicitud Fecha de solicitud
     * @return Nueva instancia de Prestamo en estado PENDIENTE
     */
    public static Prestamo crearSolicitud(Solicitante solicitante, BigDecimal monto, int plazoMeses, LocalDate fechaSolicitud) {
        if (solicitante == null) {
            throw new IllegalArgumentException("El solicitante no puede ser nulo");
        }
        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }
        if (plazoMeses <= 0) {
            throw new IllegalArgumentException("El plazo debe ser positivo");
        }
        if (fechaSolicitud == null) {
            throw new IllegalArgumentException("La fecha de solicitud no puede ser nula");
        }

        return new Prestamo(
            UUID.randomUUID(),
            solicitante,
            monto,
            plazoMeses,
            fechaSolicitud,
            EstadoPrestamo.PENDIENTE,
            null,
            null,
            null
        );
    }

    /**
     * Aprobar el préstamo con una tasa de interés.
     * @param fechaAprobacion Fecha de aprobación
     * @param interesTasa Tasa de interés aplicada
     * @return El préstamo con estado APROBADO
     */
    public Prestamo aprobar(LocalDate fechaAprobacion, BigDecimal interesTasa) {
        if (this.estado != EstadoPrestamo.PENDIENTE) {
            throw new IllegalStateException("Solo se puede aprobar préstamos en estado PENDIENTE");
        }
        if (fechaAprobacion == null) {
            throw new IllegalArgumentException("La fecha de aprobación no puede ser nula");
        }
        if (interesTasa == null || interesTasa.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("La tasa de interés debe ser positiva");
        }

        this.estado = EstadoPrestamo.APROBADO;
        this.fechaAprobacion = fechaAprobacion;
        this.interesTasa = interesTasa;
        return this;
    }

    /**
     * Rechazar el préstamo con un motivo.
     * @param motivo Motivo del rechazo
     * @return El préstamo con estado RECHAZADO
     */
    public Prestamo rechazar(String motivo) {
        if (this.estado != EstadoPrestamo.PENDIENTE) {
            throw new IllegalStateException("Solo se puede rechazar préstamos en estado PENDIENTE");
        }
        if (motivo == null || motivo.trim().isEmpty()) {
            throw new IllegalArgumentException("El motivo no puede ser nulo o vacío");
        }

        this.estado = EstadoPrestamo.RECHAZADO;
        this.motivoRechazo = motivo;
        return this;
    }

    public static Prestamo reconstruir(UUID id, Solicitante solicitante, BigDecimal monto, int plazoMeses,
            LocalDate fechaSolicitud, EstadoPrestamo estado, LocalDate fechaAprobacion,
            BigDecimal interesTasa, String motivoRechazo) {
        return new Prestamo(id, solicitante, monto, plazoMeses, fechaSolicitud, estado,
                fechaAprobacion, interesTasa, motivoRechazo);
    }

    /**
     * Enum que representa los posibles estados de un préstamo.
     */
    public enum EstadoPrestamo {
        PENDIENTE,
        APROBADO,
        RECHAZADO
    }
}