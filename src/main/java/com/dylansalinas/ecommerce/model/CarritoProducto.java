package com.dylansalinas.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carrito_productos")
public class CarritoProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // RelaciÃ³n con Carrito â€” muchos CarritoProducto pertenecen a un Carrito
    @ManyToOne
    @JoinColumn(name = "carrito_id")
    @JsonIgnore
    private Carrito carrito;

    // RelaciÃ³n con Producto â€” muchos CarritoProducto apuntan a un Producto
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    // Atributo propio de la relaciÃ³n.
    // Esto es lo que no podÃ­amos tener con @ManyToMany directo.
    @Column(nullable = false)
    private Integer cantidad;
}
