package ui;

import exceptions.WrongTypeOfProblemException;
import model.Quiz;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

public class GUI implements ActionListener {

    private final JFrame frame = new JFrame();
    private Quiz quiz;
    private int numProblems;
    private String typeOfProblem;
    private final JPanel panel = new JPanel();
    private JTextArea answersArea = new JTextArea(10, 1);
    private JTextArea questionsArea = new JTextArea(10, 1);
    private static final String newLine = "\n";
    JScrollPane answersScroll = new JScrollPane(answersArea);
    JScrollPane questionsScroll = new JScrollPane(questionsArea);

    private JFileChooser loadFileChooser = new JFileChooser();
    private final JButton loadButton = new JButton("LOAD");

    private JFileChooser saveFileChooser = new JFileChooser();
    private final JButton saveButton = new JButton("SAVE");

    private String numProblemsDisplay;
    JLabel numProblemsLabel = new JLabel("Enter the Number of Problems: " + numProblemsDisplay);
    private JTextField numProblemsField = new JTextField();
    private JButton numProblemsButton = new JButton("ENTER");

    private String typeOfProblemsDisplay;
    JLabel typeOfProblemsLabel = new JLabel("Enter the Type of Problems (+, -, +-): " + typeOfProblemsDisplay);
    private JTextField typeOfProblemsField = new JTextField();
    private JButton typeOfProblemsButton = new JButton("ENTER");

    private ActionListener loadAL = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            playSound();
            loadFileChooser.setCurrentDirectory(new File(System.getProperty("user.home"))); //user.home ./data
            Component parent = new JDialog();
            int result = loadFileChooser.showOpenDialog(parent);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = loadFileChooser.getSelectedFile();
                String loadFileLocation = selectedFile.getAbsolutePath();
                JsonReader jsonReader = new JsonReader(loadFileLocation);
                try {
                    typeOfProblem = jsonReader.parseQuizForString();
                    numProblems = jsonReader.parseQuizForInt();
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(frame, "Wrong Format");
                }
                finishSettingLoadAL();
            }

        }
    };
    private ActionListener saveAL = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            playSound();
            JFrame parentFrame = new JFrame();

            saveFileChooser.setDialogTitle("Specify a file to save");

            int userSelection = saveFileChooser.showSaveDialog(parentFrame);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = saveFileChooser.getSelectedFile();
                String saveFileLocation = fileToSave.getAbsolutePath();
                JsonWriter jsonWriter = new JsonWriter(saveFileLocation);
                try {
                    quiz = new Quiz(numProblems, typeOfProblem);
                } catch (WrongTypeOfProblemException wrongTypeOfProblemException) {
                    JOptionPane.showMessageDialog(frame, "You Entered An Invalid Type");
                }
                try {
                    jsonWriter.open();
                    jsonWriter.write(quiz);
                    jsonWriter.close();
                } catch (FileNotFoundException fileNotFoundException) {
                    JOptionPane.showMessageDialog(frame, "File Not Found Exception");
                }
            }
        }
    };

    private ActionListener numProblemsAL = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                playSound();
                numProblems = Integer.parseInt(numProblemsField.getText());
                numProblemsDisplay = numProblemsField.getText();
                numProblemsLabel.setText("Enter the Number of Problems: " + numProblemsDisplay);
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(frame, "Please Enter an Integer");
            }
        }
    };
    private ActionListener typeOfProblemsAL = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            playSound();
            typeOfProblem = typeOfProblemsField.getText();

            if (typeOfProblem.contains("+") || typeOfProblem.contains("-")
                    || typeOfProblem.contains("*") || typeOfProblem.contains("/")) {
                typeOfProblemsDisplay = typeOfProblemsField.getText();
                typeOfProblemsLabel.setText("Enter the Type of Problems (+, -, *, /): " + typeOfProblemsDisplay);
            } else {
                typeOfProblemsLabel.setText("Enter the Type of Problems (+, -, *, /): You Entered An Invalid Type");
                JOptionPane.showMessageDialog(frame, "You Entered An Invalid Type");
            }
        }
    };

    private JButton makeQuizButton = new JButton("MAKE QUIZ");

    public GUI() {
        // Set Up JPanel
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setLayout(new GridLayout(0, 1));

        // Load Button
        loadButton.addActionListener(loadAL);
        panel.add(loadButton);

        // Save Button
        saveButton.addActionListener(saveAL);
        panel.add(saveButton);

        // Number of Problems
        setUpProblemGUI(numProblemsLabel, numProblemsField, numProblemsButton, numProblemsAL);

        // Type of Problems
        setUpProblemGUI(typeOfProblemsLabel, typeOfProblemsField, typeOfProblemsButton, typeOfProblemsAL);

        // Make A Quiz
        makeQuizButton.addActionListener(this);
        panel.add(makeQuizButton);

        // Questions
        JLabel questionsLabel = new JLabel("Questions: ");
        panel.add(questionsLabel);
        questionsArea.setEditable(false);
        panel.add(questionsScroll);

        // Answers
        JLabel answersLabel = new JLabel("Answers: ");
        panel.add(answersLabel);
        answersArea.setEditable(false);
        panel.add(answersScroll);

        // set up the frame and display it
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("QuizMaker");
        frame.pack();
        frame.setVisible(true);
    }

    private void setUpProblemGUI(JLabel label, JTextField textField, JButton button, ActionListener al) {
        panel.add(label);
        panel.add(textField);
        button.addActionListener(al);
        panel.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            playSound();
            quiz = new Quiz(numProblems, typeOfProblem);

            questionsArea.setText("");
            answersArea.setText("");

            for (int i = 0; i < quiz.getQuizSize(); i++) {
                String questionsText = quiz.getQuiz().get(i).getProblem();

                questionsArea.append(questionsText + newLine);
            }

            for (int i = 0; i < quiz.getQuizSize(); i++) {
                String answersText = quiz.getQuiz().get(i).getAnswer();
                answersArea.append(answersText + newLine);
            }
        } catch (WrongTypeOfProblemException wrongTypeOfProblemException) {
            JOptionPane.showMessageDialog(frame, "Please Enter The Correct Type Of Problem");
        }
    }

    public void finishSettingLoadAL() {
        numProblemsDisplay = Integer.toString(numProblems);
        typeOfProblemsDisplay = typeOfProblem;
        numProblemsField.setText(numProblemsDisplay);
        typeOfProblemsField.setText(typeOfProblem);
        numProblemsLabel.setText("Enter the Number of Problems: " + numProblemsDisplay);
        typeOfProblemsLabel.setText("Enter the Type of Problems (+, -, *, /): " + typeOfProblemsDisplay);
    }

    public void playSound() {
        try {
            String soundName = "./data/sound.wav";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }


}
