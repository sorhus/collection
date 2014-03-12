package sorhus.collection;

import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertNull;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author: anton.sorhus@gmail.com
 */
public class PersistentMapTest {

    @Test
    public void testPutGet() throws Exception {
        String key1 = "foo", value1 = "foobar";
        String key2 = "bar", value2 = "barbar";

        PersistentMap<String, String> instance1 = new PersistentMap<>();
        assertNull(instance1.get(key1));
        assertNull(instance1.get(key2));

        PersistentMap<String, String> instance2 = instance1.put(key1, value1);
        assertNull(instance1.get(key1));
        assertNull(instance1.get(key2));
        assertThat(instance2.get(key1), is(value1));
        assertNull(instance2.get(key2));

        PersistentMap<String, String> instance3 = instance2.put(key2, value2);
        assertNull(instance1.get(key1));
        assertNull(instance1.get(key2));
        assertThat(instance2.get(key1), is(value1));
        assertNull(instance2.get(key2));
        assertThat(instance3.get(key1), is(value1));
        assertThat(instance3.get(key2), is(value2));
    }

    @Test
    public void testMask() throws Exception {
        Random rnd = new Random();
        for (int d = 0; d < 6; d++) {
            PersistentMap.Node instance = new PersistentMap.Node(d);
            for (int i = 0; i < 100; i++) {
                int hash = rnd.nextInt();
                int result = instance.mask(hash);
                assertTrue(result > -1 && result < 32);
            }
        }
    }

}
