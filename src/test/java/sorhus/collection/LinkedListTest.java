package sorhus.collection;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author Anton Sorhus <anton.sorhus@gmail.com>
 */
public class LinkedListTest {

    @Test
    public void testAddSize() {
        LinkedList<String> instance = new LinkedList<>();
        instance.add("1").add("2").add("3");
        assertThat(instance.size(), is(3));
    }

    @Test
    public void testEquals() {
        LinkedList<String> instance1 = new LinkedList<>();
        instance1.add("1").add("2");
        LinkedList<String> instance2 = new LinkedList<>();
        instance2.add("1").add("2");
        assertThat(instance1, is(instance2));
        instance2.add("3");
        assertFalse(instance1.equals(instance2));
    }

    @Test
    public void testAddRemove() {
        LinkedList<String> instance1 = new LinkedList<>();
        instance1.add("1").add("2").add("3").add("4");
        assertTrue(instance1.remove("3"));
        assertFalse(instance1.remove("3"));
        LinkedList<String> instance2 = new LinkedList<>();
        instance2.add("1").add("2").add("4");
        assertThat(instance1, is(instance2));
    }

    @Test
    public void testClear() {
        LinkedList<String> instance = new LinkedList<>();
        instance.add("1");
        assertThat(instance.size(), is(1));
        instance.clear();
        assertThat(instance.size(), is(0));
    }

    @Test
    public void testReverse() {
        LinkedList<String> instance = new LinkedList<>();
        instance.add("1").add("2").add("3");
        List<String> expected = new LinkedList<>();
        expected.add("3").add("2").add("1");
        List<String> result = instance.reverse();
        assertThat(result, is(expected));
    }

}
