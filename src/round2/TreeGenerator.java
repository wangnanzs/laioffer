package round2;
import java.util.*;

class TreeGenerator {
	public TreeNode generate(Integer[] array) {
		if(array == null || array.length == 0) {
			return null;
		}
		TreeNode[] nodeArray = new TreeNode[array.length];
		for(int i=0; i<array.length;i++) {
			if(array[i] != null) {
				nodeArray[i] = new TreeNode(array[i]);
				if(i>0 && i%2==1) {
					nodeArray[(i-1)/2].left = nodeArray[i];
				}else if(i>0 && i%2==0){
					nodeArray[(i-1)/2].right = nodeArray[i];
				}
			}
		}
		return nodeArray[0];
	}
	public TreeNode deserialize(String input) {
		if(input.isEmpty()) {
			return null;
		}
		Deque<TreeNode> queue = new ArrayDeque<>();
		String[] str = input.split(",");
		TreeNode root = new TreeNode(Integer.valueOf(str[0]));
		queue.offerLast(root);
		for(int i=1;i<str.length;i+=2) {
			TreeNode cur = queue.pollFirst();
			if(!str[i].equals("#")) {
				cur.left = new TreeNode(Integer.valueOf(str[i]));
				queue.offerLast(cur.left);
			}
			if(!str[i+1].equals("#")) {
				cur.right = new TreeNode(Integer.valueOf(str[i+1]));
				queue.offerLast(cur.right);
			}
		}
		return root;
	}
}
