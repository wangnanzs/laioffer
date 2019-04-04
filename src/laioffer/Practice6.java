package laioffer;
public class Practice6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BoundedStack bs = new BoundedStack(10);
		bs.push(1);
		bs.push(2);
		bs.push(3);
		bs.push(4);
		while(bs.size != 0) {
			System.out.println(bs.pop());
		}
	}

}

class IntStack{
	private ListNode head;
	public void push(int value) {
		ListNode newhead = new ListNode(value);
		newhead.next = head;
		head = newhead;
	}
	public Integer pop() {
		if(head == null) {
			return null;
		}
		ListNode prev = head;
		head = head.next;
		prev.next = null;
		return prev.value;
	}
	public Integer peek() {
		if(head == null) {
			return null;
		}
		return head.value;
	}
}

class IntQueue{
	private ListNode head;
	private ListNode tail;
	public void offer(int value) {
		if(head == null) {
			head = new ListNode(value);
			tail = head;
		}else {
			tail.next = new ListNode(value);
			tail = tail.next;
		}
	}
	public Integer poll() {
		if(head == null) {
			return null;
		}
		ListNode prev = head;
		if(head == tail) {
			head = null;
			tail = null;
			return prev.value;
		}
		head = head.next;
		prev.next = null;
		return prev.value;
	}
	public Integer peek() {
		if(head == null) {
			return null;
		}
		return head.value;
	}
}

class BoundedQueue {
	int head;
	int tail;
	int size;
	Integer[] array;
	public BoundedQueue(int cap) {
		array = new Integer[cap];
		size = 0;
		head = 0;
		tail = 0;
	}
	public boolean offer(Integer value) {
		if(size == array.length) {
			return false;
		}
		array[tail] = value;
		tail = (tail+1)%array.length;
		size++;
		return true;
	}
	public Integer poll() {
		if(size == 0) {
			return null;
		}
		Integer res = array[head];
		head = (head+1)%array.length;
		size--;
		return res;
	}
	public Integer peek() {
		if(size == 0) {
			return null;
		}
		return array[head];
	}
}

class BoundedStack{
	int[] array;
	int top;
	int size;
	public BoundedStack(int cap) {
		array = new int[cap];
		top = -1;
		size = 0;
	}
	public boolean push(int value) {
		if(size == array.length) {
			return false;
		}
		array[++top] = value;
		size++;
		return true;
	}
	public Integer pop() {
		if(size == 0) {
			return null;
		}
		int res = array[top--];
		size--;
		return res;
	}
	public Integer peek() {
		if(size == 0) {
			return null;
		}
		return array[top];
	}
	public int size() {
		return size;
	}
}

