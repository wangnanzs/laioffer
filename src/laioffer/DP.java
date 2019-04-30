package laioffer;

import java.util.Arrays;

public class DP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = new int[]{1,2,4,-10,-20,4,5,6,7,8,9};
		int[] result;
		result = DPsolution.largestSubArraySum(array);
		System.out.println(result[0]);
		System.out.println(result[1]);
		System.out.println(result[2]);
		int[][] matrix = new int[7][4];
		Arrays.fill(matrix[0], 1);
		Arrays.fill(matrix[1], 1);
		Arrays.fill(matrix[2], 1);
		Arrays.fill(matrix[3], 1);
		Arrays.fill(matrix[4], 1);
		Arrays.fill(matrix[5], 1);
		Arrays.fill(matrix[6], 1);
		System.out.println(DPsolution.longestX(matrix));
	}
}
class DPsolution{
	public static int[] largestSubArraySum(int[] array) {
		//DP ..
		int[] result = new int[3];
		int[] M = new int[array.length];
		//Base Case
		M[0] = array[0];
		// M[i] represents the largest subarray sum that ends at index i
		// M[i] = M[i-1] + array[i] if M[i-1]>=0
		// M[i] = array[i] if M[i]<0
		int curLeft = 0;
		int globalLeft = 0;
		int globalRight = 0;
		int globalMax = M[0];
		for(int i =1;i<M.length;i++) {
			if(M[i-1]>=0) {
				M[i] = M[i-1] + array[i];
			}else {
				M[i] = array[i];
				curLeft = i;
			}
			if(M[i]>globalMax) {
				globalLeft = curLeft;
				globalRight = i;
				globalMax = M[i];
			}
		}
		result[0] = globalMax;
		result[1] = globalLeft;
		result[2] = globalRight;
		return result;
	}
	// find the longest X in a matrix
	public static int longestX(int[][] matrix) {
		// DP 
//		int[][] M = new int[matrix.length][matrix[0].length];
		// Find the longest consecutive 1s in 4 directions: top2bot, left2right, bottom2top,right2left
		// Pre-process
		int[][] min = findLongest(matrix);
		int globalMax = 0;
		for(int i=0;i<min.length;i++) {
			for(int j=0;j<min[0].length;j++) {
//				M[i][j] = Math.min(topleft[i][j], bottomright[i][j]);
				globalMax = Math.max(globalMax, min[i][j]);
			}
		}
		return globalMax;
	}
	private static int[][] findLongest(int[][] matrix) {
		//DP 
		int N = matrix.length;
		int M = matrix[0].length;
		int[][] M1 = new int[N][M];
		int[][] M2 = new int[N][M];
		int[][] M3 = new int[N][M];
		int[][] M4 = new int[N][M];
		int[][] min = new int[N][M];
		// Base case
//		left[0][0] = matrix[0][0];
//		up[0][0] = matrix[0][0];
		// Induction rule
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(matrix[i][j]==1) {
					M1[i][j] = getValue(M1,i-1,j-1)+1;
				}else {
					M1[i][j] = 0;
				}
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=M-1;j>=0;j--) {
				if(matrix[i][j]==1) {
					M2[i][j] = getValue(M2,i-1,j+1)+1;
				}else {
					M2[i][j] = 0;
				}
			}
		}
		for(int i=N-1;i>=0;i--) {
			for(int j=0;j<M;j++) {
				if(matrix[i][j]==1) {
					M3[i][j] = getValue(M3,i+1,j-1)+1;
				}else {
					M3[i][j] = 0;
				}
			}
		}
		for(int i=N-1;i>=0;i--) {
			for(int j=M-1;j>=0;j--) {
				if(matrix[i][j]==1) {
					M4[i][j] = getValue(M4,i+1,j+1)+1;
				}else {
					M4[i][j] = 0;
				}
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				min[i][j] = Math.min(Math.min(M1[i][j], M2[i][j]), Math.min(M3[i][j], M4[i][j]));
			}
		}
		return min;
	}
	private static int[][] findLongestFromBottom(int[][] matrix) {
		//DP 
		int N = matrix.length;
		int M = matrix[0].length;
		int[][] right = new int[N][M];
		int[][] bottom = new int[N][M];
		int[][] min = new int[N][M];
		// Base case
//		left[0][0] = matrix[0][0];
//		up[0][0] = matrix[0][0];
		// Induction rule
		for(int i=N-1;i>=0;i--) {
			for(int j=M-1;j>=0;j--) {
				if(matrix[i][j]==1) {
					right[i][j] = getValue(right,i,j+1)+1;
					bottom[i][j] = getValue(bottom,i+1,j)+1;
				}else {
					right[i][j] = 0;
					bottom[i][j] = 0;
				}
				min[i][j] = Math.min(right[i][j], bottom[i][j]);
			}
		}
		return min;
	}
	
	// find the largest orthgonal cross in a matrix
	public static int longestCross(int[][] matrix) {
		// DP 
		int[][] M = new int[matrix.length][matrix[0].length];
		// Find the longest consecutive 1s in 4 directions: top2bot, left2right, bottom2top,right2left
		// Pre-process
		int[][] topleft = findLongestTopLeft(matrix);
		int[][] bottomright = findLongestBottomRight(matrix);
		int globalMax = 0;
		for(int i=0;i<M.length;i++) {
			for(int j=0;j<M[0].length;j++) {
				M[i][j] = Math.min(topleft[i][j], bottomright[i][j]);
				globalMax = Math.max(globalMax, M[i][j]);
			}
		}
		return globalMax;
	}
	private static int[][] findLongestTopLeft(int[][] matrix) {
		//DP 
		int N = matrix.length;
		int M = matrix[0].length;
		int[][] left = new int[N][M];
		int[][] top = new int[N][M];
		int[][] min = new int[N][M];
		// Base case
//		left[0][0] = matrix[0][0];
//		up[0][0] = matrix[0][0];
		// Induction rule
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(matrix[i][j]==1) {
					left[i][j] = getValue(left,i,j-1)+1;
					top[i][j] = getValue(top,i-1,j)+1;
				}else {
					left[i][j] = 0;
					top[i][j] = 0;
				}
				min[i][j] = Math.min(left[i][j], top[i][j]);
			}
		}
		return min;
	}
	private static int[][] findLongestBottomRight(int[][] matrix) {
		//DP 
		int N = matrix.length;
		int M = matrix[0].length;
		int[][] right = new int[N][M];
		int[][] bottom = new int[N][M];
		int[][] min = new int[N][M];
		// Base case
//		left[0][0] = matrix[0][0];
//		up[0][0] = matrix[0][0];
		// Induction rule
		for(int i=N-1;i>=0;i--) {
			for(int j=M-1;j>=0;j--) {
				if(matrix[i][j]==1) {
					right[i][j] = getValue(right,i,j+1)+1;
					bottom[i][j] = getValue(bottom,i+1,j)+1;
				}else {
					right[i][j] = 0;
					bottom[i][j] = 0;
				}
				min[i][j] = Math.min(right[i][j], bottom[i][j]);
			}
		}
		return min;
	}
	private static int getValue(int[][] matrix, int i, int j) {
		if(i<0 || j<0 || i == matrix.length || j == matrix[0].length) {
			return 0;
		}
		return matrix[i][j];
	}
}
