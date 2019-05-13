package laioffer;

import java.util.*;
import java.util.Iterator;


public class MyBST<T> implements Iterable<T> {
	static class MyTreeNode<T>{
		T key;
		MyTreeNode<T> left;
		MyTreeNode<T> right;
		public MyTreeNode(T key) {
			this.key = key;
			left = null;
			right = null;
		}
	}
	private MyTreeNode<T> root;
	public MyBST(T[] array){
		if(array == null || array.length == 0) {
			return;
		}
		@SuppressWarnings("unchecked")
		MyTreeNode<T>[] nodeArray = (MyTreeNode<T>[])new MyTreeNode[array.length];
		for(int i=0; i<array.length;i++) {
			if(array[i] != null) {
				nodeArray[i] = new MyTreeNode<T>(array[i]);
				if(i>0 && i%2==1) {
					nodeArray[(i-1)/2].left = nodeArray[i];
				}else {
					nodeArray[(i-1)/2].right = nodeArray[i];
				}
			}
		}
		root = nodeArray[0];
	}
	public void print() {
		preOrder(this.root);
	}
	private void preOrder(MyTreeNode<T> root) {
		if(root == null) {
			return;
		}
		System.out.println(root.key);
		preOrder(root.left);
		preOrder(root.right);
	}
	@SuppressWarnings("unused")
	private void inOrder(MyTreeNode<T> root) {
		//Base case
		if(root == null) {
			return;
		}
		inOrder(root.left);
		System.out.println(root.key);
		inOrder(root.right);
	}
	public Iterator<T> iterator(){
		// Pre-Order iterator
		return new preOrderIterator();
	}
	class preOrderIterator implements Iterator<T>{

//		private MyTreeNode<T> curr;
		Deque<MyTreeNode<T>> st;
		public preOrderIterator() {
			st = new ArrayDeque<>();
			if(root != null) {
				 st.offerLast(root);
			}
		}
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return (!st.isEmpty());
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			MyTreeNode<T> curr = st.pollLast();
			if(curr.right!=null) {
				st.offerLast(curr.right);
			}
			if(curr.left!= null) {
				st.offerLast(curr.left);
			}
			return curr.key;
		}
		
	}
}
