package round2;
import java.util.*;
class TreeSolution {
	public TreeNode recover1(TreeNode root) {
	    // Write your solution here
	    // morris traverse
	    if(root == null){
	      return root;
	    }
	    TreeNode curr = root;
		TreeNode prev = new TreeNode(Integer.MIN_VALUE);
		TreeNode first = null;
		TreeNode second = null;
		TreeNode prevInOrder = null;
		while(curr != null) {
			if(curr.left == null) {
				if(first == null && curr.key<prev.key){
					first = prev;
				}
				if(first != null && curr.key<prev.key) {
					second = curr;
				}
				prev = curr;
				curr = curr.right;
			}else {
				prevInOrder = findPrev(curr);
				if(prevInOrder.right == null) {
					prevInOrder.right = curr;
					curr = curr.left;
				}else if(prevInOrder.right == curr) {
					prevInOrder.right = null;
					if(first == null && curr.key<prev.key){
						first = prev;
					}
					if(first != null && curr.key<prev.key) {
						second = curr;
					}
					prev = curr;
					curr = curr.right;
				}
			}
		}
		if(second == null) {
			return root;
		}
		int tmp = first.key;
	    first.key = second.key;
	    second.key = tmp;
		return root;
	}
	// O(n) time  
	// O(1) space
	public void morrisTraverse(TreeNode root) {
		if(root == null) {
			return;
		}
		TreeNode curr = root;
		TreeNode prev = null;
		while(curr != null) {
			if(curr.left == null) {
				System.out.println(curr.key);
				curr = curr.right;
			}else {
				prev = findPrev(curr);
				if(prev.right == null) {
					prev.right = curr;
					curr = curr.left;
				}else if(prev.right == curr) {
					prev.right = null;
					System.out.println(curr.key);
					curr = curr.right;
				}
			}
		}
	}
	private TreeNode findPrev(TreeNode root) {
		TreeNode curr = root.left;
		while(curr.right!= null && curr.right != root) {
			curr = curr.right;
		}
		return curr;
	}
	public TreeNode recover(TreeNode root) {
	    // Write your solution here
	    // Brute force
	    if(root == null){
	      return null;
	    }
	    TreeNode[] first = new TreeNode[1];
	    TreeNode[] second = new TreeNode[1];
	    TreeNode[] prev = new TreeNode[1];
	    prev[0] = new TreeNode(Integer.MIN_VALUE);
	    inOrder(root,prev,first,second);
	    int tmp = first[0].key;
	    first[0].key = second[0].key;
	    second[0].key = tmp;
	    return root;
	  }
	private void inOrder(TreeNode root, TreeNode[] prev, TreeNode[] first, TreeNode[] second){
	    if(root == null){
	      return;
	    }
	    inOrder(root.left,prev,first,second);
	    if(first[0] == null && prev[0].key > root.key){
	      first[0] = prev[0];
	    }
	    if(first[0] != null && root.key < prev[0].key){
	      second[0] = root;
	    }
	    prev[0] = root;
	    inOrder(root.right,prev,first,second);
	}
	
	public int getDepth(TreeNode root, TreeNode target) {
		if(root == null) {
			return -1;
		}
		if(root == target) {
			return 0;
		}
		int left = getDepth(root.left,target);
		int right = getDepth(root.right,target);
		if(left == -1 && right == -1) {
			return -1;
		}
		return left == -1 ? right +1 : left +1;
	}
	
	public int getDepth(TreeNode root, int target) {
		if(root == null) {
			return -1;
		}
		if(root.key == target) {
			return 0;
		}
		int left = getDepth(root.left,target);
		int right = getDepth(root.right,target);
		if(left == -1 && right == -1) {
			return -1;
		}
		return left == -1 ? right +1 : left +1;
	}
	
