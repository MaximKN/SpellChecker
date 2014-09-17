package uk.ac.standrews.cs2001.main;

/**
 * Stores the array of strings and provides a method check to search it.
 * @author  cs2001 student
 */

public class SpellChecker implements ISpellChecker{

    private final String[] wordsInDictionary;

    /**
     * Constructor for the default path
     */
    public SpellChecker(){
        this.wordsInDictionary = DictionaryLoader.loadDictionary(false, "default");
    }

    /**
     * Constructor for the path typed by user
     */
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
                return new SpellCheckResult();
            } else if (word.compareTo(wordsInDictionary[j]) < 0) {
                if (j == 0) {
                    return new SpellCheckResult(j, null, wordsInDictionary[j]);
                }
                return new SpellCheckResult(j, wordsInDictionary[j - 1], wordsInDictionary[j]);
            }
        }
        return new SpellCheckResult(wordsInDictionary.length - 1, wordsInDictionary[wordsInDictionary.length - 1], null);
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
                return new SpellCheckResult(low, null, wordsInDictionary[0]);
            }
            if (high == wordsInDictionary.length){
                return new SpellCheckResult(high, wordsInDictionary[wordsInDictionary.length - 1], null);
            }
            return new SpellCheckResult(low, wordsInDictionary[low - 1], wordsInDictionary[low]);
        }
        int mid = low + (high - low) / 2;
        int cmp = word.compareTo(wordsInDictionary[mid]);

        if      (cmp < 0) return bsCheck(word, low, mid);
        else if (cmp > 0) return bsCheck(word, mid + 1, high);
        else              return new SpellCheckResult();
    }

    /**
     * Finds possible suggestions
     * @param word word typed by user
     * @return array of possible suggestions
     */
    public SpellCheckerSuggestion[] findSuggestions(String word){
        boolean notSorted = true;
        SpellCheckerSuggestion temp;
        SpellCheckerSuggestion[] suggestions;

        int index = bsCheck(word).getIndex();

        // If word is at the beginning of a dictionary
        if (index <= 10){
            suggestions = new SpellCheckerSuggestion[10 - index];
            for (int i = 0; i < 10 - index; i++) {
                suggestions[i] = new SpellCheckerSuggestion(wordsInDictionary[i], findNumOfSimilarChar(word, wordsInDictionary[i]));
            }
        }
        // If word is at the end of a dictionary
        else if (index >= wordsInDictionary.length - 10) {
            suggestions = new SpellCheckerSuggestion[wordsInDictionary.length - index];
            for (int i = 0; i < wordsInDictionary.length - index; i++) {
                suggestions[i] = new SpellCheckerSuggestion(wordsInDictionary[index-1+i], findNumOfSimilarChar(word, wordsInDictionary[index-1+i]));
            }
        }
        // If word is somewhere else
        else {
            suggestions = new SpellCheckerSuggestion[20];
            // Look up 10 words before and after the given word
            for (int i = 0; i < 20; i++) {
                suggestions[i] = new SpellCheckerSuggestion(wordsInDictionary[index - 10 + i], findNumOfSimilarChar(word, wordsInDictionary[index - 10 + i]));
            }
        }
        // Sort suggestions using bubble sort to find the most suitable word
        while ( notSorted ) {
            notSorted = false;
            for(int j = 0;  j < suggestions.length - 1;  j++ ) {
                if ( suggestions[j].getNumOfSimilarChar() < suggestions[j+1].getNumOfSimilarChar() ){
                    temp = suggestions[j];
                    suggestions[j] = suggestions[ j+1 ];
                    suggestions[ j+1 ] = temp;
                    notSorted = true;
                }
            }
        }
        return suggestions;
    }

    /**
     * Finds number of possible similar characters in two words
     * @param firstWord word from user
     * @param secondWord word from the dictionary
     * @return number of similar characters
     */
    public int findNumOfSimilarChar(String firstWord, String secondWord){
        String commonChars = "";
        for (int i = 0; i < firstWord.length(); i++)
        {
            char ch = firstWord.charAt(i);
            if (secondWord.indexOf(ch) != -1)
            {
                commonChars = commonChars + ch;
            }
        }
        return commonChars.length();
    }
}
