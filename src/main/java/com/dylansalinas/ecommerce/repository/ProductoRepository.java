package com.dylansalinas.ecommerce.repository;

import com.dylansalinas.ecommerce.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    // Containing â†’ LIKE %valor%
    List<Producto> findByNombreContaining(String nombre);

    // Navega la relaciÃ³n hacia Categoria.
    // IgnoreCase â†’ sin distinciÃ³n de mayÃºsculas
    // Containing â†’ LIKE %valor%
    List<Producto> findByCategoriaNombreContainingIgnoreCase(String nombre);
}
