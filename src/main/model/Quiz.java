package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

public class Quiz implements Writable {
    ArrayList<Problem> quiz;
    int numProblems;
    String typeOfProblems;

    // REQUIRES: int for num, string for type
    // EFFECTS: runs adds problems which adds problems to quiz
    public Quiz(int num, String type) {
        quiz = new ArrayList<>();
        numProblems = num;
        typeOfProblems = type;
        addProblems();
    }

    // MODIFIES: quiz
    // EFFECTS: adds problems to quiz in a for loop
    public void addProblems() {
        for (int i = 0; i < numProblems; i++) {
            quiz.add(new Problem(typeOfProblems));
        }
    }

    // EFFECTS: returns quiz size
    public int getQuizSize() {
        return quiz.size();
    }

    // EFFECTS: returns quiz
    public ArrayList<Problem> getQuiz()     {
        return quiz;
    }

    // EFFECTS: returns the type of problems
    public String getTypeOfProblems() {
        return typeOfProblems;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("numOfProblems", numProblems);
        json.put("typeOfProblems", typeOfProblems);
        return json;
    }
}
