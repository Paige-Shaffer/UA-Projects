import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializeTestScores {
    public static void main(String[] args) {
         System.out.println("Current working directory: " + System.getProperty("user.dir"));
        // Array of TestScores objects
        TestScores[] testScoresArray = new TestScores[5];
        
        // Test data
        testScoresArray[0] = new TestScores(new int[]{85, 90, 78});
        testScoresArray[1] = new TestScores(new int[]{92, 88, 76});
        testScoresArray[2] = new TestScores(new int[]{100, 95, 98});
        testScoresArray[3] = new TestScores(new int[]{55, 60, 65});
        testScoresArray[4] = new TestScores(new int[]{77, 80, 85});
        
        try (FileOutputStream fileOut = new FileOutputStream("testScores.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            
            // Serialize 
            out.writeObject(testScoresArray);
            System.out.println("TestScores objects have been serialized to testScores.ser.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}