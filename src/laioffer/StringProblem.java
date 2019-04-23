package laioffer;

import java.util.*;
import java.util.List;

public class StringProblem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringSolution sol = new StringSolution();
//		System.out.println(sol.strstr("abba","ba"));
//		int[] array = new int[] {1,2,3,4,5,6};
//		int[] result = sol.reorder(array);
//		for(int i : result) {
//			System.out.println(i);
//		}
		List<String> result = sol.permutations("baa");
		for(String s : result) {
			System.out.println(s);
		}
		System.out.println(sol.decompress("a1c0b3c4"));
		System.out.println(sol.longest("abcdebcd"));
		System.out.println(Integer.valueOf("11"));
		MyHashMap<String,Integer> mhm = new MyHashMap<>();
		mhm.put("Tiger", 1);
		mhm.put("Lion", 2);
		System.out.println(mhm.get("Tiger"));
		mhm.showBucketSize();
		mhm.put("Leopard", 3);
		mhm.showBucketSize();
		System.out.println(mhm.get("Tiger"));
	}

}
class StringSolution{
	public int longest(String input) {
		int global_max = 0;
		Set<Character> hs = new HashSet<>();
		int i = 0;
		for(int j = 0;j<input.length();) {
			if(hs.contains(input.charAt(j))) {
				hs.remove(input.charAt(i));
				i++;
			}else {
				hs.add(input.charAt(j));
				j++;
				global_max = Math.max(global_max, j-i);
			}
		}
		return global_max;
	}
	
	public String decompress(String input) {
		// Count how many characters in total that should be in the decompressoed string
		// Case1 
		// Case2 
		//int count = countTotal(input);
		StringBuilder sb = new StringBuilder();
		for(int j =0;j<input.length();j++) {
			char curr = input.charAt(j);
			if(curr > '0' && curr <='9') {
				char letter = input.charAt(j-1);
				for( int k = 0;k<curr-'0';k++) {
					sb.append(letter);
				}
			}
		}
		return sb.toString();
	}

//	private int countTotal(String input) {
//		int count = 0;
//		for(int i=0;i<input.length();i++) {
//			char c = input.charAt(i);
//			if(c >='0' && c <='9') {
//				count += c -'0';
//			}
//		}
//		return count;
//	}
	public List<String> permutations(String set) { // Consider duplicate characters
		List<String> res = new ArrayList<>();
		if(set == null) {
			return res;
		}
		if(set.isEmpty()) {
			res.add("");
			return res;
		}
		char[] array = set.toCharArray();
		helper(array,0,res);
		return res;
	}
	private void helper(char[] set, int level, List<String> ls) {
		if(level == set.length) {
			ls.add(new String(set));
			return;
		}
		Map<Character,Boolean> hm = new HashMap<>();
		for(int i = level; i<set.length;i++) {
			if(hm.get(set[i]) == null || !hm.get(set[i])) {
				swap(set,level,i);
				hm.put(set[level],true);
				helper(set,level+1,ls);
				swap(set,level,i);
			}
		}
		
	}
	private void swap(char[] set, int i, int j) {
		char tmp = set[j];
		set[j] = set[i];
		set[i] = tmp;
	}
	
