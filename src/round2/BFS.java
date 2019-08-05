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


