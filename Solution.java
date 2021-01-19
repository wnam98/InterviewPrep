import java.util.*;

public class Solution{
    //------------------------------Array Questions-----------------------------------------------------------//
    /***
     * LeetCode Easy, popular question asked by Google <p>
     *Runtime: O(n) one for-loop that traverses through the elements of the array.
     * Space: O(n) new hashset will add all values in nums in the worst case.</p>
     *<p>
     * Explanation: Create a hashmap that maps the array elements to their indices. Then create
     * a new variable difference which is the difference between target and an element at a specific
     * index during iteration. If there exists an element in the array that equals the difference, add that element
     * to the solution vector along with its index.
     *</p>
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
     * LeetCode Medium, popular question asked by Google <p>
     *     Runtime: O(n) because the first step requires rotating the whole array.
     *     Space: O(1) all the rotating is performed in-place.
     * </p>
     * <p>
     *  Explanation: Reverse all the elements in the array. Then reverse the elements again from the
     *  first index to the k - 1th index. Finally, reverse the remaining elements of the array and now
     *  you have your rotated array.
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
     * LeetCode Medium, popular question asked by Amazon.
     * <p>
     * The easiest solution would be to mergesort the array and then return nums.length - k.
     * However, this is rather costly at O(k(N log N)). What do we do?
     * We use an auxillary data structure: the minHeap. Add the elements to the minHeap until it contains
     * k elements, then remove the kth element and return it.
     * </p>
     * <p>
     * Runtime: O(N log k) Add N elements to the minHeap and the cost of remove is log k.
     * Space: O(k) number of elements in the minHeap.
     * </p>
     * @param nums input array
     * @param k index of element to be returned if sorted
     * @return kth largest element
     */
    public int KthLargestArray(int[] nums, int k){
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int num: nums){
            minHeap.add(num);
            if(minHeap.size() > k){
                minHeap.remove();
            }
        }return minHeap.remove();
    }
    //------------------------------BST Questions-----------------------------------------------------------//

    /***
     * LeetCode TreeNode definition. Contains a val, constructor, and references to its children.
     */
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){}
        TreeNode(int val){this.val = val;}
        TreeNode(int val, TreeNode left, TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    /***
     * LeetCode Medium, asked by Facebook
     *<p>
     * Runtime: O(n) because we iterate through the whole tree definitively. N is the number of nodes in the tree
     * Space: O(n) worst case, our tree is a LinkedList with N being the number of nodes.
     *</p>
     * @param root the current TreeNode during iteration
     * @return Binary Tree is a valid BST
     */

    public boolean isValidBST(TreeNode root){
        return BSTHelper(root, null, null);
    }

    /***
     * Auxillary recursive function for validating BST. Our base case is root == null because
     * that means we had successfully traversed through the tree down to the leaf without hitting a
     * condition that would return false. Finally, validate the left subtree and right subtree.
     * @param root the current TreeNode at iteration
     * @param min (Integer object) reference to an "absolute minimum" to be compared to the left child
     * @param max (Integer object) reference to an "absolute maximum" to be compared to the right child
     * @return Binary Tree is a valid BST
     */

    public boolean BSTHelper(TreeNode root, Integer min, Integer max){
        if(root == null) return true;
        if(min != null && root.val <= min || max != null && root.val >= max){
            return false;
        }
        return BSTHelper(root.left, min, root.val) && BSTHelper(root.right, root.val, max);
    }

    //------------------------------LinkedList Questions-----------------------------------------------------------//
    /***
     * LeetCode ListNode definition
     */
    public class ListNode{
        int val;
        ListNode next;
        ListNode(){}
        ListNode(int val){this.val = val;}
        ListNode(int val, ListNode next){this.val = val; this.next = next;}
    }

    /***
     * LeetCode Easy, asked by Amazon, failed by JomaTech
     * <p>
     * Explanation: Basic traversal, head = head.next until reaching null. First, have access to a previous node,
     * so create a reference and set it to null (because there's nothing previous to the first node). Now set the
     * head.next = prev (reverse the pointers) then set the prev to head, then head to next. Reverse all the pointers essentially,
     * as you traverse through the list.
     *<p>
     * prev -> head -> head.next (next)
     * (next) head.next -> head -> prev
     * Now prev is the new head, so return prev
     *</p>
     * <p>
     * Runtime: O(N) where N is the number of nodes in the list.
     * Space: O(1) all the reversing is done in-place. </p>
     * </p>
     * @param head reference to first ListNode in the list.
     * @return reversed LinkedList
     */

    public ListNode reverseList(ListNode head){
        ListNode prev = null;
        while(head != null){
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }return prev;
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