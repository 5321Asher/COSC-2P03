package pracProbs;

import binSerTree.*;

public class count {
    int leaves = 0;
    int nodes = 0;
    binTree tree = new binTree();
    binNode root;
    binNode cur = root;

    public static void main(String args[]) {
        count count = new count();
        count.init();

        count.countNodes(count.root);
        count.countLeaves(count.root);
        System.out.println("nodes: " + count.nodes);
        System.out.println("leaves: " + count.leaves);
    }

    public void countLeaves(binNode node) {
        if (root == null) {
            System.out.println("root null");
            return;
        }
        if (node == null) {
            return;
        }

        countLeaves(node.left);
        countLeaves(node.right);
        if (node.left == null && node.right == null) {
            leaves += 1;
        }
    }

    public void countNodes(binNode node) {
        if (root == null) {
            System.out.println("root null");
            return;
        }
        if (node == null) {
            return;
        }

        countNodes(node.left);
        countNodes(node.right);
        nodes += 1;
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
