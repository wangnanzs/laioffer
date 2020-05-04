package round2;
import java.util.*;
class StringSolution {
	public String countAndSay(int n) {
		if(n<1) {
			return null;
		}
		if(n==1) {
			return "1";
		}
		StringBuilder res = new StringBuilder("1");
		int seq = 1;
		while(seq++ < n) {
			StringBuilder tmp = new StringBuilder();
			for(int i=0;i<res.length();i++) {
				int count=1;
				while(i+1<res.length() && res.charAt(i+1)==res.charAt(i)) {
					count++;
					i++;
				}
				tmp.append(count);
				tmp.append(res.charAt(i));
			}
			res = tmp;
		}
		return res.toString();
	}
	
	public boolean canMerge(String a, String b, String c) {
		return canMergeDFS(c,0,a,0,b,0);
	}
	private boolean canMergeDFS(String c, int index, String a, int aleft, String b, int bleft) {
		if(index == c.length()) {
			return true;
		}
		if((aleft < a.length() && c.charAt(index)!=a.charAt(aleft))  && (bleft < b.length() && c.charAt(index)!= b.charAt(bleft))) {
			return false;
		}
		if(aleft < a.length() && c.charAt(index)==a.charAt(aleft)) {
			if(canMergeDFS(c,index+1,a,aleft+1,b,bleft)) {
				return true;
			}
		}
		if(bleft < b.length() && c.charAt(index)==b.charAt(bleft)) {
			if(canMergeDFS(c,index+1,a,aleft,b,bleft+1)) {
				return true;
			}
		}
		return false;
	}
	
	public int strstr(String large, String small) {
	    // C: 1. if both are null?   2. if small string is empty 3. are they all lower case?
	    // A: 1. assume non-existing 2. return 0  3. assume they are all lower case
	    // R: Rabin-Karp
	    // T: 
	    // Write your solution here
	    // assume large length is M, and small length is N
	    // O(MN)
	      long smallHash = rabinKarp(small,0,small.length()-1);
	      long hash = rabinKarp(large,0,small.length()-1);
//	      long multi = (long)Math.pow(26,small.length()-1);
	      long mod = (long)Math.pow(26,small.length()-1);
	      for(int i=0;i<large.length()-small.length()+1;i++){
	        if(i!=0){
//	          hash = hash-multi*(large.charAt(i-1)-'a')+large.charAt(i+small.length()-1)-'a';
	          hash = (hash%mod)*26+large.charAt(i+small.length()-1)-'a';
	        }
	        if(hash == smallHash){
	          return i;
	        }
	      }
	      return -1;
	 }
	 public long rabinKarp(String str,int left, int right){  // O(N)
	      long res = 0;
//	      long largePrime = 100000001;
	      for(int i=left;i<=right;i++){
	        res = (26*res)+str.charAt(i)-'a';
	      }
	      return res;
	 }
	 
