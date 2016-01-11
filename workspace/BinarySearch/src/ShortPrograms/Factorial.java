package ShortPrograms;

public class Factorial {

	static int x=4;
	static int y;
	public int recursion(int x){
		
		if (x>1){
		x = x*recursion(x-1);
	//	System.out.println(x);
		}
		return x;  
	}
	
	public int normal() {
		
		for (int i= x-1; i >0; i--) {
			x = x*i;
		} return x;
	}
	int sum=0;
	public int fact(int num){
		
		if (num>1)	{
			System.out.println(num);
		num = num*fact(num-1);
		} return num;
	
	}
	
	public static void main(String[] args) {
	
		Factorial obj = new Factorial();
		System.out.println(obj.fact(4));
	}

}
