package ticTacToe;

import java.util.List;
import java.util.LinkedList;

public class Board {

    int score;
    Character[][] hash = new Character[3][3];
    List<int[]> empty = new LinkedList<>();

    public Board() {
        int[] cur;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                hash[i][j] = null;
                cur = new int[2];
                cur[0] = i;
                cur[1] = j;
                empty.add(cur);
            }
        }
    }

    public Board(Board other) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.hash[i][j] = other.hash[i][j];
            }
        }
        this.empty = new LinkedList<>();
        for (int[] pos : other.empty) {
            int[] newPos = { pos[0], pos[1] };
            this.empty.add(newPos);
        }
    }

    public boolean endCheck() {
        if (empty.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public int setScore() {

        if (rowCheck(0) || rowCheck(1) || rowCheck(2) || colCheck(0) || colCheck(1) || colCheck(2) || diaCheck()) {
            score = 10;
        } else if (oppRowCheck(0) || oppRowCheck(1) || oppRowCheck(2) || oppColCheck(0) || oppColCheck(1)
                || oppColCheck(2) || oppDiaCheck()) {
            score = -10;
        } else {
            score = 0;
        }
        return score;
    }

    public boolean diaCheck() {
        // Check main diagonal (top-left to bottom-right)
        if (hash[0][0] != null && hash[1][1] != null && hash[2][2] != null) {
            if (hash[0][0] == 'x' && hash[1][1] == 'x' && hash[2][2] == 'x') {
                return true;
            }
        }
        
        // Check anti-diagonal (top-right to bottom-left)
        if (hash[0][2] != null && hash[1][1] != null && hash[2][0] != null) {
            if (hash[0][2] == 'x' && hash[1][1] == 'x' && hash[2][0] == 'x') {
                return true;
            }
        }
        
        return false;
    }

    public boolean rowCheck(int row) {
        if (hash[row][0] == null || hash[row][1] == null || hash[row][2] == null) {
            return false;
        }
        return (hash[row][0] == 'x' && hash[row][1] == 'x' && hash[row][2] == 'x');
    }

    public boolean colCheck(int col) {
        if (hash[0][col] == null || hash[1][col] == null || hash[2][col] == null) {
            return false;
        }
        return (hash[0][col] == 'x' && hash[1][col] == 'x' && hash[2][col] == 'x');
    }

    public boolean oppDiaCheck() {
        // Check main diagonal (top-left to bottom-right)
        if (hash[0][0] != null && hash[1][1] != null && hash[2][2] != null) {
            if (hash[0][0] == 'o' && hash[1][1] == 'o' && hash[2][2] == 'o') {
                return true;
            }
        }
        
        // Check anti-diagonal (top-right to bottom-left)
        if (hash[0][2] != null && hash[1][1] != null && hash[2][0] != null) {
            if (hash[0][2] == 'o' && hash[1][1] == 'o' && hash[2][0] == 'o') {
                return true;
            }
        }
        
        return false;
    }

    public boolean oppRowCheck(int row) {
        if (hash[row][0] == null || hash[row][1] == null || hash[row][2] == null) {
            return false;
        }
        return (hash[row][0] == 'o' && hash[row][1] == 'o' && hash[row][2] == 'o');
    }

    public boolean oppColCheck(int col) {
        if (hash[0][col] == null || hash[1][col] == null || hash[2][col] == null) {
            return false;
        }
        return (hash[0][col] == 'o' && hash[1][col] == 'o' && hash[2][col] == 'o');
    }

    public boolean isFull() {
        boolean isFull = true;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (hash[i][j] == null) {
                    isFull = false;
                }
            }
        }

        return isFull;
    }

    public List<int[]> emtpySpots() {
        int[] emptySpot;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (hash[i][j] == null) {
                    emptySpot = new int[2];
                    emptySpot[0] = i;
                    emptySpot[1] = j;
                    empty.add(emptySpot);
                }
            }
        }

        return empty;
    }

    public void print() {
        System.out.println(getCell(hash[0][0]) + "|" + getCell(hash[0][1]) + "|" + getCell(hash[0][2]));
        System.out.println("-----");
        System.out.println(getCell(hash[1][0]) + "|" + getCell(hash[1][1]) + "|" + getCell(hash[1][2]));
        System.out.println("-----");
        System.out.println(getCell(hash[2][0]) + "|" + getCell(hash[2][1]) + "|" + getCell(hash[2][2]));
    }
    
    // Helper method to display cell value
    private String getCell(Character cell) {
        return (cell == null) ? " " : cell.toString();
    }
}
