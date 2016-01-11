package ShortPrograms;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

public class SortHashMap {


	public static void main(String[] args) {
		
		HashMap<String, Integer> MyMap = new HashMap<String, Integer>();
		MyMap.put("Jitu", 1);
		MyMap.put("Kinu", 5);
		MyMap.put("zitu", 4);
		MyMap.put("Jhadu", 9);
		MyMap.put("Dipu", 6);
		
		MyMap.values();
		
		for (Map.Entry<String, Integer> kv: MyMap.entrySet()){
			System.out.println("Key: "+kv.getKey()+ " Value: "+kv.getValue());
		}
		
		
		Set<Entry<String, Integer>> set = MyMap.entrySet();
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
		Collections.sort( list, new Comparator<Map.Entry<String, Integer>>(){
			
			
		}
			
		for (Integer n : Value) {
			System.out.println(n);
		}
		
		
		
		
	}
	
	
	
	
	
	

}
