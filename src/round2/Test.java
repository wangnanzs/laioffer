package round2;
import java.util.*;
public class Test {
	public static void main(String[] args) {
		TreeGenerator tg = new TreeGenerator();
		TreeSolution ts = new TreeSolution();
//		Integer[] input = new Integer[] {8,5,13,3,6,9,17,2,4,null,7,null,11,15,null,1,null,null,null,null,null,null,null,14,16};
//		Integer[] input = new Integer[] {5,3,8,1,4,7,9,null,2,null,null,6,null,null,null}; // BST
		Integer[] input = new Integer[] {1,2,3,4,5,6,7}; 
//		Integer[] input = new Integer[] {1,2,3,4,null,null,6,null,5,null,null,null,null,7,null}; 
//		TreeNode root = tg.generate(input);
		TreeNode root = tg.deserialize("1,2,3,#,#,4,5,#,#,#,#");
		
//		int[] array = new int[] {1,2,3,4,5,6,7,8,9,10};
//		BinarySearch bs = new BinarySearch();
//		System.out.println(bs.smallestLargerThan(array, 11));
//		System.out.println(ts.smallestLargerThan(root, 8));
//		System.out.println(ts.getDepth(root, 10));
		
		// === Testing reconstruct ===============
//		int[] in = new int[] {1,3,4,5,8,11};
//		int[] post = new int[] {1,4,3,11,8,5};
//		TreeNode root = ts.reconstruct(in, post);
//		List<Integer> list = ts.inOrderIterative(root);
//		for(Integer i : list) {
//			System.out.print(i+" ");
//		}
//		System.out.println();
		
		// === Testing constructPostOrder =========
//		int[] in = new int[] {4,2,5,1,3,6};
//		int[] pre = new int[] {1,2,4,5,3,6};
//		int[] post = ts.constructPostOrder(in, pre);
//		for(int i : post) {
//			System.out.print(i+" ");
//		}
		
		// === Testing expressionTree =============
//		ExpNode res = ts.expressionTree("a?b?c:d:e?f:g");
//		System.out.println(res.right.right.symbol);
		
		// === Testing shiftPosition =============
//		int res = bs.shiftPosition(new int[] {1,2});
//		System.out.println(res);
		
		// === Testing totalOccurrence ===========
//		int[] array = new int[] {0};
//		int res = bs.totalOccurrence(array, 2);
//		System.out.println(res);
		
		// === Testing kthSum ====================
//		BFS bfs = new BFS();
//		int[] A = new int[] {1,2,3,4,5,6};
//		int[] B = new int[] {1,2,3,4,5,6};
//		for(int i = 1;i<12;i++) {
//			System.out.println(bfs.kthSum(A, B, i));
//		}
		
		// === Testing subsetsOfSizeK ============
//		DFS dfs = new DFS();
//		List<String> res = dfs.subsetsOfSizeK("abcde",3);
//		List<String> res1 = dfs.subsetsOfSizeK1("abcde",3);
//		for(int i=0;i<res.size();i++) {
//			System.out.print(res.get(i)+"  "+res1.get(i)+"\n");
//		}
		
		// === Testing subsetsOfSizeK2 ============
//		DFS dfs = new DFS();
//		List<String> res = dfs.subsetsOfSizeK2("abbbbceee",3);
//		List<String> res1 = dfs.subsetsOfSizeK3("abbbbceee",3);
//		for(int i=0;i<res.size();i++) {
//			System.out.print(res.get(i)+"  "+res1.get(i)+"\n");
//		}
		
		// === Testing validParenthesesIII ========
//		DFS dfs = new DFS();
//		List<String> res = dfs.validParenthesesIII(2,0,1);
//		for(int i=0;i<res.size();i++) {
//			System.out.print(res.get(i)+"\n");
//		}
		
		// === Testing allPermutationsOfSubsets ===
//		DFS dfs = new DFS();
//		List<String> res = dfs.allPermutationsOfSubsets("abcdf");
//		for(int i=0;i<res.size();i++) {
//			System.out.print(res.get(i)+"\n");
//		}
		
		// === Testing canPartitionKSubsets ===
//		DFS dfs = new DFS();
////		int[] nums = new int[] {129,17,74,57,1421,99,92,285,1276,218,1588,215,369,117,153,22};
//		int[] nums = new int[] {292,452,106,668,2879,3168,265,288,26,18,437,3051,4109,306,323,1624};
//		
//		long startTime1 = System.nanoTime();
//		boolean res1 = dfs.canPartitionKSubsets1(nums,4);
//		long endTime1 = System.nanoTime();
//		long timeElapsed1 = endTime1 - startTime1;
//		System.out.println(res1);
//		System.out.println(timeElapsed1/1000);
//
//		long startTime = System.nanoTime();
//		Arrays.sort(nums);
//		boolean res = dfs.canPartitionKSubsets(nums,4);
//		long endTime = System.nanoTime();
//		long timeElapsed = endTime - startTime;
//		System.out.println(res);
//		System.out.println(timeElapsed/1000);
		
		// === Testing solveSudoku ===
//		DFS dfs = new DFS();
//		char[][] board = new char[][] {
//			{'5','3','.','.','7','.','.','.','.'},
//			{'6','.','.','1','9','5','.','.','.'},
//			{'.','9','8','.','.','.','.','6','.'},
//			{'8','.','.','.','6','.','.','.','3'},
//			{'4','.','.','8','.','3','.','.','1'},
//			{'7','.','.','.','2','.','.','.','6'},
//			{'.','6','.','.','.','.','2','8','.'},
//			{'.','.','.','4','1','9','.','.','5'},
//			{'.','.','.','.','8','.','.','7','9'}};
//		dfs.solveSudoku(board);
//		for(int i = 0;i<9;i++) {
//			for(int j =0;j<9;j++) {
//				System.out.print(board[i][j]);
//				if(j==8) {
//					System.out.println();
//				}
//			}
//		}
		
		// === Testing maxProfit ===
//		DP dp = new DP();
////		int[] array = new int[] {3,4,1,2,6,2,3,5,1,7,3,8};
//		int[] array = new int[] {1,2,3,4,5,6,7,8};
//		int res = dp.maxProfit(array);
//		System.out.println(res);
		
		// === Testing maxProfit ===
//		DP dp = new DP();
//		int[] array = new int[] {5,1,2,3,7,2,5,1,3};
//		int res = dp.maxProfit(array,1);
//		System.out.println(res);
		
		// === Testing minCost ===
//		DP dp = new DP();
//		int[] array = new int[] {4,3,3,4};
//		int res = dp.minCost(array);
//		System.out.println(res);
		
		// === Testing largestProduct ===
//		DP dp = new DP();
//		double[] array = new double[] {0.4,0.2,0.1};
//		double res = dp.largestProduct(array);
//		System.out.println(res);
		
		// === Testing findMedianSortedArrays  ===
//		BinarySearch bs = new BinarySearch();
//		int[] nums1 = new int[] {1};
//		int[] nums2 = new int[] {2,3,4,5,6};
//		double res = bs.findMedianSortedArrays(nums1, nums2);
//		System.out.println(res);
		
		// === Testing sqrt  ===
//		BinarySearch bs = new BinarySearch();
//		int res = bs.sqrt(2147395600);
//		System.out.println(res);
		
		// === Testing recover  ===
//		Integer[] input = new Integer[] {4,2,6,1,5,3,7};
//		TreeNode root = tg.generate(input);
//		ts.morrisTraverse(root);
//		ts.recover1(root);
//		ts.morrisTraverse(root);
		
		// === Testing smallerPairs  ===
//		BinarySearch bs = new BinarySearch();
//		int[] array = new int[] {3,4,0,-1,2,0,5};
//		int res = bs.smallerPairs(array, 10);
//		System.out.println(res);
		
		// === Testing countAndSay  ===
//		StringSolution ss = new StringSolution();
//		String res = ss.countAndSay(17);
//		System.out.println(res);
		
		// === Testing canMerge  ===
//		StringSolution ss = new StringSolution();
//		boolean res = ss.canMerge("ab", "ac", "abca");
//		System.out.println(res);
		
		// === Testing reverseBits  ===
//		BitOpSolution bos = new BitOpSolution();
//		long res = bos.reverseBits(255);
//		System.out.println(res);
		
		// === Testing postOrder  ===
//		BinarySearch bs = new BinarySearch();
//		List<Integer> res = bs.postOrder(root);
//		System.out.println(res);
		
		// === Testing strstr  ===
//		StringSolution ss = new StringSolution();
//		int res = ss.strstr("mnopqrstuvwxyzzabcdefghijklmnopqrstu", "qrstuvwxyzzabcdefghijklmnopqrstu");
//		System.out.println(res);
		
		// === Testing replace  ===
//		StringSolution ss = new StringSolution();
//		String res = ss.replace("burundiitalybruneibulgariachinalebanon", "brunei", "georgia");
//		System.out.println(res);
		
		// === allAnagrams  ===
//		StringSolution ss = new StringSolution();
//		List<Integer> res = ss.allAnagrams("aa", "baaaa");
//		System.out.println(res);
		
		// === match  ===
//		StringSolution ss = new StringSolution();
//		boolean res = ss.match("soood", "s111d");
//		System.out.println(res);
		
		// === Testing closestKValues  ===
//		int[] res = ts.closestKValues(root, 0.0, 1);
//		System.out.println(res);
		
		// === Testing countArray  ===
//		ArraySolution as = new ArraySolution();
//		int[] res = as.countArray(new int[] {4,1,3,2});
//		for(int i:res) {
//			System.out.print(i+" ");
//		}
//		System.out.println();
		
		// === Testing minDifference  ===
//		DFS dfs = new DFS();
//		long startTime1 = System.nanoTime();
//		int res = dfs.minDifference(new int[] {1,4,2,3,5,4,46,6,7,7,8,6,4,6,3,1});
//		long endTime1 = System.nanoTime();
//		long timeElapsed1 = endTime1 - startTime1;
//		System.out.println(res);
//		System.out.print(timeElapsed1/1000);
//		System.out.println("us");
		
		// === Testing keepDistance  ===
//		DFS dfs = new DFS();
//		long startTime1 = System.nanoTime();
//		int[] res = dfs.keepDistance(13);
//		long endTime1 = System.nanoTime();
//		long timeElapsed1 = endTime1 - startTime1;
//		if(res==null) {
//			System.out.println(res);
//		}else {
//			for(int i:res) {
//				System.out.print(i+" ");
//			}
//			System.out.println();
//		}
//		System.out.print(timeElapsed1/1000);
//		System.out.println("us");
		
		// === Testing maze  ===
//		DFS dfs = new DFS();
//		long startTime1 = System.nanoTime();
//		int[][] res = dfs.maze(5);
//		long endTime1 = System.nanoTime();
//		long timeElapsed1 = endTime1 - startTime1;
//		if(res==null) {
//			System.out.println(res);
//		}else {
//			print2DArray(res);
//		}
//		System.out.print(timeElapsed1/1000);
//		System.out.println("us");
		// === numOfSteps  ===
//		BFS bfs = new BFS();
//		long startTime1 = System.nanoTime();
//		int res = bfs.numOfSteps(new int[] {4,1,2,3,5,0,6,7});
//		long endTime1 = System.nanoTime();
//		long timeElapsed1 = endTime1 - startTime1;
//		System.out.println(res);
//		System.out.print(timeElapsed1/1000);
		
		// === Testing ladderLength  ===
//		BFS bfs = new BFS();
//		long startTime1 = System.nanoTime();
//		List<String> wordList = new ArrayList<>();
//		wordList.add("git");
//		wordList.add("hit");
//		wordList.add("hog");
//		wordList.add("hot");
//		int res = bfs.ladderLength("git", "hot", wordList);
//		long endTime1 = System.nanoTime();
//		long timeElapsed1 = endTime1 - startTime1;
//		System.out.println(res);
//		System.out.print(timeElapsed1/1000);
		
		// === Testing findLadders  ===
//		BFS bfs = new BFS();
//		long startTime1 = System.nanoTime();
//		List<String> wordList = new ArrayList<>();
//		wordList.add("git");
//		wordList.add("hit");
//		wordList.add("hog");
//		wordList.add("hot");
//		wordList.add("got");
//		List<List<String>> res = bfs.findLadders("coder","goner",new ArrayList<>(Arrays. asList("lover","coder","comer","toner","cover","tower","coyer","bower","honer","poles","hover","lower","homer","boyer","goner","loner","boner","cower","never","sower","asian")));
//		long endTime1 = System.nanoTime();
//		long timeElapsed1 = endTime1 - startTime1;
//		System.out.println(res);
//		System.out.println(res.size());
//		System.out.print(timeElapsed1/1000);
		
		// === Testing largestSquareSurroundedByOne   ===
//		DP dp = new DP();
//		int res = dp.largestSquareSurroundedByOne(new int[][] {{0,0},{0,1}});
//		System.out.println(res);
		
		// === Testing findOrder   ===
//		BFS bfs = new BFS();
//		int[] res = bfs.findOrder(4, new int[][] {{1,0},{2,0},{3,1},{3,2}});
//		printArray(res);
		
		// === Testing alienOrder   ===
//		BFS bfs = new BFS();
//		String res = bfs.alienOrder(new String[] {"z","x"});
//		System.out.println(res);
		
		// === Testing reverseWords   ===
//		StringSolution ss = new StringSolution();
//		String res = ss.reverseWords(" a b c d ");
//		System.out.println(res);
		
		// === Testing canWin   ===
//		DP dp = new DP();
//		int res = dp.canWin(new int[] {601,49373,38681,14134,577,28610,57699,258,19236,88206,490,202,73112,526,39634,811,1032,28458,462});
//		System.out.println(res);
		
		// === Testing longest   ===
//		DP dp = new DP();
//		int[] res = dp.longest(new int[] {5, 2, 6, 3, 4, 7, 5});
//		printArray(res);
		
		// === Testing longestPalindrome   ===
//		DP dp = new DP();
//		long startTime1 = System.nanoTime();
//		String res = dp.longestPalindrome("abcbcbddljkalsfjkladsjfklasjfklasdjglkasdjgkldsajgklsadgjlkag");
//		long endTime1 = System.nanoTime();
//		long timeElapsed1 = endTime1 - startTime1;
//		System.out.println(res);
//		System.out.println(timeElapsed1/1000);
//		
//		StringSolution ss = new StringSolution();
//		startTime1 = System.nanoTime();
//		String res1 = ss.longestPalindrome("abcbcbddljkalsfjkladsjfklasjfklasdjglkasdjgkldsajgklsadgjlkag");
//		endTime1 = System.nanoTime();
//		timeElapsed1 = endTime1 - startTime1;
//		System.out.println(res1);
//		System.out.println(timeElapsed1/1000);
		
		// === Testing rainbowSortII   ===
//		ArraySolution as = new ArraySolution();
//		int[] res = as.rainbowSortII(new int[] {1,1,1,0,0,0,0});
//		printArray(res);
		
		// === Testing compress   ===
//		StringSolution ss = new StringSolution();
//		String res = ss.compress("lnxwxafklddbdbfrqlqwoknpwmhzewhhhucvloukabsfsgzwpvagyrjxrjkwzzknbvoymulkjqwxmdxqlajfnrzxwznhuharmfonbjeloajowdbihwupgvibqqxsqsmcdephqbmaqttevrebjoqaszmjxojsl");
//		System.out.println(res);
		
		// === Testing largest   ===
//		DP dp = new DP();
//		int res = dp.largest(new int[][] {{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1}});
//		System.out.println(res);
		
		// === Testing largest   === double largest(double[][] matrix)
//		DP dp = new DP();
////		double res = dp.largest(new double[][] {{2,-1,0.5,1,-3},{0,-2,-1,2,0.1},{3,0.2,1,-3,-2}});
//		double res = dp.largest(new double[][] {{0,0,0},{0,0,0},{0,0,0}});
//		System.out.println(res);
		
		// === Testing topView   ===
//		TreeSolution ts = new TreeSolution();
//		List<Integer> res = ts.topView(root);
//		System.out.println(res);
		
		// === Testing leftView   ===
//		List<Integer> res = ts.leftView(root);
//		System.out.println(res);
		
		// === Testing factors   ===
//		DFS dfs = new DFS();
//		List<List<Integer>> res = dfs.factors(12);
//		System.out.println(res);

		// === Testing combinationSum2   ===
//		DFS dfs = new DFS();
//		List<List<Integer>> res = dfs.combinationSum2(new int[] {10,1,2,7,6,1,5}, 8);
//		System.out.println(res);
		
		// === Testing canWin   ===
//		DP dp = new DP();
//		boolean res = dp.canWin(new int[] {55,77,73,72,9,86,35,25,53,73,100,88,89});
//		System.out.println(res);
		
		// === Testing coinChange === 
//		DFS dfs = new DFS();
//		int res = dfs.coinChange(500,new int[] {11,24,37,50,63,76,89,102});
//		System.out.println(res);
		
		// === Testing allPossibleIfBlocks === 
//		DFS dfs = new DFS();
//		List<String> res = dfs.allPossibleIfBlocks(6);
//		for(String str: res) {
//			System.out.print(str);
//		}
		
		// === Testing  isValidSerialization1 === 
//		boolean res = ts.isValidSerialization("9,#,92,#,#");
//		System.out.println(res);
		
		// === Testing  generateBSTs === 
//		List<TreeNode> res = ts.generateBSTs(3);
//		for(TreeNode root1:res) {
//			ts.preOrder(root1);
//			System.out.println("========");
//		}
		// === Testing  threeSumSmaller === 
//		ArraySolution as = new ArraySolution();
//		int res = as.threeSumSmaller(new int[] {1,-1,2,8,10,4,7,3}, 14);
//		System.out.println(res);
		// === Testing  diff === 
//		ArraySolution as = new ArraySolution();
//		int[][] res = as.diff(new int[]{856,858,860,861,862,863,864,865,866,869,870,871,872,876,877,879,880,882,883,885,886,888,889,890,892,894,895,896,897,903,906,907,909,910,912,913,914,915,918,920,921,922,923,924,925,927,928,930,931,932,934,935,937,938,939,940,942,944,947,950,951,952,953,955,957,958,959,960,961,963,964,966,967,968,969,970,972,974,976,978,979,981,982}, new int[]{858,860,861,862,863,864,865,866,868,870,871,872,873,874,875,876,878,880,882,884,885,886,887,888,889,892,893,894,895,898,899,900,902,903,904,905,906,911,912,914,916,918,920,921,923,924,925,927,928,929,931,932,933,934,936,937,939,940,945,946,947,951,953,954,955,956,958,959,960,964,965,967,968,972,973,976,977,978,979,981,982,985,986,988,989});
//		print2DArray(res);
		
		// === Testing  combinations ===
//		DFS dfs = new DFS();
//		List<List<Integer>> res = dfs.combinations(24);
//		System.out.println(res);
		
		// === Testing  deleteSingleChildNode ===
//		TreeNode res = ts.deleteSingleChildNode(root);
//		ts.preOrder(res);
		
		// === Testing  eventSchedule ===
//		DFS dfs = new DFS();
//		List<String> res = dfs.eventSchedule("ABC");
//		System.out.println(res);
		
		// === minimumBox ===
//		DP dp = new DP();
//		int res = dp.minimumBox(156);
//		System.out.println(res);
		
		// === Testing  isCousins ===
//		BFS bfs = new BFS();
//		TreeNode a = ts.findNode(root, 4);
//		TreeNode b = ts.findNode(root, 1);
//		boolean res = bfs.isCousins(root, a, b);
//		System.out.println(res);
//		boolean res1 = ts.isCousins(root, a, b);
//		System.out.println(res1);
		
		// === Testing  canBuildLoop ===
//		DFS dfs = new DFS();
//		boolean res = dfs.canBuildLoop(new String[] {"alice","eric","chris","selina"});
//		System.out.println(res);
		
		// === Testing  findWords ===
		DFS dfs = new DFS();
		List<String> res = dfs.findWords(new char[][]{{'a'}}, new String[]{"a"});
		System.out.println(res);
		
		ts.preOrder(root);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public static void printArray(int[] array) {
		for(int i:array) {
			System.out.print(i+" ");
		}
		System.out.println();
	}
	public static void print2DArray(int[][] array) {
		for(int i=0;i<array.length;i++) {
			for(int j=0;j<array[0].length;j++) {
				System.out.print(array[i][j]+" ");
			}
			System.out.println();
		}
	}
}
