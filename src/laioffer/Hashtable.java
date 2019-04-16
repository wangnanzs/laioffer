package laioffer;
import java.util.*;
public class Hashtable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HTsolution sol = new HTsolution();
		String[] result = sol.topKFrequent(new String[] {"d","a","c","b","d","a","b","b","a","d","d","a","d"}, 3);
		for(String s : result) {
			System.out.println(s);
		}
	}

}

class HTsolution{
	public String[] topKFrequent(String[] combo, int k) {
	    // Write your solution here
	    Map<String,Integer> hm = getFreqMap(combo);
	    Queue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(new myComparator());
	    int i=0;
	    if(k>hm.size()){
		      k = hm.size();
		}
	    for(Map.Entry<String,Integer> entry : hm.entrySet()){
	      if(i<k) {
	    	  pq.offer(entry);
	      }else {
	    	  if(entry.getValue() > pq.peek().getValue()) {
	    		  pq.poll();
	    		  pq.offer(entry);
	    	  }
	      }
	      i++;
	    }
	    String[] result = new String[k];
	    for(int j =0;j<k;j++){
	      result[k-j-1] = pq.poll().getKey();
	    }
	    return result;
	  }
	  private Map<String,Integer> getFreqMap(String[] input){
	    Map<String,Integer> hm = new HashMap<>();
	    for(String s: input){
	      if(!hm.containsKey(s)){
	        hm.put(s,1);
	      }else{
	        hm.put(s,hm.get(s)+1);
	      }
	    }
	    return hm;
	  }
}
class myComparator implements Comparator<Map.Entry<String, Integer>>{
	  @Override
	  public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
		  return o1.getValue().compareTo(o2.getValue());
	  }
}