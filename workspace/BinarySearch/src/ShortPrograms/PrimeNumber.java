package ShortPrograms;

public class PrimeNumber {

	
	public static void main(String[] args) {
		PrimeNumber c = new PrimeNumber();
		System.out.println(c.prime());

	}
	
	int x= 17;
	
	public boolean prime(){
		
		for (int i = 2; i < x; i++) {
			if((x%i)==0){
				return false;
			}
			
		}return true;
	}

}
