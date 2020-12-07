package ds.simple;

public class SimpleStack<E> {
    static class Node<E> {
        E item;
        Node<E> next;

        Node(E item) {
            this.item = item;
            next = null;
        }
    }

    private Node<E> head;
    private int size;

    public SimpleStack() {
        size = 0;
        head = new Node<>(null);
    }

    // 栈大小
    public int size() {
        return size;
    }

    // 栈空判断
    public boolean empty() {
        return size == 0;
    }

    // 入栈并返回入栈元素
    public E push(E item) {
        Node<E> first = new Node<>(item);
        first.next = head.next;
        head.next = first;
        size++;
        return item;
    }

    // 出栈并返回出栈元素
    public  E pop() {
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

    // 返回栈顶元素
    public E peek() {
        if (!empty())
            return head.next.item;
        return null;
    }

    public static void main(String[] args) {
        SimpleStack stack = new SimpleStack<Float>();
        System.out.println(stack.push(1.1f));
        System.out.println(stack.push(2.2f));
        System.out.println(stack.push(3.3f));
        System.out.println(stack.size());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.size());
    }
}
