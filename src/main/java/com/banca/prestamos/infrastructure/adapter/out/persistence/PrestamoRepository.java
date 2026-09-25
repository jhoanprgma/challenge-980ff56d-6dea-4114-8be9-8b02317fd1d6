package com.banca.prestamos.infrastructure.adapter.out.persistence;


import com.banca.prestamos.domain.model.EstadoPrestamo;
import com.banca.prestamos.domain.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, UUID> {

    Optional<Prestamo> findById(UUID id);

    List<Prestamo> findBySolicitanteId(UUID solicitanteId);

    List<Prestamo> findByEstado(Prestamo.EstadoPrestamo estado);

    @Query("SELECT p FROM Prestamo p WHERE p.fechaSolicitud BETWEEN :fechaInicio AND :fechaFin")
    List<Prestamo> findByRangoFechas(@Param("fechaInicio") LocalDate fechaInicio,
                                      @Param("fechaFin") LocalDate fechaFin);

    @Query("SELECT p FROM Prestamo p WHERE p.solicitante.numeroDocumento = :numeroDocumento")
    List<Prestamo> findByNumeroDocumento(@Param("numeroDocumento") String numeroDocumento);

    @Query("SELECT COUNT(p) FROM Prestamo p WHERE p.estado = :estado")
    long countByEstado(@Param("estado") Prestamo.EstadoPrestamo estado);

    @Query("SELECT p FROM Prestamo p WHERE p.estado = 'APROBADO' AND p.fechaAprobacion <= :fechaLimite")
    List<Prestamo> findAprobadosAntesDe(@Param("fechaLimite") LocalDate fechaLimite);

    boolean existsBySolicitanteIdAndEstado(UUID solicitanteId, Prestamo.EstadoPrestamo estado);
}