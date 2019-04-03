package laioffer;
import java.util.*;
public class Practice7 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] array = new Integer[] {5,2,6,1,4};
		TreeNode root = TreeGenerator.generate(array);
		BSTSolution ts = new BSTSolution();
		ts.inOrder(root);
		System.out.println();
		root = ts.insertBST2(root, 3);
		ts.inOrder(root);
		System.out.println();
		System.out.println(ts.searchBST(root, 6).val);
		System.out.println(ts.searchBST1(root, 2).val);
	}

}

//class TreeNode {
//	int key;
//	TreeNode left;
//	TreeNode right;
//	public TreeNode(int value) {
//		key = value;
//		left = null;
//		right = null;
//	}
//}

class TreeGenerator{
	public static TreeNode generate(Integer[] array) {
		if(array == null || array.length == 0) {
			return null;
		}
		TreeNode[] nodeArray = new TreeNode[array.length];
		for(int i=0; i<array.length;i++) {
			if(array[i] != null) {
				nodeArray[i] = new TreeNode(array[i]);
				if(i>0 && i%2==1) {
					nodeArray[(i-1)/2].left = nodeArray[i];
				}else {
					nodeArray[(i-1)/2].right = nodeArray[i];
				}
			}
		}
		return nodeArray[0];
	}
}

class BSTSolution{
	public void inOrder(TreeNode root) {
		if(root == null) {
			return;
		}
		inOrder(root.left);
		System.out.print(root.val);
		System.out.print(" ");
		inOrder(root.right);
	}
	public TreeNode searchBST(TreeNode root, int target) { // recursive
		if(root == null) {
			return root;
		}
		if(target < root.val) {
			return searchBST(root.left,target);
		}else if(target > root.val) {
			return searchBST(root.right,target);
		}
		return root;
	}
	public TreeNode searchBST1(TreeNode root, int target) { //iterative
		if(root == null) {
			return root;
		}
		while(root!=null) {
			if(target == root.val) {
				return root;
			}
			else if(target < root.val) {
				root = root.left;
			}else {
				root = root.right;
			}
		}
		return root;
	}
	
	public TreeNode insertBST(TreeNode root, int value){
		if(root == null){
			return new TreeNode(value);
		}
		TreeNode prev = null;
		TreeNode curr = root;
		while(curr != null){
			prev = curr;
			if(curr.val == value){
				return root;
			}else if(value < curr.val){
				curr = curr.left;
			}else{
				curr = curr.right;
			}
		}
		if(value < prev.val){
			prev.left = new TreeNode(value);
		}else if(value > prev.val){
			prev.right = new TreeNode(value);
		}
		return root;
	}
	public TreeNode insertBST1(TreeNode root, int value) {
		//Base case
		if(root == null) {
			return new TreeNode(value);
		}
		if(value < root.val) {
			root.left = insertBST1(root.left,value);
		}else if(value > root.val){
			root.right = insertBST1(root.right,value);
		}
		return root;
	}
	public TreeNode insertBST2(TreeNode root, int value) {
		//Base case
		if(root == null) {
			return new TreeNode(value);
		}
		helper(root,value);
		return root;
	}
	private void helper(TreeNode root, int value) {
		if(value < root.val) {
			if(root.left == null) {
				root.left = new TreeNode(value);
			}else {
				helper(root.left,value);
			}
		}else if(value > root.val) {
			if(root.right == null) {
				root.right = new TreeNode(value);
			}else {
				helper(root.right,value);
			}
		}
		return;
	}
}
