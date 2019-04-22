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

	public final static int DEFAULT_CAP = 100;
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
	public void put(K s, V val) {
		int index = hash(s);
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

