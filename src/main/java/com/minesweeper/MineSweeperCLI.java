package com.minesweeper;

import com.minesweeper.game.Game;
import com.minesweeper.game.GameConfig;
import com.minesweeper.handler.MineSweeperHandler;

import java.util.Scanner;

/**
 * The MineSweeperCLI class serves as the entry point for the Minesweeper game.
 * It handles the user interface and game flow, including getting input from the player
 * for grid size and number of mines, starting the game, and managing the game loop.
 */

public class MineSweeperCLI {
    public static void main(String[] args) {
        System.out.println("Welcome to Minesweeper!\n");

        while (true) {
            Scanner scanner = new Scanner(System.in);

            // Get Grid Size from Player
            int gridSize = getGridSizeFromPlayer(scanner);

            // Get Mines Count from Player
            int minesCount = getMinesCountFromPlayer(scanner, gridSize);

            // Start game
            startGame(gridSize, minesCount);

            // Exit the game if asked to do so
            if (optExit(scanner)) {
                System.out.println("Exiting the game!!!");
                break;
            }
        }
    }

    private static void startGame(int gridSize, int minesCount) {
        Game game = new Game(gridSize);
        MineSweeperHandler mineSweeperHandler = new MineSweeperHandler(game);
        mineSweeperHandler.start(gridSize, minesCount);
    }

    static int getGridSizeFromPlayer(Scanner scanner) {
        System.out.println("Enter the size of the grid (e.g. 4 for a 4x4 grid): ");
        int gridSize = Integer.parseInt(scanner.nextLine());

        while (gridSize < GameConfig.MIN_GRID_SIZE || gridSize > GameConfig.MAX_GRID_SIZE) {
            System.out.println("Grid size must be greater than 2 and less than 26! ");
            System.out.println("Enter the size of the grid (e.g. 4 for a 4x4 grid): ");
            gridSize = scanner.nextInt();
        }

        return gridSize;
    }

    static int getMinesCountFromPlayer(Scanner scanner, int gridSize) {
        int allowedMinesCount = (int) ((gridSize * gridSize) * (GameConfig.TOTAL_MINES_ALLOWED_PERCENT / 100.0));
        System.out.println("Enter the number of mines to place on the grid (Maximum allowed mines : " + allowedMinesCount + ")");
        int minesCount = scanner.nextInt();

        while (!(isMinesCountWithinThreshold(minesCount, allowedMinesCount))) {
            System.out.println("Mines allowed are 35% of the total squares, Enter the number of mines again:");
            minesCount = scanner.nextInt();
        }

        return minesCount;
    }

    static boolean optExit(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Press any key to play again or type exit to stop...");
        String key = scanner.nextLine();
        return "exit".equalsIgnoreCase(key);
    }

    private static boolean isMinesCountWithinThreshold(int minesCount, int allowedMinesCount) {
        return minesCount > 0 && minesCount <= allowedMinesCount;
    }
}
