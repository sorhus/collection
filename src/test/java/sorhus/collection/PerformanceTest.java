package sorhus.collection;

import org.junit.Ignore;
import org.junit.Test;

import java.util.UUID;

/**
 * @author Anton Sorhus <anton.sorhus@gmail.com>
 */
@Ignore
public class PerformanceTest {


    @Test
    public void testPerformance() {
        int elements = 1000000;
        long time = System.currentTimeMillis();
        UUID[] uuids = new UUID[elements];
        for(int i = 0; i < elements; i++)
            uuids[i] = UUID.randomUUID();
        System.out.println("created " + elements + " elements in " + (System.currentTimeMillis() - time) + "ms");

        System.out.println("/////");
        System.out.println("Testing with unknown size");

        // Maps

        time = System.currentTimeMillis();
        java.util.Map<UUID, UUID> utilMap = new java.util.HashMap<UUID, UUID>();
        for(UUID uuid : uuids)
            utilMap.put(uuid, uuid);
        System.out.println("added elements to utilMap in " + (System.currentTimeMillis()-time) + "ms");

        time = System.currentTimeMillis();
        sorhus.collection.Map<UUID, UUID> myMap = new sorhus.collection.HashMap<UUID, UUID>();
        for(UUID uuid : uuids)
            myMap.put(uuid, uuid);
        System.out.println("added elements to myMap in " + (System.currentTimeMillis()-time) + "ms");

        // Sets

        time = System.currentTimeMillis();
        java.util.Set<UUID> utilSet = new java.util.HashSet<UUID>();
        for(UUID uuid : uuids)
            utilSet.add(uuid);
        System.out.println("added elements to utilSet in " + (System.currentTimeMillis()-time) + "ms");

        time = System.currentTimeMillis();
        sorhus.collection.Set<UUID> mySet = new sorhus.collection.HashSet<UUID>();
        for(UUID uuid : uuids)
            mySet.add(uuid);
        System.out.println("added elements to mySet in " + (System.currentTimeMillis()-time) + "ms");

        // Lists

        time = System.currentTimeMillis();
        java.util.LinkedList<UUID> utilList = new java.util.LinkedList<UUID>();
        for(UUID uuid : uuids)
            utilList.addFirst(uuid);
        System.out.println("added elements to utilList in " + (System.currentTimeMillis()-time) + "ms");

        time = System.currentTimeMillis();
        sorhus.collection.List<UUID> myList = new sorhus.collection.LinkedList<UUID>();
        for(UUID uuid : uuids)
            myList.add(uuid);
        System.out.println("added elements to myList in " + (System.currentTimeMillis()-time) + "ms");


        System.out.println("/////");
        System.out.println("Testing with known size");

        // Maps

        time = System.currentTimeMillis();
        utilMap = new java.util.HashMap<UUID, UUID>(elements, 1.0f);
        for(UUID uuid : uuids)
            utilMap.put(uuid, uuid);
        System.out.println("added elements to utilMap in " + (System.currentTimeMillis()-time) + "ms");

        time = System.currentTimeMillis();
        myMap = new sorhus.collection.HashMap<UUID, UUID>(elements, 1.0);
        for(UUID uuid : uuids)
            myMap.put(uuid, uuid);
        System.out.println("added elements to myMap in " + (System.currentTimeMillis()-time) + "ms");

        // Sets

        time = System.currentTimeMillis();
        utilSet = new java.util.HashSet<UUID>(elements);
        for(UUID uuid : uuids)
            utilSet.add(uuid);
        System.out.println("added elements to utilSet in " + (System.currentTimeMillis()-time) + "ms");

        time = System.currentTimeMillis();
        mySet = new sorhus.collection.HashSet<UUID>(elements);
        for(UUID uuid : uuids)
            mySet.add(uuid);
        System.out.println("added elements to mySet in " + (System.currentTimeMillis()-time) + "ms");

    }

}
