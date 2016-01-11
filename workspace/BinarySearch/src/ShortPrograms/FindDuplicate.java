package ShortPrograms;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.text.StyledEditorKit.ForegroundAction;

public class FindDuplicate{

	
	public int arr[] = {1,2,3,4,5,6,8,8,9,9};
	public int x;
	public void findDup(){
		
		for (int i = 0; i < arr.length; i++) {
			x= arr[i];
			for (int j = i+1; j < arr.length; j++) {
				if(x == arr[j]){
					System.out.println("Found duplicate : "+x);
				}
			}
		}
	}
	
	public void findDupUsingSorting(){
		
		Arrays.sort(arr);
		for (int j = 0; j < arr.length-1; j++) {
			if(arr[j] == arr[j+1]){
				System.out.println("Found duplicate : "+arr[j]);
			}
		}
	
	}
		
	public static void main(String[] args) {
		FindDuplicate  ab = new FindDuplicate();
		ab.findDupUsingSorting();
		
	}
	
	public void DemoFunc(){
		Singleton objt1 = Singleton.getInstance();
		Singleton objt2 = Singleton.getInstance();
		
	}
	
	
}
