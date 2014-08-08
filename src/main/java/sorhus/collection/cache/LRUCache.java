package sorhus.collection.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
* @author: anton.sorhus@gmail.com
*/
class LRUCache<K,V> extends LinkedHashMap<K,V> implements Cache<K,V> {

    private int size;

    protected LRUCache(int size) {
        super(16, 0.75f, true);
        this.size = size;
    }

    @Override
    public boolean contains(K key) {
        return super.containsKey(key);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > this.size;
    }

    @Override
    public void invalidate(K key) {
        super.remove(key);
    }
}
