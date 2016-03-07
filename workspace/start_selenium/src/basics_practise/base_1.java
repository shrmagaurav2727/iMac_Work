package basics_practise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class base_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

//	        WebDriver firefox = new FirefoxDriver();
//	        
//	        
//	
//	        firefox.get("http://www.tripver.com/");
//	        
//	        String i = firefox.getCurrentUrl();
//	
//	System.out.println("Current URl is" + i);
//	
//	
//	firefox.close();
		
		
	System.setProperty("webdriver.chrome.driver","/Users/Labuser/Documents/chromedriver" );
		
	WebDriver  chromeDriver = new ChromeDriver();
		
	
	chromeDriver.get("https://www.starling.com/");
	
	String i = chromeDriver.getCurrentUrl();
	
	System.out.println("URL launched on Google chrome is "+ i);
	
	
	chromeDriver.close();
	

	
	
	}

}
