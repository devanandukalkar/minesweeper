package com.minesweeper.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {
    private static final int GRID_SIZE = 4;
    private Grid grid;

    @BeforeEach
    void setUp() {
        grid = new Grid(GRID_SIZE);
    }

    @Test
    void shouldTestInitializingGrid() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                assertFalse(grid.isMine(i, j));
                assertFalse(grid.isUncovered(i, j));
                assertEquals(0, grid.getAdjacentMines(i, j));
            }
        }
    }

    @Test
    void shouldTestMinesPlacementOnGrid() {
        grid.placeMinesOnGrid(
                Set.of(
                        new Position(1, 1),
                        new Position(2, 2)
                )
        );

        assertTrue(grid.isMine(1, 1));
        assertTrue(grid.isMine(2, 2));
    }

    @Test
    void shouldTestInvalidPositionForPlacingMines() {
        grid.placeMinesOnGrid(
                Set.of(
                        new Position(-1, -1),
                        new Position(GRID_SIZE + 1, GRID_SIZE + 2)
                )
        );

        assertFalse(grid.isMine(0, 0));
    }

    @Test
    void shouldTestUncoveringSquaresOnGrid() {
        grid.placeMinesOnGrid(Set.of(new Position(2, 2)));

        grid.uncoverSquares(0, 0);

        assertTrue(grid.isUncovered(0, 0));
        assertTrue(grid.isUncovered(1, 0));
        assertTrue(grid.isUncovered(0, 1));
        assertFalse(grid.isUncovered(2, 3));
    }

    @Test
    void shouldTestGridDisplayString() {
        grid.placeMinesOnGrid(Set.of(new Position(0, 0)));

        String display = grid.generateGridDisplayString(false);

        assertTrue(display.contains(" 1 2 3 4"));
        assertTrue(display.contains("A _ _ _ _"));
        assertTrue(display.contains("B _ _ _ _"));
    }
}