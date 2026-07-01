package com.dylansalinas.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dylansalinas.ecommerce.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    
} 

