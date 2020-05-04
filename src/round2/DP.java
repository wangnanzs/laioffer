package round2;

import java.util.*;

class DP {
	public double largestProduct(double[] array) {
	    // Write your solution here
	    // DP 
	    double[] M1 = new double[array.length];
	    // M1[i] represents max subarray product from array[0...i] and must include index i-th
	    double[] M2 = new double[array.length];
	    // M2[i] represents negatively max subarray product from array[0...i] and must include index i-th
	    M1[0] = array[0] > 0 ? array[0] : 0;
	    M2[0] = array[0] > 0 ? 0 : array[0];
	    double max = M1[0];
	    for(int i=1;i<array.length;i++){
	      // Case 1: array[i] > 0 && M1[i-1] < 1  -> M1[i] = array[i]
	      // Case 2: array[i] > 0 && M1[i-1] >= 1  -> M1[i] = M1[i-1]*array[i]
	      // Case 3: array[i] = 0  => M1[i] = 0
	      // Case 4: array[i] < 0 M1[i] = M2[i-1]*array[i]
	      if(array[i] > 0){
	        M1[i] = M1[i-1] < 1 ? M1[i]= array[i]:M1[i-1]*array[i];
	        M2[i] = M2[i-1] *array[i];
	      }else if(array[i]==0){
	        M1[i] = 0;
	        M2[i] = 0;
	      }else{
	        M1[i] = M2[i-1]*array[i];
	        M2[i] = M1[i-1] < 1 ? array[i]:M1[i-1]*array[i];
	      }
	      max = Math.max(max,M1[i]);
	    }
	    return max;
	}
	public int minCost(int[] stones) {
	    // Write your solution here
	    // DP 
	    int[] preSum = new int[stones.length];
	    preSum[0] = stones[0];
	    for(int i=1;i<stones.length;i++){
	      preSum[i] = preSum[i-1]+stones[i];
	    }
	    int[][] M = new int[stones.length][stones.length];
	    // M[i][j] reps min cost of merging from i-th to j-th inclusively
	    // Initialization
	    for(int i=0;i<stones.length;i++){
	      M[i][i] = 0;
	    }
	    for(int j = 1;j<stones.length;j++){
	      int x = 0;
	      int y = j;
	      while(x<stones.length && y<stones.length){
	        M[x][y] = Integer.MAX_VALUE;
	        for(int i=x;i<y;i++){
	          M[x][y] = Math.min(M[x][y],M[x][i]+M[i+1][y]+preSum[y]-preSum[x]+stones[x]);
	        }
	        x++;
	        y++;
	      }
	    }
	    return M[0][stones.length-1];
	}
	public int maxProfit(int[] array, int k) {
	    // Write your solution here
	    // DP 
      int[][] M = new int[k][array.length];
      // M[i][j] represent the max profix in max i transactions using price [0...j]
      M[0][0] = 0;
      for(int i=0;i<M.length;i++){
        for(int j=0;j<M[0].length;j++){
          M[i][j] = 0;
          for(int t=0;t<j;t++){
            M[i][j] = Math.max(M[i][j],getValue(M,i-1,t-1)+array[j]-array[t]);
          }
          M[i][j] = Math.max(getValue(M,i,j-1), M[i][j]);
        }
      }
      return M[k-1][array.length-1];
	}
	private int getValue(int[][] array, int i, int j){
		if(i<0 || j<0 || i>=array.length || j >= array[0].length){
			return 0;
		}
		else{
			return array[i][j];
		}
	}
	public int maxProfit(int[] array) {
	    // Write your solution here
	    int[] ML = new int[array.length]; // ML represent the lowest price from 0 to i-th,not including the i-th index
	    int[] MH = new int[array.length]; // MH reprenent the highest price from i-th to the last, not including i-th 
	    ML[0] = 0;
	    ML[1] = array[0];
	    MH[array.length-1] = 0;
	    MH[array.length-2] = array[array.length-1];
	    for(int i=2;i<array.length;i++){
	        ML[i] = Math.min(ML[i-1],array[i-1]);
	    }
	    for(int i=array.length-3;i>=0;i--){
	      MH[i] = Math.max(MH[i+1],array[i+1]);
	    }
	    int max = 0;
	    for(int i=1;i<array.length;i++){
	        int left = 0;
	        int right = 0;
	        for(int j=1;j<=i;j++){
	          left = Math.max(left,array[j]-ML[j]);
	        }
	        for(int j=i+1;j<array.length;j++){
	          right = Math.max(right,MH[j]-array[j]);
	        }
	        max = Math.max(max,left+right);
	    }
	    return max;
	  }
	
