package laioffer;

public class Recursion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(2);
		ListNode c = new ListNode(3);
		ListNode d = new ListNode(4);
		ListNode e = new ListNode(5);
		a.next = b;
		b.next = c;
		c.next = d;
		d.next = e;
		RecursionSolution rs = new RecursionSolution();
		ListNode result = rs.reverseLinkedList(a);
		while(result != null) {
			System.out.print(result.value);
			result = result.next;
		}
	}
}

class RecursionSolution{
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
