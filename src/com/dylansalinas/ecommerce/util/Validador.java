package com.dylansalinas.ecommerce.util;

import com.dylansalinas.ecommerce.exception.StockInsuficienteException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Validador {

    public static void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
    }

    public static void validarPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
    }

    public static void validarStock(int stock) {
        if (stock < 0) {
            throw new StockInsuficienteException("El stock no puede ser negativo.");
        }
    }

    public static void validarCategoria(String categoria) {
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new IllegalArgumentException("La categoría no puede estar vacía.");
        }
    }

    public static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = sc.nextInt();
                sc.nextLine(); // consume el salto de línea que deja nextInt()
                return valor;
            } catch (InputMismatchException e) {
                System.out.println(" Debe ingresar un número entero. Intente nuevamente.");
                sc.nextLine(); // descarta la línea con entrada no numérica
            }
        }
    }

    public static double leerDouble(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double valor = sc.nextDouble();
                sc.nextLine(); // consume el salto de línea que deja nextDouble()
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un número (puede usar coma o punto). Intente nuevamente.");
                sc.nextLine(); // descarta la línea con entrada no numérica
            }
        }
    }

    public static String leerTexto(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine();
    }
}
