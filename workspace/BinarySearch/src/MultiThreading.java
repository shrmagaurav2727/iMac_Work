import java.lang.reflect.Array;


public class MultiThreading extends Thread {
	
	
	private static MultiThreading object1;
			
	public static MultiThreading getinstance(){
		return new MultiThreading();
	}
		
	
	
	public void running() {
		
		
		String abc = "jitend  er";
		System.out.println("running: "+Thread.currentThread().getName());
		//System.out.println(+\\s+);
		String arr[] = abc.split(" ");
		System.out.println(arr.length);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			
			if (arr[i].equalsIgnoreCase(" ")){
				sb.append(arr[i]);
			}
		}
		
	}
	
	public void run(){
		running();
	}
	
  

	
	public static void main(String[] args) {
	
//		MultiThreading abc = new MultiThreading();
//		abc.start();
//		
//		MultiThreading abc1 = new MultiThreading();
//		abc1.start();
		
		Threadingimplementation ab = new Threadingimplementation();
		ab.run();
		System.out.println("asda");
		
		StringBuffer sb1 = new StringBuffer("Amit");
		  StringBuffer sb2 = new StringBuffer("Amit");
		  String ss1 = "Amit";
		  System.out.println(sb1==sb2); // false
		  System.out.println(sb1.equals(sb2)); // true
		  System.out.println(sb1.equals(ss1)); //true
		  System.out.println("Poddar".substring(3)); // dar
		
		  String s = "Hello  ";
		  s += "Adobe  ";
		  s.trim(); 
		  System.out.print(s.trim()); 
		
	}

}


