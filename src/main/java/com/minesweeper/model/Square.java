package com.minesweeper.model;

/**
 * Square class represents a single square on the grid
 * It tracks mine status, uncovered state, and adjacent mine count
 */

public class Square {
    private boolean isMine;
    private boolean isUncovered;
    private int adjacentMines;

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public boolean isUncovered() {
        return isUncovered;
    }

    public void setUncovered(boolean uncovered) {
        isUncovered = uncovered;
    }

    public int getAdjacentMines() {
        return adjacentMines;
    }

    public void setAdjacentMines(int adjacentMines) {
        this.adjacentMines = adjacentMines;
    }
}
