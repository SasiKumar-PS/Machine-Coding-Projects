package Model;


import Strategy.WinningStrategy;
import exception.InvalidInputException;
import exception.SymbolAlreadyExistException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameState gameState;
    private Player currentPlayer;
    private int currentPlayerIndex;
    private List<WinningStrategy> winningStrategies;
    private Player winner;

    private Game(Board board, List<Player> players, List<WinningStrategy> winningStrategies, int startingPlayer) {
        this.board = board;
        this.players = players;
        this.winningStrategies = winningStrategies;
        this.currentPlayer = players.get(startingPlayer);
        this.currentPlayerIndex = startingPlayer;
        this.moves = new ArrayList<>();
        this.gameState = GameState.INPROGRESS;
    }

    public Game() {}

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void makeMove() throws InvalidInputException {
        Move move = currentPlayer.makeMove(board);
        moves.add(move);
        checkWin(move.getCell().getRowIndex(), move.getCell().getColumnIndex(), currentPlayer);
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        currentPlayer = players.get(currentPlayerIndex);
    }

    private void checkWin(int row, int column, Player currentPlayer) {
        for(WinningStrategy winningStrategy : winningStrategies) {
            if(winningStrategy.checkWin(board, row, column, currentPlayer)) {
                winner = currentPlayer;
                gameState = GameState.ENDED;
            }
        }

        if(moves.size() == board.getDimension() * board.getDimension()) {
            gameState = GameState.DRAW;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Board board;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;
        private int startingPlayer;

        public Builder setBoard(Board board) {
            this.board = board;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder setStartingPlayer(int startingPlayer) {
            this.startingPlayer = startingPlayer;
            return this;
        }

        public Game build() {
            validatePlayers();
            return new Game(board, players, winningStrategies, startingPlayer);
        }

        private void validatePlayers() {
            Set<String> symbols = new HashSet<>();

            for(Player player : players) {
                String symbol = player.getSymbol().getSymbol();
                if(symbols.contains(symbol)){
                    throw new SymbolAlreadyExistException("Symbol " + symbol + " is already exist, Failed to initiate the game");
                }
                else {
                    symbols.add(symbol);
                }
            }
        }
    }
}
