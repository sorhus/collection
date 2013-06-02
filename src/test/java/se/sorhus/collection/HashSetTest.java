package se.sorhus.collection;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Anton Sorhus <anton.sorhus@gmail.com>
 */
public class HashSetTest {

    @Test
    public void testEquals() {
        HashSet<String> instance1 = new HashSet<String>();
        instance1.add("1").add("2");
        HashSet<String> instance2 = new HashSet<String>();
        instance2.add("1").add("2");
        assertThat(instance1.equals(instance2), is(true));
    }

    @Test
    public void testSize() {
        HashSet<String> instance = new HashSet<String>();
        instance.add("1").add("2");
        assertThat(instance.size(), is(2));
    }

    @Test
    public void testToString() {
        HashSet<String> instance = new HashSet<String>();
        instance.add("1").add("2").add("3");
        System.out.println(instance.toString());
    }
}
