package laioffer;

import java.util.*;




public class DFS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<List<Integer>> result = DFSSolution.combinations(99, new int[] {25,10,5,1});
//		Collections.sort(result);
		for(List<Integer> ls : result) {
			System.out.println(ls);
		}
		
		// Understanding Arrays.asList() method 
		System.out.println(result.size());
		Integer[] test = new Integer[] {1,2,3,4};
		List<Integer> list = Arrays.asList(test);
		System.out.println(test.hashCode());
		System.out.println(list.hashCode());
		System.out.println(list);
		test[3] =5;
		test[2] =5;
		test[1] =5;
		test[0] =5;
		System.out.println(list);
	}

}
class DFSSolution{
	public static List<String> subSets(String set) {
		List<String> ls = new ArrayList<>();
		char[] chars = set.toCharArray();
		StringBuilder sb = new StringBuilder();
		subSets(chars,0,sb,ls);
		return ls;
	}
	private static void subSets(char[] set, int level, StringBuilder sb, List<String> ls) {
		if(level == set.length) {
			ls.add(sb.toString());
			return;
		}
		sb = sb.append(set[level]);
		subSets(set,level+1,sb,ls);
		sb = sb.deleteCharAt(sb.length()-1);
		subSets(set,level+1,sb,ls);
	}
	
	
	public static List<String> validParentheses(int n) {
		List<String> ls = new ArrayList<>();
		char[] sol = new char[2*n];
		helper(n,0,0,sol,ls);
		return ls;
	}
	private static void helper(int n,int l,int r, char[] sol, List<String> ls) {
		if(l==n && r==n) {
			ls.add(new String(sol));
			return;
		}
		if(l<n) {
			sol[l+r] = '(';
			helper(n,l+1,r,sol,ls);
		}
		if(l>r) {
			sol[l+r] = ')';
			helper(n,l,r+1,sol,ls);
		}
	}
	
	public static List<List<Integer>> combinations(int target, int[] coins){
		// Assume coins is not null or empty, target >=0
		List<List<Integer>> ls = new ArrayList<>();
//		int[] sol = new int[coins.length];
		Integer[] sol = new Integer[coins.length];
		helper(target,coins,0,sol,ls);
		return ls;
	}
	private static void helper(int target, int[] coins, int level, Integer[] sol, List<List<Integer>> ls) {
		if(level == coins.length) {
			if(target == 0) {
//				ls.add(convertIntArrayToList(sol));
				ls.add(new ArrayList<Integer>(Arrays.asList(sol)));
			}
			return;
		}
		for(int i=0;i*coins[level]<=target;i++) {
			sol[level] = i;
			helper(target-i*coins[level],coins,level+1,sol,ls);
		}
	}
//	private static List<Integer> convertIntArrayToList(int[] array){
//		List<Integer> result = new ArrayList<>();
//		for(int element : array) {
//			result.add(element);
//		}
//		return result;
//	}
//	
	public static List<String> permutations(String set) {
		List<String> ls = new ArrayList<>();
		if(set == null || set.length()==0) {
			return ls;
		}
//		boolean[] used = new boolean[set.length()];
//		char[] sol = new char[set.length()];
//		helper1(set.toCharArray(),0,used,sol,ls);
		helper2(set.toCharArray(),0,ls);
		return ls;
	}
//	private static void helper1(char[] set,int level, boolean[] used, char[] sol, List<String> ls ) {
//		if(level == set.length) {
//			ls.add(new String(sol));
//			return;
//		}
//		for(int i=0;i<set.length;i++) {
//			if(!used[i]) {
//				sol[level] = set[i];
//				used[i] = true;
//				helper1(set,level+1,used,sol,ls);
//				used[i] = false;
//			}
//		}
//	}
	private static void helper2(char[] set, int level, List<String> ls) {
		if(level == set.length) {
			ls.add(new String(set));
			return;
		}
		for(int i=level;i<set.length;i++) {
			swap(set,level,i);
			helper2(set,level+1,ls);
			swap(set,level,i);
		}
	}
	private static void swap(char[] set, int i, int j) {
		char temp = set[i];
		set[i] = set[j];
		set[j] = temp;
	}
	
}