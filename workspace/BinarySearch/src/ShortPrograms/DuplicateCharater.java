package ShortPrograms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class DuplicateCharater {

	public static void main(String[] args) {
		System.out.println("abc");
		DuplicateCharater dup = new DuplicateCharater();
		dup.Duplicate();
	}

	public String  myString = "jitu is good";

	public void Duplicate(){

		Map<Character, Integer> Map = new HashMap<Character, Integer>();
		char[] chr = myString.toCharArray();

		System.out.println(chr);
		for (int i = 0; i < chr.length-1; i++) {
			char x = chr[i];
			
			if(Map.containsKey(x)){
				Map.put(x, Map.get(x)+1);
				System.out.println(x);
			}
			else{
				Map.put(x, 1);
			}
		}	
		
	//	Iterator<Map.Entry<Character, Integer>> itr = Map.entrySet().iterator();
		
//		while(itr.hasNext()){
//			System.out.println(itr.next().getKey() +"   "+ Map.get(itr.next().getKey()));
//		}
		
		Set<Map.Entry<Character, Integer>> entrySet = Map.entrySet();
        System.out.printf("List of duplicate characters in String '%s' %n", myString);
        for (Map.Entry<Character, Integer> entry : entrySet) {
            if (entry.getValue() > 1) {
                System.out.printf("%s : %d %n", entry.getKey(), entry.getValue());
            }
        }




		
				
			
//			for (int j = i+1; j < chr.length-1; j++) {
//				if (x == chr[j]){
//					System.out.println("x :"+x);
//					Map.put(x, Map.get(x+1));
//				}
//			}
			
	}


	public void WithoutHashMap(){
		String input = "Java2Novice";
		ArrayList<Character> used = new ArrayList<Character>();
		for(int i = 0 ; i < input.length(); i++)
		{
			if(!used.contains(input.charAt(i)))
			{
				int count = 0;
				for(int j = 0; j < input.length(); j++)
				{
					if(input.charAt(i) == input.charAt(j))
					{
						count++;
					}

				}
	     			if(count > 1)
				{
					System.out.println(input.charAt(i)+" "+ (count));
				}
				used.add(input.charAt(i));
			}
		}
	}



}
