package Implemetations;

import Interfaces.IGameLoop;

public class TicTacToe {
    public static void main(String[] args) {
        IGameLoop game = new GameLoop();
        game.MainGameLoop();
    }
}