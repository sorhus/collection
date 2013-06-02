package sorhus.collection;

import java.util.ArrayList;

/**
 * @author Anton Sorhus <anton.sorhus@gmail.com>
 */
public class HashMap<K,V> implements Map<K,V> {

    private ArrayList<List<Entry<K,V>>> elements;
    private int size;
    private int capacity;
    private final int initCapacity;
    private final double loadFactor;

    public HashMap() {
        initCapacity = 16;
        capacity = initCapacity;
        loadFactor = 0.75;
        init();
    }

    public HashMap(int capacity, double loadFactor) {
        this.initCapacity = capacity;
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        init();
    }

    @Override
    public V get(K key) {
        Iterator<Entry<K,V>> it = elements.get(hash(key)).iterator();
        while(it.hasNext()) {
            Entry<K,V> entry = it.next();
            if(entry.getKey().equals(key))
                return entry.getValue();
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<K>(size);
        Iterator<K> it = iterator();
        while(it.hasNext())
            keys.add(it.next());
        return keys;
    }

    @Override
    public Map<K, V> put(K key, V value) {
        if(key == null || value == null)
            throw new IllegalArgumentException();
        if(containsKey(key))
            remove(key);
        elements.get(hash(key)).add(new Entry<K, V>(key, value));
        if(++size > capacity*loadFactor)
            increaseCapacity();
        return this;
    }

    @Override
    public Map<K, V> remove(K key) {
        if(containsKey(key) && elements.get(hash(key)).remove(new Entry<K, V>(key, get(key))))
            size--;
        return this;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        capacity = initCapacity;
        init();
    }

    protected void init() {
        elements = new ArrayList<List<Entry<K, V>>>(capacity);
        for(int i = 0; i < capacity; i++)
            elements.add(i, new LinkedList<Entry<K, V>>());
        size = 0;
    }

    protected int hash(K key) {
        int hash = key.hashCode() % capacity;
        return hash < 0 ? -hash : hash;
    }

    protected void increaseCapacity() {
        capacity *= 2;
        ArrayList<List<Entry<K, V>>> oldElements = elements;
        init();
        for(List<Entry<K, V>> entries : oldElements) {
            Iterator<Entry<K, V>> it = entries.iterator();
            while(it.hasNext()) {
                Entry<K, V> entry = it.next();
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(getClass() != o.getClass())
            return false;
        HashMap that = (HashMap) o;
        if(size() != that.size())
            return false;
        Iterator<K> it = iterator();
        while(it.hasNext()) {
            K key = it.next();
            if(!that.containsKey(key) || !get(key).equals(that.get(key)))
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(size() + ":{");
        Iterator<K> it = iterator();
        while(it.hasNext()) {
            K key = it.next();
            sb.append("(").append(key.toString()).append(",").append(get(key).toString()).append("),");
        }
        return sb.substring(0,sb.length()-1) + "}";
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {

            private java.util.Iterator<List<Entry<K, V>>> outerIterator;
            private Iterator<Entry<K, V>> innerIterator;
            private K next;

            @Override
            public boolean hasNext() {
                next = getNext();
                return next != null;
            }

            @Override
            public K next() {
                return next;
            }

            protected K getNext() {
                if(!init() || (!innerIterator.hasNext() && !outerIterator.hasNext()))
                    return null;
                while (!innerIterator.hasNext() && outerIterator.hasNext())
                    innerIterator = outerIterator.next().iterator();
                return innerIterator.hasNext() ? innerIterator.next().getKey() : null;
            }

            /**
             * @return true if iterator already initialised or initialisation went fine.
             */
            protected boolean init() {
                if(outerIterator != null)
                    return true;
                outerIterator = elements.iterator();
                if(!outerIterator.hasNext())
                    return false;
                innerIterator = outerIterator.next().iterator();
                return true;
            }
        };
    }

    class Entry<K,V> {

        private final K key;
        private final V value;

        protected Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        protected K getKey() {
            return key;
        }

        protected V getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (getClass() != o.getClass())
                return false;
            @SuppressWarnings("unchecked")
            Entry that = (Entry) o;
            return key.equals(that.key) && value.equals(that.value);
        }

        @Override
        public int hashCode() {
            return key.hashCode() * value.hashCode();
        }
    }
}