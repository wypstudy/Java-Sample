package ds.simple;

import java.util.Comparator;

/**
 * Date  :  2020/12/8
 * Author:  YiPing, Wei
 **/
public class SimpleRBTreeMap<K extends Comparator<K>, V> {
    private static boolean BLACK = true;
    private static boolean RED = false;

    static class Node<K, V> {

        K key;
        V value;
        boolean color;
        Node<K, V> parent, left, right;

        Node(K key, V value, boolean color, Node<K, V> parent, Node<K, V> left, Node<K, V> right) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    Node<K, V> root;
    int size;

    SimpleRBTreeMap() {
        size = 0;
    }


    public int size() {
        return size;
    }

    public boolean empty() {
        return size == 0;
    }

    public void put(K key, V value) {
        int cmp;
        Node<K, V> p = search(key), e;
        if (p == null) {
            // 根节点插入
            root = new Node<>(key, value, BLACK, null, null, null);
            size++;
        } else {
            // 叶节点插入
            Comparator<? super K> k = key;
            cmp = k.compare(key, p.key);
            if (cmp == 0) {
                // 能找到 key 赋值
                p.value = value;
            } else {
                // 找不到 key 插入
                e = new Node<>(key, value, RED, p, null, null);
                if (cmp < 0) {
                    p.left = e;
                } else {
                    p.right = e;
                }
                // 修正成红黑树
                fixAdd(e);
                size++;
            }
        }

    }

    public V get(K key) {
        Node<K, V> x = search(key);
        if (x != null && key.equals(x.key))
            return x.value;
        return null;
    }

    public V remove(K key) {
        Node<K, V> x = search(key);
        V re = null;
        if (x != null && key.equals(x.key)) {
            re = x.value;
            removeNode(x);
        }
        return re;
    }

    private void removeNode(Node<K, V> x) {
        // TODO 完成节点删除逻辑
    }

    // 查找 key,找不到时返回可插入节点
    private Node<K, V> search(K key) {
        int cmp;
        Node<K, V> p = root, e = p;
        Comparator<? super K> k = key;
        do {
            p = e;
            cmp = k.compare(key, e.key);
            if (cmp < 0) {
                e = e.left;
            } else if (cmp > 0) {
                e = e.right;
            } else {
                // 查找到返回
                return e;
            }
        } while (e != null);
        // 查找不到返回可插入节点
        return p;
    }

    private void fixAdd(Node<K, V> x) {
        Node<K, V> parent, grandparent, uncle;

        // 父节点存在且父节点是红色与当前节点颜色冲突
        while ((parent = x.parent) != null && isRed(parent)) {
            grandparent = parent.parent;

            if (parent == grandparent.left) {
                // 父节点是祖父节点左子节点
                uncle = grandparent.right;

                // 情况1 叔叔节点是红色
                if (uncle != null && isRed(uncle)) {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(grandparent);
                    x = grandparent;
                    continue;
                }

                // 情况2 叔叔节点是黑色,且当前节点是右子节点
                if (parent.right == x) {
                    Node<K, V> tmp;
                    rotateLeft(parent);
                    tmp = parent;
                    parent = x;
                    x = tmp;
                }

                // 情况3 叔叔节点是黑色,且当前节点是左子节点
                setBlack(parent);
                setRed(grandparent);
                rotateRight(grandparent);
            } else {
                // 父节点是祖父节点右子节点
                uncle = grandparent.left;

                // 情况1 叔叔节点是红色
                if (uncle != null && isRed(uncle)) {
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(grandparent);
                    x = grandparent;
                    continue;
                }

                // 情况2 叔叔节点是黑色,且当前节点是左孩子
                if (parent.left == x) {
                    Node<K, V> tmp;
                    rotateRight(parent);
                    tmp = parent;
                    parent = x;
                    x = tmp;
                }

                // 情况3 叔叔节点是黑色,且当前节点是右孩子
                setBlack(parent);
                setRed(grandparent);
                rotateLeft(grandparent);
            }
        }
        setBlack(root);
    }

    private void fixRemove(Node<K, V> x) {
        // TODO 完成删除后修复红黑树逻辑
    }

    private boolean isRed(Node<K, V> x) {
        return x != null && x.color == RED;
    }

    private boolean isBlack(Node<K, V> x) {
        return x == null || x.color == BLACK;
    }

    private void setRed(Node<K, V> x) {
        x.color = RED;
    }

    private void setBlack(Node<K, V> x) {
        x.color = BLACK;
    }

    /*
     *       px                   px
     *      /                    /
     *     x                    y
     *    / \      --左旋->     / \
     *  lx  y                 x   ry
     *     / \               / \
     *   ly  ry            lx  ly
     */
    private void rotateLeft(Node<K, V> x) {
        Node<K, V> y = x.right;
        x.right = y.left;
        if (y.left != null)
            y.left.parent = x;
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else {
            if (x.parent.left == x) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        }
        y.left = x;
        x.parent = y;
    }

    /*
     *       py                   py
     *      /                    /
     *     y                    x
     *    / \      --右旋->     / \
     *   x  ry                lx  y
     *  / \                      / \
     * lx  rx                   rx  ry
     */
    private void rotateRight(Node<K, V> y) {
        Node<K, V> x = y.left;
        y.left = x.right;
        if (x.right != null) {
            x.right.parent = y;
        }
        x.parent = y.parent;
        if (y.parent == null) {
            root = x;
        } else {
            if (y.parent.left == y) {
                y.parent.left = x;
            } else {
                y.parent.right = x;
            }

        }
        x.right = y;
        y.parent = x;
    }

}
