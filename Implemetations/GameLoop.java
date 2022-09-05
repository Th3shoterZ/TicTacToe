package Implemetations;

import java.util.Scanner;

import Interfaces.Board;
import Interfaces.IGameLoop;

public class GameLoop implements IGameLoop {

    @Override
    public void MainGameLoop() {
        Board board = new TicTacToeBoard();
        Scanner input = new Scanner(System.in);
        boolean reset = false;
        boolean playAi = false;

        // decide AI
        System.out.println("Do you waynt to play AI? (y/n)");
        if (input.next().equals("y")) {
            playAi = true;
        }

        board.InitBoard();
        System.out.println(" --------- new board ------------- " + System.lineSeparator());
        while (true) {
            reset = false;
            System.out.println(board.AsString());
            System.out.println("Please enter a move: [x,y]");
            var move = input.next();
            if (move.equals("exit")) {
                break;
            } else if (board.ValidateMove(move)) {
                if (board.MakeMove(move)) {
                    if (Win_Reset(board, input)) {
                        reset = true;
                        // decide AI
                        System.out.println("Do you want to play AI? (y/n)");
                        if (input.next().equals("y")) {
                            playAi = true;
                        }
                    } else {
                        break;
                    }
                }
                System.out.println(System.lineSeparator());

                // #region AI Move start
                if (!reset && playAi) {
                    if (board.MakeAiMove()) {
                        if (!Win_Reset(board, input)) {
                            break;
                        }
                    }
                }
                // #region AI Move end
            } else {
                System.err.println("invalid input/move, try again");
                System.out.println(System.lineSeparator());
            }
        }
        System.out.println("------- exiting -------");
        input.close();
    }

    @Override
    public boolean Win_Reset(Board board, Scanner input) {
        System.out.println(board.AsString());
        System.out.println(board.GetWinner() + " has won the game!" + System.lineSeparator());
        while (true) {
            System.out.println("Do you want to play again? (y/n)");
            String answer = input.next();
            if (answer.equals("y") || answer.equals("n")) {
                if (answer.equals("y")) {
                    board.InitBoard();
                    System.out.println(System.lineSeparator() + " --------- new board ------------- ");
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

}
