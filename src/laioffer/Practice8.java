package laioffer;

import java.util.*;

public class Practice8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] array = new Integer[] {5,2,6,1,4};
		TreeNode root = TreeGenerator.generate(array);
//		BSTSolution1 ts = new BSTSolution1();
//		ts.inOrder(root);
//		System.out.println();
//		root = ts.insertBST2(root, 3);
//		ts.inOrder(root);
//		System.out.println();
//		System.out.println(ts.searchBST(root, 6).val);
//		System.out.println(ts.searchBST1(root, 2).val);
//		root = ts.deleteBST1(root, 5);
//		ts.inOrder(root);
		IterativeTreeSolution its = new IterativeTreeSolution();
		its.preOrder(root);
		its.inOrder(root);
	}

}
class BSTSolution1 extends BSTSolution{
	public TreeNode deleteBST(TreeNode root, int target) { //Recursive
		//Search the node
		//Once find the node:
		//Case1 : left == null && right == null   return root.left or root.right
		//Case2 : left == null                    return root.right
		//Case3 : right == null					  return root.left
		//Case4 : left != null && right != null
		if(root == null) {
			return null;
		}
		if(target < root.val) {
			root.left = deleteBST(root.left, target);
			return root;
		}else if(target > root.val) {
			root.right = deleteBST(root.right, target);
			return root;
		}else {
			if(root.left == null) {
				return root.right;
			}
			if(root.right == null) {
				return root.left;
			}
			//case 4.1 root.right.left == null -> root.right is the smallest
			
			if(root.right.left == null) {
				root.right.left = root.left;
				return root.right;
			}
			//case 4.2 root.right.left != null
			TreeNode substitute = deleteMin(root.right);
			substitute.left = root.left;
			substitute.right = root.right;
			return substitute;
		}
	}
	// 
	private TreeNode deleteMin(TreeNode root) {
		//case 1. root.right.left == null -> root.right is the smallest
		//case 2. root.right.left != null
		TreeNode prev = null;
		TreeNode curr = root;
		while(curr.left != null) {
			prev = curr;
			curr = curr.left;	
		}
		prev.left = curr.right;
		return curr;
	}
	public TreeNode deleteBST1(TreeNode root, int target) { //iterative
		if(root == null) {
			return root;
		}
		TreeNode dummy = new TreeNode(0);
		TreeNode prev = dummy;
		prev.left = root;
		TreeNode curr = root;
		while(curr!=null) {
			if(target < curr.val) {
				prev = curr;
				curr = curr.left;
			}else if(target > curr.val) {
				prev = curr;
				curr = curr.right;
			}else {
				if(curr == prev.left) {
					if(curr.left == null) {
						prev.left = curr.right;
						break;
					}else if(curr.right == null) {
						prev.left = curr.left;
						break;
					}else if(curr.right.left == null) {
						prev.left = curr.right;
						curr.right.left = curr.left; 
						//use curr.right to replace original curr and connect with left child of original curr
						break;
					}else {
						TreeNode sub = deleteMin(curr.right);
						sub.left = curr.left;
						sub.right =curr.right;
						prev.left = sub;
						break;
					}
				}else {
					if(curr.left == null) {
						prev.right = curr.right;
						break;
					}else if(curr.right == null) {
						prev.right = curr.left;
						break;
					}else if(curr.right.left == null) {
						prev.right = curr.right;
						curr.right.left = curr.left;
						break;
					}else {
						TreeNode sub = deleteMin(curr.right);
						sub.left = curr.left;
						sub.right =curr.right;
						prev.right = sub;
						break;
					}
				}
			}
		}
		return dummy.left;
	}
}

class IterativeTreeSolution {
	public void preOrder(TreeNode root) {
		if(root == null) {
			return;
		}
		Deque<TreeNode> stack = new ArrayDeque<>();
		stack.offerFirst(root);
		while(!stack.isEmpty()) {
			TreeNode top = stack.pollFirst();
			System.out.println(top.val);
			if(top.right != null) {
				stack.offerFirst(top.right);
			}
			if(top.left != null) {
				stack.offerFirst(top.left);
			}
		}
		return;
	}
	public void inOrder(TreeNode root) {
		if(root == null) {
			return;
		}
		Deque<TreeNode> stack = new ArrayDeque<>();
		TreeNode curr = root;
		while(curr!=null || !stack.isEmpty()) {
			if(curr!=null) {
				stack.offerFirst(curr);
				curr = curr.left;
			}else {
				curr = stack.pollFirst();
				System.out.println(curr.val);
				curr = curr.right;
			}
		}
	}
	
	
}