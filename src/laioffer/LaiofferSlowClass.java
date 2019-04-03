package laioffer;

import java.util.*;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class LaiofferSlowClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 9;
        System.out.println(sum(n));
        myQueue q = new myQueue();
//        for(int i=0;i<100;i++) {
//        	q.offer(i);
//        }
//        for(int i=0;i<100;i++) {
//        	System.out.println(q.poll());
//        }
        int[] in = new int[]{6,5,4,3,2,1};
        Sort.mergeSort(in);
        for(int i : in) {
        	System.out.println(i);
        }
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n1.left = n2;
        n1.right = n3;
        TreeSolution ts = new TreeSolution();
        System.out.println(ts.getHeight(n1));    
        System.out.println(ts.countNodes(n1));  
        //Complexity of normal queue
        // offer
        // poll
        // peek
        // size
        // isEmpty
        //Example for Heap
        // offer O(logn)
        // poll O(logn)
        // peek  O(1)
        // size  O(1)
        // isEmpty  O(1)
//        PriorityQueue<Integer> pq = new PriorityQueue<>();
//        pq.offer(7);
//        pq.offer(3);
//        pq.offer(9);
//        System.out.println(pq.poll());
        
//        PriorityQueue<Node> pq = new PriorityQueue<>(new NodeComparator());
//        pq.offer(new Node(7));
//        pq.offer(new Node(3));
//        pq.offer(new Node(9));
//        System.out.println(pq.poll().value);
        
        // Construct a 最大堆	
        PriorityQueue<String> pq = new PriorityQueue<>(Collections.reverseOrder());
        pq.offer("sss");
        pq.offer("tttt");
        pq.offer("aaa");
        System.out.println(pq.poll());
        
        int[] example = new int[] {1,2,3,4,50,60,70,80,90,100};
        laicode lc = new laicode();
        int result = lc.kClosest(example,60,6);
        System.out.println(result);
        
	}
	private static int sum(int n){
        if(n==1)
            return 1;
        else
            return sum(n-1)+ n;
    }
}
class Node {
	public int value;
	public Node(int v) {
		value = v;
	}
}

class NodeComparator implements Comparator<Node> {
	@Override
	public int compare(Node o1, Node o2) {
		if(o1.value < o2.value) {
			return -1;
		}else if(o1.value > o2.value) {
			return 1;
		}else {
			return 0;
		}
	}
	
}

class myQueue{
	Deque<Integer> in = new LinkedList<>();
	Deque<Integer> out = new LinkedList<>();
	
	public void offer(int n) {
		in.push(n);
	}
	
	public Integer poll() {
		shuffleIfNecessary();
		if(out.isEmpty()) {
			return null;
		}
		return out.pop();
	}
	
	public Integer peek() {
		shuffleIfNecessary();
		if(out.isEmpty()) {
			return null;
		}
		return out.peek();
	}
	
	public int size() {
		return in.size()+out.size();
	}
	
	public boolean isEmpty() {
		return in.isEmpty() && out.isEmpty();
	}
	
	private void shuffleIfNecessary() {
		if(out.isEmpty()){
			while(!in.isEmpty()) {
				out.push(in.pop());
			}
		}
	}
}

class minStack{
	Deque<Integer> stack = new LinkedList<>();
	Deque<Integer> min = new LinkedList<>();
	public void push(int value) {
		stack.push(value);
		if(min.isEmpty() || value <= min.peek()) {
			min.push(value);
		}
	}
	public Integer pop() {
		if(stack.isEmpty()) {
			return null;
		}
		int value = stack.pop();
		if(value == min.peek()) {
			min.pop();
		}
		return value;
	}
	public Integer peek() {
		if(stack.isEmpty()) {
			return null;
		}
		return stack.peek();
	}
	public int size() {
		return stack.size();
	}
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	public Integer min() {
		if(min.isEmpty()) {
			return null;
		}
		return min.peek();
	}
}

class ListNode{
	public int value;
	public ListNode next;
	public ListNode(int v) {
		value = v;
	}
}

