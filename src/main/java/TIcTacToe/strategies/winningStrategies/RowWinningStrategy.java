package TIcTacToe.strategies.winningStrategies;

import TIcTacToe.models.*;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy{
    private Map<Integer, Map<Symbol, Integer>> rowHashmaps = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {

        Cell cell = move.getCell();
        int row = cell.getRow();

        Player player = move.getPlayer();
        Symbol symbol = player.getSymbol();

        if (!rowHashmaps.containsKey(row)){
            rowHashmaps.put(row, new HashMap<Symbol, Integer>());
        }

        Map<Symbol, Integer> hm = rowHashmaps.get(row);

        if (!hm.containsKey(symbol)){
            hm.put(symbol, 0);
        }

        hm.put(symbol, hm.get(symbol) + 1);

        int count = hm.get(symbol);
        return count == (board.getDimension());
    }
}


/*
[            ] -> (0, hm1)
[ X , X , ...    X      ] -> (1, hm2) -> {X, 3}, {O , 0}
[            ] -> (2, hm3) - 'X'

(1, 2) -> (1, hm)
In every hashmap, are we storing the count of every symbol??
HashMap<Symbol, Integer> hm -> N

 */