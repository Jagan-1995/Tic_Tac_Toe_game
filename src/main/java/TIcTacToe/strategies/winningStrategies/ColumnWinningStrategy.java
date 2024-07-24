package TIcTacToe.strategies.winningStrategies;

import TIcTacToe.models.*;

import java.util.HashMap;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategy{
    private Map<Integer, Map<Symbol, Integer>> colHashmaps = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        Cell cell = move.getCell();
        int col = cell.getCol();

        Player player = move.getPlayer();
        Symbol symbol = player.getSymbol();

        if (!colHashmaps.containsKey(col)){
            colHashmaps.put(col, new HashMap<Symbol, Integer>());
        }

        Map<Symbol, Integer> hm = colHashmaps.get(col);

        if (!hm.containsKey(symbol)){
            hm.put(symbol, 0);
        }

        hm.put(symbol, hm.get(symbol) + 1);

        int count = hm.get(symbol);
        return count == (board.getDimension());
    }
}
