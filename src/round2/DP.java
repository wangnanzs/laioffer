package round2;

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
	
//	public int maxProfit(int[] array, int k) {
//	    // Write your solution here
//	    // Recursion
//	    return maxProfit(array,0,array.length-1,k);
//	}
//	private int maxProfit(int[] array, int left, int right, int k){
//	    
//	    if(right-left < 0){
//	      return 0;
//	    }
//	    if(right-left <= 1){
//	      return Math.max(0,array[right]-array[left]);
//	    }
//	    if(k==1){
//	      int[] M = new int[right-left+1]; // M[i] represent smallest number from left to left+i-th , not including ith
//	      M[1] = array[left];
//	      for(int i=2;i<M.length;i++){
//	        M[i] = Math.min(M[i-1], array[left+i-1]);
//	      }
//	      int max = 0;
//	      for(int i=1;i<M.length;i++) {
//	    	  max = Math.max(max, array[left+i]-M[i]);
//	      }
//	      return max;
//	    }
//	    int res = 0;
//	    for(int i = right;i>=0;i--){
//	      res = Math.max(res,maxProfit(array,left,i,k-1)+maxProfit(array,i+1,right,1));
//	    }
//	    return res;
//	}
}
