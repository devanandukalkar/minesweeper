package com.minesweeper.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    Position position;

    @BeforeEach
    void setup() {
        position = new Position(1, 2);
    }

    @Test
    void shouldTestPositionCreation() {
        assertEquals(1, position.gridRow());
        assertEquals(2, position.gridCol());
    }

    @Test
    void shouldTestObjectEquality() {
        Position actualPosition = new Position(1, 1);

        assertNotEquals(position, actualPosition);
    }

    @Test
    void shouldTestObjectInEquality() {
        Position actualPosition = new Position(3, 1);

        assertNotEquals(position, actualPosition);
    }

    @Test
    void shouldTestValidPlayerInputForPositionOfSquareOnGrid() {
        Position actualPosition = Position.parseInput("B3");

        assertEquals(position, actualPosition, "Player input for square position is invalid.");
    }

    @Test
    void shouldTestInvalidPlayerInputForPositionOfSquareOnGrid() {
        Position actualPosition = Position.parseInput("A2");

        assertNotEquals(position, actualPosition);
    }

    @Test
    void shouldTestInvalidPlayerInputWhenStringIsNull() {
        assertNull(Position.parseInput(null));
    }

    @Test
    void shouldTestInvalidPlayerInputWhenStringIsLessThanTwoCharacters() {
        assertNull(Position.parseInput("C"));
        assertNull(Position.parseInput("1A"));
        assertNull(Position.parseInput("A0"));
    }
}