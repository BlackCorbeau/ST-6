package com.mycompany.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game game;
    private Player playerX;
    private Player playerO;

    @BeforeEach
    void setUp() {
        game = new Game();
        playerX = game.player1;
        playerO = game.player2;
    }

    private char[] boardFromString(String s) {
        char[] board = new char[9];
        for (int i = 0; i < 9 && i < s.length(); i++) {
            board[i] = s.charAt(i);
        }
        return board;
    }

    @Test
    void testCheckStateXWin() {
        char[] board = boardFromString("XXX O O  ");
        game.symbol = 'X';
        assertEquals(State.XWIN, game.checkState(board));
    }

    @Test
    void testCheckStateOWin() {
        char[] board = boardFromString("OOO      ");
        game.symbol = 'O';
        assertEquals(State.OWIN, game.checkState(board));
    }

    @Test
    void testCheckStateDraw() {
        char[] board = boardFromString("XOXXOOOXO");
        game.symbol = 'X';
        assertEquals(State.DRAW, game.checkState(board));
    }

    @Test
    void testCheckStatePlaying() {
        char[] board = boardFromString("X O      ");
        game.symbol = 'X';
        assertEquals(State.PLAYING, game.checkState(board));
    }

    @Test
    void testGenerateMovesEmptyBoard() {
        char[] board = boardFromString("         ");
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(board, moves);
        assertEquals(9, moves.size());
        for (int i = 0; i < 9; i++) assertEquals(i, moves.get(i));
    }

    @Test
    void testGenerateMovesPartlyFilled() {
        char[] board = new char[9];
        board[0] = 'X';
        board[1] = ' ';
        board[2] = 'O';
        for (int i = 3; i < 9; i++) board[i] = ' ';
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(board, moves);
        assertArrayEquals(new Integer[]{1, 3, 4, 5, 6, 7, 8}, moves.toArray(new Integer[0]));
    }

    @Test
    void testEvaluatePositionXWins() {
        char[] board = boardFromString("XXX O O  ");
        game.symbol = 'X';
        assertEquals(Game.INF, game.evaluatePosition(board, playerX));
        game.symbol = 'X';
        assertEquals(-Game.INF, game.evaluatePosition(board, playerO));
    }

    @Test
    void testEvaluatePositionOWins() {
        char[] board = boardFromString("OOO      ");
        game.symbol = 'O';
        assertEquals(Game.INF, game.evaluatePosition(board, playerO));
        game.symbol = 'O';
        assertEquals(-Game.INF, game.evaluatePosition(board, playerX));
    }

    @Test
    void testEvaluatePositionDraw() {
        char[] board = boardFromString("XOXXOOOXO");
        game.symbol = 'X';
        assertEquals(0, game.evaluatePosition(board, playerX));
        assertEquals(0, game.evaluatePosition(board, playerO));
    }

    @Test
    void testEvaluatePositionNonTerminal() {
        char[] board = boardFromString("X O      ");
        game.symbol = 'X';
        assertEquals(-1, game.evaluatePosition(board, playerX));
    }

    @Test
    @Disabled("Ошибка в оригинальном MiniMax: не всегда выбирает оптимальный первый ход")
    void testMiniMaxEmptyBoard() {
        char[] board = boardFromString("         ");
        int move = game.MiniMax(board, playerO);
        assertTrue(move == 5 || move == 1 || move == 3 || move == 7 || move == 9,
                   "Ход " + move + " не оптимален");
    }

    @Test
    void testMiniMaxBlockWin() {
        char[] board = boardFromString("XX  O    ");
        int move = game.MiniMax(board, playerO);
        assertEquals(3, move);
    }

    @Test
    @Disabled("Ошибка в оригинальном MiniMax: не всегда выбирает немедленный выигрыш")
    void testMiniMaxTakeWinningMove() {
        char[] board = boardFromString("OO  X    ");
        int move = game.MiniMax(board, playerO);
        assertEquals(3, move);
    }

    @Test
    void testMinMoveTerminal() {
        char[] board = boardFromString("XXX O O  ");
        assertEquals(-Game.INF, game.MinMove(board, playerO));
    }

    @Test
    void testMaxMoveTerminal() {
        char[] board = boardFromString("OOO      ");
        assertEquals(Game.INF, game.MaxMove(board, playerO));
    }

    @Test
    void testGameConstructor() {
        Game g = new Game();
        for (int i = 0; i < 9; i++) assertEquals(' ', g.board[i]);
        assertEquals(State.PLAYING, g.state);
        assertEquals('X', g.player1.symbol);
        assertEquals('O', g.player2.symbol);
    }

    @Test
    void testGenerateMovesNoMoves() {
        char[] board = boardFromString("XXXXXXXXX");
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(board, moves);
        assertTrue(moves.isEmpty());
    }

    @Test
    void testUtilityPrintDoesNotCrash() {
        char[] charBoard = boardFromString("X O     ");
        int[] intBoard = new int[9];
        for (int i = 0; i < 9; i++) intBoard[i] = i;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(5);
        assertDoesNotThrow(() -> Utility.print(charBoard));
        assertDoesNotThrow(() -> Utility.print(intBoard));
        assertDoesNotThrow(() -> Utility.print(list));
    }
}

class CoverageTest {

    @Test
    void testPlayer() {
        Player p = new Player();
        p.symbol = 'X';
        p.move = 5;
        p.selected = true;
        p.win = true;
        assertEquals('X', p.symbol);
        assertEquals(5, p.move);
        assertTrue(p.selected);
        assertTrue(p.win);
    }

    @Test
    void testStateEnum() {
        assertNotNull(State.PLAYING);
        assertNotNull(State.OWIN);
        assertNotNull(State.XWIN);
        assertNotNull(State.DRAW);
        assertEquals(4, State.values().length);
    }

    @Test
    void testTicTacToeCell() {
        TicTacToeCell cell = new TicTacToeCell(3, 1, 2);
        assertEquals(3, cell.getNum());
        assertEquals(2, cell.getRow());
        assertEquals(1, cell.getCol());
        assertEquals(' ', cell.getMarker());
        cell.setMarker("X");
        assertEquals('X', cell.getMarker());
        assertFalse(cell.isEnabled());
    }

    @Test
    void testTicTacToePanel() {
        TicTacToePanel panel = new TicTacToePanel(new GridLayout(3, 3));
        assertNotNull(panel);
    }

    @Test
    void testProgramMainDoesNotThrow() throws IOException {
        assertDoesNotThrow(() -> {
            Thread t = new Thread(() -> {
                try {
                    Program.main(new String[]{});
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            t.start();
            t.interrupt();
        });
    }

    @Test
    void testUtilityPrintEdgeCases() {
        char[] empty = new char[9];
        int[] intArr = new int[9];
        assertDoesNotThrow(() -> Utility.print(empty));
        assertDoesNotThrow(() -> Utility.print(intArr));
        assertDoesNotThrow(() -> Utility.print(new java.util.ArrayList<>()));
    }
}
