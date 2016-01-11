package ShortPrograms;

public class ArmstrongNumber {

	static int  sum=0;
	static int num = 371;
	static int power=0;
	static int Amstatic ;
	
	public static void AmStaticMethod(){
		
	}
	
	
	public static void main(String[] args) {
		//ArmstrongNumber arm = new ArmstrongNumber();
		//sum = arm.Sum(num);
		int tmp ;
		//power = String.valueOf(num).length();
		String n =  Integer.toString(num);
		int power = n.length();
		
		for (int i = 1; i <= power; i++) {
			tmp  = num%10;
			int div =1;
			for (int j = 1; j <= power; j++) {
				div *=tmp;
			}
			sum += div; 
			num = num/10;
		}
		
		System.out.println("sum :" + sum);
		
		
	}
	
	
		

}
