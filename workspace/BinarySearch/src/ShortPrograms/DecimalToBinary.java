package ShortPrograms;

import java.util.ArrayList;
import java.util.LinkedList;

public class DecimalToBinary {


    public static void covert(){
    	
    	int num = 10 ;
    	int num1[] = new int [30];
    	int index =0;
    		while (num >=1){
    			num1[index++] = num%2;
    			
    			num =num/2;    		}
    		for(int i = index-1;i >= 0;i--){
                System.out.print(num1[i]);
            }
    }
    
	public void usingList(int Num){
			
			LinkedList<Integer> Arr = new LinkedList<Integer>();
			while(Num>=1){
				Arr.add(Num%2);
				Num = Num/2;
			}
			System.out.println(Arr);
		}
	public static void main(String[] args) {
		//covert();
		DecimalToBinary a = new DecimalToBinary();
		a.BinarytoDecimal2();
	}
	
	public void BinarytoDecimal(){
		int num = 100110;
		int pow =0;
		int decimal=0;
		while (num>0){
			int tmp = num%10;
			decimal = decimal + (int) (tmp*Math.pow(2, pow));
			num = num/10;
			pow++;
			
		} System.out.println(decimal);
		                                                              
	}
	
	
	

}
