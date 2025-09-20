package pracProbs;

import binSerTree.*;

public class treeCheck {
    binTree tree = new binTree();
    binNode root;
    binNode cur = root;
    binNode father = null;
    binNode grandFather = null;
    boolean result = true;

    public static void main(String args[]) {
        treeCheck check = new treeCheck();
        check.init();

        System.out.println(check.isSearch(check.root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    public boolean isSearch(binNode node, int min, int max) {
        if (node == null) {
            return true;
        }

        if (node.data <= min || node.data >= max) {
            return false;
        }

        return isSearch(node.left, min, node.data) && isSearch(node.right, node.data, max);
    }

    public void init() {
        tree.add(100);
        tree.add(20);
        tree.add(200);
        tree.add(10);
        tree.add(30);
        tree.add(150);
        tree.add(300);
        root = tree.search(100);
    }

    public binNode getMin() {
        binNode min = null;

        if (root == null) {
            System.out.println("root null");
            return null;
        }

        cur = root;
        while (cur.left != null) {
            cur = cur.left;
        }

        min = cur;

        return min;
    }

    public binNode getMax() {
        binNode max = null;

        if (root == null) {
            System.out.println("root null");
            return null;
        }

        cur = root;
        while (cur.right != null) {
            cur = cur.right;
        }
        max = cur;

        return max;
    }
}
