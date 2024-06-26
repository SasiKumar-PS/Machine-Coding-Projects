package Model;


import Controller.GameController;
import exception.InvalidInputException;

import java.util.Scanner;

public class HumanPlayer extends Player{
    public HumanPlayer(int id, String name, PlayerType playerType, Symbol symbol) {
        super(id, name, playerType, symbol);
    }

    @Override
    public Move makeMove(Board board) throws InvalidInputException {
        Scanner scanner = GameController.scanner;   //Reusing the scanner object from GameController
        System.out.println("Its " + this.getName() + "'s turn!");
        System.out.println("Choose the row index to place your symbol");
        int row = scanner.nextInt();
        if(row < 0 || row >= board.getDimension()) {
            throw new InvalidInputException("Provided row value is invalid, Please try again!");
        }

        System.out.println("Choose the column index to place your symbol");
        int column = scanner.nextInt();
        if(column < 0 || column >= board.getDimension()) {
            throw new InvalidInputException("Provided column value is invalid, Please try again!");
        }

        if(board.getCells()[row][column].getCellState().equals(CellState.FILLED)) {
            throw new InvalidInputException("Provided cell is already Occupied, Please try again!");
        }

        board.getCells()[row][column].setPlayer(this);
        board.getCells()[row][column].setCellState(CellState.FILLED);

        return new Move(board.getCells()[row][column], this);
    }
}
