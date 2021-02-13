package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuizTest {

    @Test
    void testAddProblems(){
        Quiz quiz = new Quiz(5, "+");
        assertEquals(5, quiz.getQuizSize());

        quiz = new Quiz(8, "-");
        assertEquals(8, quiz.getQuizSize());

        quiz = new Quiz(85, "+");
        assertEquals(85, quiz.getQuizSize());
    }
}
