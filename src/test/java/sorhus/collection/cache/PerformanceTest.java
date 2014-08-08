package sorhus.collection.cache;

import org.junit.Test;

import java.util.UUID;

/**
 * @author: anton.sorhus@gmail.com
 */
public class PerformanceTest {

    @Test
    public void testLFUCache() {

        int c = 10;
        Cache[] caches = new Cache[] {
            new LFUCache<String, String>(c),
            new LRUCache<String, String>(c)
        };

        for(Cache cache : caches) {

            TestDao instance = new TestDao(cache);

            int s = 20;
            int n = 100;
            String[] strings = new String[n];
            for (int i = 0; i < s; i++) {
                strings[i] = UUID.randomUUID().toString();
            }
            long time = System.currentTimeMillis();
            for (int i = 0; i < n; i++) {
                instance.get(strings[n % s]);
            }
            System.out.println(String.format("Took %d ms", System.currentTimeMillis() - time));
        }
    }


    private class TestDao {

        Cache<String, String> cache;

        TestDao(Cache<String,String> cache) {
            this.cache = cache;
        }

        String get(String key) {
            if(cache.contains(key)) {
                return cache.get(key);
            } else {
                String value = expensiveGet(key);
                cache.put(key, value);
                return value;
            }
        }

        String expensiveGet(String key) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
            return key + ":" + key;
        }
    }

}
