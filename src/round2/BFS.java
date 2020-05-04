package round2;
import java.util.*;
class BFS {
	public int kthSum(int[] A, int[] B, int k) {
		// Use a heap to store Ai and Bi, and a sum variable to sort
		Queue<Pair> pq = new PriorityQueue<>(k,new MyComparator());
		int count = 0;
		boolean[][] visited = new boolean[A.length][B.length];
		int i = 0;
		int j = 0;
		int sum = A[i]+B[j];
		visited[i][j] = true;
		pq.offer(new Pair(i,j,sum));
		while(!pq.isEmpty()) {
			Pair curr = pq.poll();
			count++;
			if(count == k) {
				return curr.sum;
			}
			if(curr.i+1<A.length && curr.j<B.length && !visited[curr.i+1][curr.j]) {
				visited[curr.i+1][curr.j] = true;
				pq.offer(new Pair(curr.i+1,curr.j,A[curr.i+1]+B[curr.j]));
			}
			if(curr.i<A.length && curr.j+1<B.length && !visited[curr.i][curr.j+1]) {
				visited[curr.i][curr.j+1] = true;
				pq.offer(new Pair(curr.i,curr.j+1,A[curr.i]+B[curr.j+1]));
			}
		}
		return -1;
	}
	
	public int numOfSteps(int[] values) {
	    int start = encode (values);
	    Deque<Integer> queue = new ArrayDeque<>();
	    Set<Integer> hs = new HashSet<>();
	    queue.offerLast(start);
	    hs.add(start);
	    int res = 0;
	    while(!queue.isEmpty()){
	      int size = queue.size();
	      for(int i=0;i<size;i++){
	        Integer curr = queue.pollFirst();
	        if(curr == 1234567){
	          return res;
	        } 
	        int zero = recover(values,curr);
	        for(int j=0;j<4;j++){
					  int next = 0;
					  switch(j){
					    case 0: next = zero-4;break;
					    case 1: next = zero+1;break;
					    case 2: next = zero+4;break;
					    case 3: next = zero-1;break;
					  }
	          if(canMove(values,zero,next)){
	            swap(values,zero,next);
	            int code = encode(values);
	            swap(values,zero,next);
	            if(!hs.contains(code)){
	              queue.offerLast(code);
	              hs.add(code);
	            }
	          }
	        }
	      }
	      res++;
	    }
	    return -1;
	  }
	  private boolean canMove(int[] values,int curr, int next){
			if(next<0 || next>=values.length){
				return false;
			}
			if((next == 3 && curr == 4) || (next==4 && curr ==3)){
				return false;
			}
			return true;
	  }
	  private int encode(int[] array){
			int res = 0;
			for(int element : array){
				res = res * 10 + element;
			}
			return res;
		}
	  private int recover(int[] array, int map){
	    int res=0; 
	    for(int i=0;i<8;i++){
	      array[7-i] = map%10;
	      if(array[7-i]==0){
	        res = 7-i;
	      }
	      map = map/10;
	    }
	    return res;
	  }
	  private void swap(int[] nums, int i, int j){
	    int tmp = nums[i];
	    nums[i]= nums[j];
	    nums[j] = tmp;
	  }
	  
	  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		    // Write your solution here
		    // BFS: consider every transformation as a node in the graph
		    // initial condition: beginWord
		    // expand/generate rule
		    // termination condition
		    // dedup
		    Deque<String> queue = new ArrayDeque<>();
		    Set<String> hs = new HashSet<>();
		    queue.offerLast(beginWord);
		    hs.add(beginWord);
		    int res = 0;
		    while(!queue.isEmpty()){
		      int size = queue.size();
		      for(int i=0;i<size;i++){
		        String curr = queue.pollFirst();
		        if(curr == endWord){
		          return res;
		        }
		        for(int j=0;j<curr.length();j++){
		          for(int k=0;k<26;k++){
		            String next = curr.substring(0,j)+(char)('a'+k)+curr.substring(j+1,curr.length());
		            if(wordList.contains(next)&&!hs.contains(next)){
		              queue.add(next);
		              hs.add(next);
		            }
		          }
		        }
		      }
		      res++; 
		    }
		    return -1;
		  }
	  
	  public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
	        //write your code here
	    Deque<List<String>> queue = new ArrayDeque<>();
	    Set<String> hs = new HashSet<>();
	    List<List<String>> res = new ArrayList<>();
	    List<String> begin = new ArrayList<>();
	    begin.add(beginWord);
	    queue.offerLast(begin);
	    hs.add(beginWord);
	    while(!queue.isEmpty()){
	      int size = queue.size();
	      boolean reach = false;
	      Set<String> subVisited = new HashSet<>();
	      for(int i=0;i<size;i++){
	        List<String> currList = queue.pollFirst();
	        String curr = currList.get(currList.size()-1);
	        for(int j=0;j<curr.length();j++){
	          for(int k=0;k<26;k++){
	        	if(k==curr.charAt(j)-'a') {
	        		continue;
	        	}
	            String next = curr.substring(0,j)+(char)('a'+k)+curr.substring(j+1,curr.length());
	            if(wordList.contains(next) && !hs.contains(next)){ //     &&(!hm.containsKey(next)||next.equals(endWord))
	              if(next.equals(endWord)){
	    	          reach = true;
	    	          currList.add(next);
	    	          res.add(new ArrayList<>(currList));
	    	          currList.remove(currList.size()-1);
	    	      }
	              
	              currList.add(next);
	              queue.offerLast(new ArrayList<>(currList));
	              currList.remove(currList.size()-1);
	              subVisited.add(next);
	            }
	          }
	        }
	      }
	      hs.addAll(subVisited);
	      if(reach) {
	    	  return res;
	      }
	    }
	    return res;
	    }
	  
	  public int[] findOrder(int numCourses, int[][] prerequisites) {
		    // Write your solution here
		    // first to construct the graph 
		    // BFS to find order
		    List<List<Integer>> adj = new ArrayList<>(numCourses);
		    int[] inc = new int[numCourses]; // # of incoming edges
		    int n = numCourses;
		    while(n-->0){
		      adj.add(new ArrayList<>());
		    }
		    for(int[] edge: prerequisites){
		      inc[edge[0]]++;
		      adj.get(edge[1]).add(edge[0]);
		    }
		    // at this point, inc[] record how many incoming edge to the nodes and adj record the adjacency of the graph
		    Set<Integer> visited = new HashSet<>();
		    int[] res = new int[numCourses];
		    for(int i=0;i<numCourses;i++){
		      if(inc[i]==0 && !visited.contains(i)){
		        bfs(adj,i,res,visited,inc);
		      }
		    }
		    return res;
		  }
		  private void bfs(List<List<Integer>> adj, int node,int[] results,Set<Integer> hs,int[] inc){
		    Deque<Integer> queue = new ArrayDeque<>();
		    queue.offerLast(node);
		    results[hs.size()] = node;
		    hs.add(node);
		    while(!queue.isEmpty()){
		      int curr = queue.pollFirst();
		      for(Integer i: adj.get(curr)){
		        inc[i]--;
		        if(inc[i]==0 && !hs.contains(i)){
		          queue.offerLast(i);
		          results[hs.size()] = i;
		          hs.add(i);
		        }
		      }
		    }
		  }
		  
		  public String alienOrder(String[] words) {
			    // Write your solution here
			    // C : 1.dictionary always provide enough informartion to conclude the order? 
			    // A : 1. assume yes
			    // R
			    // T
			    // first, reconstruct the graph. use a List<List<Character>> to store neiboring relations and use an int[] array to keep track of how many incoming edges
			    if(words==null || words.length ==0){
			      return new String();
			    }
			    if(words.length==1){
			      return words[0];
			    }
			    if(words.length==2 && words[0].equals(words[1])){
			      return words[0];
			    }
			    int[] inc = new int[26];
			    List<Set<Character>> order = new ArrayList<>();
			    Set<Integer> visited = new HashSet<>();
			    reconstruct(words,order,inc,visited);
			    String res = new String();
					res = bfs(inc,order,new StringBuilder(),visited);
					for(int i=0;i<26;i++){
						if(inc[i]!=0){
							return new String();
						}
					}
			    return res;
			  }
			  private String bfs(int[] inc, List<Set<Character>> order, StringBuilder sb, Set<Integer> visited){
			    Deque<Integer> queue = new ArrayDeque<>();
			    for(int i=0;i<26;i++){
						if(inc[i]==0 && visited.contains(i)){
						  queue.offerLast(i);
						}
					}
			    while(!queue.isEmpty()){
			      Integer curr = queue.pollFirst();
			      sb.append((char)('a'+curr));
			      Set<Character> hs = order.get(curr);
			      for(Character c : hs){
			        inc[c-'a']--;
			        if(inc[c-'a']==0){
			          queue.offerLast(c-'a');
			        }
			      }
			    }
			    return sb.toString();
			  }

			  private void reconstruct(String[] words, List<Set<Character>> order, int[] inc,Set<Integer> visited){
				    int n = 26;
				    while(n-->0){
				      order.add(new HashSet<>());
				    }
				    for(int i=0;i<words.length;i++){
				      String curr = words[i];
				      for(int j=0;j<curr.length();j++){
				    	visited.add(curr.charAt(j)-'a');
				        while(j+1<curr.length() && curr.charAt(j)==curr.charAt(j+1)){
				          j++;
				        }
				        if(j+1<curr.length()){
				          visited.add(curr.charAt(j+1)-'a');
				          order.get(curr.charAt(j)-'a').add(curr.charAt(j+1));
				          inc[curr.charAt(j+1)-'a']++;
				        }
				      }
				      if(i+1<words.length) {
					      String next = words[i+1];
					      int j=0;
					      while(j<curr.length() && j< next.length() && curr.charAt(j)==next.charAt(j)){
								  // visited.add(prev.charAt(j)-'a');
								  j++;
								}
					      if(j<curr.length() && j< next.length()){
					        order.get(curr.charAt(j)-'a').add(next.charAt(j));
					        inc[next.charAt(j)-'a']++;
					      }
				      }
				    }
				 }
			  public boolean isCousins(TreeNode root, TreeNode a, TreeNode b) {
				  Deque<TreeNode> queue = new ArrayDeque<>();
				  queue.offerLast(root);
				  while(!queue.isEmpty()) {
					  int size = queue.size();
					  boolean oneCousinFound = false;
					  for(int i=0;i<size;i++) {
						  boolean oneSiblingFound = false;
						  TreeNode curr = queue.pollFirst();
						  if(curr==a || curr == b) {
							  if(!oneCousinFound) {
								  oneCousinFound = true;
							  }else {
								  return true;
							  }
							  
						  }
						  if(curr.left != null) {
							  queue.offerLast(curr.left);
							  if(curr.left== a || curr.left == b) {
								  oneSiblingFound = true;
							  }
						  }
						  if(curr.right != null) {
							  queue.offerLast(curr.right);
							  if((curr.right== a || curr.right == b) && oneSiblingFound) {
								 return false;
							  }
						  }
					  }
				  }
				  return false;
			  }
			  
}
class Pair{
	int i;
	int j;
	int sum;
	public Pair(int i,int j,int sum) {
		this.i = i;
		this.j = j;
		this.sum = sum;
	}
}
class MyComparator implements Comparator<Pair>{
	@Override
	public int compare(Pair a, Pair b) {
		if(a.sum == b.sum) {
			return 0;
		}else if(a.sum<b.sum) {
			return -1;
		}else {
			return 1;
		}
	}
}


