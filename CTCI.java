import java.util.*;

public class CTCI{
    //-------------------------Chapter 1: Arrays and Strings----------------------------------------------------//

    /***
     * Linear time solution using extra data-structure
     * @param string Input string to test
     * @return boolean value if string is all unique characters
     */
    public boolean isUnique(String string){
        if(string == null || string.length() == 0) return false;
        char[] chars = string.toLowerCase().toCharArray();
        HashSet<Character> uniques = new HashSet<>();
        for(char c: chars){
            if(uniques.contains(c)){
                return false;
            }uniques.add(c);
        }return true;
    }

    /***
     * Linear time solution with no extra memory
     * <p>
     *  Two pointer solution: first moves to the right, second moves to left. If they
     *  have a same value, return false.
     * </p>
     * <p>
     * Runtime: O(N) linear traversal through the whole array with N the # of elements.
     * Space: O(1) no extra data structure, comparisons are done in-place.
     * </p>
     * @param string Input string to test
     */
    public boolean isUniqueOptimal(String string){
        if(string == null || string.length() == 0) return false;
        char[] array = string.toLowerCase().toCharArray();
        int first = 0;
        int second = array.length - 1;
        while(first != second){
            if(array[first] == array[second]) return false;
            first++;
            second--;
        }return true;
    }

    /***
     *Naive solution: Sort the characters of both string and return whether they are equal
     * <p>
     * Runtime: O(NlogN) where N is the number of characters in both strings combined
     * Space: O(N) where N is the number of characters in both strings combined
     * </p>
     * @param first First input string
     * @param second Second input string
     * @return boolean value if both strings are permutations of each other
     */

    public boolean checkPermutation(String first, String second){
        if(first.length() != second.length()) return false;
        char[] firstArr = first.toLowerCase().toCharArray();
        char[] secondArr = second.toLowerCase().toCharArray();
        Arrays.sort(firstArr);
        Arrays.sort(secondArr);
        return Arrays.equals(firstArr, secondArr);
    }

    /***
     * Solution using third-party library. Not sure if allowed?
     * @param string Input String
     * @return 'URLified' string
     */
    public String URLify(String string){
        return string.replace(" ", "%20");
    }

    public static void main(String[] args) {
        CTCI ctci = new CTCI();
        String test = "Mr John Smith";
        System.out.println(ctci.URLify(test));
    }
}