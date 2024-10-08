package TIcTacToe.models;

import TIcTacToe.exceptions.InvalidBotCountException;
import TIcTacToe.exceptions.InvalidMoveException;
import TIcTacToe.exceptions.InvalidPlayerCountException;
import TIcTacToe.strategies.winningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private Player winner;
    private GameState gameState;
    private int nextPlayerMoveIndex;
    private List<WinningStrategy> winningStrategies;


    public static Builder getBuilder(){
        return new Builder();
    }

    private boolean validateMove(Move move){
//        Player player = move.getPlayer();
        Cell cell = move.getCell();
        int row = cell.getRow();
        int col = cell.getCol();

        if (row < 0 || col < 0 || row >= board.getDimension() || col >= board.getDimension()){
            return false;
        }
        // validate if the cell is not empty
        if (board.getBoard().get(row).get(col).getCellState().equals(CellState.FILLED)){
            return false;
        }

        return true;

    }

    public void makeMove() throws InvalidMoveException {
        Player currentPlayer = players.get(nextPlayerMoveIndex);
        System.out.println("It is "+ currentPlayer.getName() + "'s turn");

        // Ask the player to choose the move

        Move move = currentPlayer.makeMove(board);

        // Before executing the move, first validate that if the cell is empty or not

        if (!validateMove(move)){
            throw new InvalidMoveException("Invalid Move, please try again");
        }

        // Valid move, let's execute on board
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        Cell cell = board.getBoard().get(row).get(col);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(currentPlayer);

        Move finalMove = new Move(currentPlayer, cell);
        moves.add(finalMove);

        // [p1, p2, p3, p4] -  list <player>
        // index = 0, 1, 2, 3, 0, 1, 2, 3,...
        nextPlayerMoveIndex = (nextPlayerMoveIndex + 1) % players.size();

        // Check if it is the winning move
        if (checkWinner(finalMove)){
            gameState = GameState.ENDED;
            winner = currentPlayer;
        } else if (moves.size() == board.getDimension() * board.getDimension()) {
            // Game has Drawn
            gameState = GameState.DRAW;
        }
    }

    private boolean checkWinner(Move move){
        // Check row , col and diagonal (if applicable)
        for (WinningStrategy winningStrategy : winningStrategies){
            if (winningStrategy.checkWinner(board, move)){
                return true;
            }
        }
        return false;
    }

    public void undo(Game game){
        /*
        remove the last move from the list and corresponding to that you have to make that
        cell empty in that board and also go to the previous player.
         */
    }

    public void printBoard(){
        board.print();
    }

    private Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies){
        this.players = players;
        this.winningStrategies = winningStrategies;
        this.board = new Board(dimension);
        this.moves = new ArrayList<>();
        this.gameState = GameState.IN_PROGRESS;
        this.nextPlayerMoveIndex = 0;
    }


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

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getNextPlayerMoveIndex() {
        return nextPlayerMoveIndex;
    }

    public void setNextPlayerMoveIndex(int nextPlayerMoveIndex) {
        this.nextPlayerMoveIndex = nextPlayerMoveIndex;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public static class Builder{
        private int dimension;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;

        // Game.getBuilder().setDimension().setplayers().setWinningStrategies().build();
        public Builder setDimension(int dimension) {
            this.dimension = dimension;
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

        private void validateBotCount() throws InvalidBotCountException{
            int botCount = 0;
            for (Player player : players){
                if (player.getPlayerType().equals(PlayerType.BOT)){
                    botCount++;
                }
            }
            if(botCount > 1){
                throw new InvalidBotCountException("Count of Bots are greater than 1");
            }
        }

        private void validatePlayerCount() throws InvalidPlayerCountException{
            if (players.size() != dimension - 1){
                throw new InvalidPlayerCountException("Number of players should be 1 less than the dimension");
            }
        }

        private void validateUniquePlayerSymbols(){

        }

        private void validate() throws InvalidBotCountException, InvalidPlayerCountException{
            validateBotCount();
            validatePlayerCount();
            validateUniquePlayerSymbols();

        }
        public Game build() throws InvalidPlayerCountException, InvalidBotCountException{
            // Before building the game, we should first validate the game object

            validate();
            return new Game(
                    dimension,
                    players,
                    winningStrategies
            );
        }
    }
}
