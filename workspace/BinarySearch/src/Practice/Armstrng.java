package Practice;

import java.awt.List;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;


public class Armstrng {
	
	public void readfromFile() throws FileNotFoundException{
		
		FileInputStream fis = new FileInputStream("ShortPrograms.abc.txt") ;
		DataInputStream dis = new DataInputStream(fis);
		BufferedReader bf = new BufferedReader(new InputStreamReader(dis));
		
		
	}
	
	public void Duplicate(String Name){
		LinkedList<Character> list = new LinkedList<Character>();
		
		for (int i = 0; i < Name.length(); i++) {
			
			if (list.contains(Name.charAt(i))){
				System.out.println( "Duplicate char: "+Name.charAt(i));
			}
			
			else{
			list.add(Name.charAt(i));
			}
		}
		
		
	}
	
	static int value =1;
	
	public int getValue(){
		try{
		
		value= value +1;
		return value;
		}
		catch(Exception e){
			return 0;
		}
		finally{
			System.out.println("in final");
			value = value+3;
		}
	}
	
	public static void main(String[] args) {
		
		Armstrng a = new Armstrng();
		System.out.println(a.getValue());
		System.out.println(value);
		
		} 
	}


