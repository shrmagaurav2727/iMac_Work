package cpa.qa.testcases;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Hashtable;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cpa.qa.util.Keywords;
import cpa.qa.util.TestUtil;
import cpa.qa.util.Xls_Reader;

public class Dashboard {

		Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"\\src\\cpa\\qa\\xls\\Dashboard.xlsx");
		
		@Test(dataProvider="getData")
			public void doDashboard(Hashtable<String,String> testData) throws Throwable{
			System.out.println("##teamcity[testSuiteStarted name='suite.Dashboard']");
			
			// run-mode of test
			if(!TestUtil.getRunmode("Dashboard", xls))
				throw new SkipException("Skipping the entire test as Runmode is N");

			// run-mode of Data set
			if(testData.get("Runmode").equals("N"))
				throw new SkipException("Skipping the test data set as runmode is N");
			
			// execution
			if(testData.get("Runmode").equals("Y")) {
				
			Keywords k = Keywords.getInstance();//new Keywords();// init OR
			k.executeTest("Dashboard",xls,testData); 
			
			Xls_Reader xls1 = new Xls_Reader(System.getProperty("user.dir")+"\\src\\cpa\\qa\\xls\\Test Report.xlsx");
			TestUtil.getReport("Dashboard", xls1);
			
			System.out.println("##teamcity[testSuiteFinished name='suite.Dashboard']");
			String filename=System.getProperty("user.dir")+"\\src\\cpa\\qa\\xls\\Test Report.xlsx";
			FileInputStream fs =new FileInputStream(filename);    
		    XSSFWorkbook wb = new XSSFWorkbook(fs);
			FileOutputStream fileOut =  new FileOutputStream(filename);
			wb.write(fileOut);
			fileOut.close();
			System.out.println("THE END >>>>>>>>>>>>");
			}
		
				
			
		}
		
		
		@DataProvider
		public Object[][] getData(){
				return 	TestUtil.getData("Dashboard", xls);
		}


	}


		