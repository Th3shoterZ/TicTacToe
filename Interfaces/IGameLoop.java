package Interfaces;

import java.util.Scanner;

public interface IGameLoop {
    void MainGameLoop();

    boolean Win_Reset(Board board, Scanner input);
}
