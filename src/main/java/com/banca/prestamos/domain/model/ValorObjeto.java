package com.banca.prestamos.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class ValorObjeto {

    public static class TasaInteres {
        private final BigDecimal valor;
        private final String tipo;

        private TasaInteres(BigDecimal valor, String tipo) {
            this.valor = Objects.requireNonNull(valor, "El valor de la tasa no puede ser nulo");
            this.tipo = Objects.requireNonNull(tipo, "El tipo de tasa no puede ser nulo");
            validarRango(valor);
            validarTipo(tipo);
        }

        private void validarRango(BigDecimal valor) {
            if (valor.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("La tasa de interés no puede ser negativa");
            }
            if (valor.compareTo(new BigDecimal("1.0")) > 0) {
                throw new IllegalArgumentException("La tasa de interés no puede exceder el 100%");
            }
        }

        private void validarTipo(String tipo) {
            if (!tipo.equals("FIJA") && !tipo.equals("VARIABLE")) {
                throw new IllegalArgumentException("Tipo de tasa inválido: " + tipo + ". Debe ser FIJA o VARIABLE");
            }
        }

        public static TasaInteres crear(BigDecimal valor, String tipo) {
            return new TasaInteres(valor, tipo);
        }

        public static TasaInteres tasaFija(BigDecimal porcentaje) {
            return new TasaInteres(porcentaje.divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_UP), "FIJA");
        }

        public static TasaInteres tasaVariable(BigDecimal porcentaje) {
            return new TasaInteres(porcentaje.divide(BigDecimal.valueOf(100), 6, RoundingMode.HALF_UP), "VARIABLE");
        }

        public BigDecimal getValor() {
            return valor;
        }

        public String getTipo() {
            return tipo;
        }

        public BigDecimal getValorPorcentual() {
            return valor.multiply(BigDecimal.valueOf(100));
        }

        public boolean esFija() {
            return "FIJA".equals(tipo);
        }

        public boolean esVariable() {
            return "VARIABLE".equals(tipo);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TasaInteres that = (TasaInteres) o;
            return valor.compareTo(that.valor) == 0 && Objects.equals(tipo, that.tipo);
        }

        @Override
        public int hashCode() {
            return Objects.hash(valor, tipo);
        }

        @Override
        public String toString() {
            return String.format("TasaInteres{valor=%s, tipo=%s}", valor, tipo);
        }
    }

    public static class Moneda {
        private final String codigo;
        private final String simbolo;
        private final String nombre;

        private Moneda(String codigo, String simbolo, String nombre) {
            this.codigo = Objects.requireNonNull(codigo, "El código de moneda no puede ser nulo");
            this.simbolo = Objects.requireNonNull(simbolo, "El símbolo no puede ser nulo");
            this.nombre = Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
            validarCodigo(codigo);
        }

        private void validarCodigo(String codigo) {
            if (codigo.length() != 3) {
                throw new IllegalArgumentException("El código de moneda debe tener 3 caracteres");
            }
            if (!codigo.equals(codigo.toUpperCase())) {
                throw new IllegalArgumentException("El código de moneda debe estar en mayúsculas");
            }
        }

        public static Moneda de(String codigo) {
            return switch (codigo.toUpperCase()) {
                case "PEN" -> new Moneda("PEN", "S/.", "Sol Peruano");
                case "USD" -> new Moneda("USD", "$", "Dólar Estadounidense");
                case "EUR" -> new Moneda("EUR", "€", "Euro");
                default -> new Moneda(codigo, codigo, "Moneda Desconocida");
            };
        }

        public static Moneda soles() {
            return new Moneda("PEN", "S/.", "Sol Peruano");
        }

        public static Moneda dolares() {
            return new Moneda("USD", "$", "Dólar Estadounidense");
        }

        public String getCodigo() {
            return codigo;
        }

        public String getSimbolo() {
            return simbolo;
        }

        public String getNombre() {
            return nombre;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Moneda moneda = (Moneda) o;
            return Objects.equals(codigo, moneda.codigo);
        }

        @Override
        public int hashCode() {
            return Objects.hash(codigo);
        }

        @Override
        public String toString() {
            return String.format("Moneda{codigo='%s', simbolo='%s', nombre='%s'}", codigo, simbolo, nombre);
        }
    }

    public static class Monto {
        private final BigDecimal valor;
        private final Moneda moneda;

        private Monto(BigDecimal valor, Moneda moneda) {
            this.valor = Objects.requireNonNull(valor, "El monto no puede ser nulo");
            this.moneda = Objects.requireNonNull(moneda, "La moneda no puede ser nula");
            validarPositivo(valor);
        }

        private void validarPositivo(BigDecimal valor) {
            if (valor.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("El monto debe ser mayor a cero");
            }
        }

        public static Monto crear(BigDecimal valor, Moneda moneda) {
            return new Monto(valor, moneda);
        }

        public static Monto soles(BigDecimal valor) {
            return new Monto(valor, Moneda.soles());
        }

        public static Monto dolares(BigDecimal valor) {
            return new Monto(valor, Moneda.dolares());
        }

        public BigDecimal getValor() {
            return valor;
        }

        public Moneda getMoneda() {
            return moneda;
        }

        public boolean esMayorQue(Monto otro) {
            return this.valor.compareTo(otro.valor) > 0;
        }

        public boolean tieneMismaMoneda(Monto otro) {
            return this.moneda.equals(otro.moneda);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Monto monto = (Monto) o;
            return valor.compareTo(monto.valor) == 0 && Objects.equals(moneda, monto.moneda);
        }

        @Override
        public int hashCode() {
            return Objects.hash(valor, moneda);
        }

        @Override
        public String toString() {
            return String.format("%s %s", moneda.getSimbolo(), valor);
        }
    }
}