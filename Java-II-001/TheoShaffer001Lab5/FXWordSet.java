/* 
Theo Shaffer: 11/25/24
This programs prompts a user to enter multiple words, seperated by space in a GUI. 
The program then removes duplicates, non-alphabetical characters, and ignores case senstivity before 
sorting the characters in alphabetical order. 
*/

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import java.util.*;
import java.util.stream.*;

public class FXWordSet extends Application {
    
    public static void main(String[] args) {
        // Run JavaFX
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Input field for user data
        TextField userInput = new TextField();
        userInput.setPromptText("Enter a group of words seperated by spaces.");

        // "Submit" button - runs program with user input
        Button runBTN = new Button("Sort");
        
        // TextArea for output
        TextArea btnResult = new TextArea();
        btnResult.setEditable(false);
        btnResult.setWrapText(true);

        // Button sorts input
        runBTN.setOnAction(e -> {
            // Get the input from the text field: turns all to lower case, replaces nonAlphabetical characters
            String wordInput = userInput.getText().toLowerCase().replaceAll("[^a-zA-Z\\s]", "");
            if (wordInput.trim().isEmpty()) {
                btnResult.setText("You must have an input value.");
                return;
            }

            // Remove duplicate words
            Set<String> wordsSet = new TreeSet<>(Arrays.asList(wordInput.split("\\s+")));

            // Format and display
            String sortedWords = wordsSet.stream()
                                         .collect(Collectors.joining("\n"));
            btnResult.setText(sortedWords);
        });

        // VBox layout
        VBox layout = new VBox(10, userInput, runBTN, btnResult);
        layout.setPadding(new javafx.geometry.Insets(20));
        
        // Java FX stage setup
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setTitle("Word Set Processor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
                                                                                                                                                                                                                                                                                 