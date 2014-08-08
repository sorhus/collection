package sorhus.collection.cache;

/**
 * @author: anton.sorhus@gmail.com
 */
public interface Cache<K,V> {

    public boolean contains(K key);

    public V get(K key);

    public V put(K key, V value);

    public void invalidate(K key);

    public int size();
}
