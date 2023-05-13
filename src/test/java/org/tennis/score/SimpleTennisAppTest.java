package org.tennis.score;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    }

    @Test
    void testGiveGameOnGoing() {
        simpleTennisApp.printGame("ABB");
        String expectedOutput = """
                Player A : 15 | Player B : 0
                Player A : 15 | Player B : 15
                Player A : 15 | Player B : 30
                """;
        String actualOutput = systemOutContent.toString();
        assertPrint(expectedOutput, actualOutput);
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
        assertPrint(expectedOutput, actualOutput);
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
        assertPrint(expectedOutput, actualOutput);
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
        assertPrint(expectedOutput, actualOutput);
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
                Deuce
                Deuce
                Deuce
                Deuce
                Deuce
                Deuce
                Deuce
                Deuce
                Deuce
                Deuce
                Deuce
                Deuce
                Deuce
                Deuce
                Deuce
                Deuce
                Player A wins the game
                """;
        String actualOutput = systemOutContent.toString();
        assertPrint(expectedOutput, actualOutput);
    }

    @Test
    public void testDeuce() {
        SimpleTennisApp app = new SimpleTennisApp();
        app.printGame("AAABBB");
        String expectedOutput = """
                Player A : 15 | Player B : 0
                Player A : 30 | Player B : 0
                Player A : 40 | Player B : 0
                Player A : 40 | Player B : 15
                Player A : 40 | Player B : 30
                Deuce
                """;
        String actualOutput = systemOutContent.toString();
        assertPrint(expectedOutput, actualOutput);

    }

    @Test
    void testGiveGameInvalid() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> simpleTennisApp.printGame("ACABAA"));
        assertEquals("The game is only between A & B", illegalArgumentException.getMessage());
    }

    @Test
    void testGiveGameInvalidString() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> simpleTennisApp.printGame("A"));
        assertEquals("The score string must be at least two chars", illegalArgumentException.getMessage());
    }

    private void assertPrint(String expectedOutput, String actualOutput) {
        assertEquals(
                expectedOutput,
                actualOutput,
                String.format("\nExpected :\n%sActual :\n%s\n", expectedOutput, actualOutput)
        );
    }
}