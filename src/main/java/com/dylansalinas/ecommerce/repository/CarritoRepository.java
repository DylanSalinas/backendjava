package com.dylansalinas.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dylansalinas.ecommerce.model.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
}
