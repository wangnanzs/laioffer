package laioffer;


//import java.util.*;

public class Practice18 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Integer[] array = new Integer[] {1,2,3,4,5};
//		MyList<Integer> ml = new MyList<>(array);
//		for(Iterator<Integer> iter = ml.iterator(); iter.hasNext();) {
//			System.out.println(iter.next());
//		}
		Integer[] array = new Integer[] {1,2,3,null,null,4,5};
		MyBST<Integer> bst = new MyBST<>(array);
		bst.print();
	}

}
class Solution18{
	
}