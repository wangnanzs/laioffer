package laioffer;
import java.util.*;
public class MyHeap {
	// for index i
	// left child 2*i+1
	// right child 2*i+2
	// parent (i-1)/2
	
	int[] array;
	int size;
	public MyHeap(int cap) {
		array = new int[cap];
		size = 0;
	}
	public MyHeap(int[] input) {
		if(input == null || input.length == 0) {
			throw new IllegalArgumentException("input array cannot be null or empty");
		}
		array = input;
		size = input.length;
		heapify();
	}
	public boolean isEmpty() {
		return size == 0;
	}
	public void offer(int value) {
		if(size == array.length) {
			int[] newArray = new int[2*array.length];
			for(int i = 0;i<array.length;i++) {
				newArray[i] = array[i];
			}
			array = newArray;
		}
		array[size++] = value;
		percolateUp(size-1);
	}
	public int update(int idx, int ele){
		if(idx < 0 || idx >= size) {
			throw new ArrayIndexOutOfBoundsException("invalid index range");
		}
		int result = array[idx];
		array[idx] = ele;
		if(ele > array[idx]) {
			percolateDown(idx);
		}else if(ele < array[idx]) {
			percolateUp(idx);
		}
		return result;
	}
	public Integer peek() {
		if(size <= 0) {
			throw new NoSuchElementException("Heap is empty");
		}
		return array[0];
	}
	public Integer poll() {
		if(size <= 0) {
			throw new NoSuchElementException();
		}
		Integer res = array[0];
		array[0] = array[--size];
		percolateDown(0);
		return res;
	}
	private void percolateUp(int i){
		while(i>0) {
			int parentIndex = (i-1)/2;
			if(array[i]<array[parentIndex]) {
				swap(i,parentIndex);
				i = parentIndex;
			}else {
				break;
			}
		}
	}
	private void percolateDown(int i) {
		// case 1 : has both left and right child
		while(2*i+1<size) {
			int leftChildIndex = 2*i+1;
			int rightChildIndex = 2*i+2;
			if(array[i] > array[leftChildIndex] && rightChildIndex < size && array[i]>array[rightChildIndex] ) {
				if(array[leftChildIndex]<array[rightChildIndex]) {
					swap(i,leftChildIndex);
					i = leftChildIndex;
				}else {
					swap(i,rightChildIndex);
					i = rightChildIndex;
				}
			}else if(array[i] > array[leftChildIndex]) {
				swap(i,leftChildIndex);
				i = 2*i+1;
			}else if(rightChildIndex < size && array[i] > array[rightChildIndex]) {
				swap(i,rightChildIndex);
				i = rightChildIndex;
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
	private void heapify() {
		int n = array.length;
		for(int i= n/2-1;i>=0;i--) {
			percolateDown(i);
		}
	}
	
	public void test(int[] input) {
		int n = input.length;
		for(int i= 0;i<n;i++) {
			input[i] = 888;
		}
	}
	
}