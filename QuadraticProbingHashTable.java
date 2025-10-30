package TP2;

@SuppressWarnings("unchecked")
public class QuadraticProbingHashTable<K, V> extends AbstractHashTable<K, V> {
    private Entry<K, V>[] tabla;

    // definir constructor de la clase
    public QuadraticProbingHashTable(int capacity) {
        super(capacity);
        tabla = (Entry<K, V>[]) new Entry[capacity];
    }

    // método sondeo cuadratico
    private int probe(int hash, int i) { return (hash + i * i) % capacity; }

    @Override
    public void put(K key, V value) {
        if (key == null)
            throw new IllegalArgumentException("la clave no puede ser null");

        ensureCapacity();
        int hash = hash(key);

        // para evitar duplicados si la clave existe más adelante
        int firstDeletedIndex = -1;

        for (int i = 0; i < capacity; i++) {
            int index = probe(hash, i);
            Entry<K, V> e = tabla[index];

            if (e == null) {
                // si vimos una celda borrada antes, reutilizarla
                int target = (firstDeletedIndex != -1) ? firstDeletedIndex : index;
                tabla[target] = new Entry<>(key, value);
                size++;
                return;
            } else if (e.isDeleted()) {
                // recordar la primera celda borrada y seguir buscando por si la clave ya existe
                if (firstDeletedIndex == -1) firstDeletedIndex = index;
            } else if (e.getKey().equals(key)) {
                // clave ya existe -> actualizar valor
                e.setValue(value);
                return;
            }
            // sigue buscando
        }

        // si llega aca la tabla está llena (muy raro con ensureCapacity, pero defensivo)
        rehash(capacity * 2); // duplicamos capacidad
        put(key, value);
    }

    @Override
    public V get(K key) {
        if (key == null)
            throw new IllegalArgumentException("la clave no puede ser null");

        int hash = hash(key);

        for (int i = 0; i < capacity; i++) {
            int index = probe(hash, i);
            Entry<K, V> e = tabla[index];

            if (e == null) {
                return null;
            } else if (!e.isDeleted() && e.getKey().equals(key)) {
                return e.getValue();
            }
            // si está borrada o es diferente sigue
        }
        return null; // no se encontró
    }

    @Override
    public V remove(K key) {
        if (key == null)
            throw new IllegalArgumentException("la clave no puede ser null");

        int hash = hash(key);

        for (int i = 0; i < capacity; i++) {
            int index = probe(hash, i);
            Entry<K, V> e = tabla[index];

            if (e == null) {
                return null;
            } else if (!e.isDeleted() && e.getKey().equals(key)) {
                V oldValue = e.getValue();
                e.setDeleted(true);
                size--;
                return oldValue;
            }
        }
        return null; // no encontrado
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null)
            throw new IllegalArgumentException("la clave no puede ser null");
        return get(key) != null;
    }

    @Override
    protected void rehash(int newCapacity) {
        // guardar referencia de la tabla actual
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
