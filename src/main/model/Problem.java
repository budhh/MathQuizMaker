package model;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.Random;


public class Problem {

    private static final int MAX_NUMBER = 99;
    private static final int MIN_NUMBER = 0;
    String typeOfProblem;
    String problem;
    String answer;
    Random random = new Random();


    public Problem(int firstNumber, int secondNumber, String typeOfProblem) {
        this.typeOfProblem = typeOfProblem;
        makeProblem(firstNumber, secondNumber, typeOfProblem);
    }

    public Problem(String typeOfProblem) {
        this.typeOfProblem = typeOfProblem;
        int firstNumber = random.nextInt((MAX_NUMBER - MIN_NUMBER) + 1) + MIN_NUMBER;
        int secondNumber = random.nextInt((firstNumber - MIN_NUMBER) + 1) + MIN_NUMBER;
        makeProblem(firstNumber, secondNumber, typeOfProblem);
    }

    public void makeProblem(int firstNumber, int secondNumber, String typeOfProblem) {
        int operation = random.nextInt(typeOfProblem.length());
        if (typeOfProblem.charAt(operation) == '+') {
            problem = firstNumber + " + " + secondNumber;
            answer = firstNumber + secondNumber + "";
        } else {
            problem = firstNumber + " - " + secondNumber;
            answer = firstNumber - secondNumber + "";
        }
    }

    public String getProblem() {
        return problem;
    }

    public String getAnswer() {
        return answer;
    }

}