	public List<Integer> inOrderIterative(TreeNode root){
		/*
		 * 1. Initialization: push left children all the way to the leftmost into stack
		 * 2. Stack top is the current node
		 */
		List<Integer> res = new ArrayList<>();
		Deque<TreeNode> stack = new ArrayDeque<>();
		pushLeft(root,stack);
		while(!stack.isEmpty()) {
			TreeNode curr = stack.pollFirst();
			res.add(curr.key);
			pushLeft(curr.right,stack);
		}
		return res;
	}
	private void pushLeft(TreeNode root, Deque<TreeNode> stack) {
		TreeNode curr = root;
		while(curr!= null) {
			stack.offerFirst(curr);
			curr = curr.left;
		}
	}
	
	// Return the TreeNode whose key is smalleset and larger than the target
	public TreeNode smallestLargerThan(TreeNode root, int target) {
		TreeNode res = null;
		TreeNode curr = root;
		while(curr != null) {
			if(curr.key>target) {
				res = curr;
				curr = curr.left;
			}else {
				curr = curr.right;
			}
		}
		return res;
	}
	
	//Reconstruct a binary tree from inorder and postorder traversal sequence 
	// post 1 4 3 11 8 5
	// in   1 3 4 5 8 11 
	public TreeNode reconstruct(int[] in, int[] post) {
		if(in == null || post == null) {
			return null;
		}
		Map<Integer,Integer> hm = new HashMap<>();
		for(int i=0;i<in.length;i++) {
			hm.put(in[i], i);
		}
		return reconstructHelper(in,0,in.length-1,post,0,post.length-1,hm);
	}
	private TreeNode reconstructHelper(int[] in, int inLeft, int inRight, int[] post, int postLeft, int postRight, Map<Integer,Integer> inMap) {
		// Base Case 
		if(inLeft > inRight) {
			return null;
		}
		TreeNode root = new TreeNode(post[postRight]);
		int inMid = inMap.get(post[postRight]);
		int leftSize = inMid-inLeft;
		root.left = reconstructHelper(in,inLeft,inLeft+leftSize-1,post,postLeft,postLeft+leftSize-1,inMap);
		root.right = reconstructHelper(in,inLeft+leftSize+1,inRight,post,postLeft+leftSize,postRight-1,inMap);
		return root;
	}
	
	// Given inorder and preorder traversal sequence and return postorder sequence
	// in  : 4,2,5,1,3,6
	// pre : 1,2,4,5,3,6
	// post: 4,5,2,6,3,1
	public int[] constructPostOrder(int[] in, int[] pre) {
		if(in == null || pre == null) {
			return null;
		}
		Map<Integer,Integer> hm = new HashMap<>();
		for(int i=0;i<in.length;i++) {
			hm.put(in[i], i);
		}
		int[] post = new int[in.length];
		postOrderHelper(in,pre,post,hm,0,in.length-1,0,pre.length-1,post.length-1);
		return post;
	}
	private void postOrderHelper(int[] in, int[] pre, int[] post, Map<Integer,Integer> inMap, 
			int inLeft,int inRight, int preLeft,int preRight, int postRight) {
		if(inLeft > inRight) {
			return;
		}
		int inMid = inMap.get(pre[preLeft]);
		int leftSize = inMid - inLeft;
		int rightSize = inRight - inMid;
		post[postRight] = pre[preLeft];
		postOrderHelper(in,pre,post,inMap,inLeft,inLeft+leftSize-1,preLeft+1,preLeft+leftSize,postRight-1-rightSize);
		postOrderHelper(in,pre,post,inMap,inMid+1,inRight,preLeft+leftSize+1,preRight,postRight-1);
	}
	
