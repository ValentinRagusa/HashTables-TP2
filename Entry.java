package TP2;

/**
 * Clase Entry
 * Representa un par clave-valor dentro de la tabla hash
 * También incluye un flag de borrado lógico para las tablas
 * con direccionamiento abierto (lineal o cuadrático)
 */

public class Entry<K, V> {
    protected K key;
    protected V value;
    protected boolean deleted;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
        this.deleted = false;
    }

    // Métodos de acceso
    // getters
    public K getKey() {
        return key;
    }
    public V getValue() {
        return value;
    }

    public boolean isDeleted() {
        return deleted;
    }

    // setters
    public void setValue(V value) {
        this.value = value;
    }
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return String.format(
                "{key=%s, value=%s, deleted=%b}",
                key, value, deleted
        );
    }
}
