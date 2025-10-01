import java.util.function.Consumer;

public class ThreadedTree<A extends Comparable<A>> {
    private ThreadedTree<A> root = null;
    private ThreadedTree<A> left;
    private ThreadedTree<A> right;
    private boolean lThread;
    private boolean rThread;
    private A elem;

    private ThreadedTree(ThreadedTree<A> left, boolean lthread, A elem, ThreadedTree<A> right, boolean rthread) {
        this.left = left;
        this.lThread = lthread;
        this.elem = elem;
        this.right = right;
        this.rThread = rthread;
    }

    public static <A extends Comparable<A>> ThreadedTree<A> createTree(A x) {
        ThreadedTree<A> tree = new ThreadedTree<A>(null, true, x, null, true);
        tree.root = tree;
        return tree;
    }

    public void insert(A x) {
        ThreadedTree<A> cur = root;

        while (true) {
            int compare = x.compareTo(cur.elem);
            if (compare == 0) {
                return; 
            } else if (compare < 0) {
                if (!cur.lThread) {
                    cur = cur.left;
                } else {
                    ThreadedTree<A> newNode = new ThreadedTree<>(cur.left, true, x, cur, true);
                    cur.left = newNode;
                    cur.lThread = false;
                    return;
                }
            } else {
                if (!cur.rThread) {
                    cur = cur.right;
                } else {
                    ThreadedTree<A> newNode = new ThreadedTree<>(cur, true, x, cur.right, true);
                    cur.right = newNode;
                    cur.rThread = false;
                    return;
                }
            }
        }
    }

    private ThreadedTree<A> findSuccessor() {
        if (rThread) {
            return right; 
        }
        ThreadedTree<A> cur = right;
        while (!cur.lThread) {
            cur = cur.left;
        }
        return cur;
    }

    public void inOrderTraverse(Consumer<A> action) {
        ThreadedTree<A> cur = leftMost(root);

        while (cur != null) {
            action.accept(cur.elem);
            cur = cur.findSuccessor();
        }
    }

    private ThreadedTree<A> leftMost(ThreadedTree<A> node) {
        if (node == null) {
            return null;
        }
        while (!node.lThread) {
            node = node.left;
        }
        return node;
    }

    public static void main(String[] args) {
        ThreadedTree<Character> tree = ThreadedTree.createTree('h');
        tree.insert('d');
        tree.insert('b');
        tree.insert('a');
        tree.insert('c');
        tree.insert('f');
        tree.insert('e');
        tree.insert('k');
        tree.insert('i');
        tree.insert('j');

        tree.inOrderTraverse(System.out::print);
    }
}
