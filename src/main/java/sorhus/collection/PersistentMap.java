package sorhus.collection;

/**
 * @author: anton.sorhus@gmail.com
 */
public class PersistentMap<K,V> {

    private final Node<K,V> root;

    public PersistentMap() {
        this(new Node<K,V>());
    }

    private PersistentMap(Node<K, V> root) {
        this.root = root;
    }

    public V get(K key) {
        return root.get(key, key.hashCode());
    }

    public PersistentMap<K, V> put(K key, V value) {
        Entry<K, V> entry = new Entry<>(key, value, key.hashCode());
        Node<K, V> newRoot = root.put(entry);
        return newRoot == null ? this : new PersistentMap<>(newRoot);
    }

    protected static class Node<K,V> {

        private final int d;
        private Entry<K,V> entry;
        private Node<K,V>
            node0, node1, node2, node3, node4, node5, node6, node7,
            node8, node9, node10,node11,node12,node13,node14,node15,
            node16,node17,node18,node19,node20,node21,node22,node23,
            node24,node25,node26,node27,node28,node29,node30,node31;

        public Node() {
            this(0);
        }

        protected Node(int d) {
            this.d = d;
        }

        private Node(int d, Entry<K,V> entry) {
            this(d);
            this.entry = entry;
        }

        protected V get(K key, int hash) {
            if(entry != null && key.equals(entry.key)) {
                return entry.val;
            }
            Node<K,V> node = get(hash);
            return node == null ? null : node.get(key, hash);
        }

        protected int mask(int hash) {
            return (hash >>> 5*d) & 0b11111;
        }

        protected Node<K,V> put(Entry<K,V> entry) {
            Node<K,V> result;
            if((result = get(entry)) != null) {
                result.put(entry);
            } else {
                result = copy();
                if(result.entry == null) {
                    result.entry = entry;
                } else {
                    result.set(entry, new Node<>(d+1, entry));

                    Node<K, V> node = get(result.entry);
                    if(node == null) {
                        node = new Node<>(d+1, result.entry);
                    } else { // special case: entry and result.entry share mask. no need to copy
                        node = node.put(result.entry);
                    }
                    result.set(result.entry, node);
                    result.entry = null;
                }
            }
            return result;
        }

        protected Node<K,V> copy() {
            Node<K,V> result = new Node<>(d);
            for (int i = 0; i < 32; i++) {
                result.set(i, get(i));
            }
//            for (int i = 0; i < 32; i++) {
//                Node<K,V> node = get(i);
//                if(node != null) {
//                    Node<K,V> copy = node.copy();
//                    result.set(i, copy);
//                }
//            }
            result.entry = entry;
            return result;
        }

        private void set(Entry entry, Node<K, V> node) {
            set(entry.hash, node);
        }

        private Node<K,V> get(Entry entry) {
            return get(entry.hash);
        }

        private Node<K,V> get(int hash) {
            switch (mask(hash)) {
                case 0: return node0;
                case 1: return node1;
                case 2: return node2;
                case 3: return node3;
                case 4: return node4;
                case 5: return node5;
                case 6: return node6;
                case 7: return node7;
                case 8: return node8;
                case 9: return node9;
                case 10: return node10;
                case 11: return node11;
                case 12: return node12;
                case 13: return node13;
                case 14: return node14;
                case 15: return node15;
                case 16: return node16;
                case 17: return node17;
                case 18: return node18;
                case 19: return node19;
                case 20: return node20;
                case 21: return node21;
                case 22: return node22;
                case 23: return node23;
                case 24: return node24;
                case 25: return node25;
                case 26: return node26;
                case 27: return node27;
                case 28: return node28;
                case 29: return node29;
                case 30: return node30;
                case 31: return node31;
                default: throw new RuntimeException("Mask out of range: " + mask(hash));
            }
        }

        private void set(int hash, Node<K, V> node) {
            switch (mask(hash)) {
                case 0: node0 = node; return;
                case 1: node1 = node; return;
                case 2: node2 = node; return;
                case 3: node3 = node; return;
                case 4: node4 = node; return;
                case 5: node5 = node; return;
                case 6: node6 = node; return;
                case 7: node7 = node; return;
                case 8: node8 = node; return;
                case 9: node9 = node; return;
                case 10: node10 = node; return;
                case 11: node11 = node; return;
                case 12: node12 = node; return;
                case 13: node13 = node; return;
                case 14: node14 = node; return;
                case 15: node15 = node; return;
                case 16: node16 = node; return;
                case 17: node17 = node; return;
                case 18: node18 = node; return;
                case 19: node19 = node; return;
                case 20: node20 = node; return;
                case 21: node21 = node; return;
                case 22: node22 = node; return;
                case 23: node23 = node; return;
                case 24: node24 = node; return;
                case 25: node25 = node; return;
                case 26: node26 = node; return;
                case 27: node27 = node; return;
                case 28: node28 = node; return;
                case 29: node29 = node; return;
                case 30: node30 = node; return;
                case 31: node31 = node; return;
                default: throw new RuntimeException("Mask out of range: " + mask(hash));
            }
        }
    }

    private final static class Entry<K, V> {
        private final K key;
        private final V val;
        private final int hash;

        private Entry(K key, V val, int hash) {
            this.key = key;
            this.val = val;
            this.hash = hash;
        }
    }
}
