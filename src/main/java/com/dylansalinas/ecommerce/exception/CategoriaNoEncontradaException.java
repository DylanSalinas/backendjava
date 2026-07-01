package com.dylansalinas.ecommerce.exception;

// [REPASO] Nueva excepciÃ³n.
// Se lanza cuando se busca una categorÃ­a por id y no existe.
public class CategoriaNoEncontradaException extends RuntimeException {

    public CategoriaNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}

