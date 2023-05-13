package org.tennis.score;

import java.util.List;
import java.util.regex.Pattern;

public class SimpleTennisApp {
    private static final List<Integer> SCORES = List.of(0, 15, 30, 40);
    private static final int MINIMUM_POINTS_TO_WIN = 4;
    private static final int MINIMUM_POINT_DIFFERENCE_TO_WIN = 2;

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
            if (pointsForA >= MINIMUM_POINTS_TO_WIN && pointsForA - pointsForB >= MINIMUM_POINT_DIFFERENCE_TO_WIN) {
                System.out.print("Player A wins the game\n");
                break;
            }
            // Player B wins
            else if (pointsForB >= MINIMUM_POINTS_TO_WIN && pointsForB - pointsForA >= MINIMUM_POINT_DIFFERENCE_TO_WIN) {
                System.out.print("Player B wins the game\n");
                break;
            }
            // Print current score
            else if (pointsForA > MINIMUM_POINT_DIFFERENCE_TO_WIN && pointsForB > MINIMUM_POINT_DIFFERENCE_TO_WIN) {
                System.out.print("Deuce\n");
            } else {
                System.out.printf("Player A : %s | Player B : %s\n", getScore(pointsForA), getScore(pointsForB));
            }
        }
    }

    private void validateString(String scoreString) {
        if (scoreString == null || scoreString.length() < 2) {
            throw new IllegalArgumentException("The score string must be at least two chars");
        }
        if (!Pattern.matches("[AB]+", scoreString)) {
            throw new IllegalArgumentException("The game is only between A & B");
        }
    }

    private int getScore(int points) {
        if (points >= SCORES.size()) {
            return SCORES.get(SCORES.size() - 1);
        } else {
            return SCORES.get(points);
        }
    }
}
