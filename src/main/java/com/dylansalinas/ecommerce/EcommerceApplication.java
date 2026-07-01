package com.dylansalinas.ecommerce;

import com.dylansalinas.ecommerce.model.Categoria;
import com.dylansalinas.ecommerce.model.Producto;
import com.dylansalinas.ecommerce.service.CategoriaService;
import com.dylansalinas.ecommerce.service.ProductoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Bean
    CommandLineRunner cargarDatos(CategoriaService categoriaService,
                                  ProductoService productoService) {
        return args -> {
            if (categoriaService.listarTodas().isEmpty()) {

                Categoria ropa = categoriaService.guardar(
                    new Categoria(null, "Ropa masculina", "Indumentaria para hombre"));
                Categoria ropaFem = categoriaService.guardar(
                    new Categoria(null, "Ropa femenina", "Indumentaria para mujer"));
                Categoria joyeria = categoriaService.guardar(
                    new Categoria(null, "Joyeria", "Anillos, collares y accesorios"));
                Categoria electronica = categoriaService.guardar(
                    new Categoria(null, "Electronica", "Dispositivos y accesorios tecnologicos"));

                productoService.guardar(crearProducto(
                    "Mochila plegable para notebook",
                    "Mochila resistente al agua con compartimento para notebook de hasta 15 pulgadas.",
                    109.95, 20, "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_t.png", ropa));
                productoService.guardar(crearProducto(
                    "Camiseta slim fit manga larga",
                    "Camiseta de algodon con corte ajustado y mangas largas.",
                    22.30, 50, "https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_t.png", ropa));
                productoService.guardar(crearProducto(
                    "Campera de algodon",
                    "Campera liviana de algodon para uso diario.",
                    55.99, 30, "https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_t.png", ropa));
                productoService.guardar(crearProducto(
                    "Pantalon casual slim fit",
                    "Pantalon de corte slim ideal para looks casuales.",
                    15.99, 40, "https://fakestoreapi.com/img/71YXzeOuslL._AC_UY879_t.png", ropa));

                productoService.guardar(crearProducto(
                    "Pulsera dragon oro y plata",
                    "Pulsera artesanal con detalles en oro y plata.",
                    695.00, 5, "https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_t.png", joyeria));
                productoService.guardar(crearProducto(
                    "Anillo de oro macizo micropave",
                    "Anillo de oro con incrustaciones micropave.",
                    168.00, 8, "https://fakestoreapi.com/img/61sbMiUnoGL._AC_UL640_QL65_ML3_t.png", joyeria));
                productoService.guardar(crearProducto(
                    "Anillo de compromiso banado en oro blanco",
                    "Anillo elegante con acabado en oro blanco.",
                    9.99, 15, "https://fakestoreapi.com/img/71YAIFU48IL._AC_UL640_QL65_ML3_t.png", joyeria));
                productoService.guardar(crearProducto(
                    "Aros dobles de acero inoxidable rosado",
                    "Aros hipoalergenicos de acero inoxidable color rosado.",
                    10.99, 12, "https://fakestoreapi.com/img/51UDEzMJVpL._AC_UL640_QL65_ML3_t.png", joyeria));

                productoService.guardar(crearProducto(
                    "Disco externo portatil 2TB USB 3.0",
                    "Disco rigido externo de 2TB con conexion USB 3.0.",
                    64.00, 25, "https://fakestoreapi.com/img/61IBBVJvSDL._AC_SY879_t.png", electronica));
                productoService.guardar(crearProducto(
                    "SSD interno 1TB SATA III",
                    "Unidad de estado solido interna de 1TB SATA III.",
                    109.00, 18, "https://fakestoreapi.com/img/61U7T1koQqL._AC_SX679_t.png", electronica));
                productoService.guardar(crearProducto(
                    "SSD 256GB 3D NAND SATA III",
                    "SSD de 256GB con memoria 3D NAND para mejor rendimiento.",
                    109.00, 22, "https://fakestoreapi.com/img/71kWymZ+c+L._AC_SX679_t.png", electronica));
                productoService.guardar(crearProducto(
                    "Disco externo 4TB para PlayStation 4",
                    "Disco externo compatible con consolas PlayStation 4.",
                    114.00, 10, "https://fakestoreapi.com/img/61mtL65D4cL._AC_SX679_t.png", electronica));
                productoService.guardar(crearProducto(
                    "Monitor Full HD 21.5 IPS",
                    "Monitor IPS de 21.5 pulgadas con resolucion Full HD.",
                    599.00, 7, "https://fakestoreapi.com/img/81QpkIctqPL._AC_SX679_t.png", electronica));
                productoService.guardar(crearProducto(
                    "Monitor curvo gaming 49 144Hz QLED",
                    "Monitor curvo ultrawide de 49 pulgadas para gaming.",
                    999.99, 4, "https://fakestoreapi.com/img/81Zt42ioCgL._AC_SX679_t.png", electronica));

                productoService.guardar(crearProducto(
                    "Campera 3 en 1 para nieve",
                    "Campera impermeable 3 en 1 ideal para clima frio.",
                    56.99, 20, "https://fakestoreapi.com/img/51Y5NI-I5jL._AC_UX679_t.png", ropaFem));
                productoService.guardar(crearProducto(
                    "Campera moto cuero sintetico con capucha",
                    "Campera estilo motociclista en cuero sintetico con capucha.",
                    29.95, 35, "https://fakestoreapi.com/img/81XH0e8fefL._AC_UY879_t.png", ropaFem));
                productoService.guardar(crearProducto(
                    "Campera impermeable con capucha",
                    "Campera resistente al agua con capucha ajustable.",
                    39.99, 28, "https://fakestoreapi.com/img/71HblAHs5xL._AC_UY879_-2t.png", ropaFem));
                productoService.guardar(crearProducto(
                    "Remera manga corta escote bote",
                    "Remera de manga corta con escote bote.",
                    9.85, 45, "https://fakestoreapi.com/img/71z3kpMAYsL._AC_UY879_t.png", ropaFem));
                productoService.guardar(crearProducto(
                    "Remera deportiva manga corta",
                    "Remera deportiva transpirable de secado rapido.",
                    7.95, 50, "https://fakestoreapi.com/img/51eg55uWmdL._AC_UX679_t.png", ropaFem));
                productoService.guardar(crearProducto(
                    "Remera casual de algodon manga corta",
                    "Remera basica de algodon para uso diario.",
                    12.99, 38, "https://fakestoreapi.com/img/61pHAEJ4NML._AC_UX679_t.png", ropaFem));
            }
        };
    }

    private static Producto crearProducto(String nombre, String descripcion, double precio,
                                          int stock, String imagenUrl, Categoria categoria) {
        Producto p = new Producto();
        p.setNombre(nombre);
        p.setDescripcion(descripcion);
        p.setPrecio(precio);
        p.setStock(stock);
        p.setImagenUrl(imagenUrl);
        p.setCategoria(categoria);
        return p;
    }
}
