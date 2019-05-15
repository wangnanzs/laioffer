package laioffer;
import java.util.*;
public class PeiLianKe {
	
}

class KTreeNode{
	int val;
	List<KTreeNode> children;
}
class PLKSolution{
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
