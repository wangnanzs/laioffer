package laioffer;
import java.util.*;
public class PeiLianKe {
	public static void main(String[] args) {
		Integer[] array = new Integer[] {5,2,10,1,3,8,13,null,null,null,4,null,null,11,15};
//		TreeNode root = TreeGenerator.generate(array);
		PLKSolution plk = new PLKSolution();
		TreeNode root = plk.reconstruct(new int[] {1,3,2,5,7,6,4});
		root = plk.convertToList(root);
		while(root!=null) {
			System.out.print(root.val+" ");
			root = root.right;
		}
	}
}

class KTreeNode{
	int val;
	List<KTreeNode> children;
}

class PLKSolution{
	public TreeNode reconstruct(int[] post) {
	    // Write your solution here
	    int[] in = new int[post.length];
	    for(int i=0;i<in.length;i++){
	        in[i] = post[i];
	    }
	    Arrays.sort(in);
	    Map<Integer,Integer> inMap = new HashMap<>();
	    for(int i=0;i<in.length;i++){
	        inMap.put(in[i],i);
	    }
	    return reconstruct(post,0,post.length-1,inMap);
	}
	private TreeNode reconstruct(int[] post, int left, int right, Map<Integer,Integer> inMap){
	    if(left > right){
	        return null;
	    }
	    TreeNode root = new TreeNode(post[right]);
	    int leftSize = inMap.get(post[right])-inMap.get(post[left]);
	    root.left = reconstruct(post,left,left+leftSize-1,inMap);
	    root.right = reconstruct(post,left+leftSize,right-1,inMap);
	    return root;
	}
	public TreeNode construct(int[] inOrder, int[] preOrder) {
		Map<Integer,Integer> inMap = new HashMap<>();
		for(int i=0;i<inOrder.length;i++) {
			inMap.put(inOrder[i],i);
		}
		return construct(inOrder,0,inOrder.length-1,preOrder,0,preOrder.length-1,inMap);
	}
	private TreeNode construct(int[] inOrder, int inLeft, int inRight,
							   int[] preOrder, int preLeft, int preRight,
							   Map<Integer,Integer> inMap) {
		// Base Case
		if(preLeft > preRight) {
			return null;
		}
		TreeNode root = new TreeNode(preOrder[preLeft]);
		int rootIndex = inMap.get(preOrder[preLeft]);
		int leftSize = rootIndex-inLeft;
		root.left = construct(inOrder,inLeft,inLeft+leftSize-1,preOrder,preLeft+1,preLeft+leftSize,inMap);
		root.right = construct(inOrder,rootIndex+1,inRight,preOrder,preLeft+leftSize+1,preRight,inMap);
		return root;
	}
	
	public TreeNode convertToList(TreeNode root) {
		TreeNode[] res = new TreeNode[2];// res[0] is head; res[1] is previous
		convertInOrder(root,res);
		return res[0];
	}
	private void convertInOrder(TreeNode root, TreeNode[] res) {
		if(root == null) {
			return;
		}
		convertInOrder(root.left,res);
		if(res[1] == null) {
			res[0] = root;
		}else {
			res[1].right = root;
			root.left = res[1];
		}
		res[1] = root;
		convertInOrder(root.right,res);
	}
	private void convertPreOrder(TreeNode root, TreeNode[] res) {
		if(root == null) {
			return;
		}
		TreeNode leftChild = root.left;
		TreeNode rightChild = root.right;
		if(res[0] == null) {
			res[0] = root;
		}
		if(res[1] != null) {
			res[1].right = root;
			root.left = res[1];
		}
		res[1] = root;
		convertPreOrder(leftChild,res);
		convertPreOrder(rightChild,res);
	}
	public List<Integer> zigZag(TreeNode root) {
	    // Write your solution here
	    // Similar to level-order traversal but with a Deque data structure
	    List<Integer> res = new ArrayList<>();
	    if(root == null){
	      return res;
	    }
	    Deque<TreeNode> dq = new ArrayDeque<>();
	    dq.offerFirst(root);
	    int layer = 0;
	    while(!dq.isEmpty()){
	      int size = dq.size();
	      for(int i=0;i<size;i++){
	        if(layer%2==0){
	          TreeNode curr = dq.pollFirst();
	          res.add(curr.val);
	          if(curr.right != null){
	            dq.offerLast(curr.right);
	          }
	          if(curr.left != null){
	            dq.offerLast(curr.left);
	          }
	        }else{
	          TreeNode curr = dq.pollLast();
	          res.add(curr.val);
	          if(curr.left != null){
	            dq.offerFirst(curr.left);
	          }
	          if(curr.right != null){
	            dq.offerFirst(curr.right);
	          }
	        }
	      }
	      layer++;
	    }
	    return res;  
	  }
	public KTreeNode LCA(KTreeNode root, Set<KTreeNode> set) {
		//Base case
		if(root == null || set.contains(root)) {
			return root;
		}
		KTreeNode temp = null;
		int count = 0;
		for(KTreeNode child : root.children) {
			if(LCA(child,set)!=null) {
				temp = child;
				count++;
				if(count == 2) {
					break;
				}
			}
		}
		if(count == 2) {
			return root;
		}
		if(count == 1) {
			return temp;
		}
		return null;
	}
}
