package laioffer;

public class StringProblem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringSolution sol = new StringSolution();
//		System.out.println(sol.strstr("abba","ba"));
		System.out.println(sol.strstr("abcdefghijklmnopqrstuvwxyzzabcdefghijklmnopqrstu","qrstuvwxyzzabcdefghijklmnopqrstu"));
	}

}
class StringSolution{
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