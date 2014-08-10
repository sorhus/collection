package sorhus.collection;

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
        if(size() > 0) {
            LinkedList<E> newNext = new LinkedList<>();
            newNext.data = data;
            newNext.next = next;
            next = newNext;
        }
        data = elem;
        size++;
        return this;
    }

    @Override
    public boolean remove(E elem) {
        if(elem == null) {
            return false;
        }
        if(elem.equals(data)) {
            if(next != null) {
                data = next.data;
                next = next.next;
            } else {
                data = null;
            }
            size--;
            return true;
        } else if(next != null) {
            boolean result = next.remove(elem);
            if(result)
                size--;
            return result;
        } else {
            return false;
        }
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
    public List<E> reverse() {
        int tmp = size;
        size = 0;
        LinkedList<E> reversed = reverse(null);
        reversed.size = tmp;
        return reversed;
    }

    private LinkedList<E> reverse(LinkedList<E> previous) {
        LinkedList<E> tmp = next;
        next = previous;
        return tmp == null ? this : tmp.reverse(this);
    }


    @Override
    public String toString() {
        String prefix = size() > 0 ? String.format("%d:{", size()) : "";
        String seperator = next != null && next.data != null ? "," : "";
        return data == null ? "}" : String.format("%s%s%s%s", prefix, data.toString(), seperator, next.toString());
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
        return new ElementIterator<>(this);
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
            if(next != null) {
                current = next.data;
                next = next.next;
            } else {
                current = null;
            }
            return result;
        }
    }


}