	//Verify pre-order serialization is valid
	public boolean isValidSerialization(String preorder) {
	    if(preorder == null || preorder.length() == 0) return false;
	    Deque<String> stack = new ArrayDeque<>();
	    String[] array = preorder.split(",");
	    stack.offerLast(array[0]);
	    for(int i=1;i<array.length;i++){
	      if(!array[i].equals("#") || stack.peekLast() == null || !stack.peekLast().equals("#")){
	        stack.offerLast(array[i]);
	      }else{
	        String top = stack.peekLast();
	        while(top!=null && top.equals("#")){
	          stack.pollLast();
	          if(stack.isEmpty()){
	              return false;
	          }
	          top = stack.peekLast();
	        }
	        stack.offerLast("#");
	      }
	    }
	    return stack.size() == 1 && stack.pollLast().equals("#");
	    }
	
	//Ternary Expression Tree
	public ExpNode expressionTree(String exp) {
		if(exp == null || exp.isEmpty()) {
			return null;
		}
		return expressionTreeHelper(exp.toCharArray(),0,exp.length()-1);
	}
	private ExpNode expressionTreeHelper(char[] exp, int left, int right) {
		if(left == right) {
			ExpNode root = new ExpNode(exp[left]);
			return root;
		}
		ExpNode root = new ExpNode(exp[left]);
		int count = 0;
		for(int i=left+1;i<right;i++) {
			if(exp[i]=='?') {
				count++;
			}else if(exp[i] == ':') {
				count--;
			}
			if(count == 0) {
				root.left = expressionTreeHelper(exp,left+2,i-1);
				root.right = expressionTreeHelper(exp,i+1,right);
				break;
			}
		}
		return root;
	}
	
	public TreeNode findNode(TreeNode root, int value) {
		if(root==null || root.key == value) {
			return root;
		}
		TreeNode left = findNode(root.left,value);
		TreeNode right = findNode(root.right,value);
		return left==null? right : left;
	}
	
	
	public int[] closestKValues(TreeNode root, double target, int k) {
	    // Write your solution here
	    // if we have a sorted array, find the closest k values, we can binary search to find the closest first, and find the other k-1 values subsequently. O(logN + k )
	    // Here is binary search tree, to find the closest, O(logn). 
	    // A trick we can do here is to record the path that we get to the closest node using Stacks  :-(i don't know how to do it...
	    List<Integer> list = new ArrayList<>();
	    inOrder(root,list);
	    // Binary search to find the closest
	    int left = 0;
	    int right = list.size()-1;
	    while(left<right-1){
	      int mid = left + (right-left)/2;
	      if(list.get(mid)<=target){
	        left = mid;
	      }else{
	        right = mid;
	      }
	    }
	    // at this point, left or right must be point to the number closest to target
	    //(left, right)is the result
	    while((right<list.size() || left >=0) && right-left-1<k){
	      if((right<list.size()&&left>=0)){
	        if(Math.abs(list.get(left)-target) <= Math.abs(list.get(right)-target)){
	          left--;
	        }else {
	          right++;
	        }
	      }else if(right==list.size()){
	        left--;
	      }else{
	        right++;
	      }
	    }
	    // Copy to int[] and return
	    int size = Math.min(k,right-left-1);
	    int[] res = new int[size];
	    for(int i=0;i<size;i++){
	      res[i] =  list.get(left+i+1);
	    }
	    return res;
	  }
	  private void inOrder(TreeNode root, List<Integer> list){
	    if(root == null){
	      return;
	    }
	    inOrder(root.left,list);
	    list.add(root.key);
	    inOrder(root.right,list);
	  }
	  
	  public List<Integer> leftView(TreeNode root) {
		  List<Integer> res = new ArrayList<>();
		  while(root != null) {
			  res.add(root.key);
			  if(root.left != null) {
				  root = root.left;
			  }else {
				  root = root.right;
			  }  
		  }
		  return res;
	  }
	  
