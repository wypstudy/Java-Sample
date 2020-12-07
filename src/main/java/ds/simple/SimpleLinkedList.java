package ds.simple;

/**
 * Date  :  2020/12/7
 * Author:  YiPing, Wei
 **/
public class SimpleLinkedList<E> {
    static class Node<E> {
        Node<E> pre;
        Node<E> next;
        E item;

        Node(Node<E> pre, E item, Node<E> next) {
            this.pre = pre;
            this.item = item;
            this.next = next;
        }
    }

    private int size;
    private Node<E> first, last;

    public SimpleLinkedList() {
        size = 0;
        first = new Node<>(null, null, null);
        last = new Node<>(null, null, null);
        first.next = last;
        last.pre = first;
    }

    private void linkFirst(E item) {
        linkBefore(item, first.next);
    }

    private void linkLast(E item) {
        linkBefore(item, last);
    }

    private void linkBefore(E item, Node<E> next) {
        Node<E> pre = next.pre;
        Node<E> node = new Node<>(pre, item, next);
        next.pre = node;
        pre.next = node;
        size++;
    }

    private void unlinkFirst() {
        unlink(first.next);
    }

    private void unlinkLast() {
        unlink(last.pre);
    }

    private void unlink(Node<E> node) {
        Node<E> pre = node.pre;
        Node<E> next = node.next;
        pre.next = next;
        next.pre = pre;
        node.pre = null;
        node.next = null;
        size--;
    }

    private Node<E> node(int index) {
        // 该方法必须保证index在范围内
        if (index < size >> 1) {
            // 节点在前半部分,从头找
            Node<E> x = first.next;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            // 节点在后半部分,从尾找
            Node<E> x = last.pre;
            for (int i = size - 1; i > index; i--)
                x = x.pre;
            return x;
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

    public boolean add(E item) {
        linkLast(item);
        return true;
    }

    public boolean add(int index, E item) {
        if (rangeCheck(index)) {
            linkBefore(item, node(index));
            return true;
        }
        return false;
    }

    public E get(int index) {
        if (rangeCheck(index)) {
            return node(index).item;
        }
        return null;
    }

    public E set(int index, E item) {
        if (rangeCheck(index)) {
            node(index).item = item;
            return item;
        }
        return null;
    }

    public int indexOf(E item) {
        if (item == null) {
            Node<E> x = first.next;
            for (int i = 0; i < size; i++) {
                if (x.item == null)
                    return i;
                x = x.next;
            }
        } else {
            Node<E> x = first.next;
            for (int i = 0; i < size; i++) {
                if (item.equals(x.item))
                    return i;
                x = x.next;
            }
        }
        return -1;
    }

    public boolean contains(E item) {
        return indexOf(item) >= 0;
    }

    public E fastRemove(int index) {
        if (rangeCheck(index)) {
            Node<E> x = node(index);
            unlink(x);
            return x.item;
        }
        return null;
    }

    public E remove(E item) {
        return fastRemove(indexOf(item));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        Node<E> x = first.next;
        for (int i = 0; i < size; i++) {
            sb.append(x.item);
            sb.append(",");
            x = x.next;
        }
        sb.append("}");
        return sb.toString();
    }

    public static void main(String[] args) {
        SimpleLinkedList<Character> l = new SimpleLinkedList<>();
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
        System.out.println(l);
        l.remove('E');
        l.fastRemove(0);
        l.fastRemove(0);
        System.out.println(l);
        System.out.println(l.size());
    }
}
