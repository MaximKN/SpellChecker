package uk.ac.standrews.cs2001;

/**
 * This is a class used by SpellChecker to return results.
 * A SpellCheckResult object has public fields indicating whether the word was correctly spelt and,
 * if not, what were the words immediately before and after it.
 * If it was before the first dictionary entry or after the last one then one of these fields might be null.
 */
public class SpellCheckResult {

    private boolean correct;
    private String before;
    private String after;

    /**
     * Constructor for correct result
     * @param result correct result expects true value
     */
    public SpellCheckResult(boolean result) {
        this.correct = result;
    }

    /**
     * Constructor for incorrect result
     * @param result incorrect result expects false value
     * @param before the word before
     * @param after the word after
     */
    public SpellCheckResult(boolean result, String before, String after) {

        this.correct = result;
        this.before = before;
        this.after = after;
    }

    /**
     * @return true if the word was found
     */
    public boolean isCorrect() {
        return correct;
    }

    /**
     * @return the word in the dictionary position before the one being checked, or null if there isn't such a word
     */
    public String getBefore() {
        return before;
    }

    /**
     * @return the word in the dictionary position after the one being checked, or null if there isn't such a word
     */
    public String getAfter() {
        return after;
    }
}