	public int largestSquareSurroundedByOne(int[][] matrix) {
	    // Write your solution here
	    // Brute force : O(n^4)
	    int M = matrix.length;
	    int N = matrix[0].length;
	    int[][] left2right = new int[M][N]; // longest consecutive ones that must end with col N in row M 
	    int[][] right2left = new int[M][N];
	    int[][] top2bot = new int[M][N];
	    int[][] bot2top = new int[M][N];

	    for(int i=0;i<M;i++){
	      for(int j=0;j<N;j++){
	        if(j==0){
	          left2right[i][j] = matrix[i][j];
	          right2left[i][N-1-j] = matrix[i][N-1-j];
	        }else{
	          left2right[i][j] = matrix[i][j] == 1? left2right[i][j-1]+1 : 0;
	          right2left[i][N-1-j] = matrix[i][N-1-j] == 1? right2left[i][N-j]+1 : 0;
	        }
	      }
	    }
	    for(int j=0;j<N;j++){
	      for(int i=0;i<M;i++){
	        if(i==0){
	          top2bot[i][j] = matrix[i][j];
	          bot2top[M-1-i][j] = matrix[M-1-i][j];
	        }else{
	          top2bot[i][j] = matrix[i][j] == 1? top2bot[i-1][j]+1 : 0;
	          bot2top[M-1-i][j] = matrix[M-1-i][j] == 1? bot2top[M-i][j]+1 : 0;
	        }
	      }
	    }
	    int global_max = 0;
	    for(int i=0;i<M;i++){
	      for(int j=0;j<N;j++){
	        int len = 1;
	        while(i+len-1<M && j+len-1<N){
	          if(bot2top[i][j]>=len && right2left[i][j]>=len && top2bot[i+len-1][j+len-1]>=len && left2right[i+len-1][j+len-1]>=len){
	            global_max = Math.max(global_max,len);
	          } 
	          len++;
	        }
	      }
	    }
	    return global_max;
	  }
	
//	public int canWin(int[] nums) {
//	    // Write your solution here
//	    // DP
//	    // M[i][j] represent the sum of pizza that I can get from index i to index j
//	    int N = nums.length;
//	    int[][] M = new int[N][N];
//	    // base case
//	    for(int i=0;i<N;i++){
//	      M[i][i] = nums[i];
//	      if(i+1<N){
//	        M[i][i+1] = Math.max(nums[i],nums[i+1]);
//	      }
//	    }
//	    for(int i=N-2;i>=0;i--){
//	      for(int j=i+2;j<N;j++){
//	        // if I choose i
//	        int sum1 = 0;
//	        if(nums[i+1]>nums[j]){
//	          sum1 = nums[i] + M[i+2][j];
//	        }else{
//	          sum1 = nums[i] + M[i+1][j-1];
//	        }
//	        // if I choose j
//	        int sum2 = 0;
//	        if(nums[i]>nums[j-1]){
//	          sum2 = nums[j] + M[i+1][j-1];
//	        }else{
//	          sum2 = nums[j] + M[i][j-2];
//	        }
//	        M[i][j] = Math.max(sum1,sum2);
//	      }
//	    }
//	    return M[0][N-1];
//	  }
	
