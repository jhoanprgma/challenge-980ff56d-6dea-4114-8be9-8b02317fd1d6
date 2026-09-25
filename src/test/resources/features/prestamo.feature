Feature: Gestión de Préstamos Personales

  Background:
    Given un solicitante con nombre "Juan", apellido "Pérez", documento "DNI" numero "12345678"

  Scenario: Solicitud de préstamo exitosa
    When solicita un prestamo de 50000.00 pesos con plazo de 12 meses
    Then la solicitud debe ser exitosa
    And el prestamo debe estar en estado "SOLICITADO"
    And el prestamo debe tener un identificador unico
    And el monto del prestamo debe ser 50000.00
    And el plazo del prestamo debe ser 12 meses
    And la fecha de solicitud debe ser hoy

  Scenario: Aprobación de préstamo exitosa
    Given un solicitante con nombre "María", apellido "Gómez", documento "DNI" numero "87654321"
    And el solicitante tiene email "maria@test.com" y telefono "987654321"
    When solicita un prestamo de 100000.00 pesos con plazo de 24 meses
    And el sistema aprueba el prestamo con tasa de interes 0.15
    Then la aprobación debe ser exitosa
    And el prestamo debe estar en estado "APROBADO"
    And la tasa de interes debe ser 0.15

  Scenario: Rechazo de préstamo por riesgo crediticio
    Given un solicitante con nombre "Carlos", apellido "López", documento "DNI" numero "11223344"
    When solicita un prestamo de 200000.00 pesos con plazo de 36 meses
    And el sistema rechaza el prestamo con motivo "Riesgo crediticio elevado"
    Then el rechazo debe ser exitoso
    And el prestamo debe estar en estado "RECHAZADO"
    And el motivo de rechazo debe ser "Riesgo crediticio elevado"

  Scenario: Solicitud de préstamo con monto mínimo
    When solicita un prestamo de 1000.00 pesos con plazo de 3 meses
    Then la solicitud debe ser exitosa
    And el prestamo debe estar en estado "SOLICITADO"
    And el monto del prestamo debe ser 1000.00
    And el plazo del prestamo debe ser 3 meses

  Scenario: Solicitud de préstamo con monto máximo
    When solicita un prestamo de 500000.00 pesos con plazo de 60 meses
    Then la solicitud debe ser exitosa
    And el prestamo debe estar en estado "SOLICITADO"
    And el monto del prestamo debe ser 500000.00
    And el plazo del prestamo debe ser 60 meses

  Scenario: Múltiples solicitudes del mismo solicitante
    Given un solicitante con nombre "Ana", apellido "Martínez", documento "PASAPORTE" numero "AA123456"
    When solicita un prestamo de 30000.00 pesos con plazo de 6 meses
    Then la solicitud debe ser exitosa
    And el prestamo debe estar en estado "SOLICITADO"
    When solicita un prestamo de 25000.00 pesos con plazo de 6 meses
    Then la solicitud debe ser exitosa
    And el prestamo debe tener un identificador unico