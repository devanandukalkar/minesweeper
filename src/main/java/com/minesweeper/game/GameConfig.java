package com.minesweeper.game;

/**
 * GameConfig is configuration class for Minesweeper game
 */

public class GameConfig {
    public static final int MIN_GRID_SIZE = 2;
    public static final int MAX_GRID_SIZE = 26;
    public static final int TOTAL_MINES_ALLOWED_PERCENT = 35;

    private GameConfig() {
        //prevent instantiation
    }
}
