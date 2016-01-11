package cpa.qa.library;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


import cpa.qa.util.Keywords;
import cpa.qa.util.PropertiesUtil;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.HasInputDevices;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.Select;


public class Functions {
	WebDriver driver=null;
	public static String browserType;
	
	
	
	/**
	 * 
	Method Name: getmanifest file
 	Parameters: None
 	Return Type: String
 	Objective: Method reads Browser Name from Manifest file created by Build.xml
	 *
	 **/
	
	
	public static String getManifestInfo() throws IOException {
	try{  
		 String filename = System.getProperty("user.dir")+"\\MANIFEST.MF";
		 BufferedReader reader = new BufferedReader(new FileReader(filename));

		 
		   String line;
		  
		   while((line = reader.readLine()) != null){ 
		     System.out.println(line);
		     if(line.contains("BrowserName")){
		    	 String BrowserName = line.replace("BrowserName:", "").trim();
		    	 return BrowserName;
		    } 
		   } 
		   System.out.println("BrowserName doesnot found in Build.xml, hence forcing Mozilla as Browser");
		   return "Mozilla";
		 } 
		catch (IOException e) {
		   e.printStackTrace();
		   System.out.println("Exception occured while reading build.xml, Forcing mozilla");
		   return "Mozilla";
		 }

	
	}
	
	
	
	
	/**
	 * 
	Method Name: openBrowser
 	Parameters: browserType
 	Return Type: String
 	Objective: Public method to open browser of 'browserType' specified in Test Cases.xlsx
	 *
	 **/
	
	
	public String openBrowser(){
		System.setProperty("org.uncommons.reportng.title", "CPA Global Discover");
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		System.setProperty("org.uncommons.reportng.stylesheet", "hudsonesque.css");
		
		try { this.browserType = getManifestInfo(); } 
		catch (IOException e) {  e.printStackTrace(); browserType ="Chrome"; }
		
		if(browserType.equals("Mozilla")){
			driver = new FirefoxDriver();
		}else if(browserType.equals("Chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browserType.equals("IE")){
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			
						
		}
		driver.manage().window().maximize();

		// Updated by Pushpendra
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		return "Pass";
	}



	

	/**
	 * 
	Method Name: closeBrowser
 	Parameters: null 
 	Return Type: String
 	Objective: Public method to close browser by quitting the driver
	 * 
	 **/


	public String closeBrowser(){
		driver.quit();
		return "Pass";

	}


	
	

	/**
	 * 
	Method Name: Launch
 	Parameters: URL 
 	Return Type: String
 	Objective: Public method to Launch the requested 'URL' by the user 
	 * 
	 **/
	public String Launch(String url)	  {

		try{
			// Updated by Pushpendra
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			driver.get(url);
			
			if (browserType.equalsIgnoreCase("IE")){
				
				try {
					driver.get("javascript:document.getElementById('overridelink').click();");
				} catch (Exception e) {}
				
				
				}
			
			
			
			
		} catch ( Exception ex ) {
			System.out.println(ex);
			return "Fail";
		}
		String windowtitle=driver.getTitle();
		if (windowtitle != null) 
			return "Pass";		
		else
			return "Fail";

	}


	
	
	/**
	 * 
 	Method Name: Login
 	Parameters: Username, Password
 	Return Type: String
 	Objective: Perform login operation [Also, (1) Handle JavaScript Alert for invalid login scenarios. (2) Handle Sign In intermittent issue with the new UI]
	 * 
	 **/
	public String Login(String uid, String pwd)	  
	{
		//String a = null;
		try{
			String LoginURL = driver.getCurrentUrl();
			System.out.println("url--> "+LoginURL);
			driver.findElement(By.xpath(PropertiesUtil.getProperty("UserName"))).clear();	
			driver.findElement(By.xpath(PropertiesUtil.getProperty("UserName"))).sendKeys(uid);
			driver.findElement(By.xpath(PropertiesUtil.getProperty("Password"))).clear();
			driver.findElement(By.xpath(PropertiesUtil.getProperty("Password"))).sendKeys(pwd);
			driver.findElement(By.xpath(PropertiesUtil.getProperty("Signin_btn"))).click();
			//String NewURL = driver.getCurrentUrl();
			//System.out.println("New URL now==> "+NewURL);
			Thread.sleep(1000);
			String a = VerifyExistence("Navigation_Menu");
			System.out.println(a);
			if (a.equalsIgnoreCase("Pass"))
			{
				//System.out.println("UserName:"+uid);
				System.out.println("Logged in Successfully");
				return "Pass";
			}
			return "Fail";

		} 
		catch ( Exception ex ) 
		{
			return "Fail";
		}
	}

	
	
	//private function to check presence of JavaScript error
	/*
		 Parameter: null
		 Return Type: String
		 Objective: Checks/handle JavaScript alert whenever login() is called.
	 */
	public String chkAlert()
	{ 
		try
		{
			try { Thread.sleep(500); } catch (InterruptedException e) {}
			Alert javascriptAlert = driver.switchTo().alert();
			System.out.println(javascriptAlert.getText()); // Get text on alert box
			javascriptAlert.accept(); 
			driver.findElement(By.xpath(PropertiesUtil.getProperty("UserName"))).clear();	
			driver.findElement(By.xpath(PropertiesUtil.getProperty("Password"))).clear();
			return "Fail"; 
		}
		catch(NoAlertPresentException e)
		{	System.out.println("Javascript error not displayed");
			chkSignIn();
			
			return "Pass";
		}
	}






	//private function to handle Sign In intermittent issue on new UI
	/*
		 Parameter: null
		 Return Type: String
		 Objective: Check/handles intermittent 'Sign In' issue with Login.
	 */
	private void chkSignIn() 
	{
		try
		{
			boolean chk = driver.findElement(By.xpath(PropertiesUtil.getProperty("Signin_btn"))).isDisplayed();
			if (chk == true)
			{
				DismissErrors();
				driver.findElement(By.xpath(PropertiesUtil.getProperty("Signin_btn"))).click();
				//Updated by Pushpendra
				Thread.sleep(500);
				//				return "Pass";
			}
			else if (chk == false)
			{
				return;
			}
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			return;
		}

	}


	
	
	/**
	 * 
	  	Method Name: Logout
 		Parameters: null
 		Return Type: String
 		Objective: Logs out the user from the application.
	 * 
	 **/	
	
	
	public String Logout()  {
		try{
			driver.findElement(By.xpath(PropertiesUtil.getProperty("NAV_Logged_in_as_UserName"))).click();
			driver.findElement(By.xpath(PropertiesUtil.getProperty("NAV_Log_Out"))).click();
			//Thread.sleep(100);
			return "Pass";

		} catch ( Exception ex ) {
			System.out.println(ex);
			return "Fail";
		} 
	}





	/**
	 * 
	  	Method Name: JavascriptAlert
 		Parameters: Operation
 		Return Type: String
 		Objective: Function to accept or dismiss java script alert thrown	
	 * 
	 **/


	public String JavascriptAlert(String Operation) throws Throwable
	{ 
		try
		{
			//	Thread.sleep(1000);
			Alert javascriptAlert = driver.switchTo().alert();
			System.out.println(javascriptAlert.getText()); // Get text on alert box
			if(Operation.equalsIgnoreCase("Accept")){
				javascriptAlert.accept(); 
			}
			else if(Operation.equalsIgnoreCase("Dismiss")){
				javascriptAlert.dismiss(); 
			}
			else return "Fail"; 
			return "Pass"; 
		}
		catch(Exception e)
		{
			return "Fail";
		}
	}











	/**
	 * 
	  	Method Name: EmailReport
 		Parameters: null
 		Return Type: String
 		Objective: Called in the end of the script to send an email repor to the desired recipients.  	
	 * 
	 **/	



	public String EmailReport() throws IOException{

		// Recipient's email ID needs to be mentioned.
		String to = "prathore@cpaglobal.com";

		String cc = "CFussell@cpaglobal.com";
		String cc1 = "XMorera@searchtechnologies.com";
		String cc2 = "EQuiros@searchtechnologies.com";
		String cc3 = "pmadriz@searchtechnologies.com";
		String cc4 = "kmartin@searchtechnologies.com";
		// Sender's email ID needs to be mentioned
		String from = "cpa.discover@gmail.com";

		// Assuming you are sending email from localhost
		String host = "jsy-einsmtp";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		try{
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO,
					new InternetAddress(to));

			message.addRecipient(Message.RecipientType.CC,
					new InternetAddress(cc));
			
			message.addRecipient(Message.RecipientType.CC,
					new InternetAddress(cc1));
			
			message.addRecipient(Message.RecipientType.CC,
					new InternetAddress(cc2));
			
			message.addRecipient(Message.RecipientType.CC,
					new InternetAddress(cc3));
			message.addRecipient(Message.RecipientType.CC,
					new InternetAddress(cc4));
			
			// Set Subject: header field
			message.setSubject("Automation Test Results");

			// Create the message part 
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart.setText("Attached are the execution results of automated functional tests on "+browserType+".    \n\n\n");

			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			String filename = System.getProperty("user.dir")+"\\src\\cpa\\qa\\xls\\Test Report.xlsx";
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts
			message.setContent(multipart );

			// Send message
			Transport.send(message);
			System.out.println("Automation run completed successfully....");
			return "Pass";
		}catch (MessagingException mex) {
			mex.printStackTrace();
			return "Fail";

		}
	}

	/**
	 * 
		 Method Name: VerifyExistence
		 Parameters: Element
		 Return Type: String
		 Objective: Verify existence of given element
	 * 
	 **/	

