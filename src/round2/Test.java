package round2;
import java.util.*;
public class Test {
	public static void main(String[] args) {
		TreeGenerator tg = new TreeGenerator();
		TreeSolution ts = new TreeSolution();
		Integer[] input = new Integer[] {1,2,3,4,5,6,7,8,9};
		TreeNode root = tg.generate(input);
		List<Integer> list = ts.inOrderIterative(root);
		for(Integer i : list) {
			System.out.print(i+" ");
		}
//		System.out.println(ts.getDepth(root, 10));
	}
}
