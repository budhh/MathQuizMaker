package persistence;

import model.Quiz;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class JsonWriterTest {

    @Test
    public void testWriterInvalidFile() {
        try {
            Quiz quiz = new Quiz(5, "+");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterGeneralQuiz() {
        try {
            Quiz quiz = new Quiz(5, "+");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralQuiz.json");
            writer.open();
            writer.write(quiz);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralQuiz.json");
            Quiz quizTest = reader.read();
            assertEquals(quizTest.getQuizSize(), quiz.getQuizSize());
            assertEquals(quizTest.getTypeOfProblems(), quiz.getTypeOfProblems());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
