package ShortPrograms;

public class ReverseString {


	public String x = "Jitender";
	public String y;
	String reverse = "";
	StringBuilder sb = new StringBuilder();

	public void reverse(){

		for (int i = x.length()-1; i >= 0; i--) {
			sb =  sb.append(x.charAt(i)); 
		}

	}

	public String NS = "";
	public String recursion(String s){


		//		if(index == 1){
		//			return s.substring(0,index);
		//			} else {
		//				System.out.println(s.charAt(index));
		//				reverse += recursion(s.substring(1), index - 1) + s.substring(0, 1);
		//			return reverse;
		//			}

		int len = s.length();
		if(len >0){
			System.out.println(s);
			NS +=  recursion(s.substring(1)) + s.substring(0, 1) ;

		}
		return NS;

	}

	public String recursion1(String s){
		System.out.println(s);
		while (s.length() >=1){
			System.out.println("NS");
			NS +=recursion1(s.substring(1)) +  s.substring(0,1) ;
			return NS;
		}return NS;
	}



	public static void main(String[] args) {
		ReverseString obj = new ReverseString();
		String y = obj.recursion1("Jitender");
		System.out.println("y:"+y);
		//System.out.println(y.);

	}



}
