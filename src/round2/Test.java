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
		BinarySearch bs = new BinarySearch();
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
		DFS dfs = new DFS();
		List<String> res = dfs.allPermutationsOfSubsets("abcdf");
		for(int i=0;i<res.size();i++) {
			System.out.print(res.get(i)+"\n");
		}
	}
}
