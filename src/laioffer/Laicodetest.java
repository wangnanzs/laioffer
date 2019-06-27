package laioffer;
import java.util.*;
public class Laicodetest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution sol = new Solution();
		List<String> res = sol.Restore("25525511135");
		for(String s:res) {
			System.out.println(s);
		}
	}

}



//class ListNode {
//	public int value;
//    public ListNode next;
//    public ListNode(int value) {
//    this.value = value;
//    next = null;
//    }
//}

class Solution {
	public List<String> Restore(String ip) {
	    // Write your solution here
	    StringBuilder sb = new StringBuilder();
	    List<String> res = new ArrayList<>();
	    ipDFS(ip,0,0,sb,res);
	    return res;
	  }
	  // Index represent a dot is inserted after this index
	  private void ipDFS(String ip, int level, int index, StringBuilder sb, List<String> list){
	    // Base Case
	    if(level == 4){
	      if(sb.length()==ip.length()+4){
	        StringBuilder tmp = new StringBuilder(sb);
	        tmp.setLength(tmp.length()-1);
	        list.add(tmp.toString());
	      }
	      return;
	    }
	    for(int i=index;i<index+3;i++){
	      if(isValid(ip,index,i+1)){
	        sb.append(ip.substring(index,i+1));
	        sb.append(".");
	        ipDFS(ip,level+1,i+1,sb,list);
	        sb.setLength(sb.length()-i-2+index);
	      }
	    }
	  }
	  private boolean isValid(String ip, int left, int right){
	    if(right-left > 3 || right>ip.length() || left < 0 || left>=right || (ip.charAt(left)=='0' && right!= left+1) ){
	      return false;
	    }
	    int sum = 0;
	    for(int i=left;i<right;i++){
	      sum = 10*sum + ip.charAt(i) - '0';
	    }
	    return sum<256 && sum >=0;
	  }
	
	  public void mergeSort(int[] array) {
		  if(array==null || array.length == 0) {
			  return;
		  }
		  int[] helper = new int[array.length];
		  mergeSort(helper,array,0,array.length-1);
	  }
	  private void mergeSort(int[] helper,int[] array,int left,int right) {
		  if(left == right) {
			  return;
		  }
		  int mid = left + (right-left)/2;
		  mergeSort(helper,array,left,mid);
		  mergeSort(helper,array,mid+1,right);
		  for(int i=left;i<=right;i++) {
			  helper[i] = array[i];
		  }
		  int i = left;
		  int j = mid+1;
		  int k = left;
		  while(i<=mid && j<=right) {
			  if(helper[i]<helper[j]) {
				  array[k++] = helper[i++];
			  }else {
				  array[k++] = helper[j++];
			  }
		  }
		  while(i<=mid) {
			  array[k++] = helper[i++];
		  }
		  return;
	  }
	
	  public List<String> validParentheses(int n) {
	    // Write your solution here
	    List<String> res = new ArrayList<>();
	    String s = new String();
	    helper(s,n,n,res);
	    return res;
	  }
	  private void helper(String s, int left, int right, List<String> res){
	    if(left == 0 && right == 0){
	      res.add(s);
	      return;
	    }
	    if(left > 0) {
	      helper(s+"(",left-1,right,res);
	    }
	    if(right>left){
	      helper(s+")",left,right-1,res);
	    }
	  }
	  public List<String> subSets(String set) {
	    // Write your solution here.
	    // Base case 
	    if(set == null) 
	      return new ArrayList<>();
	    if(set.length() == 0){
	      List<String> ls = new ArrayList<>();
	      ls.add("");
	      return ls;
	    }
	    StringBuilder shorter = new StringBuilder(set);
	    char last = set.charAt(set.length()-1);
	    shorter.deleteCharAt(set.length()-1);
	    List<String> subls = subSets(shorter.toString());
	    int n = subls.size();
	    for(int i = 0;i < n;i++){
	      subls.add(subls.get(i) + Character.toString(last));
	    }
	    return subls;
	  }
	
