package uk.ac.standrews.cs2001;

/**
 * This class contains a parse method which splits the string into a individual words.
 * <p>It also checks array to make sure that it contains only words and not integers or doubles.</p>
 * @author  cs2001 student
 */
public class SpellCheckerParser {

    public String[] parse(String inputWords){

        String[] inputWordsArray = inputWords.split(" ");

        // Find numeric values in the array
        for(String s : inputWordsArray){
            if (isInteger(s)) {
                System.err.println(s + " is a number. Please enter the word instead");
                System.exit(2);
            }
        }

        return inputWordsArray;
    }

    public static boolean isInteger(String s) {
        try {
            Double.parseDouble(s);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }
}
