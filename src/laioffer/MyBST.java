package laioffer;

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
		inOrder(this.root);
	}
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
		return null;
	}
}
