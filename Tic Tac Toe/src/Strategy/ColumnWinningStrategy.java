package Strategy;

import Model.Board;
import Model.Player;
import Model.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategy{
    private static final Map<Integer, Map<Symbol, Integer>> columnEntry = new HashMap<>();
    @Override
    public boolean checkWin(Board board, int row, int column, Player currentPlayer) {
        if( !columnEntry.containsKey(column)) {
            columnEntry.put(column, new HashMap<>());
        }
        columnEntry.get(column).put(currentPlayer.getSymbol(), columnEntry.get(column).getOrDefault(currentPlayer.getSymbol(), 0)+1);

        return columnEntry.get(column).get(currentPlayer.getSymbol()) == board.getDimension();
    }
}
