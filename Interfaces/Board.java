package Interfaces;

public abstract class Board implements IAsString {
    String[][] board = new String[3][3];
    boolean isX = true;
    String winner = null;

    public abstract void InitBoard();

    public abstract boolean ValidateMove(String move);

    // returns true when it's a winning move
    public abstract boolean MakeMove(String move);

    public abstract boolean CheckVictory(int x, int y);

    public abstract boolean MakeAiMove();

    public abstract String GetWinner();
}