package com.dylansalinas.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carrito")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // OneToMany: un carrito tiene muchos CarritoProducto.
    // mappedBy indica que la relaciÃ³n estÃ¡ definida en CarritoProducto (campo "carrito").
    // CascadeType.ALL: si se elimina el carrito, se eliminan sus CarritoProducto.
    // orphanRemoval: si se quita un CarritoProducto de la lista, se elimina de la base.
    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarritoProducto> productos = new ArrayList<>();
}
