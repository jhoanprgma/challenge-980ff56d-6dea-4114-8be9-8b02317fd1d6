package com.banca.prestamos.bdd;



import com.banca.prestamos.domain.model.Monto;
import com.banca.prestamos.domain.model.EstadoPrestamo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.cucumber.junit.platform.Cucumber;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.ConfigurationParameters;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.jupiter.api.Disabled;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.banca.prestamos.application.port.in.PrestamoUseCase;
import com.banca.prestamos.domain.model.Prestamo;
import com.banca.prestamos.domain.model.Solicitante;

@Cucumber
@SelectPackages("com.banca.prestamos.bdd")
@SelectClasspathResource("features")
@ConfigurationParameters({
    @ConfigurationParameter(key = "plugin", value = "pretty"),
    @ConfigurationParameter(key = "glue", value = "com.banca.prestamos.bdd")
})
@Disabled("Superficie de práctica - implementar los steps según los escenarios BDD")
public class PrestamoSteps {

    private PrestamoUseCase prestamoUseCase;
    private Prestamo prestamoActual;
    private Solicitante solicitanteActual;
    private BigDecimal montoSolicitado;
    private int plazoMesesSolicitado;
    private LocalDate fechaSolicitud;
    private String resultadoOperacion;
    private Exception exceptionCapturada;

    @Given("un solicitante con nombre {string}, apellido {string}, documento {string} numero {string}")
    public void unSolicitanteConNombreApellidoDocumentoNumero(String nombre, String apellido, String tipoDoc, String numeroDoc) {
        solicitanteActual = Solicitante.crearSolicitante(nombre, apellido, tipoDoc, numeroDoc, "test@email.com", "123456789");
    }

    @Given("el solicitante tiene email {string} y telefono {string}")
    public void elSolicitanteTieneEmailYTelefono(String email, String telefono) {
        if (solicitanteActual != null) {
            solicitanteActual = Solicitante.crearSolicitante(
                solicitanteActual.getNombre(),
                solicitanteActual.getApellido(),
                solicitanteActual.getTipoDocumento(),
                solicitanteActual.getNumeroDocumento(),
                email,
                telefono
            );
        }
    }

    @When("solicita un prestamo de {double} pesos con plazo de {int} meses")
    public void solicitaUnPrestamoDePesosConPlazoDeMeses(Double monto, Integer plazo) {
        montoSolicitado = BigDecimal.valueOf(monto);
        plazoMesesSolicitado = plazo;
        fechaSolicitud = LocalDate.now();
        try {
            prestamoActual = prestamoUseCase.crearSolicitudPrestamo(
                solicitanteActual,
                montoSolicitado,
                plazoMesesSolicitado,
                fechaSolicitud
            );
            resultadoOperacion = "EXITO";
        } catch (Exception e) {
            exceptionCapturada = e;
            resultadoOperacion = "ERROR";
        }
    }

    @When("el sistema aprueba el prestamo con tasa de interes {double}")
    public void elSistemaApruebaElPrestamoConTasaDeInteres(Double tasaInteres) {
        if (prestamoActual != null) {
            try {
                prestamoActual = prestamoUseCase.aprobarPrestamo(
                    prestamoActual.getId(),
                    LocalDate.now(),
                    BigDecimal.valueOf(tasaInteres)
                );
                resultadoOperacion = "APROBADO";
            } catch (Exception e) {
                exceptionCapturada = e;
                resultadoOperacion = "ERROR";
            }
        }
    }

    @When("el sistema rechaza el prestamo con motivo {string}")
    public void elSistemaRechazaElPrestamoConMotivo(String motivo) {
        if (prestamoActual != null) {
            try {
                prestamoActual = prestamoUseCase.rechazarPrestamo(
                    prestamoActual.getId(),
                    motivo
                );
                resultadoOperacion = "RECHAZADO";
            } catch (Exception e) {
                exceptionCapturada = e;
                resultadoOperacion = "ERROR";
            }
        }
    }

    @Then("el prestamo debe estar en estado {string}")
    public void elPrestamoDebeEstarEnEstado(String estadoEsperado) {
        if (prestamoActual != null) {
            Prestamo.EstadoPrestamo estado = prestamoActual.getEstado();
            assert estado.name().equals(estadoEsperado) : "Estado esperado: " + estadoEsperado + ", actual: " + estado.name();
        }
    }

    @Then("el prestamo debe tener un identificador unico")
    public void elPrestamoDebeTenerUnIdentificadorUnico() {
        if (prestamoActual != null) {
            UUID id = prestamoActual.getId();
            assert id != null : "El préstamo debe tener un ID asignado";
        }
    }

    @Then("la solicitud debe ser exitosa")
    public void laSolicitudDebeSerExitosa() {
        assert "EXITO".equals(resultadoOperacion) : "La operación debería ser exitosa";
    }

    @Then("la aprobación debe ser exitosa")
    public void laAprobacionDebeSerExitosa() {
        assert "APROBADO".equals(resultadoOperacion) : "La aprobación debería ser exitosa";
    }

    @Then("el rechazo debe ser exitoso")
    public void elRechazoDebeSerExitoso() {
        assert "RECHAZADO".equals(resultadoOperacion) : "El rechazo debería ser exitoso";
    }

    @And("el monto del prestamo debe ser {double}")
    public void elMontoDelPrestamoDebeSer(Double montoEsperado) {
        if (prestamoActual != null) {
            BigDecimal monto = prestamoActual.getMonto();
            assert monto.compareTo(BigDecimal.valueOf(montoEsperado)) == 0 : 
                "Monto esperado: " + montoEsperado + ", actual: " + monto;
        }
    }

    @And("el plazo del prestamo debe ser {int} meses")
    public void elPlazoDelPrestamoDebeSerMeses(Integer plazoEsperado) {
        if (prestamoActual != null) {
            int plazo = prestamoActual.getPlazoMeses();
            assert plazo == plazoEsperado : "Plazo esperado: " + plazoEsperado + ", actual: " + plazo;
        }
    }

    @And("la tasa de interes debe ser {double}")
    public void laTasaDeInteresDebeSer(Double tasaEsperada) {
        if (prestamoActual != null && prestamoActual.getInteresTasa() != null) {
            BigDecimal tasa = prestamoActual.getInteresTasa();
            assert tasa.compareTo(BigDecimal.valueOf(tasaEsperada)) == 0 :
                "Tasa esperada: " + tasaEsperada + ", actual: " + tasa;
        }
    }

    @And("el motivo de rechazo debe ser {string}")
    public void elMotivoDeRechazoDebeSer(String motivoEsperado) {
        if (prestamoActual != null) {
            String motivo = prestamoActual.getMotivoRechazo();
            assert motivo != null && motivo.equals(motivoEsperado) :
                "Motivo esperado: " + motivoEsperado + ", actual: " + motivo;
        }
    }

    @And("la fecha de solicitud debe ser hoy")
    public void laFechaDeSolicitudDebeSerHoy() {
        if (prestamoActual != null) {
            LocalDate fecha = prestamoActual.getFechaSolicitud();
            assert fecha.equals(LocalDate.now()) : "La fecha de solicitud debe ser hoy";
        }
    }
}