package com.minesweeper.model;

/**
 * Position class represents grid coordinates, mine placement and square selection
 */

public record Position(int gridRow, int gridCol) {
    public static Position parseInput(String input) {
        if (input == null || input.length() < 2) return null;

        try {
            int row = input.charAt(0) - 'A';
            int col = Integer.parseInt(input.substring(1)) - 1;

            if (row < 0 || col < 0) return null;

            return new Position(row, col);
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}
