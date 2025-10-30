package TP2;

@SuppressWarnings("unchecked")
public class LinearProbingHashTable<K, V> extends AbstractHashTable<K, V> {
    private Entry<K, V>[] tabla;

    // constructor
    public LinearProbingHashTable(int capacity) {
        super(capacity);
        tabla = (Entry<K, V>[]) new Entry[capacity];
    }

    // Método sondeo lineal
    private int probe(int hash, int i) {
        return (hash + i) % capacity;
    }

    @Override
    public void put(K key, V value) {

        if (key == null) throw new IllegalArgumentException("la key no puede ser null");
        ensureCapacity();
        int hash = hash(key);

        for (int i = 0; i < capacity; i++) {
            int index = probe(hash, i);
            Entry <K, V> entry = tabla[index];

            if (entry == null) {
                tabla[index] = new Entry<>(key, value);
                size ++;
                return;
            }
            else if (entry.isDeleted()) {
             tabla[index] = new Entry<>(key, value);
             size++;
             return;
            }
            else if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
            // sino sigue buscando
        }
        // si llega acá, la tabla está llena
        rehash(capacity * 2); // entonces duplicamos su capacidad
        put(key, value); // volvemos a ejecutar el mismo método (recursivo)
    }

    @Override
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("la key no puede ser null");
        int hash = hash(key);

        for (int i = 0; i < capacity; i++) {
            int index = probe(hash, i);
            Entry <K, V> entry = tabla[index];

            /* System.out.printf(
                    "Probando indice %d (hash=%d, i=%d)%n",
                    index, hash, i
            ); */ // PRINT DE DEPURACION PARA MIS PRUEBAS

            if (entry == null) {
                return null;
            }

            else if (!entry.isDeleted() && entry.getKey().equals(key)) {
                return entry.getValue();
            }
            // Si está borrada o distinta, sigue
        }
        return null; // no encontrada
    }

    @Override
    public V remove(K key) {
        if (key == null) throw new IllegalArgumentException("la key no puede ser null");
        int hash = hash(key);

        for (int i = 0 ; i < capacity ; i++) {
            int index = probe(hash, i);
            Entry <K, V> entry = tabla[index];

            if (entry == null) {
                return null;
            }
            else if(!entry.isDeleted() && entry.getKey().equals(key)) {
                V oldValue = entry.getValue();
                entry.setDeleted(true);
                size--;
                return oldValue;
            }
        }
        return null; // no encontrada
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    protected void rehash(int newCapacity) {
        // Guardar referencia a la tabla actual
        Entry<K, V>[] tablaVieja = tabla;

        // crear nueva tabla con el tamaño indicado
        tabla = (Entry<K, V>[]) new Entry[newCapacity];

        // actualizar capacidad y reiniciar tamaño
        capacity = newCapacity;
        size = 0;

        // recorrer la tabla anterior e insertar las entradas validas
        for (Entry<K, V> entry : tablaVieja) {
            if (entry != null && !entry.isDeleted()) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }
}