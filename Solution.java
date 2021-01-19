import java.util.*;

public class Solution{

    /***
     * LeetCode Easy, popular question asked by Google <p>
     *Runtime: O(n) one for-loop that traverses through the elements of the array.
     * Space: O(n) new hashset will add all values in nums in the worst case.</p>
     * @param nums List of numbers
     * @param target Target sum of any two numbers in the above list.
     */
    public int[] twoSum(int[] nums, int target){
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] solution = new int[2];
        for(int i = 0; i < nums.length; i++){
            int difference = target - nums[i];
            if(map.containsKey(difference)){
                solution[0] = i;
                solution[1] = map.get(difference);
                return solution;
            }map.put(i, nums[i]);
        }return solution;
    }

    /***
     * LeetCode medium, popular question asked by Google <p>
     *     Runtime: O(n) because the first step requires rotating the whole array.
     *     Space: O(1) all the rotating is performed in-place.
     * </p>
     * @param nums List of numbers to be rotated
     * @param k Bound of reversal
     */

    public void rotateArray(int[] nums, int k){
        k = k % nums.length;
        reverseArray(nums, 0, nums.length - 1);
        reverseArray(nums, 0, k - 1);
        reverseArray(nums, k, nums.length - 1);
    }

    /***
     * Auxillary array reversal method for rotateArray.
     * @param array Input Array
     * @param start First index to begin reversal
     * @param end Last index of reversal
     */
    public void reverseArray(int[] array, int start, int end){
        while(start < end){
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }

    /***
     * Driver method
     */
    public static void main(String[] args){
        int[] array = {1,2,3,4,5};
        int target = 7;

        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.twoSum(array, target)));

        int k = 2;
        solution.rotateArray(array, k);
        System.out.println(Arrays.toString(array));
    }
}
