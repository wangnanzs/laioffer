package round2;

class BinarySearch {
	// Return index of the element that is smallest and larger than the target
	// -1 if can not find
	public int smallestLargerThan(int[] array, int target) {
		int left = 0;
		int right = array.length-1;
		int res = -1;
		while(left<=right) {
			int mid = left + (right-left)/2;
			if(array[mid]>target) {
				right = mid-1;
				res = mid;
			}else {
				left = mid+1;
			}
		}
		return res;
	}
}
