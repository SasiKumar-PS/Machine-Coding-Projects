package Controller;

import Model.*;
import Strategy.ColumnWinningStrategy;
import Strategy.DiagonalWinningStrategy;
import Strategy.RowWinningStrategy;
import Strategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameController {
    private Game game;
    public static final Scanner scanner = new Scanner(System.in);

    public GameController(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    // start game
    public void startGame(){
        Board board = createBoard();
        List<Player> players = addPlayers(board.getDimension()-1);
        List<WinningStrategy> winningStrategies = addWinningStrategies();
        int startingPlayer = getStartingPlayer(board.getDimension()-2);

        this.game = Game.builder()
                .setBoard(board)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .setStartingPlayer(startingPlayer)
                .build();

        playGame();
    }

    // create board
    public Board createBoard() {
        System.out.println("provide the board size");
        int boardDimension = scanner.nextInt();
        return new Board(boardDimension);
    }

    // add players
    private List<Player> addPlayers(int playersCount) {
        System.out.println("Provide " + playersCount + " players details");
        List<Player> players = new ArrayList<>();

        for(int i=0; i<playersCount; i++) {
            System.out.println("provide player no: " + i + "'s name");
            String playerName = scanner.next();

            System.out.println("provide player no: " + i + "'s Symbol");
            String playerSymbol = scanner.next();

            System.out.println("provide player no: " + i + "'s Player Type");
            PlayerType playerType = PlayerType.valueOf(scanner.next().toUpperCase());

            if(playerType.equals(PlayerType.BOT)) {
                System.out.println("provide Bot player's Difficulty Type");
                BotDifficultyLevel botDifficultyLevel = BotDifficultyLevel.valueOf(scanner.next().toUpperCase());
                BotPlayer botPlayer = new BotPlayer(i, playerName, playerType, new Symbol(playerSymbol), botDifficultyLevel);
                players.add(botPlayer);
            }
            else {
                HumanPlayer humanPlayer = new HumanPlayer(i, playerName, playerType, new Symbol(playerSymbol));
                players.add(humanPlayer);
            }
        }

        return players;
    }

    // add winning strategy
    private List<WinningStrategy> addWinningStrategies() {
        List<WinningStrategy> winningStrategies = new ArrayList<>();

        System.out.println("Provide 'Y' if you want Row-wise winning");
        String response1 = scanner.next().toUpperCase();
        if(response1.equals("Y")) {
            winningStrategies.add(new RowWinningStrategy());
        }

        System.out.println("Provide 'Y' if you want Column-wise winning");
        String response2 = scanner.next().toUpperCase();
        if(response2.equals("Y")) {
            winningStrategies.add(new ColumnWinningStrategy());
        }

        System.out.println("Provide 'Y' if you want Diagonal-wise winning");
        String response3 = scanner.next().toUpperCase();
        if(response3.equals("Y")) {
            winningStrategies.add(new DiagonalWinningStrategy());
        }

        return winningStrategies;
    }

    private int getStartingPlayer(int limit) {
        System.out.println("Provide the starting player Index to start the Game!");
        int startingPlayer = -1;
        while( startingPlayer < 0 || startingPlayer > limit) {
            System.out.println("Valid Index range is 0 to " + limit);
            startingPlayer = scanner.nextInt();
        }
        return startingPlayer;
    }

    private void playGame() {
        while(game.getGameState().equals(GameState.INPROGRESS)) {
            printBoard();
            try {
                game.makeMove();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        if(game.getGameState().equals(GameState.ENDED)) {
            printBoard();
            System.out.println(game.getWinner().getName() + " has Won the Game");
        }
        else {
            printBoard();
            System.out.println("Game is Draw!");
        }
    }

    //print Board
    private void printBoard() {
        Board board = game.getBoard();

        System.out.println();
        for(Cell[] cells : board.getCells()) {
            for(Cell cell : cells) {
                System.out.print('|');
                if(cell.getCellState().equals(CellState.EMPTY)) {
                    System.out.print(" ");
                }
                else {
                    System.out.print(cell.getPlayer().getSymbol().getSymbol());
                }
            }
            System.out.print('|');
            System.out.println();
        }
        System.out.println();
    }

}
