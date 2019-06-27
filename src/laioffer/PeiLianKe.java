package laioffer;
import java.util.*;
public class PeiLianKe {
	public static void main(String[] args) {
//		Integer[] array = new Integer[] {5,2,10,1,3,8,13,null,null,null,4,null,null,11,15};
//		TreeNode root = TreeGenerator.generate(array);
		PLKSolution plk = new PLKSolution();
//		TreeNode root = plk.reconstruct(new int[] {1,3,2,5,7,6,4});
//		root = plk.convertToList(root);
//		while(root!=null) {
//			System.out.print(root.val+" ");
//			root = root.right;
//		}
//		int[] array = new int[] {1,1,1,4,3,3};
//		System.out.println(plk.maxTrapped(array));
//		System.out.println(plk.majority(array));
		Point p1 = new Point(1,1);
		Point p2 = new Point(2,3);
		Point p3 = new Point(1,1);
		Point p4 = new Point(2,3);
		Point p5 = new Point(1,1);
		Point p6 = new Point(2,2);
		Point p7 = new Point(3,4);
		Point p8 = new Point(9,10);
		Point[] points = new Point[] {p1,p2,p3,p4,p5,p6,p7,p8};
		System.out.println(plk.most(points));
	}
}

class KTreeNode{
	int val;
	List<KTreeNode> children;
}

class Point {
   public int x;
   public int y;
   public Point(int x, int y) {
     this.x = x;
     this.y = y;
   }
}

class Pair{
    private double k;
    private double b;
    public Pair(double k, double b){
        this.k = k;
        this.b = b;
    }
    @Override
    public int hashCode() {
    	return (int)(k*31+b);
    }
    @Override 
    public boolean equals(Object o) {
    	if(o == null) {
    		return false;
    	}
    	if(this == o) {
    		return true;
    	}
    	if(o instanceof Pair && ((Pair) o).k == k && ((Pair) o).b == b ) {
    		return true;
    	}else {
    		return false;
    	}
    }
}

class PLKSolution{
	public int most(Point[] points) {
	    // Write your solution here.
	    Map<Pair,Set<Point>> map1 = new HashMap<>();
	    Map<Integer,Set<Point>> map2 = new HashMap<>();
	    for(int i = 0;i<points.length;i++){
	        for(int j=i+1;j<points.length;j++){
	            Point one = points[i];
	            Point two = points[j];
	            if(one.x == two.x){
	                if(map2.get(one.x) == null){
	                    map2.put(one.x, new HashSet<>());
	                }
	                map2.get(one.x).add(one);
	                map2.get(one.x).add(two);
	            }else{
	                double k = (one.y-two.y+0.0)/(one.x-two.x);
	                double b = (one.x*two.y-two.x*one.y+0.0)/(one.x-two.x);
	                Pair pair = new Pair(k,b);
	                if(map1.get(pair)==null){
	                    map1.put(pair,new HashSet<>());
	                }
	                map1.get(pair).add(one);
	                map1.get(pair).add(two);
	            }
	        }
	    }
	    int max = 0;
	    for(Pair key : map1.keySet()){
	        max = Math.max(max,map1.get(key).size());
	    }
	    for(Integer key:map2.keySet()){
	        max = Math.max(max,map2.get(key).size());
	    }
	    return max;
	  }
	  
	public List<Integer> majority(int[] array) {
	    // Write your solution here
	    // Maintain 2 spots - Hashmap pair - value : count
	    // Read an element every time
	    // Case 1: if doesn't exist && size < 2: create one
	    // Case 2: if count == 1 count++
	    // Case 3: if doesn't exist && size == 2: count -- && delete if count == 0
	    List<Integer> res = new ArrayList<>();
	    if(array.length == 0){
	        return res;
	    }
	    Map<Integer,Integer> map = new HashMap<>();
	    for(int i =0;i<array.length;i++){
	        Integer curr = map.get(array[i]);
	        if(curr !=null){
	            map.put(array[i],curr+1);
	        }else if(map.size()<2){
	            map.put(array[i],1);
	        }else{
	            List<Integer> toDelete = new ArrayList<>();
	            for(Integer key : map.keySet()){
	                if(map.get(key)==1){
	                    toDelete.add(key);
	                }else{
	                map.put(key,map.get(key)-1);
	                }
	            }
	            for(Integer key: toDelete){
	                map.remove(key);
	            }
	        }
	    }
	    for(Integer key : map.keySet()){
	        int count = 0;        
	        for(int i=0;i<array.length;i++){
	            if(array[i] == key){
	                count++;
	            }
	            if(count>array.length/3){
	                res.add(key);
	                break;
	            }
	        }
	    }
	    return res;
	  }
	public int maxTrapped(int[] array) {
	    // Write your solution here
	    // DP find the max max element from left end to i-th and right end to i-th
	    int[] left = new int[array.length];
	    int[] right = new int[array.length];
	    left[0] = array[0];
	    right[array.length-1] = array[array.length-1];
	    for(int i = 1;i<left.length;i++){
	        left[i] = left[i-1] > array[i] ? left[i-1] : array[i];
	    }
	    for(int i = right.length-2;i>=0;i--){
	        right[i] = right[i+1] > array[i] ? right[i+1] : array[i];
	    }
	    int sum = 0;
	    for(int i=0;i<array.length;i++){
	        int leftMax = i==0? 0 : left[i-1];
	        int rightMax = i== array.length-1? 0:right[i+1];
	        int height = Math.min(leftMax,rightMax) - array[i];
	        sum += Math.max(0,height);
	    }
	    return sum;
	}
	
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
//	private TreeNode construct1(int[] preOrder,int[] preLeft, int[] preRight, int target,Map<Integer,Integer> inMap) {
//		// Base case
//		if(preLeft[0] >= preOrder.length ||inMap.get(preOrder[preLeft[0]]) > inMap.get(preOrder[preRight[0]])) {
//			return null;
//		}
//		TreeNode root = new TreeNode(preOrder[preLeft[0]++]);
//		root.left = construct1(preOrder, preLeft, root.val,inMap);
//		root.right = construct1(preOrder,preLeft, target, inMap);
//	}
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
