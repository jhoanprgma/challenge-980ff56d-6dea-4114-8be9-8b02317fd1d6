package com.banca.prestamos.domain.model;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Entidad del dominio que representa a un solicitante de préstamo.
 * Contiene información personal y de contacto del solicitante.
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Solicitante {

    @NotNull
    private UUID id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(max = 100, message = "El apellido no puede exceder 100 caracteres")
    private String apellido;

    @NotBlank(message = "El tipo de documento no puede estar vacío")
    @Size(min = 2, max = 5, message = "El tipo de documento debe tener entre 2 y 5 caracteres")
    private String tipoDocumento;

    @NotBlank(message = "El número de documento no puede estar vacío")
    @Pattern(regexp = "^[0-9]{5,20}$", message = "El número de documento debe contener solo dígitos (5-20)")
    private String numeroDocumento;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email debe ser válido")
    private String email;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Pattern(regexp = "^[0-9]{7,15}$", message = "El teléfono debe contener solo dígitos (7-15)")
    private String telefono;

    /**
     * Crea un nuevo solicitante con los datos proporcionados.
     * @param nombre Nombre del solicitante
     * @param apellido Apellido del solicitante
     * @param tipoDocumento Tipo de documento (ej: DNI, PAS)
     * @param numeroDocumento Número de documento
     * @param email Email de contacto
     * @param telefono Teléfono de contacto
     * @return Nueva instancia de Solicitante
     */
    public static Solicitante crearSolicitante(String nombre, String apellido, String tipoDocumento,
            String numeroDocumento, String email, String telefono) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
        }
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede ser nulo o vacío");
        }
        if (tipoDocumento == null || tipoDocumento.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de documento no puede ser nulo o vacío");
        }
        if (numeroDocumento == null || numeroDocumento.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de documento no puede ser nulo o vacío");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El email no puede ser nulo o vacío");
        }
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono no puede ser nulo o vacío");
        }

        return new Solicitante(
            UUID.randomUUID(),
            nombre.trim(),
            apellido.trim(),
            tipoDocumento.trim(),
            numeroDocumento.trim(),
            email.trim(),
            telefono.trim()
        );
    }

    public static Solicitante reconstruir(UUID id, String nombre, String apellido, String tipoDocumento,
            String numeroDocumento, String email, String telefono) {
        return new Solicitante(id, nombre, apellido, tipoDocumento, numeroDocumento, email, telefono);
    }
}