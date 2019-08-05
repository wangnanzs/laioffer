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
	
	// Return the TreeNode whose key is smalleset and larger than the target
	public TreeNode smallestLargerThan(TreeNode root, int target) {
		TreeNode res = null;
		TreeNode curr = root;
		while(curr != null) {
			if(curr.key>target) {
				res = curr;
				curr = curr.left;
			}else {
				curr = curr.right;
			}
		}
		return res;
	}
	
	//Reconstruct a binary tree from inorder and postorder traversal sequence 
	// post 1 4 3 11 8 5
	// in   1 3 4 5 8 11 
	public TreeNode reconstruct(int[] in, int[] post) {
		if(in == null || post == null) {
			return null;
		}
		Map<Integer,Integer> hm = new HashMap<>();
		for(int i=0;i<in.length;i++) {
			hm.put(in[i], i);
		}
		return reconstructHelper(in,0,in.length-1,post,0,post.length-1,hm);
	}
	private TreeNode reconstructHelper(int[] in, int inLeft, int inRight, int[] post, int postLeft, int postRight, Map<Integer,Integer> inMap) {
		// Base Case 
		if(inLeft > inRight) {
			return null;
		}
		TreeNode root = new TreeNode(post[postRight]);
		int inMid = inMap.get(post[postRight]);
		int leftSize = inMid-inLeft;
		root.left = reconstructHelper(in,inLeft,inLeft+leftSize-1,post,postLeft,postLeft+leftSize-1,inMap);
		root.right = reconstructHelper(in,inLeft+leftSize+1,inRight,post,postLeft+leftSize,postRight-1,inMap);
		return root;
	}
	
	// Given inorder and preorder traversal sequence and return postorder sequence
	// in  : 4,2,5,1,3,6
	// pre : 1,2,4,5,3,6
	// post: 4,5,2,6,3,1
	public int[] constructPostOrder(int[] in, int[] pre) {
		if(in == null || pre == null) {
			return null;
		}
		Map<Integer,Integer> hm = new HashMap<>();
		for(int i=0;i<in.length;i++) {
			hm.put(in[i], i);
		}
		int[] post = new int[in.length];
		postOrderHelper(in,pre,post,hm,0,in.length-1,0,pre.length-1,post.length-1);
		return post;
	}
	private void postOrderHelper(int[] in, int[] pre, int[] post, Map<Integer,Integer> inMap, 
			int inLeft,int inRight, int preLeft,int preRight, int postRight) {
		if(inLeft > inRight) {
			return;
		}
		int inMid = inMap.get(pre[preLeft]);
		int leftSize = inMid - inLeft;
		int rightSize = inRight - inMid;
		post[postRight] = pre[preLeft];
		postOrderHelper(in,pre,post,inMap,inLeft,inLeft+leftSize-1,preLeft+1,preLeft+leftSize,postRight-1-rightSize);
		postOrderHelper(in,pre,post,inMap,inMid+1,inRight,preLeft+leftSize+1,preRight,postRight-1);
	}
	
	//Verify pre-order serialization is valid
	public boolean isValidSerialization(String preOrder) {
		// Use a stack to mimic recursive
		// Case1: valid number - push 
		// Case2: a '#' - do nothing
		// Case3: two consecutive '#' - pop
		// Stack top is the parent of curr
		Deque<Integer> stack = new ArrayDeque<>();
		if(preOrder.charAt(0)=='#') {
			return false;
		}else {
			stack.offerFirst(preOrder.charAt(0)-'0');
		}
		char prev = preOrder.charAt(0);
		for(int i=1;i<preOrder.length();i++) {
			char curr = preOrder.charAt(i);
			if(curr != '#') {
				stack.offerFirst(curr-'0');
				prev = curr;
			}else if(prev!='#'){
				prev = curr;
			}else {
				if(stack.isEmpty()) {
					return false;
				}else {
					stack.pollFirst();
					prev = '@';
				}
			}
		}
		return true;
	}
	
	//Ternary Expression Tree
	public ExpNode expressionTree(String exp) {
		if(exp == null || exp.isEmpty()) {
			return null;
		}
		return expressionTreeHelper(exp.toCharArray(),0,exp.length()-1);
	}
	private ExpNode expressionTreeHelper(char[] exp, int left, int right) {
		if(left == right) {
			ExpNode root = new ExpNode(exp[left]);
			return root;
		}
		ExpNode root = new ExpNode(exp[left]);
		int count = 0;
		for(int i=left+1;i<right;i++) {
			if(exp[i]=='?') {
				count++;
			}else if(exp[i] == ':') {
				count--;
			}
			if(count == 0) {
				root.left = expressionTreeHelper(exp,left+2,i-1);
				root.right = expressionTreeHelper(exp,i+1,right);
				break;
			}
		}
		return root;
	}
	
}
