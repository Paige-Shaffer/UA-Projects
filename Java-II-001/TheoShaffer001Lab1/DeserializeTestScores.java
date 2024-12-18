import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializeTestScores {
    public static void main(String[] args) {
        try (FileInputStream fileIn = new FileInputStream("testScores.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            
            // Deserialize the array of TestScores objects
            TestScores[] testScoresArray = (TestScores[]) in.readObject();
            
            // Display the deserialized TestScores
            System.out.println("Deserialized TestScores:");
            for (TestScores score : testScoresArray) {
                score.displayScores();             }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}