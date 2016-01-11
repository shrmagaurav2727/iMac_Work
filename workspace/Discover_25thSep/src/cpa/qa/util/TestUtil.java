package cpa.qa.util;

import java.util.ArrayList;
import java.util.Hashtable;

import com.google.common.base.Joiner;

public class TestUtil {

	public static int ctr=0;
	public static int RowsCosl=3;
	public static Object[][] getData(String testCase, Xls_Reader xls){
		int testStartRowNum=1;
		while(!xls.getCellData("TestData", 0, testStartRowNum).equals(testCase)){
			testStartRowNum++;
		}
		System.out.println();

		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println();
		System.out.println("Test Case "+ testCase+ " starts from row number "+ testStartRowNum + " of TestData Sheet.");


		// rows of data
		int dataStartRowNum=testStartRowNum+2;
		int rows=0;
		while(!xls.getCellData("TestData",0,dataStartRowNum+rows).equals("")){
			rows++;
		}
		System.out.println("Total test data rows in test case:  "+testCase+" are : "+ rows );


		// total cols
		int colstartRowNum = testStartRowNum+1;
		int cols=0;
		while(!xls.getCellData("TestData", cols, colstartRowNum).equals("")){
			cols++;
		}
		System.out.println("Total test data columns in test case: "+testCase+" are "+ cols );

		Object testData[][] = new Object[rows][1];
		int index=0;
		Hashtable <String,String> table = null;
		// extract data
		System.out.println();
		System.out.println("************ Test data format - Display Start *******************");
		for(int rNum=dataStartRowNum;rNum<dataStartRowNum+rows;rNum++){
			table = new Hashtable<String,String>(); // init for every row
			System.out.println();
			for(int cNum=0;cNum<cols;cNum++){
				String key = xls.getCellData("TestData", cNum, colstartRowNum);
				String value = xls.getCellData("TestData", cNum, rNum);
				System.out.print(value +" --- ");
				//fill the table
				table.put(key, value);
			}

			// put table in array

			System.out.println("");
			testData[index][0]=table;
			index++;
		}

		System.out.println();
		System.out.println("************Test data format - Display End *******************");
		System.out.println();
		return testData;

	}

	// true  -  Y
	// false -  N
	public static boolean getRunmode(String testName, Xls_Reader xls){

		int rows = xls.getRowCount("Tests");
		System.out.println();
		System.out.println("Total number of test suites to be executed are  -> "+ (rows-1));

		for(int i=2;i<=rows;i++){

			String tcid = xls.getCellData("Tests", "TestSuite", i);
			String runmode=xls.getCellData("Tests", "Runmode", i);

			if(tcid.equals(testName)){
				if(runmode.equals("Y"))
				{ System.out.println();
				System.out.println("Execution started for test suite  -> "+ (i-1));
				System.out.println();
				return true; }
				else
				{
					System.out.println();
					System.out.println("Execution skipped for test suite  -> "+ (i-1));
					System.out.println();
					return false; }

			}
		}

		return false;// default

	}



