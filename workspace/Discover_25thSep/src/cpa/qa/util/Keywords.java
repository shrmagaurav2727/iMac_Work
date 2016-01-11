package cpa.qa.util;


import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;

import cpa.qa.library.Functions;

public class Keywords {

	// global variables
	//		Properties OR=null;
	WebDriver driver=null;
	//public int rowid=1;
	static Keywords k;
	WebDriver chrome_bak; 
	WebDriver moz_bak;
	Reporting rep=new Reporting();
	public static String count;
	public static String sDate;
	
	public Keywords() {
		try{

		}catch(Exception e){
			System.out.println("File not found ");
		}
	}

	public static Keywords getInstance(){

		if(k==null)
			k=new Keywords();
		return k;
	}



	public static void main(String[] arg) throws Exception{

	}

	public void executeTest(String testName,Xls_Reader xls,Hashtable<String,String> testData) throws Throwable{
		int rowid=0;
		Functions fp = new Functions();
		String filename=System.getProperty("user.dir")+"\\src\\cpa\\qa\\xls\\Test Report.xlsx";
		//System.out.println("filename and test name are : "+filename+testName);
		rep.GenerateReport(filename,testName);
		//CreateExcel(filename,testName);
		int rows = xls.getRowCount("Test Cases");
		String result=null;
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//java.util.Date date= new java.util.Date();
		//String sDate = formatter.format(date);

		System.out.println("************* Execution started for test case : " +testName +" *************");   

		for(int rNum=2;rNum<=rows;rNum++){

			String tcid = xls.getCellData("Test Cases", "TCID", rNum);
			String tcase = xls.getCellData("Test Cases", "TestCase", rNum);
			java.util.Date date= new java.util.Date();
			sDate = formatter.format(date);

			if(tcid.equals(testName)){
				System.out.println();

				String keyword=xls.getCellData("Test Cases", "Keyword", rNum);
				String Arg1=testData.get(xls.getCellData("Test Cases", "Arg1", rNum));
				String Arg2=testData.get(xls.getCellData("Test Cases", "Arg2", rNum));
				String Arg3=testData.get(xls.getCellData("Test Cases", "Arg3", rNum));
				String Arg4=testData.get(xls.getCellData("Test Cases", "Arg4", rNum));
				String Arg5=testData.get(xls.getCellData("Test Cases", "Arg5", rNum));
				String Arg6=testData.get(xls.getCellData("Test Cases", "Arg6", rNum));
				String Arg7=testData.get(xls.getCellData("Test Cases", "Arg7", rNum));
				
				String Desc=xls.getCellData("Test Cases", "Description", rNum);
				
				if(keyword.equals(""))
				{	
					result= "Pass";
				}
				else if(keyword.equals("OpenBrowser"))
				{	
					result= fp.openBrowser();
				}
				//Assert.assertEquals(result, "Pass");
				else if(keyword.equals("Launch"))
				{	
					result= fp.Launch(Arg1);
				}
				//Assert.assertEquals(result, "Pass");

				else if(keyword.equals("Login"))
				{	
					result = fp.Login(Arg1,Arg2);
				}
				else if(keyword.equals("EnterText"))
				{
					result = fp.EnterText(Arg1,Arg2);
				}
				else if(keyword.equals("VerifyExistence"))
				{
					result = fp.VerifyExistence(Arg1);
				}
				else if(keyword.equals("SetCheckBox"))
				{
					result = fp.SetCheckBox(Arg1,Arg2);
				}
				else if(keyword.equals("SetRadio"))
				{
					result = fp.SetRadio(Arg1);
				}
				else if(keyword.equals("Click"))
				{
					result = fp.Click(Arg1,Arg2,Arg3);
				}
				else if(keyword.equals("SingleClick"))
				{
					result = fp.SingleClick(Arg1);
				}
				else if(keyword.equals("SetDropdown"))
				{
					result = fp.SetDropdown(Arg1,Arg2);
				}
				else if(keyword.equals("GetData"))
				{
					result = fp.GetData(Arg1);
				}
				else if(keyword.equals("Navigate"))
				{
					result = fp.Navigate(Arg1,Arg2);
				}
				else if(keyword.equals("VerifyData"))
				{
					result = fp.VerifyData(Arg1, Arg2);
				}
				else if(keyword.equals("VerifyDate"))
				{
					result = fp.VerifyDate(Arg1,Arg2,Arg3);
				}	
				else if(keyword.equals("GetText"))
				{
					result = fp.GetText(Arg1,Arg2);
				}
				else if(keyword.equals("MatchCount"))
				{
					result = fp.MatchCount(Arg1,Arg2);
				}
				else if(keyword.equals("DataNavigator"))
				{
					result = fp.DataNavigator(Arg1,Arg2, Arg3);
				}
				else if(keyword.equals("DismissErrors"))
				{
					result = fp.DismissErrors();
				}
				else if(keyword.equals("JavascriptAlert"))
				{
					result = fp.JavascriptAlert(Arg1);
				}
				else if(keyword.equals("KeywordHighlight"))
				{
					result = fp.KeywordHighlight(Arg1, Arg2, Arg3, Arg4);
				}
				else if(keyword.equals("VerifyHighlighting"))
				{
					result = fp.VerifyHighlighting(Arg1, Arg2, Arg3);
				}
				else if(keyword.equals("SelectFolder"))
				{
					result = fp.SelectFolder(Arg1,Arg2);
				}
				else if(keyword.equals("ExtractDetails"))
				{
					result = fp.ExtractDetails(Arg1, Arg2, Arg3);
				}
				else if(keyword.equals("AlertOperation"))
				{
					result = fp.AlertOperation(Arg1, Arg2, Arg3);
				}
				else if(keyword.equals("GetRichText"))
				{
					result = fp.GetRichText(Arg1,Arg2);
				}
				else if (keyword.equals("GetSearchBoxText"))
				{
					result=fp.GetSearchBoxText(Arg1,Arg2);
				}
				else if (keyword.equals("VerifyDropDownOptions"))
				{
					result=fp.VerifyDropDownOptions(Arg1,Arg2);
				}
				else if (keyword.equals("VerifyOptions"))
				{
					result=fp.VerifyOptions(Arg1,Arg2);
				}
				else if (keyword.equals("NewWindowOperation"))
				{
					result=fp.NewWindowOperation(Arg1,Arg2);
				}
				else if (keyword.equals("CalenderWidgetVerification"))
				{
					result=fp.CalenderWidgetVerification(Arg1,Arg2,Arg3,Arg4,Arg5);
				}
				else if(keyword.equals("matchElementCount"))
				{
					result = fp.matchElementCount(Arg1, Arg2);
				}
				else if (keyword.equals("MatchResults"))
				{
					result=fp.MatchResults(Arg1,Arg2);
				}
				else if (keyword.equals("copyPasteText"))
				{
					result=fp.copyPasteText(Arg1,Arg2);
				}
				else if (keyword.equals("FileDownloadPopUp"))
				{
					result=fp.FileDownloadPopUp();
				}

				else if (keyword.equals("PrintCommand"))
				{
					result=fp.PrintCommand();
				}

				else if (keyword.equals("ListHaveDesireTxt"))
				{
					result=fp.ListHaveDesireTxt(Arg1,Arg2);
				}

				else if (keyword.equals("GetLocation"))
				{
					result=fp.GetLocation(Arg1,Arg2,Arg3);
				}
				else if (keyword.equals("GetCount"))
				{
					result=fp.GetCount(Arg1);
				}
				else if (keyword.equals("DragAndDrop"))
				{
					result=fp.DragAndDrop(Arg1,Arg2,Arg3);
				}
				else if (keyword.equals("VerifySortedList"))
				{
					result=fp.VerifySortedList(Arg1,Arg2);
				}
				else if (keyword.equals("MouseHover"))
				{
					result=fp.MouseHover(Arg1,Arg2,Arg3);
				}
				else if (keyword.equals("GetCSSProperty"))
				{
					result=fp.GetCSSProperty(Arg1,Arg2,Arg3);
				}
				else if (keyword.equals("GetAttribute"))
				{
					result=fp.GetAttribute(Arg1,Arg2,Arg3);
				}
				else if (keyword.equals("IsElementClickable"))
				{
					result=fp.IsElementClickable(Arg1);
				}
				else if (keyword.equals("VerifyElementsExistence"))
				{
					result=fp.VerifyElementsExistence(Arg1);
				}
				else if (keyword.equals("SetMultipleCheckbox"))
				{
					result=fp.SetMultipleCheckbox(Arg1,Arg2);
				}
				else if (keyword.equals("VerifyFieldQualifier"))
				{
					result=fp.VerifyFieldQualifier(Arg1);
				}
				else if (keyword.equals("VerifyParentFolder"))
				{
					result=fp.VerifyParentFolder(Arg1,Arg2,Arg3,Arg4);
				}
				else if (keyword.equals("CompareData"))
				{
					result=fp.CompareData(Arg1,Arg2,Arg3,Arg4,Arg5);
				}
				else if (keyword.equals("SecurityPopUP"))
				{
					result=fp.SecurityPopUP();
				}
				else if (keyword.equals("MouseDrag"))
				{
					result=fp.MouseDrag(Arg1,Arg2);
				}
				else if (keyword.equals("ClickOnDesiredLink"))
				{
					result=fp.ClickOnDesiredLink(Arg1,Arg2);
				}
				else if (keyword.equals("VerifyDesiredLinks"))
				{
					result=fp.VerifyDesiredLinks(Arg1,Arg2,Arg3);
				}
				else if (keyword.equals("ClickForDownload"))
				{
					result=fp.ClickForDownload(Arg1);
				}
				else if (keyword.equals("ClearTextbox"))
				{
					result=fp.ClearTextbox(Arg1);
				}
				else if (keyword.equals("ClickForPrint"))
				{
					result=fp.ClickForPrint(Arg1);
				}
				else if (keyword.equals("FilePrintPopUp"))
				{
					result=fp.FilePrintPopUp();
				}
				else if (keyword.equals("SwitchWindows"))
				{
					result=fp.SwitchWindows(Arg1);
				}
				else if (keyword.equals("ScrollbarPosition"))
				{
					result=fp.ScrollbarPosition(Arg1);
				}
				else if (keyword.equals("TextShouldPresent"))
				{
					result=fp.TextShouldPresent(Arg1);
				}
				else if (keyword.equals("StringFormatMatcher"))
				{
					result=fp.StringFormatMatcher(Arg1,Arg2);
				}
				else if (keyword.equals("Tooltip"))
				{
					result=fp.Tooltip(Arg1,Arg2,Arg3);
				}
				else if (keyword.equals("VerifySelectDeselectAll"))
				{
					result=fp.VerifySelectDeselectAll(Arg1,Arg2);
				}
				else if (keyword.equals("copyPasteTextFromFile"))
				{
					result=fp.copyPasteTextFromFile(Arg1);
				}
				else if (keyword.equals("FileUpload"))
				{
					result=fp.FileUpload(Arg1,Arg2);
				}
				else if (keyword.equals("Wait"))
				{
					result=fp.Wait(Arg1);
				}
				else if (keyword.equals("DeleteAllKeyword"))
				{
					result=fp.DeleteAllKeyword();
				}
				else if (keyword.equals("JumptoLastPage"))
				{
					result=fp.JumptoLastPage();
				}
				
				else if(keyword.equals("Logout"))
				{result = fp.Logout();
				//Assert.assertEquals(result, "Pass");
				}
				else if(keyword.equals("closeBrowser"))
				{result=fp.closeBrowser();
				}
				// Assert.assertEquals(result, "Pass");

				else if (keyword.equals("EmailReport"))
				{result=fp.EmailReport();
				}

				if(!result.equals("Pass")){
					System.out.println(keyword+" is not executed when the compare result is : "+result );
					System.out.println("The test step parameters are : "+keyword+","+rowid+","+Arg1+","+Arg2+","+Arg3+","+Arg4+","+ result);
					//  Assert.fail(result);
					rowid=rowid+1;
					rep.ReportLine(testName,tcase,keyword,rowid, Arg1, Arg2, Arg3,Arg4,Arg5,Arg6,Arg7, result, sDate, Desc);
					
					try {Screenshot();   } catch (Exception e) {}
					
				}
				else if (result.equals("Pass")){ 
					System.out.println("The test step parameters are : "+keyword+","+rowid+","+Arg1+","+Arg2+","+Arg3+","+Arg4+","+ result);
					System.out.println(keyword+" is executed when the compare result is : "+result );
					System.out.println();
					rowid=rowid+1;
					rep.ReportLine(testName,tcase,keyword,rowid, Arg1, Arg2, Arg3,Arg4,Arg5,Arg6,Arg7, result, sDate, Desc);
					//System.out.println(testName,tcase,keyword,rowid, Arg1, Arg2, Arg3,Arg4,Arg5,Arg6,Arg7, result,sDate); 
					System.out.println(); }
				else if(keyword.equals("closeBrowser")) 
				{
					System.out.println();
					System.out.println("************* TC execution completed *************************");
					System.out.println();
				}	
				if(keyword.equals("VerifyFieldQualifier"))
				{
					rep.ReportLine(testName,tcase,keyword,rowid, Arg1, Arg2, Arg3,Arg4,Arg5,Arg6,count, result, sDate, Desc);

				}
			}
		}

	}

	public void getResultCount(String count){
		this.count="Record count= "+count;
	}
	
	public void Screenshot() throws Exception {
		try {
			
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			
			BufferedImage capture = new Robot().createScreenCapture(screenRect);
			System.out.println("name: "+sDate);
			ImageIO.write(capture, "gif", new File(System.getProperty("user.dir")+"\\Screenshot\\Screen "+sDate.replace(":","-")+".gif"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}