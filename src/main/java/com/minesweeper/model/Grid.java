package com.minesweeper.model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Grid class manages the game grid and game logic.
 * <ul>
 *   <li>Places and validates mines on the grid</li>
 *   <li>Uncovers squares</li>
 *   <li>Calculates adjacent mines</li>
 *   <li>Generates grid display</li>
 * </ul>
 */

public class Grid {
    private final int gridSize;
    private final Square[][] gridSquares;

    public Grid(int gridSize) {
        this.gridSize = gridSize;
        this.gridSquares = new Square[gridSize][gridSize];
        initializeSquares();
    }

    /**
     * Initialize grid with player specified size
     */
    private void initializeSquares() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                gridSquares[i][j] = new Square();
            }
        }
    }

    /**
     * Place mines on grid and count adjacent
     * @param minePositions Set of Positions with mines
     */
    public void placeMinesOnGrid(Set<Position> minePositions) {
        for (Position minePosition : minePositions) {
            if (isValidPosition(minePosition.gridRow(), minePosition.gridCol()))
                gridSquares[minePosition.gridRow()][minePosition.gridCol()].setMine(true);
        }

        calculateAdjacentMines();
    }

    private boolean isValidPosition(int gridRow, int gridCol) {
        return gridRow >= 0 && gridRow < gridSize && gridCol >= 0 && gridCol < gridSize;
    }

    private void calculateAdjacentMines() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (!gridSquares[i][j].isMine()) {
                    gridSquares[i][j].setAdjacentMines(countAdjacentMines(i, j));
                }
            }
        }
    }

    private int countAdjacentMines(int row, int col) {
        int adjacentMines = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i == row && j == col)
                    continue;
                if (isValidPosition(i, j) && gridSquares[i][j].isMine())
                    adjacentMines++;
            }
        }

        return adjacentMines;
    }

    /**
     * Uncover Squares with Breadth First Search approach
     * If square has 0 adjacent mines, recursively uncover neighboring squares
     */
    public void uncoverSquares(int row, int col) {
        if (!isValidPosition(row, col)) return;

        Queue<Position> positionQueue = new LinkedList<>();
        positionQueue.add(new Position(row, col));

        while (!positionQueue.isEmpty()) {
            Position position = positionQueue.poll();
            uncoverSquare(position, positionQueue);
        }
    }

    private void uncoverSquare(Position position, Queue<Position> positionQueue) {
        int r = position.gridRow();
        int c = position.gridCol();

        if (!isValidPosition(r, c)) return;
        Square square = gridSquares[r][c];

        if (square.isUncovered()) return;
        square.setUncovered(true);

        if (square.getAdjacentMines() == 0) {
            addAdjacentPositions(r, c, positionQueue);
        }
    }

    private void addAdjacentPositions(int r, int c, Queue<Position> positionQueue) {
        for (int i = r - 1; i <= r + 1; i++) {
            for (int j = c - 1; j <= c + 1; j++) {
                if (i == r && j == c) continue;
                positionQueue.add(new Position(i, j));
            }
        }
    }

    /**
     * Generate grid display string based on state of each square
     */
    public String generateGridDisplayString(boolean revealMines) {
        StringBuilder gridDisplayString = new StringBuilder();
        gridDisplayString.append("Here is your minefield:").append("\n").append(" ");
        for (int i = 0; i < gridSize; i++)
            gridDisplayString.append(i + 1).append(" ");
        gridDisplayString.append("\n");

        for (int i = 0; i < gridSize; i++) {
            gridDisplayString.append((char) ('A' + i)).append(" ");
            for (int j = 0; j < gridSize; j++) {
                Square square = gridSquares[i][j];
                if (revealMines && square.isMine())
                    gridDisplayString.append("* ");
                else if (square.isUncovered())
                    gridDisplayString.append(square.getAdjacentMines()).append(" ");
                else
                    gridDisplayString.append("_ ");
            }

            gridDisplayString.append("\n");
        }

        return gridDisplayString.toString();
    }

    public int getGridSize() {
        return gridSize;
    }

    public boolean isMine(int row, int col) {
        return gridSquares[row][col].isMine();
    }

    public boolean isUncovered(int row, int col) {
        return gridSquares[row][col].isUncovered();
    }

    public int getAdjacentMines(int row, int col) {
        return gridSquares[row][col].getAdjacentMines();
    }
}
