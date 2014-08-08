package sorhus.collection.cache;

import org.junit.Test;

import java.util.Map;

import static junit.framework.Assert.assertEquals;

/**
 * @author: anton.sorhus@gmail.com
 */
public class LFUCacheTest {

    @Test
    public void test() {
        Cache<String, String> instance = new LFUCache<>(2);
        instance.put("k1", "v1");
        instance.put("k2", "v2");
        instance.get("k1");
        instance.get("k2");
        instance.get("k2");
        instance.put("k3", "v3");

        assertEquals(2, instance.size());
        assertEquals(null, instance.get("k1"));
        assertEquals("v2", instance.get("k2"));
        assertEquals("v3", instance.get("k3"));

        instance.get("k3");
        instance.get("k3");
        instance.get("k3");
        instance.put("k1", "v1");

        assertEquals(2, instance.size());
        assertEquals("v1", instance.get("k1"));
        assertEquals(null, instance.get("k2"));
        assertEquals("v3", instance.get("k3"));
    }

}
