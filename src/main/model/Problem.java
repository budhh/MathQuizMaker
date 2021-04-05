package model;

import exceptions.WrongTypeOfProblemException;

import java.util.Random;


public class Problem {

    private static final int MAX_NUMBER = 99;
    private static final int MIN_NUMBER = 0;
    String typeOfProblem;
    String problem;
    String answer;
    Random random = new Random();

    // REQUIRES: int for firstNumber & secondNumber, string for type of problem, should be +, -, +-
    // EFFECTS: makes a problem
    public Problem(int firstNumber, int secondNumber, String typeOfProblem) throws WrongTypeOfProblemException {
        this.typeOfProblem = typeOfProblem;
        makeProblem(firstNumber, secondNumber, typeOfProblem);
    }

    // REQUIRES: string for type of problem, should be +, -, +-
    // EFFECTS: sets firstNumber & secondNumber as a random int [0, 99] and makes a problem
    public Problem(String typeOfProblem) throws WrongTypeOfProblemException {
        this.typeOfProblem = typeOfProblem;
        int firstNumber = random.nextInt((MAX_NUMBER - MIN_NUMBER) + 1) + MIN_NUMBER;
        int secondNumber = random.nextInt((firstNumber - MIN_NUMBER) + 1) + MIN_NUMBER;
        makeProblem(firstNumber, secondNumber, typeOfProblem);
    }

    // REQUIRES: int, string
    // MODIFIES: int to a random number form length of typeOfProblem, problem, answer
    // EFFECTS: takes in firstnumber, second number, modifies problem and answer based on typeOfProblem
    public void makeProblem(int firstNumber, int secondNumber, String typeOfProblem)
            throws WrongTypeOfProblemException  {
        int operation = random.nextInt(typeOfProblem.length());
        if (typeOfProblem.charAt(operation) == '+') {
            problem = firstNumber + " + " + secondNumber;
            answer = firstNumber + secondNumber + "";
        } else if (typeOfProblem.charAt(operation) == '-') {
            problem = firstNumber + " - " + secondNumber;
            answer = firstNumber - secondNumber + "";
        } else if (typeOfProblem.charAt(operation) == '*') {
            problem = firstNumber + " * " + secondNumber;
            answer = firstNumber * secondNumber + "";
        } else if (typeOfProblem.charAt(operation) == '/') {
            problem = firstNumber + " / " + secondNumber;
            answer = firstNumber / secondNumber + "";
        } else {
            throw new WrongTypeOfProblemException();
        }
    }

    // EFFECTS: returns problem
    public String getProblem() {
        return problem;
    }

    // EFFECTS: returns answer
    public String getAnswer() {
        return answer;
    }

}
