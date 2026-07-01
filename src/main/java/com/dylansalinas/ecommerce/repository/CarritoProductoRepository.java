package com.dylansalinas.ecommerce.repository;

import com.dylansalinas.ecommerce.model.Carrito;
import com.dylansalinas.ecommerce.model.CarritoProducto;
import com.dylansalinas.ecommerce.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CarritoProductoRepository extends JpaRepository<CarritoProducto, Integer> {

    // Busca si ya existe una fila con ese carrito y ese producto.
    // Si existe, incrementamos la cantidad. Si no, creamos una nueva fila.
    Optional<CarritoProducto> findByCarritoAndProducto(Carrito carrito, Producto producto);
}
