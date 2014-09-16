package uk.ac.standrews.cs2001;

public class SpellCheckBenchmark {

    private static SpellCheckerParser parser = new SpellCheckerParser();
    private static SpellChecker spellChecker = new SpellChecker();
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

    public static void linearSearchSmallText(){
        inputWords = parser.parse(SMALL_TEXT);
        scResults = new SpellCheckResult[inputWords.length];

        long start = System.currentTimeMillis();
        for (int i = 0; i < inputWords.length; i++) {
            scResults[i] = spellChecker.check(inputWords[i]);

        }
        long end = System.currentTimeMillis();
        System.out.println("It took " + (end - start) + " ms for the linear search to complete task for small text");
    }
    public static void linearSearchLargeText(){
        inputWords = parser.parse(LARGE_TEXT);
        scResults = new SpellCheckResult[inputWords.length];

        long start = System.currentTimeMillis();
        for (int i = 0; i < inputWords.length; i++) {
            scResults[i] = spellChecker.check(inputWords[i]);

        }
        long end = System.currentTimeMillis();
        System.out.println("It took " + (end - start) + " ms for the linear search to complete task for large text");
    }
    public static void binarySearchSmallText(){
        inputWords = parser.parse(SMALL_TEXT);
        scResults = new SpellCheckResult[inputWords.length];

        long start = System.currentTimeMillis();
        for (int i = 0; i < inputWords.length; i++) {
            scResults[i] = spellChecker.bsCheck(inputWords[i]);

        }
        long end = System.currentTimeMillis();
        System.out.println("It took " + (end - start) + " ms for the binary search to complete task for small text");
    }
    public static void binarySearchLargeText(){
        inputWords = parser.parse(LARGE_TEXT);
        scResults = new SpellCheckResult[inputWords.length];

        long start = System.currentTimeMillis();
        for (int i = 0; i < inputWords.length; i++) {
            scResults[i] = spellChecker.bsCheck(inputWords[i]);
        }
        long end = System.currentTimeMillis();
        System.out.println("It took " + (end - start) + " ms for the binary search to complete task for large text");
    }
}
