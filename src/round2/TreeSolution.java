package round2;
import java.util.*;
class TreeSolution {
	public int getDepth(TreeNode root, TreeNode target) {
		if(root == null) {
			return -1;
		}
		if(root == target) {
			return 0;
		}
		int left = getDepth(root.left,target);
		int right = getDepth(root.right,target);
		if(left == -1 && right == -1) {
			return -1;
		}
		return left == -1 ? right +1 : left +1;
	}
	public int getDepth(TreeNode root, int target) {
		if(root == null) {
			return -1;
		}
		if(root.key == target) {
			return 0;
		}
		int left = getDepth(root.left,target);
		int right = getDepth(root.right,target);
		if(left == -1 && right == -1) {
			return -1;
		}
		return left == -1 ? right +1 : left +1;
	}
	public List<Integer> inOrderIterative(TreeNode root){
		/*
		 * 1. Initialization: push left children all the way to the leftmost into stack
		 * 2. Stack top is the current node
		 */
		List<Integer> res = new ArrayList<>();
		Deque<TreeNode> stack = new ArrayDeque<>();
		pushLeft(root,stack);
		while(!stack.isEmpty()) {
			TreeNode curr = stack.pollFirst();
			res.add(curr.key);
			pushLeft(curr.right,stack);
		}
		return res;
	}
	private void pushLeft(TreeNode root, Deque<TreeNode> stack) {
		TreeNode curr = root;
		while(curr!= null) {
			stack.offerFirst(curr);
			curr = curr.left;
		}
	}
}
