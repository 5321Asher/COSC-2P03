package binSerTree;
public class binNode {
    public binNode left;
    public binNode right;
    public int data;

    public binNode(binNode l, int d, binNode r) {
        this.data = d;
        this.left = l;
        this.right = r;

    }
}