	  public void sort(LinkedList<Integer> s1) {
	    LinkedList<Integer> s2 = new LinkedList<Integer>();
	    LinkedList<Integer> s3 = new LinkedList<Integer>();
	    // Write your solution here.
	    sort(s1,s2,s3,s1.size());
	    return;
	  }
	  private void sort(LinkedList<Integer> s1, LinkedList<Integer> s2, LinkedList<Integer> s3, int length) {
		  if(length <= 1) {
			  return;
		  }
		  int len2 = length/2;
		  int len1 = length - len2;
		  for(int i=0;i<len2;i++) {
			  s2.push(s1.pop());
		  }
		  sort(s1,s2,s3,len1);
		  sort(s2,s1,s3,len2);
		  int i=0,j=0;
		  while(i<len1 && j<len2) {
			  if(s1.peek()<=s2.peek()) {
				  s3.push(s1.pop());
				  i++;
			  }else {
				  s3.push(s2.pop());
				  j++;
			  }
		  }
		  while(i<len1) {
			  s3.push(s1.pop());
			  i++;
		  }
		  while(j<len2) {
			  s3.push(s2.pop());
			  j++;
		  }
		  for(int idx=0;idx<length;idx++) {
			  s1.push(s3.pop());
		  }
	  }
	  // Insert a target in a sorted cycle linked list
	  public ListNode insertSortedCycle(ListNode head, int target) {
		  //assume head is not null
		  if(target < head.value) {
			  ListNode curr = head;
			  while(curr.next.value >= curr.value) {
				  	curr = curr.next;
			  }
			  curr.next = new ListNode(target);
			  curr.next.next = head;
			  return curr.next;
		  }
		  ListNode curr = head;
		  while(curr.next.value >= curr.value) {
			  if(curr.value<target && curr.next.value >= target) {
				  ListNode temp = new ListNode(target);
				  temp.next = curr.next;
				  curr.next = temp;
				  return head;
			  }
			  curr = curr.next;
		  }
		  curr.next = new ListNode(target);
		  curr.next.next = head;
		  return head;
	  }
	  
	  // Laicode
	  public ListNode selectionSort(ListNode head) {
		    // Write your solution here
		    if(head == null){
		      return head;
		    }
		    ListNode sorted = new ListNode(0);
		    ListNode dummy = new ListNode(0);
		    dummy.next = head;
		    ListNode minPrev = dummy;
		    ListNode min = minPrev.next;
		    ListNode s_curr = sorted;
		    while(dummy.next != null){
		      ListNode prev = dummy;
		      ListNode curr = prev.next;
		      minPrev = prev;
		      min = curr;
		      int global_min = curr.value;
		      while(curr != null){
		        if(curr.value < global_min){
		          global_min = curr.value;
		          minPrev = prev;
		          min = curr;
		        }
		        prev = curr;
		        curr = curr.next;
		      }
		      //remove min from head list
		      minPrev.next = min.next;
		      //connect min to sorted list
		      s_curr.next = min;
		      s_curr = s_curr.next;
		    }
		    s_curr.next = null;
		    return sorted.next;      
		  }
	  
	  // remove index k in a linked list
	  public ListNode remove(ListNode head, int index) {
		  if(head == null || index < 1) {
			  	return head;
		  }
		  ListNode dummy =  new ListNode(0);
		  dummy.next = head;
		  ListNode prev = dummy;
		  ListNode curr = head;
		  while(--index>0 && curr !=null) {
			  prev = curr;
			  curr = curr.next;
		  }
		  if(curr!=null) {
			  prev.next = curr.next;
		  }
		  return dummy.next;
	  }
	  
	  // 1->2->3->4->null  => 2->1->4->3->null change order in pair
	  public ListNode changeOrder(ListNode head) {
		  if(head == null || head.next == null) {
			  	return head;
		  }
		  ListNode dummy = new ListNode(0);
		  dummy.next = head;
		  ListNode pp = dummy;
		  ListNode prev = head;
		  ListNode curr = head.next;
		  while(curr!=null && curr.next!=null) {
			  ListNode next = curr.next;
			  curr.next = prev;
			  prev.next = next;
			  pp.next = curr;
			  pp = prev;
			  prev = next;  
			  curr = prev.next;
		  }
		  if(curr!=null) {
			  curr.next = prev;
			  prev.next = null;
			  pp.next = curr;
		  }
		  return dummy.next;
	  }
	  
	  //Laicode 
	  public ListNode reorder(ListNode head) {
		    // Write your solution here
		    if(head == null || head.next == null)
		      return head;
		    // Find mid node
		    ListNode slow = head, fast = head;
		    while(fast.next != null && fast.next.next != null){
		      slow = slow.next;
		      fast = fast.next.next;
		    }
		    ListNode l2 = slow.next;
		    ListNode l1 = head;
		    slow.next = null;
		    l2 = reverse(l2);
		    ListNode dummy = new ListNode(0);
		    ListNode curr = dummy,s1 = l1, s2 = l2;
		    while(s1!=null && s2!=null){
		      curr.next = s1;
		      curr = curr.next;
		      s1 = s1.next;
		      curr.next = s2;
		      curr = curr.next;
		      s2 = s2.next;
		    }
		    if(s1!=null){
		      curr.next = s1;
		    }
		    if(s2!=null){
		      curr.next = s2;
		    }
		    return dummy.next;
	}
		  private ListNode reverse(ListNode head){
		    if(head == null || head.next ==null) return head;
		    ListNode subhead = reverse(head.next);
		    head.next.next = head;
		    head.next = null;
		    return subhead;
		  }
}
