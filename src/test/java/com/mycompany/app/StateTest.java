package com.mycompany.app;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class StateTest {
    @Test
    void testEnumValues() {
        State[] states = State.values();
        assertEquals(4, states.length);
        assertTrue(containsState(states, State.PLAYING));
        assertTrue(containsState(states, State.OWIN));
        assertTrue(containsState(states, State.XWIN));
        assertTrue(containsState(states, State.DRAW));
    }

    private boolean containsState(State[] states, State target) {
        for (State s : states) if (s == target) return true;
        return false;
    }
}