package uk.ac.standrews.cs2001.benchmark;

import uk.ac.standrews.cs2001.main.*;
import uk.ac.standrews.cs2001.parser.*;

public class SpellCheckBenchmark {

    private static final SpellCheckerParser parser = new SpellCheckerParser();
    private static final SpellChecker spellChecker = new SpellChecker();
    private static String[] inputWords;
    private static SpellCheckResult[] scResults;
    public static final String SMALL_TEXT = "computer zoothecia zoothecial " +
                                            "zoothecium zootheism zootheist" +
                                            "zootheistic zootherapy zoothome" +
                                            "zootic zootoc anovennial" +
                                            "novercal Novial novice" +
                                            "novicehood novicelike noviceship";
    public static final String LARGE_TEXT = "computer zoothecia zoothecial computer zoothecia zoothecial " +
                                            "zoothecium zootheism zootheist zoothecium zootheism zootheist" +
                                            "zootheistic zootherapy zoothome zootheistic zootherapy zoothome" +
                                            "zootic zootoc anovennial zootic zootoc anovennial" +
                                            "novercal Novial novice novercal Novial novice" +
                                            "novicehood novicelike noviceship novicehood novicelike noviceship" +
                                            "computer zoothecia zoothecial computer zoothecia zoothecial" +
                                            "zoothecium zootheism zootheist zoothecium zootheism zootheist" +
                                            "zootheistic zootherapy zoothome zootheistic zootherapy zoothome" +
                                            "zootic zootoc anovennial zootic zootoc anovennial" +
                                            "novercal Novial novice novercal Novial novice" +
                                            "novicehood novicelike noviceship novicehood novicelike noviceship" +
                                            "computer zoothecia zoothecial computer zoothecia zoothecial" +
                                            "zoothecium zootheism zootheist zoothecium zootheism zootheist" +
                                            "zootheistic zootherapy zootheistic zootherapy zoothome" +
                                            "zootic zootoc anovennial zootic zootoc anovennial" +
                                            "novercal Novial novice novercal Novial novice" +
                                            "novicehood novicelike noviceship novicehood novicelike noviceship" +
                                            "computer zoothecia zoothecial computer zoothecia zoothecial " +
                                            "zoothecium zootheism zootheist zoothecium zootheism zootheist" +
                                            "zootheistic zootherapy zoothome zootheistic zootherapy zoothome" +
                                            "zootic zootoc anovennial zootic zootoc anovennial" +
                                            "novercal Novial novice novercal Novial novice" +
                                            "novicehood novicelike noviceship novicehood novicelike noviceship" +
                                            "computer zoothecia zoothecial computer zoothecia zoothecial" +
                                            "zoothecium zootheism zootheist zoothecium zootheism zootheist" +
                                            "zootheistic zootherapy zoothome zootheistic zootherapy zoothome" +
                                            "zootic zootoc anovennial zootic zootoc anovennial" +
                                            "novercal Novial novice novercal Novial novice" +
                                            "novicehood novicelike noviceship novicehood novicelike noviceship" +
                                            "computer zoothecia zoothecial computer zoothecia zoothecial" +
                                            "zoothecium zootheism zootheist zoothecium zootheism zootheist" +
                                            "zootheistic zootherapy zootheistic zootherapy zoothome" +
                                            "zootic zootoc anovennial zootic zootoc anovennial" +
                                            "novercal Novial novice novercal Novial novice" +
                                            "novicehood novicelike noviceship novicehood novicelike noviceship";

    public static void main(String[] args){
        linearSearchSmallText();
        linearSearchLargeText();
        binarySearchSmallText();
        binarySearchLargeText();
    }

    /**
     * Finds the time required to process the small text using linear search
     */
    public static long linearSearchSmallText(){
        inputWords = parser.parse(SMALL_TEXT);
        scResults = new SpellCheckResult[inputWords.length];

        long start = System.currentTimeMillis();
        for (int i = 0; i < inputWords.length; i++) {
            scResults[i] = spellChecker.check(inputWords[i]);

        }
        long end = System.currentTimeMillis();
        System.out.println("It took " + (end - start) + " ms for the linear search to complete task for small text");
        return end - start;
    }

    /**
     * Finds the time required to process the large text using linear search
     */
    public static long linearSearchLargeText(){
        inputWords = parser.parse(LARGE_TEXT);
        scResults = new SpellCheckResult[inputWords.length];

        long start = System.currentTimeMillis();
        for (int i = 0; i < inputWords.length; i++) {
            scResults[i] = spellChecker.check(inputWords[i]);

        }
        long end = System.currentTimeMillis();
        System.out.println("It took " + (end - start) + " ms for the linear search to complete task for large text");
        return end - start;
    }

    /**
     * Finds the time required to process the small text using binary search
     */
    public static long binarySearchSmallText(){
        inputWords = parser.parse(SMALL_TEXT);
        scResults = new SpellCheckResult[inputWords.length];

        long start = System.currentTimeMillis();
        for (int i = 0; i < inputWords.length; i++) {
            scResults[i] = spellChecker.bsCheck(inputWords[i]);

        }
        long end = System.currentTimeMillis();
        System.out.println("It took " + (end - start) + " ms for the binary search to complete task for small text");
        return end - start;
    }

    /**
     * Finds the time required to process the large text using binary search
     */
    public static long binarySearchLargeText(){
        inputWords = parser.parse(LARGE_TEXT);
        scResults = new SpellCheckResult[inputWords.length];

        long start = System.currentTimeMillis();
        for (int i = 0; i < inputWords.length; i++) {
            scResults[i] = spellChecker.bsCheck(inputWords[i]);
        }
        long end = System.currentTimeMillis();
        System.out.println("It took " + (end - start) + " ms for the binary search to complete task for large text");
        return end - start;
    }
}
