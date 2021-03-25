import java.util.*;

public class Codility {
    //-----------------Chapter 1: Iterations-----------------------------------------------//

	/***
	 * Problem Statement:
	 * <p>
	 *A binary gap within a positive integer N is any maximal sequence of consecutive zeros that is surrounded by ones
	 *  at both ends in the binary representation of N. For example, number 9 has binary representation 1001 and contains a
	 * binary gap of length 2. The number 529 has binary representation 1000010001 and contains two binary gaps: one of
	 * length 4 and one of length 3. The number 20 has binary representation 10100 and contains one binary gap of length 1.
	 * The number 15 has binary representation 1111 and has no binary gaps. The number 32 has binary representation 100000
	 * and has no binary gaps.
	 * <p/>
	 * <p>
	 * Write a function:
	 * class Solution { public int solution(int N); }
	 * that, given a positive integer N, returns the length of its longest binary gap. The function should return 0 if N doesn't contain a binary gap.
	 * For example, given N = 1041 the function should return 5, because N has binary representation 10000010001 and so its longest binary gap is of length 5. Given N = 32 the function should return 0, because N has binary representation '100000' and thus no binary gaps.
	 * Write an efficient algorithm for the following assumptions:
	 * N is an integer within the range [1..2,147,483,647].
	 *<p/>
	 * ***/
	public int binaryGap(int N) {
        String binary = Integer.toBinaryString(N);

		int max = 0;
		int start = binary.indexOf("1");

		if(start == -1)
			return 0;
		while(true){
			int index = binary.indexOf("1", start + 1);
			if(index == -1){
				break;
			}
			int diff = index - start - 1;
			max = Math.max(diff, max);
			start = index;
		}
		return max;
    }

    //---------------------------Chapter 2: Arrays-------------------------------------------//

	/***
	 * An array A consisting of N integers is given. Rotation of the array means that each element is shifted right by one index, and the last element of the array is moved to the first place. For example, the rotation of array A = [3, 8, 9, 7, 6] is [6, 3, 8, 9, 7] (elements are shifted right by one index and 6 is moved to the first place).
	 * The goal is to rotate array A K times; that is, each element of A will be shifted to the right K times.
	 * Write a function:
	 * class Solution { public int[] solution(int[] A, int K); }
	 * that, given an array A consisting of N integers and an integer K, returns the array A rotated K times.
	 * For example, given
	 *     A = [3, 8, 9, 7, 6]
	 *     K = 3
	 * the function should return [9, 7, 6, 3, 8]. Three rotations were made:
	 *     [3, 8, 9, 7, 6] -> [6, 3, 8, 9, 7]
	 *     [6, 3, 8, 9, 7] -> [7, 6, 3, 8, 9]
	 *     [7, 6, 3, 8, 9] -> [9, 7, 6, 3, 8]
	 * For another example, given
	 *     A = [0, 0, 0]
	 *     K = 1
	 * the function should return [0, 0, 0]
	 *
	 * Given
	 *
	 *     A = [1, 2, 3, 4]
	 *     K = 4
	 * the function should return [1, 2, 3, 4]
	 *
	 * Assume that:
	 *
	 * N and K are integers within the range [0..100];
	 * each element of array A is an integer within the range [−1,000..1,000].
	 * In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.
	 *
	 * ***/

	public int[] rotateArray(int[] A, int K){
		K = K % A.length;
		shift(0, A.length - 1, A);
		shift(0, K - 1, A);
		shift(K, A.length - 1, A);
		return A;
	}

	public void shift(int start, int end, int[] array){
		while(start < end){
			int temp = array[start];
			array[start] = array[end];
			array[end] = temp;
			start++;
			end--;
		}
	}

    //Driver method
    public static void main(String[] args) {
        Codility solution = new Codility();
        System.out.println(solution.binaryGap(100));
        int[] test = {3, 8, 9, 7, 6};
		System.out.println(Arrays.toString(solution.rotateArray(test, 3)));
    }
}
