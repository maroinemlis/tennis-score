package org.tennis.score;

import java.util.List;
import java.util.regex.Pattern;

public class SimpleTennisApp {
    private static final List<Integer> SCORES = List.of(0, 15, 30, 40);
    private static final int THRESHOLD = 4;
    private static final int MIN_DIFF_TO_WIN = 2;

    public void validateString(String scoreString) {
        if (!Pattern.matches("[AB]+", scoreString)) {
            throw new IllegalArgumentException("The game is only between A & B");
        }
    }

    public void printGame(String scoreString) {
        validateString(scoreString);
        int pointsForA = 0;
        int pointsForB = 0;
        for (char c : scoreString.toCharArray()) {
            switch (c) {
                case 'A' -> pointsForA++;
                case 'B' -> pointsForB++;
            }
            // Player A wins
            if (pointsForA >= THRESHOLD && pointsForA - pointsForB >= MIN_DIFF_TO_WIN) {
                System.out.print("Player A wins the game\n");
                break;
            }
            // Player B wins
            if (pointsForB >= THRESHOLD && pointsForB - pointsForA >= MIN_DIFF_TO_WIN) {
                System.out.print("Player B wins the game\n");
                break;
            }
            // Print current score
            System.out.printf("Player A : %s | Player B : %s\n", getScore(pointsForA), getScore(pointsForB));
        }
    }


    public int getScore(int points) {
        if (points >= SCORES.size()) {
            return SCORES.get(SCORES.size() - 1);
        } else {
            return SCORES.get(points);
        }
    }
}
