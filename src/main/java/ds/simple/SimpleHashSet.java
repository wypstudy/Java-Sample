package ds.simple;

/**
 * Date  :  2020/12/7
 * Author:  YiPing, Wei
 **/
public class SimpleHashSet<E> {
    // 哈希表里的链表
    static class Node<E> {
        int hash;
        E item;
        Node<E> next;

        Node(int hash, E item, Node<E> next) {
            this.item = item;
            this.hash = hash;
            this.next = next;
        }
    }

    // int为32位,高位hash移动到低位,保证只有高位变化的时候也能有不同hash值
    static private int hash(Object o) {
        return (o == null) ? 0 : o.hashCode() >> 16 & o.hashCode();
    }

    // 哈希表初始容量
    static private int INIT_CAPACITY = 1 << 4;
    // 哈希表最大容量
    static private int MAX_CAPACITY = 1 << 30;
    // 哈希表扩容负载,即达到0.75乘容量的size就应该扩容了
    static private float LOAD_FACTORY = 0.75f;

    // 哈希表
    private Node<E>[] table;
    // 哈希表扩容阈值
    private int threshold;
    // 元素数量
    private int size;

    // 哈希表扩容和返回
    private Node<E>[] resizeTable() {
        Node<E>[] oldTable = table;
        int oldCap = table != null ? table.length : 0;
        int oldThr = threshold;
        int newCap, newThr;

        // 扩容数量
        if (oldCap > 0) {
            if (oldCap >= MAX_CAPACITY) {
                // 扩容达到上限就不扩容了
                return oldTable;
            } else {
                // 没达到上限则双倍容量和阈值
                newCap = oldCap << 1;
                newThr = oldThr << 1;
            }
        } else {
            // 还没建表则初始化容量和阈值
            newCap = INIT_CAPACITY;
            newThr = (int) LOAD_FACTORY * newCap;
        }

        // hash表扩容,链会根据hash值分为高位和低位链
        @SuppressWarnings({"rawtypes", "unchecked"})
        Node<E>[] newTable = (Node<E>[]) new Node[newCap];
        for (int i = 0; i < oldCap; i++) {
            Node<E> loHead = null, loTail = null;
            Node<E> hiHead = null, hiTail = null;
            Node<E> e = oldTable[i];
            while (e != null) {
                Node<E> next = e.next;
                if ((e.hash & oldCap) == 0) {
                    // 插入低位链
                    if (loHead == null) {
                        loHead = e;
                    } else {
                        loTail.next = e;
                    }
                    loTail = e;
                } else {
                    // 插入高位链
                    if (hiHead == null) {
                        hiHead = e;
                    } else {
                        hiTail.next = e;
                    }
                    hiTail = e;
                }
                e = next;
            }
            if (loHead != null) {
                newTable[i] = loHead;
                loTail.next = null;
            }
            if (hiHead != null) {
                newTable[i + oldCap] = hiHead;
                hiTail.next = null;
            }
        }

        threshold = newThr;
        table = newTable;
        return newTable;
    }

    public int size() {
        return size;
    }

    public boolean empty() {
        return size == 0;
    }

    public boolean add(E item) {
        boolean change = false;
        Node<E>[] tab;
        Node<E> p;
        int n, i, h = hash(item);
        if ((tab = table) == null || (n = table.length) == 0) {
            n = (tab = resizeTable()).length;
        }
        if ((p = tab[i = (h & (n - 1))]) == null) {
            // Hash链无数据,生成头部
            tab[i] = new Node<>(h, item, null);
            change = true;
        } else {
            // Hash链有数据,在链中搜索
            Node<E> e;
            if (p.hash == h && p.item.equals(item)) {
                // 链头情况
                e = p;
            } else {
                // 链内情况
                while ((e = p.next) != null) {
                    if (e.hash == h && e.item.equals(item)) {
                        break;
                    }
                    p = e;
                }
            }
            if (e == null) {
                // 找不到的时候挂在链后面
                p.next = new Node<>(h, item, null);
                change = true;
            }
        }
        if (change) {
            if (++size > threshold)
                resizeTable();
        }
        return change;
    }

    public boolean remove(E item) {
        boolean change = false;
        Node<E>[] tab;
        Node<E> p;
        int n, i, h = hash(item);
        // 表非空
        if (!((tab = table) == null || (n = table.length) == 0)) {
            // 链非空
            if (!((p = tab[i = (h & (n - 1))]) == null)) {
                Node<E> e;
                if (p.hash == h && p.item.equals(item)) {
                    // 链头删除
                    tab[i] = p.next;
                    p.next = null;
                    change = true;
                } else {
                    // 链内删除
                    while ((e = p.next) != null) {
                        if (e.hash == h && e.item.equals(item)) {
                            break;
                        }
                        p = e;
                    }
                    if (e != null) {
                        p.next = e.next;
                        e.next = null;
                        change = true;
                    }
                }
            }
        }
        if (change) {
            size--;
        }
        return change;
    }

    public boolean contain(E item) {
        Node<E>[] tab;
        Node<E> p;
        int n, i, h = hash(item);
        // 表空直接返回删除失败
        if ((tab = table) == null || (n = table.length) == 0) {
            return false;
        }
        // 链空直接返回删除失败
        if ((p = tab[i = (h & (n - 1))]) == null) {
            return false;
        } else {
            // Hash链有数据,在链中搜索
            Node<E> e;
            if (p.hash == h && p.item.equals(item)) {
                // 链头情况
                e = p;
            } else {
                // 链内情况
                while ((e = p.next) != null) {
                    if (e.hash == h && e.item.equals(item)) {
                        break;
                    }
                    p = e;
                }
            }
            // 链内找不到返回删除失败
            return e != null;
        }
    }

    public static void main(String[] args) {
        SimpleHashSet<Character> s = new SimpleHashSet<>();
        System.out.println(s.add('A'));
        System.out.println(s.add('A'));
        System.out.println(s.add('B'));
        System.out.println(s.add('C'));
        System.out.println(s.add('D'));
        System.out.println(s.contain('D'));
        System.out.println(s.remove('D'));
        System.out.println(s.contain('D'));
        System.out.println(s.add('D'));
        System.out.println(s.add('E'));
        System.out.println(s.add('F'));
        System.out.println(s.add('G'));
        System.out.println(s.add('0'));
        System.out.println(s.add('1'));
        System.out.println(s.add('2'));
        System.out.println(s.add('3'));
        System.out.println(s.add('4'));
        System.out.println(s.add('5'));
        System.out.println(s.add('6'));
        System.out.println(s.add('7'));
        System.out.println(s.add('8'));
        System.out.println(s.add('9'));
        System.out.println(s.size());
    }
}
