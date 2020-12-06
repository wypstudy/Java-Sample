package data.structrue;

import java.util.Arrays;

public class SStack<E> {
    private Object[] data;
    private int size;

    SStack() {
        this(2);
    }

    SStack(int capacity) {
        size = 0;
        data = new Object[capacity];
    }

    // 栈扩容
    private synchronized void ensureCapacity(int capacity) {
        if (capacity > data.length) {
            data = Arrays.copyOf(data, capacity);
        }
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
    public synchronized E push(E item) {
        ensureCapacity(size + 1);
        data[size++] = item;
        return item;
    }

    // 出栈并返回出栈元素
    public synchronized E pop() {
        if (!empty())
            return (E) data[--size];
        return null;
    }

    // 返回栈顶元素
    public synchronized E peek() {
        if (!empty())
            return (E) data[size - 1];
        return null;
    }

    // 查询距离栈顶最近的元素实体索引
    public synchronized int search(E item) {
        for (int i = size - 1; i >= 0; i--)
            if (item.equals(data[i]))
                return size-i;
        return -1;
    }

    public static void main(String[] args) {
        SStack stack = new SStack<Float>();
        System.out.println(stack.push(1.1f));
        System.out.println(stack.push(2.2f));
        System.out.println(stack.push(3.3f));
        System.out.println(stack.search(3.3f));
        System.out.println(stack.pop());
        System.out.println(stack.search(3.3f));
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
