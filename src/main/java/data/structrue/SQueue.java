package data.structrue;

/**
 * Date  :  2020/12/7
 * Author:  YiPing, Wei
 **/
public class SQueue<E> {
    static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E item) {
            this.item = item;
            next = null;
        }
    }

    private Node<E> head, tail;
    private int size;

    public SQueue() {
        size = 0;
        head = tail = new Node<E>(null);
    }

    // 队列大小
    public int size() {
        return size;
    }

    // 判断队列空
    public boolean empty() {
        return size == 0;
    }

    // 入队
    public E add(E item) {
        tail = tail.next = new Node<>(item);
        size++;
        return item;
    }

    // 出队
    public E poll() {
        if (!empty()) {
            Node<E> first = head.next;
            E item = first.item;
            head.next = first.next;
            first.next = first;
            size--;
            return item;
        }
        return null;
    }

    // 队头查询,但不出队
    public E peek() {
        if (!empty()) {
            return head.next.item;
        }
        return null;
    }

    public static void main(String[] args) {
        SQueue<Float> q = new SQueue<>();
        System.out.println(q.add(1.1f));
        System.out.println(q.add(2.2f));
        System.out.println(q.add(3.3f));
        System.out.println(q.add(4.4f));
        System.out.println(q.size());
        System.out.println(q.poll());
        System.out.println(q.peek());
        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.poll());
        System.out.println(q.size());
    }
}
