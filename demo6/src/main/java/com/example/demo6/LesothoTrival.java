package com.example.demo6;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LesothoTrival extends Application {

    private int questionIndex = 0;
    private String[] questions = {
            "What is the capital city of Lesotho?",
            "What is the highest point in Lesotho?",
            "Which river forms part of the border between Lesotho and South Africa?",
            "What is the traditional Basotho blanket called?",
            "What is the name of the national park in Lesotho known for its dinosaur footprints?"
    };
    private String[][] options = {
            {"Maseru", "Leribe", "Mokhotlong", "Butha-Buthe"},
            {"Thabana Ntlenyana", "Mount Qiloane", "Njesuthi", "Tsoelike"},
            {"Orange River", "Vaal River", "Caledon River", "Lepelle River"},
            {"Sesotho", "Mokorotlo", "Bogolan", "Kente"},
            {"Sehlabathebe National Park", "Ts'ehlanyane National Park", "Bokong Nature Reserve", "Sequoia National Park"}
    };
    private String[] correctAnswers = {"Maseru", "Thabana Ntlenyana", "Caledon River", "Mokorotlo", "Ts'ehlanyane National Park"};

    private Label questionLabel;
    private Button[] optionButtons;

    @Override
    public void start(Stage primaryStage) {
        questionLabel = new Label();
        questionLabel.setWrapText(true);

        ImageView imageView = new ImageView(new Image("lesotho.jpg"));
        imageView.setFitWidth(400);
        imageView.setFitHeight(300);

        optionButtons = new Button[4];
        for (int i = 0; i < 4; i++) {
            final int optionIndex = i;
            optionButtons[i] = new Button();
            optionButtons[i].setOnAction(event -> checkAnswer(optionIndex));
        }

        VBox root = new VBox(10, imageView, questionLabel, optionButtons[0], optionButtons[1], optionButtons[2], optionButtons[3]);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f2f2f2;");
        String internalCSS =
                ".question-label {" +
                        "    -fx-font-size: 18px;" +
                        "    -fx-font-weight: bold;" +
                        "    -fx-text-fill: #333;" +
                        "}" +
                        ".option-button {" +
                        "    -fx-font-size: 14px;" +
                        "    -fx-padding: 8px 16px;" +
                        "    -fx-background-color: #4CAF50;" + // Green
                        "    -fx-text-fill: white;" +
                        "    -fx-cursor: hand;" +
                        "}" +
                        ".option-button:hover {" +
                        "    -fx-background-color: #45a049;" + // Darker Green
                        "}";

        Scene scene = new Scene(root, 600, 500);
        scene.getStylesheets().add("data:text/css;charset=utf-8," + internalCSS);
        primaryStage.setTitle("Lesotho Trivia Game");
        primaryStage.setScene(scene);
        primaryStage.show();

        displayQuestion();
    }

    private void displayQuestion() {
        questionLabel.setText(questions[questionIndex]);
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(options[questionIndex][i]);
        }
    }

    private void checkAnswer(int optionIndex) {
        if (options[questionIndex][optionIndex].equals(correctAnswers[questionIndex])) {
            showFeedback("Correct!", "green");
            // Move to the next question if the answer is correct
            questionIndex++;
            // Check if all questions have been answered
            if (questionIndex < questions.length) {
                // Display the next question
                displayQuestion();
            } else {
                // End of the game
                showFeedback("Congratulations! You have completed all questions.", "blue");
            }
        } else {
            showFeedback("Incorrect. Try again.", "red");
        }
    }

    private void showFeedback(String message, String color) {
        Label feedbackLabel = new Label(message);
        feedbackLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: " + color + ";");
        ((VBox) questionLabel.getParent()).getChildren().add(feedbackLabel);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
