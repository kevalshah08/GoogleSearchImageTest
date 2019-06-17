/*
 * Keval Rajesh Shah
 * Name : ReadPropertyFile 
 * Use : Used for getting url and image from defined path.  
 * 
 */
package googleimagesearch;

import java.io.*;
import java.util.Properties;

public class ReadPropertyFile 
{
	protected static Properties prop = null;
	protected InputStream input = ReadPropertyFile.class.getClassLoader().getResourceAsStream("config.properties");

	public ReadPropertyFile() throws Exception
	{
		prop = new Properties();
		prop.load(input);
	}
	
	public String getUrl() 
	{
		return prop.getProperty("url");
		
	}
	
	public String getImagePath() {
		return prop.getProperty("image");
		
	}
	
}