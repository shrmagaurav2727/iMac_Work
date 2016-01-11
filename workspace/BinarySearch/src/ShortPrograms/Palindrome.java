package ShortPrograms;

public class Palindrome {

	
	public  void PalindromeNUm(int Num){
		
		
	for (int i = 0; i <= Num; i++) {
		
		for (int j = 0; j <= i; j++) {
			System.out.print(j);
		}
		System.out.println();
	}
	
	}
	public static void main(String[] args) {
		Palindrome pali = new Palindrome();
		pali.PalindromeNUm(5);
	}

}
