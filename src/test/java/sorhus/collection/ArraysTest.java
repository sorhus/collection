package sorhus.collection;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author: anton.sorhus@gmail.com
 */
public class ArraysTest {

    @Test
    public void testSortInt() {
        int[] array = new int[] { 4, 3, 2, 1};
        Arrays.mergeSort(array);
        assertThat(array[0], is(1));
        assertThat(array[1], is(2));
        assertThat(array[2], is(3));
        assertThat(array[3], is(4));
        Arrays.mergeSort(getRandomArray(1000));
        int tmp = array[0];
        for (int i : array) {
            assertThat(tmp <= i, is(true));
            tmp = i;
        }
    }

    @Test
    public void testPerformance() {
        int n = 1000000;
        for (int i = 0; i < 5; i++) {

            int[] my = getRandomArray(n);
            long time = System.nanoTime();
            Arrays.mergeSort(my);
            System.out.println("my time: " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - time));;

            int[] other = getRandomArray(n);
            time = System.nanoTime();
            java.util.Arrays.sort(other);
            System.out.println("java time: " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - time));;
        }
    }

    @Test
    public void testSearch() {
        int[] array = new int[] { 0, 1, 2, 3, 4};
        assertThat(Arrays.search(array, 0), is(0));
        assertThat(Arrays.search(array, 1), is(1));
        assertThat(Arrays.search(array, 2), is(2));
        assertThat(Arrays.search(array, 3), is(3));
        assertThat(Arrays.search(array, 4), is(4));
        assertThat(Arrays.search(array, 5), is(-1));
        array = new int[] { 0, 1, 2, 3, 4, 5};
        assertThat(Arrays.search(array, 0), is(0));
        assertThat(Arrays.search(array, 1), is(1));
        assertThat(Arrays.search(array, 2), is(2));
        assertThat(Arrays.search(array, 3), is(3));
        assertThat(Arrays.search(array, 4), is(4));
        assertThat(Arrays.search(array, 5), is(5));
        assertThat(Arrays.search(array, 6), is(-1));
        array = new int[] { 0, 0, 2, 3, 4, 5};
        assertThat(Arrays.search(array, 0), is(0));
        assertThat(Arrays.search(array, 2), is(2));
        assertThat(Arrays.search(array, 3), is(3));
        assertThat(Arrays.search(array, 4), is(4));
        assertThat(Arrays.search(array, 5), is(5));
        assertThat(Arrays.search(array, 1), is(-1));

    }


    private int[] getRandomArray(int n) {
        Random rnd = new Random();
        int[] array = new int[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = rnd.nextInt();
        }
        return array;
    }


}