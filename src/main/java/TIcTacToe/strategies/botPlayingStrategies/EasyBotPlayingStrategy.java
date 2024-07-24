package TIcTacToe.strategies.botPlayingStrategies;

import TIcTacToe.models.Board;
import TIcTacToe.models.Cell;
import TIcTacToe.models.CellState;
import TIcTacToe.models.Move;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move makeMove(Board board) {

        // Iterate through the board and make at the first available empty cell.
        for (List<Cell> cells : board.getBoard()){
            for (Cell cell : cells){
                if (cell.getCellState().equals(CellState.EMPTY)){
                    return new Move(
                            null,
                            cell
                    );
                }
            }
        }

        return null;
    }
}
