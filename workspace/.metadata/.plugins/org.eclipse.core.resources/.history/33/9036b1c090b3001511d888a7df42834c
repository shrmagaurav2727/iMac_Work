package com;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class NewTest extends Thread{
	
	WebDriver driver;
	
  @Test
  public void f() {
	  

          Thread t1 = new Thread();
          Thread t2 = new Thread();
          System.out.println("Starting MyThreads");
          t1.start();
          t2.start();
          System.out.println("Thread has been started");
	  
	
  }
//  @BeforeTest
//  public void beforeTest() {
//	  driver =   new FirefoxDriver();
//	  driver.get("http://www.gmail.com");
//  }
//
//  @AfterTest
//  public void afterTest() {
//  }
  
  

  @Override
  public void run() {
       System.out.println("Thread- Started"
                                     + Thread.currentThread().getName());
       
               try {
				setUp("Firefox");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
               try {
				testGoogleSearch();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

         
      System.out.println("Thread- END " + Thread.currentThread().getName());
  }

  // main method to create thread and run multiple thread
  
  String baseUrl;
  // set up method to initialize driver object
  public void setUp(String browsertype) throws Exception {

             
              if (browsertype.contains("Firefox")) {
                          driver = new FirefoxDriver();
              }
              baseUrl = "https://www.google.co.in/";
              driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
              driver.manage().window().maximize();
  }

  // test scripts
  public void testGoogleSearch() throws Exception {
              driver.get(baseUrl + "/");
              driver.findElement(By.id("gbqfq")).clear();
              driver.findElement(By.id("gbqfq")).sendKeys("Testing");
              driver.findElement(By.id("gbqfb")).click();
              driver.findElement(By.id("ab_opt_icon")).click();
              driver.findElement(By.cssSelector("#ab_as > div")).click();
              driver.findElement(By.xpath("//input[@value='Advanced Search']"))
                                      .click();
  }

  // tear down function to close browser
  public void tearDown() {
              driver.quit();
  }

}