	public int[] reorder(int[] array) { // Do it in place
		if(array == null || array.length == 0) {
			return array;
		}
		if(array.length%2 == 0) {
			convert(array,0,array.length-1);
		}else {
			convert(array,0,array.length-2);
		}
		return array;
	}
	private void convert(int[] array, int start, int end) {
		if(end - start <= 1) {
			return;
		}
		int n = end - start + 1;
		int m = start + n/2 - 1;
		int lm = start + n/4 - 1;
		int rm = m + 1 + n/4 - 1;
		reverse(array,lm+1,m);
		reverse(array,m+1,rm);
		reverse(array,lm+1,rm);
		convert(array,start,start+2*(lm-start+1)-1);
		convert(array,start+2*(lm-start+1),end);
	}
	private void reverse(int[] array, int start, int end) {
		int i = start;
		int j = end;
		while(i<j) {
			int tmp = array[j];
			array[j] = array[i];
			array[i] = tmp;
			i++;
			j--;
		}
	}
	public String replace(String input, String source, String target) {
		StringBuilder inputSet = new StringBuilder(input);
		StringBuilder srcSet = new StringBuilder(source);
		StringBuilder tarSet = new StringBuilder(target);
		if(srcSet.length() >= tarSet.length()) {
			int i = 0;
			for(int j=0;j<inputSet.length();) {
				if(isMatch(inputSet,j,srcSet)) {
					j += srcSet.length();
					for(int k=0;k<tarSet.length();k++) {
						inputSet.setCharAt(i++, tarSet.charAt(k));
					}
				}else {
					inputSet.setCharAt(i,inputSet.charAt(j));
					i++;
					j++;
				}
			}
			inputSet.delete(i, inputSet.length());
			return inputSet.toString();
		}else {
			inputSet = inputSet.reverse();
			srcSet = srcSet.reverse();
			tarSet = tarSet.reverse();
			int count = 0;
			for(int j=0;j<inputSet.length();) {
				if(isMatch(inputSet,j,srcSet)) {
					count++;
					j += srcSet.length();
				}else {
					j++;
				}
			}
			int addition = count*(tarSet.length()-srcSet.length());
			for(int j=0;j<addition;j++) {
				inputSet.append(' ');
			}
			int i=inputSet.length()-1;
			for(int j=inputSet.length()-1-addition;j>=0;) {
				if(isMatch(inputSet,j-srcSet.length()+1,srcSet)) {
					j -= srcSet.length();
					for(int k=tarSet.length()-1;k>=0;k--) {
						inputSet.setCharAt(i--, tarSet.charAt(k));
					}
				}else {
					inputSet.setCharAt(i,inputSet.charAt(j));
					i--;
					j--;
				}
			}
			return inputSet.reverse().toString();
		}
		
	}
	private boolean isMatch(StringBuilder input, int index, StringBuilder pattern) {
		if(index > input.length() - pattern.length() || index < 0) {
			return false;
		}
		for(int i = 0;i<pattern.length();i++) {
			if(input.charAt(i+index) != pattern.charAt(i)) {
				return false;
			}
		}
		return true;
	}
	
	public String removePattern(String input, String t) {
	    // Write your solution here
	    char[] text = input.toCharArray();
	    char[] pattern = t.toCharArray();
	    int i=0;
	    int j=0;
	    for(;j<text.length;){
	      boolean flag = true;
	      for(int k = 0; k<pattern.length;k++){
	        if(text[j+k] != pattern[k]){
	          flag = false;
	          break;
	        }
	      }
	      if(flag == true){
	        j += pattern.length;
	      }else{
	        text[i] = text[j];
	        i++;
	        j++;
	      }
	    }
	    StringBuilder result = new StringBuilder();
	    for(int idx = 0;idx<i;idx++){
	      result.append(text[idx]);
	    }
	    return result.toString();
	}
	public int strstr(String large, String small) {
	    // Write your solution here
	    if(large == null || small == null || large.isEmpty()){
	      return -1;
	    }
	    if(small.isEmpty()){
	      return 0;
	    }
	    char[] largeSet = large.toCharArray();
	    char[] smallSet = small.toCharArray();
	    int smallPrime = 31;
	    int largePrime = 101;
	    int hashPat = hash(smallSet,0,smallSet.length,smallPrime,largePrime);
	    int rolling = hash(largeSet,0,smallSet.length,smallPrime,largePrime);
	    int seed = 1;
	    for(int i=1;i<smallSet.length;i++) {
	    	seed *= smallPrime;
	    }
	    for(int i = 0;i<largeSet.length-smallSet.length+1;i++){
	      if(i>0){
	        rolling = ((rolling - (largeSet[i-1])*seed)*smallPrime%largePrime+largeSet[i+smallSet.length-1])%largePrime;
	      }
	      if(rolling == hashPat){
	        if(isMatch(largeSet,i,smallSet.length,smallSet,0,smallSet.length)){
	          return i;
	        }
	      }
	    }
	    return -1;
	  }
	  private int hash(char[] array, int start, int length, int smallPrime, int largePrime){
	    int result = 0;
	    for(int i = start;i<start+length;i++){
	      result = (result*smallPrime%largePrime+array[i]) % largePrime;
	    }
	    return result;
	  }
	  private boolean isMatch(char[] one, int start1, int length1, char[] two, int start2, int length2){
	    for(int i = 0;i<length2;i++){
	      if(one[start1+i] != two[start2+i]){
	        return false;
	      }
	    }
	    return true;
	  }
}