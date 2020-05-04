package round2;
import java.util.*;
class ArraySolution {
	public int[] countArray(int[] array) {
//	Sort indexArray based on array[indexArray[i]]
		int[] indexArray = initializeIndexArray(array);
		int[] helper = new int[array.length];
		int[] countArray = new int[array.length];
		mergeSort(array,indexArray,helper,countArray,0,array.length-1);
		return indexArray;
	}
	private int[] initializeIndexArray(int[] array) {
		int[] indexArray = new int[array.length];
		for(int i=0;i<array.length;i++) {
			indexArray[i] = i;
		}
		return indexArray;
	}
	private void mergeSort(int[] array, int[] indexArray, int[] helper, int[] countArray, int left, int right) {
		if(left >= right) {
			return;
		}
		int mid = left + (right-left)/2;
		mergeSort(array,indexArray,helper,countArray,left,mid);
		mergeSort(array,indexArray,helper,countArray,mid+1,right);
		merge(array,indexArray,helper,countArray,left,mid,right);
	}
	private void merge(int[] array, int[] indexArray, int[] helper, int[] countArray, int left, int mid, int right) {
		for(int i=left;i<=right;i++) {
			helper[i] = indexArray[i];
		}
		int l = left;
		int r = mid+1;
		int curr = left;
		while(l<=mid) {
			if(r>right || array[helper[l]]<=array[helper[r]]) {
				countArray[helper[l]] += r-mid-1;
				indexArray[curr++] = helper[l++];
			}else {
				indexArray[curr++] = helper[r++];
			}
		}
	}
	
	public int[] rainbowSortII(int[] array) {
	    // Write your solution here
	    // We can use 4 pointers to divide this array into 5 regions
	    // [0,i) Red 
	    // [i,j) Green
	    // [j,k) Blue
	    // [k,t] to be checked
	    // (t,N-1]
	    int i=0;
	    int j = 0;
	    int k = 0;
	    int t = array.length-1;
	    while(k<=t){
	      if(array[k]==0){
	    	if(j!=k) {
	    		swap(array,i,j);
	    	}
	        swap(array,i,k);
	        i++;
	        j++;
	        k++;
	      }else if(array[k]==1){
	    	swap(array,i,k);
	        j++;
	        k++;
	      }else if(array[k] == 2){
	        k++;
	      }else if(array[t] == 3){
	        t--;
	      }else{
	        swap(array,k,t);
	        t--;
	      }
	    }
	    return array;
	  }
	  private void swap(int[] array, int i, int j){
	    int temp = array[i];
	    array[i] = array[j];
	    array[j] = temp;
	  }
	  public int threeSumSmaller(int[] num, int target) {
		    if(num == null){
		      return 0;
		    }
		    Arrays.sort(num); // O(nlogn)
		    int res = 0;
		    for(int i=0;i<num.length-2;i++){ // O(n^2)
		      res += twoSumSmaller(num,target-num[i],i+1,num.length-1);
		    }
		    return res;
		  }
		  private int twoSumSmaller(int[] num, int target, int left, int right){ //O(n)
		    int i = left;
		    int j = right;
		    int res = 0;
		    while(i<j){
		      if(num[i]+num[j]>=target){
		        j--;
		      }else{
		        res++;
		        i++;
		      }
		    }
		    return res;
		  }
		  public int search(int[][] matrix) {
			    // Write your solution here
			    if(matrix.length<2) return -1;
			    int i=0;
			    int j=0;
			    int common = -1;
			    while(i<matrix[0].length && j<matrix[1].length ){
			      if(matrix[0][i]==matrix[1][j]){
			        common = matrix[0][i];
			        break;
			      }else if(matrix[0][i]<matrix[1][j]){
			        i++;
			      }else{
			        j++;
			      }
			    }
			    for(int k=2;k<matrix.length;k++){
			      if(!find(matrix[k],common)){
			        return -1;
			      }
			    }
			    return common;
			  }
			  private boolean find(int[] array, int target){
			    int left = 0;
			    int right = array.length-1;
			    while(left<right-1){
			      int mid = left + (right-left)/2;
			      if(array[mid] == target){
			        return true;
			      }else if(array[mid]<target){
			        left = mid;
			      }else{
			        right = mid;
			      }
			    }
			    if(array[left] == target || array[right] == target) return true;
			    return false;
			  }
			  
			  public int[][] diff(int[] a, int[] b) {
				    // 谁小移谁
				    List<Integer> onlyA = new ArrayList<>();
				    List<Integer> onlyB = new ArrayList<>();
				    int i=0;
				    int j=0;
				    while(i<a.length && j<b.length){
				      if(a[i]<b[j]){
				        onlyA.add(a[i++]);
				      }else if(a[i]>b[j]){
				        onlyB.add(b[j++]);
				      }else{
				        i++;
				        j++;
				      }
				    }
				    while(i<a.length){
				      onlyA.add(a[i++]);
				    }
				    while(j<b.length){
				      onlyB.add(b[j++]);
				    }
				    int[][] res = new int[2][onlyA.size()];
				    for(j=0;j<onlyA.size();j++){
				      res[0][j] = onlyA.get(j);
				      res[1][j] = onlyB.get(j);
				    }
				    return res;
				  }
			  
}
