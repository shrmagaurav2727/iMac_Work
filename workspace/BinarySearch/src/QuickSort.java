  
public class QuickSort {

	public static int[] array ={5,6,1,3,4,8,9,10,4}; ;
	public static void main(String[] args) {
		  printNumbers(array);
		quickSort(0, array.length-1);
		
		
	}
	
	private static void quickSort(int lowerIndex, int higherIndex) {
        
        int i = lowerIndex;
        int j = higherIndex;
        // calculate pivot number, I am taking pivot as middle index number
        int pivot = array[lowerIndex+(higherIndex-lowerIndex)/2]; 
        System.out.println("pivot :"+pivot );
        // Divide into two arrays
        while (i <= j) {
        //	System.out.println("while again");
            /**
             * In each iteration, we will identify a number from left side which
             * is greater then the pivot value, and also we will identify a number
             * from right side which is less then the pivot value. Once the search
             * is done, then we exchange both numbers.
             */
            while (array[i] < pivot) {
                i++;
                System.out.println("I :"+ i);
            }
            while (array[j] > pivot) {
                j--;
                System.out.println("J :"+ j);
            }
            if (i <= j) {
                exchangeNumbers(i, j);
                //move index to next position on both sides
                i++;
                j--;
            }printNumbers(array);
        } 
        // call quickSort() method recursively
        if (lowerIndex < j){
        	System.out.println("Final J :"+j);
            quickSort(lowerIndex, j); }
        if (i < higherIndex)
            quickSort(i, higherIndex);
    }
 
    private static void exchangeNumbers(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
private static void printNumbers(int[] input) {
        
        for (int i = 0; i < input.length; i++) {
            System.out.print(input[i] + ", ");
        }
        System.out.println("\n");
    }



}
