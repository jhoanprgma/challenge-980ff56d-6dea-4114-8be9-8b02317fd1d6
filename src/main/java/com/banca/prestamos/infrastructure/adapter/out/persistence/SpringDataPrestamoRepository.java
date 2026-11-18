package com.banca.prestamos.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataPrestamoRepository extends JpaRepository<PrestamoEntity, UUID> {
}
