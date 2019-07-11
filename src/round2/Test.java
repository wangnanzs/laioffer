package round2;
import java.util.*;
public class Test {
	public static void main(String[] args) {
		TreeGenerator tg = new TreeGenerator();
		TreeSolution ts = new TreeSolution();
//		Integer[] input = new Integer[] {1,2,3,4,5,6,7,8,9};
		Integer[] input = new Integer[] {5,3,8,1,4,7,9,null,2,null,null,6,null,null,null}; // BST
		TreeNode root = tg.generate(input);
		List<Integer> list = ts.inOrderIterative(root);
		for(Integer i : list) {
			System.out.print(i+" ");
		}
		System.out.println();
		int[] array = new int[] {1,2,3,4,5,6,7,8,9,10};
		BinarySearch bs = new BinarySearch();
		System.out.println(bs.smallestLargerThan(array, 11));
		System.out.println(ts.smallestLargerThan(root, 8));
//		System.out.println(ts.getDepth(root, 10));
	}
}
