package ds.simple;

import java.util.Comparator;

/**
 * Date  :  2020/12/8
 * Author:  YiPing, Wei
 **/
public class SimpleRBTreeMap<K, V> {
    private static boolean BLACK = true;
    private static boolean RED = false;

    static class TreeNode<K, V> {

        K key;
        V value;
        boolean color;
        TreeNode<K, V> parent, left, right;

        TreeNode(K key, V value, TreeNode<K, V> parent) {
            this.color = BLACK; // 插入节点默认黑色
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    TreeNode<K, V> root;
    Comparator<? super K> cpr;
    int size;

    SimpleRBTreeMap() {
        size = 0;
    }

    SimpleRBTreeMap(Comparator<? super K> cpr) {
        this();
        this.cpr = cpr;
    }

    public int size() {
        return size;
    }

    public boolean empty() {
        return size == 0;
    }

    public void put(K key, V value) {
        int cmp;
        TreeNode<K, V> p, e;
        if ((p = root) == null) {
            root = new TreeNode<>(key, value, null);
            size++;
        } else {
            Comparator<? super K> k = cpr != null ? cpr : (Comparator<? super K>) key;
            e = p;
            do {
                p = e;
                cmp = k.compare(key, e.key);
                if (cmp < 0) {
                    e = e.left;
                } else if (cmp > 0) {
                    e = e.right;
                } else {
                    e.value = value;
                    return;
                }
            } while (e != null);
            e = new TreeNode<>(key, value, p);
            if (cmp < 0) {
                p.left = e;
            } else {
                p.right = e;
            }
            fixAdd(e);
        }

    }

    public V get(K key) {
        int cmp;
        TreeNode<K, V> p = root;
        Comparator<? super K> k = cpr != null ? cpr : (Comparator<? super K>) key;
        while (p != null) {
            cmp = k.compare(key, p.key);
            if (cmp < 0) {
                p = p.left;
            } else if (cmp > 0) {
                p = p.right;
            } else {
                return p.value;
            }
        }
        return null;
    }

    public V remove(K key) {
        return null;
    }

    private void fixAdd(TreeNode<K, V> x) {
        x.color = RED;
        while (x != null && x!=root && x.parent.color == RED) {
            if (x.parent == x.parent.parent.left) {

            }
        }
    }

    private void fixRemove(TreeNode<K, V> x) {

    }

}
