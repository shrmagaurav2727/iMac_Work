package encapsulation;

public class encapCod {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

 private String sname;
 private String ssection;
 private int srollno;
 
 
 //Setter method
 public void S1 (String s_name ){
	  sname= s_name;
	 
	 
 }
 
 
 //seter method
 public void S2 (String ssecString){
	 
	 ssection = ssecString;
 }
  

 //setter method
public void S3  (int s_rollno){
	
 srollno =  s_rollno;  
	
}


public String  G1(){
	
	return sname;
	
	
	
}


public String G2()  {
	
return ssection;
	
}



public int G3(){
	
	
	return srollno;
	
}

















}
