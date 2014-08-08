package sorhus.collection.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: anton.sorhus@gmail.com
 */
public class LFUCache<K,V> extends HashMap<K,V> implements Cache<K,V> {

    private int size;
    private Map<K, LFUCounter> counters;

    protected LFUCache(int size) {
        super();
        this.size = size;
        this.counters = new HashMap<>();
    }

    @Override
    public boolean contains(K key) {
        return super.containsKey(key);
    }

    @Override
    public V put(K key, V value) {
        V result = super.put(key, value);
        if(size() > this.size) {
            remove();
        }
        counters.put(key, new LFUCounter(key));
        return result;
    }

    @Override
    public V get(Object key) {
        if(super.containsKey(key)) {
            counters.get(key).val++;
        }
        return super.get(key);
    }

    @Override
    public void invalidate(Object key) {
        super.remove(key);
        counters.remove(key);
    }

    private void remove() {
        ArrayList<LFUCounter> values = new ArrayList(counters.values());
        Collections.sort(values);
        Object LFUKey = values.get(0).key;
        invalidate(LFUKey);
    }

    private class LFUCounter implements Comparable<LFUCounter> {

        int val;
        Object key;

        LFUCounter(Object key) {
            this.key = key;
        }

        @Override
        public int compareTo(LFUCounter that) {
            return this.val - that.val;
        }
    }

}
