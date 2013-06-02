package se.sorhus.collection;

/**
 * @author Anton Sorhus <anton.sorhus@gmail.com>
 */
public interface Map<K,V> extends Iterable<K> {

    V get(K key);
    boolean containsKey(K key);
    Set<K> keySet();
    Map<K,V> put(K key, V value);
    Map<K,V> remove(K key);
    int size();
    void clear();
}
