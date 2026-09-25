package com.banca.prestamos.application.port.in;

import com.banca.prestamos.domain.model.Prestamo;
import com.banca.prestamos.domain.model.Solicitante;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Puerto de entrada para los casos de uso relacionados con préstamos.
 * Define las operaciones que el dominio expone a la capa de aplicación.
 */
public interface PrestamoUseCase {

    /**
     * Crea una nueva solicitud de préstamo.
     * @param solicitante Datos del solicitante del préstamo
     * @param monto Monto solicitado
     * @param plazoMeses Plazo en meses para la devolución
     * @param fechaSolicitud Fecha de solicitud
     * @return El préstamo creado con su identificador único
     * @throws IllegalArgumentException Si algún parámetro no cumple con las invariantes del dominio
     */
    Prestamo crearSolicitudPrestamo(Solicitante solicitante, BigDecimal monto, int plazoMeses, LocalDate fechaSolicitud);

    /**
     * Aprobar un préstamo existente.
     * @param prestamoId Identificador único del préstamo
     * @param fechaAprobacion Fecha en la que se aprueba el préstamo
     * @param interesTasa Tasa de interés aplicada (ej: 0.05 para 5%)
     * @return El préstamo aprobado con su nuevo estado
     * @throws IllegalStateException Si el préstamo no está en estado PENDIENTE
     * @throws IllegalArgumentException Si la tasa de interés es negativa
     */
    Prestamo aprobarPrestamo(UUID prestamoId, LocalDate fechaAprobacion, BigDecimal interesTasa);

    /**
     * Rechazar un préstamo existente.
     * @param prestamoId Identificador único del préstamo
     * @param motivo Motivo del rechazo
     * @return El préstamo rechazado con su nuevo estado
     * @throws IllegalStateException Si el préstamo no está en estado PENDIENTE
     * @throws IllegalArgumentException Si el motivo es nulo o vacío
     */
    Prestamo rechazarPrestamo(UUID prestamoId, String motivo);

    /**
     * Obtener los detalles de un préstamo por su identificador.
     * @param prestamoId Identificador único del préstamo
     * @return Detalles completos del préstamo
     * @throws IllegalArgumentException Si el préstamo no existe
     */
    Prestamo obtenerDetallesPrestamo(UUID prestamoId);

    /**
     * Calcular el cronograma de pagos para un préstamo aprobado.
     * @param prestamoId Identificador único del préstamo
     * @return Lista de cuotas con fechas y montos
     * @throws IllegalStateException Si el préstamo no está aprobado
     */
    // Método comentado intencionalmente para que el participante lo implemente en la fase 3
    // List<Cuota> calcularCronogramaPagos(UUID prestamoId);
}