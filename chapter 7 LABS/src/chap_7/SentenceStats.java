// Example 7.3: Count the words and compute the average
// word length in a sentence
package chap_7; 
import java.util.Scanner;

public class SentenceStats{

   public static void main(String[] args){

      Scanner reader = new Scanner(System.in);

      // Keep taking inputs
      while (true){
         System.out.print("Enter a sentence: ");
         String input = reader.nextLine();

         // Quit when the user just presses Enter
         if (input.equals(""))
            break;

         // Initialize the counters and indexes
         int wordCount = 0;
         int sentenceLength = 0;
         int beginPosition = 0;
         int endPosition = input.indexOf(' ');

         // Continue until a blank is not seen
         while (endPosition != -1){
 
            // If at least one non-blank character (a word) was seen
            if (endPosition > beginPosition){
               wordCount++;
               String word = input.substring(beginPosition, endPosition);
               sentenceLength += word.length();
            }

            // Update the indexes to go to the next word
            beginPosition = endPosition + 1;
            endPosition = input.indexOf(' ', beginPosition);
         }

         // If at least one non-blank character was seen 
         // at the end of the sentence, consider it a word
         if (beginPosition < input.length()){
            wordCount++;
            String word = input.substring(beginPosition, input.length());
            sentenceLength += word.length();
         }

         // Trap the case where there were no words
         if (wordCount > 0){
            System.out.println("Word count: " + wordCount);
            System.out.println("Sentence length: " + sentenceLength);
            System.out.println("Average word length: " + 
                                sentenceLength / wordCount);
         }
      }
   }
}

// Darian Siembab
// 7.3
// Program asks the user to input sentences and calculates 
// and displays data about the sentence, 
// including the number of words, the average word length, 
// and the length in characters of the sentence (not including whitespace)

