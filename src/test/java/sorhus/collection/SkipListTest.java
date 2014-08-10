package sorhus.collection;

import org.junit.Test;

import java.util.Random;
import java.util.UUID;

/**
 * @author: anton.sorhus@gmail.com
 */
public class SkipListTest {

    @Test
    public void test() throws Exception {
        SkipList<Integer, String> instance = new SkipList<>(0.5f, 16);
        instance.add(3, "a");
        instance.add(7, "b");
        instance.add(5, "c");
        instance.add(4, "d");
        instance.add(3, "e");

        System.out.println(instance);
        System.out.println(instance.search(4));
    }

    @Test
    public void testLarge() throws Exception {
        final int n = 10;
        Random rnd = new Random();
        SkipList<Integer, String> instance = new SkipList<>(0.1f, n);
        for (int i = 0; i < n; i++) {
            instance.add(rnd.nextInt(n*n), UUID.randomUUID().toString());
        }

        System.out.println(instance);
        System.out.println(instance.search(4));
    }
}
