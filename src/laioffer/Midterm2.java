package laioffer;

public class Midterm2 {
	public static final void main(String[] args) {
		Midterm2Solution sol = new Midterm2Solution();
		sol.nQueens(4);
	}
}
class Midterm2Solution{
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