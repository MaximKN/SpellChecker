package uk.ac.standrews.cs2001.parser;

/**
 * Contains a parse method which splits the string into a individual words.
 * <p>It also checks array to make sure that it contains only words and not integers or doubles.</p>
 * @author  cs2001 student
 */
public class SpellCheckerParser {

    /**
     * Splits the string into individual words
     * @param inputWords string with input words
     * @return array that contains all words individually
     */
    public String[] parse(String inputWords){

        String[] inputWordsArray = inputWords.split("\\s+");

        // Find numeric values in the array
        for(String s : inputWordsArray){
            if (isNumerical(s)) {
                System.err.println(s + " is a number. Please enter the word instead");
                return null;
            }
        }

        return inputWordsArray;
    }

    /**
     * Determines is the given string numerical by trying to parse it
     * @param s given string
     * @return is it numerical or not
     */
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
