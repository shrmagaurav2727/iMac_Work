package ShortPrograms;

import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class Fibonacci {

	static int sum = 0; 
	public static void main(String[] args) {
		
		LinkedList<Integer> li = new LinkedList<Integer>();
		li.add(0);
		li.add(1);
		int sum=0;
		for (int i = 2; i < 10; i++) {
			sum = li.get(i-1) + li.get(i-2) ;
			 li.add(sum);
		}
		
		System.out.println(li);

	}
	
	// Using Array
	public void fibonacci(int Num){
		int Arr[] = new int [Num];
		Arr[0]= 0;
		Arr[1]=1;
		for (int i = 2; i < Num; i++) {
			
			Arr[i] = Arr[i-2] + Arr[i-1];
		}
		
		for (int i = 0; i < Arr.length; i++) {
			System.out.print(Arr[i]+ " ");
		}
	}

}
