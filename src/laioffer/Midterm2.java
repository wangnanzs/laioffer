package laioffer;
import java.util.*;
public class Midterm2 {
	public static final void main(String[] args) {
		Midterm2Solution sol = new Midterm2Solution();
		List<List<Integer>> list = sol.nqueens(4);
		for(List<Integer> l:list){
			sol.print(l);
		}
	}
}
class Midterm2Solution{
	public void print(List<Integer> list) {
		for(Integer i : list) {
			System.out.print(i + " ");
		}
	}
	public List<List<Integer>> nqueens(int n) {
	    // Write your solution here
	    List<List<Integer>> res = new ArrayList<>();
	    int[] array = new int[n];
	    for(int i=0;i<n;i++){
	        array[i] = i;
	    }
	    boolean[] left = new boolean[2*n-1];
	    boolean[] right = new boolean[2*n-1];
	    nqueensDFS(array,0,res,left,right);
	    return res;
	  }
	  // DFS: N queens have to be in different rows and different cols, so its like permutation
	  // of [0:N-1]
	  // N levels: every level present which col the queen is on for this row
	  private void nqueensDFS(int[] array, int level, List<List<Integer>> res, boolean[] left, boolean[] right){
	    // Base Case 
	    if(level == array.length){
	        List<Integer> temp = new ArrayList<>();
	        for(int i = 0;i<array.length;i++){
	            temp.add(array[i]);
	        }
	        res.add(temp);
	        return;
	    }
	    for(int i = level;i<array.length;i++){
	    	swap(array,level,i);
	    	if(!left[level-array[level]+array.length-1] && !right[array[level]+level]){
	            left[level-array[level]+array.length-1] = true;
	            right[array[level]+level] = true;
	            nqueensDFS(array,level+1,res,left,right);
	            right[array[level]+level] = false;
	            left[level-array[level]+array.length-1] = false;
	        }
	    	swap(array,level,i);
	    }
	  }
	  private void swap(int[] array, int i, int j){
	    int temp = array[i];
	    array[i] = array[j];
	    array[j] = temp;
	  }
	
	
	public void nQueens(int n){
		int[] array = new int[n];
		boolean[] checkCol = new boolean[n];
		boolean[] checkLeft = new boolean[2*n-1]; // slope = -1
		boolean[] checkRight = new boolean[2*n-1]; // slope = 1
		nQueensDFS(array,0,checkCol,checkLeft,checkRight);
	}
	private void nQueensDFS(int[] array, int row, boolean[] checkCol, boolean[] checkLeft, boolean[] checkRight) {
		//Base Case
		if(row == array.length) {
			print(array);
			return;
		}
		for(int i=0;i<array.length;i++) {
			if(isValid(array,row,i,checkCol,checkLeft,checkRight)) {
				array[row] = i;
				checkCol[i] = true;
				checkLeft[i-row+array.length-1] = true;
				checkRight[row+i] = true;
				nQueensDFS(array,row+1,checkCol,checkLeft,checkRight);
				checkCol[i] = false;
				checkLeft[i-row+array.length-1] = false;
				checkRight[row+i] = false;
			}
		}
	}
	private boolean isValid(int[] array, int row, int col, boolean[] checkCol, boolean[] checkLeft, boolean[] checkRight) {
		return !checkCol[col] && !checkLeft[col-row+array.length-1] && !checkRight[row+col];
	}
	private void print(int[] array) {
		for(int i=0;i<array.length;i++) {
			System.out.print(array[i]+" ");
		}
		System.out.println();
	}
	
}