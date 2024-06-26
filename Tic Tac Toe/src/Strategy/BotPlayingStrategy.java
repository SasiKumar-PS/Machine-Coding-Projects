package Strategy;

import Model.Board;
import Model.Move;
import Model.Player;

public interface BotPlayingStrategy {
    Move makeMove(Player currentPlayer, Board board);
}
