package com;


import java.io.File;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class GoogleSearchMultiThread extends Thread {

   private static WebDriver driver;
   private String baseUrl;
   private String browsertype;

   public GoogleSearchMultiThread(String name, String browsertype) {
               super(name);
               this.browsertype = browsertype;
   }

   @Override
   public void run() {
        System.out.println("Thread- Started"
                                      + Thread.currentThread().getName());
        try {
                Thread.sleep(1000);
                setUp(this.browsertype);
                testGoogleSearch();

          } catch (InterruptedException e) {
                 e.printStackTrace();
         } catch (Exception e) {
               // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                tearDown();
        }
       System.out.println("Thread- END " + Thread.currentThread().getName());
   }

   // main method to create thread and run multiple thread
   public static void main(String[] args) {
	   System.out.println("asdasdasda MyThreads");
               Thread t1 = new GoogleSearchMultiThread("Thread Firefox", "Firefox");
               Thread t2 = new GoogleSearchMultiThread("Thread Firefox", "Firefox");
               System.out.println("Starting MyThreads");
               t1.start();
               t2.start();
               System.out.println("Thread has been started");

   }

   // set up method to initialize driver object
   public void setUp(String browsertype) throws Exception {

               if (browsertype.contains("IE")) {
                           File IEDriver = new File("IEDriverServer.exe");
                           System.setProperty("webdriver.ie.driver",
                                                   IEDriver.getAbsolutePath());
                           driver = new InternetExplorerDriver();
               } else if (browsertype.contains("Firefox")) {
                           driver = new FirefoxDriver();
                           driver = new FirefoxDriver();
               }
               baseUrl = "https://www.google.co.in/";
               driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
               driver.manage().window().maximize();
   }

   // test scripts
   public void testGoogleSearch() throws Exception {
               driver.get(baseUrl + "/");
//               driver.findElement(By.id("gbqfq")).clear();
//               driver.findElement(By.id("gbqfq")).sendKeys("Testing");
//               driver.findElement(By.id("gbqfb")).click();
//               driver.findElement(By.id("ab_opt_icon")).click();
//               driver.findElement(By.cssSelector("#ab_as > div")).click();
//               driver.findElement(By.xpath("//input[@value='Advanced Search']"))
//                                       .click();
   }

   // tear down function to close browser
   public void tearDown() {
               driver.quit();
   }

}
