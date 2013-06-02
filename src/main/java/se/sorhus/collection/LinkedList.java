package se.sorhus.collection;

/**
 * @author Anton Sorhus <anton.sorhus@gmail.com>
 */
public class LinkedList<E> implements List<E> {

    private E data;
    private LinkedList<E> next;
    private int size;

    @Override
    public LinkedList<E> add(E elem) {
        if(elem == null)
            throw new IllegalArgumentException();
        LinkedList<E> newNext = new LinkedList<E>();
        newNext.data = data;
        newNext.next = next;
        next = newNext;
        data = elem;
        size++;
        return this;
    }

    @Override
    public boolean remove(E elem) {
        boolean result;
        if (data == null) {
            result = false;
        } else if(data.equals(elem)) {
            data = next.data;
            next = next.next;
            size--;
            result = true;
        } else {
            result = next.remove(elem);
            if(result)
                size--;
        }
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        data = null;
        next = null;
        size = 0;
    }

    @Override
    public String toString() {
        String prefix = size() > 0 ? size() + ":{" : "";
        String seperator = next != null && next.data != null ? "," : "";
        return data == null ? "}" : prefix + data.toString() + seperator + next.toString();
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(getClass() != o.getClass())
            return false;
        LinkedList that = (LinkedList) o;
        if(size() != that.size())
            return false;
        Iterator<E> thisIt = iterator();
        Iterator thatIt = that.iterator();
        while (thisIt.hasNext())
            if(!thisIt.next().equals(thatIt.next()))
                return false;
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return new ElementIterator<E>(this);
    }

    class ElementIterator<E> implements Iterator<E> {

        E current;
        LinkedList<E> next;

        ElementIterator(LinkedList<E> first) {
            current = first.data;
            next = first.next;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E result = current;
            current = next.data;
            next = next.next;
            return result;
        }
    }
}
