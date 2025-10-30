
package TP2;

/**
 * Interfaz HashTable
 * Definimos las operaciones básicas de una tabla hash
 * @param <K> tipo de clave
 * @param <V> tipo de valor (nuestro hash-value)
 */

public interface HashTable<K, V> {

    // Inserta o actualiza una clave con su valor asociado
    void put(K key, V value);

    // devuelve el valor asociado a la clave, o null si no existe
    V get(K key);

    // Elimina una entrada y devuelve su valor, o null si no se encuentra
    V remove(K key);

    // verifica si la tabla contiene la clave dad
    boolean containsKey(K key);

    // devuelve la cantidad de elementos almacenados
    int size();

    // verifica si la tabla está vacía
    boolean isEmpty();

    // Eliminas todos los elementos de la tabla.
    void clear();
}