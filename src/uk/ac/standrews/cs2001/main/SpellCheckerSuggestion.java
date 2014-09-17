package uk.ac.standrews.cs2001.main;

/**
 * Used to store number of similar characters and the given word
 */
public class SpellCheckerSuggestion {
    private String word;
    private int numOfSimilarChar;

    /**
     * Default constructor
     * @param word given word
     * @param numOfSimilarChar number of similar characters
     */
    public SpellCheckerSuggestion(String word, int numOfSimilarChar){
        this.word = word;
        this.numOfSimilarChar = numOfSimilarChar;
    }

    /**
     * Getter for the word
     * @return given word
     */
    public String getWord(){ return this.word; }

    /**
     * Getter for the number of similar characters
     * @return the number
     */
    public int getNumOfSimilarChar(){ return  this.numOfSimilarChar;}
}
