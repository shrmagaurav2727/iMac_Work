package ShortPrograms;
1
import java.awt.List;
import java.util.LinkedList;
import java.util.ListIterator;

public class PerfectNumber {

	/**A perfect number is a positive integer that is equal to the sum of its proper positive divisors,
	 * that is, the sum of its positive divisors excluding the number itself. Equivalently, a perfect 
	 * number is a number that is half the sum of all of its positive divisors. The first perfect number
	 *  is 6, because 1, 2 and 3 are its proper positive divisors, and 1 + 2 + 3 = 6. Equivalently,
	 *   the number 6 is equal to half the sum of all its positive divisors: 
	 *   ( 1 + 2 + 3 + 6 ) / 2 =6
	 */
	public static void main(String[] args) {

		int x= 28;
		
		
		int sum =0;
		LinkedList<Integer> Pnum = new LinkedList<Integer>();	
		for (int i = 10; i >0; i--) {
			if(x%i ==0){
				Pnum.add(i);
			}
		}
		System.out.println(Pnum);
		ListIterator<Integer> litr = Pnum.listIterator();
		
		while(litr.hasNext()){
				sum = sum + litr.next();
			}
		System.out.println(sum);
		
		if (sum/2 == x){
			System.out.println("FOUND NUmber :" + x);
		}	
		else{
			System.out.println("Nothing");	
		}
		
	}



}
