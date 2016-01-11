import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class Random extends Threadingimplementation {
	
	public int x;
	public int arr[] = new int [3];	
	
	public static void StaticMethod(){
		System.out.println("in static ");
	}
	public void  nonstatic(){
		x=2;
		super.hello();
	}
	
	@Override
	public void hello() {
		
		MultiThreading obj = new MultiThreading();
		MultiThreading obj2 = new MultiThreading();
		
		System.out.println("base class");
		
	}

	
	public static void main(String[] args) {
		int[] a = {1,2,3};
		int[] b = new int[3];
		List<Integer> li = new ArrayList<Integer>();
	        ListIterator<Integer> litr = null;
	        li.add(1);
	        li.add(1);
	        li.add(1);
	        li.add(1);
	        li.add(1);
	        Random override = new Random();
	        litr=li.listIterator();
	        override.nonstatic();
	       
	        System.out.println("Elements in backward directiton");
	        while(litr.hasPrevious()){
	            System.out.println(litr.hasPrevious());
	        }
	        Collections.reverse(li);
			}
		
	

}
