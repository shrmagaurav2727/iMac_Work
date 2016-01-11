package array_P;

import java.util.ArrayList;

public class arr_1 {

	int i;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

 ArrayList<String>	Arrl1 = new ArrayList<String>();
		
   Arrl1.add("Row1");
   Arrl1.add("Row2");
   Arrl1.add("Row3"); 
 System.out.println("_______________________________________");
		
 //to set index value
 
 for (int i=0; i<Arrl1.size(); i++)
 {
	 
	 System.out.println("Index of al the item is "+ Arrl1.get(i));
	 
	 
	 
	 
 }
 
int Indexnumber = Arrl1.indexOf("Row1"); 
System.out.println("getting inde of 3rd Item in array"+ Indexnumber);
	
 	


Arrl1.remove(2);	
	
for (int i=0; i<Arrl1.size(); i++)
	
	System.out.println("New array will be like"+ Arrl1.get(i));
	
 }













}
