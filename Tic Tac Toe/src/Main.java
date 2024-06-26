import Controller.GameController;
import Model.Game;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");

        Game game = new Game();
        GameController gameController = new GameController(game);

        gameController.startGame();
    }
}