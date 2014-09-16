package uk.ac.standrews.cs2001;
/**
 * This class stores the array of strings and provides a method check to search it.
 * @author  cs2001 student
 */

public class SpellChecker implements ISpellChecker{

    private String[] wordsInDictionary;

    public SpellChecker(){
        this.wordsInDictionary = DictionaryLoader.loadDictionary(false, "default");
    }

    public SpellChecker(String ownPath){
        this.wordsInDictionary = DictionaryLoader.loadDictionary(true, ownPath);
    }

    /**
     * Linear search algorithm
     * @param word the word to be checked
     * @return result of the search
     */
    @Override
    public SpellCheckResult check(String word) {
        for (int j = 0; j < wordsInDictionary.length; j++) {
            if (word.compareTo(wordsInDictionary[j]) == 0) {
                return new SpellCheckResult(true);
            } else if (word.compareTo(wordsInDictionary[j]) < 0) {
                if (j == 0) {
                    return new SpellCheckResult(false, null, wordsInDictionary[j]);
                }
                return new SpellCheckResult(false, wordsInDictionary[j - 1], wordsInDictionary[j]);
            }
        }
        return new SpellCheckResult(false, wordsInDictionary[wordsInDictionary.length - 1], null);
    }

    /**
     * Implementation of the binary search algorithm
     * Sub-method
     * @param word given word by user
     * @return pass word to the algorithm and return the result
     */
    public SpellCheckResult bsCheck(String word){
        return bsCheck(word, 0, wordsInDictionary.length);
    }

    /**
     * Implementation of the binary search algorithm
     * Main method
     * @param word word typed by user
     * @param low value needed for the binary search
     * @param high value needed for the binary search
     * @return result of the search or go deeper by the recursion
     */
    public SpellCheckResult bsCheck(String word, int low, int high){
        if (high <= low) {
            if (low == 0){
                return new SpellCheckResult(false, null, wordsInDictionary[0]);
            }
            if (high == wordsInDictionary.length){
                return new SpellCheckResult(false, wordsInDictionary[wordsInDictionary.length - 1], null);
            }
            return new SpellCheckResult(false, wordsInDictionary[low - 1], wordsInDictionary[low]);
        }
        int mid = low + (high - low) / 2;
        int cmp = word.compareTo(wordsInDictionary[mid]);

        if      (cmp < 0) return bsCheck(word, low, mid);
        else if (cmp > 0) return bsCheck(word, mid + 1, high);
        else              return new SpellCheckResult(true);
    }
}
