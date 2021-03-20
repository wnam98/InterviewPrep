import java.util.*;

public class Solution{
    //------------------------------Array Questions-----------------------------------------------------------//
    /***
     * LeetCode Easy, popular question asked by Google <p>
     *      *Runtime: O(n) one for-loop that traverses through the elements of the array.
     *      * Space: O(n) new hashset will add all values in nums in the worst case.</p>
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
            }map.put(nums[i], i);
        }return solution;
    }

    /***
     * LeetCode Easy, popular TwoSum with a twist, where the input array is sorted
     * <p>
     * Runtime: O(n) Worst case, the while loop will traverse through all the elements in the array
     * Space: O(1) We're returning a data structure with constant space./p>
     * <p>
     * @param nums Sorted list of numbers.
     * @param target Target sum of any two numbers in the above list.
     * @return indices of the two numbers that add to target
     */
    public int[] newSum(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        while(left < right){
            int sum = nums[left] + nums[right];
            if(sum > target) right--;
            if(sum < target) left++;
            if(sum == target){
                return new int[]{left + 1, right + 1};
            }
        }
        return new int[2];
    }

    /***
     * LeetCode Medium, popular question asked by Google <p>
     *     Runtime: O(N) the N elements go through three reversals.
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

    /***
     *<p>
     *     Explanation: Greedy Algorithm solution. Traverse the matrix, and at
     *     each step find the optimal minimum and append to new DP 2D array.
     *</p>
     * @param grid Takes in an M by N grid containing ints
     * @return Minimum sum from top left to bottom right
     */
    public int minPathSum(int[][] grid){
        if(grid == null || grid.length == 0) return 0;
        int dp[][] = new int[grid.length][grid[0].length];

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                dp[i][j] += grid[i][j];
                if(i > 0 && j > 0){
                    dp[i][j] += Math.min(dp[i - 1][j], dp[i][j - 1]);
                }else if(i > 0){
                    dp[i][j] += dp[i - 1][j];
                }else if(j > 0){
                    dp[i][j] += dp[i][j - 1];
                }
            }
        }

        return dp[grid.length-1][grid[0].length-1];
    }

    /***
     * LeetCode Medium, asked by Google
     * <p>
     * Explanation: Create a DFS helper method to traverse the board. Look through the grid,
     * if we find the first letter, run the helper and look for the other characters.
     * </p>
     * <p>
     * Runtime: O(N) where N is the number of cells in the grid
     * Space: O(N) worst case, we need everything in the board, so N numbers of cells
     * in the grid
     * </p>
     * @param board The 2D array of characters to be inputted.
     * @param word Input string, word to search for.
     * @return boolean value if word is in the grid.
     */

    public boolean wordSearch(char[][] board, String word){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] == word.charAt(0) && wordHelperDFS(board, i, j, 0, word)){
                    return true;
                }
            }
        }return false;
    }

    public boolean wordHelperDFS(char[][] board, int i, int j, int count, String word){
        if(count == word.length()) return true;
        if(i < 0 || i >= board.length || j < 0 || j >= board[i].length || board[i][j] != word.charAt(count)){
            return false;
        }
        char temp = board[i][j];
        board[i][j] = ' ';
        return wordHelperDFS(board, i + 1, j, count + 1, word) ||
                wordHelperDFS(board, i, j + 1,count + 1, word) ||
                wordHelperDFS(board, i - 1, j, count + 1, word) ||
                wordHelperDFS(board, i, j - 1, count + 1, word);
    }

    /**
     * N queens Problem, LeetCode Hard
     * Runtime: O(N^N)
     * Space: O(N * N)
     */
    List<List<String>> res = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        placeQueen(new int[n][n], 0, new HashSet<>(), new HashSet<>(), new HashSet<>());
        return res;
    }

    //recursive helper function
    public void placeQueen(int[][] board, int i, Set<Integer> diagonal, Set<Integer> diagonal2, Set<Integer> vertical){
        if(i == board.length){
            addToList(board);
            return;
        }

        for(int j = 0; j < board.length; j++){
            if(!diagonal.contains(i + j) && !diagonal2.contains(j - i) && !vertical.contains(j)){
                board[i][j] = 1;
                diagonal.add(i + j);
                diagonal2.add(j - i);
                vertical.add(j);
                placeQueen(board, i + 1, diagonal, diagonal2, vertical);
                board[i][j] = 0;
                diagonal.remove(i + j);
                diagonal2.remove(j - i);
                vertical.remove(j);
            }
        }
    }

    public void addToList(int[][] board){
        List<String> list = new ArrayList<>();
        for(int i = 0; i < board.length; i++){
            String temp = "";
            for(int j = 0; j < board.length; j++){
                if(board[i][j] == 0){
                    temp += ".";
                }else{
                    temp += "Q";
                }
            }
            list.add(temp);
        }
        res.add(list);
    }

    /***Permutations, LeetCdde Medium
     * Recursively swap places with the next element.
     * @param nums Input array of nums to be swapped.
     * @return List of lists of numbers that are permutations of the original.
     */
    public List<List<Integer>> permute(int[] nums) {
        //swap each element with each element after it
        List<List<Integer>> result = new ArrayList<>();
        helper(0, nums, result);
        return result;
    }

    private void helper(int start, int[] nums, List<List<Integer>> result){
        if(start == nums.length - 1){
            ArrayList<Integer> list = new ArrayList<>();
            for(int num: nums){
                list.add(num);
            }result.add(list);
            return;
        }

        for(int i = start; i < nums.length; i++){
            swap(nums, i, start);
            helper(start + 1, nums, result);
            swap(nums, i ,start);
        }
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /***
     * LeetCode Easy: Sort both strings as char arrays and then check if they're the same
     * @param s first input string
     * @param t second input string
     * @return boolean value of whether two strings are valid anagrams
     */
    public boolean isAnagram(String s, String t) {
        char[] first = s.toCharArray();
        char[] second = t.toCharArray();
        Arrays.sort(first);
        Arrays.sort(second);
        return Arrays.equals(first, second);
    }

    /**
     * Slalom Build Interview Question, my solution
     *LeetCode Easy: Find the longest common prefix from a list of strings
     * @param list List of strings
     * @return prefix string
     * */

    public String LongestCommonPrefix(List<String> list){
        if(list.size() == 0) return "";
        String prefix = list.get(0);
        for(int i = 0; i < list.size(); i++){
            while(list.get(i).indexOf(prefix) != 0){
                prefix = prefix.substring(0, prefix.length() - 1);
                if(prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    //------------------------------Binary Tree Questions-----------------------------------------------------------//

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

    /***
     * LeetCode Easy, Basic BST question everyone should know
     * <p>
     * Recursively check the depth of the left subtree and right subtree and return the max of those two.
     * Add one to account for the root node at the top.
     * </p>
     * <p>
     * Runtime: O(N) Where N is the number of Nodes in the BST. We traverse through all the nodes of the tree.
     * Space: O(N) Because we make N calls for which the callstack will contain N elements.
     * </p>
     * @param root Root TreeNode of the whole BST
     * @return Integer value of the depth of BST
     */

    public int maxDepthBST(TreeNode root){
        if(root == null) return 0;
        return Math.max(maxDepthBST(root.left), maxDepthBST(root.right)) + 1;
    }

    /***
     * Recursively check the values of left and right subtrees of root. Then check the subtrees of the children
     * <p>
     * Runtime: O(N) Where N is the number of nodes in the BST
     * Space: O(N) Recursive N calls to the callstack
     * </p>
     * @param root Root TreeNode of whole BST
     * @return boolean value whether left subtree is the same as right
     */

    public boolean isSymmetric(TreeNode root){
        if(root == null) return true;
        return isSymmetricHelper(root.left, root.right);
    }

    public boolean isSymmetricHelper(TreeNode left, TreeNode right){
        if(left == null || right == null){
            return left == right;
        }
        if(left.val != right.val) return false;
        return isSymmetricHelper(left.left, right.right) && isSymmetricHelper(left.right, right.left);
    }

    /***
     * <p>
     * Inorder traversal recursive solution. Given a binary tree, return a list of the nodes inorder.
     * Runtime: O(N) where N is the number of nodes in the tree.
     * Space: O(N) for the N nodes in the callstack.
     * </p>
     * @param root Root TreeNode of the tree.
     * @return List of Nodes in order.
     */
    public List<Integer> inorderTraversal(TreeNode root){
        return inorderHelper(root, new ArrayList<>());
    }

    public List<Integer> inorderHelper(TreeNode root, List<Integer> nodeList){
        if(root != null){
            inorderHelper(root.left, nodeList);
            nodeList.add(root.val);
            inorderHelper(root.right, nodeList);
        }
        return nodeList;
    }

    /***
     *LeetCode Medium, asked by Amazon, Linkedin, Google
     * <p>
     *  Explanation: Simple BFS solution, add all the nodes to a queue and return them to get the correct order.
     * </p>
     * <p>
     * Runtime: O(N) where N is the number of TreeNodes.
     * Space: O(N) worst case, the queue will stack all the Nodes.
     * </p>
     * @param root reference to current TreeNode in traversal
     * @return Lists of TreeNodes in the correct in-order
     */

    public List<List<Integer>> levelOrder(TreeNode root){
        List<List<Integer>> answer = new ArrayList<>();
        if(root == null) return answer;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            for(int i = 0; i < size; i++){
                TreeNode current = queue.remove();
                currentLevel.add(current.val);
                if(current.left != null) queue.add(current.left);
                if(current.right != null) queue.add(current.right);
            }
            answer.add(currentLevel);
        }return answer;
    }

    /***
     * Explanation: Recursively check that p and q values in the left of the root are less than root val/
     * Do the same for the right side.
     * <p>
     * Runtime: O(N) worst case, we traverse through all N nodes of the BST.
     * Space: O(N) worst case, the recursion stack pushes all N nodes.
     * </p>
     * @param  root Parent Node
     * @param p First Node of interest
     * @param q Second Node of interest
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        if(p.val < root.val && q.val < root.val) return lowestCommonAncestor(root.left, p, q);
        if(p.val > root.val && q.val > root.val) return lowestCommonAncestor(root.right, p, q);
        return root;
    }

    /***
     * LeetCode Easy, asked by Google
     * <p>
     *  Explanation: Define two bases cases, where both trees are null or either are
     *  null. Then, recursively determine whether left and right subtrees have equivalent Node
     *  values.
     * </p>
     * <p>
     *     Runtime: O(N) worst case we traverse through all the nodes N.
     *     Space: O(N) recursion stack will push all N nodes worst case.
     * </p>
     * @param first Reference to first Binary Tree
     * @param second Reference to second Binary Tree
     * @return boolean value whether two trees are equal
     */

    public boolean isSameTree(TreeNode first, TreeNode second){
        if(first == null && second == null) return true;
        if(first == null || second == null)return false;
        return first.val == second.val && isSameTree(first.left, second.left)
                && isSameTree(first.right, second.right);
    }

    /***
     * LeetCode Easy, asked by Google.
     * <p>
     *     Explanation: Basecase, if the root is null, return 0. Recrusively check each node for a left child
     *     and whether it's a leaf. Then aggregate the sum and return the value.
     * </p>
     * <p>
     *     Runtime: O(N) Worst case, traverse through all N nodes of the tree.
     *     Space: O(N) Worst case, recursive stack pushes all N nodes.
     * </p>
     * @param root Root Node of the input tree.
     * @return sum of the left nodes of the tree.
     */

    public int sumOfLeftLeaves(TreeNode root){
        if(root == null) return 0;
        int sum = 0;
        //check if there exists a left child of the root. Next, check if it's a leaf.
        if(root.left != null && root.left.left == null && root.left.right == null){
            sum += root.left.val;
        }
        return sum + sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }

    /***
     * LeetCode Medium, asked by Google.
     * <p>
     *     Explanation: Basic BFS because we are performing a top to bottom exploration.
     *     Remember, an inorder traversal of a Binary Tree is left, root, right. We use a FIFO
     *     queue to traverse a binary tree in order. We have a check to ensure that our current
     *     reference TreeNode is indeed the right child of the root (in comparing the size of the queue
     *     with our current iteration index). Next, we check their child and add their children to
     *     the queue accordingly. After we break out of the while loop, we simply return the list of node values.
     * </p>
     * <p>
     *     Runtime: O(N) Worst case, we traverse through all N of the nodes.
     *     Space: O(N) Worst case, the queue pushes in all N of the nodes.
     * </p>
     * @param root Root node of input tree
     * @return List of all the TreeNodes that would be seen if viewing from the right side.
     */
    public List<Integer> rightSideView(TreeNode root){
        List<Integer> visibleValues = new ArrayList<>();
        if(root == null) return visibleValues;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                TreeNode current = queue.remove();
                if(i == size - 1){
                    visibleValues.add(current.val);
                }
                if(current.left != null){
                    queue.add(current.left);
                }
                if(current.right != null){
                    queue.add(current.right);
                }
            }
        }return visibleValues;
    }

    /***
     * LeetCode Medium, asked by Google
     * <p>
     *     Explanation: Given a Binary Tree, and Nodes to delete, return the forest. We will do
     *     a bottom-up DFS traversal, in case we are on a root node to delete and would subsequently lose
     *     references to its children. First, we append all the values of the to_delete array into a
     *     HashSet. We write a recursive helper function that takes in the nodes to delete list and the
     *     list of remaining nodes to return. We include a check in-case we need to push in the root node.
     *     Recursively check the left and right subtrees of the root to append nodes to the forest list. Else, we
     *     return the root, or reference to the whole tree.
     * </p>
     * <p>
     *     Runtime: O(N) where N is the number of nodes in the Tree.
     *     Space: O(N) where N is the number of nodes in the Tree.
     * </p>
     */
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        //bottom up traversal of the Tree
        //DFS, move down the tree move to below the trees, return null, then keep or remove
        List<TreeNode> remaining = new ArrayList<>();
        Set<Integer> toDelete = new HashSet<>();
        for(int i: to_delete){
            toDelete.add(i);
        }
        helper(root, toDelete, remaining);
        if(!toDelete.contains(root.val)) remaining.add(root);
        return remaining;
    }

    public TreeNode helper(TreeNode root, Set<Integer> toDelete, List<TreeNode> remaining){
        if(root == null) return null;
        root.left = helper(root.left, toDelete, remaining);
        root.right = helper(root.right, toDelete, remaining);
        if(toDelete.contains(root.val)) {
            if(root.left != null) remaining.add(root.left);
            if(root.right != null) remaining.add(root.right);
            return null;
        }

        return root;
    }

    /***
     * LeetCode Medium, asked by Google
     * <p>
     * Explanation: Find the longest consecutive sequence path from parent to child nodes.
     * Keep a list of maximum values and recursively check their values with the TreeNode values.
     * At each Node, check if its value is the same as the previous iteration Node + 1.
     * </p>
     * <p>
     * Runtime: O(N) Worst case, our binary tree is a long linked list of N nodes to traverse
     * Space: O(N) Recursive calls could also be N levels deep
     * </p>
     * @param root Reference to the
     * @return
     */

    public int longestConsecutive(TreeNode root){
        int[] max = new int[1];
        findLongestHelper(root, 0, 0, max);
        return max[0];
    }

    public void findLongestHelper(TreeNode root, int count, int target, int[] max){
        if(root == null) return;
        if(root.val == target) count++;
        count = 1;
        max[0] = Math.max(max[0], count);
        findLongestHelper(root.left, count, root.val + 1, max);
        findLongestHelper(root.right, count, root.val + 1, max);
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
     * LeetCode Hard, popular question asked by Facebook
     * <p>
     * Explanation: traverse through the array of all the LinkedLists and add each node to a minHeap.
     * Create a new LinkedList and append all the ListNodes from the minHeap into the new list.
     * </p>
     * <p>
     * Runtime: O(N*M log(N*M)) where N is the max number of lists and M is the max number of nodes of a list.
     * Space: O(N*M)
     * </p>
     * @param lists List of LinkedLists of any size
     * @return new sorted LinkedList
     */
    public ListNode mergeKSorted(ListNode[] lists){
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(ListNode head: lists){
            while(head != null){
                minHeap.add(head.val);
                head = head.next;
            }
        }

        //create a dummy node to start the new list
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;
        while(!minHeap.isEmpty()){
            head.next = new ListNode(minHeap.remove());
            head = head.next;
        }
        return dummy.next;
    }

    /***
     * LeetCode Easy, popular question asked by Google
     * Detect a cycle in a LinkedList
     * <p>
     *     Explanation: slow pointer moves one node at a time and the fast moves two
     *     at a time. When they collide, there's a cycle.
     * </p>
     * <p>
     *     Runtime: O(N) worst case, we traverse through all N elements
     *     Space: O(1) no extra data structure, the traversal is done in-place
     * </p>
     */

    public boolean LinkedListCycle(ListNode head){
        if(head == null) return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while(slow != fast){
            if(slow == null || fast == null) return false;
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    /***
     * LeetCode Medium, asked by Amazon
     * <p>
     * Explanation: We can use a two-pointer solution, with a slow and fast pointer.
     * We traverse the List N times until the fast pointer reaches the N + 1th index. Then we simply
     * delete the Node at the Nth index by setting the slow pointer to next.next. We can then return
     * the new List.
     * </p>
     * <p>
     *     Runtime: O(N) worst case, we traverse through all N nodes of the list
     *     Space: O(1) we don't allocate extra memory for a new data structure. Everything is performed
     *     in-place.
     * </p>
     * @param head reference to the first ListNode in the LinkedList.
     * @param n number of the ListNode we wish to remove.
     * @return new shortened LinkedList.
     */

    public ListNode removeNthFromEnd(ListNode head, int n){
        ListNode dummy = new ListNode(0);
        dummy = head.next;
        ListNode slow = dummy;
        ListNode fast = dummy;

        for(int i = 1; i <= n + 1; i++){
            fast = fast.next;
        }

        while(fast != null){
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    //------------------------------Graph Questions-----------------------------------------------------------//
	// Definition for a Node.
    class Node {
	    public int val;
	    public List<Node> neighbors;

	    public Node() {
		val = 0;
		neighbors = new ArrayList<Node>();
	    }

	    public Node(int _val) {
		val = _val;
		neighbors = new ArrayList<Node>();
	    }

	    public Node(int _val, ArrayList<Node> _neighbors) {
		val = _val;
		neighbors = _neighbors;
	    }
    }
    
    HashMap<Node, Node> found = new HashMap<>();

    /**LeetCode Medium
     * Given a reference of a node in a connected undirected graph. 
     * Return a deep copy (clone) of the graph.
     * @param "root" pointer node
     * @return the same root pointer node
     */
    public Node cloneGraph(Node node) {
        if(node == null)
            return null;
        if(found.containsKey(node))
            return found.get(node);
        var newNode = new Node(node.val);
        found.put(node, newNode);
        List<Node> neighbors = new ArrayList<>();
        for(Node neighbor : node.neighbors){
            neighbors.add(cloneGraph(neighbor));
        }
        newNode.neighbors = neighbors;
        return newNode;
    }

    /***
     * Driver method
     */
    public static void main(String[] args){
        int[] array = {1,2,3,4,5};
        int target = 7;

        List<String> strings = new ArrayList<>();
        strings.add("boat");
        strings.add("boar");
        strings.add("board");

        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.twoSum(array, target)));

        int k = 2;
        solution.rotateArray(array, k);
        System.out.println(Arrays.toString(array));
        System.out.println(solution.LongestCommonPrefix(strings));
    }
}
