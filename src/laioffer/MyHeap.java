package laioffer;

public class MyHeap {
	// for index i
	// left child 2*i+1
	// right child 2*i+2
	// parent (i-1)/2
	
	Integer[] array;
	int index;
	public MyHeap(int size) {
		array = new Integer[size];
		index = -1;
	}
	public void insert(int value) {
		array[++index] = value;
		percolateUp();
	}
	//public void update(int)
	public Integer get() {
		return array[0];
	}
	public Integer pop() {
		Integer res = array[0];
		array[0] = array[index--];
		percolateDown();
		return res;
	}
	private void percolateUp(){
		int i = index;
		while(i>0) {
			if(array[i]<array[(i-1)/2]) {
				swap(i,(i-1)/2);
				i = (i-1)/2;
			}else {
				break;
			}
		}
	}
	private void percolateDown() {
		// case 1 : has both left and right child
		
		int i = 0; 
		while(2*i+1<=index) {
			if(array[i] > array[2*i+1] && 2*i+2 <= index &&array[i]>array[2*i+2] ) {
				if(array[2*i+1]<array[2*i+2]) {
					swap(i,2*i+1);
					i = 2*i+1;
				}else {
					swap(i,2*i+2);
					i = 2*i+2;
				}
			}else if( array[i] > array[2*i+1]) {
				swap(i,2*i+1);
				i = 2*i+1;
			}else if(2*i+2 <= index && array[i] > array[2*i+2]) {
				swap(i,2*i+2);
				i = 2*i+2;
			}else {
				break;
			}
		}
	}
	private void swap(int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	//public void heapify() 
}
