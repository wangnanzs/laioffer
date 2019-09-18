package round2;
import java.util.*;
class DFS {
	public void solveSudoku(char[][] board) {
        // Parse original board
        int[][] col = new int[9][9];
        int[][] row = new int[9][9];
        int[][] block = new int[9][9];
        int count = 0;
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                int blockseq = (i/3)*3+j/3;
                if(board[i][j]!='.'){
                	count++;
                    int num = board[i][j] - '0';
                    col[j][num-1] = 1;
                    row[i][num-1] = 1;
                    block[blockseq][num-1] = 1;
                }
            }
        }
        dfsSudoku(81-count,board,col,row,block);
    }
	private boolean dfsSudoku(int remaining, char[][] board, int[][] col, int[][] row, int[][] block){
        if(remaining == 0) {
        	return true;
        }
		for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]=='.'){
                    int blockseq = (i/3)*3+j/3;
                    for(int k=1;k<=9;k++){
                        if(col[j][k-1]==0 && row[i][k-1] == 0 && block[blockseq][k-1]==0){
                        	col[j][k-1]=1;
                            row[i][k-1] = 1;
                            block[blockseq][k-1]=1;
                            board[i][j] = (char)('0' + k);
                            boolean flag = dfsSudoku(remaining-1,board,col,row,block);
                            if(flag == true) {
                            	return true;
                            }
                            board[i][j] = '.';
                            col[j][k-1]=0;
                            row[i][k-1] = 0;
                            block[blockseq][k-1]=0;
                        }
                    }
                    return false;
                }
            }
        }
        return false;
    }
	public boolean canPartitionKSubsets1(int[] nums, int k) {
        int sum = 0;
        for(int num:nums)sum += num;
        if(k <= 0 || sum%k != 0)return false;
        int[] visited = new int[nums.length];
        return canPartition(nums, visited, 0, k, 0, 0, sum/k);
    }
    
    public boolean canPartition(int[] nums, int[] visited, int start_index, int k, int cur_sum, int cur_num, int target){
        if(k==1)return true;
        if(cur_sum == target && cur_num>0)return canPartition(nums, visited, 0, k-1, 0, 0, target);
        for(int i = start_index; i<nums.length; i++){
            if(visited[i] == 0){
                visited[i] = 1;
                if(canPartition(nums, visited, i+1, k, cur_sum + nums[i], cur_num++, target))return true;
                visited[i] = 0;
            }
        }
        return false;
    }
	public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for(int i=0;i<nums.length;i++){
            sum += nums[i];
        }
        if(sum%k!=0){
            return false;
        }
        int target = sum/k;
        // [i,j] in current set
        // (j,n-1] TBD
        return dfs(nums,nums.length-1,k,0,target);
    }
    private boolean dfs(int[] nums, int start, int k, int curSum, int target){
        if(k==1){
            return true;
        }
        for(int i = start;i>=0;i--){
            boolean flag = false;
            swap(nums,i,start);
            int sum = curSum + nums[start];
            if(sum == target){
                return  dfs(nums,start-1,k-1,0,target);
            }else if(sum<target){
                flag =  dfs(nums,start-1,k,sum,target);
            }
            swap(nums,i,start);
            if(flag == true) return flag;
        }
        return false;
    }
    private void swap(int[] nums, int i, int j){
            int tmp = nums[i];
            nums[i]= nums[j];
            nums[j] = tmp;
    }
