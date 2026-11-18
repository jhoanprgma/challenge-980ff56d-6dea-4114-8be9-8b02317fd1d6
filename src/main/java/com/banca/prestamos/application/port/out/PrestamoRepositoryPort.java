package com.banca.prestamos.application.port.out;

import com.banca.prestamos.domain.model.Prestamo;

import java.util.Optional;
import java.util.UUID;

public interface PrestamoRepositoryPort {

    Prestamo save(Prestamo prestamo);

    Optional<Prestamo> findById(UUID id);
}
