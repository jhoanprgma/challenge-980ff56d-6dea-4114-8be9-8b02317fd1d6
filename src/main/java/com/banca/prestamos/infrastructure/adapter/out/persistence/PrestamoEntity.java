package com.banca.prestamos.infrastructure.adapter.out.persistence;


import com.banca.prestamos.domain.model.Prestamo;
import com.banca.prestamos.domain.model.Solicitante;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "prestamos")
public class PrestamoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String solicitanteNombre;

    @Column(nullable = false)
    private String solicitanteApellido;

    @Column(nullable = false)
    private String solicitanteTipoDocumento;

    @Column(nullable = false, unique = true)
    private String solicitanteNumeroDocumento;

    @Column(nullable = false)
    private String solicitanteEmail;

    @Column(nullable = false)
    private String solicitanteTelefono;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal monto;

    @Column(nullable = false)
    private Integer plazoMeses;

    @Column(nullable = false)
    private LocalDate fechaSolicitud;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPrestamoEntity estado;

    private LocalDate fechaAprobacion;

    @Column(precision = 19, scale = 4)
    private BigDecimal interesTasa;

    private String motivoRechazo;

    public PrestamoEntity() {}

    public static PrestamoEntity fromDomain(Prestamo prestamo) {
        PrestamoEntity entity = new PrestamoEntity();
        entity.setId(prestamo.getId());
        entity.setSolicitanteNombre(prestamo.getSolicitante().getNombre());
        entity.setSolicitanteApellido(prestamo.getSolicitante().getApellido());
        entity.setSolicitanteTipoDocumento(prestamo.getSolicitante().getTipoDocumento());
        entity.setSolicitanteNumeroDocumento(prestamo.getSolicitante().getNumeroDocumento());
        entity.setSolicitanteEmail(prestamo.getSolicitante().getEmail());
        entity.setSolicitanteTelefono(prestamo.getSolicitante().getTelefono());
        entity.setMonto(prestamo.getMonto());
        entity.setPlazoMeses(prestamo.getPlazoMeses());
        entity.setFechaSolicitud(prestamo.getFechaSolicitud());
        entity.setEstado(EstadoPrestamoEntity.valueOf(prestamo.getEstado().name()));
        entity.setFechaAprobacion(prestamo.getFechaAprobacion());
        entity.setInteresTasa(prestamo.getInteresTasa());
        entity.setMotivoRechazo(prestamo.getMotivoRechazo());
        return entity;
    }

    public Prestamo toDomain() {
        Solicitante solicitante = Solicitante.reconstruir(
                this.id,
                this.solicitanteNombre,
                this.solicitanteApellido,
                this.solicitanteTipoDocumento,
                this.solicitanteNumeroDocumento,
                this.solicitanteEmail,
                this.solicitanteTelefono
        );

        Prestamo prestamo = Prestamo.reconstruir(
                this.id,
                solicitante,
                this.monto,
                this.plazoMeses,
                this.fechaSolicitud,
                this.estado.toDomain(),
                this.fechaAprobacion,
                this.interesTasa,
                this.motivoRechazo
        );

        return prestamo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSolicitanteNombre() {
        return solicitanteNombre;
    }

    public void setSolicitanteNombre(String solicitanteNombre) {
        this.solicitanteNombre = solicitanteNombre;
    }

    public String getSolicitanteApellido() {
        return solicitanteApellido;
    }

    public void setSolicitanteApellido(String solicitanteApellido) {
        this.solicitanteApellido = solicitanteApellido;
    }

    public String getSolicitanteTipoDocumento() {
        return solicitanteTipoDocumento;
    }

    public void setSolicitanteTipoDocumento(String solicitanteTipoDocumento) {
        this.solicitanteTipoDocumento = solicitanteTipoDocumento;
    }

    public String getSolicitanteNumeroDocumento() {
        return solicitanteNumeroDocumento;
    }

    public void setSolicitanteNumeroDocumento(String solicitanteNumeroDocumento) {
        this.solicitanteNumeroDocumento = solicitanteNumeroDocumento;
    }

    public String getSolicitanteEmail() {
        return solicitanteEmail;
    }

    public void setSolicitanteEmail(String solicitanteEmail) {
        this.solicitanteEmail = solicitanteEmail;
    }

    public String getSolicitanteTelefono() {
        return solicitanteTelefono;
    }

    public void setSolicitanteTelefono(String solicitanteTelefono) {
        this.solicitanteTelefono = solicitanteTelefono;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Integer getPlazoMeses() {
        return plazoMeses;
    }

    public void setPlazoMeses(Integer plazoMeses) {
        this.plazoMeses = plazoMeses;
    }

    public LocalDate getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDate fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public EstadoPrestamoEntity getEstado() {
        return estado;
    }

    public void setEstado(EstadoPrestamoEntity estado) {
        this.estado = estado;
    }

    public LocalDate getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(LocalDate fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public BigDecimal getInteresTasa() {
        return interesTasa;
    }

    public void setInteresTasa(BigDecimal interesTasa) {
        this.interesTasa = interesTasa;
    }

    public String getMotivoRechazo() {
        return motivoRechazo;
    }

    public void setMotivoRechazo(String motivoRechazo) {
        this.motivoRechazo = motivoRechazo;
    }

    public enum EstadoPrestamoEntity {
        PENDIENTE,
        APROBADO,
        RECHAZADO;

        public Prestamo.EstadoPrestamo toDomain() {
            return Prestamo.EstadoPrestamo.valueOf(this.name());
        }

        public static EstadoPrestamoEntity fromDomain(Prestamo.EstadoPrestamo estado) {
            return EstadoPrestamoEntity.valueOf(estado.name());
        }
    }
}