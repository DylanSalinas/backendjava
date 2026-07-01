package com.dylansalinas.ecommerce.exception;

// Se lanza cuando se busca un producto por id y no existe.
// Hereda de RuntimeException: no obliga a try/catch pero permite capturarla.
public class ProductoNoEncontradoException extends RuntimeException {

    public ProductoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}

