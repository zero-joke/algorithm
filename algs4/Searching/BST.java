package algs4.Searching;

/**
 * 二叉查找数
 */
public class BST<Key extends Comparable<Key>, Value> {

    private BTNode root;

    public int size() {
        return size(root);
    }

    private int size(BTNode x) {
        if (x == null)
            return 0;
        return x.N;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(BTNode x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.val;
        }
    }

    public Key min() {
        BTNode x = min(root);
        return x == null ? null : x.key;
    }

    private BTNode min(BTNode x) {
        if (x == null)
            return null;
        if (x.left == null)
            return x;
        return min(x.left);
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private BTNode put(BTNode x, Key key, Value val) {
        if (x == null)
            return new BTNode(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private BTNode delete(BTNode x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = delete(x.left, key);
        } else if (cmp > 0) {
            x.right = delete(x.right, key);
        } else {
            if (x.right == null)
                return x.left;
            if (x.left == null)
                return x.right;
            BTNode t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private BTNode deleteMin(BTNode x) {
        if (x.left == null) {
            return x.right;
        } else {
            x.left = deleteMin(x.left);
            x.N = size(x.left) + size(x.right) + 1;
            return x;
        }
    }

    private class BTNode {
        Key key;
        Value val;
        BTNode left, right;
        int N; // 节点数

        BTNode(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }
}
