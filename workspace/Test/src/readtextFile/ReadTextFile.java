package readtextFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/* READ Lines in text file */
// use methods 


public class ReadTextFile {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub


		// 1st Create New File
		
  String Tfile = "/Users/Labuser/Desktop/File/"  ;
  
  File FL = new File(Tfile);
  FL.createNewFile();
  
	/*  Writing into the file*/
  
  
  
  FileWriter Fw = new FileWriter(FL);
  BufferedWriter  Bw = new BufferedWriter(Fw);
  
  Bw.write("This is a new file line");
  Bw.newLine();
  
  Bw.write("This is a new line No 2");
  
  Bw.close();
  
  /* Reading code from the file */  
		

  FileReader Fr= new FileReader(Tfile);
  
  BufferedReader Br = new BufferedReader(Fr);
  
  String content = "";
  
  
  
  while ((content = Br.readLine())!=null)
  {
	  
	  System.out.println(content);
	  
  }
  
  
		
		
		
		
	
	
	
	

}



























}
