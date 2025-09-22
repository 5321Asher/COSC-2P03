package ticTacToe;

import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;

public class ticTacToe {
    Tree tree = new Tree();
    boolean turn = true;

    public static void main(String args[]) {
        ticTacToe tic = new ticTacToe();
        tic.play();
    }

    public ticTacToe() {
        Board starting = new Board();
        Node cur = new Node(starting);
        tree.root = cur;
        genTree(tree.root, true);
        
    }

    public void play() {
        Node current = tree.root;
        Scanner sc = new Scanner(System.in);
        boolean botTurn = true; 

        while (!gameIsOver(current.state)) {
            if (botTurn) {
                
                System.out.println("Bot's turn:");
                current = getBest(current);
                current.state.print();

                if (gameIsOver(current.state)) {
                    break; 
                }
            } else {
                
                System.out.println("Your turn. Current board:");
                //current.state.print();

                Node playerChoice = getPlayerMove(current, sc);
                if (playerChoice != null) {
                    current = playerChoice;

                    if (gameIsOver(current.state)) {
                        break; 
                    }
                } else {
                    System.out.println("Invalid move! Try again.");
                    continue; 
                }
            }

            botTurn = !botTurn; 
        }

        announceWinner(current.state);
    }

    private boolean gameIsOver(Board board) {
        return board.isFull() || hasWinner(board);
    }

    private boolean hasWinner(Board board) {
        if (board.rowCheck(0) || board.rowCheck(1) || board.rowCheck(2) ||
                board.colCheck(0) || board.colCheck(1) || board.colCheck(2) ||
                board.diaCheck()) {
            return true;
        }

      
        if (board.oppRowCheck(0) || board.oppRowCheck(1) || board.oppRowCheck(2) ||
                board.oppColCheck(0) || board.oppColCheck(1) || board.oppColCheck(2) ||
                board.oppDiaCheck()) {
            return true;
        }

        return false;
    }

    private Node getPlayerMove(Node current, Scanner sc) {
        System.out.print("Enter row (0-2): ");
        int row = sc.nextInt();
        System.out.print("Enter col (0-2): ");
        int col = sc.nextInt();

   
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            return null; 
        }

        if (current.state.hash[row][col] != null) {
            return null; 
        }

        
        for (Node child : current.children) {
            if (child.state.hash[row][col] != null && child.state.hash[row][col] == 'o') {
                return child;
            }
        }

        return null; 
    }



    public void genTree(Node node, boolean Cturn) {
        if (!node.state.isFull() && !node.state.endCheck()) {
            List<int[]> emptyPos = new LinkedList<>(node.state.empty);
            for (int[] p : emptyPos) {
                Board curB = new Board(node.state);

                curB.hash[p[0]][p[1]] = Cturn ? 'x' : 'o';

                curB.empty.removeIf(pos -> pos[0] == p[0] && pos[1] == p[1]);

                Node curN = new Node(curB);

                node.children.add(curN);
            }
            for (Node c : node.children) {
                genTree(c, !Cturn);
            }
        }
    }

    public Node getBest(Node node) {
        Node best = null;
        int bestScore = Integer.MIN_VALUE;
        for (Node c : node.children) {
            int score = getBestHelper(c);
            if (score > bestScore) {
                bestScore = score;
                best = c;
            }
        }
        return best;
    }

    public Integer getBestHelper(Node node) {
        if (node == null) {
            return null;
        }

        if (node.children.isEmpty()) {
            return node.state.setScore();
        }

        int totalScore = node.state.setScore();
        for (Node c : node.children) {
            totalScore += getBestHelper(c);
        }

        return totalScore;
    }

    
    private void announceWinner(Board board) {
        System.out.println("\n" + "=".repeat(30));
        System.out.println("         GAME OVER!");
        System.out.println("=".repeat(30));
        
       
        System.out.println("Final board:");
        board.print();
        System.out.println();
        
        
        boolean xWins = board.rowCheck(0) || board.rowCheck(1) || board.rowCheck(2) ||
                       board.colCheck(0) || board.colCheck(1) || board.colCheck(2) ||
                       board.diaCheck();
        
        boolean oWins = board.oppRowCheck(0) || board.oppRowCheck(1) || board.oppRowCheck(2) ||
                       board.oppColCheck(0) || board.oppColCheck(1) || board.oppColCheck(2) ||
                       board.oppDiaCheck();
        
        if (xWins) {
            System.out.println("🤖 BOT WINS! (X)");
            System.out.println("Better luck next time!");
        } else if (oWins) {
            System.out.println("🎉 YOU WIN! (O)");
            System.out.println("Congratulations! You beat the bot!");
        } else if (board.isFull()) {
            System.out.println("🤝 IT'S A TIE!");
            System.out.println("Good game! Nobody wins this time.");
        } else {
            System.out.println("🤔 Game ended unexpectedly...");
        }
        
        System.out.println("=".repeat(30));
        System.out.println("Thanks for playing Tic-Tac-Toe!");
        System.out.println("=".repeat(30));
    }
}
