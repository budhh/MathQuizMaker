package persistence;

import exceptions.WrongTypeOfProblemException;
import model.Quiz;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads quiz from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Quiz read() throws IOException, WrongTypeOfProblemException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseQuiz(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses quiz from JSON object and returns it
    public Quiz parseQuiz(JSONObject jsonObject) throws WrongTypeOfProblemException {
        int num = jsonObject.getInt("numOfProblems");
        String type = jsonObject.getString("typeOfProblems");

        Quiz quiz = new Quiz(num, type);
        return quiz;
    }

    public int parseQuizForInt() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        int num = jsonObject.getInt("numOfProblems");
        return num;
    }

    public String parseQuizForString() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        String type = jsonObject.getString("typeOfProblems");
        return type;
    }
}
