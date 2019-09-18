package round2;
import java.util.*;
public class Test {
	public static void main(String[] args) {
		TreeGenerator tg = new TreeGenerator();
		TreeSolution ts = new TreeSolution();
//		Integer[] input = new Integer[] {1,2,3,4,5,6,7,8,9};
//		Integer[] input = new Integer[] {5,3,8,1,4,7,9,null,2,null,null,6,null,null,null}; // BST
//		TreeNode root = tg.generate(input);
		
//		int[] array = new int[] {1,2,3,4,5,6,7,8,9,10};
//		BinarySearch bs = new BinarySearch();
//		System.out.println(bs.smallestLargerThan(array, 11));
//		System.out.println(ts.smallestLargerThan(root, 8));
//		System.out.println(ts.getDepth(root, 10));
		
		// === Testing reconstruct ===============
//		int[] in = new int[] {1,3,4,5,8,11};
//		int[] post = new int[] {1,4,3,11,8,5};
//		TreeNode root = ts.reconstruct(in, post);
//		List<Integer> list = ts.inOrderIterative(root);
//		for(Integer i : list) {
//			System.out.print(i+" ");
//		}
//		System.out.println();
		
		// === Testing constructPostOrder =========
//		int[] in = new int[] {4,2,5,1,3,6};
//		int[] pre = new int[] {1,2,4,5,3,6};
//		int[] post = ts.constructPostOrder(in, pre);
//		for(int i : post) {
//			System.out.print(i+" ");
//		}
		
		// === Testing expressionTree =============
//		ExpNode res = ts.expressionTree("a?b?c:d:e?f:g");
//		System.out.println(res.right.right.symbol);
		
		// === Testing shiftPosition =============
//		int res = bs.shiftPosition(new int[] {1,2});
//		System.out.println(res);
		
		// === Testing totalOccurrence ===========
//		int[] array = new int[] {0};
//		int res = bs.totalOccurrence(array, 2);
//		System.out.println(res);
		
		// === Testing kthSum ====================
//		BFS bfs = new BFS();
//		int[] A = new int[] {1,2,3,4,5,6};
//		int[] B = new int[] {1,2,3,4,5,6};
//		for(int i = 1;i<12;i++) {
//			System.out.println(bfs.kthSum(A, B, i));
//		}
		
		// === Testing subsetsOfSizeK ============
//		DFS dfs = new DFS();
//		List<String> res = dfs.subsetsOfSizeK("abcde",3);
//		List<String> res1 = dfs.subsetsOfSizeK1("abcde",3);
//		for(int i=0;i<res.size();i++) {
//			System.out.print(res.get(i)+"  "+res1.get(i)+"\n");
//		}
		
		// === Testing subsetsOfSizeK2 ============
//		DFS dfs = new DFS();
//		List<String> res = dfs.subsetsOfSizeK2("abbbbceee",3);
//		List<String> res1 = dfs.subsetsOfSizeK3("abbbbceee",3);
//		for(int i=0;i<res.size();i++) {
//			System.out.print(res.get(i)+"  "+res1.get(i)+"\n");
//		}
		
		// === Testing validParenthesesIII ========
//		DFS dfs = new DFS();
//		List<String> res = dfs.validParenthesesIII(2,0,1);
//		for(int i=0;i<res.size();i++) {
//			System.out.print(res.get(i)+"\n");
//		}
		
		// === Testing allPermutationsOfSubsets ===
//		DFS dfs = new DFS();
//		List<String> res = dfs.allPermutationsOfSubsets("abcdf");
//		for(int i=0;i<res.size();i++) {
//			System.out.print(res.get(i)+"\n");
//		}
		
		// === Testing canPartitionKSubsets ===
//		DFS dfs = new DFS();
////		int[] nums = new int[] {129,17,74,57,1421,99,92,285,1276,218,1588,215,369,117,153,22};
//		int[] nums = new int[] {292,452,106,668,2879,3168,265,288,26,18,437,3051,4109,306,323,1624};
//		
//		long startTime1 = System.nanoTime();
//		boolean res1 = dfs.canPartitionKSubsets1(nums,4);
//		long endTime1 = System.nanoTime();
//		long timeElapsed1 = endTime1 - startTime1;
//		System.out.println(res1);
//		System.out.println(timeElapsed1/1000);
//
//		long startTime = System.nanoTime();
//		Arrays.sort(nums);
//		boolean res = dfs.canPartitionKSubsets(nums,4);
//		long endTime = System.nanoTime();
//		long timeElapsed = endTime - startTime;
//		System.out.println(res);
//		System.out.println(timeElapsed/1000);
		
		// === Testing solveSudoku ===
//		DFS dfs = new DFS();
//		char[][] board = new char[][] {
//			{'5','3','.','.','7','.','.','.','.'},
//			{'6','.','.','1','9','5','.','.','.'},
//			{'.','9','8','.','.','.','.','6','.'},
//			{'8','.','.','.','6','.','.','.','3'},
//			{'4','.','.','8','.','3','.','.','1'},
//			{'7','.','.','.','2','.','.','.','6'},
//			{'.','6','.','.','.','.','2','8','.'},
//			{'.','.','.','4','1','9','.','.','5'},
//			{'.','.','.','.','8','.','.','7','9'}};
//		dfs.solveSudoku(board);
//		for(int i = 0;i<9;i++) {
//			for(int j =0;j<9;j++) {
//				System.out.print(board[i][j]);
//				if(j==8) {
//					System.out.println();
//				}
//			}
//		}
		
		// === Testing maxProfit ===
//		DP dp = new DP();
////		int[] array = new int[] {3,4,1,2,6,2,3,5,1,7,3,8};
//		int[] array = new int[] {1,2,3,4,5,6,7,8};
//		int res = dp.maxProfit(array);
//		System.out.println(res);
		
		// === Testing maxProfit ===
//		DP dp = new DP();
//		int[] array = new int[] {5,1,2,3,7,2,5,1,3};
//		int res = dp.maxProfit(array,1);
//		System.out.println(res);
		
		// === Testing minCost ===
//		DP dp = new DP();
//		int[] array = new int[] {4,3,3,4};
//		int res = dp.minCost(array);
//		System.out.println(res);
		
		// === Testing largestProduct ===
//		DP dp = new DP();
//		double[] array = new double[] {0.4,0.2,0.1};
//		double res = dp.largestProduct(array);
//		System.out.println(res);
		
		// === Testing findMedianSortedArrays  ===
//		BinarySearch bs = new BinarySearch();
//		int[] nums1 = new int[] {1};
//		int[] nums2 = new int[] {2,3,4,5,6};
//		double res = bs.findMedianSortedArrays(nums1, nums2);
//		System.out.println(res);
		
		// === Testing sqrt  ===
//		BinarySearch bs = new BinarySearch();
//		int res = bs.sqrt(2147395600);
//		System.out.println(res);
		
		// === Testing recover  ===
//		Integer[] input = new Integer[] {4,2,6,1,5,3,7};
//		TreeNode root = tg.generate(input);
//		ts.morrisTraverse(root);
//		ts.recover1(root);
//		ts.morrisTraverse(root);
		
		// === Testing smallerPairs  ===
		BinarySearch bs = new BinarySearch();
		int[] array = new int[] {3,4,0,-1,2,0,5};
		int res = bs.smallerPairs(array, 10);
		System.out.println(res);
	}
}
