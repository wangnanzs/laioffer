package round2;

class BitOpSolution {
	public long reverseBits(long n) {
	    // Write your solution here
	    for(long i=0;i<16;i++){
	      long low = (n>>i)&1;
	      long high = (n>>(31-i))&1;
	      long mask = ((long)1 << (31-i));
	      if(low == 1){
	        n = n | (mask);
	      }else{
	        n = n & ((~mask) );
	      }
	      mask = ((long)1 << (i));
	      if(high==1){
	        n = n | (mask);
	      }else{
	        n = n & ((~mask));
	      }
	    }
	    return n;
	}
}