class LinkedListSolution{
	public ListNode reverse(ListNode head) {
		if(head == null || head.next == null) {
			return head;
		}
		ListNode prev = null, curr = head;
		while(curr != null) {
			ListNode next = curr.next;
			
			curr.next = prev;
			
			prev = curr;
			curr = next;
		}
		return prev;
	}
	
	public ListNode reverse1(ListNode head) {
		if(head == null || head.next == null)
			return head;
		ListNode subhead = reverse1(head.next);
		head.next.next = head;
		head.next = null;
		return subhead;
	}
	//Time O(n)
	//Space O(1)
	public ListNode findMiddle(ListNode head) {
		if(head ==null || head.next == null) {
			return head;
		}
		ListNode fast=head,slow=head;
		while(fast.next !=null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}
	//Time: O(n)
	//Space: O(1)
	public boolean hasCircle(ListNode head) {
		if(head == null || head.next == null)
			return false;
		ListNode slow = head, fast = head;
		while(fast!=null && fast.next!=null) {
			slow = slow.next;
			fast = fast.next.next;
			if(slow == fast) {
				return true;
			}
		}
		return false;
	}
	
	public ListNode insert(ListNode head, int target) {
		ListNode newNode = new ListNode(target);
		if(head == null) {
			return newNode;
		}
		ListNode prev = null, curr = head;
		while(curr!=null && curr.value<target) {
			prev = curr;
			curr = curr.next;
		}
		if(prev == null) {
			newNode.next = head;
			return newNode;
		}else{
			prev.next = newNode;
			newNode.next = curr;
			return head;
		}
	}
	
	public ListNode merge(ListNode h1, ListNode h2) {
		ListNode dummy = new ListNode(0);
		ListNode c = dummy,c1 = h1,c2 = h2;
		while(c1!=null && c2!=null) {
			if(c1.value<c2.value) {
				c.next = c1;
				c1 = c1.next;
			}else {
				c.next = c2;
				c2 = c2.next;
			}
			c = c.next;
		}
		if(c1!=null) {
			c.next = c1;
		}else if(c2!=null) {
			c.next = c2;
		}
		return dummy.next;
	}
	//Time O(n)
	//Space O(1)
	public ListNode partition(ListNode head, int target) {
		if(head == null || head.next == null) {
			return head;
		}
		ListNode dummy1 = new ListNode(0);
		ListNode dummy2 = new ListNode(0);
		ListNode s = dummy1, l = dummy2, curr = head;
		while(curr!=null) {
			if(curr.value<target) {
				s.next = curr;
				s = s.next;
			}else {
				l.next = curr;
				l = l.next;
			}
			curr = curr.next;
		}
		s.next = dummy2.next;
		l.next = null;
		return dummy1.next;
	}
	
}

class Solution14{
	public void smallestK(int[] array, int K) {
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		// Heapify  O(K)
		// this code snippet is O(KlogK)
		for(int i=0;i<K;i++) {
			pq.offer(array[i]);
		}
		//Time : O((N-K)logK)
		// When K<<n, becomes O(nlogK)
		// When K ~= n , becomes O(n) heapify dominates
		// when k ~= n/2 becomes O(nlogn)
		for(int i=K; i<array.length;i++) {
			if(array[i] < pq.peek()) {
				pq.poll();
				pq.offer(array[i]);
			}
		}
	}
	// O(n+klogn)
	public void smallestK2(int[] array, int K) {
		List<Integer> list = new ArrayList<>();
		for(int x : array) {
			list.add(x);
		}
		PriorityQueue<Integer> pq = new PriorityQueue<>(list);
		for(int i=0;i<K;i++) {
			pq.poll();
		}
	}
}

class Sort{
	public static void selectionSort(int[] array) {
		if(array == null || array.length <=1) {
			return;
		}
		int n = array.length;
		for(int i=0;i<n-1;i++) {
			int min_idx = i;
			for(int j = i+1;j<n;j++) {
				if(array[j]<array[min_idx]) {
					min_idx = j;
				}
			}
			swap(array,min_idx,i);
		}
	}
	private static void swap(int[] array, int i, int j) {
		int temp = array[j];
		array[j] = array[i];
		array[i]= temp;
	}
	public static void mergeSort(int[] array) {
		if(array == null || array.length<=1)
			return;
		int[] helper = new int[array.length];
		mergeSort(array,0,array.length-1,helper);
	}
	private static void mergeSort(int[] array, int left, int right,int[] helper) {
		if(left >= right) { //left == right is also okay
			return; 
		}
		int mid = left + (right-left)/2; // avoid overflow
		mergeSort(array,left,mid,helper);
		mergeSort(array,mid+1,right,helper);
		merge(array,helper,left,mid,right);
	}
	private static void merge(int[] array, int[] helper, int left, int mid, int right) {
		for(int i=left;i<=right;i++) {
			helper[i] = array[i];
		}
		int i = left, j = mid+1, k = left;
		while(i<=mid && j<=right) {
			if(helper[i] < helper[j]) {
				array[k] = helper[i];
				i++;
			}else {
				array[k] = helper[j];
				j++;
			}
			k++;
		}
		while(i<=mid) {
			array[k++] = helper[i++];
		}
	}
}
class laicode {
	public int kClosest(int[] array, int target, int k) {
	    // Write your solution here
	    int left = 0, right = array.length-1;
	    int closest_idx = 0;
	    while(left<right-1){
	      int mid = left +(right-left)/2;
	      if(array[mid] == target){
	        closest_idx = mid;
	        break;
	      }else if(target < array[mid]){
	        right = mid;
	      }else{
	        left = mid;
	      }
	    }
	    if(left>=right-1) {
	    	if(Math.abs(array[left]-target) <= Math.abs(array[right]-target)){
	    		closest_idx = left;
	    	}else{
	    		closest_idx = right;
	    	}
	    }
	    int l = closest_idx, r = l+1;
	    int dist = k;
	    while(dist>1){
	      if(r+dist/2-1>array.length-1){
	        l = l - dist/2;
	      }else if(l-dist/2+1<0){
	        r = r + dist/2;
	      }else if(Math.abs(array[r+dist/2-1]-target)<Math.abs(array[l-dist/2+1]-target)){
	        r = r + dist/2;
	      }else{
	        l = l - dist/2;
	      }
	      dist = dist - dist/2;
	    }
	    int kth = 0;
	    if(l<0)
	      kth = r;
	    else if(r>array.length-1)
	      kth = l;
	    else if(Math.abs(array[r]-target)<Math.abs(array[l]-target))
	    	kth = r;
	    else 
	    	kth = l;
	    return array[kth];
	  }
	
