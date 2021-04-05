package model;

import exceptions.WrongTypeOfProblemException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuizTest {

    @Test
    void testAddProblems(){
        try {
            Quiz quiz = new Quiz(5, "+");
            assertEquals(5, quiz.getQuizSize());

            quiz = new Quiz(8, "-");
            assertEquals(8, quiz.getQuizSize());

            quiz = new Quiz(85, "+");
            assertEquals(85, quiz.getQuizSize());
        } catch (WrongTypeOfProblemException e) {
            //success
        }
    }

    @Test
    void testWrongTypeAddProblem() {
        try {
            Quiz quiz = new Quiz(5, "p");
            fail();
        } catch (WrongTypeOfProblemException e) {
            //success
        }
    }

}
