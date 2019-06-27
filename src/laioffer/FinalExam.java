package laioffer;
import java.util.*;


public class FinalExam {
	public ListNode deleteTarget(ListNode head, int target) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode prev = dummy;
		ListNode curr = head;
		while(curr!=null) {
			if(curr.value == target) {
				prev.next = curr.next;
			}else {
				prev = prev.next;
			}
			curr = curr.next;
		}
		return dummy.next;
	}
}


