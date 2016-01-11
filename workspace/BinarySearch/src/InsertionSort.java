import java.util.ArrayList;


public class InsertionSort {

	
	public static int arr2[] = {9,3,8,1,0};
	public static void main(String arr[]){
		
		
		
	for (int k=0; k<arr2.length; k++){	
		for (int n=k; n>0; n--){
			//System.out.println(n);
			if (arr2[n] < arr2[n-1]) {
				//System.out.println("swaped ");
				int temp = arr2[n];
				arr2[n] = arr2[n-1];
                arr2[n-1] = temp;

			}printNumbers(arr2);
		}
	}
		}
	
	private static void swapNumbers(int i, int j ) {
		  
        int temp;
        temp = arr2[i];
        arr2[i] = arr2[j];
        arr2[j] = temp;
    }
	private static void printNumbers(int[] input) {
        
		ArrayList<Integer> li = new ArrayList<Integer>();
		
        for (int i = 0; i < input.length; i++) {
            System.out.print(input[i] + ", ");
        }
        System.out.println("\n");
    } 
	
	
}
