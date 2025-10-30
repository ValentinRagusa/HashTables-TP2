package TP2;

public class Main {
    public static void main(String[] args) {

        // ===========================================================
        // PRUEBAS CON LINEAR PROBING

        System.out.println("=== PRUEBA: LINEAR PROBING ===");

        // Crear tabla hash con direccionamiento lineal
        LinearProbingHashTable<Integer, String> linearTable = new LinearProbingHashTable<>(5);

        // Inserciones básicas
        linearTable.put(1, "Uno");
        linearTable.put(2, "Dos");
        linearTable.put(3, "Tres");
        linearTable.put(11, "Once"); // Colisiona con 1 (1 % 5 == 1, 11 % 5 == 1)
        linearTable.put(6, "Seis");  // Aumenta la carga y fuerza un poco la dispersión

        // Mostrar estado actual de la tabla
        System.out.println("\n--- Estado inicial (LINEAR) ---");
        for (int i = 0; i < 5; i++) {
            System.out.printf("[%d] -> %s%n", i, linearTable.get(i));
        }

        // Pruebas de búsqueda
        System.out.println("\nBuscar clave 11 -> " + linearTable.get(11));
        System.out.println("Buscar clave 4 (no existe) -> " + linearTable.get(4));

        // Prueba de eliminación
        System.out.println("\nEliminar clave 2 -> " + linearTable.remove(2));
        System.out.println("Buscar clave 2 luego de eliminar -> " + linearTable.get(2));

        // Inserción posterior a eliminación (reusa espacio borrado)
        linearTable.put(7, "Siete");

        // Forzar rehash superando el factor de carga
        linearTable.put(12, "Doce");
        linearTable.put(13, "Trece");

        System.out.println("\n--- Estado final (LINEAR) ---");
        System.out.println(linearTable);


        // ===========================================================
        // PRUEBAS CON QUADRATIC PROBING


        System.out.println("\n\n=== PRUEBA: QUADRATIC PROBING ===");

        // Crear tabla hash con direccionamiento cuadrático
        QuadraticProbingHashTable<Integer, String> quadraticTable = new QuadraticProbingHashTable<>(5);

        // Inserciones básicas
        quadraticTable.put(1, "Uno");
        quadraticTable.put(2, "Dos");
        quadraticTable.put(3, "Tres");
        quadraticTable.put(11, "Once"); // Colisiona con 1
        quadraticTable.put(6, "Seis");

        // Mostrar estado actual de la tabla
        System.out.println("\n--- Estado inicial (QUADRATIC) ---");
        for (int i = 0; i < 5; i++) {
            System.out.printf("[%d] -> %s%n", i, quadraticTable.get(i));
        }

        // Pruebas de búsqueda
        System.out.println("\nBuscar clave 11 -> " + quadraticTable.get(11));
        System.out.println("Buscar clave 4 (no existe) -> " + quadraticTable.get(4));

        // Prueba de eliminación
        System.out.println("\nEliminar clave 2 -> " + quadraticTable.remove(2));
        System.out.println("Buscar clave 2 luego de eliminar -> " + quadraticTable.get(2));

        // Inserción posterior a eliminación (reusa espacio borrado)
        quadraticTable.put(7, "Siete");

        // Forzar rehash superando el factor de carga
        quadraticTable.put(12, "Doce");
        quadraticTable.put(13, "Trece");

        System.out.println("\n--- Estado final (QUADRATIC) ---");
        System.out.println(quadraticTable);

        // FIN DE PRUEBAS


        System.out.println("\nPRUEBAS COMPLETADAS EXITOSAMENTE ");
    }
}
