package TP2;

public abstract class AbstractHashTable<K, V> implements HashTable<K, V> {
    protected int capacity;
    protected int size;
    protected double loadFactorThreshold = 0.75;

    public AbstractHashTable(int initialCapacity) {
        this.capacity = initialCapacity;
        this.size = 0;
    }

    public AbstractHashTable(int initialCapacity, double loadFactorThreshold) {
        this(initialCapacity);
        this.loadFactorThreshold = loadFactorThreshold;
    }

    // Métodos abstractos que implementan las subclases
    public abstract void put(K key, V value);
    public abstract V get(K key);
    public abstract V remove(K key);
    public abstract boolean containsKey(K key);
    protected abstract void rehash(int newCapacity);

    // Métodos comunes de la interfaz
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
        // Las subclases tienen que limpiar su estructura interna
    }

    // Métodos comunes
    protected int hash(K key) {
        int hashCode = key.hashCode();
        return HashFunctions.hMod(hashCode, capacity);
    }

    protected void ensureCapacity() {
        if ((double) size / capacity > loadFactorThreshold) {
            rehash(capacity * 2);
        }
    }

    @Override
    public String toString() {
        return String.format(
                "HashTable{capacity=%d, size=%d, loadFactorThreshold=%f}",
                capacity, size, ((double) size / capacity)
        );
    }
}
