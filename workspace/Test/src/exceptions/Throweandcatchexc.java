package exceptions;

import java.rmi.AccessException;

public class Throweandcatchexc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		codecexc();
		
		}




public static void  codecexc(){
	
	try {
		
		throwexc();
	}
		
catch (Exception e){
		
	System.out.println("Division by 0  is th error");
		
		}
	
}

public static void throwexc() throws ArithmeticException  {


int i = (40/0);


}




}
