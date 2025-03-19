package com.minesweeper.handler;

import com.minesweeper.game.Game;
import com.minesweeper.game.GameStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MineSweeperHandlerTest {
    private static final int GRID_SIZE = 4;
    private static final int MINES_COUNT = 3;
    private Game game;

    @BeforeEach
    void setup() {
        game = new Game(GRID_SIZE);
    }

    @Test
    void shouldStartGameAndProcessValidMovesFromPlayer() {
        MineSweeperHandler mineSweeperHandler = new MineSweeperHandler(game) {
            int moveCount = 0;
            String[] moves = {
                    "A1", "A2", "A3", "A4",
                    "B1", "B2", "B3", "B4",
                    "C1", "C2", "C3", "C4",
                    "D1", "D2", "D3", "D4"
            };

            @Override
            protected String getPlayerMove() {
                if (moveCount < moves.length && game.getGameStatus() == GameStatus.IN_PROGRESS) {
                    return moves[moveCount++];
                }
                return moves[0];
            }
        };

        mineSweeperHandler.start(GRID_SIZE, MINES_COUNT);
        assertTrue(game.getGameStatus() == GameStatus.GAME_LOST ||
                        game.getGameStatus() == GameStatus.GAME_WON,
                "Game should have been terminated!");
    }
}