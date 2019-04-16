package laioffer;

public class MyHashMap {
	private Node[] buckets;
	private int size;
	public final static int DEFAULT_CAP = 100;
	
	public MyHashMap(){
		this(DEFAULT_CAP);
	}
	
	public MyHashMap(int cap){
		buckets = new Node[cap];
		this.size = 0;
	}
	public void put(String s, Integer val) {
		int index = getHash(s);
		Node n = buckets[index];
		if(n == null) {
			buckets[index] = new Node(s,val);
			size++;
			return;
		}
		Node prev = null;
		while(n!=null) {
			if(n.key.equals(s)) {
				n.value = val;
				return;
			}
			prev = n;
			n = n.next;
		}
		prev.next = new Node(s,val);
		size++;
		return;
	}
	public Integer get(String s) {
		int index = getHash(s);
		Node n = buckets[index];
		while(n!=null) {
			if(n.key.equals(s)) {
				return n.value;
			}
			n = n.next;
		}
		return null;
	}
	private int getHash(String s) {
		return s.hashCode()%buckets.length;
	}
}
class Node{
	String key;
	Integer value;
	Node next;
	public Node(String key, Integer value) {
		this.key = key;
		this.value = value;
		next = null;
	}
}