	public void print2DArray(int[][] array) {
		for(int i=0;i<array.length;i++) {
			for(int j=0;j<array[0].length;j++) {
				System.out.print(array[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public int[] longest(int[] a) {
	    // Write your solution here
	    int N = a.length;
	    int[] M = new int[N];
	    // M represents the length of longest subquence from [0,i] which must include a[i]
	    M[0] = 1;
	    int globalMax = 1;
	    int index = 0;
	    for(int i=1;i<N;i++){
	      int max = 1;
	      for(int j=0;j<i;j++){
	        if(a[i]>a[j]){
	          max = Math.max(max,M[j]+1);
	        }
	      }
	      M[i] = max;
	      if(max>=globalMax){
	        globalMax = max;
	        index = i;
	      }
	    }
	    int[] res = new int[globalMax];
	    int i = res.length-1;
	    int curr = a[index];
	    res[i--] = a[index];
	    while(index>=0){
	      if(a[index]<curr){
	        curr = a[index];
	        res[i--] = a[index];
	      }
	      index--;
	    }
	    return res;
	  }
	
	public String longestPalindrome(String input) {
	    // Write your solution here
	    // DP
	    // time O(N^2)  space O(N^2)
	    if(input == null || input.length()==0){
	      return input;
	    }
	    int N = input.length();
	    boolean[][] M = new boolean[N][N];
	    // M[i][j] represents if substring [i,j] is palindrome
	    for(int i=0;i<N;i++){
	      M[i][i] = true;
	    }
	    int left = 0;
	    int globalMax = 1; // length of longest palindrome substring
	    for(int i=N-2;i>=0;i--){
	      for(int j=i;j<N;j++){
	        M[i][j] = input.charAt(i)==input.charAt(j) && (i+1<=j-1? M[i+1][j-1] : true);
	        if(M[i][j] == true && j-i+1>globalMax){
	          globalMax = j-i+1;
	          left = i;
	        }
	      }
	    }
	    return input.substring(left,left+globalMax);
	  }
	
	public int largest(int[][] matrix) {
	    // Write your solution here 
	    // First create 4 2D array to memorize longest consecutive 1's from 4 differnet direction
	    // assume matrix is not null and M >=0 N>=0
	    if(matrix.length ==0 || matrix[0].length == 0){
	      return 0;
	    }
	    int M = matrix.length;
	    int N = matrix[0].length; // M[i][j] represent the longest consecutive 1 in M[i] that ends at M[i][j]
	    int[][] top2bottom = new int[M][N];
	    int[][] left = new int[M][N];
	    int[][] right = new int[M][N];
	    // Base Case
	    for(int j=0;j<N;j++){
	      top2bottom[0][j] = matrix[0][j];
	    }
	    // Induction rule
	    for(int i=1;i<M;i++){
	      for(int j=0;j<N;j++){
	        if(matrix[i][j] == 1){
	          top2bottom[i][j] = top2bottom[i-1][j]+1;
	        }else{
	          top2bottom[i][j] = 0; 
	        }
	      }
	    }
	    for(int i=0;i<M;i++){
	      for(int j=0;j<N;j++){
	        int height  = top2bottom[i][j];
	        int index = j;
	        while(index >=0 && top2bottom[i][index]>=height){
	          index--;
	        }
	        left[i][j] = index+1;
	        index = j;
	        while(index<N && top2bottom[i][index]>=height){
	          index++;
	        }
	        right[i][j] = index-1;
	      }
	    }
	    int globalMax = 0;
	    for(int i=0;i<M;i++){
	      for(int j=0;j<N;j++){ // top left corner position(k,t)
	        int max_area = top2bottom[i][j] * (right[i][j]-left[i][j]+1);
	        globalMax = Math.max(globalMax,max_area);
	      }
	    }
	    return globalMax;
	  }
	
	public double largest(double[][] matrix) {
	    // Write your solution here
	    return subLargest(matrix,0,0,matrix.length-1,matrix[0].length-1);
	  }

	  private double subLargest(double[][] matrix, int topI, int topJ, int botI, int botJ){
	    if(topI > botI || topJ > botJ){
	      return 0;
	    }
	    for(int i = botI; i>= topI; i--){
	      for(int j=botJ; j>= topJ; j--){
	        if(matrix[i][j] == 0){
	          double sub1 = subLargest(matrix,topI,topJ,i-1,botJ);
	          double sub2 = subLargest(matrix,topI,topJ,botI,j-1);
	          double sub3 = subLargest(matrix,topI,j+1,botI,botJ);
	          double sub4 = subLargest(matrix,i+1,topJ,botI,botJ);
	          return Math.max(Math.max(Math.max(sub1,sub2),Math.max(sub3,sub4)),0);
	        }
	      }
	    }
	    int m = botI - topI + 1;
	    int n = botJ - topJ + 1;
	    double[][] M = new double[m][n];
	    // M[i][j] represent the submatrix product whose left top corner is 0,0 and right bottom corner is i,j
	    // Base Case
	    M[0][0] = matrix[topI][topJ];
	    for(int j=1;j<n;j++){
	      M[0][j] = M[0][j-1] * matrix[topI][topJ+j];
	    }
	    for(int i=1;i<m;i++){
	      M[i][0] = M[i-1][0] * matrix[topI+i][topJ];
	    }
	    for(int i=1;i<m;i++){
	      for(int j=1;j<n;j++){
	        M[i][j] = M[i-1][j] * M[i][j-1]/ M[i-1][j-1] * matrix[topI+i][topJ+j]; 
	      }
	    }
	    double globalMax = matrix[topI][topJ];
	    for(int i=0;i<m;i++){
	      for(int j=0;j<n;j++){
	        for(int k=i;k<m;k++){
	          for(int t=j;t<n;t++){
	            double product = M[k][t]/getValue(M,k,j-1,topI,topJ)/getValue(M,i-1,t,topI,topJ)*getValue(M,i-1,j-1,topI,topJ);
	            globalMax = Math.max(globalMax,product);
	          }
	        }
	      }
	    }
	    return globalMax;
	  }
	  private double getValue(double[][] M, int i, int j,int topI, int topJ){
	    if(i<topI || j<topJ){
	      return 1;
	    }else{
	      return M[i][j];
	    }
	  }
	  
	  public boolean canWin(int[] nums) {
		   // Write your solution here
		   if(nums == null || nums.length == 0){
		     return false;
		   }
		   if(nums.length == 1){
		     return true;
		   }
		   int N = nums.length;
		   // DP
		   int[][] M = new int[N][N];
		   // M[i][j] represent if I choose first from index i thru index j, the maximum total sum I can get no matter what the friend choose
		   // Base case
		   for(int i=0;i<N;i++){
		     M[i][i] = nums[i];
		     if(i+1<N){
		       M[i][i+1] = Math.max(nums[i],nums[i+1]);
		     }
		   }
		   // induction rule
		   // case 1: I pick i, the friend pick nums[i+1] , I have M[i+2][j]
		   // case 2: I pick i , the friend pick num[j], I have M[i+1][j-1]
		   // case 3: I pick j, the friend pick nums[i], I have M[i+1][j-1]
		   // case 4: I pick j, the friend pick nums[j-1], I have M[i][j-2]
		   for(int i=N-3;i>=0;i--){
		     for(int j=i+2;j<N;j++){
		       M[i][j] = nums[i] + Math.min( M[i+2][j] , M[i+1][j-1]);
		       M[i][j] = Math.max(M[i][j],nums[j] + Math.min( M[i+1][j-1] ,M[i][j-2]));
		     }
		   }
		   int sum = 0;
		   for(int i=0;i<N;i++){
		     sum += nums[i];
		   }
		   return M[0][N-1] >= sum-M[0][N-1];
		 }

		
	  public int minimumBox(int n) {
		  Set<Integer> hs = new HashSet<>();
		  for(int i=1;i*i<=n;i++) {
			  hs.add(i*i);
		  }
		  int[] M = new int[n+1];
		  // M[i] reprsent the minimum box needed to wrap n gifts
		  M[0] = 0;
		  M[1] = 1;
		  // M[i] = min(M[j])+1 if (i-j) is a square number
		  for(int i=2;i<=n;i++) {
			  M[i] = i;
			  for(int j=0;j<i;j++) {
				  if(hs.contains(i-j)) {
					  M[i] = Math.min(M[i], M[j]+1);
				  }
			  }
		  }
		  return M[n];
	  }
}
