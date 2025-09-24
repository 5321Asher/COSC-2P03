import java.util.function.Consumer;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree<A> {
    BinaryTree<A> root = null;
    BinaryTree<A> left;
    BinaryTree<A> right;
    A elem;

    public BinaryTree(BinaryTree<A> left, A elem, BinaryTree<A> right) {
        this.left = left;
        this.right = right;
        this.elem = elem;
        root = this;
    }

    public BinaryTree(A elem) {
        root = new BinaryTree<A>(null, elem, null);
    }

    public void bfTraversal(Consumer<A> action) {
        if (root == null)
            return;

        Queue<BinaryTree<A>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            BinaryTree<A> cur = null;
            if (queue.peek() != null) {
                cur = queue.remove();
            }
            action.accept(cur.elem);

            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    public void postOrderTraversal(Consumer<A> action) {
        postOrderTraversalFunc(action, root);
    }

    private void postOrderTraversalFunc(Consumer<A> action, BinaryTree<A> node) {
        if (node == null) {
            return;
        }

        postOrderTraversalFunc(action, node.left);
        postOrderTraversalFunc(action, node.right);
        action.accept(node.elem);
    }

    public void preOrderTraversal(Consumer<A> action) {
        preOrderTraversalFunc(action, root);
    }

    private void preOrderTraversalFunc(Consumer<A> action, BinaryTree<A> node) {
        if (node == null) {
            return;
        }

        action.accept(node.elem);
        preOrderTraversalFunc(action, node.left);
        preOrderTraversalFunc(action, node.right);
    }

    public void inOrderTraversal(Consumer<A> action) {
        inOrderTraversalFunc(action, root);
    }

    private void inOrderTraversalFunc(Consumer<A> action, BinaryTree<A> node) {
        if (node == null) {
            return;
        }

        inOrderTraversalFunc(action, node.left);
        action.accept(node.elem);
        inOrderTraversalFunc(action, node.right);
    }

    public int height() {
        return heightFunc(root);
    }

    private int heightFunc(BinaryTree<A> node) {
        if (node == null) {
            return -1;
        }

        int leftHeight = heightFunc(node.left);
        int rightheight = heightFunc(node.right);

        return Math.max(leftHeight, rightheight) + 1;
    }

    public boolean contains(A x) {
        return containsFunc(x, root);
    }

    private boolean containsFunc(A x, BinaryTree<A> node) {
        if (root == null) {
            System.out.println("no nodes");
            return false;
        }
        if (node == null) {
            return false;
        }
        if (node.elem == x) {
            return true;
        }

        return containsFunc(x, node.left) || containsFunc(x, node.right);
    }

}