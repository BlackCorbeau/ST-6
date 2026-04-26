package com.mycompany.app;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void testPlayerFields() {
        Player p = new Player();
        p.symbol = 'X';
        p.move = 5;
        p.selected = true;
        p.win = false;
        assertEquals('X', p.symbol);
        assertEquals(5, p.move);
        assertTrue(p.selected);
        assertFalse(p.win);
    }
}