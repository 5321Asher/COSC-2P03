package binSerTree;
public class binTree {

    binNode root = null;

    public Integer depth(binNode node) {
        if (node == null) {
            return 0;
        }

        int leftDepth = depth(node.left);
        int rightDepth = depth(node.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }

    public void delete(int element) {
        binNode deleteNode = search(element);
        binNode curr;
        binNode father;
        binNode small;
        binNode parent;
        if (deleteNode == null) { //if delete node = null
            System.out.println("element does not exist");
            return;
        }
        if (root == null) { //if root = null
            System.out.println("no root");
            return;
        } else {
            curr = root;
        }
        while (curr.left != deleteNode && curr.right != deleteNode) { //find father
            if (curr.data > element) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        father = curr;

        if (deleteNode.left == null && deleteNode.right == null) { // if delete node = leaf
            if (deleteNode == root) {
                root = null;
                System.out.println("root deleted");
                return;
            } else if (father.left == deleteNode) {
                father.left = null;
            } else {
                father.right = null;
            }
            System.out.println("node deleted");
            return;
        } else if (deleteNode.left != null ^ deleteNode.right != null) { // if delete node has only 1 child
            if (deleteNode == root) {
                if (root.left != null) {
                    root = root.left;
                } else {
                    root = root.right;
                }
                System.out.println("root deleted");
                return;
            } else if (deleteNode.left != null) {
                if (father.left == deleteNode) {
                    father.left = deleteNode.left;
                } else {
                    father.right = deleteNode.left;
                }
            } else {
                if (father.right == deleteNode) {
                    father.right = deleteNode.right;
                } else {
                    father.left = deleteNode.right;
                }
            }
            System.out.println("node deleted");
            return;
        } else if (deleteNode.left != null && deleteNode.right != null) { // if delete node has 2 children
            if (deleteNode == root) {
                // Handle root deletion with 2 children
                curr = root.right;
                parent = null;
                
                // Find smallest node in right subtree
                while (curr.left != null) {
                    parent = curr;
                    curr = curr.left;
                }
                small = curr;

                // Disconnect smallest node from its current position
                if (parent != null) {
                    parent.left = small.right;
                } else {
                    // smallest node is root.right itself
                    root.right = small.right;
                }

                // Connect smallest node as new root
                small.left = root.left;
                small.right = root.right;
                root = small;
                System.out.println("root deleted");
                return;
            } else {
                // Handle non-root deletion with 2 children
                curr = deleteNode.right;
                parent = null;
                
                // Find smallest node in right subtree
                while (curr.left != null) {
                    parent = curr;
                    curr = curr.left;
                }
                small = curr;

                // Disconnect smallest node from its current position
                if (parent != null) {
                    parent.left = small.right;
                } else {
                    // smallest node is deleteNode.right itself
                    deleteNode.right = small.right;
                }

                // Connect smallest node to replace deleteNode
                small.left = deleteNode.left;
                small.right = deleteNode.right;
                
                // Connect small to deleteNode's parent
                if (father.left == deleteNode) {
                    father.left = small;
                } else {
                    father.right = small;
                }
                System.out.println("node deleted");
                return;
            }   
        }
    }

    public binNode search(int element) {
        binNode curr = root;
        if (root == null) {
            System.out.println("no nodes to search");
            return null;
        }

        while (curr != null) {
            if (curr.data == element) {
                System.out.println("node found");
                return curr;
            } else if (curr.data > element) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        System.out.println("node not found");
        return null;
    }

    public void add(int element) {
        binNode newNode = new binNode(null, element, null);

        if (root == null) {
            root = newNode;
            System.out.println("node added as root");
            return;
        }

        binNode curr = root;
        while (true) {
            if (curr.data == element) {
                System.out.println("node already in tree");
                return; // Exit the method, don't add duplicate
            } else if (curr.data > element) {
                if (curr.left != null) {
                    curr = curr.left;
                } else {
                    curr.left = newNode;
                    System.out.println("node added");
                    return;
                }
            } else {
                if (curr.right != null) {
                    curr = curr.right;
                } else {
                    curr.right = newNode;
                    System.out.println("node added");
                    return;
                }
            }
        }
    }

}
