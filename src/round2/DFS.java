package round2;
import java.util.*;
class DFS {
	
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
