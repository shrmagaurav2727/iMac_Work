package ShortPrograms;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class duplicatesInFile {


	public static void main(String[] args) {
		duplicatesInFile dp = new duplicatesInFile();
		dp.duplicateFile();
	}

	public void duplicateFile(){
		try {
			FileInputStream fis = new FileInputStream("src\\abc.txt");
			DataInputStream dis = new DataInputStream(fis);
			BufferedReader buf = new BufferedReader(new InputStreamReader(dis));
			String line = null;//buf.readLine();
			System.out.println(line);
			HashMap<String, Integer> Map = new HashMap<String, Integer>();

			while((line = buf.readLine()) != null){ 
			
			StringTokenizer st =new StringTokenizer(line, " ");
			while(st.hasMoreTokens()){
				String tmp = st.nextToken().toLowerCase();
				//System.out.println(tmp);
				if(Map.containsKey(tmp)){
					Map.put(tmp, Map.get(tmp)+1);
				} else {
					Map.put(tmp, 1);
				}

			}
			}
			for(Entry<String, Integer> kv: Map.entrySet()){
				System.out.println("Key: "+ kv.getKey()+ " Value: "+ kv.getValue());
			}
		//******************************************************************************************

			//without HahMap
			/*	
		
			ArrayList<String> list = new ArrayList<String>();
			
			String[] arr = line.split(" ");
			
					
			for (int i = 0; i < arr.length; i++) {
			
				String tmp = arr[i];
				if (list.contains(tmp)){
					//System.out.println("inside");
					int count = 2;
					for (int j = i+1; j < arr.length-1; j++) {
					//	System.out.println(arr[j]);
						if (tmp.equalsIgnoreCase(arr[j])){
							//System.out.println(arr[j]);
							count++;
						}
					} System.out.println("Word :" + tmp + " = "+ count);
					
				}
				else{
					list.add(tmp);
					//System.out.println(list);
				}

			}
*/
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

}
