package ctci;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by chungnguyen on 6/26/17.
 */
public class ArraysAndStrings {

    /**
     * Is Unique: Implement an algorithm to determine if a string has all unique characters. What if you cannot use
     * additional data structures?
     * Example: "apple" == false
     * Example: "sequoia" == true
     */
    public boolean isUnique(String string) {
        // hash table
        return true;
    }

    public boolean isUniqueInPlace(String string) {
        // sort the string
        return true;
    }

    /**
     *
     */
    public boolean isPermutationPalindrome(String string) {
        // a palindrome at the most can have only one letter with an odd count
        // so if it's not a palindrome, then return false
        Map<Character, Integer> map = new HashMap<>();

        // loop through each letter in the string
        for (int i = 0; i < string.length(); i++) {

            // get the character
            Character character = string.charAt(i);

            // check if it's already in map
            if (map.get(character) != null) {
                // if it is, then increment the count
                Integer value = map.get(character);
                map.put(character, value + 1);
            } else {
                // if not, then put it in the dictionary with 1
                map.put(character, 1);
            }
        }

        // count number of odd values in map; if greater than 1, then return false
        Integer count = 0;
        for (Integer value : map.values()) {
            if (value % 2 != 0) {
                count += 1;
            }
        }

        return count > 1;
    }

}
