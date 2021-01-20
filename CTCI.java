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

    /**
     * Approach 1: HashMap
     * Algorithm
     * This hashmap takes the form (character_i, number of occurences of character_i).
     * We traverse over the given string s.
     * For every new character found in s, we create a new entry in the map for this character with the number of occurences as 1.
     * Whenever we find the same character again, we update the number of occurences appropriately.
     *
     * At the end, we traverse over the map created and find the number of characters with odd number of occurences.
     * If this count happens to exceed 1 at any step, we conclude that a palindromic permutation isn't possible for the string s.
     * But, if we can reach the end of the string with count lesser than 2, we conclude that a palindromic permutation is possible for s.
     *
     * Complexity Analysis
     * Time complexity : O(n).
     * We traverse over the given string s with n characters once.
     * We also traverse over the map which can grow upto a size of n in case all characters in s are distinct.
     * Space complexity : O(n). The hashmap can grow upto a size of n, in case all the characters in s are distinct.
     */

    public boolean PalindromePermutation(String string){
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < string.length(); i++){
            map.put(string.charAt(i), map.getOrDefault(string.charAt(i), 0) + 1);
        }
        int count = 0;
        for(char key: map.keySet()){
            count += map.get(key) % 2;
        }
        return count <= 1;
    }

    public static void main(String[] args) {
        CTCI ctci = new CTCI();
        String test = "tactcoa";
        System.out.println(ctci.PalindromePermutation(test));
    }
}