public class avlTree<A extends Comparable<A>> {
    avlTree<A> left;
    avlTree<A> right;
    int height;
    A elem;

    private avlTree(A element) {
        this.elem = element;
        this.left = null;
        this.right = null;
        this.height = 0;
    }

    public static <A extends Comparable<A>> avlTree<A> createTree(A x) {
        return new avlTree<A>(x);
    }

    public static <A extends Comparable<A>> int height(avlTree<A> t) {
        if (t == null) {
            return -1;
        }
        return t.height;
    }

    private avlTree<A> rotateWithLeft(avlTree<A> k2) {
        avlTree<A> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }

    private avlTree<A> rotateWithRight(avlTree<A> k2) {
        avlTree<A> k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2;
        k2.height = Math.max(height(k2.right), height(k2.left)) + 1;
        k1.height = Math.max(height(k1.right), k2.height) + 1;
        return k1;
    }

    private avlTree<A> doubleWithLeft(avlTree<A> k3) {
        k3.left = rotateWithRight(k3.left);
        return rotateWithLeft(k3);
    }

    private avlTree<A> doubleWithRight(avlTree<A> k3) {
        k3.right = rotateWithLeft(k3.right);
        return rotateWithRight(k3);
    }

    public avlTree<A> insert(A x) {
        if (elem == null) {
            elem = x;
            return this;
        }
        
        if (x.compareTo(elem) < 0) {
            if (left == null) {
                left = new avlTree<A>(x);
            } else {
                left = left.insert(x);
            }
            
            if (height(left) - height(right) == 2) {
                if (x.compareTo(left.elem) < 0) {
                    return rotateWithLeft(this);
                } else {
                    return doubleWithLeft(this);
                }
            }
        } else if (x.compareTo(elem) > 0) {
            if (right == null) {
                right = new avlTree<A>(x);
            } else {
                right = right.insert(x);
            }
            
            if (height(right) - height(left) == 2) {
                if (x.compareTo(right.elem) > 0) {
                    return rotateWithRight(this);
                } else {
                    return doubleWithRight(this);
                }
            }
        }
        
        height = Math.max(height(left), height(right)) + 1;
        return this;
    }

   public String toString() {
    return "AVLTree{" + "element=" + elem + ", left=" + left + ", right=" + right + ", height= " + height + "}";

   }

    public static void main(String[] args) {
        avlTree<Integer> tree = avlTree.createTree(20);
        tree = tree.insert(22);
        tree = tree.insert(11);
        tree = tree.insert(19);
        tree = tree.insert(7);
        tree = tree.insert(10);
        tree = tree.insert(21);
        tree = tree.insert(67);
        tree = tree.insert(69);
        tree = tree.insert(17);
        System.out.println(tree.toString());
    }
}
