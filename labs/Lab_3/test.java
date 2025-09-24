public class test {
    public static void main(String[] args) {
        BinaryTree<Character> tree = new BinaryTree<>(
                new BinaryTree<Character>(new BinaryTree<Character>(null, 'd', null), 'b', null), 'a',
                new BinaryTree<Character>(new BinaryTree<Character>(null, 'e', null), 'c',
                        new BinaryTree<Character>(new BinaryTree<Character>(null, 'g', null), 'f', null)));

        System.out.println("Breath First Traversal: ");
        tree.bfTraversal(System.out::print);
        System.out.println();

        System.out.println("Pre Order Traversal: ");
        tree.preOrderTraversal(System.out::print);
        System.out.println();

        System.out.println("In Order Traversal: ");
        tree.inOrderTraversal(System.out::print);
        System.out.println();

        System.out.println("Post Order Traversal: ");
        tree.postOrderTraversal(System.out::print);
        System.out.println();

        System.out.println("Height: ");
        System.out.print(tree.height());
        System.out.println();

        System.out.println("Contains e? ");
        System.out.println(tree.contains('e'));

        System.out.println("Contains h? ");
        System.out.println(tree.contains('h'));

    }
}
