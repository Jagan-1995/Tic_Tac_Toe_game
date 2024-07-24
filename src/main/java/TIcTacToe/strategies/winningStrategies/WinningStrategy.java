package TIcTacToe.strategies.winningStrategies;

import TIcTacToe.models.Board;
import TIcTacToe.models.Move;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);
}