	public String VerifyExistence(String Element)
	{
		try
		{	
			boolean chk = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).isDisplayed();
			if (chk == true)
			{
				System.out.println(Element+" exists");
				return "Pass";
			}
			else 
			{
				System.out.println(Element+" does not exist");
				return "Fail";
			}

		}
		catch(NoSuchElementException ex)
		{	String Chk = IsPopUpPresent();
		if (Chk == "Pass")
		{
			System.out.println("Pop Up Present");
			return "Pass";
		}
		else if (Chk == "Fail")
		{
			return "Fail";
		}
		else
		{
			return "Fail";	
		}
		}
		catch(Exception e)
		{
			//				e.printStackTrace();
			return "Fail";
		}


	}


	/**
	 * 
		 Method Name: VerifyData
		 Parameters: Name
		 Return Type: String
		 Objective: Search the presence of desired publication number in result page.
	 * 
	 **/

	public String VerifyData(String Elementpath, String Name)
	{
		try
		{	
			java.util.List<WebElement> Allpubnumbr = driver.findElements(By.xpath(PropertiesUtil.getProperty(Elementpath)));

			for (int i=0; i<Allpubnumbr.size(); i++)
			{ //System.out.println(Allpubnumbr.get(i).getText());
				if(Allpubnumbr.get(i).getText().equalsIgnoreCase(Name) || Allpubnumbr.get(i).getText().contains(Name) )
				{	
					System.out.println("Match found : " +(Allpubnumbr.get(i).getText()));	    	
					return "Pass";
				} 
			}  
			System.out.println("Match not found");
			return "Fail";
		}
		catch(Exception e)
		{
			//			   e.printStackTrace();
			return "Fail";
		}
	}



	/**
	 * 
		 Method Name: EnterText
		 Parameters: FieldName, Text
		 Return Type: String
		 Objective: Enter 'Text' into given 'Field Name'
	 * 
	 **/
	public String EnterText(String FieldName, String Text)
	{
		try
		{	
			driver.findElement(By.xpath(PropertiesUtil.getProperty(FieldName))).clear();
			driver.findElement(By.xpath(PropertiesUtil.getProperty(FieldName))).sendKeys(Text);
			return "Pass";
		}
		catch (Exception e)
		{
			return "Fail";
		}
	}

	/**
	 * 
	  	 Method Name: SingleClick
	  	 Parameters: Element
	  	 Return Type: String
	  	 Objective: Clicks on given 'Element' if the element is displayed.
	 * 
	 * 
	 **/

	public String SingleClick(String Element)
	{
		try
		{ 
			if (driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).isDisplayed());
			
			{	System.out.println("");
				driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).click();
				Thread.sleep(6000);
			}
			return "Pass";

		}
		catch(NoSuchElementException ex)
		{	String Chk = IsPopUpPresent();
		if (Chk == "Pass")
		{
			System.out.println("Pop Up Present");
			return "Pass";
		}
		else if (Chk == "Fail")
		{
			return "Fail";
		}
		else
		{
			System.out.println("XXXXXXXXXXXXXXX");
			return "Fail";	
		}
		}
		catch(Exception e)
		{
			//				e.printStackTrace();
			return "Fail";
		}
	}

	/**
	 * 
	  	 Method Name: Click
	  	 Parameters: ElementName, Expected
	  	 Return Type: String
	  	 Objective: Click on given 'ElementName' and validate it by confirming the 'Expected' text after the click.
	 * 
	 * 
	 **/

	public String Click(String ElementName, String Verificationpath, String VerificationText)
	{
		try
		{	//System.out.println(VerificationText);
			driver.findElement(By.xpath(PropertiesUtil.getProperty(ElementName))).click();
			Thread.sleep(3000);
			if(ElementName.equals("Search_btn")){
			int a=1;
			while(a<15)
			{
				try{
						String text=driver.findElement(By.xpath(PropertiesUtil.getProperty("RP_ResultCount"))).getText();
						if(text.contains("many"))
						{
							Thread.sleep(1000);
							System.out.println("increamnted: "+a);
							a++;
						}
						else
							break;
					}
				
				catch(Exception e1){
				}
			}
			if(a==15){
				System.out.println("Search results are not found due to Application error");
			}
			}
			
			
			if (driver.findElement(By.xpath(PropertiesUtil.getProperty(Verificationpath))).isDisplayed());
			{
				String Text = driver.findElement(By.xpath(PropertiesUtil.getProperty(Verificationpath))).getText();  
				System.out.println(Text);
				//String Text1 = StringUtils.deleteWhitespace(Text);
				if(Text.contains(VerificationText))
				{
					System.out.println(VerificationText +" : Text Found");
					return "Pass";
				}
				else 
				{
					System.out.println(VerificationText+" : Text not found");
					return "Fail";
				}  
			}
		}
		catch(NoSuchElementException ex)
		{	String Chk = IsPopUpPresent();
		if (Chk == "Pass")
		{
			System.out.println("Pop Up Present");
			return "Pass";
		}
		else if (Chk == "Fail")
		{
			return "Fail";
		}
		else
		{
			System.out.println("XXXXXXXXXXXXXXX");
			return "Fail";	
		}
		}
		catch (Exception e)
		{
			//  				e.printStackTrace();
			return "Fail";
		}
	}


	
	/**
	 * 
		 Method Name: SetCheckbox
		 Parameters: ChkBoxName, State
		 Return Type: String
		 Objective: Check/Uncheck the selected 'Checkbox' to given 'State (On/Off)'
	 * 
	 **/

	public String SetCheckBox(String ChkBoxName, String State)
	{
		try
		{	
			System.out.println("Working with Chechbox: "+ChkBoxName);
			boolean chk=driver.findElement(By.xpath(PropertiesUtil.getProperty(ChkBoxName))).isSelected();
			//System.out.println("==>"+ chk);
			if (chk == true && State.equalsIgnoreCase("On"))
			{
				System.out.println(ChkBoxName+" chechbox is already set to "+ State);
			}
			else if (chk == true && State.equalsIgnoreCase("Off"))
			{
				driver.findElement(By.xpath(PropertiesUtil.getProperty(ChkBoxName))).click();
			}
			else if (State.equalsIgnoreCase("On") && chk == false )
			{
				driver.findElement(By.xpath(PropertiesUtil.getProperty(ChkBoxName))).click();
			}
			else if (State.equalsIgnoreCase("Off") && chk == false)
			{
				System.out.println(ChkBoxName+" chechbox is already set to "+ State);
			}

			return "Pass";
		}
		catch (Exception e)
		{
			//	  			e.printStackTrace();
			return "Fail";
		}
	}


	
	
	/**
	 * 
		 Method Name: ExtractDetails
		 Parameters: Type, Element, Option
		 Return Type: String
		 Objective: Extract the current value/state for the "Type" of 'Element' and compares it to the desired 'Option'
		 			Eg: Type: Dropdown, Element Name: AS_SearchAgainst_dd: Option: Current value of the dropdown
	 * 
	 **/
	
	public String ExtractDetails(String Type, String Element, String Option) {
		try {
			switch (Type) {
			case "Dropdown": {
				Select select = new Select(driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))));

				WebElement option = select.getFirstSelectedOption();
				System.out.println(option.getText());
				if (Option.equals(option.getText())) {
					System.out.println("Selected Option GetText:"+ option.getText());
					return "Pass";
				} else {
					System.out.println("Extracted Value:" + option.getText());
					System.out
							.println("Dropdown current value does not match with the provided Option");
					return "Fail";
				}
			}

			case "ChkBox": {
				System.out.println("Checking for " + Element+ " Checkbox's state");
				boolean chk = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).isSelected();

				if ((chk == true) && (Option.equalsIgnoreCase("On"))) {
					System.out.println(Element + "is Checked");
					return "Pass";
				} else if ((chk == false) && (Option.equalsIgnoreCase("Off"))) {
					System.out.println(Element + "is Unchecked");
					return "Pass";
				} else {
					System.out.println("Error Occured!");
					return "Fail";
				}
			}
			case "radioBtn": {
				System.out.println("Checking for " + Element
						+ " Radiobtn's state");
				boolean chk = driver.findElement(
						By.xpath(PropertiesUtil.getProperty(Element)))
						.isSelected();

				if ((chk == true) && (Option.equalsIgnoreCase("On"))) {
					System.out.println(Element + "is Checked");
					return "Pass";
				} else if ((chk == false) && (Option.equalsIgnoreCase("Off"))) {
					System.out.println(Element + "is Unchecked");
					return "Pass";
				} else {
					System.out.println("Error Occured!");
					return "Fail";
				}
			}
			default:
				throw new IllegalArgumentException();

			}

		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}
	}
	/**
	 * 
		 Method Name: SetDropdown
		 Parameters: DropdownName, Value
		 Return Type: String
		 Objective: Select given 'Value' in 'Drop down'
	 * 
	 **/

	public String SetDropdown(String DropdownName, String Option)
	{
		try
		{
			Select select = new Select(driver.findElement(By.xpath(PropertiesUtil.getProperty(DropdownName))));
			select.selectByVisibleText(Option);
			//document.getElementById('your_select_id').value;

			//sel.options[sel.selectedIndex].text;
			Thread.sleep(500);

			return "Pass";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "Fail";
		}
	}


	/**
	 * 
		 Method Name: SetRadio
		 Parameters: Radio Button Name: BtnName
		 Return Type: String
		 Objective: Select radio button/ Set the desired radio button as 'On'
	 * 
	 **/

	public String SetRadio(String BtnName)
	{
		try
		{	
			//Thread.sleep(1000);
			driver.findElement(By.xpath(PropertiesUtil.getProperty(BtnName))).click();
			return "Pass";
		}
		catch (Exception e)
		{
			return "Fail";
		}
	}


	/**
	 * 
		 Method Name: GetData
		 Parameters: FieldName
		 Return Type: String
		 Objective: Returns the data present in given 'FieldName'
	 * 
	 **/

	public String GetData(String FieldName)
	{
		try
		{
			Thread.sleep(1000);
			if (driver.findElement(By.xpath(PropertiesUtil.getProperty(FieldName))).isDisplayed());
			{

				String Text = driver.findElement(By.xpath(PropertiesUtil.getProperty(FieldName))).getText(); 
				System.out.println("Total count: "+ Text);
				String Text2 = StringUtils.substringBetween(Text,"of","results").trim();
				//Scanner in = new Scanner(Text2).useDelimiter("[^0-9]+");
				int count = Integer.parseInt(Text2);
				System.out.println("Total count: "+ count);

				if ((count != 0) && (driver.findElements(By.xpath(PropertiesUtil.getProperty("AllPubnumbr"))).size() != 0))
				{
					return "Pass";
				}
				else
				{
					return "Fail";
				}

			}
		}
		catch (Exception e)
		{
			return "Fail";
		}
	}



	/**
	 * 
		 Method Name: Navigate
		 Parameters: Name 
		 Return Type: String
		 Objective: Navigate user to desired page using Navigation menu.
	 * 
	 **/

	public String Navigate(String Element1 , String Element2) 
	{
		try 
		{

			try{
				if(driver.findElement(By.xpath("//*[@class='modal-header']")).isDisplayed())
					{
					driver.navigate().refresh();
					Thread.sleep(500);
					driver.findElement(By.xpath(PropertiesUtil.getProperty(Element1))).click();
					driver.findElement(By.xpath(PropertiesUtil.getProperty(Element2))).click();
					return "Pass";
					
				}
			}
			catch(NoSuchElementException ex)
			{
				driver.findElement(By.xpath(PropertiesUtil.getProperty(Element1))).click();
				driver.findElement(By.xpath(PropertiesUtil.getProperty(Element2))).click(); 
				Thread.sleep(350);	
			} return "Pass";
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();

			try {
				if (driver.findElement(By.xpath(PropertiesUtil.getProperty("UserName"))).isDisplayed());
				{	System.out.println("Application logged out abruptly, Forcing Login ");
				driver.findElement(By.xpath(PropertiesUtil.getProperty("UserName"))).clear();	
				driver.findElement(By.xpath(PropertiesUtil.getProperty("UserName"))).sendKeys("devtest");
				driver.findElement(By.xpath(PropertiesUtil.getProperty("Password"))).clear();
				driver.findElement(By.xpath(PropertiesUtil.getProperty("Password"))).sendKeys("Pass@word17");
				driver.findElement(By.xpath(PropertiesUtil.getProperty("Signin_btn"))).click();
				Thread.sleep(1000);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			} 
			return "Fail" ;
		}
	}

	/**
	 * 
		 Method Name: VerifyDate
		 Parameters: FromDate, ToDate, Elementname
		 Return Type: String
		 Objective: Verifies that Publication date, Application date and priority date of all records on QRI falls within a specified date range or not.
	 * 
	 **/

	public String VerifyDate( String FromDate, String ToDate, String Elementname ) throws Exception{


		try {  
			//Thread.sleep(2000);
			java.util.Date From;
			java.util.Date To;
			DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
			//Convert 'From Date' to YYYY-MM-DD format	

			if (FromDate.length()<5){
				From = df.parse((FromDate+"-01-01"));
				System.out.println(From + ".....");
			}
			else if (FromDate.length()< 8) {
				From = df.parse((FromDate+"-01"));
				System.out.println(From + ".....");
			}
			else {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
				From = df.parse(FromDate);
			}
			//Convert 'ToDate' to YYYY-MM-DD format              
			if (ToDate.length()<5){
				To = df.parse((ToDate+"-01-01"));
				System.out.println(From + "....." + To);
			}
			else if (ToDate.length()< 8) {
				To = df.parse((ToDate)+"-01");
				System.out.println(From + "....." + To);
			}
			else {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
				To = df.parse(ToDate);
			}
			//Compare given date with patent dates displayed on QRI.				
			//driver.findElement(By.xpath(PropertiesUtil.getProperty("RP_Expandall_lnk"))).click();
			//Thread.sleep(1000);
			java.util.List<WebElement> AllpubDate = driver.findElements(By.xpath(PropertiesUtil.getProperty(Elementname)));

			for (int i=1; i<AllpubDate.size() ; i++ ){
				java.util.Date ActualDate = df.parse(AllpubDate.get(i).getText());

				if ( ActualDate.compareTo(From)>0 && ActualDate.compareTo(To)<0 ) {
					System.out.println("Date verified :" +AllpubDate.get(i).getText() );
				}
				else {
					System.out.println("Failed for Date : "+AllpubDate.get(i).getText());
					return "Fail";
				}	
			} return "Pass";
		}
		catch (ParseException e) {
			//				e.printStackTrace();
			return "Fail";
		}
		catch(Exception e)
		{
			return "Fail";
		}


	} 



	/**
	 * 
		 Method Name: IsEnabled
		 Parameters: Element
		 Return Type: String
		 Objective: Verify if the given Element is displayed on the web page or not.
	 * 
	 **/

	public String IsEnabled(String Element)
	{
		try
		{
			if (driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).isEnabled())
			{
				System.out.println(Element+" is enabled");
				return "Pass";
			}
			else
			{
				return "Fail";
			}
		}
		catch(Exception e)
		{
			return "Fail";
		}
	}


	/**
	 * 
		 Method Name: GetText
		 Parameters: Element, DesiredText
		 Return Type: String
		 Objective: return pass if element text matches with the expected text.
	 * 
	 **/

	public String GetText(String Element, String DesiredText)
	{
		try
		{	
			String Text = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).getText();
			System.out.println("Desired Text: "+DesiredText);
			System.out.println("Text Found: "+Text);
			boolean chk = Text.isEmpty();
			if ((chk == false) && ((Text.contains(DesiredText))||(Text.toLowerCase().contains(DesiredText.toLowerCase()))))
			{
				return "Pass";
			}
			else
			{
				return "Fail";
			}
		}
		catch (Exception e)
		{
			return "Fail";
		}
	}


	/**
	 * 
		 Method Name: MatchCount
		 Parameters: Preference, Value
		 Return Type: String
		 Objective: Matches the actual and expected result count.
	 * 
	 **/



	public String MatchCount(String Preference, String Value)
	{
		try
		{
			switch(Preference)
			{
			case "Result":
			{
				Thread.sleep(3000);
				String ResultLine = driver.findElement(By.xpath(PropertiesUtil.getProperty("RP_Results_lbl"))).getText();
				//TBD: Use of ExtractString function:
				String Total = ResultLine.substring(10, 13);
				System.out.println("Result Per Page dropdown is set to: "+Value+ " records");

				if (Total.contains(Value))
				{
					System.out.println("Result count displayed: "+Total);
					return "Pass";
				}
				//			   		else
				//			   		{
				//			   			System.out.println("Error Occured!");
				//			   			return "Fail";
				//			   		}
				break;
			}
			case "PRDCount":
			{
				Thread.sleep(3000);
				String Count= ExtractString("PRD_RecordCount", 7, 11);
				if (Count.contains(Value))
				{
					System.out.println("Patent record displayed: "+Count);
					return "Pass";
				}

				else
				{
					System.out.println("Error Occured!");
				}
				break;
			}
			case "List":
			{	
				Thread.sleep(3000);
				java.util.List<WebElement> Ranks = driver.findElements(By.xpath(PropertiesUtil.getProperty("Ranks")));
				System.out.println("size is "+ Ranks.size());
				int val = Integer.parseInt(Value);
				if (Ranks.size() == val)
				{
					return "Pass";	
				}
				else
				{
					return "Fail";
				}
			}
			default:
				throw new IllegalArgumentException();

			}
			return "Pass";  
		}
		catch(Exception e)
		{
			return "Fail";
		}
	}

	//Private function that returns the substring from the Element's text by given Start & End index
	private String ExtractString(String Element, int Start, int End)
	{
		try
		{
			String ExtractedString = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).getText();
			String Total = ExtractedString.substring(Start, End);
			return Total;
		}
		catch (Exception e)
		{
			return "Fail";
		}
	}

	// Private function to verify & deal with pop up (if present)
	private String IsPopUpPresent ()
	{
		try
		{
			String Check = VerifyExistence(PropertiesUtil.getProperty("Modal_Dialog"));
			if (Check == "Pass")
			{
				// WebDriver popup = webDriver.switchTo().window(handle);
				String parentWindowHandle = driver.getWindowHandle();
				WebDriver popup = null;
				Iterator<String> windowIterator = (Iterator<String>) driver.getWindowHandles();
				while(windowIterator.hasNext()) 
				{ 
					String windowHandle = windowIterator.next(); 
					popup = driver.switchTo().window(windowHandle);
				}
			}
			else if (Check == "Fail")
			{
				return "Fail";
			}
			return "Pass";
		}
		catch (Exception e)
		{
			return "Fail";
		}
	}


	/**
	 * 
	  	 Method Name: DataNavigator
	  	 Parameters: NavigatorName, FilterType, NmbrofFilters
	  	 Return Type: String
	  	 Objective: Apply filters on provided data navigator (eg keyword, country, assignee etc)
	 * 
	 * 
	 **/

	public String DataNavigator(String NavigatorName, String FilterType, String NmbrofFilters ) throws Exception {


		try {
			Integer Initialcount = Resultcount(); 			//Gets initial count of number of records in QRI
			Integer TotalHitCount=0;
			int Type;
			int Number = Integer.parseInt(NmbrofFilters);
			System.out.println("Initial count :"+Initialcount);
			if ( FilterType.equalsIgnoreCase("Include")) {
				Type = 3;
			}
			else if (FilterType.equalsIgnoreCase("Exclude")) {
				Type = 4;
			}
			else {
				System.out.println("Invalid filter type ");
				return "Fail";
			}
			//System.out.println("Prepare list");
			//Apply Desired Filters
			//Thread.sleep(2000);
			java.util.List<WebElement> NmbrofRow = driver.findElements(By.xpath((PropertiesUtil.getProperty(NavigatorName))+("/tbody[*]")));
			ArrayList<Integer> RandomNumber = new ArrayList<Integer>() ;

			for(int j =0; j < NmbrofRow.size()-1; j++)
			{
				RandomNumber.add(j+1);
			}
			Collections.shuffle(RandomNumber);
			//System.out.println("Prepare Randomnumber");
			for (int i=0; i< Number ; i++){

				Thread.sleep(200);
				driver.findElement(By.xpath((PropertiesUtil.getProperty(NavigatorName))+("/tbody["+RandomNumber.get(i)+"]/tr/td["+Type+"]/input"))).click();    
				//System.out.println(Number);
				String Text = driver.findElement(By.xpath((PropertiesUtil.getProperty(NavigatorName))+("/tbody["+RandomNumber.get(i)+"]/tr/td[2]"))).getText();
				int Hitcount = Integer.parseInt(Text);
				TotalHitCount = TotalHitCount + Hitcount ;

			}

			System.out.println("Hitcount: "+ TotalHitCount);
			driver.findElement(By.xpath(PropertiesUtil.getProperty("RP_NV_Apply_btn"))).click();
			
			// Dynamic wait till navigator get applied			
			int b=1;
			while(b<10)
			{
				try{
					if(driver.findElement(By.xpath(PropertiesUtil.getProperty("RP_Navigator_UpdateNav_btn"))).isDisplayed())
						{
							Thread.sleep(1000);
							System.out.println("incremented: "+b);
							b++;
						}
						else
							break;
					}

				catch(Exception e1){
				}
			}
			if(b==10){
				System.out.println("Navigators are not updated due to Application error");
			}

	//Verify Filter is applied successfully or not				

			Integer B = Resultcount();
			Integer A = (Initialcount - TotalHitCount);
			System.out.println("Final Count :"+B+" (After applying "+FilterType+" Operation) ");

			if (Type==3 && ((TotalHitCount >= B)||(!TotalHitCount.equals(B)))){
				return "Pass";
			}
			else if (Type== 4 && (B < Initialcount) ){
				return "Pass";
			}
			else return "Fail";


		} catch (NumberFormatException e) {

			e.printStackTrace();
			return "Fail";
		}
		catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}
	}

	
	// Private function to get record count on QRI.   
	private int Resultcount() {

		try {
			String Text = driver.findElement(By.xpath(PropertiesUtil.getProperty("RP_ResultCount"))).getText(); 
			String Text2 = StringUtils.substringBetween(Text,"of","results").trim();
			int count = Integer.parseInt(Text2);
			// System.out.println("Total count: "+ count);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}


	/**
	 * 
		 Method Name: KeywordHighlight
		 Parameters: ElementPath, ElementName, Operation, NewName
		 Return Type: String
		 Objective: Perform operations like "Delete", "Edit", "toggle switch" on desired keyword.
	 * 
	 **/


	public String KeywordHighlight(String ElementPath, String ElementName, String Operation, String NewName){

		try
		{   //Thread.sleep(2000);
			java.util.List<WebElement> Allelements = driver.findElements(By.xpath(PropertiesUtil.getProperty(ElementPath)));

			for (int i=0; i<=Allelements.size(); i++)
			{	
				if(Allelements.get(i).getText().equalsIgnoreCase(ElementName))
				{	 i++ ;
				switch (Operation)
				{
				case "Delete":
				{    
					driver.findElement(By.xpath(".//*[@class='keyword-body container-fluid']/div["+i+"]/div[3]/a/i")).click();
					System.out.println("Keyword deleted");
					return "Pass";
				}
				case "Switch":
				{
					driver.findElement(By.xpath(".//*[@class='keyword-body container-fluid']/div["+i+"]/div[1]/a/span[1]")).click();
					System.out.println("Switch toggled   ");
					return "Pass";
				}
				case "Edit":
				{   
					// Functionality not implemented yet, Hence function for edit can be modified once it is implemented.
				}
				}
				}    

			} System.out.println("Match Failed ");
			return "Fail";
		}
		catch(Exception e)
		{
			//               e.printStackTrace();
			return "Fail";
		}
	}




	/**
	 * 
		 Method Name: VerifyHighlighting
		 Parameters: Color, Keyword, Location
		 Return Type: String
		 Objective: Function verify whether the keyword is highlighted with applied colour on QRI or keywords column itself.
	 * 
	 **/



	public String VerifyHighlighting(String Color, String Keyword, String Location ){

		try {
			switch (Color)
			{
			case "Orange": { Color = "E78D3B"; break ; }
			case "Pink":  { Color = "E47A7B"; break ; }
			case "Blue":{ Color = "4F8BC0"; break ; }
			case "Green":{ Color = "1A8B54"; break ; }
			case "Yellow":{ Color = "E8E24B"; break ; }
			case "Brown":{ Color = "8B522D"; break ; }
			case "Grey":{ Color = "A4A4A4"; break ; }
			}

			boolean chk ;
			if (Location.contains("ResultList")){
				chk = driver.findElement(By.xpath(".//em[@style='background-color: #"+Color+";' and text()='"+Keyword+"']")).isDisplayed();
				System.out.println("On Result List :"+ chk);
			}
			else if (Location.contains("ResultTools")){
				System.out.println(".//*[@title='#"+Color+"' and contains(String(),'"+Keyword+"')]");
				
				chk = driver.findElement(By.xpath(".//*[@title='#"+Color+"']/span[text()='"+Keyword+"']")).isDisplayed();
				System.out.println("On result tools : "+ chk);
			}
			else { chk =false; }

			if(chk == true){
				return "Pass";
			}
			else return "Fail";
		}
		catch (Exception e) {
			return "Fail";
		}
	}



	/**
	 * 
		 Method Name: SelectFolder
		 Parameters: RelativePath, FolderName
		 Return Type: String
		 Objective: Click on the desired folder from folder explorer
	 * 
	 **/


	public String SelectFolder(String RelativePath, String FolderName) {

		try
		{  	


			try
			{
				if(driver.findElement(By.xpath(".//*[@class='branch ng-scope active'][2]/span[1]/i[1]")).isDisplayed())	
				{
					driver.findElement(By.xpath(".//*[@class='branch ng-scope active'][2]/span[1]/i[1]")).click();
					Thread.sleep(3000);
				}
			}

			catch(Exception e)
			{

			}

			//Expand All folders 
			java.util.List<WebElement> TotalArrow = driver.findElements(By.xpath(PropertiesUtil.getProperty("PF_ExpandAllFolders")));
			for (int i=0; i<TotalArrow.size(); i++)
			{	
				if(TotalArrow.get(i).isDisplayed() == true )
				{	
					TotalArrow.get(i).click();
					//System.out.println("done arrow");
				} 
			}

			//Select the desired folder		 

			java.util.List<WebElement> TotalFolders = driver.findElements(By.xpath(PropertiesUtil.getProperty(RelativePath)));

			for (int i=0; i<=TotalFolders.size(); i++)
			{	//System.out.println(TotalFolders.get(i).getText());
				if(TotalFolders.get(i).getText().equalsIgnoreCase(FolderName))
				{	
					String NameOfFolder = TotalFolders.get(i).getText().trim();
					System.out.println("Folder found: " +NameOfFolder);	    	
					TotalFolders.get(i).click();
					Thread.sleep(2000);
					return "Pass";
				} 
			}

			System.out.println("Folder not found");
			return "Fail";
		}
		catch(Exception e)
		{
			//  e.printStackTrace();
			return "Fail";
		}

	}    




	// Public function to dismiss all errors displayed at top of the page.
	public String DismissErrors()
	{
		try
		{   boolean chk = driver.findElement(By.xpath(PropertiesUtil.getProperty("allErrors"))).isDisplayed();
		if (chk == true)
		{
			java.util.List<WebElement> Errors = driver.findElements(By.xpath(PropertiesUtil.getProperty("allErrors")));
			//				  System.out.println("Errors: "+Errors.size());
			for (int i =0; i<=Errors.size()-1;i++)
			{
				driver.findElement(By.xpath(PropertiesUtil.getProperty("Error"))).click();
			}
			return "Pass";
		}
		else if (chk == false)
		{
			System.out.println("No Errors displayed at top of the page");
			return "Pass";
		}
		return "Pass";   
		}
		catch (Exception e)
		{
			//			   e.printStackTrace();
			return "Fail";
		}
	}



	/**
	 * 
		Method Name: AlertOperation
		Parameters: ElementPath, ElementName, Operation
		Return Type: String
		Objective: Perform operations such as "Edit alert" , "Turn on" and "Turn off" alert for given Alert name.
	 * 
	 **/

	public String AlertOperation(String ElementPath, String ElementName, String OperationName)
	{	
		try
		{   
			//Thread.sleep(2000);
			java.util.List<WebElement> Allelements = driver.findElements(By.xpath(PropertiesUtil.getProperty(ElementPath)));

			for (int i=0; i<=Allelements.size(); i++)
			{	
				if(Allelements.get(i).getText().equalsIgnoreCase(ElementName))
				{		 
					System.out.println("Alert name is : " +(Allelements.get(i).getText()));
					i++ ;

					switch (OperationName)
					{	
					case "Edit":
					{	    
						driver.findElement(By.xpath(".//*[@id='wrap-inner']/div[2]/form/div[2]/div[2]/div/table/tbody["+i+"]/tr[1]/td[4]/div[1]/a")).click();
						// driver.findElement(By.xpath("AM_EditStart_lnk"+i+"AM_EditEnd_lnk")).click();
						System.out.println("Edit alert");
						return "Pass";
					}
					case "TurnOn":
					{
						//turn on path
					}


					}
				}
			}    
			System.out.println("Match is Failed");
			return "Fail";
		}
		catch(Exception e)
		{
			return "Fail";
		}
	}




	public String GetRichText(String Element, String DesiredText)
	{
		try
		{	
			WebElement text = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element)));
			String textagain = text.getAttribute("value");  
			System.out.println("Desired Text: "+DesiredText);
			System.out.println("Text Found: "+textagain);
			if (textagain.equalsIgnoreCase(DesiredText)||textagain.contains(DesiredText))
			{
				return "Pass";
			}
			else
			{
				return "Fail";
			}
		}
		catch (Exception e)
		{
			return "Fail";
		}
	}





	/**
	 * 
	   	Method Name: VerifyOptions
	   	Parameters: ElementPath, Options
	   	Return Type: String
	   	Objective: Perform verification of all options present in Dropdown.
	 * 
	 **/



	public String VerifyOptions(String ElementPath,String Options)
	{
		try {
			boolean chk1=false,chk2=true;
			List<WebElement> options =driver.findElements(By.xpath(PropertiesUtil.getProperty(ElementPath)));
			List<String> desiredText = null;
			//Change
			if(Options.contains(";"))
				desiredText = Arrays.asList(Options.split("\\s*;\\s*"));	
			else 
				desiredText = Arrays.asList(Options.split("\\s*,\\s*"));
			
			for(int index=0;index<desiredText.size();index++){
				if(desiredText.get(index).equals(options.get(index).getText()))
				{
					System.out.println("Option Value: "+options.get(index).getText());
					System.out.println("desiredText Value: "+desiredText.get(index));
					chk1=true;
				}    
				else{
					chk2=false;
				}
			}

			if(chk1 && chk2){
				return "Pass";
			}
			System.out.println("Dropdown current value does not match with the provided Option");
			return "Fail";
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}

	}







	/**
	 * 
	   	Method Name: VerifyDropDownOptions
	   	Parameters: ElementPath, DropdownOptions
	   	Return Type: String
	   	Objective: Perform verification of all options present in Dropdown.
	 * 
	 **/



	public String VerifyDropDownOptions(String ElementPath,String dropdownOptions)
	{
		try {
			List<WebElement> options =driver.findElements(By.xpath(PropertiesUtil.getProperty(ElementPath)+"/option"));
			Iterator<WebElement> itr = options.iterator();
			List<String> elementTexts = new ArrayList<String>();
			while (itr.hasNext()) {
				elementTexts.add(itr.next().getText().trim());
			}
			System.out.println("Element Text Values: "+elementTexts);
			List<String> desiredText = Arrays.asList(dropdownOptions.split("\\s*,\\s*"));
			System.out.println("Desired Text Values: "+desiredText);

			if(elementTexts.containsAll(desiredText))
			{
				return "Pass";
			}    
			else{
				System.out.println("Dropdown current value does not match with the provided Option");
				return "Fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}

	}






	/**
	 * 
	   	  	 Method Name: GetSearchBoxText
	   	  	 Parameters: Element, DesiredText
	   		 Return Type: String
	   		 Objective: return pass if element text matches with the expected text.
	 * 
	 **/	


	public String GetSearchBoxText(String Element, String DesiredText)
	{
		try
		{	
			WebElement object= driver.findElement(By.xpath(PropertiesUtil.getProperty(Element)));
			String searchtext=object.getAttribute("Value");
			System.out.println("Desired Text: "+DesiredText);
			System.out.println("Text Found: "+searchtext);
			boolean chk = searchtext.isEmpty();
			if ((chk == false) && (searchtext.contains(DesiredText)))
			{
				return "Pass";
			}
			else
			{
				return "Fail";
			}
		}
		catch (Exception e)
		{
			return "Fail";
		}
	}

	
	

	/**
	 *
	      Method Name: matchElementCount
	      Parameters: RelativePath, DesiredCount
	      Return Type: String
	      Objective: Match row count of a table with the desired count
	 *
	 **/

	public String matchElementCount(String RelativePath, String DesiredCount)
	{

		try {
			List<WebElement> rows = driver.findElements(By.xpath(PropertiesUtil.getProperty(RelativePath)));
			int rowCount = rows.size();

			String ActualCount = Integer.toString(rowCount);
			String DesiredCountFinal = DesiredCount;
			System.out.println(ActualCount);
			System.out.println(DesiredCountFinal);
			if (ActualCount.equals(DesiredCountFinal))
			{
				return "Pass";
			}
			else{
				return "Fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}
	}




	/**
	 * 
	   	Method Name: copyPasteText
	   	Parameters: Text to copy, TextBoxElement
	   	Return Type: String
	   	Objective: Perform copy and paste of text
	 *
	 **/


	public String copyPasteText(String TextBoxElement, String text)
	{
		try{
			driver.findElement(By.xpath(PropertiesUtil.getProperty(TextBoxElement))).clear();
			driver.findElement(By.xpath(PropertiesUtil.getProperty(TextBoxElement))).sendKeys(text);
			driver.findElement(By.xpath(PropertiesUtil.getProperty(TextBoxElement))).sendKeys(Keys.chord(Keys.CONTROL,"a"),Keys.chord(Keys.CONTROL,"c"));
			driver.findElement(By.xpath(PropertiesUtil.getProperty(TextBoxElement))).clear();
			WebElement TextBox = driver.findElement(By.xpath(PropertiesUtil.getProperty(TextBoxElement)));
			Actions action= new Actions(driver); 
			action.contextClick(TextBox).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
			return "Pass";
		}

		catch(Exception ex)
		{
			return "Fail";
		}
	}



	
	/**
	 * 
	   		Method Name: MatchResults
	   		Parameters: Element1, Element2
	   		Return Type: String
	   		Objective: Perform Result match for records on QRI page and Export page for a particular Search Keyword
	 * 
	 **/

	public String MatchResults(String Element1,String Element2)
	{
		try {
			String ResultLine = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element1))).getText();

			String Finalcount=ResultLine.replaceAll("\\D+","");
			System.out.println("Result displayed for the Search Keyword: "+Finalcount+ " records");
			String ResultToMatch = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element2))).getText();
			System.out.println("Records exported: "+ResultToMatch+ " records");

			if (Finalcount.contains(ResultToMatch))
			{
				System.out.println("Result count displayed: "+ResultToMatch+ "records");
				return "Pass";
			}
			else
			{
				System.out.println("Error Occured!");
				return "Fail";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}
	}





	/**
	 * 
	   		Method Name: CalenderWidgetVerification
	   		Parameters: M_Y_Element,D_Element, TextBoxPath, Date, Action
	   		Return Type: String
	   		Objective: Perform all CalenderWidgetVerification.
	 * 
	 **/

	public String CalenderWidgetVerification(String M_Y_Element,String D_Element,String TextBoxPath,String Date,String Action)
	{
		try {
			WebElement Inputbox =driver.findElement(By.xpath(PropertiesUtil.getProperty(TextBoxPath)));	

			String date=Date.substring(8);
			String month=Date.substring(5,7);
			String year=Date.substring(0,4);

			switch (Action)
			{
			case "WidgetVerify": 
			{ 
				Inputbox.clear();
				driver.findElement(By.xpath(".//*[@class='nav pull-right']/li[1]/a")).click();
				Inputbox.sendKeys(Date);
				try {Thread.sleep(2000);}catch(InterruptedException w){}
				if(M_Y_Element.contains("From")){
				driver.findElement(By.xpath(PropertiesUtil.getProperty("SS_Date_Options_From_Calender_YearRange"))).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath(PropertiesUtil.getProperty("SS_Date_Options_From_Calender_Month"))).click();
				Thread.sleep(1000);
				}
				else if(M_Y_Element.contains("To")){
					driver.findElement(By.xpath(PropertiesUtil.getProperty("SS_Date_Options_To_Calender_YearRange"))).click();
					Thread.sleep(1000);
					driver.findElement(By.xpath(PropertiesUtil.getProperty("SS_Date_Options_To_Calender_Month"))).click();
					Thread.sleep(1000);
				}
				WebElement Calender_M_Y =driver.findElement(By.xpath(PropertiesUtil.getProperty(M_Y_Element)));
				WebElement Calender_D =driver.findElement(By.xpath(PropertiesUtil.getProperty(D_Element)));
				//Pick Date, Month and Year From Given Date to match with Calender Widget
				if (Calender_D.getText().equals(date) && Calender_M_Y.getText().equals(monthInString(month)+" "+year))
					return "Pass";

			}break;

			case "CurrentDate": 
			{ 
				Inputbox.clear();
				driver.findElement(By.xpath(".//*[@class='nav pull-right']/li[1]/a")).click();
				Inputbox.click();
				if(M_Y_Element.contains("From")){
					driver.findElement(By.xpath(PropertiesUtil.getProperty("SS_Date_Options_From_Calender_YearRange"))).click();
					Thread.sleep(1000);
					driver.findElement(By.xpath(PropertiesUtil.getProperty("SS_Date_Options_From_Calender_Month"))).click();
					Thread.sleep(1000);
					}
					else if(M_Y_Element.contains("To")){
						driver.findElement(By.xpath(PropertiesUtil.getProperty("SS_Date_Options_To_Calender_YearRange"))).click();
						Thread.sleep(1000);
						driver.findElement(By.xpath(PropertiesUtil.getProperty("SS_Date_Options_To_Calender_Month"))).click();
						Thread.sleep(1000);
					}
				Calendar cal=Calendar.getInstance();
				int currenDate=cal.get(Calendar.DATE);
				int currentMonth=cal.get(Calendar.MONTH)+1;
				int currentYear=cal.get(Calendar.YEAR);
				try {Thread.sleep(2000);}catch(InterruptedException w){}
				
				WebElement Calender_M_Y =driver.findElement(By.xpath(PropertiesUtil.getProperty(M_Y_Element)));
				WebElement Calender_D =driver.findElement(By.xpath(PropertiesUtil.getProperty(D_Element)));
				System.out.println(monthInString(Integer.toString(currentMonth))+" "+Integer.toString(currentYear));
				if(Calender_D.getText().equals(Integer.toString(currenDate)) && Calender_M_Y.getText().equals(monthInString(Integer.toString(currentMonth))+" "+Integer.toString(currentYear)))
					return "Pass";

			}break;
			case "InputVerify": 
			{
				Inputbox.clear();
				driver.findElement(By.xpath(".//*[@class='nav pull-right']/li[1]/a")).click();
				Inputbox.sendKeys(Date);
				try {Thread.sleep(2000);}catch(InterruptedException w){}
				if(M_Y_Element.contains("From")){
					driver.findElement(By.xpath(PropertiesUtil.getProperty("SS_Date_Options_From_Calender_YearRange"))).click();
					Thread.sleep(1000);
					driver.findElement(By.xpath(PropertiesUtil.getProperty("SS_Date_Options_From_Calender_Month"))).click();
					Thread.sleep(1000);
					}
					else if(M_Y_Element.contains("To")){
						driver.findElement(By.xpath(PropertiesUtil.getProperty("SS_Date_Options_To_Calender_YearRange"))).click();
						Thread.sleep(1000);
						driver.findElement(By.xpath(PropertiesUtil.getProperty("SS_Date_Options_To_Calender_Month"))).click();
						Thread.sleep(1000);
					}
				WebElement Calender_D =driver.findElement(By.xpath(PropertiesUtil.getProperty(D_Element)));
				Calender_D.click();
				try {Thread.sleep(2000);}catch(InterruptedException w){}
				String a=Inputbox.getAttribute("value");
				System.out.println("Extracted date from Input box: "+a);
				if(Date.equals(a))
					return "Pass";
			}break;

			}
		}
		catch(Exception e){
			e.printStackTrace();
			return "Fail";
		}
		System.out.println("Calender Wigdet not working properly");
		return "Fail";

	}


	public String monthInString(String month){
		String monthString = null;
		int i=Integer.parseInt(month);
		switch(i){
		case 1: monthString="January"; break;
		case 2: monthString="February"; break;
		case 3: monthString="March"; break;
		case 4: monthString="April"; break;
		case 5: monthString="May"; break;
		case 6: monthString="June"; break;
		case 7: monthString="July"; break;
		case 8: monthString="August"; break;
		case 9: monthString="September"; break;
		case 10: monthString="October"; break;
		case 11: monthString="November"; break;
		case 12: monthString="December"; break;
		}
		return monthString;
	}





	/**
	 * 
	   		Method Name: NewWindowOperation
	   		Parameters: ElementPath, ElementName, Operation
	   		Return Type: String
	   		Objective: Perform operations such as "Edit alert" , "Turn on" and "Turn off" alert for given Alert name.
	 * 
	 **/


	public String NewWindowOperation(String ElementPath,String newWindowText){


		try {
			boolean chk = false;
			String parentWindowHandle = driver.getWindowHandle();
			for(String handle:  driver.getWindowHandles()){
				if(!handle.equals(parentWindowHandle)){
					driver.switchTo().window(handle);
					WebElement element=driver.findElement(By.xpath(PropertiesUtil.getProperty(ElementPath)));
					if(element.getText().trim().equals(newWindowText))
					{
						chk=true;					
					}
					break;
				}
			}
			//Close Child tab or Window

			if(chk==true)
			{
				driver.close();
				driver.switchTo().window(parentWindowHandle);
				return "Pass";
			}
			
			return "Fail";
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}
	}



	/**
	 * 
	   	 Method Name: FileDownloadPopUp
	   	 Parameters: 
	   	 Return Type: 
	   	 Objective: Handle Export File Download PopUp for Export Feature
	 * @throws AWTException 
	 * 
	 **/

	public String FileDownloadPopUp()
	{
		driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);


		try
		{
			Robot r=new Robot();
			r.keyPress(KeyEvent.VK_TAB);
			//	r.delay(1000);
			r.keyPress(KeyEvent.VK_TAB);
			//		r.delay(1000);
			r.keyPress(KeyEvent.VK_TAB);
			//	r.delay(1000);
			r.keyPress(KeyEvent.VK_TAB);
			//	r.delay(1000);
			r.keyPress(KeyEvent.VK_ENTER);

			return "Pass";

		}catch(AWTException e){
			return "Fail";
		}

	}

	/**
	 * 
		   	 Method Name: ListContainsDesiredText
		   	 Parameters: 
		   	 Return Type: 
		   	 Objective: Handle Export File Download PopUp for Export Feature
	 * @throws AWTException 
	 * 
	 **/

	/**
	 * 
		   	 Method Name: ListContainsDesiredText
		   	 Parameters: 
		   	 Return Type: 
		   	 Objective: Handle Export File Download PopUp for Export Feature
	 * @throws AWTException 
	 * 
	 **/

	public String ListHaveDesireTxt(String ListPath,String DesiredTxt)
	{ 		
		System.out.println("Desired Text is: "+DesiredTxt);
		boolean chk = true;
		try
		{	Thread.sleep(1000);
		List<WebElement> elements=driver.findElements(By.xpath(PropertiesUtil.getProperty(ListPath)));
		if(elements.size()==0)
			chk=false;
		else{
			for(int i=0;i<=elements.size()-1;i++)
			{
				System.out.println("Element Text: %s "+i+elements.get(i).getText());
				if(elements.get(i).getText().toLowerCase().contains(DesiredTxt.toLowerCase()) && !elements.get(i).getText().equals(""))
					chk=chk && true;
				else{
					System.out.println("Here "+i);
					chk=chk && false;
				}
			}
		}
		}catch(Exception e){
			e.printStackTrace();
			return "Fail";
		}
		System.out.println(chk);
		if(chk)
			return "Pass";
		return "Fail";

	}


	/**
	 * 
		   	 Method Name: Print Command
		   	 Parameters: 
		   	 Return Type: 
		   	 Objective: Handle Export File Download PopUp for Export Feature
	 * @throws AWTException 
	 * 
	 **/

	public String PrintCommand()
	{
		try{
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			Robot r=new Robot();
			System.out.println(driver.getWindowHandles());
			r.setAutoDelay(40);
			r.keyPress(KeyEvent.VK_ESCAPE);
			r.keyRelease(KeyEvent.VK_ESCAPE);
			return "Pass";
		}
		catch(Exception e){
			e.printStackTrace();
			return "Fail";
		}
	}


	/**
	 * 
		 Method Name: GetCount
		 Parameters: element Xpath
		 Return Type: String
		 Objective: Get the count of any list item.
	 * 
	 **/



	public String GetCount(String Element)
	{
		try
		{
			//Thread.sleep(2000);
			int rowCount= driver.findElements(By.xpath(PropertiesUtil.getProperty(Element))).size();
			System.out.println("Result count displayed: "+rowCount);
			return "Pass";
		}catch(Exception e)
		{
			return "Fail";
		}
	}


	/**
	 * 
		   	 Method Name: GetLocation
		   	 Parameters: Element
		   	 Return Type: 
		   	 Objective: 

	 **/
	public String GetLocation(String Element, String locationX, String locationY)
	{
		try
		{	DismissErrors();
		int LocationX =driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).getLocation().getX();
		int LocationY =driver.findElement(By.xpath(PropertiesUtil.getProperty(Element))).getLocation().getY();
		System.out.println("X expected location: "+LocationX);
		System.out.println("Y expected Location: "+LocationY);
		if(Integer.toString(LocationX).equals(locationX) && Integer.toString(LocationY).equals(locationY))
			return "Pass";

		}
		catch(Exception e){
			return "Fail";
		}
		return "Fail";
	}





	/**
	 * 
		   	 Method Name: DragAndDrop
		   	 Parameters: Element ,source, Target
		   	 Return Type: 
		   	 Objective: Drag a drop 

	 **/

	public String DragAndDrop(String ElementType, String Source, String Target)
	{
		switch(ElementType){
		case "Preference":
		{
			try
			{
				WebElement LocatorFrom =driver.findElement(By.xpath(PropertiesUtil.getProperty(Source)));
				WebElement LocatorTo =driver.findElement(By.xpath(PropertiesUtil.getProperty(Target)));
				driver.findElement(By.xpath(PropertiesUtil.getProperty("MP_Export_Setting_link"))).click();
				Thread.sleep(3000);
				String textBefore1=LocatorFrom.getText();
				String textBefore2=LocatorTo.getText();
				System.out.println("Element1 text before action: "+textBefore1);
				System.out.println("Element2 text before action: "+textBefore2);
				Actions clicker=new Actions(driver);
				Thread.sleep(5000);
				clicker.clickAndHold(LocatorFrom).moveToElement(LocatorTo, 1, 45).build().perform();
				((HasInputDevices) driver).getMouse().mouseUp(((Locatable)LocatorFrom).getCoordinates());
				Thread.sleep(5000);
				driver.findElement(By.xpath(PropertiesUtil.getProperty("MP_SaveAsDeafult_btn"))).click();
				Thread.sleep(2000);
				//				Navigate("NAV_Search", "NAV_Standard_Search");
				//				Thread.sleep(1000);

				driver.manage().deleteAllCookies();
				Thread.sleep(1000);
				closeBrowser();
				openBrowser();
				Launch("https://qa.discover.cpaglobal.com");
				Login("jthakur@cpaglobal.com", "L5*p~Q2A");
				Thread.sleep(2000);
				Navigate("NAV_Logged_in_as_UserName", "NAV_Manage_Preferences");
				Thread.sleep(1000);				
				driver.findElement(By.xpath(PropertiesUtil.getProperty("MP_Export_Setting_link"))).click();
				Thread.sleep(1000);

				String textAfter1=driver.findElement(By.xpath(PropertiesUtil.getProperty(Source))).getText();
				String textAfter2=driver.findElement(By.xpath(PropertiesUtil.getProperty(Target))).getText();
				System.out.println("Element1 text After action: "+textAfter1);
				System.out.println("Element2 text After action: "+textAfter2);
				if(textBefore1.equals(textAfter2) && textBefore2.equals(textAfter1))
					return "Pass";
				else
					return "Fail";

			}
			catch(Exception e){
				e.printStackTrace();
			}
		}break;
		case "PRD":
		{
			try
			{
				WebElement LocatorFrom =driver.findElement(By.xpath(PropertiesUtil.getProperty(Source)));
				WebElement LocatorTo =driver.findElement(By.xpath(PropertiesUtil.getProperty(Target)));
				//				driver.findElement(By.xpath(PropertiesUtil.getProperty("MP_Export_Setting_link"))).click();
				Thread.sleep(3000);
				String textBefore1=LocatorFrom.getText();
				String textBefore2=LocatorTo.getText();
				System.out.println("Element1 text before action: "+textBefore1);
				System.out.println("Element2 text before action: "+textBefore2);
				System.out.println(LocatorFrom.getLocation());
				System.out.println(LocatorTo.getLocation());
				Actions clicker=new Actions(driver);
				Thread.sleep(5000);
				clicker.clickAndHold(LocatorFrom).moveToElement(LocatorTo, 1, 12).build().perform();
				((HasInputDevices) driver).getMouse().mouseUp(((Locatable)LocatorFrom).getCoordinates());
				Thread.sleep(5000);
				driver.findElement(By.xpath(PropertiesUtil.getProperty("MP_SaveAsDeafult_btn"))).click();
				Thread.sleep(2000);

				driver.manage().deleteAllCookies();
				Thread.sleep(1000);
				closeBrowser();
				openBrowser();
				Launch("https://qa.discover.cpaglobal.com");
				Login("devtest", "Pass@word17");
				Thread.sleep(2000);
				Navigate("NAV_Search", "NAV_Advanced_Search");
				EnterText("AS_Pubnumbr_Txtbox","EP-1000048-B1");
				Click("Search_btn","RP_Pubnumbr_label","Publication Number");
				Click("RP_PubNum1_lnk","PRD_Backtoresults_lnk","Back to results");

				String textAfter1=driver.findElement(By.xpath(PropertiesUtil.getProperty(Source))).getText();
				String textAfter2=driver.findElement(By.xpath(PropertiesUtil.getProperty(Target))).getText();
				System.out.println("Element1 text After action: "+textAfter1);
				System.out.println("Element2 text After action: "+textAfter2);
				if(textBefore1.equals(textAfter2) && textBefore2.equals(textAfter1))
					return "Pass";
				else
					return "Fail";

			}
			catch(Exception e){
				e.printStackTrace();
			}
		}break;
		case "ModalOverlay":
		{

		}break;
		}
		return "Fail";
	}



	public String VerifySortedList(String Elements,String order)
	{
		try
		{
			switch(order){
			case "ASC":{
				List<WebElement> elements =driver.findElements(By.xpath(PropertiesUtil.getProperty(Elements)));
				Iterator<WebElement> itr = elements.iterator();
				List<String> elementTexts = new ArrayList<String>();
				while (itr.hasNext()) {
					elementTexts.add(itr.next().getText().trim());
				}
				System.out.println("Element Text Values before sorting: "+elementTexts);
				List<String> beforeSorting = new ArrayList<String>();
				for (String text : elementTexts) {
					beforeSorting.add(text);
				}
				Collections.sort(elementTexts);
				System.out.println("Sorted Element Values: "+elementTexts);
				if(beforeSorting.equals(elementTexts))
					return "Pass";
			}break;
			case "DESC":{
				List<WebElement> elements =driver.findElements(By.xpath(PropertiesUtil.getProperty(Elements)));
				Iterator<WebElement> itr = elements.iterator();
				List<String> elementTexts = new ArrayList<String>();
				while (itr.hasNext()) {
					elementTexts.add(itr.next().getText().trim());
				}
				System.out.println("Element Text Values before sorting: "+elementTexts);
				Collections.reverse(elementTexts);
				System.out.println("Element Text Values in reverse order: "+elementTexts);
				List<String> beforeSorting = new ArrayList<String>();
				for (String text : elementTexts) {
					beforeSorting.add(text);
				}
				Collections.sort(elementTexts);
				System.out.println("Sorted Element Values: "+elementTexts);
				if(beforeSorting.equals(elementTexts))
					return "Pass";
			}
			break;
			}
		}
		catch(Exception e){
			return "Fail";
		}
		return "Fail";
	}



	/**
	 * 
		   	 Method Name: MouseHover
		   	 Parameters: Element1, Element2, text
		   	 Return Type: 
		   	 Objective: 

	 **/
	public String MouseHover(String Element1, String Element2, String text)
	{
		try
		{
			switch(text)
			{
			case "Highlight":
			{
				WebElement LocationX =driver.findElement(By.xpath(PropertiesUtil.getProperty(Element1)));
				Actions builder=new Actions(driver);
				System.out.println("Color before Hover : "+LocationX.getCssValue("color"));
				builder.clickAndHold(LocationX).build().perform();
				Thread.sleep(3000);
				String colorValue=LocationX.getCssValue("color");
				System.out.println("Color After Hover : "+LocationX.getCssValue("color"));
				builder.release().build().perform();
				if(colorValue.equalsIgnoreCase(Element2))
					return "Pass";
			}break;
			default:
			{
				WebElement LocationX =driver.findElement(By.xpath(PropertiesUtil.getProperty(Element1)));
				Actions builder=new Actions(driver);
				builder.clickAndHold(LocationX).build().perform();
				Thread.sleep(3000);
				WebElement LocationY =driver.findElement(By.xpath(PropertiesUtil.getProperty(Element2)));
				String ActualText= LocationY.getText().trim();
				System.out.println("ActualText: "+ActualText);
				builder.release().build().perform();
				if(ActualText.equalsIgnoreCase(text) || ActualText.contains(text) )
					return "Pass";
			}
			}

		}
		catch(Exception e){
			e.printStackTrace();
			return "Fail";
		}
		System.out.println("3");
		return "Fail";
	}

	/**
	 * 
		   	 Method Name: GetCSSPropery
		   	 Parameters: Element1, CSSproperty, DesiredCSS
		   	 Return Type: 
		   	 Objective: 

	 **/
	public String GetCSSProperty(String Element1, String CSSproperty, String DesiredCSS)
	{
		try
		{

			WebElement LocationX =driver.findElement(By.xpath(PropertiesUtil.getProperty(Element1)));
			String val=LocationX.getCssValue(CSSproperty);
			System.out.println("CSS Value: "+val);
			if(val.equalsIgnoreCase(DesiredCSS))
				return "Pass";
		}
		catch(Exception e){
			return "Fail";
		}
		return "Fail";
	}

	/**
	 * 
		   	 Method Name: GetAttribute
		   	 Parameters: Element, Attribute, DesiredText
		   	 Return Type: 
		   	 Objective: 

	 **/
	public String GetAttribute(String Element,String Attribute, String DesiredText)
	{
		try
		{	
			WebElement text = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element)));
			String textagain = text.getAttribute(Attribute);  
			System.out.println("Desired Text: "+DesiredText);
			System.out.println("Text Found: "+textagain);
			if (textagain.equalsIgnoreCase(DesiredText) || textagain.contains(DesiredText))
			{
				return "Pass";
			}
			else
			{
				return "Fail";
			}
		}
		catch (Exception e)
		{
			return "Fail";
		}
	}

	/**
	 * 
			   	 Method Name: IsElementClickable
			   	 Parameters: Element
			   	 Return Type: 
			   	 Objective: Verifies that elements in a list are clickable

	 **/

	public String IsElementClickable (String Element)
	{
		boolean chk=true;
		try
		{
			List<WebElement> clickElementlist = driver.findElements(By.xpath(PropertiesUtil.getProperty(Element)));

			for (int i=0; i<clickElementlist.size(); i++)
			{
				if (clickElementlist.get(i).isEnabled())
				{
					System.out.println(Element+" is enabled");
					chk=chk&&true;
				}
				else 
					chk=chk&&false;
			}
			if(chk)
				return "Pass";

		}catch(Exception e)
		{return "Fail";}
		return "Fail";
	}

	/**
	 * 
			   	 Method Name: VerifyElementsExistence
			   	 Parameters: Element
			   	 Return Type: 
			   	 Objective: Verify Existence of multiple Elements in a list

	 **/

	public String VerifyElementsExistence (String Element)
	{
		boolean chk=true;
		try
		{
			List<WebElement> clickElementlist = driver.findElements(By.xpath(PropertiesUtil.getProperty(Element)));

			for (int i=0; i<clickElementlist.size(); i++)
			{
				if (clickElementlist.get(i).isDisplayed())
				{
					System.out.println(Element+" is displayed");
					chk=chk&&true;
				}
				else 
					chk=chk&&false;
			}
			if(chk)
				return "Pass";

		}catch(Exception e)
		{return "Fail";}
		return "Fail";
	}


	

	/**
	 * 
		 Method Name: SetMultipleCheckbox
		 Parameters: ChkBoxPath, State
		 Return Type: String
		 Objective: Check/Uncheck the selected 'Checkbox' to given 'State (On/Off)'
	 * 
	 **/

	public String SetMultipleCheckbox(String ChkBoxPath, String State)
	{
		try
		{	
			System.out.println("Working with Chechboxes: "+ChkBoxPath);
			List<WebElement> Checkboxlist=driver.findElements(By.xpath(PropertiesUtil.getProperty(ChkBoxPath)));
			for(int i=0;i<Checkboxlist.size();i++){

				//System.out.println("==>"+ chk);
				if (Checkboxlist.get(i).isSelected()==true && State.equalsIgnoreCase("On"))
				{
					System.out.println(ChkBoxPath+" chechbox is already set to...... "+ State);
				}
				else if (Checkboxlist.get(i).isSelected()==true&& State.equalsIgnoreCase("Off"))
				{
					Checkboxlist.get(i).click();
					System.out.println(ChkBoxPath+" chechbox is set to "+ State);
				}
				else if (State.equalsIgnoreCase("On") && Checkboxlist.get(i).isSelected()==false )
				{
					Checkboxlist.get(i).click();
					System.out.println(ChkBoxPath+" chechbox is set to "+ State);
				}
				else if (State.equalsIgnoreCase("Off") && Checkboxlist.get(i).isSelected()== false)
				{
					System.out.println(ChkBoxPath+" chechbox is already set to "+ State);
				}
			}
			Thread.sleep(3000);
		}
		catch (Exception e)
		{
			//	  			e.printStackTrace();
			return "Fail";
		}
		return "Pass";
	}

	
	
	
	
	
	/**
	 * 
	   	 Method Name: VerifyFieldQualifier
	   	 Parameters: 
	   	 Return Type: String
	   	 Objective: Apply all types of search term and perform search

	 **/
	public String VerifyFieldQualifier(String searchterm)
	{
		try
		{
			DismissErrors();
			Navigate("NAV_Search","NAV_Advanced_Search");
			System.out.println("Search Tearm: "+searchterm);
			EnterText("AS_KeywordTextArea", searchterm);
			Click("Search_btn","RP_Pubnumbr_label","Publication Number");
			String Text = driver.findElement(By.xpath(PropertiesUtil.getProperty("RP_ResultCount"))).getText(); 
			String Text2 = StringUtils.substringBetween(Text,"of","results").trim();
			int count = Integer.parseInt(Text2);
			Keywords k= Keywords.getInstance();
			k.getResultCount(Integer.toString(count));
			System.out.println("Total count: "+ count);
			if ((count != 0) && (driver.findElements(By.xpath(PropertiesUtil.getProperty("AllPubnumbr"))).size() != 0))
			{
				return "Pass";
			}

		}
		catch(Exception e){
			return "Fail";
		}
		return "Fail";
	}


	
	
	
	
	
	/**
	 * 
		 Method Name: VerifyParentFolder
		 Parameters: RelativePath, FolderName, ParentElementPath, ChildElementPath
		 Return Type: String
		 Objective: Click on the desired folder from folder explorer and verifies Parent and Child Elements
	 * 
	 **/


	public String VerifyParentFolder(String RelativePath, String FolderName, String ParentElementPath, String ChildElementPath) {

		try
		{  	
			//Expand All folders 		 
			java.util.List<WebElement> TotalArrow = driver.findElements(By.xpath(PropertiesUtil.getProperty("PF_ExpandAllFolders")));
			for (int i=0; i<TotalArrow.size(); i++)
			{	
				if(TotalArrow.get(i).isDisplayed() == true )
				{	
					TotalArrow.get(i).click();
					//System.out.println("done arrow");
				} 
			}

			//Select the desired folder		 

			List<WebElement> TotalFolders = driver.findElements(By.xpath(PropertiesUtil.getProperty(RelativePath)));
			int count = 0;
			for (int i=0; i<=TotalFolders.size(); i++)
			{	//System.out.println(TotalFolders.get(i).getText());
				if(TotalFolders.get(i).getText().equalsIgnoreCase(FolderName))
				{	
					System.out.println("Folder found: " +TotalFolders.get(i).getText());	    	
					//TotalFolders.get(i).click();
					List<WebElement> Parent=driver.findElements(By.xpath(PropertiesUtil.getProperty(ParentElementPath)));
					for(WebElement ele:Parent){
						if(ele.isDisplayed())
						{
						count++;
						System.out.println("Parent Folders are: " +ele.getText());
					}
					}
					List<WebElement> Child=driver.findElements(By.xpath(PropertiesUtil.getProperty(ChildElementPath)));
					for(WebElement el:Child){
					if(el.isDisplayed())
					{
						count++;
						System.out.println();
						System.out.println("Child Folders are: " +el.getText());
					}
					}
//					System.out.println("Folders are Parent Folders");

					Thread.sleep(2000);
					return "Pass";
				} 
			}

			System.out.println("Folder not found");
			return "Fail";
		}
		catch(Exception e)
		{
			//  e.printStackTrace();
			System.out.println("Failed due to Exception");
			return "Fail";
		}

	}
	
	
	
	
	
	/**
	 * 
		Method Name: CompareData 
		Parameters: Element1,Element2,Element3,Element4,Element5
		Return Type: String
		Objective: Compares the data on one page to Page
	 * @throws Exception 
	 * @throws Exception 

	 **/

	public String CompareData(String Element1, String Element2, String Element3, String Element4, String Element5)
	{
		try
		{
		List<WebElement> DB_Recent=driver.findElements(By.xpath(PropertiesUtil.getProperty(Element1)));
		String DB_Recent_Data=null;
		for(WebElement el1:DB_Recent)
		{
			DB_Recent_Data+=el1.getText();

		}
		System.out.println("Data on First Page is: " +DB_Recent_Data);

		driver.findElement(By.xpath(PropertiesUtil.getProperty(Element2))).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(PropertiesUtil.getProperty(Element3))).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(PropertiesUtil.getProperty(Element4))).click();

		List<WebElement> AS_Recent=driver.findElements(By.xpath(PropertiesUtil.getProperty(Element5)));
		String AS_Recent_Data=null;
		for(WebElement el2:AS_Recent)
		{
			AS_Recent_Data+=el2.getText();
		}
		System.out.println("Data on Second Page is: " +AS_Recent_Data);


		if(DB_Recent_Data.equalsIgnoreCase(AS_Recent_Data))
		{
			System.out.println("Data on First Page matches with Data on Second Page");
			return "Pass";
		}
		System.out.println("Data on First Page doesnot match with Data on Second Page");
		return "Fail";
	}	catch(Exception e)
	{
		System.out.println("Failed due to Exception");
		return "Fail";
	}
}
	
	
	
	/**
	 * 
		   	 Method Name: Security PopUp
		   	 Parameters: 
		   	 Return Type: 
		   	 Objective: Handle Export File Download PopUp for Export Feature
	 * @throws AWTException 
	 * 
	 **/

	public String SecurityPopUP()
	{

		try{
			Robot r=new Robot();
			Alert alert = driver.switchTo().alert();
			alert.accept();
			Thread.sleep(5000);
			r.keyPress(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			r.keyPress(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			r.keyPress(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			r.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(5000);
			return "Pass";
		}
		catch(Exception e){
			e.printStackTrace();
			return "Fail";
		}
	}
	
	
	/**
	 * 
		   	 Method Name: MouseDrag
		   	 Parameters: Element1, Element2
		   	 Return Type: String
		   	 Objective: Drag the Mouse cursor from one location to another in navigators
	 *
	 **/
	public String MouseDrag(String Element1, String Element2)
	{
		try
		{
			
				WebElement LocationX =driver.findElement(By.xpath(PropertiesUtil.getProperty(Element1)));
				WebElement LocationY =driver.findElement(By.xpath(PropertiesUtil.getProperty(Element2)));
				Actions builder=new Actions(driver);
				builder.clickAndHold(LocationX).build().perform();
				Thread.sleep(3000);
				
				builder.release(LocationY).build().perform();
				Thread.sleep(3000);
				return "Pass";
		}
		catch(Exception e){
			e.printStackTrace();
			return "Fail";
		}
	}
	
	
	
	/**
	 * 
		   	 Method Name: ClickOnDesiredLink
		   	 Parameters: Element1, Element2
		   	 Return Type: 
		   	 Objective: Handle Export File Download PopUp for Export Feature
	 * @throws AWTException 
	 * 
	 **/

	public String ClickOnDesiredLink(String Element1,String Element2)
	{
		try{
			int i=1;
			while(i<=10)
			{
				try{
					WebElement element1=driver.findElement(By.xpath(PropertiesUtil.getProperty(Element1).replaceAll("%", Integer.toString(i))));
					if(element1.isDisplayed()){
						System.out.println("Desire link found on: "+i+"th row");
						try{
						WebElement element2=driver.findElement(By.xpath(PropertiesUtil.getProperty(Element2).replaceAll("%", Integer.toString(i))));
						if(element2.isDisplayed())
						{
						element2.click();
						Thread.sleep(4000);
						return "Pass";
						}
						else{
							System.out.println("Desire link not found");
						}
						}
						catch(NoSuchElementException e){
							System.out.println("Desire link not found");
						}
						
						break;
					}
					
				}
				catch(Exception e){
					System.out.println("Row not found yet");
				}
				i++;
			}
			if(i==11)
				return "Fail";
			
			return "Pass";
		}
		catch(Exception e){
			e.printStackTrace();
			return "Fail";
		}
	}
	

	
	
	
	/**
	 * 
		   	 Method Name: VerifyDesiredLinks
		   	 Parameters: Element1, Element2, Element 3
		   	 Return Type: 
		   	 Objective: Verify elements or links corresponding to queries or values in a tables
	 * @throws AWTException 
	 * 
	 **/
	
	
	public String VerifyDesiredLinks(String Element1,String Element2, String Element3)
	{
		try{
			int i=1;
			while(i<=10)
			{
				try{
					WebElement element1=driver.findElement(By.xpath(PropertiesUtil.getProperty(Element1).replaceAll("%", Integer.toString(i))));

					if(element1.isDisplayed()){
						System.out.println("Desire link found on: "+i+"th row");
						WebElement element2=driver.findElement(By.xpath(PropertiesUtil.getProperty(Element2).replaceAll("%", Integer.toString(i))));
						String element2Text=element2.getText();
						System.out.println(element2Text);
						if(element2Text.contains(Element3))
						{
							return "Pass";
						}
						break;
					}
				}
				catch(Exception e){
					System.out.println("Element not found yet");
				}
				i++;
			}
			if(i==11)
				return "Fail";
			
			return "Pass";
		}
		catch(Exception e){
			e.printStackTrace();
			return "Fail";
		}
	}
	
	
	
	
	/**
	 * 
		   	 Method Name: ClickForDownload
		   	 Parameters: Element1,
		   	 Return Type: 
		   	 Objective: Handle download window on various browsers
	 * @throws AWTException 
	 * 
	 **/
	
	
	
	public String ClickForDownload(String Element1)
	{
		try{
			WebElement element=driver.findElement(By.xpath(PropertiesUtil.getProperty(Element1)));

			if(browserType.equals("Mozilla"))
			{
				element.click();
				try {
					JavascriptAlert("Accept");
					System.out.println("Alert window found");
				} catch (Throwable e) {
				}
				FileDownloadPopUp();
				return "Pass";
			}
			else if(browserType.equals("Chrome"))
			{
				element.click();
				try {
					JavascriptAlert("Accept");
					System.out.println("Alert window found");
				} catch (Throwable e) {
				}
				return "Pass";
			}
			else if(browserType.equals("IE"))
			{
				System.out.println("Browser is: "+browserType);

				try{
					if(driver.findElement(By.xpath(PropertiesUtil.getProperty("AS_Cross_Btn"))).isDisplayed()){
						WebElement element2=driver.findElement(By.xpath(PropertiesUtil.getProperty("AS_Cross_Btn")));
						element2.click();
					}

				}
				catch(Exception e){
				}
				return "Fail";
			}
			return "Fail";
		}
		catch(Exception e){
			return "Fail";
		}

	}
	
	
	
	
	
	/**
	 * 
		   	 Method Name: ClearTextbox
		   	 Parameters: TextBoxElement
		   	 Return Type: 
		   	 Objective: To clear a textbox/ inputbox
	 * @throws AWTException 
	 * 
	 **/
	public String ClearTextbox(String TextBoxElement)
	{


		try{
			driver.findElement(By.xpath(PropertiesUtil.getProperty(TextBoxElement))).clear();
			return "Pass";
		}


		catch(Exception e){
		
		return "Fail";
	}

	}
	
	
	
	
	/**
	 * 
		   	 Method Name: ClickForPrint
		   	 Parameters: Element1
		   	 Return Type: 
		   	 Objective: To cancel or Accept Windows Print Popup
	 * @throws AWTException 
	 * 
	 **/
	
	
	public String ClickForPrint(String Element1)
	{
		try{
			WebElement element=driver.findElement(By.xpath(PropertiesUtil.getProperty(Element1)));

			if(browserType.equals("Mozilla"))
			{
				System.out.println("Browser is: "+browserType);
				element.click();
				FilePrintPopUp();
				return "Pass";
			}

			else if(browserType.equals("IE"))
			{
				System.out.println("Browser is: "+browserType);
				element.click();
				FilePrintPopUp();
				return "Pass";
			}

			else if(browserType.equals("Chrome"))
			{
				System.out.println("Browser is: "+browserType);
				element.click();
				FilePrintPopUpChrome();
				return "Pass";

			}
			return "Pass";
			}

			catch(Exception e){
				return "Fail";
			}

		}
	
	public String FilePrintPopUpChrome()
	{
	driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);

	try
	{
		Robot r = new Robot();
		r.delay(3000);
		r.keyPress(KeyEvent.VK_TAB);
		r.keyRelease(KeyEvent.VK_TAB);
		r.keyPress(KeyEvent.VK_ENTER);									
		return "Pass";
	}

	catch(AWTException e){
		return "Fail";
	}

}
	
	
	public String FilePrintPopUp()
	{
		driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);


		try
		{
			Robot r = new Robot();
			r.delay(4000);
			r.keyPress(KeyEvent.VK_ESCAPE);	
			r.keyRelease(KeyEvent.VK_ESCAPE);
			return "Pass";
		}

		catch(AWTException e){
			return "Fail";
		}

	}
	



	/**
	 * 
		   	 Method Name: SwitchWindows
		   	 Parameters: Window
		   	 Return Type: 
		   	 Objective: To switch between Main window and Child Window
	 * 		@throws AWTException 
	 * 
	 **/
	public String SwitchWindows(String Window){

		try {

			switch(Window)
			{
			case "Child":
			{
				String parentWindowHandle = driver.getWindowHandle();
				for(String handle:  driver.getWindowHandles()){
					if(!handle.equals(parentWindowHandle))
					{
						driver.switchTo().window(handle);
						return "Pass";
					}	break;

				}
			}
			case "Main":
			{
				String childWindowHandle = driver.getWindowHandle();
				for(String handle:  driver.getWindowHandles()){
					if(!handle.equals(childWindowHandle))
					{
						driver.switchTo().window(handle);
						return "Pass";
					}	break;

				}
			}
			}	

		}
		catch(Exception e){
			e.printStackTrace();
			return "Fail";
		}
		return "Pass";
	}

	
	
	
	
	/**
	 * 
	  	 Method Name: ScrollbarPosition
	  	 Parameters: Desired Text
	  	 Return Type: String
	  	 Objective: Clicks on given 'Element' if the element is displayed.
	 * 
	 * 
	 **/
	
	public String ScrollbarPosition(String DesiredText)
	{
		try{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		String a=js.executeScript("document.getScroll= function(){if(window.pageYOffset!= undefined){return [pageYOffset];}else{var sx, sy, d= document, r= d.documentElement, b= d.body; sx= r.scrollLeft || b.scrollLeft || 0; sy= r.scrollTop || b.scrollTop || 0; return [sy]; }};return document.getScroll()").toString();
		String finalString=a.replaceAll("^\\[|\\]$", "");
		System.out.println("scroll position: "+finalString);
		
		if(finalString.contains(DesiredText))
		{
			System.out.println("Scrollbar Position moved to" +finalString+ "position");
			return "Pass";
		}
				return "Fail";
		}
		catch(Exception exception){
			exception.printStackTrace();
			return "Fail";
		}
	}


	
	
	
	/**
	 * 
			   	 Method Name: TextShouldPresent
			   	 Parameters: Element
			   	 Return Type: 
			   	 Objective: Verifies that elements text in a list is present

	 **/

	public String TextShouldPresent(String Element)
	{
		boolean chk=true;
		try
		{
			List<WebElement> clickElementlist = driver.findElements(By.xpath(PropertiesUtil.getProperty(Element)));
			int i;
			for (i=0; i<clickElementlist.size(); i++)
			{
				if (!clickElementlist.get(i).getText().isEmpty())
				{
					chk=chk&&true;
				}
				else 
				{
					System.out.println("String is Empty on "+i+"th row, text is: "+clickElementlist.get(i).getText());
					chk=chk&&false;
				}
			}
			if(chk)
				return "Pass";

		}catch(Exception e)
		{return "Fail";}
		return "Fail";
	}



	

	/**
	 * 
			   	 Method Name: StringFormatMatcher
			   	 Parameters: Element
			   	 Return Type: 
			   	 Objective: Verifies that elements text in a list is present

	 **/

	public String StringFormatMatcher(String Element, String DesiredFormat)
	{
		boolean chk=true;
		try
		{
			List<WebElement> clickElementlist = driver.findElements(By.xpath(PropertiesUtil.getProperty(Element)));
			int i;
			for (i=0; i<clickElementlist.size(); i++)
			{
				if (!clickElementlist.get(i).getText().matches(DesiredFormat))
				{
					chk=chk&&true;
				}
				else {
					System.out.println("String is not in desire format on "+i+"th row, text is: "+clickElementlist.get(i).getText());
					chk=chk&&false;
				}
			}
			if(chk)
				return "Pass";

		}catch(Exception e)
		{return "Fail";}
		return "Fail";
	}

	
	
	/**
	 * 
			   	 Method Name: Tooltip
			   	 Parameters: HoverElement, Tootltip, Text
			   	 Return Type: 
			   	 Objective: Verify the text in tool tip

	 **/
	
	public String Tooltip(String HoverElement,String Tootltip, String Text)
	{
		try{
			WebElement LocatorFrom =driver.findElement(By.xpath(PropertiesUtil.getProperty(HoverElement)));

			Actions clicker=new Actions(driver);
			Thread.sleep(2000);
			clicker.clickAndHold(LocatorFrom).moveToElement(LocatorFrom, 20, 0).build().perform();
			Thread.sleep(5000);
			((HasInputDevices) driver).getMouse().mouseUp(((Locatable)LocatorFrom).getCoordinates());
			Thread.sleep(5000);
			WebElement LocatorTooltip =driver.findElement(By.xpath(PropertiesUtil.getProperty(Tootltip)));
			String tootlipText=LocatorTooltip.getText();
			System.out.println("Tooltip text: "+tootlipText);
			if(tootlipText.equalsIgnoreCase(Text) ||tootlipText.contains(Text))
					return "Pass";
		}
		catch(Exception e)
		{return "Fail";}
		return "Fail";
	}
	
	
	
	
	/**
	 * 
		   	 Method Name: VerifySelectDeselectAll
		   	 Parameters: Element1, Element2
		   	 Return Type: 
		   	 Objective: To verify select all, de-select all feature for multiple check boxes.
	 * 		@throws AWTException 
	 * 
	 **/
	public String VerifySelectDeselectAll(String Element, String Option)
	{
		try
		{
			List<WebElement> chkboxes = driver.findElements(By.xpath(PropertiesUtil.getProperty(Element).replaceAll("%", "*")));
			int count = chkboxes.size();
			Boolean chk1 =true;Boolean chk2 =false;
			for(int i=1; i<=count; i++)
			{
				System.out.println("Checking for "+Element+" Checkbox's state");
				WebElement element1=driver.findElement(By.xpath(PropertiesUtil.getProperty(Element).replaceAll("%", Integer.toString(i))));
				chk1 = chk1 && element1.isSelected();
				chk2 = chk2 || element1.isSelected();

			}
			if (Option.equals("checked")) 
			{
				if(chk1)
					System.out.println("All Checkboxes are Checked");
				return "Pass";
			}
			else if (Option.equals("unchecked"))
			{
				if(!chk2)
					System.out.println("All Checkboxes are UnChecked");
				return "Pass";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();

		}
		return "Fail";
	}
	
	
	/**
	 * 
	   	Method Name: VerifyOrderOfElements
	   	Parameters: ElementPath1, ElementPath2
	   	Return Type: String
	   	Objective: Perform Order Of Elements.
	 * 
	 **/



	public String VerifyOrderOfElements(String ElementPath1,String ElementPath2)
	{
		try {
			boolean chk1=true,chk2=true;
			List<WebElement> options1 =driver.findElements(By.xpath(PropertiesUtil.getProperty(ElementPath1)));
			List<WebElement> options2 =driver.findElements(By.xpath(PropertiesUtil.getProperty(ElementPath2)));
			if(options1.size()!=options2.size())
				return "Fail";
			for(int i=0;i<options1.size();i++){
					if(!options1.get(i).getText().equals(options2.get(i).getText())){
						System.out.println("Values are not equal for element 1: "+options1.get(i).getText());
						System.out.println("Values are not equal for element 2: "+options2.get(i).getText());
						chk1=false;
					}
				}

			if(chk1)
			return "Fail";
			else 
				return "Pass";
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}

	}
	
	/**
	 * 
	   	Method Name: copyPasteTextFromFile
	   	Parameters: Text to copy, TextBoxElement
	   	Return Type: String
	   	Objective: Perform copy and paste of text
	 *
	 **/


	
	public String copyPasteTextFromFile(String TextBoxElement)
	{
		try{
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec("C:\\Windows\\notepad.exe C:\\Users\\viskumar\\WorkSpace\\Discover\\src\\cpa\\qa\\xls\\File\\username.txt");
			Robot r=new Robot();
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_A);
			r.keyRelease(KeyEvent.VK_A);
			r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_C);
			r.keyRelease(KeyEvent.VK_C);
			r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_ALT);
			r.keyPress(KeyEvent.VK_F4);
			r.keyRelease(KeyEvent.VK_F4);
			r.keyRelease(KeyEvent.VK_ALT);
			driver.findElement(By.xpath(PropertiesUtil.getProperty(TextBoxElement))).sendKeys(Keys.chord(Keys.CONTROL,"v"));
			
			return "Pass";
		}catch(Exception e)
		{	e.printStackTrace();
		return "Fail";
		}
		
		}
	
	private void type(String s) throws AWTException {
		byte[] bytes = s.getBytes();
		for (byte b : bytes) {
			Robot robot = new Robot();
			// System.out.println("Codes: "+ b);
			int code = b;
			// keycode only handles [A-Z] (which is ASCII decimal [65-90])
			if (code > 96 && code < 127) {
				code = code - 32;

				robot.delay(10);
				robot.keyPress(code);
				robot.keyRelease(code);
			} else if (Byte.toString(b).equals("92")
					|| Byte.toString(b).equals("46")) {
				// System.out.println(Byte.toString(b));
				robot.delay(10);
				robot.keyPress(code);
				robot.keyRelease(code);
			} else if (Byte.toString(b).equals("58")) {
				robot.setAutoDelay(10);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_SEMICOLON);
				robot.keyRelease(KeyEvent.VK_SEMICOLON);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (Byte.toString(b).equals("95")) {
				robot.setAutoDelay(10);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_MINUS);
				robot.keyRelease(KeyEvent.VK_MINUS);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			}
		}
	}

	
	
	
	
	/**
	 * 
	   	 Method Name: FileUpload
	   	 Parameters: Element, Filepath
	   	 Return Type: String
	   	 Objective: Apply all types of search term and perform search

	 **/
	public String FileUpload(String Element1, String Sheet_Name) {
		try {
			String Filepath=(System.getProperty("user.dir")).toLowerCase()+"\\testdata\\"+Sheet_Name;
			WebElement ClickBrowse = driver.findElement(By.xpath(PropertiesUtil.getProperty(Element1)));
			ClickBrowse.click();
			Robot robot = new Robot();
			robot.setAutoDelay(100);
			type(Filepath);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			return "Pass";
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}
	}
	
	
	
	
	/**
	 * 
		 Method Name: Wait
		 Parameters: Element
		 Return Type: String
		 Objective: Verify existence of given element
	 * 
	 **/	

	public String Wait(String ms)
	{
		try
		{	
			Thread.sleep(Long.parseLong(ms));
			return "Pass";
		}
		catch(Exception e){
			return "Fail";
		}
	}
	
	
	
	


