package se.sorhus.collection;

/**
 * @author Anton Sorhus <anton.sorhus@gmail.com>
 */
public interface Set<E> extends Iterable<E> {

    Set<E> add(E elem);
    boolean contains(E elem);
    Set<E> remove(E elem);
    int size();
    void clear();

}
