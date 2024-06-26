package Strategy;

import Model.Board;
import Model.Player;
import Model.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy {
    private static final Map<Integer, Map<Symbol, Integer>> rowEntry = new HashMap<>();
    @Override
    public boolean checkWin(Board board, int row, int column, Player currentPlayer) {
        if( !rowEntry.containsKey(row)) {
            rowEntry.put(row, new HashMap<>());
        }
        rowEntry.get(row).put(currentPlayer.getSymbol(), rowEntry.get(row).getOrDefault(currentPlayer.getSymbol(), 0)+1);

        return rowEntry.get(row).get(currentPlayer.getSymbol()) == board.getDimension();
    }
}
