package round2;
import java.util.*;
class BinarySearch {
	public int smallerPairs(int[] array, int target) {
	    // Write your solution here
	    Arrays.sort(array); // O(nlogn)
	    int res = 0;
	    for(int i=0;i<array.length-2;i++){
	      int end = 0;
	      int left = i+1;
	      int right = array.length-1;
	      while(left<right-1){
	        int mid = left + (right-left)/2;
	        if(array[i]+array[mid] >= target){
	          right = mid;
	        }else{
	          left = mid;
	        }
	      }
	      if(array[i]+array[right] < target){
	        end = right;
	      }else if(array[i]+array[left] < target){
	        end = left;
	      }else{
	        end = i;
	      }
	      res += end - i;
	    }
	    return res;
	}
	public int sqrt(int x) {
	    // Write your solution here
	    if(x<=1){
	      return x;
	    }
	    int left = 0;
	    int right = x;
	    while(left<right-1){
	      int mid = left + (right-left)/2;
	      long product = (long)mid*mid;
	      if(product == x){
	        return mid;
	      }else if(product > x){
	        right = mid;
	      }else{
	        left = mid;
	      }
	    }
	    return left;
	}  
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Binary search
        // Assuming m, n will not overflow and array is sorted in ascending order
        int m = nums1.length;
        int n = nums2.length;
        int total = m+n;
        int k = total%2 == 0? total/2 : total/2 + 1;
        if(m==0){
            return total%2 == 0? (nums2[k-1]+nums2[k])/2.0 : (double)nums2[k-1];
        }
        if(n==0){
            return total%2 == 0? (nums1[k-1]+nums1[k])/2.0 : (double)nums1[k-1];
        }
        int start1 = 0;
        int start2 = 0;
        while((start1<m && start2<n) && k>1){
            int idx1 = start1 + k/2-1;
            int idx2 = start2 + k-k/2-1;
            // Check if indexes are out of bound
            if(idx1 >= m){
                idx1 = m-1; 
            }
            if(idx2 >= n){
                idx2 = n-1;
            }
            if(nums1[idx1]<=nums2[idx2]){
                k = k - (idx1 - start1 + 1);
                start1 = idx1+1;
            }else{
                k = k - (idx2 - start2 + 1);
                start2 = idx2+1;
            }
        }
        int left = 0;
        if(start1 == m){
            left = nums2[start2+k-1];
            start2 = start2+k;
        }
        else if(start2 == n){
            left = nums1[start1+k-1];
            start1 = start1+k;
        }
        else if(k==1){
            if(nums1[start1]<nums2[start2]){
                left = nums1[start1];
                start1++;
            }
            else{
                left = nums2[start2];
                start2++;
            }
        }
        if(total%2 == 1){
            return (double)left;
        }
        int right = 0;
        if(start1 == m){
            right = nums2[start2];
            start2++;
        }else if(start2 == n){
            right = nums1[start1];
            start1++;
        }else{
            if(nums1[start1]<nums2[start2]){
                right = nums1[start1];
                start1++;
            }
            else{
                right = nums2[start2];
                start2++;
            }
        }
        return (left+right)/2.0;
    }
	
	
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
