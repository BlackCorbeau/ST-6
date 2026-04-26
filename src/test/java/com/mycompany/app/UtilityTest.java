package com.mycompany.app;

import org.junit.jupiter.api.*;
import java.io.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream bos;

    @BeforeEach
    void setUp() {
        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testPrintCharArray() {
        char[] board = {'X','O',' ',' ',' ',' ',' ',' ',' '};
        Utility.print(board);
        String output = bos.toString();
        assertTrue(output.contains("X-O-"));
    }

    @Test
    void testPrintIntArray() {
        int[] arr = {1,2,3,0,0,0,0,0,0};
        Utility.print(arr);
        String output = bos.toString();
        assertTrue(output.contains("1-2-3-"));
    }

    @Test
    void testPrintArrayList() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(8);
        Utility.print(list);
        String output = bos.toString();
        assertTrue(output.contains("4-8-"));
    }
}