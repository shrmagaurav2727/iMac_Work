package Inheritance_j;

public class Audi extends Cars {

	public int speed =150;
	
	
	public static void main (String args[]){
		
		//override class method
		
		Cars A1 = new Audi();
		
		A1.Seats();
		  int seat = 2;
		
		System.out.println("seats for audi"+ seat);
		
//		System.out.println("Fuel type of this car is "+A1.type);
//		
//		System.out.println("Max Speed of car is "+ A1.speed);
//		
//		A1.result();
               		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void result(){
		
		System.out.println("The color of car is " + color);
		
		Seats();
		
	}
	
	
	
	
	
	
	
	
	
}
