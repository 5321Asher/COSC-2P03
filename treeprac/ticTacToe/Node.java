package ticTacToe;

import java.util.List;
import java.util.LinkedList;

public class Node  {
    List<Node> children;
    Board state;
    int childLim;

    public Node (Board d) {
        this.state = d;
        children = new LinkedList<>();
        childLim = 10;
    }

    public void addChild(Node node) {
        if (children.size() >= 9) {
            System.out.println("child limit reached");
            return;
        }
        children.add(node);
    }

    public int getChildNum() {
        return this.children.size();
    }

    public boolean isFull() {
        boolean isFull = false;
        if (getChildNum() >= 10) {
            isFull = true;
        }

        return isFull;
    }
}