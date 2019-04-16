package laioffer;

public class StringProblem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char a = 'z';
		char b = 'x';
		System.out.println(a-b);
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
}