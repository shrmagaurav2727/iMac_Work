
public class selectionsort {

	
	public static int arr2[] = {9,3,8,1,0};
	public static void main(String arr[]){
		
		for (int k=0; k<arr2.length-1; k++){
			int index=k ;
		for (int n=k+1; n<arr2.length; n++){
			if (arr2[k] > arr2[n]) {
				index = n ;
			}
		}
		swapNumbers(k,index);
		printNumbers(arr2);
	    }
		}
	
	private static void swapNumbers(int i, int j ) {
		  
        int temp;
        temp = arr2[i];
        arr2[i] = arr2[j];
        arr2[j] = temp;
    }
	private static void printNumbers(int[] input) {
        
        for (int i = 0; i < input.length; i++) {
            System.out.print(input[i] + ", ");
        }
        System.out.println("\n");
    }

}
