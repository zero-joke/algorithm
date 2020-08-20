package Exercises.tree;

public class IsBalancedTree {
    
    public boolean isBalanced(TreeNode node) {
        return levelDiff(node) >= 0;
    }

    private int levelDiff(TreeNode node) {
        if(node == null)
            return 0;
        int d1 = levelDiff(node.left);
        int d2 = levelDiff(node.right);
        if(d1 == -1 || d2 == -1 || Math.abs(d1-d2) > 1) {
            return -1;
        }
        return Math.max(d1, d2) + 1;
    }

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
}