package ui;


import model.Problem;
import model.Quiz;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class QuizMaker {
    private static final String JSON_STORE = "./data/quiz.json";
    private Quiz quiz;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs QuizMaker
    public QuizMaker() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runQuizMaker();
    }

    // MODIFIES: this
    // EFFECTS: initializes input, sets numProblems, sets typeOfProblems, sets a new quiz, and prints out quiz+answers
    private void runQuizMaker() {
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tm -> make a quiz");
        System.out.println("\tp -> print the quiz and answers");
        System.out.println("\ts -> save quiz to file");
        System.out.println("\tl -> load quiz from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("m")) {
            makeQuiz();
        } else if (command.equals("p")) {
            printQuiz();
        } else if (command.equals("s")) {
            saveQuiz();
        } else if (command.equals("l")) {
            loadQuiz();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void loadQuiz() {
        try {
            quiz = jsonReader.read();
            System.out.println("Loaded quiz from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    private void saveQuiz() {
        try {
            jsonWriter.open();
            jsonWriter.write(quiz);
            jsonWriter.close();
            System.out.println("Saved quiz to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void printQuiz() {
        System.out.println("The Problems are:");

        for (int i = 0; i < quiz.getQuizSize(); i++) {
            System.out.println("\t" + quiz.getQuiz().get(i).getProblem());
        }

        System.out.println("The Answers are:");
        for (int i = 0; i < quiz.getQuizSize(); i++) {
            System.out.println("\t" + quiz.getQuiz().get(i).getAnswer());
        }
    }

    private void makeQuiz() {
        System.out.println("How many problems do you want?");
        int numProblems = input.nextInt();

        System.out.println("What type of Questions do you want?");
        System.out.println("\nSelect From:");
        System.out.println("\t+  : Addition");
        System.out.println("\t-  : Subtraction");
        System.out.println("\t+- : Addition and Subtraction");

        String typeOfProblem = input.next();

        quiz = new Quiz(numProblems, typeOfProblem);
    }
}
