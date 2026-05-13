package com.dylansalinas.ecommerce;

import com.dylansalinas.ecommerce.exception.ProductoNoEncontradoException;
import com.dylansalinas.ecommerce.exception.StockInsuficienteException;
import com.dylansalinas.ecommerce.model.Producto;
import com.dylansalinas.ecommerce.service.ProductoService;
import com.dylansalinas.ecommerce.ui.MenuProducto;
import com.dylansalinas.ecommerce.util.Validador;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ProductoService service = new ProductoService();
        Scanner sc = new Scanner(System.in);
        MenuProducto menu = new MenuProducto(sc, service);

        cargarDatosDePrueba(service);

        int opcion;

        do {
            menu.mostrarMenu();
            opcion = Validador.leerEntero(sc, "Elija una opción: ");

            try {
                switch (opcion) {
                    case 1:
                        menu.agregarProducto();
                        break;
                    case 2:
                        menu.listarProductos();
                        break;
                    case 3:
                        menu.buscarProducto();
                        break;
                    case 4:
                        menu.actualizarProducto();
                        break;
                    case 5:
                        menu.eliminarProducto();
                        break;
                    case 6:
                        System.out.println("¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción inválida. Elija un número del 1 al 6.");
                        break;
                }
            } catch (ProductoNoEncontradoException | StockInsuficienteException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Dato inválido: " + e.getMessage());
            }

            System.out.println(); // separa visualmente cada operación del menú

        } while (opcion != 6);

        sc.close();
    }

    private static void cargarDatosDePrueba(ProductoService service) {
        service.guardar(new Producto("Café molido 500g", 4500, 30, "Bebidas"));
        service.guardar(new Producto("Yerba mate 1kg", 3200, 50, "Bebidas"));
        service.guardar(new Producto("Galletitas dulces", 1850, 100, "Almacén"));
        service.guardar(new Producto("Aceite de oliva 500ml", 6700, 20, "Almacén"));
        service.guardar(new Producto("Chocolate amargo 70%", 2900, 15, "Golosinas"));
        System.out.println("✔ Se cargaron 5 productos de prueba.\n");
    }
}
