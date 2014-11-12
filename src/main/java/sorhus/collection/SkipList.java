package sorhus.collection;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author: anton.sorhus@gmail.com
 */
public class SkipList<S extends Comparable<S>, M> {

    /* variables */
    private int level = 1;
    private int size = 0;

    /* constants */
    private final float p;
    private final int MAX_LEVEL;
    private final Random rnd = new Random();
    private final Node head = new Node();
    private final Node nil = new Node() {
        @Override
        public int compareTo(Node that) {
            return 1;
        }
    };

    public SkipList(float p, int n) {
        this.p = p;
        this.MAX_LEVEL = (int) Math.ceil(Math.log(n) / Math.log(2));
        this.head.set(0, nil);
    }

    public int size() {
        return size;
    }

    public void add(S score, M member) {
        Node node = new Node(score, member);
        int randomLevel = getRandomLevel();
        Object[] update = new Object[Math.max(randomLevel, level)];
        Node next = head;
        for (int i = level - 1; i >= 0; i--) {
            while(node.compareTo(next.get(i)) >= 0) {
                next = next.get(i);
            }
            update[i] = next;
        }
        if(score.equals(next.score)) {
            next.member = member;
        } else {
            if (randomLevel > level) {
                for (int i = level; i < randomLevel; i++) {
                    head.set(i, nil);
                    update[i] = head;
                }
                this.level = randomLevel;
            }
            for (int i = 0; i < level; i++) {
                Node u = (Node) update[i];
                node.set(i, u.get(i));
                u.set(i, node);
            }
            size++;
        }
    }

    public boolean remove(M member) {
        return false;
    }

    public M get(S score) {
        java.util.Iterator<Node> it = head.iterator();
        Node next;
        while (it.hasNext() && (next = it.next()) != nil) {
            if(next.score.equals(score)) {
                return next.member;
            } else if(next.score.compareTo(score) < 0) {
                it = next.iterator();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(int i = level - 1; i >= 0; i--) {
            int j = 0;
            Node next = head;
            StringBuilder sb = new StringBuilder("Head -> ");
            while((next = next.get(i)) != nil) {
                j++;
                sb.append(next.toString()).append(" -> ");
            }
            sb.append("Nil\n");
            result.append(j).append(": ").append(sb);
        }
        return result.toString();
    }

    private int getRandomLevel() {
        int level = 1;
        while(rnd.nextDouble() < p && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }

    private class Node implements Comparable<Node> {

        M member;
        S score;
        List<Node> refs = new ArrayList<>();

        Node(S score, M member) {
            this.score = score;
            this.member = member;
        }

        Node() {}

        Node get(int level) {
            return refs.size() > level ? refs.get(level) : null;
        }

        void set(int level, Node node) {
            if(level < refs.size()) {
                refs.set(level, node);
            } else {
                refs.add(level, node);
            }
        }

        Iterator<Node> iterator() {
            return refs.iterator();
        }

        @Override
        public String toString() {
            return String.format("(%s, %s)", score, member);
        }

        @Override
        public int compareTo(Node that) {
            if(that == null) {
                return 1;
            } else if(that == nil) {
                return -1;
            } else {
                return this.score.compareTo(that.score);
            }
        }
    }
}
