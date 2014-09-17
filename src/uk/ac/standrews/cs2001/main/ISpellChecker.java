package uk.ac.standrews.cs2001.main;

public interface ISpellChecker {

    /**
     * Checks whether a word is correctly spelled.
     *
     * @param word the word to be checked
     * @return an object representing the outcome of the check
     */
    SpellCheckResult check(String word);
}