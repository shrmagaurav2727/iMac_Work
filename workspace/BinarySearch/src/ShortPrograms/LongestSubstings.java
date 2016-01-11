package ShortPrograms;

public class LongestSubstings {


	public static void main(String[] args) {
		//LongestSubstings b = new LongestSubstings();
		System.out.println(getLongestSubString("Jitender"));

	}

	private static String getLongestSubString(String str) {
		
		
		String curSubStr = "";
		String LongestSubStr = "";
		
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (curSubStr.indexOf(c) == -1) {
				curSubStr += c;
				System.out.println("cuSub:"+curSubStr);
				continue;
			} else {
				if(curSubStr.length()> LongestSubStr.length())
					LongestSubStr = curSubStr;
				curSubStr = curSubStr.substring(curSubStr.indexOf(c) + 1) +c;
			}
		}

		if(curSubStr.length() > LongestSubStr.length())
			LongestSubStr = curSubStr;
		return LongestSubStr;

	}

}
