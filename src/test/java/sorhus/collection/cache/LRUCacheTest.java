package sorhus.collection.cache;

import org.junit.Test;

import java.util.Map;

import static junit.framework.Assert.assertEquals;

/**
 * @author: anton.sorhus@gmail.com
 */
public class LRUCacheTest {

    @Test
    public void test() {
        Map<String, String> instance = new LFUCache<>(2);
        instance.put("k1", "v1");
        instance.put("k2", "v2");
        instance.get("k2");
        instance.get("k1");
        instance.get("k2");
        instance.put("k3", "v3");

        assertEquals(instance.size(), 2);
        assertEquals(instance.get("k1"), null);
        assertEquals(instance.get("k2"), "v2");
        assertEquals(instance.get("k3"), "v3");
    }

}
