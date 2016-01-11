package ShortPrograms;

public class pyramid {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for(int i=0;i<=9;i++)
		{
			System.out.println();
			for(int k=0; k<=9-i; k++)
				System.out.print(" ");
			for(int j=0;j<=i;j++)
				System.out.print(" *");
		}
	}

}
