package Strategy;

import Model.Board;
import Model.Player;

public interface WinningStrategy {
    boolean checkWin(Board board, int row, int column, Player currentPlayer);
}
