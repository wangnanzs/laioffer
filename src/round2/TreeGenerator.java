package round2;

class TreeGenerator {
	public TreeNode generate(Integer[] array) {
		if(array == null || array.length == 0) {
			return null;
		}
		TreeNode[] nodeArray = new TreeNode[array.length];
		for(int i=0; i<array.length;i++) {
			if(array[i] != null) {
				nodeArray[i] = new TreeNode(array[i]);
				if(i>0 && i%2==1) {
					nodeArray[(i-1)/2].left = nodeArray[i];
				}else if(i>0 && i%2==0){
					nodeArray[(i-1)/2].right = nodeArray[i];
				}
			}
		}
		return nodeArray[0];
	}
}
