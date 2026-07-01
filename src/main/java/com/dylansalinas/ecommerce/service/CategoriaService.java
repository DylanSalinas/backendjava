package com.dylansalinas.ecommerce.service;

// [REPASO] Nueva clase: servicio para categorÃ­as.
// Mismo patrÃ³n que ProductoService.

import com.dylansalinas.ecommerce.exception.CategoriaNoEncontradaException;
import com.dylansalinas.ecommerce.exception.CategoriaNombreInvalidoException;
import com.dylansalinas.ecommerce.model.Categoria;
import com.dylansalinas.ecommerce.repository.CategoriaRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public Categoria guardar(Categoria c) {
        return repository.save(c);
    }

    public List<Categoria> listarTodas() {
        return repository.findAll();
    }

    public Categoria obtenerPorId(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new CategoriaNoEncontradaException("No se encontrÃ³ una categorÃ­a con id " + id));
    }

    public Categoria actualizar(int id, Categoria datos) {
        if (datos.getNombre() == null || datos.getNombre().isBlank()) {
            throw new CategoriaNombreInvalidoException("El nombre de la categorÃ­a no puede estar vacÃ­o.");
        }
        Categoria c = obtenerPorId(id);
        c.setNombre(datos.getNombre());
        c.setDescripcion(datos.getDescripcion());
        return repository.save(c);
    }

    public void eliminar(int id) {
        Categoria c = obtenerPorId(id);
        repository.delete(c);
    }
}