//	
	
	public List<String> subsetsOfSizeK(String set, int k){ // Time: O(2^n) Space: O(n)
		// DFS - every level represent add or not add a certain letter
		// Check size at base case
		List<String> list = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		subsetsK(set,0,sb,list,k);
		return list;
	}
	private void subsetsK(String set, int level, StringBuilder sb, List<String> list, int k) {
		// Base Case
		if(level == set.length()) {
			if(sb.length()==k) {
				list.add(sb.toString());
				return;
			}
			return;
		}
		sb.append(set.charAt(level));
		subsetsK(set,level+1,sb,list,k);
		sb.setLength(sb.length()-1);
		subsetsK(set,level+1,sb,list,k);
	}
	
	public List<String> subsetsOfSizeK1(String set, int k){
		// DFS - k level recursion tree - every level represent a letter to add
		char[] chars = set.toCharArray();
//		Arrays.sort(chars);
		List<String> list = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		subsetsK(chars,0,sb,list,k);
		return list;
	}
	private void subsetsK(char[] set, int level, StringBuilder sb, List<String> list, int k){
		// Base Case
		if(sb.length() == k) {
			list.add(sb.toString());
			return;
		}
		for(int i=level;i<set.length;i++) {
			sb.append(set[i]);
			subsetsK(set,i+1,sb,list,k);
			sb.setLength(sb.length()-1);
		}
	}
	// Input set may contain duplicate elements
	public List<String> subsetsOfSizeK2(String set, int k){
		// Sort the set first 
		// DFS - size of set level recursion tree - every level represent whether to add or not to add current letter
		// When no-add is selected, skip all repeating elements until next new different elements
		char[] chars = set.toCharArray();
		Arrays.sort(chars);
		List<String> list = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		subsetsKWithDup(chars,0,sb,list,k);
		return list;
	}
	private void subsetsKWithDup(char[] set, int level, StringBuilder sb, List<String> list, int k){
		// Base Case
		if(set.length == level) {
			if(sb.length() == k) {
				list.add(sb.toString());
				return;
			}
			return;
		}
		sb.append(set[level]);
		subsetsKWithDup(set,level+1,sb,list,k);
		sb.setLength(sb.length()-1);
		while(level+1<set.length && set[level]==set[level+1]) {
			level++;
		}
		subsetsKWithDup(set,level+1,sb,list,k);
	}
	
	// Input set may contain duplicate elements
	public List<String> subsetsOfSizeK3(String set, int k){
		// Sort the set first 
		// DFS - k level recursion tree - every level represent a letter to add
		char[] chars = set.toCharArray();
		Arrays.sort(chars);
		List<String> list = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		subsetsKWithDup2(chars,0,sb,list,k);
		return list;
	}
	private void subsetsKWithDup2(char[] set, int level, StringBuilder sb, List<String> list, int k){
		//Base Case
		if(sb.length() == k) {
			list.add(sb.toString());
			return;
		}
		for(int i=level;i<set.length;i++) {
			if(i==level || set[i] != set[i-1]) {
				sb.append(set[i]);
				subsetsKWithDup2(set,i+1,sb,list,k);
				sb.setLength(sb.length()-1);
			}
		}
	}
	
	public List<String> validParenthesesIII(int l, int m, int n){
		StringBuilder sb = new StringBuilder();
		List<String> res = new ArrayList<>();
		parenthesesDFS(new int[] {l,l,m,m,n,n},sb,res);
		return res;
	}
	// left[0] - # of ( left   left[1] - # of ) left
	// left[2] - # of < left   left[3] - # of > left
	// left[4] - # of { left   left[5] - # of } left
	private void parenthesesDFS(int[] left, StringBuilder sb, List<String> list) {
		if(nothingLeft(left)) {
			list.add(sb.toString());
			return;
		}
		if(left[0]>0) {
			sb.append('(');
			left[0]--;
			parenthesesDFS(left,sb,list);
			left[0]++;
			sb.setLength(sb.length()-1);
		}
		if(left[1]>left[0]) {
			sb.append(')');
			left[1]--;
			parenthesesDFS(left,sb,list);
			left[1]++;
			sb.setLength(sb.length()-1);
		}
		if(left[2]>0 && left[0]==left[1]) {
			sb.append('<');
			left[2]--;
			parenthesesDFS(left,sb,list);
			left[2]++;
			sb.setLength(sb.length()-1);
		}
		if(left[3]>left[2] && left[0]==left[1]) {
			sb.append('>');
			left[3]--;
			parenthesesDFS(left,sb,list);
			left[3]++;
			sb.setLength(sb.length()-1);
		}
		if(left[4]>0 && left[0]==left[1] && left[2]==left[3]) {
			sb.append('{');
			left[4]--;
			parenthesesDFS(left,sb,list);
			left[4]++;
			sb.setLength(sb.length()-1);
		}
		if(left[5]>left[4] && left[0]==left[1] && left[2]==left[3]) {
			sb.append('}');
			left[5]--;
			parenthesesDFS(left,sb,list);
			left[5]++;
			sb.setLength(sb.length()-1);
		}
	}
	private boolean nothingLeft(int[] array) {
		for(int i=0;i<array.length;i++) {
			if(array[i] != 0) {
				return false;
			}
		}
		return true;
	}
	
	public List<String> allPermutationsOfSubsets(String set){
		StringBuilder sb = new StringBuilder();
		List<String> res = new ArrayList<>();
		char[] chars = set.toCharArray();
		allPermutationsOfSubsets(chars,sb,res,0);
		return res;
	}
	private void allPermutationsOfSubsets(char[] set, StringBuilder sb, List<String> list, int level) {
		// Base Case
		if(level == set.length) {
			list.add(sb.toString());
			return;
		}
		for(int i=level;i<set.length;i++) {
			swap(set,level,i);
			sb.append(set[level]);
			if(level<set.length-1) {
				list.add(sb.toString());
			}
			allPermutationsOfSubsets(set,sb,list,level+1);
			sb.setLength(sb.length()-1);	
			swap(set,level,i);
		}
	}
	private void swap(char[] array, int i, int j) {
		char temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
