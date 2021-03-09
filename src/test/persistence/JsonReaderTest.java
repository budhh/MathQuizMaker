package persistence;

import model.Quiz;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Quiz quiz = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderGeneralQuiz() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralQuiz.json");
        try {
            Quiz quiz = reader.read();
            assertEquals(5, quiz.getQuizSize());
            assertEquals("+", quiz.getTypeOfProblems());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


}
