package pracProbs;

import binSerTree.*;

// a program that inserts 50, 30, 70, 20, 40, 60, 80, in that order and prints the tree using inorder, preorder, and postorder traversals
public class insertTraverse {
    binTree tree = new binTree();
    binNode root;
    binNode cur = root;

    public static void main(String args[]) {
        insertTraverse prac = new insertTraverse();
        prac.init();

        System.out.println("inorder:");
        prac.inorder(prac.root);
        System.out.println();
        System.out.println("preorder:");
        prac.preorder(prac.root);
        System.out.println();
        System.out.println("postorder:");
        prac.postorder(prac.root);

    }

    public void postorder(binNode node) {
        if (node == null) {
            return;
        }

        postorder(node.left);
        postorder(node.right);
        System.out.println(node.data);
    }

    public void preorder(binNode node) {
        if (node == null) {
            return;
        }

        System.out.println(node.data);
        preorder(node.left);
        preorder(node.right);
    }

    public void inorder(binNode node) {
        if (node == null) {
            return;
        }

        inorder(node.left);
        System.out.println(node.data);
        inorder(node.right);
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
