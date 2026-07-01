package com.dylansalinas.ecommerce.exception;

// [REPASO] Nueva excepciÃ³n.
// Se lanza cuando se intenta guardar o actualizar una categorÃ­a con nombre vacÃ­o o nulo.
public class CategoriaNombreInvalidoException extends RuntimeException {

    public CategoriaNombreInvalidoException(String mensaje) {
        super(mensaje);
    }
}