	//Method to genrate consolidated test report
	public static boolean getReport(String testName, Xls_Reader xls1){

		int rows = xls1.getRowCount(testName);
		System.out.println();
		System.out.println("Total number of rows -> "+ (rows-1));
		int Pass=0;
		int Fail=0;
		String Teamcity = null;
		String TestDesc = null;								//						
		
		ArrayList<String> Status = new ArrayList<String>();
		ArrayList<String> TCName = new ArrayList<String>();
		ArrayList<String> TcDescription = new ArrayList<String>();     //
		
		for(int i=2;i<=rows;i++){

			String a = xls1.getCellData(testName, "Status", i);
			Status.add(a);
			String TC = xls1.getCellData(testName, "Test Case", i);
			String TcDesc = xls1.getCellData(testName, "Description", i);			//

			// System.out.println(" " +Status);
			if(!TC.equals("") && !TC.equalsIgnoreCase("TCN")) {
				
					if(i!=2) { 
						Teamcity = TCName.get(TCName.size()-1);
						System.out.println("##teamcity[testStarted name='"+Teamcity+"']");
						TestDesc = TcDescription.get(TcDescription.size()-1);      //
					}
					
				TcDescription.add(TcDesc);			//
				TCName.add(TC);
				String b = Status.get(Status.size() - 1);
				Status.remove(Status.size() - 1);
				//System.out.println("next" +Status);
				if (Status.contains("Fail") == true ){
					// System.out.println("Fail");
					System.out.println("##teamcity[testFailed name='"+Teamcity+"' message=' Description ->>  "+TestDesc+"']");   //
					Fail++ ;
				}
				else {
					if (i!=2){
						TCName.remove(TCName.size() - 2);
						TcDescription.remove(TcDescription.size() - 2);   //
					}
					//  System.out.println("Pass");
					Pass++;
				}

				Status.clear();
				Status.add(b);
				if(i!=2) {  System.out.println("##teamcity[testFinished name='"+Teamcity+"']");	}
			}    
			if(!TC.equals("") && TC.equalsIgnoreCase("TCN")) {
				String b = Status.get(Status.size() - 1);
				if (b.equalsIgnoreCase("Pass")){
					Status.remove(Status.size() - 1);
					Status.add("Fail");
				}
				else {
					Status.remove(Status.size() - 1);
					Status.add("Pass");
				}
			}

		}  

		TCName.remove(TCName.size()-1);
		System.out.println("Total pass TC's :" +(Pass-1));
		System.out.println("Total Fail TC's :" +Fail);
		System.out.println("Failed TC "+TCName );
		String Pass1 = Integer.toString((Pass-1));
		String Fail1 = Integer.toString(Fail);
		String FailedTC = Joiner.on(", ").join(TCName);

		// Create Consolidated sheet test Report      
		String Sheetname= "consolidated" ;
		if (xls1.isSheetExist(Sheetname)){
			if (ctr==0) {

				xls1.removeColumn(Sheetname, 0);
				xls1.removeColumn(Sheetname, 1);
				xls1.removeColumn(Sheetname, 2);
				xls1.removeColumn(Sheetname, 3);
				xls1.addColumn(Sheetname, "Component");
				xls1.addColumn(Sheetname, "Pass count");
				xls1.addColumn(Sheetname, "Fail count");
				xls1.addColumn(Sheetname, "Failed Test Cases");
				xls1.setCellData(Sheetname, "Pass count", 2, Pass1);
				xls1.setCellData(Sheetname, "Fail count", 2, Fail1);
				xls1.setCellData(Sheetname, "Component", 2, testName);
				xls1.setCellData(Sheetname, "Failed Test Cases", 2, FailedTC);

				ctr=ctr+1;
				return false;
			}
			else{
				ctr=ctr+1;
				// RowsCos2 = xls1.getRowCount("consolidated");
			}
		}

		else
		{
			xls1.addSheet(Sheetname);
			xls1.addColumn(Sheetname, "Component");
			xls1.addColumn(Sheetname, "Pass count");
			xls1.addColumn(Sheetname, "Fail count");
			xls1.addColumn(Sheetname, "Failed Test Cases");
			ctr=ctr+1;
			RowsCosl= RowsCosl-1 ;
			//  RowsCos2 = xls1.getRowCount("consolidated");

		}

		// int RowsCosl = xls1.getRowCount("consolidated");
		xls1.setCellData(Sheetname, "Pass count", RowsCosl, Pass1);
		xls1.setCellData(Sheetname, "Fail count", RowsCosl, Fail1);
		xls1.setCellData(Sheetname, "Component", RowsCosl, testName);
		xls1.setCellData(Sheetname, "Failed Test Cases", RowsCosl, FailedTC);
		RowsCosl++;
		return false; // default

	}

}
