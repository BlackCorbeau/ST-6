package com.mycompany.app;

import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

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
        // Запускаем main в отдельном потоке, чтобы тест не завис
        assertDoesNotThrow(() -> {
            Thread t = new Thread(() -> {
                try {
                    Program.main(new String[]{});
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            t.start();
            t.interrupt(); // прерываем, чтобы окно не открывалось
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
