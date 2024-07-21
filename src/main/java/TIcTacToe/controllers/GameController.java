package TIcTacToe.controllers;

import TIcTacToe.exceptions.InvalidBotCountException;
import TIcTacToe.exceptions.InvalidMoveException;
import TIcTacToe.exceptions.InvalidPlayerCountException;
import TIcTacToe.models.Game;
import TIcTacToe.models.GameState;
import TIcTacToe.models.Player;
import TIcTacToe.strategies.winningStrategies.WinningStrategy;

import java.util.List;

public class GameController {

    public Game startGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategies)throws InvalidBotCountException, InvalidPlayerCountException {

        return Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .build();

    }

    public void makeMove(Game game) throws InvalidMoveException {
        game.makeMove();

    }

    public GameState getGameState(Game game){
        return game.getGameState();
    }

    public void printBoard(Game game){
        game.printBoard();
    }

    public Player getWinner(Game game){
        return game.getWinner();
    }

}
