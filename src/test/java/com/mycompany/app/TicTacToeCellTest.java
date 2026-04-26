package com.mycompany.app;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class TicTacToeCellTest {
    private TicTacToeCell cell;

    @BeforeEach
    void setUp() {
        cell = new TicTacToeCell(3, 1, 0);
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals(3, cell.getNum());
        assertEquals(0, cell.getRow());
        assertEquals(1, cell.getCol());
        assertEquals(' ', cell.getMarker());
        assertTrue(cell.isEnabled());
    }

    @Test
    void testSetMarker() {
        cell.setMarker("X");
        assertEquals('X', cell.getMarker());
        assertEquals("X", cell.getText());
        assertFalse(cell.isEnabled());
    }
}
