package sorhus.collection.cache;

/**
 * @author: anton.sorhus@gmail.com
 */
public class CacheBuilder<K,V> {

    int size = 16;
    Strategy strategy = Strategy.LRU;

    public CacheBuilder setSize(int size) {
        this.size = size;
        return this;
    }

    public CacheBuilder setStrategy(Strategy strategy) {
        this.strategy = strategy;
        return this;
    }

    public Cache build() {
        switch (strategy) {
            case LRU:
                return new LRUCache<K,V>(size);
            case LFU:
                return new LFUCache<K,V>(size);
            default:
                throw new RuntimeException("Strategy not implemented yet!");
        }
    }

}
