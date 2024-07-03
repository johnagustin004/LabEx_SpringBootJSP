package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;

public class Quiz {
    private ArrayList<Question> questions;

    public Quiz() {
        questions = new ArrayList<>();
        // Add your questions here
        questions.add(new Question("What country has the highest life expectancy?",
                new String[]{"Philippines", "Hong Kong", "USA", "Singapore"}, 1));
        questions.add(new Question("How many elements are in the periodic table?",
                new String[]{"119", "118", "117", "120"}, 1));
        questions.add(new Question("Who was the Ancient Greek God of the Sun?",
                new String[]{"Athena", "Hades", "Apollo", "Venus"}, 2));
        questions.add(new Question("What country drinks the most coffee per capita?",
                new String[]{"Russia", "China", "USA", "Finland"}, 3));
        questions.add(new Question("What Renaissance artist is buried in Rome's Pantheon?",
                new String[]{"Donatello", "Leonardo", "Michael", "Raphael"}, 3));

        Collections.shuffle(questions); // Randomize questions
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public int evaluate(int[] userAnswers) {
        int score = 0;
        for (int i = 0; i < userAnswers.length; i++) {
            if (userAnswers[i] == questions.get(i).getCorrectOption()) {
                score++;
            }
        }
        return score;
    }
}
