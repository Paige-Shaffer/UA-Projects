/* Name: Theo Shaffer
   Date: October 15th, 2024
   Purpose: This program simulates a coin flip when the user hits enter, while using javafx to create a visual interface.  
*/

//Importing Resources
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label; // Imports the label
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Random; // Generates random numbers - used to randomize coin flip 

//JAVAFX Class 
public class CoinToss extends Application {
private ImageView coinView; // Display Coin Image
private Random NumRandom; // Random object defined for randomized number
private Label instructionLbl; //Label for user instruction

   @Override public void start(Stage primaryStage) {
   //Initialize ImageView, Label & Random 
   coinView =  new ImageView();
   instructionLbl = new Label("Press Enter for a coin flip!");
   NumRandom = new Random(); 
   
   //Size the coin images
   coinView.setFitWidth(200);  // Set desired width
   coinView.setFitHeight(200); // Set desired height
   coinView.setPreserveRatio(true); // Maintain aspect ratio
   
   //Scene using StackPane + Coin Images
   StackPane root = new StackPane(coinView, instructionLbl);
   Scene scene = new Scene(root); 
   
   scene.setOnKeyPressed(this::handleKeyPress);
   primaryStage.setTitle("Coin Toss");
   primaryStage.setScene(scene); 
   primaryStage.show(); 
   }

   //Method that handles events for keys
   private void handleKeyPress(KeyEvent event) {
   // Enter is pressed
   if (event.getCode().toString().equals("ENTER")) {
   // Generates coin flip
   instructionLbl.setVisible(false);
   int tossResult = NumRandom.nextInt(2); 
   String imageShow = tossResult == 0 ? "heads.jpg" : "tails.jpg"; 
   coinView.setImage(new Image(imageShow));
   }
   
   //Once coin is flipped, label will dissapear and reappear after a timed duration
   Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(15), e -> {
   coinView.setImage(null);
   instructionLbl.setVisible(true);
   
            }));
            timeline.play();
}
    public static void main(String[] args) {
        launch(args); 
    }
}






