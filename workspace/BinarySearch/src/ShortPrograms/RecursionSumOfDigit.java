package ShortPrograms;

public class RecursionSumOfDigit {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RecursionSumOfDigit a = new RecursionSumOfDigit();
		System.out.println(a.Sum(2371));
	}
	int sum=0;
	public int SumOf(int num){
		

		if(num >0){	
			sum += num%10;
			System.out.println("sum: "+sum);
			SumOf(num/10);
		}
		
		return sum;
	}
		
		public int Sum(int num){
			
				while(num >=1){
					sum = sum + num%10 ;
					System.out.println("sum: "+sum);
					Sum(num/10);
					return sum;
				}
				return sum;
			}

}
