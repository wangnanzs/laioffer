package laioffer;
import java.util.*;
public class midteam1 {
	public int kthSum(int[] A, int[] B, int k) {
		boolean[][] visited = new boolean[A.length][B.length];
		Queue<Element> pq = new PriorityQueue<>(k,new MyComparator());
		pq.add(new Element(0,0,A[0]+B[0]));
		visited[0][0] = true;
		while(k>1) {
			Element curr = pq.poll();
			k--;
			if(!visited[curr.x][curr.y+1]) {
				pq.add(new Element(curr.x,curr.y+1,A[curr.x]+B[curr.y+1]));
				visited[curr.x][curr.y+1] = true;
			}
			if(!visited[curr.x+1][curr.y]) {
				pq.add(new Element(curr.x+1,curr.y,A[curr.x+1]+B[curr.y]));
				visited[curr.x+1][curr.y] = true; 
			}
		}
		return pq.peek().sum;
	}
	class Element{
		int x;
		int y;
		int sum;
		public Element(int x,int y,int sum) {
			this.x = x;
			this.y = y;
			this.sum = sum;
		}
	}
	class MyComparator implements Comparator<Element>{
		public int compare(Element o1, Element o2) {
			if(o1.sum < o2.sum) {
				return -1;
			}else if(o1.sum > o2.sum) {
				return 1;
			}else {
				return 0;
			}
		}
	}	
	// Iterative solution 
	// Approach: Traverse the linked list, in each step, reverse the direction of pointer.  
	// Time O(n)
	// Space O(1)
	public ListNode reverse1(ListNode head){
	  ListNode curr = head;
	  ListNode prev = null;
	  while(curr != null){
	    ListNode next = curr.next;
	    curr.next = prev;
	    prev = curr;
	    curr = next;
	  }
	  return prev;
	}


	// Recursive Solution
	// subproblem: reverse head.next
	// recursion rule: reverse the direction of point of current level
	// base case: when "head" linked list has only one node, head itself is the reversed linked list
	// Time O(n)
	// Space O(n) n level call stack 
	public ListNode reverse2(ListNode head){
	  if(head == null || head.next == null){
	    return head;
	  }
	  ListNode newhead = reverse2(head.next);
	  head.next.next = head;
	  head.next = null;
	  return newhead;
	}
}
