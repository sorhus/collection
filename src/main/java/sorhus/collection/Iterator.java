package sorhus.collection;

/**
 * @author Anton Sorhus <anton.sorhus@gmail.com>
 */
public interface Iterator<E> {

    boolean hasNext();
    E next();
}
