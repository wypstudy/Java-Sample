package ds.simple;

/**
 * Date  :  2020/12/7
 * Author:  YiPing, Wei
 **/
public class SimpleArrayList<E> {
    private Object[] data;
    private int size;

    public SimpleArrayList() {
        this(2);
    }

    public SimpleArrayList(int initCapacity) {
        data = new Object[initCapacity];
        size = 0;
    }

    private void ensureCapacity(int minCapacity) {
        while (minCapacity > data.length) {
            Object[] copy = new Object[data.length << 1];
            System.arraycopy(data, 0, copy, 0, size);
            data = copy;
        }
    }

    private boolean rangeCheck(int index) {
        return index >= 0 && index < size;
    }

    public int size() {
        return size;
    }

    public boolean empty() {
        return size == 0;
    }

    public boolean add(Object item) {
        ensureCapacity(size + 1);
        data[size++] = item;
        return true;
    }

    public boolean add(int index, Object item) {
        if (rangeCheck(index)) {
            ensureCapacity(++size);
            System.arraycopy(data, index, data, index + 1, size - index - 1);
            data[index] = item;
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (rangeCheck(index)) {
            return (E) data[index];
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public E set(int index, Object item) {
        if (rangeCheck(index)) {
            data[index] = item;
            return (E) item;
        }
        return null;
    }

    public int indexOf(Object item) {
        if (item == null) {
            for (int i = 0; i < size; i++)
                if (data[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (item.equals(data[i]))
                    return i;
        }
        return -1;
    }

    public boolean contains(Object item) {
        return indexOf(item) >= 0;
    }

    public E remove(Object item) {
        return fastRemove(indexOf(item));
    }

    @SuppressWarnings("unchecked")
    public E fastRemove(int index) {
        if (rangeCheck(index)) {
            System.arraycopy(data, index + 1, data, index, size - index - 1);
            size--;
            return (E) data[index];
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            sb.append(",");
        }
        sb.append("}");
        return sb.toString();
    }

    public static void main(String[] args) {
        SimpleArrayList<Character> l = new SimpleArrayList<>();
        l.add('A');
        l.add('B');
        l.add('C');
        l.add('D');
        l.add('E');
        System.out.println(l.size());
        System.out.println(l);
        System.out.println(l.indexOf('E'));
        l.remove('B');
        l.remove('C');
        System.out.println(l.indexOf('E'));
        l.remove('E');
        l.fastRemove(0);
        l.fastRemove(0);
        System.out.println(l.indexOf('E'));
        System.out.println(l.size());
    }
}
