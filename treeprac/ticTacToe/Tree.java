package ticTacToe;

public class Tree {
    Node root;

    public Node search(Board b) {
        return findNodeHelper(root, b);
    }

    public Node findNodeHelper(Node node, Board d) {
        if (root == null) {
            return null;
        }

        if (node.state == d) {
            return node;
        }

        for (Node c : node.children) {
            Node result = findNodeHelper(c, d);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    public void addFirst(Board elem) {
        if (root == null) {
            Node newNode = new Node(elem);
            root = newNode;
            return;
        }

        Node cur = new Node(elem);
        addFirstHelper(root).children.add(cur);
    }

    public Node addFirstHelper(Node node) {
        if (root == null) {
            return null;
        }

        if (!node.isFull()) {
            return node;
        }

        for (Node c : node.children) {
            Node result = addFirstHelper(c);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    public void addAtParent(Board elem, Node parent) {
        Node cur = new Node(elem);
        parent.children.add(cur);
    }
}
