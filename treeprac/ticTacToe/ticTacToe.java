package ticTacToe;

import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;

public class ticTacToe {
    Tree tree = new Tree();
    boolean turn = true;
    static boolean playerStartsFirst = true; // Track who starts first

    public static void main(String args[]) {
        Scanner mainScanner = new Scanner(System.in);
        boolean playAgain = true;
        int gameNumber = 1;
        
        while (playAgain) {
            System.out.println("\n" + "=".repeat(40));
            System.out.println("           GAME " + gameNumber);
            System.out.println("=".repeat(40));
            
            if (playerStartsFirst) {
                System.out.println("🎮 You go first this game! (O)");
            } else {
                System.out.println("🤖 Bot goes first this game! (X)");
            }
            System.out.println();
            
            ticTacToe game = new ticTacToe(playerStartsFirst);
            game.play(mainScanner); // Pass the scanner to the play method
            
            // Switch who goes first for next game
            playerStartsFirst = !playerStartsFirst;
            gameNumber++;
            
            // Ask if they want to play again
            System.out.print("\nWould you like to play another game? (y/n): ");
            mainScanner.nextLine(); // Clear any leftover newline from previous nextInt() calls
            String response = mainScanner.nextLine().trim().toLowerCase();
            playAgain = response.equals("y") || response.equals("yes");
        }
        
        System.out.println("\n🎉 Thanks for playing! See you next time!");
        mainScanner.close();
    }

    public ticTacToe(boolean playerStartsFirst) {
        Board starting = new Board();
        Node cur = new Node(starting);
        tree.root = cur;
        // Generate tree based on who goes first
        // If player starts first, first level should be 'o' moves (false)
        // If bot starts first, first level should be 'x' moves (true)
        genTree(tree.root, !playerStartsFirst);
    }

    public void play(Scanner sc) {
        Node current = tree.root;
        boolean botTurn = !playerStartsFirst; // Bot's turn is opposite of player starts first
        
        // Show initial empty board
        System.out.println("Starting board:");
        current.state.print();
        System.out.println();

        while (!gameIsOver(current.state)) {
            if (botTurn) {
                System.out.println("Bot is thinking...");
                current = getBest(current);
                System.out.println("Bot played:");
                current.state.print();
                System.out.println();

                if (gameIsOver(current.state)) {
                    break; 
                }
            } else {
                System.out.print("Your turn - Enter row (0-2): ");
                int row = sc.nextInt();
                System.out.print("Enter col (0-2): ");
                int col = sc.nextInt();
                
                // Validate input
                if (row < 0 || row > 2 || col < 0 || col > 2) {
                    System.out.println("Invalid position! Please enter 0, 1, or 2.");
                    continue;
                }

                if (current.state.hash[row][col] != null) {
                    System.out.println("That spot is already taken! Try again.");
                    continue;
                }

                // Find the corresponding child node
                Node playerChoice = null;
                for (Node child : current.children) {
                    if (child.state.hash[row][col] != null && child.state.hash[row][col] == 'o') {
                        playerChoice = child;
                        break;
                    }
                }
                
                if (playerChoice != null) {
                    current = playerChoice;
                    System.out.println("You played:");
                    current.state.print();
                    System.out.println();
                    
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
        // Don't close the scanner here since it's shared
    }    private boolean gameIsOver(Board board) {
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
