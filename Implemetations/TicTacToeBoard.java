package Implemetations;

import java.util.Random;

import Interfaces.Board;

public class TicTacToeBoard extends Board {
    String[][] board = new String[3][3];
    boolean isX = true;
    public String winner = null;

    public TicTacToeBoard(String[][] board, boolean isX, String winner) {
        this.board = board;
        this.isX = isX;
        this.winner = winner;
    }

    public TicTacToeBoard() {
    }

    public void InitBoard() {
        isX = true;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = null;
            }
        }
    }

    public String AsString() {
        String stringedBoard = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                stringedBoard += board[i][j] == null ? "-" : board[i][j];
                // ignore last index
                if (j != board[i].length - 1) {
                    stringedBoard += " | ";
                }
            }
            stringedBoard += System.lineSeparator();
            // ignore last index
            if (i != board[i].length - 1) {
                stringedBoard += "---------";
                stringedBoard += System.lineSeparator();
            }
        }
        return stringedBoard;
    }

    public boolean ValidateMove(String move) {
        var position = move.split(",");
        if (position.length != 2)
            return false;
        var y = Integer.parseInt(position[0]);
        var x = Integer.parseInt(position[1]);
        if ((x > 0 && x <= 3 && y > 0 && y <= 3)) {
            if (board[x - 1][y - 1] == null) {
                return true;
            }
            return false;
        }
        return false;
    }

    // returns true when it's a winning move
    public boolean MakeMove(String move) {
        var position = move.split(",");
        var y = Integer.parseInt(position[0]);
        var x = Integer.parseInt(position[1]);
        if (isX == true) {
            board[x - 1][y - 1] = "X";
            isX = false;
        } else {
            board[x - 1][y - 1] = "O";
            isX = true;
        }
        if (CheckVictory(x - 1, y - 1)) {
            return true;
        }
        return false;
    }

    public boolean CheckVictory(int x, int y) {
        int n = 3;
        String player = isX == true ? "O" : "X";
        // check row
        for (int i = 0; i < n; i++) {
            if (board[i][y] != player)
                break;
            if (i == n - 1) {
                // report win for player
                winner = player;
                return true;
            }
        }

        // check diag
        if (x == y) {
            // we're on a diagonal
            for (int i = 0; i < n; i++) {
                if (board[i][i] != player)
                    break;
                if (i == n - 1) {
                    // report win for player
                    winner = player;
                    return true;
                }
            }
        }

        // check anti diag (thanks rampion)
        if (x + y == n - 1) {
            for (int i = 0; i < n; i++) {
                if (board[i][(n - 1) - i] != player)
                    break;
                if (i == n - 1) {
                    // report win for player
                    winner = player;
                    return true;
                }
            }
        }

        // check draw
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == null) {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean MakeAiMove() {
        Random random = new Random();
        int x = 0;
        int y = 0;
        boolean valid = false;
        while (!valid) {
            x = random.nextInt(3 - 1) + 1;
            y = random.nextInt(3 - 1) + 1;
            valid = ValidateMove(new String(x + "," + y));
        }
        System.out.println(new String(x + "," + y));
        return MakeMove(new String(x + "," + y));
    }

    public String GetWinner() {
        return winner;
    }
}