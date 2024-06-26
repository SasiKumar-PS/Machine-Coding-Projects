package Strategy;

import Model.*;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move makeMove(Player currentPlayer, Board board) {
        Move move = null;
        for(Cell[] cells : board.getCells()) {
            for(Cell cell : cells) {
                if(cell.getCellState().equals(CellState.EMPTY)){
                    cell.setCellState(CellState.FILLED);
                    cell.setPlayer(currentPlayer);
                    move = new Move(cell, currentPlayer);
                    break;
                }
            }
            if(move != null) break;
        }
        System.out.println(currentPlayer.getName() + ": I made the move!");
        return move;
    }
}
