package exceptions;

public class TryCatch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int a[] = {2,9,0};
		
		System.out.println("Arrayes defined in the rprogram are " + "  " +a[0] + a[1] +a[2]);
		
		
		try {
			
			System.out.println("before exception generated");
			
			
			System.out.println(a[9]);
			
		}
			catch(Exception e) {
				
				//Handling exception
				//code will come into catch when exception is going to be generated
				
			System.out.println("Code came into catch block"+ e);	
				
				
				
				
				
			}
			
			
			
			
			
			
			
			
		
		
	}

}
