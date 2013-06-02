package sorhus.collection;

import java.lang.*;

/**
 * @author Anton Sorhus <anton.sorhus@gmail.com>
 */
public interface Set<E> extends java.lang.Iterable<E> {

    Set<E> add(E elem);
    boolean contains(E elem);
    Set<E> remove(E elem);
    int size();
    void clear();

}
