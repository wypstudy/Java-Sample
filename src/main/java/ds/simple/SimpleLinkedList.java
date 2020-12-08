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

    // 链表头插入
    private void linkFirst(E item) {
        linkBefore(item, first.next);
    }

    // 链表尾插入
    private void linkLast(E item) {
        linkBefore(item, last);
    }

    // 在节点next前插入
    private void linkBefore(E item, Node<E> next) {
        Node<E> pre = next.pre;
        Node<E> node = new Node<>(pre, item, next);
        next.pre = node;
        pre.next = node;
        size++;
    }

    // 链表头删除
    private void unlinkFirst() {
        unlink(first.next);
    }

    // 链表尾删除
    private void unlinkLast() {
        unlink(last.pre);
    }

    // 节点node删除
    private void unlink(Node<E> node) {
        Node<E> pre = node.pre;
        Node<E> next = node.next;
        pre.next = next;
        next.pre = pre;
        node.pre = null;
        node.next = null;
        size--;
    }

    // 获取第index个节点
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

    // 判断索引合法
    private boolean rangeCheck(int index) {
        return index >= 0 && index < size;
    }

    // 返回List大小
    public int size() {
        return size;
    }

    // 返回List是否为空
    public boolean empty() {
        return size == 0;
    }

    // 链表头插入
    public void addFirst(E item) {
        linkFirst(item);
    }

    // 链表尾插入
    public void addLast(E item) {
        linkLast(item);
    }

    // 获取表头值
    public E getFirst() {
        if (!empty()) {
            return first.next.item;
        }
        return null;
    }

    // 获取表尾值
    public E getLast() {
        if (!empty()) {
            return last.pre.item;
        }
        return null;
    }

    // 删除表头并返回
    public E removeFirst() {
        if (!empty()) {
            E item = first.next.item;
            unlinkFirst();
            return item;
        }
        return null;
    }

    // 删除表尾部并返回
    public E removeLast() {
        if (!empty()) {
            E item = last.pre.item;
            unlinkLast();
            return item;
        }
        return null;
    }

    // 表尾添加元素
    public void add(E item) {
        addLast(item);
    }

    // 表指定位置添加元素
    public boolean add(int index, E item) {
        if (rangeCheck(index)) {
            linkBefore(item, node(index));
            return true;
        }
        return false;
    }

    // 表指定位置元素获取
    public E get(int index) {
        if (rangeCheck(index)) {
            return node(index).item;
        }
        return null;
    }

    // 表指定位置元素值修改
    public E set(int index, E item) {
        if (rangeCheck(index)) {
            node(index).item = item;
            return item;
        }
        return null;
    }

    // 指定元素值第一次出现表索引
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

    // 判断表里是否有元素
    public boolean contains(E item) {
        return indexOf(item) >= 0;
    }

    // 根据索引删除表
    public E fastRemove(int index) {
        if (rangeCheck(index)) {
            Node<E> x = node(index);
            unlink(x);
            return x.item;
        }
        return null;
    }

    // 根据元素值删除表
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
