package algs4.Searching;

/**
 * 红黑树
 */
public class RedBlackBST<Key extends Comparable<Key>, Val> {
    private static boolean RED = true;
    private static boolean BLACK = false;
    private RBNode root;

    public void put(Key k, Val v) {
        root = put(root, k, v);
        root.colore = BLACK;
    }

    private RBNode put(RBNode node, Key k, Val v) {
        if (node == null)
            return new RBNode(k, v, RED);
        int cmp = k.compareTo(node.k);
        if (cmp < 0)
            node.left = put(node.left, k, v);
        else if (cmp > 0)
            node.right = put(node.right, k, v);
        else
            node.v = v;
        // balance
        if (isRed(node.right) && !isRed(node.left))
            node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left))
            node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right))
            flipColor(node);
        return node;
    }

    private RBNode rotateLeft(RBNode h) {
        RBNode x = h.right;
        h.right = x.left;
        x.left = h;
        h.colore = RED;
        x.colore = h.colore;
        return x;
    }

    private RBNode rotateRight(RBNode h) {
        RBNode x = h.left;
        h.left = x.right;
        x.right = h;
        h.colore = RED;
        x.colore = h.colore;
        return x;
    }

    private void flipColor(RBNode x) {
        x.left.colore = BLACK;
        x.right.colore = BLACK;
        x.colore = RED;
    }

    private boolean isRed(RBNode x) {
        if (x == null)
            return false;
        return x.colore;
    }

    private class RBNode {
        Key k;
        Val v;
        boolean colore;
        RBNode left;
        RBNode right;

        RBNode(Key k, Val v, boolean colore) {
            this.k = k;
            this.v = v;
            this.colore = colore;
        }
    }
}
