package org.tennis.score;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleTennisAppTest {

    private PrintStream originalSystemOut;
    private ByteArrayOutputStream systemOutContent;

    private final SimpleTennisApp simpleTennisApp = new SimpleTennisApp();


    /**
     * In order to capture system out stream
     */
    @BeforeEach
    void redirectSystemOutStream() {
        originalSystemOut = System.out;
        systemOutContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOutContent));
    }

    /**
     * In order to capture system out stream
     */
    @AfterEach
    void restoreSystemOutStream() {
        System.setOut(originalSystemOut);
        System.out.println(systemOutContent.toString());
    }

    @Test
    void testGiveGameABABAA() {
        simpleTennisApp.printGame("ABABAA");
        String expectedOutput = """
                Player A : 15 | Player B : 0
                Player A : 15 | Player B : 15
                Player A : 30 | Player B : 15
                Player A : 30 | Player B : 30
                Player A : 40 | Player B : 30
                Player A wins the game
                """;
        String actualOutput = systemOutContent.toString();
        assertEquals(expectedOutput, actualOutput);
    }


    @Test
    void testGiveGameOnlyA() {
        simpleTennisApp.printGame("AAAAAA");
        String expectedOutput = """
                Player A : 15 | Player B : 0
                Player A : 30 | Player B : 0
                Player A : 40 | Player B : 0
                Player A wins the game
                """;
        String actualOutput = systemOutContent.toString();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testGiveGameOnlyB() {
        simpleTennisApp.printGame("BBBBBB");
        String expectedOutput = """
                Player A : 0 | Player B : 15
                Player A : 0 | Player B : 30
                Player A : 0 | Player B : 40
                Player B wins the game
                """;
        String actualOutput = systemOutContent.toString();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testLongGame() {
        simpleTennisApp.printGame("ABABABABABABABABABABAA");
        String expectedOutput = """
                Player A : 15 | Player B : 0
                Player A : 15 | Player B : 15
                Player A : 30 | Player B : 15
                Player A : 30 | Player B : 30
                Player A : 40 | Player B : 30
                Player A : 40 | Player B : 40
                Player A : 40 | Player B : 40
                Player A : 40 | Player B : 40
                Player A : 40 | Player B : 40
                Player A : 40 | Player B : 40
                Player A : 40 | Player B : 40
                Player A : 40 | Player B : 40
                Player A : 40 | Player B : 40
                Player A : 40 | Player B : 40
                Player A : 40 | Player B : 40
                Player A : 40 | Player B : 40
                Player A : 40 | Player B : 40
                Player A : 40 | Player B : 40
                Player A : 40 | Player B : 40
                Player A : 40 | Player B : 40
                Player A : 40 | Player B : 40
                Player A wins the game
                """;
        String actualOutput = systemOutContent.toString();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void testGiveGameInvalid() {
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> simpleTennisApp.printGame("ACABAA"));
        assertEquals("The game is only between A & B", illegalArgumentException.getMessage());
    }
}