package uk.ac.standrews.cs2001.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

/**
 * This class provides a static method loadTheDictionary which was modified to allow user specify it is own path.
 * <p>It reads the words from a file and returns them as an array of strings in alphabetical order.</p>
 */
class DictionaryLoader {

    public static final String DICTIONARY_PATH = "/usr/share/dict/words";

    /**
     * This method loads the dictionary depending on the path specified by user.
     * @param isOwnPath if true, it will use path specified by user; if false, it will use default path
     * @param ownPath path that was specified by user
     * @return array of words in dictionary
     */
    public static String[] loadDictionary(boolean isOwnPath, String ownPath) {
        String path;
        if (isOwnPath){
            path = ownPath;
        }
        else{
            path = DICTIONARY_PATH;
        }

        try {
            Scanner s = new Scanner(new BufferedReader(new FileReader(path)));
            List<String> dictionary = new ArrayList<>();

            while (s.hasNext()) {
                dictionary.add(s.next().toLowerCase());
            }

            String[] a = dictionary.toArray(new String[dictionary.size()]);
            Arrays.sort(a);
            return a;

        } catch (FileNotFoundException e) {
            System.err.println("Standard dictionary file " + path + " missing");
            return new String[0];
        }
    }
}
