package uk.ac.standrews.cs2001.ui;

import java.io.IOException;
import java.util.Scanner;
import uk.ac.standrews.cs2001.main.*;
import uk.ac.standrews.cs2001.parser.*;

/**
 * Contains a main method which provides the user interface to the system.
 * @author  cs2001 student
 */
class SpellCheckerUI {

    public static void main(String[] args) throws IOException, InterruptedException{
        Scanner scanner = new Scanner(System.in);
        SpellCheckerParser parser = new SpellCheckerParser();
        SpellChecker spellChecker = null;

        System.out.println("Would you like to use a default path \"/usr/share/dict/words\" or your own path?");
        System.out.println("Please type:");
        System.out.println("\t 1: If you want to use default.");
        System.out.println("\t 2: If you want to use your own.");
        System.out.print("Your choice: ");
        while (!scanner.hasNextInt()) scanner.next(); // Find integer

        boolean isCorrectNumber = false;
        // Make sure that is either 1 or 2
        while ( !isCorrectNumber ) {
            int inputNumber = scanner.nextInt();
            switch (inputNumber) {
                case 1:
                    spellChecker = new SpellChecker();
                    isCorrectNumber = true;
                    break;
                case 2:
                    scanner.nextLine();
                    System.out.print("Please type your path: ");
                    while (!scanner.hasNextLine()) scanner.next();
                    spellChecker = new SpellChecker(scanner.nextLine());
                    isCorrectNumber = true;
                    break;
                default:
                    System.err.println("I do not know what " + inputNumber + " is. Please try again...");
                    break;
            }
        }
        scanner.nextLine();
        System.out.println("OK. Please enter words to check. Enter \"quit\" to finish.");

        System.out.print("Next word(s): ");
        while (!scanner.hasNextLine()) scanner.next();

        String[] inputWords = parser.parse(scanner.nextLine());
        if (inputWords == null)    System.exit(2);     // Quit application if there is a number

        SpellCheckResult[] scResults = new SpellCheckResult[inputWords.length];

        for (int i = 0; i < inputWords.length; i++) {
            if (inputWords[i].equals("quit")) {
                System.out.println("Goodbye");
                System.exit(0);
            }
            scResults[i] = spellChecker.check(inputWords[i]); // linear search
            if (scResults[i].isCorrect()){
                System.out.println(inputWords[i] + " correct");
            }
            else{
                if (scResults[i].getBefore() != null && scResults[i].getAfter() != null) {
                System.out.println(inputWords[i] + " not found. Nearest neighbour(s): " + scResults[i].getBefore()
                                                                              + " and " + scResults[i].getAfter());
                }
                else if (scResults[i].getBefore() == null){
                    System.out.println(inputWords[i] + " not found. Nearest neighbour(s): " + scResults[i].getAfter());
                }
                else if(scResults[i].getAfter() == null){
                    System.out.println(inputWords[i] + " not found. Nearest neighbour(s): " + scResults[i].getBefore());
                }
            }
        }
    }
}
