package com.minesweeper.game;

import com.minesweeper.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameTest {

    private static final int GRID_SIZE = 4;
    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game(GRID_SIZE);
    }

    @Test
    void shouldTestGameInitializationWithMinesPlacement() {
        game.initializeGame(
                Set.of(
                        new Position(0, 0),
                        new Position(1, 1)
                )
        );

        assertTrue(game.getGrid().isMine(0, 0));
        assertTrue(game.getGrid().isMine(1, 1));
        assertEquals(GameStatus.IN_PROGRESS, game.getGameStatus());
    }

    @Test
    void shouldWinGameWhenPlayerUncoversAllSafeSquaresOnGrid() {
        game.initializeGame(Set.of(new Position(0, 0)));

        //Uncover all safe squares
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (i != 0 || j != 0)
                    game.processPlayerMove(i, j);
            }
        }

        assertEquals(GameStatus.GAME_WON, game.getGameStatus());
    }

    @Test
    void shouldLoseGameWhenPlayerSelectSquareWithMine() {
        game.initializeGame(Set.of(new Position(1, 1)));

        game.processPlayerMove(1, 1);

        assertEquals(GameStatus.GAME_LOST, game.getGameStatus());
    }

    @Test
    void shouldNotProcessPlayerMoveGivenSquareIsAlreadyUncovered() {
        game.initializeGame(Set.of(new Position(0, 0)));

        game.processPlayerMove(1, 1);
        assertTrue(game.getGrid().isUncovered(1, 1));

        game.processPlayerMove(1, 1);
        assertEquals(GameStatus.IN_PROGRESS, game.getGameStatus());
    }
}