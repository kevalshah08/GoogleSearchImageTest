/*
 * Keval Rajesh Shah
 * Name : ImageSearch 
 * 
 * Use: I have put mostly everything in one file rather than using proper POM Model because script was small 
 * 		and only couple of cases were there.
 * 		I have used TestNG framework but not into POM Model. I have tried to cover 
 * 		everything by using normal and simple coding method.
 * 
 * 
 */

package googleimagesearch;
import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.*;
import googleimagesearch.ReadPropertyFile;

public class ImageSearch 
{
    public WebDriver driver ; 
    public static String Url ;
    public static String img ;
    
    	// GUI Part
    	@BeforeTest
    	public void launchBrowser() throws Exception 
    	{
    		ReadPropertyFile data = new ReadPropertyFile(); 
        
    		System.out.println("launching chrome browser"); 
    		System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
    		
    		driver = new ChromeDriver();
    		
    		driver.manage().window().maximize();
    		Url = data.getUrl();;
    		
    		driver.get(Url);
    		WebElement label = driver.findElement(By.linkText("Images"));
    		label.click();
          
    		WebElement imageby = driver.findElement(By.className("FiqGxd"));
    		imageby.click();
    		
    		WebElement button = driver.findElement(By.linkText("Upload an image"));
    		button.click();
    		img = data.getImagePath();
    		
          
    		driver.findElement(By.id("qbfile")).sendKeys(img);
    		
    		List<WebElement> webElement = driver.findElements(By.xpath("//*/g-img[1]"));
            WebElement expect = webElement.get(3); //Here i have tried to use visit rule = 3. Please Do Comment for correction. Thanks.
            expect.click();
    		
    		
    	}
      
    	// Test Part 
    	@Test
    	public void verifyTitle() throws Exception 
    	{
    		
            takeSnapShot(driver,"F://test.jpg"); // For Last visited page screenshot. 
            Actions action = new Actions(driver);
            action.sendKeys(Keys.ESCAPE).build().perform();
            
    		String expectedTitle = "sydney opera house - Google Search";
    		String actualTitle = driver.getTitle();
    		
    		Assert.assertEquals(actualTitle, expectedTitle);
    	}
    	
    	//Driver Close 
      
    	@AfterTest
    	public void terminateBrowser()
    	{
    		driver.close();
    	}
    	
    	//Function for screenshot
    	
    	public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception
    	{
    		TakesScreenshot scrShot =((TakesScreenshot)webdriver);
    		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
    		File DestFile=new File(fileWithPath);
    		FileUtils.copyFile(SrcFile, DestFile);

        }
}
