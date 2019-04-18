package laioffer;

public class MyHashMap {
	private HashNode[] buckets;
	private int size;
	public final static int DEFAULT_CAP = 100;
	
	public MyHashMap(){
		this(DEFAULT_CAP);
	}
	
	public MyHashMap(int cap){
		buckets = new HashNode[cap];
		size = 0;
	}
	public void put(String s, Integer val) {
		int index = getHash(s);
		HashNode n = buckets[index];
		if(n == null) {
			buckets[index] = new HashNode(s,val);
			size++;
			return;
		}
		HashNode prev = null;
		while(n!=null) {
			if(n.key.equals(s)) {
				n.value = val;
				return;
			}
			prev = n;
			n = n.next;
		}
		prev.next = new HashNode(s,val);
		size++;
		return;
	}
	public Integer get(String s) {
		int index = getHash(s);
		HashNode n = buckets[index];
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
class HashNode{
	String key;
	Integer value;
	HashNode next;
	public HashNode(String key, Integer value) {
		this.key = key;
		this.value = value;
		next = null;
	}
}
