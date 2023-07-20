package com.integracionContinua;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class integracion {

	public static void main(String[] args) {
        Map<String, Double> menu = new HashMap<>();
        menu.put("Pizza", 10.0);
        menu.put("Hamburguesa", 8.0);
        menu.put("Ensalada", 6.0);
        menu.put("Pastas", 12.0);

        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> pedido = new HashMap<>();

        System.out.println("Bienvenido a la Experiencia Gastronómica");
        System.out.println("Menú:");
        for (String comida : menu.keySet()) {
            System.out.println(comida + " - $" + menu.get(comida));
        }

        int cantidadTotal = 0;
        double costoTotal = 0.0;

        while (true) {
            System.out.println("Ingrese la comida que desea ordenar (o 'fin' para finalizar el pedido):");
            String comida = scanner.nextLine();

            if (comida.equals("fin")) {
                break;
            }

            if (!menu.containsKey(comida)) {
                System.out.println("La comida ingresada no está en el menú. Por favor, intente nuevamente.");
                continue;
            }

            System.out.println("Ingrese la cantidad de " + comida + " que desea ordenar:");
            int cantidad = Integer.parseInt(scanner.nextLine());

            if (cantidad <= 0) {
                System.out.println("La cantidad debe ser un número entero positivo mayor que cero. Por favor, intente nuevamente.");
                continue;
            }

            if (cantidadTotal + cantidad > 100) {
                System.out.println("El pedido supera la cantidad máxima permitida de 100 comidas. Por favor, reduzca la cantidad o finalice el pedido.");
                continue;
            }

            pedido.put(comida, pedido.getOrDefault(comida, 0) + cantidad);
            cantidadTotal += cantidad;
            costoTotal += menu.get(comida) * cantidad;
        }

        if (cantidadTotal == 0) {
            System.out.println("El pedido ha sido cancelado.");
            return;
        }

        if (cantidadTotal > 5) {
            costoTotal *= 0.9; // Aplicar descuento del 10% si se piden más de 5 comidas
        }

        if (cantidadTotal > 10) {
            costoTotal *= 0.8; // Aplicar descuento del 20% si se piden más de 10 comidas
        }

        System.out.println("Resumen del pedido:");
        for (String comida : pedido.keySet()) {
            int cantidad = pedido.get(comida);
            double subtotal = menu.get(comida) * cantidad;
            System.out.println(cantidad + " " + comida + " - $" + subtotal);
        }

        if (costoTotal > 50) {
            costoTotal -= 10; // Aplicar descuento de $10 si el costo total supera los $50
        }

        if (costoTotal > 100) {
            costoTotal -= 25; // Aplicar descuento de $25 si el costo total supera los $100
        }

        System.out.println("Costo total: $" + costoTotal);
    }

}
