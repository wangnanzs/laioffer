package laioffer;
import java.util.*;
public class MyHashMap<K,V> {
	public static class HashNode<K,V>{
		K key;
		V value;
		HashNode<K,V> next;
		HashNode(K key, V value) {
			this.key = key;
			this.value = value;
			next = null;
		}
		public K getKey() {
			return key;
		}
		public V getValue() {
			return value;
		}
		public void setValue(V value) {
			this.value = value;
		}
	}

	public final static int DEFAULT_CAP = 4;
	public final static float DEFAULT_LOAD_FACTOR = 0.75f;
	private HashNode<K,V>[] buckets;
	private int size;
	private float loadFactor;
	
	public MyHashMap(){
		this(DEFAULT_CAP,DEFAULT_LOAD_FACTOR);
	}
	
	public MyHashMap(int cap, float loadFactor){
		if(cap <= 0) {
			throw new IllegalArgumentException("Cap cannot be <= 0");
		}
		buckets = (HashNode<K,V>[])(new HashNode[cap]);
		size = 0;
		this.loadFactor = loadFactor;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		Arrays.fill(buckets, null);
		size = 0;
	}
	
	private int hash(K key) {
		if(key == null) {
			return 0;
		}
		int hash = key.hashCode()%buckets.length;
		return hash > 0 ? hash : -1*hash;
	}
	public V put(K s, V val) {
		int index = hash(s);
		HashNode<K,V> head = buckets[index];
		HashNode<K,V> node = head;
		while(node!=null) {
			if(node.key.equals(s)) {
				V result = node.value;
				node.value = val;
				return result;
			}
			node = node.next;
		}
		HashNode<K,V> newNode = new HashNode<K,V>(s,val);
		newNode.next = head;
		buckets[index] = newNode;
		size++;
		if(needRehashing()) {
			rehashing();
		}
		return null;
	}
	private boolean needRehashing() {
		if((float)size/buckets.length >= loadFactor) {
			return true;
		}
		return false;
	}
	private void rehashing() {
		HashNode<K,V>[] newArray = (HashNode<K,V>[])new HashNode[2*buckets.length];
		HashNode<K,V>[] oldArray = buckets;
		buckets = newArray;
		size = 0;
		for(int i = 0;i<oldArray.length;i++) {
			HashNode<K,V> head = oldArray[i];
			while(head != null) {
				put(head.key,head.value);
				head = head.next;
			}
		}
	}
	public V get(K key) {
		int index = hash(key);
		HashNode<K,V> n = buckets[index];
		while(n!=null) {
			if(n.key.equals(key)) {
				return n.value;
			}
			n = n.next;
		}
		return null;
	}
	public void showBucketSize(){
		System.out.println(buckets.length);
	}
}

