package sorhus.collection;

import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;

import static junit.framework.Assert.assertNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

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

        assertThat(instance.size(), is(4));
        assertThat(instance.get(4), is("d"));
        assertThat(instance.get(3), is("e"));
        assertNull(instance.get(1));
        assertNull(instance.get(6));
        assertNull(instance.get(8));
    }

    public void testTime() throws Exception {
        Random rnd = new Random();
        float[] ps = new float[] { 0.5f };
        int[] ns = new int[] { 1, 10, 100, 1000, 10000 };
        int REPS = 2;
        for (int n : ns) {
            for (float p : ps) {
                System.out.println(n + ", " + p);
                long tsl = 0;
                long tss = 0;
                for(int i = 0; i < REPS; i++) {
                    List<Node<Integer, String>> elements = getElements(n, rnd);
                    tsl += addSL(p, n, elements);
                    tss += addSS(elements);
                }
                System.out.println(Math.log10(tsl) + " vs " + Math.log10(tss));
            }
        }
    }

    long addSL(float p, int n, List<Node<Integer, String>> elements) {
        SkipList<Integer, String> sl = new SkipList<>(p, n);
        long time = System.nanoTime();
        for (Node<Integer, String> node : elements) {
            sl.add(node.score, node.member);
        }
        return System.nanoTime() - time;

    }

    long addSS(List<Node<Integer, String>> elements) {
        SortedSet<Node<Integer, String>> ss = new TreeSet<>();
        long time = System.nanoTime();
        for (Node<Integer, String> node : elements) {
            ss.add(node);
        }
        return System.nanoTime() - time;
    }

    List<Node<Integer, String>> getElements(int n, Random rnd) {
        List<Node<Integer, String>> elements = new ArrayList<>(n);
        for (int j = 0; j < n; j++) {
            Node<Integer, String> node = new Node<>(rnd.nextInt(n*n), UUID.randomUUID().toString());
            elements.add(node);
        }
        return elements;
    }

    private class Node<S extends Comparable<S>, M> implements Comparable<Node<S, M>> {

        final S score;
        final M member;

        private Node(S score, M member) {
            this.score = score;
            this.member = member;
        }

        @Override
        public int compareTo(Node<S,M> o) {
            return this.score.compareTo(o.score);
        }

        @Override
        public boolean equals(Object that) {
            return this.score.equals(((Node<S,M>) that).score);
        }
    }
}
