package TIcTacToe.strategies.botPlayingStrategies;

import TIcTacToe.models.Board;
import TIcTacToe.models.Move;

public interface BotPlayingStrategy {
    Move makeMove(Board board);
}
