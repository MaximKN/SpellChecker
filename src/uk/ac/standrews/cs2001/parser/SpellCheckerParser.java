package uk.ac.standrews.cs2001.parser;

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
            if (isNumerical(s)) {
                System.err.println(s + " is a number. Please enter the word instead");
                return null;
            }
        }

        return inputWordsArray;
    }

    public static boolean isNumerical(String s) {
        try {
            //noinspection ResultOfMethodCallIgnored
            Double.parseDouble(s);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }
}
