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
	
	// Return the index of smallest element in a shifted ascending array
	public int shiftPosition(int[] array) {
		//Corner Case
		if(array == null || array.length == 0) {
			return -1;
		}
		// Corner Case: array is not shifted
		if(array[0]<array[array.length-1]) {
			return 0;
		}
		int left = 0;
		int right = array.length-1;
		while(left<right-1) {
			int mid = left + (right-left)/2;
			if(array[mid] < array[0]) {
				right = mid;
			}else if(array[mid]>array[0]){
				left = mid;
			}
		}
		return right;
	}
	
	public int totalOccurrence(int[] array, int target) {
		if(array == null || array.length == 0) {
			return 0;
		}
		int largestSmallerThan = largestSmallerThan(array,target);
		int smallestLargerThan = smallestLargerThan1(array,target);
		boolean include = largestSmallerThan+1<array.length && array[largestSmallerThan+1] == target? true : false;
		if(!include) {
			return 0;
		}
		if(largestSmallerThan == -1 && smallestLargerThan == -1) {
			return array.length;
		}
		if(largestSmallerThan == -1) {
			return smallestLargerThan;
		}
		if(smallestLargerThan == -1) {
			return array.length-1-(largestSmallerThan+1)+1;
		}
		return smallestLargerThan-largestSmallerThan-1;
	}
	
	private int largestSmallerThan(int[] array, int target) {
		int left = 0;
		int right = array.length-1;
		while(left < right-1) {
			int mid = left + (right - left)/2;
			if(array[mid]<target) {
				left = mid;
			}else {
				right = mid;
			}
		}
		if(array[right]<target) {
			return right;
		}else if(array[left]<target) {
			return left;
		}else {
			return -1;
		}
	}
	private int smallestLargerThan1(int[] array, int target) {
		int left = 0;
		int right = array.length-1;
		while(left < right-1) {
			int mid = left + (right - left)/2;
			if(array[mid]>target) {
				right = mid;
			}else {
				left = mid;
			}
		}
		if(array[left]>target) {
			return left;
		}else if(array[right]>target) {
			return right;
		}else {
			return -1;
		}
	}
}