	  public long fibonacci(int K) {
	    // Write your solution here
	    long[] table = new long[K+1];
	    for(long item : table){
	      item = -1;
	    }
	    return fib(K,table);
	  }
	  private long fib(int K,long[] table){
	    if(K<=0){
	      table[K]=0;
	      return 0;
	    }
	    if(K==1){
	      table[K] =1;
	      return 1;
	    }  
	    if(table[K]!=-1)
	      return table[K];
	    else{
	      table[K] = fib(K-1,table)+fib(K-2,table);
	      return table[K];
	    }
	  }  
}

class binarySearchSolution{
	public int binarySearch(int[] array,int target) {
		if(array == null || array.length==0)
			return -1;
		int left = 0, right = array.length-1;
		while(left<=right) {
			int mid = left + (right-left)/2;
			if(target==array[mid]) {
				return mid;
			}else if(target<array[mid]){
				right = mid-1;
			}else {
				left = mid+1;
			}
		}
		return -1;
	}
	
	public int fisrtOccurance(int[] array, int target) {
		if(array==null || array.length==0) {
			return -1;
		}
		int left = 0,right = array.length-1;
		while(left<right-1) {
			int mid = left + (right-left)/2;
			if(target == array[mid]) {
				right = mid;
			}else if(target<array[mid]) {
				right = mid;
			}else {
				left = mid;
			}
		}
		if(target == array[left])
			return left;
		if(target == array[right])
			return right;
		return -1;
	}
	
	public int lastOccurence(int[] array, int target) {
		if(array==null || array.length ==0) {
			return -1;
		}
		int left = 0, right= array.length-1;
		while(left<right-1) {
			int mid = left + (right-left)/2;
			if(target >= array[mid]) {
				left = mid;
			}else {
				right = mid;
			}
		}
		if(target == array[right]) {
			return right;
		}
		if(target == array[left]) {
			return left;
		}
		return -1;
	}
	
