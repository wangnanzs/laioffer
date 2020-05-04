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
	
	private void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public int minDifference(int[] array) {
	    // Write your solution here
	    // C:
	    // A:
	    // R: use DFS
	    //    1. n/2 levels
	    //    2. every level represents the value i put into subset 1
	    // T:
	    int sum = getSum(array,0,array.length-1);
	    int[] minDiff = new int[]{Integer.MAX_VALUE};
//	    dfs(array,0,minDiff,0,sum);
	    dfs1(array,0,minDiff,0,sum,0);
	    return minDiff[0];
	  }
	  private void dfs1(int[] array, int level, int[] minDiff, int subSum, int sum, int size){
		if(size == array.length/2){
	      int diff = Math.abs(sum-subSum-subSum);
	      minDiff[0] = Math.min(minDiff[0],diff);
	      return;
	    }
		if(level == array.length) return;
	    dfs1(array,level+1,minDiff,subSum+array[level],sum, size+1);
	    dfs1(array,level+1,minDiff,subSum,sum,size);
	  }
	  private void dfs(int[] array, int level, int[] minDiff, int subSum, int sum){
	    if(level == array.length/2){
	      int diff = Math.abs(sum-subSum-subSum);
	      minDiff[0] = Math.min(minDiff[0],diff);
	      return;
	    }
	    for(int i=level;i<array.length;i++){
	      swap(array,i,level);
	      dfs(array,level+1,minDiff,subSum+array[level],sum);
	      swap(array,i,level);
	    }
	  }
	  private int getSum(int[] array, int i, int j){
	    int sum = 0;
	    while(i<=j){
	      sum+=array[i++];
	    }
	    return sum;
	  }
	  
	  public int[] keepDistance(int k) {
		    // DFS k levels: at each level i find a position for two (k-i)
		    // maintain a hashtable to indicate if position is taken
		    int res[] = new int[2*k];
		    boolean flag = keepDist(k,0,new boolean[2*k],res);
		    return flag? res:null;
		  }
		  private boolean keepDist(int k, int level, boolean[] taken, int[] res){
		    if(level == k){
		      return true;
		    }
		    int num = k - level;
		    for(int i=0;i<2*k;i++){
		      if(!taken[i] && i+num+1<2*k && !taken[i+num+1]){
		        res[i] = num;
		        res[i+num+1] = num;
		        taken[i] = true;
		        taken[i+num+1] = true;
		        if(keepDist(k,level+1,taken,res)){
		          return true;
		        }
		        taken[i+num+1] = false;
		        taken[i] = false;
		      }
		    }
		    return false;
		  }
		  
		  public int[][] maze(int n) {
			    // Write your solution here.
			    // transform this into a wall-wrecking program...initialize the maze to be all wall except starting point
			    // For each pair of cells on the corridor, there must exist one and only one path between then == can't form a loop
			    
			    // Init
			    int[][] res = new int[n][n];
			    for(int i=0;i<n;i++){
			      for(int j=0;j<n;j++){
			        if(i==0 && j==0){
			          res[i][j] = 0;
			        }else{
			          res[i][j] = 1;
			        }
			      }
			    }
			    //Create an array for direction randomization
			    int[] direction = new int[]{0,1,2,3};
			    generate(res,0,0,direction);
			    return res;
			  }
			  private void generate(int[][]maze, int x,int y, int[] dir){
			    // int rand = Math.random()*4; // 0 = up ; 1 = down; 2: left ; 3=right
			    shuffle(dir);
				for(int move:dir){
			      int nextX = x;
			      int next2X = x;
			      int nextY = y;
			      int next2Y = y;
			      switch(move){
			        case 0: nextY = y+1;
			                next2Y = y+2;
			                break;
			        case 1: nextY = y-1;
			                next2Y = y-2;
			                break;
			        case 2: nextX = x-1;
			                next2X = x-2;
			                break;
			        case 3: nextX = x+1;
			                next2X = x+2;
			                break;
			    } 
			    if(isValidWall(maze,next2X,next2Y)){
			      maze[nextX][nextY] = 0;
			      maze[next2X][next2Y] = 0;
			      generate(maze,next2X,next2Y,dir);
			    }
			    }
			  }
			  private boolean isValidWall(int[][] maze, int x, int y){
			    return x>=0 && x<maze.length && y>=0 && y<maze.length && maze[x][y]==1;
			  }
			  private void shuffle(int[] array){
				    for(int i=0;i<array.length;i++){
				      int rand = (int)(Math.random()*array.length);
				      int temp = array[i];
				      array[i] = array[rand];
				      array[rand] = temp;
				    }
			  }
			  
			  public int numOfSteps(int[] values) {
				    // Write your solution here
				    int i= 0;
				    Map<Integer,Integer> hm = new HashMap<>();
				    for(;i<values.length;i++){
				      if(values[i] == 0){
				        break;
				      }
				    }
				    hm.put(encode(values),0);
				    int[] min = new int[]{Integer.MAX_VALUE};
				    dfs(values,i,0,min,hm);
				    if(min[0] == Integer.MAX_VALUE){
				      return -1;
				    }
				    return min[0];
				  }
				  private void dfs(int[] values, int curr, int move, int[] min, Map<Integer,Integer> hm){
				    if(move > hm.getOrDefault(encode(values),Integer.MAX_VALUE)){
				      return;
				    }
				    if(move > min[0]) {
							return;
						}
						if(isDone(values)){
							min[0] = Math.min(min[0],move);
							return;
				    }
				    hm.put(encode(values),move);
				    for(int i=0;i<4;i++){
				      int next = 0;
				      switch(i){
				        case 0: next = curr-4;break;
				        case 1: next = curr+1;break;
				        case 2: next = curr+4;break;
				        case 3: next = curr-1;break;
				      }
				      if(canMove(values,curr,next)){
				        swap(values,curr,next);
				        dfs(values,next,move+1,min,hm);
				        swap(values,curr,next);
				      }
				    }
				  }
				  // move up -> index - 4
				  // move right -> index + 1
				  // move down -> index + 4
				  // move left -> index - 1 
				  private boolean canMove(int[] values,int curr, int next){
				    if(next<0 || next>=values.length){
				      return false;
				    }
				    if((next == 3 && curr == 4) || (next==4 && curr ==3)){
				      return false;
				    }
				    return values[next]!=next;
				  }
				  private boolean isDone(int[] values){
				    boolean flag = true;
				    for(int i=0;i<values.length;i++){
				      if(i!=values[i]){
				        return false;
				      }
				    }
				    return flag;
				  }
				  private int encode(int[] array){
				    int res = 0;
				    for(int element : array){
				      res = res * 10 + element;
				    }
				    return res;
				  }
				  
				  public List<List<Integer>> factors(int n) {
					    // Write your solution here
					    List<Integer> facts = allFactors(n);
					    int[] count = new int[facts.size()];
					    List<List<Integer>> res = new ArrayList<>();
					    dfs(n,facts,count,res,0);
					    return res;
					  }
					  private void dfs(int n, List<Integer> facts, int[] count, List<List<Integer>> list,int level){
					    if(level == count.length){
					      if(n==1) {
					    	  List<Integer> temp = new ArrayList<>();
						      for(int i=count.length-1;i>=0;i--){
						        int counter = count[i];
						        while(counter-->0){
						          temp.add(facts.get(i));
						        }
						      }
						      list.add(temp);
					      }
					      return;
					    }
					    int factor = facts.get(level);
					    for(int i=0; n%(int)Math.pow(factor,i)==0 && n/(int)Math.pow(factor,i)>=1; i++){
					      count[level] = i;
					      dfs(n/(int)Math.pow(factor,i),facts,count,list,level+1);
					    }
					  }
					  private List<Integer> allFactors(int n){
					    List<Integer> factors = new ArrayList<>();
					    for(int i=n/2;i>1;i--){
					      if(n%i == 0){
					        factors.add(i);
					      }
					    }
					    return factors;
					  }
					  
					  public List<List<Integer>> combinationSum2(int[] num, int target) {
						    // Write your solution here
						    Arrays.sort(num);
						    List<Integer> list = new ArrayList<>();
						    List<List<Integer>> res = new ArrayList<>();
						    dfs(num,0,target,list,res);
						    return res;
						  }
						  private void dfs(int[] num, int index, int target, List<Integer> list, List<List<Integer>> result){
						    if(index == num.length || target<=0){
						      if(target == 0){
						        result.add(new ArrayList<>(list));
						      }
						      return;
						    }
						    for(int i=index;i<num.length;i++){
						      if(target -num[i]>=0){
						        int newTarget = target -num[i];
						        list.add(num[i]);
						        while(i+1<num.length && num[i]==num[i+1]){
						          i++;
						        }
						        dfs(num,i+1,newTarget,list,result);
						        list.remove(list.size()-1);
						      }
						    }
						  }
						  public int coinChange(int amount, int[] coins) {
							    // Write your solution here
							    int[] ways = new int[]{0};
							    Arrays.sort(coins);
							    dfs(amount,0,coins,ways);
							    return ways[0];
							  }
							  private void dfs(int amount, int level, int[] coins, int[] ways){
							    if(level == coins.length){
							      if(amount == 0){
							        ways[0]++;
							      }
							      return;
							    }
							    for(int i=0;i*coins[coins.length-1-level]<=amount;i++){
							      dfs(amount-i*coins[coins.length-1-level],level+1,coins,ways);
							    }
							  }
						
		public List<String> allPossibleIfBlocks(int n){
			List<String> res = new ArrayList<>();
			dfsIfBlocks(n,0,0,res,new StringBuilder());
			return res;
		}
		private void dfsIfBlocks(int n, int l, int r, List<String> list,StringBuilder sb) {
			if(l==n && r == n) {
				list.add(sb.append("\n").toString());
				return;
			}
			if(l<n) {
				int start = sb.length();
				int count = l-r;
				while(count-->0) {
					sb.append("  ");
				}
				sb.append("if{\n");
				dfsIfBlocks(n,l+1,r,list,sb);
				sb.delete(start,sb.length());
			}
			if(l>r) {
				int start = sb.length();
				int count = l-r-1;
				while(count-->0) {
					sb.append("  ");
				}
				sb.append("}\n");
				dfsIfBlocks(n,l,r+1,list,sb);
				sb.delete(start,sb.length());
			}
		}
		  public List<List<Integer>> combinations(int target) {
			    // Write your solution here
			    List<Integer> factors = getFactors(target);
			    List<Integer> holder = new ArrayList<>();
			    List<List<Integer>> res = new ArrayList<>();
			    combDFS(target,factors,0,res,holder);
			    return res;
			  }
			  private void combDFS(int target, List<Integer> factors, int level, List<List<Integer>> list, List<Integer> res){
			    if(level == factors.size()){
			      if(target == 1){
			        list.add(new ArrayList<>(res));
			      }
			      return;
			    }
			    for(int i=0;(int)Math.pow(factors.get(level),i)<=target && target%(int)Math.pow(factors.get(level),i) ==0;i++){
			      int count = i;
			      while(count-->0){
			        res.add(factors.get(level));
			      }
			      int newTarget = (int)(target/(int)Math.pow(factors.get(level),i));
			      combDFS(newTarget,factors,level+1,list,res);
			      count = i;
			      while(count-->0){
			        res.remove(res.size()-1);
			      }
			    }
			  }
			  private List<Integer> getFactors(int target){
			    List<Integer> res = new ArrayList<>();
			    for(int i=target/2;i>=2;i--){
			      if(target % i == 0){
			        res.add(i);
			      }
			    }
			    return res;
			  }
			  public List<String> eventSchedule(String input){
				  List<String> res = new ArrayList<>();
				  eventdfs(input,0,res,new StringBuilder());
				  return res;
			  }
			  private void eventdfs(String input, int level, List<String> list, StringBuilder sb){
					if(level == input.length()){
						list.add(sb.toString());
						return ;
				     	}
					sb.append(input.charAt(level));
					eventdfs(input,level+1,list,sb);
					sb.setLength(sb.length()-1);
					if(sb.length()>0 && sb.charAt(sb.length()-1)!='x'){
						sb.append('x');
						eventdfs(input, level, list, sb);
						sb.setLength(sb.length()-1);
					}
			  }
			  
			  public boolean canBuildLoop(String[] words) {
				  return canBuildLoopDFS(words,1);
			  }
			  private boolean canBuildLoopDFS(String[] words, int level) {
				  if(level == words.length) {
					  return canConnect(words[level-1],words[0]);
				  }
				  for(int i = level;i<words.length;i++) {
					  if(canConnect(words[i-1],words[i])) {
						  swap(words,i,level);
						  if(canBuildLoopDFS(words,level+1)) {
							  return true;
						  }
						  swap(words,i,level);
					  }
				  }
				  return false;
			  }
			  private void swap(String[] array, int i,int j) {
				  String temp = array[i];
				  array[i] = array[j];
				  array[j] = temp;
			  }
			  private boolean canConnect(String a, String b) {
				  return a.charAt(a.length()-1) == b.charAt(0);
			  }
			  
			  
			  public List<String> findWords(char[][] board, String[] words) {
				    // DFS + Trie
				    // First construct a trie with the dictionary
				    // Run DFS from (i,j) if board[i][j] match word.charAt(0)
				    // if average word length is l DFS is O(4^l) total time is O(kmn4^l)
				    TrieNode root = new TrieNode();
				    for(String word:words){
				      insert(root,word);
				    }
				    Set<String> res  = new HashSet<>();
				    for(int i=0;i<board.length;i++){
				      for(int j=0;j<board[0].length;j++){
				        boolean[][] visited = new boolean[board.length][board[0].length];
				        dfsBoard(board,i,j,root,visited,0,res,new StringBuilder());
				      }
				    }
				    return new ArrayList<>(res);
				  }
				  private boolean insert(TrieNode root, String word){
				    // if(search(word)){
				    //   return false;
				    // }
				    for(char c: word.toCharArray()){
				      TrieNode next = root.neighbors[c-'a'];
				      if(next == null){
				        next = new TrieNode();
				        root.neighbors[c-'a'] = next;
				      }
				      //next.count++;
				      root = next;
				    }
				    root.isWord = true;
				    return true;
				  }

				  private void dfsBoard(char[][] board, int x, int y, TrieNode root, boolean[][] visited, int level, Set<String> res, StringBuilder sb){
				    if(!inBound(board,x,y) || visited[x][y] || root.neighbors[board[x][y]-'a']==null){
				      return;
				    }
				    sb.append(board[x][y]);
				    root = root.neighbors[board[x][y]-'a'];
				    if(root.isWord){
				      res.add(sb.toString());
				    }
				    int[][] DIRS = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
				    visited[x][y] = true;
				    
				    for(int[] dir : DIRS){
				      int x1 = x + dir[0];
				      int y1 = y + dir[1];
				      dfsBoard(board,x1,y1,root,visited,level+1,res,sb);
				    }
				    sb.setLength(sb.length()-1);
				    visited[x][y] = false;
				  }
				  private boolean inBound(char[][] board, int x, int y){
				    return x>=0 && x<board.length && y>=0 && y<board[0].length;
				  }

}
class TrieNode{
	  TrieNode[] neighbors;
	  boolean isWord;
	  //int count;
	  public TrieNode(){
		    this.isWord = false;
		    this.neighbors = new TrieNode[26];
		    // this.count = 0;
		  }
}