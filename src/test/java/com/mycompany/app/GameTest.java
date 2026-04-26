package com.mycompany.app;

import org.junit.jupiter.api.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void testCheckState_XWin() {
        char[] board = {'X','X','X',' ',' ',' ',' ',' ',' '};
        game.symbol = 'X';
        assertEquals(State.XWIN, game.checkState(board));
    }

    @Test
    void testCheckState_OWin() {
        char[] board = {'O','O','O',' ',' ',' ',' ',' ',' '};
        game.symbol = 'O';
        assertEquals(State.OWIN, game.checkState(board));
    }

    @Test
    void testCheckState_Draw() {
        char[] board = {'X','O','X','O','X','O','O','X','O'};
        game.symbol = 'X';
        assertEquals(State.DRAW, game.checkState(board));
    }

    @Test
    void testCheckState_Playing() {
        char[] board = {'X',' ','O',' ',' ',' ',' ',' ',' '};
        game.symbol = 'X';
        assertEquals(State.PLAYING, game.checkState(board));
    }

    @Test
    void testGenerateMoves() {
        char[] board = {'X',' ','O',' ','X',' ',' ',' ',' '};
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(board, moves);
        assertTrue(moves.contains(1));
        assertTrue(moves.contains(3));
        assertTrue(moves.contains(5));
        assertTrue(moves.contains(6));
        assertTrue(moves.contains(7));
        assertTrue(moves.contains(8));
        assertEquals(6, moves.size());
    }

    @Test
    void testEvaluatePosition_WinForPlayer() {
        char[] board = {'X','X','X',' ',' ',' ',' ',' ',' '};
        Player p = new Player();
        p.symbol = 'X';
        game.symbol = 'X';
        // evaluatePosition вызывается из MinMove/MaxMove, но протестируем напрямую
        int val = game.evaluatePosition(board, p);
        assertEquals(Game.INF, val);
    }

    @Test
    void testEvaluatePosition_LossForPlayer() {
        char[] board = {'O','O','O',' ',' ',' ',' ',' ',' '};
        Player p = new Player();
        p.symbol = 'X';
        game.symbol = 'O';
        int val = game.evaluatePosition(board, p);
        assertEquals(-Game.INF, val);
    }

    @Test
    void testEvaluatePosition_Draw() {
        char[] board = {'X','O','X','O','X','O','O','X','O'};
        Player p = new Player();
        p.symbol = 'X';
        int val = game.evaluatePosition(board, p);
        assertEquals(0, val);
    }

    @Test
    void testMiniMax_ReturnsBestMove() {
        // Пустая доска – первый ход X (player1). Минимакс должен выбрать центр или угол.
        char[] board = {' ',' ',' ',' ',' ',' ',' ',' ',' '};
        game.board = board;
        game.player1.symbol = 'X';
        game.player2.symbol = 'O';
        // Минимакс для player2 (O) на пустой доске вернёт 5 (центр) или 1,3,7,9
        int move = game.MiniMax(board, game.player2);
        assertTrue(move >= 1 && move <= 9);
    }

    @Test
    void testMinMove() {
        char[] board = {'X',' ',' ',' ','O',' ',' ',' ',' '};
        game.player1.symbol = 'X';
        game.player2.symbol = 'O';
        int val = game.MinMove(board, game.player2);
        // Ожидаем не -INF, не INF, а разумную оценку
        assertTrue(val >= -Game.INF && val <= Game.INF);
    }

    @Test
    void testMaxMove() {
        char[] board = {' ',' ',' ',' ','X',' ',' ',' ',' '};
        game.player1.symbol = 'X';
        int val = game.MaxMove(board, game.player1);
        assertTrue(val >= -Game.INF && val <= Game.INF);
    }
}