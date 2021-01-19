import java.util.*;

public class Solution{

    /***
     * LeetCode Easy, popular question asked by Google
     *Runtime: O(n) one for-loop that traverses through the elements of the array.
     * Space: O(n) new hashset will add all values in nums in the worst case.
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


    public static void main(String[] args){
        int[] array = {1,2,3,4,5};
        int target = 7;
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.twoSum(array, target)));
    }
}
