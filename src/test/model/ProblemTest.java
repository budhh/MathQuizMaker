package model;

import exceptions.WrongTypeOfProblemException;
import org.junit.jupiter.api.Test;

import java.time.Period;

import static org.junit.jupiter.api.Assertions.*;

class ProblemTest {

    @Test
    void testMakeProblemAddition() {
        try {
            Problem problem = new Problem(0, 0, "+");
            assertEquals(0, Integer.parseInt(problem.getAnswer()));

            problem = new Problem(50, 50, "+");
            assertEquals(50 + 50, Integer.parseInt(problem.getAnswer()));

            problem = new Problem(99, 99, "+");
            assertEquals(99 + 99, Integer.parseInt(problem.getAnswer()));
        } catch (WrongTypeOfProblemException e) {
            //success
        }
    }

    @Test
    void testMakeProblemSubtraction() {
        try {
            Problem problem = new Problem(0, 0, "-");
            assertEquals(0, Integer.parseInt(problem.getAnswer()));

            problem = new Problem(50, 25, "-");
            assertEquals(50 - 25, Integer.parseInt(problem.getAnswer()));

            problem = new Problem(25, 50, "-");
            assertEquals(25 - 50, Integer.parseInt(problem.getAnswer()));

            problem = new Problem(99, 99, "-");
            assertEquals(0, Integer.parseInt(problem.getAnswer()));
        } catch (WrongTypeOfProblemException e) {
            //success
        }
    }
}