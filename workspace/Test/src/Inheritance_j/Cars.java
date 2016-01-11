package Inheritance_j;

public class Cars {

    private String Price =  "Moderate" ;
   
   public String type = "Vehicle" ;
   
  public String color = "Black"  ;
   
   
   private String fuel = "Petrol"  ;
   
	
   
   
   public static void main (String args[])
			{
		
		Cars C1 = new Cars();
		    }
	
	 
   public String  getfuel(){
	   
	   return fuel;
	   
   }
	
	
	
     protected void Seats() {
    	 
    	 int seat = 4;
    	 
    	 System.out.println("Number of Seats in care are" + "  " + seat); 
    	 
     }
   
	
	
	
	
	
	
}
