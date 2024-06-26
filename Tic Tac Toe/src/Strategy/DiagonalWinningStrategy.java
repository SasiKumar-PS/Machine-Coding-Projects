package Strategy;

import Model.Board;
import Model.Player;
import Model.Symbol;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy{
    Map<Symbol, Integer> leftDiagonal = new HashMap<>();
    Map<Symbol, Integer> rightDiagonal = new HashMap<>();

    @Override
    public boolean checkWin(Board board, int row, int column, Player currentPlayer) {
        Symbol symbol = currentPlayer.getSymbol();
        if(row == column) {
            leftDiagonal.put(symbol, leftDiagonal.getOrDefault(symbol,0)+1);
            if(leftDiagonal.get(symbol) == board.getDimension()) {
                return true;
            }
        }
        if(row + column == board.getDimension()-1) {
            rightDiagonal.put(symbol, rightDiagonal.getOrDefault(symbol,0)+1);
            if(rightDiagonal.get(symbol) == board.getDimension()) {
                return true;
            }
        }
        return false;
    }
}
