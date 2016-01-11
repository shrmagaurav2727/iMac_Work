package cpa.qa.util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Reporting{
	
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static Random rnd = new Random();
	  String filename=System.getProperty("user.dir")+"\\src\\cpa\\qa\\xls\\Test Report.xlsx"; 
	  FileInputStream fileInputStream = null;
	  
	  
	  public void GenerateReport(String filename,String Sheetname) throws IOException {
		   Xls_Reader rw = new Xls_Reader(filename);
		  
		  if (rw.isSheetExist(Sheetname)){
			   // Clear all cells
			   for ( int Index =0 ; Index <= rw.getColumnCount(Sheetname); Index ++ ){
				   rw.removeColumn(Sheetname, Index);
			   }
			   
			   rw.addColumn(Sheetname, "Test Suite");
			   rw.addColumn(Sheetname, "Test Case");
			   rw.addColumn(Sheetname, "Keyword");
			   rw.addColumn(Sheetname, "Arg1");
			   rw.addColumn(Sheetname, "Arg2");
			   rw.addColumn(Sheetname, "Arg3");
			   rw.addColumn(Sheetname,  "Arg4");
			   rw.addColumn(Sheetname,  "Arg5");
			   rw.addColumn(Sheetname,  "Arg6");
			   rw.addColumn(Sheetname,  "Arg7");
			   rw.addColumn(Sheetname, "Status");
			   rw.addColumn(Sheetname, "Execution date and time");
			   rw.addColumn(Sheetname, "Description");
			   
//			   for ( int Index =0 ; Index <= 11 ; Index ++ ){
//				   rw.removeColumn(Sheetname, Index);
//			   }
			   
			   
		   }
		   else 
		   {
		   rw.addSheet(Sheetname); 
		   rw.addColumn(Sheetname, "Test Suite");
		   rw.addColumn(Sheetname, "Test Case");
		   rw.addColumn(Sheetname, "Keyword");
		   rw.addColumn(Sheetname, "Arg1");
		   rw.addColumn(Sheetname, "Arg2");
		   rw.addColumn(Sheetname, "Arg3");
		   rw.addColumn(Sheetname,  "Arg4");
		   rw.addColumn(Sheetname,  "Arg5");
		   rw.addColumn(Sheetname,  "Arg6");
		   rw.addColumn(Sheetname,  "Arg7");
		   rw.addColumn(Sheetname, "Status");
		   rw.addColumn(Sheetname, "Execution date and time");
		   rw.addColumn(Sheetname, "Description");
		    }
		  }
	   
	
	  
	   
	public void ReportLine(String Suite,String tid, String Keyword,int rowid,String Arg1, String Arg2, String Arg3, String Arg4,String Arg5,String Arg6,String Arg7,String Status,String date,String Desc){
	
		
		/*		
		rw.setCellData(Suite, "Test Suite", rowid, Suite);
		   rw.setCellData(Suite, "Test Case", rowid, tid);
		       rw.setCellData(Suite, "Keyword", rowid, Keyword);
			   rw.setCellData(Suite, "Arg1", rowid, Arg1);
			   rw.setCellData(Suite, "Arg2", rowid, Arg2);
			   rw.setCellData(Suite, "Arg3", rowid, Arg3);
			   rw.setCellData(Suite, "Arg4", rowid, Arg4);
			   rw.setCellData(Suite, "Arg5", rowid, Arg5);
			   rw.setCellData(Suite, "Arg6", rowid, Arg6);
			   rw.setCellData(Suite, "Arg7", rowid, Arg7);
			   rw.setCellData(Suite, "Status", rowid, Status);
			   rw.setCellData(Suite, "Execution date and time", rowid,date);
			   */
			   
			   
			   
			   try{
				   //Thread.sleep(000);
			//	   String filename="C:\\Users\\prathore\\workspace\\Discover\\src\\cpa\\qa\\xls\\TestReport.xlsx";;  //"c:/test.xls" ;
				   FileInputStream fs =new FileInputStream(filename);    
			        XSSFWorkbook wb = new XSSFWorkbook(fs); 

			        XSSFSheet sheet= wb.getSheet(Suite);
				
			        XSSFRow rowhead=   sheet.createRow((int)rowid);
	
					rowhead.createCell((short) 0).setCellValue(Suite);
					rowhead.createCell((short) 1).setCellValue(tid);
					rowhead.createCell((short) 2).setCellValue(Keyword);
					rowhead.createCell((short) 3).setCellValue(Arg1);
					rowhead.createCell((short) 4).setCellValue(Arg2);
					rowhead.createCell((short) 5).setCellValue(Arg3);
					rowhead.createCell((short) 6).setCellValue(Arg4);
					rowhead.createCell((short) 7).setCellValue(Arg5);
					rowhead.createCell((short) 8).setCellValue(Arg6);
					rowhead.createCell((short) 9).setCellValue(Arg7);
					rowhead.createCell((short) 10).setCellValue(Status);
					rowhead.createCell((short) 11).setCellValue(date);
					rowhead.createCell((short) 12).setCellValue(Desc);
					FileOutputStream fileOut =  new FileOutputStream(filename);
					wb.write(fileOut);
					fileOut.close();
			      } catch ( Exception ex ) {
					  							System.out.println(ex);
				                           }
	}
			   
			 			//   System.out.println("row id "+rowid);
			   
		     //  rw.setCellData("Sheet1", "Keyword", 5, "Turbo Charger");
		   
	
	
	String randomString( int len ) 
	{
	   StringBuilder sb = new StringBuilder( len );
	   for( int ai = 0; ai < len; ai++ ) 
	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	   return sb.toString();
	}
	
	
/*
	 public void  Reporting(String Filename,String Sheetname) throws IOException{
		 String	path=System.getProperty("user.dir")+"\\src\\CreateExcel.vbs";
			System.out.println("User Directory:"+System.getProperty("user.dir"));
	         Runtime.getRuntime().exec("WScript path" + "\"" + Filename + "\"" + " " + "\""  + Sheetname + "\"" );
                          }
*/

}	   


			