	 public String replace(String input, String source, String target) {
		    // Write your solution here
		    int len1 = source.length();
		    int len2 = target.length();
		    char[] array = input.toCharArray();
		    int i=0;
		    int j=0;
		    if(len1>=len2){
		      //[0,i) to keep
		      //[i,j] discard
		      //(j,n-1] to be decided
		      for(j=0;j<input.length();){
		        if(!match(input,j,j+source.length()-1,source)){
		          array[i] = array[j];
		          i++;
		          j++;
		        }else{
		          copy(array,i,target);
		          i += target.length();
		          j += source.length();
		        }
		      }
		      return new String(array,0,i);
		    }
		    else{
		      int occur = numOfOccurance(input,source);
		      char[] temp = new char[array.length+occur*(target.length()-source.length())];
		      for(int k = 0;k<array.length;k++){
		        temp[k] = array[k];
		      }
		      array = temp;
		      //(i,n-1] to keep
		      //[j,i] discard
		      //[0,j) to be decided
		      i = array.length-1;
		      for(j=input.length()-1;j>=0;){
		        if(!match(input,j-source.length()+1,j,source)){
		          array[i] = array[j];
		          i--;
		          j--;
		        }else{
		          copy(array,i-target.length()+1,target);
		          i -= target.length();
		          j -= source.length();
		        }
		      }
		      return new String(array);
		    }
		    
		  }
		  private int numOfOccurance(String input, String pattern){
		    int count = 0;
		    for(int i = 0;i<input.length()-pattern.length()+1;){
		      if(match(input,i,i+pattern.length()-1,pattern)){
		        count++;
		        i += pattern.length();
		      }else{
		        i++;
		      }
		    }
		    return count;
		  }
		  private void copy(char[] array, int index, String target){
		    for(int i=0;i<target.length();i++){
		      array[index+i] = target.charAt(i);
		    }
		  }
		  private boolean match(String input, int start, int end, String pattern){
			
			  if(start<0 || end >= input.length() || pattern.length()>end-start+1){
		      return false;
		    }
		    for(int i=0;i<=pattern.length()-1;i++){
		      if(input.charAt(start+i)!=pattern.charAt(i)){
		        return false;
		      }
		    }
		    return true;
		  }
		  
		  public List<Integer> allAnagrams(String sh, String lo) {
			    // Write your solution here
			    // Usually how we determine if two strings are Anagram:
			    // Create a HashMap to store the number of occurence of every letter  O(n) in time and O(n) in space
			    // Here if we loop thru all the possible starting index, the brute force algorithm will be O(m*n) in time
			    // Therefore here we use a slideing window solution to get O(m) time complexity
			    List<Integer> res = new ArrayList<>();
			    Map<Character,Integer> hm = new HashMap<>();
			    for(char c : sh.toCharArray()){
			      Integer count = hm.getOrDefault(c,0);
			      hm.put(c,count+1);
			    }
			    int match = 0;
			    for(int i=0;i<lo.length()-sh.length()+1;i++){
			      if(i==0){
			        for(int j=0;j<sh.length();j++){
			          Integer count = hm.get(lo.charAt(j));
			          if(count != null){
			            hm.put(lo.charAt(j),--count);
			            if(count == 0){
			              match++;
			            }
			          }
			        }
			      }else{
			        // handle the character that was just shifted out of the window
			        Integer count = hm.get(lo.charAt(i-1));
			        if(count != null){
			            hm.put(lo.charAt(i-1),count+1);
			            if(count == 0){
			              match--;
			            }
			        }
			        // handle the character that was just shifted into the window
			        count = hm.get(lo.charAt(i+sh.length()-1));
			        if(count != null){
			            hm.put(lo.charAt(i+sh.length()-1),count-1);
			            if(count == 1){
			              match++;
			            }
			        }
			      }
			      if(match==hm.size()){
			          res.add(i);
			      }
			    }
			    return res;
		}
		  
	public boolean match(String input, String pattern) {
			    // Write your solution here
			    // C: 1.input string only has alphabets? 2. will pattern has invalid number like 0
			    // A: 1. yes 2. no
			    // R: recursion
			    // T:
			    if((input == null && pattern == null) || (input.length()==0 && pattern.length()==0) ){
			      return true;
			    }
			    if(input == null || pattern == null){
			      return false;
			    }
			    return match(input,pattern,0,0);
	}

	private boolean match(String input, String pattern, int i, int j){
			    // Base Case
			    if(i==input.length() && j == pattern.length()){
			      return true;
			    }
			    if(i==input.length() || j==pattern.length() ){
			      return false;
			    }
			    if(isDigit(pattern.charAt(j))){
			      int num = 0;
			      while(j<pattern.length()&&isDigit(pattern.charAt(j))){
			        num = 10*num + pattern.charAt(j)-'0';
			        j++;
			      }
			      while(num-->0){
			        i++;
			      }
			      if(i>input.length()){
			        return false;
			      }
			      return match(input,pattern,i,j);
			    }else if (input.charAt(i)==pattern.charAt(j)){
			      return match(input,pattern,++i,++j);
			    }else{
			      return false;
			    }
	}
	private boolean isDigit(char c){
			    if('0' <= c && c<='9'){
			      return true;
			    }
			    return false;
	}
	
