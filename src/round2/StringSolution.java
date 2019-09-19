package round2;

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
}