public String DeleteAllKeyword()
	{

		List<WebElement> keywords = driver.findElements(By.xpath(".//*[@ng-click='deleteKeyword($index)']"));

		try {
			int KeyCount = keywords.size();

			for(int i=1;i<=KeyCount;i++)
			{
				driver.findElement(By.xpath(PropertiesUtil.getProperty("RP_KeyHigh_Delete_First"))).click();
			}
			return "Pass";
		}

		catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}
	}








public String JumptoLastPage()
	{
		try
		{
			String totalPages= driver.findElement(By.xpath(PropertiesUtil.getProperty("RP_Pages_Total"))).getText().substring(10);
			System.out.println(totalPages);
			driver.findElement(By.xpath(PropertiesUtil.getProperty("RP_Jumptopage_txt"))).sendKeys(totalPages);
			driver.findElement(By.xpath(PropertiesUtil.getProperty("RP_Jumptopage_Gobtn"))).click();
			String ActivePageafterJumpto = driver.findElement(By.xpath(PropertiesUtil.getProperty("RP_Actv_Page_lnk"))).getText();

			if(ActivePageafterJumpto.equals(totalPages))
				return "Pass";

			else
				return "Fail";
		}

		catch(Exception e)
		{
			return "Fail";
		}

	}

} // Parenthesis to end class 'Functions'
