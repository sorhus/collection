package sorhus.collection;

/**
 * @author Anton Sorhus <anton.sorhus@gmail.com>
 */
public class HashSet<E> implements Set<E> {

    private HashMap<E, String> map;
    private static final String value = "";

    public HashSet() {
        map = new HashMap<E, String>();
    }

    public HashSet(int initCapacity) {
        map = new HashMap<E, String>(initCapacity, 1.0);
    }

    @Override
    public Set<E> add(E elem) {
        map.put(elem, value);
        return this;
    }

    @Override
    public boolean contains(E elem) {
        return map.containsKey(elem);
    }

    @Override
    public Set<E> remove(E elem) {
        map.remove(elem);
        return this;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(getClass() != o.getClass())
            return false;
        HashSet that = (HashSet) o;
        if(size() != that.size())
            return false;
        Iterator<E> it = iterator();
        while(it.hasNext())
            if(!that.contains(it.next()))
                return false;
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return map.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(size() + ":{");
        Iterator<E> it = iterator();
        while(it.hasNext())
            sb.append(it.next().toString()).append(",");
        return sb.substring(0,sb.length()-1) + "}";
    }
}
