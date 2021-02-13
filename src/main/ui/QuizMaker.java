package ui;


import model.Problem;
import model.Quiz;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class QuizMaker {
    private Quiz quiz;
    private Scanner input;

    // EFFECTS: runs QuizMaker
    public QuizMaker() {
        runQuizMaker();
    }

    // MODIFIES: this
    // EFFECTS: initializes input, sets numProblems, sets typeOfProblems, sets a new quiz, and prints out quiz+answers
    private void runQuizMaker() {
        input = new Scanner(System.in);

        System.out.println("How many problems do you want?");
        int numProblems = input.nextInt();

        System.out.println("What type of Questions do you want?");
        System.out.println("\nSelect From:");
        System.out.println("\t+  : Addition");
        System.out.println("\t-  : Subtraction");
        System.out.println("\t+- : Addition and Subtraction");

        String typeOfProblem = input.next();

        quiz = new Quiz(numProblems, typeOfProblem);

        System.out.println("The Problems are:");

        for (int i = 0; i < quiz.getQuizSize(); i++) {
            System.out.println("\t" + quiz.getQuiz().get(i).getProblem());
        }

        System.out.println("The Answers are:");
        for (int i = 0; i < quiz.getQuizSize(); i++) {
            System.out.println("\t" + quiz.getQuiz().get(i).getAnswer());
        }
    }
}