	public String reverseWords(String input) {
	    // Write your solution here
	    int i = 0;
	    int j = 0;
	    char[] array = input.toCharArray();
	    int newLength = removeSpace(array);
	    //[i,j) region that is processed
	    while(j<newLength){
	      if(array[j] == ' '){
	        reverse(array,i,j-1);
	        i=j+1;
	      }
	      j++;
	    }
	    reverse(array,i,j-1);
	    reverse(array,0,newLength-1);
	    return new String(array,0,newLength);
	  }
	  private void reverse(char[] array,int left, int right){
	    int i =left; 
	    int j = right;
	    while(i<j){
	      char temp = array[i];
	      array[i] = array[j];
	      array[j] = temp;
	      i++;
	      j--;
	    }
	  }
	  private int removeSpace(char[] array){
	    int i=0;
	    int j=0;
	    while(j<array.length){
	      if(array[j]==' ' && (array[i] == ' ' || i==0)){
	        j++;
	      }else{
	        array[i] = array[j];
	        i++;
	        j++;
	      }
	    }
	    if(array[i-1] == ' '){
	      return i-1;
	    }
	    return i;
	  }
	  
	  public String longestPalindrome(String input) {
		    // Write your solution here
		    // Recursively  O(N^2)
		    if(input.length()<2){
		      return input;
		    }
		    int[] left = new int[]{0};
		    int[] maxLength = new int[]{0};
		    for(int i=0;i<input.length();i++){
		      findLongest(input,i,i,left,maxLength);
		      if(i+1<input.length()){
		        findLongest(input,i,i+1,left,maxLength);
		      }
		    }
		    return input.substring(left[0],left[0]+maxLength[0]);
		  }
		  private void findLongest(String input, int i, int j, int[] left, int[] maxLength){
		    while(i>=0 && j<input.length()){
		      if(input.charAt(i)==input.charAt(j)){
		        if(j-i+1>maxLength[0]){
		          maxLength[0] = j-i+1;
		          left[0] = i;
		        }
		        i--;
		        j++;
		      }else{
		        break;
		      }
		    }
		  }
		  
		  public String compress(String input) {
			    // Write your solution here
			    if(input.length() <=1){
			      return input;
			    }
			    // Preprocessing : count # of non-repeated alphabet
			    int countSingle = 0;
			    for(int i=0;i<input.length();){
			      if(i+1<input.length() && input.charAt(i)!=input.charAt(i+1)){
			        countSingle++;
			      }
			      while(i+1<input.length() && input.charAt(i)==input.charAt(i+1)){
			        i++;
			      }
			      if(i==input.length()-1 && input.charAt(i)!=input.charAt(i-1)){
			        countSingle++;
			      }
			      i=i+1;
			    }
			    char[] array = new char[input.length()+countSingle];
			    for(int i=0;i<input.length();i++){
			      array[i] = input.charAt(i);
			    }
			    int i=array.length-1;
			    int j=input.length()-1;
			    while(j>=0){
			      int jj = j;
			      while(jj-1>=0 && array[jj] == array[jj-1]){
			        jj--;
			      }
			      int count = j-jj+1;
			      if(count == 1){
			        array[i--] = '1';
			        array[i--] = array[j--];
			      }else{
			        // Deque<Character> stack = new ArrayDeque<>();
			        while(count>0){
			          array[i--] = (char)(count%10 + '0');
			          count = count/10;
			        }
			        array[i--] = array[j];
			        j = jj-1;
			      }
			    }
			    return new String(array,i+1,array.length-i-1);
			  }
}
