package com.banca.prestamos.infrastructure.adapter.out.persistence;

import com.banca.prestamos.application.port.out.PrestamoRepositoryPort;
import com.banca.prestamos.domain.model.Prestamo;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PrestamoRepository implements PrestamoRepositoryPort {

    private final SpringDataPrestamoRepository springDataPrestamoRepository;

    public PrestamoRepository(SpringDataPrestamoRepository springDataPrestamoRepository) {
        this.springDataPrestamoRepository = springDataPrestamoRepository;
    }

    @Override
    public Prestamo save(Prestamo prestamo) {
        PrestamoEntity entity = PrestamoEntity.fromDomain(prestamo);
        return springDataPrestamoRepository.save(entity).toDomain();
    }

    @Override
    public Optional<Prestamo> findById(UUID id) {
        return springDataPrestamoRepository.findById(id).map(PrestamoEntity::toDomain);
    }
}
