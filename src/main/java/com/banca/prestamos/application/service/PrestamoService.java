package com.banca.prestamos.application.service;

import com.banca.prestamos.application.port.in.PrestamoUseCase;
import com.banca.prestamos.domain.model.Prestamo;
import com.banca.prestamos.domain.model.Solicitante;
import com.banca.prestamos.infrastructure.adapter.out.persistence.PrestamoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class PrestamoService implements PrestamoUseCase {

    private static final Logger logger = LoggerFactory.getLogger(PrestamoService.class);

    private final PrestamoRepository prestamoRepository;

    public PrestamoService(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    @Override
    public Prestamo crearSolicitudPrestamo(Solicitante solicitante, BigDecimal monto, 
                                           int plazoMeses, LocalDate fechaSolicitud) {
        logger.info("Creando solicitud de préstamo para solicitante: {} por monto: {} plazo: {} meses",
                    solicitante.getNombre(), monto, plazoMeses);

        validarMonto(monto);
        validarPlazo(plazoMeses);
        validarSolicitante(solicitante);

        Prestamo prestamo = Prestamo.crearSolicitud(solicitante, monto, plazoMeses, fechaSolicitud);
        Prestamo guardado = prestamoRepository.save(prestamo);

        logger.info("Préstamo creado exitosamente con ID: {}", guardado.getId());
        return guardado;
    }

    @Override
    public Prestamo aprobarPrestamo(UUID prestamoId, LocalDate fechaAprobacion, BigDecimal interesTasa) {
        logger.info("Aprobando préstamo ID: {} con tasa de interés: {}", prestamoId, interesTasa);

        Prestamo prestamo = obtenerPrestamoOThrow(prestamoId);
        validarTasaInteres(interesTasa);

        Prestamo aprobado = prestamo.aprobar(fechaAprobacion, interesTasa);
        Prestamo guardado = prestamoRepository.save(aprobado);

        logger.info("Préstamo ID: {} aprobado exitosamente", prestamoId);
        return guardado;
    }

    @Override
    public Prestamo rechazarPrestamo(UUID prestamoId, String motivo) {
        logger.info("Rechazando préstamo ID: {} motivo: {}", prestamoId, motivo);

        Prestamo prestamo = obtenerPrestamoOThrow(prestamoId);
        validarMotivoRechazo(motivo);

        Prestamo rechazado = prestamo.rechazar(motivo);
        Prestamo guardado = prestamoRepository.save(rechazado);

        logger.info("Préstamo ID: {} rechazado exitosamente", prestamoId);
        return guardado;
    }

    @Override
    @Transactional(readOnly = true)
    public Prestamo obtenerDetallesPrestamo(UUID prestamoId) {
        logger.debug("Obteniendo detalles del préstamo ID: {}", prestamoId);
        return obtenerPrestamoOThrow(prestamoId);
    }

    private Prestamo obtenerPrestamoOThrow(UUID prestamoId) {
        return prestamoRepository.findById(prestamoId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Préstamo no encontrado con ID: " + prestamoId));
    }

    private void validarMonto(BigDecimal monto) {
        if (monto == null) {
            throw new IllegalArgumentException("El monto del préstamo no puede ser nulo");
        }
        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del préstamo debe ser mayor a cero");
        }
        if (monto.compareTo(new BigDecimal("500000")) > 0) {
            throw new IllegalArgumentException("El monto máximo permitido es 500,000");
        }
    }

    private void validarPlazo(int plazoMeses) {
        if (plazoMeses <= 0) {
            throw new IllegalArgumentException("El plazo debe ser mayor a cero meses");
        }
        if (plazoMeses > 360) {
            throw new IllegalArgumentException("El plazo máximo permitido es 360 meses (30 años)");
        }
    }

    private void validarSolicitante(Solicitante solicitante) {
        if (solicitante == null) {
            throw new IllegalArgumentException("El solicitante no puede ser nulo");
        }
        if (solicitante.getNumeroDocumento() == null || solicitante.getNumeroDocumento().isBlank()) {
            throw new IllegalArgumentException("El número de documento es obligatorio");
        }
    }

    private void validarTasaInteres(BigDecimal interesTasa) {
        if (interesTasa == null) {
            throw new IllegalArgumentException("La tasa de interés no puede ser nula");
        }
        if (interesTasa.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("La tasa de interés no puede ser negativa");
        }
        if (interesTasa.compareTo(new BigDecimal("1.0")) > 0) {
            throw new IllegalArgumentException("La tasa de interés no puede exceder el 100%");
        }
    }

    private void validarMotivoRechazo(String motivo) {
        if (motivo == null || motivo.isBlank()) {
            throw new IllegalArgumentException("El motivo de rechazo es obligatorio");
        }
        if (motivo.length() < 10) {
            throw new IllegalArgumentException("El motivo de rechazo debe tener al menos 10 caracteres");
        }
    }
}