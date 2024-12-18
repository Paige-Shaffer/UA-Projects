import java.io.Serializable;

public class TestScores implements Serializable {

    // ID for version consistency
    private static final long serialVersionUID = 1L;

    // Array to hold test scores
    private int[] testScores;

    // Constructor to accept test scores and validate them
    public TestScores(int[] testScores) {
        // Validation for test scores
        for (int score : testScores) {
            if (score < 0 || score > 100) {
                throw new IllegalArgumentException("Test scores must be between 0 and 100.");
            }
        }
        this.testScores = testScores;
    }

    // Method to get the average of the test scores
    public double getAverage() {
        if (testScores.length == 0) {
            return 0; // Avoid division by zero
        }
        int sum = 0;
        for (int score : testScores) {
            sum += score;
        }
        return sum / (double) testScores.length;
    }

    // Method to display test scores
    public void displayScores() {
        System.out.print("Test Scores: ");
        for (int score : testScores) {
            System.out.print(score + " ");
        }
        System.out.println();
    }

    // Override toString method to simplify printing
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Test Scores: ");
        for (int score : testScores) {
            sb.append(score).append(" ");
        }
        return sb.toString().trim();
    }
}
