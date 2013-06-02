package sorhus.collection;

/**
 * @author Anton Sorhus <anton.sorhus@gmail.com>
 */
public interface List<E> extends java.lang.Iterable<E> {

    LinkedList<E> add(E elem);
    boolean remove(E elem);
    int size();
    void clear();
}
