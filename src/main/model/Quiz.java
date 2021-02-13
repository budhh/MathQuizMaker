package model;

import java.util.ArrayList;

public class Quiz {
    ArrayList<Problem> quiz;
    int numProblems;
    String typeOfProblems;

    public Quiz(int num, String type) {
        quiz = new ArrayList<>();
        numProblems = num;
        typeOfProblems = type;
        addProblems();
    }

    public void addProblems() {
        for (int i = 0; i < numProblems; i++) {
            quiz.add(new Problem(typeOfProblems));
        }
    }

    public int getQuizSize() {
        return quiz.size();
    }

    public ArrayList<Problem> getQuiz() {
        return quiz;
    }
}
