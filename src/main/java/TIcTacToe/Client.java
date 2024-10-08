package TIcTacToe;

import TIcTacToe.controllers.GameController;
import TIcTacToe.exceptions.InvalidBotCountException;
import TIcTacToe.exceptions.InvalidMoveException;
import TIcTacToe.exceptions.InvalidPlayerCountException;
import TIcTacToe.models.*;
import TIcTacToe.strategies.winningStrategies.ColumnWinningStrategy;
import TIcTacToe.strategies.winningStrategies.DiagonalWinningStrategy;
import TIcTacToe.strategies.winningStrategies.RowWinningStrategy;
import TIcTacToe.strategies.winningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) throws InvalidBotCountException, InvalidPlayerCountException, InvalidMoveException {
//        Player p1 = new Player("Jagan", new Symbol('X'), PlayerType.HUMAN);
//
////        Game.getBuilder().setPlayers()....
//
////        Game game = new Game(2, new ArrayList<>(), new ArrayList<>());
//
//        Game game = Game.getBuilder()
//                .setDimension(3)
//                .setPlayers(new ArrayList<>())
//                .setWinningStrategies(new ArrayList<>())
//                .build();

        int dimension = 3;
        List<Player> players = new ArrayList<>();
        players.add(new Player("Jagan", new Symbol('X'), PlayerType.HUMAN));
        players.add(new Bot("JarvisBot", new Symbol('O'), BotDifficultyLevel.EASY));

        List<WinningStrategy> winningStrategies = new ArrayList<>();
        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new ColumnWinningStrategy());
        winningStrategies.add(new DiagonalWinningStrategy());

        GameController gameController = new GameController();
        Game game = gameController.startGame(
                                    dimension,
                                    players,
                                    winningStrategies
        );

//        gameController.printBoard(game);
        // while comparing objects use .equals
        while (game.getGameState().equals(GameState.IN_PROGRESS)){
            /*
            1. Print the board
            2. Ask the user to choose where to make the move.
             */
            gameController.printBoard(game);

            gameController.makeMove(game);
        }

        if (game.getGameState().equals(GameState.ENDED)){
            gameController.printBoard(game);
            System.out.println("Winner is "+ gameController.getWinner(game).getName());
        }
        else {
            System.out.println("Game is Drawn");
        }

//        Game game = GameController.startGame();

    }
}
