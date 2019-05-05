package laioffer;
import java.util.Iterator;
public class MyList<T> implements Iterable<T> {
	
	public static final int DEFAULT_SIZE = 10;
	private T[] array;
	private int size;
	
	public MyList() {
		this(DEFAULT_SIZE);
	}
	@SuppressWarnings("unchecked")
	public MyList(int cap) {
		array = (T[]) new Object[cap];
		size = 0;
	}
	public MyList(T[] input) {
		array = input;
		size = input.length;
	}
	// function name: iterator
	// return type Iterator<T>
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private int current=0;
			@Override
			public T next(){
				return array[current++];
			}
			@Override
			public boolean hasNext() {
				return current<size && array[current] != null;
			}
			@Override
			public  void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
	
}
