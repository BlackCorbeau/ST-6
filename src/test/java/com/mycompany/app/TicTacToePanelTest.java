package com.mycompany.app;

import org.junit.jupiter.api.*;
import java.awt.*;
import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.*;

class TicTacToePanelTest {
    private TicTacToePanel panel;

    @BeforeEach
    void setUp() {
        panel = new TicTacToePanel(new GridLayout(3,3));
    }

    @Test
    void testConstructorCreatesCells() throws Exception {
        Field cellsField = TicTacToePanel.class.getDeclaredField("cells");
        cellsField.setAccessible(true);
        TicTacToeCell[] cells = (TicTacToeCell[]) cellsField.get(panel);
        assertNotNull(cells);
        assertEquals(9, cells.length);
        for (int i = 0; i < 9; i++) {
            assertNotNull(cells[i]);
            assertEquals(' ', cells[i].getMarker());
        }
    }

    @Test
    void testGameInitialized() throws Exception {
        Field gameField = TicTacToePanel.class.getDeclaredField("game");
        gameField.setAccessible(true);
        Game game = (Game) gameField.get(panel);
        assertNotNull(game);
        assertEquals(game.player1, game.cplayer);
    }
}