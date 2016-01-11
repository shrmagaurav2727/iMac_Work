package cpa.qa.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class PropertiesUtil {
	
	private static Properties props;
	 
	  static
	  {
	    props = new Properties();
	    try
	    {
	      PropertiesUtil util = new PropertiesUtil();
	      props = util.getPropertiesFromClasspath("Functions.properties");
	    }
	    catch (FileNotFoundException e)
	    {
	    	System.out.println("Fail:1");
	      e.printStackTrace();
	    }
	    catch (IOException e)
	    {
	    	System.out.println("Fail:2");
	      e.printStackTrace();
	    }
	  }
	 
	  // private constructor
	  private PropertiesUtil()
	  {
	  }
	 
	  public static String getProperty(String key)
	  {
	    return props.getProperty(key);
	  }
	 
	  public static Set<Object> getkeys()
	  {
	    return props.keySet();
	  }
	 
	  /** 
	   * 
	   * 
	   * 
	   * loads properties file from classpath
	   * 
	   * 
	   **/
	  private Properties getPropertiesFromClasspath(String propFileName)
	                                                                    throws IOException
	  {
	    Properties props = new Properties();
	    InputStream inputStream =
	        this.getClass().getClassLoader().getResourceAsStream("Functions.properties");
	 
	    if (inputStream == null)
	    {
	      throw new FileNotFoundException("property file '" + propFileName
	          + "' not found in the classpath");
	    }
	 
	    props.load(inputStream);
	    return props;
	  }
}
