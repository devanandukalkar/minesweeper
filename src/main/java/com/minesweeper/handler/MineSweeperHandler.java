package com.minesweeper.handler;

import com.minesweeper.game.Game;
import com.minesweeper.game.GameStatus;
import com.minesweeper.model.Position;

import java.util.*;

/**
 * MineSweeperHandler class is responsible for managing the flow
 * It interacts with the Game class to initialize the game, process player moves, and display the game state to the player.
 */

public class MineSweeperHandler {
    private final Game game;
    private final Scanner scanner = new Scanner(System.in);

    public MineSweeperHandler(Game game) {
        this.game = game;
    }

    public void start(int gridSize, int minesCount) {
        Set<Position> minePositions = generateMinePositions(gridSize, minesCount);
        game.initializeGame(minePositions);

        playGame();

        System.out.println(game.getGrid().generateGridDisplayString(true));
        String gameStatusMessage = game.getGameStatus() == GameStatus.GAME_WON
                ? "Congratulations, you have won the game!"
                : "Oh no, you detonated a mine! Game over.";
        System.out.println(gameStatusMessage);
    }

    private void playGame() {
        while (game.getGameStatus() == GameStatus.IN_PROGRESS) {
            System.out.println(game.getGrid().generateGridDisplayString(false));
            System.out.println("Select a square to reveal (e.g. A1):");
            String playerInput = getPlayerMove();
            Position position = Position.parseInput(playerInput);

            if (position == null || !isValidPosition(position, game.getGrid().getGridSize())) {
                System.out.println("Invalid square selected!");
                continue;
            }

            game.processPlayerMove(position.gridRow(), position.gridCol());
        }
    }

    private Set<Position> generateMinePositions(int gridSize, int minesCount) {
        List<Position> positionList = new ArrayList<>();
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                positionList.add(new Position(i, j));
            }
        }

        Collections.shuffle(positionList);
        return new HashSet<>(positionList.subList(0, minesCount));
    }

    private boolean isValidPosition(Position pos, int gridSize) {
        return pos.gridRow() >= 0 && pos.gridRow() < gridSize
                && pos.gridCol() >= 0 && pos.gridCol() < gridSize;
    }

    protected String getPlayerMove() {
        return scanner.nextLine().trim();
    }
}