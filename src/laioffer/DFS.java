package laioffer;

import java.util.List;
import java.util.ArrayList;

public class DFS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> result = DFSSolution.validParentheses(3);
		for(String s : result) {
			System.out.println(s);
		}
//		System.out.println(result.size());
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
}