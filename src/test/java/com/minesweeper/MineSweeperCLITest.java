package com.minesweeper;

import com.minesweeper.game.GameConfig;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MineSweeperCLITest {

    private static final int GRID_SIZE = 4;

    private Scanner createScanner(String input) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        return new Scanner(inputStream);
    }

    @Test
    void shouldTestGetGridSizeFromPlayerSmallSizeThenValid() {
        String input = "1\n5\n";
        Scanner scanner = createScanner(input);
        int result = MineSweeperCLI.getGridSizeFromPlayer(scanner);
        assertEquals(5, result);
    }

    @Test
    void shouldTestGetMinesCountFromPlayerForExceedingThresholdThenValid() {
        String input = "10\n5\n";
        Scanner scanner = createScanner(input);
        int allowedMines = (int) (GRID_SIZE * GRID_SIZE * (GameConfig.TOTAL_MINES_ALLOWED_PERCENT / 100.0));
        int result = MineSweeperCLI.getMinesCountFromPlayer(scanner, 4);
        assertEquals(5, result);
        assertTrue(result <= allowedMines);
    }

    @Test
    void shouldTestOptExitOnUpperCase() {
        String input = "\nEXIT\n";
        Scanner scanner = createScanner(input);
        assertTrue(MineSweeperCLI.optExit(scanner));
    }

    @Test
    void shouldTestOptExitOnContinueGame() {
        String input = "\nplay\n";
        Scanner scanner = createScanner(input);
        assertFalse(MineSweeperCLI.optExit(scanner));
    }
}