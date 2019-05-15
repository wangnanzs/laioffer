package laioffer;
import java.util.*;
public class Recursion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ListNode a = new ListNode(1);
//		ListNode b = new ListNode(2);
//		ListNode c = new ListNode(3);
//		ListNode d = new ListNode(4);
//		ListNode e = new ListNode(5);
//		a.next = b;
//		b.next = c;
//		c.next = d;
//		d.next = e;
		//"3","-8","9","4","10","2","-5","1","-2"
		Integer[] array = new Integer[] {5,2,10,1,3,8,13,null,null,null,4,null,null,11,15};
		TreeNode root = TreeGenerator.generate(array);
		
		RecursionSolution rs = new RecursionSolution();
		System.out.println(rs.maxPathSum(root));
		PLKSolution plk = new PLKSolution();
		List<Integer> res = plk.zigZag(root);
		for(Integer i : res) {
			System.out.print(i+" ");
		}
//		System.out.println(Integer.MIN_VALUE);
		
		
		
//		ListNode result = rs.reverseLinkedList(a);
//		while(result != null) {
//			System.out.print(result.value);
//			result = result.next;
//		}
	}
}

class RecursionSolution{
	public int maxPathSum(TreeNode root) {
	    // Write your solution here
	    int[] max = new int[1];
	    max[0] = Integer.MIN_VALUE;
	    maxPathSum(root,max);
	    return max[0];
	  }
	  // return max path sum from this root to one of its leaves
	  // update global max when both left and right exist and > current global max
	  private int maxPathSum(TreeNode root, int[] max){
	    //Base case
	    if(root == null){
	      return 0;
	    }
	    int left = maxPathSum(root.left,max);
	    int right = maxPathSum(root.right,max);
	    if(root.left != null && root.right != null){
	      max[0] = Math.max(max[0],left+right+root.val);
	      return root.val + (left>right? left: right);
	    }
	    return root.left == null? root.val+right : root.val+left;
	  }
	
	public boolean exist(TreeNode root, int target) {
	    // Write your solution here
	    if(root == null){
	      return false;
	    }
	    List<TreeNode> ls = new ArrayList<>();
	    return existHelper(root,target,ls);
	  }
	  private boolean existHelper(TreeNode root, int target,
	                              List<TreeNode> list){
	    if(root == null){
	       return false;
	    }
	    //What do we do in this level
	    int sum = 0;
	    list.add(root);
	    for(int i= list.size()-1;i>=0;i--) {
	    	sum += list.get(i).val;
	    	if(sum == target) {
	    		return true;
	    	}
	    }
	    if(existHelper(root.left,target,list)) {
	    	return true;
	    }
	    if(existHelper(root.right,target,list)) {
	    	return true;
	    }
	    list.remove(list.size()-1);
	    return false;
	  }
	public TreeNode LCA(TreeNode root, TreeNode a, TreeNode b) {
		if(root == null || root == a || root == b) {
			return root;
		}
		TreeNode left = LCA(root.left, a, b);
		TreeNode right = LCA(root.right, a, b);
		if(left != null && right != null) {
			return root;
		}
		return left == null? right:left;
	}
	public ListNode reverseLinkedList(ListNode head) {
		// Base Case
		if(head == null || head.next == null) {
			return head;
		}
		ListNode newHead = reverseLinkedList(head.next.next);
		ListNode next = head.next;
		head.next = newHead;
		next.next = head;
		return next;
	}
	
	public double power(int a,int b) throws Exception  {
		if(a == 0 && b <= 0) {
			throw new Exception();
		}
		if(a == 0) {
			return 0;
		}
		if(b>=0) {
			return powerHelper(a,b);
		}else {
			return 1/(double)powerHelper(a,-b);
		}
	}
	private int powerHelper(int a, int b){
		// Base case
		if(b == 0) {
			return 1;
		}
		int temp = powerHelper(a,b/2);
		if(b%2==0) {
			return temp*temp;
		}else {
			return temp*temp*a;
		}
	}
}
