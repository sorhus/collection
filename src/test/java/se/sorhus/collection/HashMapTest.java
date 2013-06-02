package se.sorhus.collection;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * @author Anton Sorhus <anton.sorhus@gmail.com>
 */
public class HashMapTest {

    @Test
    public void testPutGet() {
        HashMap<String, String> instance = new HashMap<String, String>();
        instance.put("key","value");
        assertThat(instance.size(), is(1));
        assertThat(instance.get("key"), is("value"));
    }

    @Test
    public void testPutContainsKey() {
        HashMap<String, String> instance = new HashMap<String, String>();
        assertThat(instance.containsKey("key"), is(false));
        instance.put("key","value");
        assertThat(instance.containsKey("key"), is(true));
    }

    @Test
    public void testPutKeySet() {
        HashMap<String, String> instance = new HashMap<String, String>();
        instance.put("key1","value").put("key2", "value");
        Set<String> expected = new HashSet<String>();
        expected.add("key1").add("key2");
        assertThat(instance.keySet(), is(expected));
    }

    @Test
    public void testPutRemove() {
        HashMap<String, String> instance = new HashMap<String, String>();
        instance.put("key","value").remove("key");
        assertThat(instance.size(), is(0));
        assertNull(instance.get("key"));
    }

    @Test
    public void testInsertSameKey() {
        HashMap<String, String> instance = new HashMap<String, String>();
        instance.put("key","value1");
        assertThat(instance.size(), is(1));
        assertThat(instance.get("key"), is("value1"));
        instance.put("key", "value2");
        assertThat(instance.size(), is(1));
        assertThat(instance.get("key"), is("value2"));
    }

    @Test
    public void testIncreaseCapacity() {
        HashMap<String, String> instance = new HashMap<String, String>(2, 0.75);
        instance.put("key1","value1");
        assertThat(instance.size(), is(1));
        assertThat(instance.get("key1"), is("value1"));
        instance.put("key2", "value2");
        assertThat(instance.size(), is(2));
        assertThat(instance.get("key1"), is("value1"));
        assertThat(instance.get("key2"), is("value2"));
        instance.put("key3", "value3");
        assertThat(instance.size(), is(3));
        assertThat(instance.get("key1"), is("value1"));
        assertThat(instance.get("key2"), is("value2"));
        assertThat(instance.get("key3"), is("value3"));
    }

    @Test
    public void testClear() {
        HashMap<String, String> instance = new HashMap<String, String>(2, 0.75);
        instance.put("key","value");
        assertThat(instance.size(), is(1));
        assertThat(instance.get("key"), is("value"));
        instance.clear();
        assertThat(instance.size(), is(0));
        assertNull(instance.get("key"));
    }

    @Test
    public void testEquals() {
        HashMap<String, String> instance1 = new HashMap<String, String>();
        instance1.put("key1","value").put("key2", "value");
        HashMap<String, String> instance2 = new HashMap<String, String>();
        instance2.put("key1","value").put("key2", "value");
        assertThat(instance1.equals(instance2), is(true));
    }

    @Test
    public void testIterator() {
        HashMap<String, String> instance = new HashMap<String, String>();
        instance.put("key1","").put("key2","").put("key3","").put("key4", "");
        Set<String> expected = new HashSet<String>();
        expected.add("key1").add("key2").add("key3").add("key4");
        Iterator<String> iterator = instance.iterator();
        Set<String> result = new HashSet<String>();
        while (iterator.hasNext())
            result.add(iterator.next());
        assertThat(result, is(expected));
    }


   @Test
    public void testToString() {
       HashMap<String, String> instance = new HashMap<String, String>();
       instance.put("key1","v").put("key2","v").put("key3","v");
       System.out.println(instance.toString());
   }
}
