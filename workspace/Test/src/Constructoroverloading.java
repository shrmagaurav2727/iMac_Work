
public class Constructoroverloading {

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String fname ;
		String  mname;
		
//	Constructoroverloading	Cons1 = new Constructoroverloading("Jim");
	
	Constructoroverloading Const2 = new Constructoroverloading("Marry", "Lake");
		
		}



     public Constructoroverloading(String mname) {
    	 
//    	 System.out.println("First construcor" +"   " + mname);
    	 
    	 System.out.println("New constructor" + "   " + mname);
    	 
     }



    public Constructoroverloading (String mname, String fname){
    	
    	this("Jhon");
    	
    	System.out.println("Two P constructor called" + "  "+ mname +"   " + fname);
    	
    }



















}
