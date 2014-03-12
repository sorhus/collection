package sorhus.collection;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertArrayEquals;

/**
 * @author: anton.sorhus@gmail.com
 */
public class HeapBinaryTreeTest {

    /*
    @Test
    public void testInOrder() throws Exception {

        Integer[] input, expected;
        HeapBinaryTree instance;
        Object[] result;


        input = new Integer[] {1,2,3,4,5,6,7,8};
        expected = new Integer[] {8,4,2,5,1,6,3,7};
        instance = new HeapBinaryTree(input);
        instance.printInOrder();
        result = instance.inOrder();
        assertArrayEquals("8",expected, result);


        input = new Integer[] {1,2,3,4,5,6,7,8,9};
        expected = new Integer[] {8,4,9,2,5,1,6,3,7};
        instance = new HeapBinaryTree(input);
        instance.printInOrder();
        result = instance.inOrder();
        assertArrayEquals("9",result, expected);


        input = new Integer[] {1,2,3,4,5,6,7,8,9,10};
        expected = new Integer[] {8,4,9,2,10,5,1,6,3,7};
        instance = new HeapBinaryTree(input);
        instance.printInOrder();
        result = instance.inOrder();
        assertArrayEquals("10",result, expected);


        input = new Integer[] {1,2,3,4,5,6,7,8,9,10,11};
        expected = new Integer[] {8,4,9,2,10,5,11,1,6,3,7};
        instance = new HeapBinaryTree(input);
        instance.printInOrder();
        result = instance.inOrder();
        assertArrayEquals("11", result, expected);


        input = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12};
        expected = new Integer[] {8,4,9,2,10,5,11,1,12,6,3,7};
        instance = new HeapBinaryTree(input);
        instance.printInOrder();
        result = instance.inOrder();
        assertArrayEquals("12",result, expected);


        input = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13};
        expected = new Integer[] {8,4,9,2,10,5,11,1,12,6,13,3,7};
        instance = new HeapBinaryTree(input);
        instance.printInOrder();
        result = instance.inOrder();
        assertArrayEquals("11",result, expected);


        input = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
        expected = new Integer[] {8,4,9,2,10,5,11,1,12,6,13,3,14,7};
        instance = new HeapBinaryTree(input);
        instance.printInOrder();
        result = instance.inOrder();
        assertArrayEquals("11",result, expected);


        input = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        expected = new Integer[] {8,4,9,2,10,5,11,1,12,6,13,3,14,7,15};
        instance = new HeapBinaryTree(input);
        instance.printInOrder();
        result = instance.inOrder();
        assertArrayEquals("11",result, expected);


        input = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
        expected = new Integer[] {16,8,4,9,2,10,5,11,1,12,6,13,3,14,7,15};
        instance = new HeapBinaryTree(input);
        instance.printInOrder();
        result = instance.inOrder();
        assertArrayEquals("11",result, expected);


        input = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17};
        expected = new Integer[] {16,8,17,4,9,2,10,5,11,1,12,6,13,3,14,7,15};
        instance = new HeapBinaryTree(input);
        instance.printInOrder();
        result = instance.inOrder();
        assertArrayEquals("11",result, expected);


        input = new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
        expected = new Integer[] {16,8,17,4,18,9,2,10,5,11,1,12,6,13,3,14,7,15};
        instance = new HeapBinaryTree(input);
        instance.printInOrder();
        result = instance.inOrder();
        assertArrayEquals("11",result, expected);

    }
    */

    @Test
    public void massiveTest() {
//        final int million = (int) Math.pow(10, 6);

        long t = System.nanoTime();

        int n = (int) Math.pow(10, 5);
//        Integer[] input = new Integer[n];
        int[] input = new int[n];
//        Random rnd = new Random();
        for(int i = 0; i < n; i++) {
//            input[i] = rnd.nextInt();
            input[i] = i;
//            if(i % million == 0) {
//                System.out.print(i/million + " ");
//                System.out.flush();
//            }
        }
        System.out.println();

        System.out.println("Initialised heap in " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - t) + " ms");
        System.out.flush();
        t = System.nanoTime();

        HeapBinaryTree<Integer> tree = new HeapBinaryTree<>(input);
        tree.printInOrder(new NullWriter()," ");

        System.out.println("Printed heap in order in " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - t) + " ms");

        System.out.flush();
        t = System.nanoTime();

        tree.printPreOrder(new NullWriter()," ");

        System.out.println("Printed heap pre order in " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - t) + " ms");


    }

    private static class NullWriter extends Writer {

        @Override
        public void write(String str) throws IOException {}

        @Override
        public void write(char[] cbuf, int off, int len) throws IOException {}

        @Override
        public void flush() throws IOException {}

        @Override
        public void close() throws IOException {}
    }
}
