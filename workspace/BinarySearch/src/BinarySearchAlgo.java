
public class BinarySearchAlgo {

	public int abc=1;
	public static  int   arr1[] = {1,2,3,4,5,6,7,8,9};
	public int x=9;
	public static void main(String args[]){

		int n1 = arr1.length;
		System.out.println(n1);
		BinarySearchAlgo abc = new BinarySearchAlgo();
		System.out.println("Found at : " +abc.findMin(arr1, 0, 8));
	}



	public int findMin(int arr[], int low, int high)
	{	
		int mid = low + (high - low)/2; /*(low + high)/2;*/
		System.out.println("mid : " +mid);
		if (low <= high) {
			//System.out.println("x = "+ x + "y = "+mid);
		if (arr1[mid] == x) {
			System.out.println("In loop "+mid);
			return mid;
		}
		if (x > arr1[mid]){
			System.out.println("In 2nd loop");
			 return findMin(arr1, mid+1, high);
		}
		else if  (x < arr1[mid]){
			System.out.println("In 3rd loop");
			return findMin(arr1, low, mid-1);
		} 
		}System.out.println("out");return 10;
	
	}
	
}
