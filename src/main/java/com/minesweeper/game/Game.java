package com.minesweeper.game;

import com.minesweeper.model.Grid;
import com.minesweeper.model.Position;

import java.util.Set;

/**
 * The Game class orchestrates the Minesweeper game state, tracks win/lose conditions, and manages grid operations.
 * It initializes the game grid, processes player moves, and checks for game win conditions.
 */

public class Game {
    private final Grid grid;
    private int minesCount;
    private GameStatus gameStatus;

    public Game(int gridSize) {
        this.grid = new Grid(gridSize);
    }

    public void initializeGame(Set<Position> minePositions) {
        grid.placeMinesOnGrid(minePositions);
        this.minesCount = minePositions.size();
        this.gameStatus = GameStatus.IN_PROGRESS;
    }

    /**
     * Processes the player's move and updates the game state based on conditions
     * @param row the row number of the selected square on the grid
     * @param col the column number of the selected square on the grid
     */
    public void processPlayerMove(int row, int col) {
        if (gameStatus == GameStatus.GAME_LOST || grid.isUncovered(row, col)) return;

        if (grid.isMine(row, col)) {
            gameStatus = GameStatus.GAME_LOST;
            return;
        }

        grid.uncoverSquares(row, col);
        checkGameWinCondition();
    }

    private void checkGameWinCondition() {
        int uncoveredSafeSquares = 0;
        int totalSafeSquares = (grid.getGridSize() * grid.getGridSize()) - minesCount;

        for (int i = 0; i < grid.getGridSize(); i++) {
            for (int j = 0; j < grid.getGridSize(); j++) {
                if (!grid.isMine(i, j) && grid.isUncovered(i, j))
                    uncoveredSafeSquares++;
            }
        }

        if (uncoveredSafeSquares == totalSafeSquares) gameStatus = GameStatus.GAME_WON;
    }

    public Grid getGrid() {
        return grid;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }
}