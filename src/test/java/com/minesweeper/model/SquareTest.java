package com.minesweeper.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SquareTest {

    private Square square;

    @BeforeEach
    void setup() {
        square = new Square();
    }

    @Test
    void shouldTestIfSquareIsMine() {
        square.setMine(true);
        assertTrue(square.isMine());
    }

    @Test
    void shouldTestSquareIsInUncoveredState() {
        square.setUncovered(true);
        assertTrue(square.isUncovered());
    }

    @Test
    void shouldTestIfSquareHasAdjacentMines() {
        square.setAdjacentMines(5);
        assertEquals(5, square.getAdjacentMines());
    }
}