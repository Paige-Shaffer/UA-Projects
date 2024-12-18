/* 
Theo Shaffer: 11/25/24
This programs prompts a user to enter multiple words, seperated by spaces. 
The program then removes duplicates, non-alphabetical characters, and ignores case senstivity before 
sorting the characters in alphabetical order. 
*/

import java.util.*; 
import java.util.stream.*;

public class ClassicWordSet {
   public static void main(String[] args) {
   //Defines scanner, prompts user and collects input
   Scanner scanner = new Scanner(System.in);
   System.out.println("Please enter a set of words seperated by spaces:"); 
   
   //Scanner read & tokenizes input: Collects words into a set removing dupes
   // ToLowerCase() - Case insenstive input, replaceAll - Removes non-alphabetical characters
   String wordInput = scanner.nextLine().toLowerCase().replaceAll("[^a-zA-Z\\s]", "");
   Set<String> wordsSet = new TreeSet<>(Arrays.asList(wordInput.split("\\s+")));
   
   //Prints sorted stream
   wordsSet.stream()
      .forEach(System.out::println); 
      
  //Closes scanner
  scanner.close();
  }
} 
   
   



