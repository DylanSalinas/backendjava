package com.dylansalinas.ecommerce.service;

import com.dylansalinas.ecommerce.exception.ProductoNoEncontradoException;
import com.dylansalinas.ecommerce.model.Producto;
import com.dylansalinas.ecommerce.util.Validador;

import java.util.ArrayList;
import java.util.List;

public class ProductoService {

    private List<Producto> productos = new ArrayList<>();

    // ids únicos aunque existan varias instancias de este servicio
    private static int contadorId = 1;

    public Producto guardar(Producto p) {
        Validador.validarNombre(p.getNombre());
        Validador.validarPrecio(p.getPrecio());
        Validador.validarStock(p.getStock());
        Validador.validarCategoria(p.getCategoria());

        p.setId(contadorId);
        contadorId++;

        productos.add(p);
        return p;
    }

    public List<Producto> listarTodos() {
        // misma instancia que usa el servicio: quien la modifica cambia el catálogo
        return productos;
    }

    public Producto obtenerPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                return p;
            }
        }
        throw new ProductoNoEncontradoException("No se encontró un producto con id " + id);
    }

    public Producto actualizar(int id, Producto datos) {
        Producto p = obtenerPorId(id);

        Validador.validarNombre(datos.getNombre());
        Validador.validarPrecio(datos.getPrecio());
        Validador.validarStock(datos.getStock());
        Validador.validarCategoria(datos.getCategoria());

        // p apunta al objeto ya contenido en productos; no hace falta reemplazar el elemento
        p.setNombre(datos.getNombre());
        p.setPrecio(datos.getPrecio());
        p.setStock(datos.getStock());
        p.setCategoria(datos.getCategoria());

        return p;
    }

    public void eliminar(int id) {
        Producto p = obtenerPorId(id);
        productos.remove(p);
    }
}