	  public List<Integer> topView(TreeNode root) {
		    // Write your solution here
		    Map<Integer,List<Element>> map = new TreeMap<>();
		    int[] min = new int[]{0};
		    int[] max = new int[]{0};
		    preprocess(root,map,0,min,max,0);
		    List<Integer> res = new ArrayList<>();
		    for(int i=min[0];i<=max[0];i++){
		      Collections.sort(map.get(i));
		      res.add(map.get(i).get(0).node.key);
		    }
		    return res;
		  }
		  private void preprocess(TreeNode root, Map<Integer,List<Element>> map, int x,int[] min, int[] max, int level){
		    if(root == null){
		      return;
		    }
		    List<Element> list = map.get(x);
		    if(list!=null){
		      list.add(new Element(level,root));
		    }else{
		      List<Element> newList = new ArrayList<>();
		      newList.add(new Element(level,root));
		      map.put(x,newList);
		    }
		    min[0] = Math.min(x,min[0]);
		    max[0] = Math.max(x,max[0]);
		    preprocess(root.left,map,x-1,min,max,level+1);
		    preprocess(root.right,map,x+1,min,max,level+1);
		  }
		  
		  public void preOrder(TreeNode root) {
			  if(root == null) return;
			  System.out.println(root.key);
			  preOrder(root.left);	  
			  preOrder(root.right);
		  }
		  
		  public List<TreeNode> generateBSTs(int n) {
			    List<TreeNode> res = new ArrayList<>();
			    res = generateBSTs(1,n);
			    return res;
			  }
		  private List<TreeNode> generateBSTs(int start, int end){
			    if(start>end){
			      return new ArrayList<>();
			    }
			    if(start==end){
			      List<TreeNode> res = new ArrayList<>();
			      res.add(new TreeNode(start));
			      return res;
			    }
			    List<TreeNode> res = new ArrayList<>();
			    for(int i=start;i<=end;i++){
			      
			      List<TreeNode> left = generateBSTs(start,i-1);
			      List<TreeNode> right = generateBSTs(i+1,end);
			      if(left.size() == 0) {
			    	  for(TreeNode rchild : right){
			    		  TreeNode root = new TreeNode(i);
				          root.right = rchild;
				          res.add(root);
				      }
			      }else if(right.size()==0) {
			    	  for(TreeNode lchild : left){
			    		  TreeNode root = new TreeNode(i);
				          root.left = lchild;
				          res.add(root);
				      }
			      }else {
			    	  for(TreeNode lchild : left){
			    		  for(TreeNode rchild : right){
			    			  TreeNode root = new TreeNode(i);
			    			  root.left = lchild;
			    			  root.right = rchild;
			    			  res.add(root);
			    		  }
			    	  }
			      }
			    }
			    return res;
			  }
		  
		 public TreeNode deleteSingleChildNode(TreeNode root) {
			 if(root == null) {
				 return root;
			 }
			 root.left = deleteSingleChildNode(root.left);
			 root.right = deleteSingleChildNode(root.right);
			 if((root.left == null && root.right == null) || (root.left !=null && root.right != null)) {
				 return root;
			 }
			 return root.left == null? root.right : root.left;
		 }
		 public boolean isCousins(TreeNode root, TreeNode a, TreeNode b) {
			 boolean[] res = new boolean[1];
			 lcaLevel(root,a,b,0,res);
			 return res[0];
		 }
		 private int lcaLevel(TreeNode root, TreeNode a, TreeNode b, int level, boolean[] res) {
			 if(root == null) {
				 return -1;
			 }
			 if(root==a || root == b) {
				 return level;
			 }
			 int left = lcaLevel(root.left,a,b,level+1,res);
			 int right = lcaLevel(root.right,a,b,level+1,res);
			 if(left!=-1 && right!= -1) {
				 if(left==right && left-level>1) {
					 res[0] = true;
				 }
				 return level;
			 }
			 return left == -1? right: left;
		 }
		  
}
class Element implements Comparable<Element>{
	  int y;
	  TreeNode node;
	  public Element(int y, TreeNode node){
	    y = y;
	    node = node;
	  }
	  public int compareTo(Element o1){
	    return this.y - o1.y;
	  }
	}