	public int closest(int[] array, int target) {
		if(array==null || array.length ==0) {
			return -1;
		}
		int left = 0, right = array.length-1;
		while(left<right-1) {
			int mid = left + (right-left)/2;
			if(target == array[mid]) {
				return mid;
			}else if(target<array[mid]) {
				right = mid;
			}else {
				left = mid;
			}
		}
		if(Math.abs(array[left]-target) < Math.abs(array[right]-target)) {
			return left;
		}else {
			return right;
		}
	}
	
	public int[] findInMatrix(int[][] matrix, int target) {
		int[] res = {-1,-1};
		if(matrix == null || matrix.length == 0 ||matrix[0].length ==0) {
			return res;
		}
		int m = matrix.length, n = matrix[0].length;
		int left = 0, right = m*n-1;
		while(left<=right) {
			int mid = left +(right-left)/2;
			if(target==matrix[mid/n][mid%n]) {
				return new int[] {mid/n,mid%n};
			}else if(target<matrix[mid/n][mid%n]) {
				right = mid-1;
			}else {
				left = mid+1;
			}
		}
		return res;
	}
}

class TreeNode{
	int val;
	TreeNode left;
	TreeNode right;
	
	public TreeNode(int val) {
		this.val = val;
		left = null;
		right = null;
	}
}

class TreeSolution{
	// Time: O(n) number of nodes
	// Space: O(height) on average ~= O(logn) for balanced tree
	public int getHeight(TreeNode root) {
		if(root == null)
			return 0;
		return 1+Math.max(getHeight(root.left), getHeight(root.right));
	}
	public int countNodes(TreeNode root) {
		if(root == null)
			return 0;
		return 1+countNodes(root.left)+countNodes(root.right);
	}
	//Time : O(n)
	//Space: heap: none
	//		 stack: O(height) worst case o(n) on average o(logn)
	public boolean isSym(TreeNode root) {
		return isSym(root,root);
		// return root == null || isSym(root.left, root.right);
	}
	private boolean isSym(TreeNode A, TreeNode B) {
		if( A == null && B == null)
			return true;
		if( A == null || B == null)
			return false;
		return A.val == B.val && isSym(A.left, B.right) && isSym(A.right,B.left);
	}
	//Time: O(n^2)
	//Space: Stack : O(height)
	//       heap : none
	public boolean areTwisted(TreeNode A, TreeNode B) {
		if(A == null & B == null)
			return true;
		if(A == null || B == null)
			return false;
		return A.val == B.val && (
			(areTwisted(A.left,B.left) && areTwisted(A.right,B.right)) || 
			(areTwisted(A.left,B.right) && areTwisted(A.right,B.left))); 
	}
	
	//Time : O(n)
	//Space : stack : O(height) heap :none
	public boolean isBST(TreeNode root) {
		return isBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
	}
	private boolean isBST(TreeNode root, int min, int max) {
		if(root == null)
			return true;
		if(root.val<= min || root.val >= max) {
			return false;
		}
		return isBST(root.left,min,root.val) && isBST(root.right,root.val,max);
	}
	
	
	//Time: O(n)
	//Space: stack O(height) heap none
	public boolean isBST2(TreeNode root) {
		lastSeen = Integer.MAX_VALUE;
		return inOrder(root);
	}
	private int lastSeen = Integer.MIN_VALUE;
	private boolean inOrder(TreeNode root) {
		if(root == null)
			return true;
		if(!inOrder(root.left))
			return false;
		if(root.val <= lastSeen)
			return false;
		lastSeen = root.val;
		return inOrder(root.right);
	}
	
	//Time:  worst case O(n), in general better than O(n), due to *branch pruning*
	//Space: O(height)
	public void printRange(TreeNode root, int min, int max) {
		if(root == null) {
			return;
		}
		if(root.val >= min) { //prune unnecessary left branches
			printRange(root.left,min,max);
		}
		if(min <= root.val && root.val <= max) {
			System.out.println(root.val);
		}
		if(root.val <= max) { //prune unnecessary right branches
			printRange(root.right,min,max);
		}
	}
	
	
}



