package com.dylansalinas.ecommerce.exception;

// Se lanza cuando el stock recibido es invÃ¡lido (por ejemplo, negativo).
// MÃ¡s adelante tambiÃ©n servirÃ¡ para validar compras del carrito.
public class StockInsuficienteException extends RuntimeException {

    public StockInsuficienteException(String mensaje) {
        super(mensaje);
    }
}

