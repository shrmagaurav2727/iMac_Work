package ShortPrograms;

public class Singleton {

	
	private static Singleton objt ;
	
	private Singleton(){
		
	}
	
	public static Singleton getInstance(){
		if (objt == null){
			objt = new Singleton();
		}
		return objt;
	}
	
	
}
