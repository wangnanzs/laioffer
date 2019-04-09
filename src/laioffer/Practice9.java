package laioffer;

import java.util.*;

public class Practice9 {
	public static void main(String[] args) {
		GraphNode one = new GraphNode(1);
		GraphNode two = new GraphNode(2);
		GraphNode three = new GraphNode(3);
		GraphNode four = new GraphNode(4);
		one.neighbors = new ArrayList<GraphNode>();
		one.neighbors.add(two);
//		one.neighbors.add(three);
		two.neighbors = new ArrayList<GraphNode>();
		two.neighbors.add(one);
		two.neighbors.add(three);
//		two.neighbors.add(four);
		three.neighbors = new ArrayList<GraphNode>();
		three.neighbors.add(two);
		three.neighbors.add(four);
		four.neighbors = new ArrayList<GraphNode>();
//		four.neighbors.add(two);
		four.neighbors.add(three);
		List<GraphNode> graph = new ArrayList<>();
		graph.add(one);
		graph.add(two);
		graph.add(three);
		graph.add(four);
		System.out.println(GraphSolution.isBipartite(graph));
	}
}
class HeapSolution{
	public static int[] smallestK(int[] array, int k) {  //minHeap  is a offline algorithm
		final long startTime = System.nanoTime();
		if(k <= 0) {
			return new int[0];
		}
		if(k>=array.length) {
			return array;
		}
		int[] res = new int[k];
		Queue<Integer> pq = new PriorityQueue<>();
		for(int i =0;i<array.length;i++) {
			pq.offer(array[i]);
		}
		for(int i = 0;i<k;i++) {
			res[i] = pq.poll();
		}
		final long duration = System.nanoTime() - startTime;
		System.out.println(duration);
		return res;
	}
	public static int[] smallestK1(int[] array, int k) {
		final long startTime = System.nanoTime();
		if(k <= 0) {
			return new int[0];
		}
		if(k>=array.length) {
			return array;
		}
		int[] res = new int[k];
		Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		for(int i=0;i<k;i++) {
			pq.offer(array[i]);
		}
		for(int i = k;i<array.length;i++) {
			if(array[i]<pq.peek()) {
				pq.poll();
				pq.offer(array[i]);
			}
		}
		for(int i =k-1;i>=0;i--) {
			res[i] = pq.poll();
		}
		final long duration = System.nanoTime() - startTime;
		System.out.println(duration);
		return res;
	}
	public static int[] quickSelect(int[] array, int k) {
		final long startTime = System.nanoTime();
		if(k <= 0) {
			return new int[0];
		}
		if(k>=array.length) {
			return array;
		}
		quickSelect(array,k,0,array.length-1);
		int[] res = new int[k];
		for(int i=0;i<k;i++) {
			res[i] = array[i];
		}
		final long duration = System.nanoTime() - startTime;
		System.out.println(duration);
		return res;
	}
	private static void quickSelect(int[] array, int k, int left, int right) {
		Random rand = new Random();
		int index = left + rand.nextInt(right-left+1);
		swap(array,index,right);
		int i = left;
		int j = right-1;
		while(i<=j) {
			if(array[i] < array[right]) {
				i++;
			}else if(array[j] > array[right]) {
				j--;
			}else {
				swap(array,i,j);
				i++;
				j--;
			}
		}
		swap(array,i,right);
		if(i-left == k-1) {
			return;
		}else if(i-left > k-1) {
			quickSelect(array,k,left,i);
		}else {
			quickSelect(array,k-1-i+left,i+1,right);
		}
	}
	private static void swap(int[]array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}

class GraphNode {
	public int key;
    public List<GraphNode> neighbors;
    public GraphNode(int key) {
      this.key = key;
      this.neighbors = new ArrayList<GraphNode>();
    }
}

class GraphSolution{
	public static boolean isBipartite(List<GraphNode> graph) {
	    // write your solution here
		
		Map<GraphNode,Integer> hm = new HashMap<>();
	    Queue<GraphNode> q = new LinkedList<>();
	    hm.put(graph.get(0),-1);
	    for(GraphNode node : graph) {
	    	q.offer(node);
	        if(!hm.containsKey(node)){
	        	hm.put(node,-1);
	        }else {
	        	continue;
	        }
	    	while(!q.isEmpty()) {
	    		GraphNode curr = q.poll();
	    		int n = curr.neighbors.size();
	    		for(int i=0;i<n;i++) {
	    			if(hm.containsKey(curr.neighbors.get(i))) {
	    				if(hm.get(curr) == hm.get(curr.neighbors.get(i))) {
	    					return false;
	    				}
	    			}else {
	    				hm.put(curr.neighbors.get(i),hm.get(curr)*-1);
	    				q.offer(curr.neighbors.get(i));
	    			}
	    		}
	    	}
	    }
	    return true;
	}
	
	public int kthSmallest(int[][] matrix, int k) {
	    // Write your solution here
	    // Initial state
	    // Expand/Generate rule
	    // Terminate condition
	    // Dedup
	    int n = matrix.length;
	    int m = matrix[0].length;
	    Queue<Cell> pq = new PriorityQueue<>(k, new Comparator<Cell>(){
	      @Override
	      public int compare(Cell l, Cell r){
	        if(l.value == r.value){
	          return 0;
	        }else if(l.value < r.value){
	          return -1;
	        }else{
	          return 1;
	        }
	      }});
	    pq.offer(new Cell(0,0,matrix[0][0]));
	    boolean[][] visited = new boolean[n][m];
	    int count = 1;
	    while(!pq.isEmpty() && count < k){
	      Cell curr = pq.poll();
	      count++;
	      if(curr.col+1<m && !visited[curr.row][curr.col+1]){
	        pq.offer(new Cell(curr.row,curr.col+1,matrix[curr.row][curr.col+1]));
	        visited[curr.row][curr.col+1] = true;
	      }
	      if(curr.row+1<n && !visited[curr.row+1][curr.col]){
	        pq.offer(new Cell(curr.row+1,curr.col,matrix[curr.row+1][curr.col]));
	        visited[curr.row+1][curr.col] = true;
	      }
	    }
	    return pq.poll().value;
	  }
	  static class Cell{
	    int row;
	    int col;
	    int value;
	    Cell(int i,int j,int value){
	      row = i;
	      col = j;
	      this.value = value;
	    }
	  }
	
}

