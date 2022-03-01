package com.lamdatest.testcase;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.net.URL;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class SingleTest {
	
	//Lambdatest Credentails can be found here at https://www.lambdatest.com/capabilities-generator
	 String username = System.getenv("LT_USERNAME") == null ? "ashmaddy79" : System.getenv("LT_USERNAME"); 
	   String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "7QOKtAsZzPwfgA9g8PUIekOoefA3KY4Q6jfPYzVQDBFVQ8nSnh" : System.getenv("LT_ACCESS_KEY"); 
		
	
	public static WebDriver driver;
	public static String status = "failed";

	@BeforeTest(alwaysRun=true)
	@Parameters(value = { "browser", "version", "platform" })
	public void setUp(String browser, String version, String platform) throws Exception {

		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability(CapabilityType.BROWSER_NAME, browser);
		capability.setCapability(CapabilityType.VERSION, version);
		capability.setCapability(CapabilityType.PLATFORM, platform);
		capability.setCapability("build", "Test");
		capability.setCapability("name", "Test");
		capability.setCapability("network", true);
		capability.setCapability("video", true);
		capability.setCapability("console", true);
		capability.setCapability("visual", true);

		String gridURL = "http://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub";
		try {
			driver = new RemoteWebDriver(new URL(gridURL), capability);
		} catch (Exception e) {
			System.out.println("driver error");
			System.out.println(e.getMessage());
		}
	}

	@Test
	public static void test() {
		try {

				driver.get("https://www.lambdatest.com/selenium-playground/");
				driver.findElement(By.linkText("Simple Form Demo")).click();
				String strUrl = driver.getCurrentUrl();
				
				if (strUrl.contains("simple-form-demo")) {
					System.out.println("Url Contains simple-form-demo Text");
				}
	
				String enterdMsg = "Welcome to LambdaTest";
				
			WebElement element =driver.findElement(By.id("user-message"));
			element.sendKeys(enterdMsg);
			driver.findElement(By.xpath("//*[@id=\"showInput\"]")).click();
			WebElement message =driver.findElement(By.id("message"));
			String expectedMsg = message.getText();
			 Assert.assertEquals(enterdMsg, expectedMsg);
			status = "passed";
			
			((JavascriptExecutor) driver).executeScript("lambda-status=" + status + "");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} catch (Error e) {
			System.out.println("Assert failed");
		}

	}
	
	
	@Test
	public static void test1() {
		try {

				driver.get("https://www.lambdatest.com/selenium-playground/");
				driver.findElement(By.linkText("Drag & Drop Sliders")).click();
				
			WebElement element =driver.findElement(By.xpath("//*[@id=\"slider3\"]/div/input"));
				Actions action = new Actions(driver);
				
				 action.clickAndHold(element).moveByOffset(120, 0).release().build().perform();
			
			status = "passed";
			
			((JavascriptExecutor) driver).executeScript("lambda-status=" + status + "");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} catch (Error e) {
			System.out.println("Assert failed");
		}

	}

	@Test
	public static void test2() {
		try {

				driver.get("https://www.lambdatest.com/selenium-playground/");
				driver.findElement(By.linkText("Input Form Submit")).click();
			    driver.findElement(By.xpath("//*[@id=\"seleniumform\"]/div[6]/button")).click();
			    JavascriptExecutor js = (JavascriptExecutor)driver;
			    WebElement name = driver.findElement(By.name("name"));
				String actualErrorMsg = "Please fill out this field.";
		        
				Boolean is_valid = (Boolean)js.executeScript("return arguments[0].checkValidity();", name);
				String expectedErrorMsg = (String)js.executeScript("return arguments[0].validationMessage;", name);
				
//		        WebElement exp = driver.findElement(By.xpath("/html/body[contains(text(),'Please fill out this field.')]"));
//		        String actualErrorMsg = exp.getText();
		        		
		        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
		        
		        
		        WebElement name1= driver.findElement(By.name("name"));
		        name1.sendKeys("TestName");
		         
		        WebElement email = driver.findElement(By.name("email"));
		        email.sendKeys("test@test.com");
		                 
		        WebElement password = driver.findElement(By.name("password"));
		        password.sendKeys("12345678");
		          
		        WebElement companyName = driver.findElement(By.name("company"));
		        companyName.sendKeys("TestCompany");
		          
		       
		        WebElement website = driver.findElement(By.name("website"));
		        website.sendKeys("test");
		        
				
		        Select country = new Select(driver.findElement(By.name("country")));
		        country.selectByVisibleText("United States");
		        
		        WebElement city = driver.findElement(By.name("city"));
		        city.sendKeys("test");
		        
		        WebElement address_line1 = driver.findElement(By.name("address_line1"));
		        address_line1.sendKeys("test");
		        

		        WebElement address_lin2 = driver.findElement(By.name("address_line2"));
		        address_lin2.sendKeys("test"); 
		        
		        
		        WebElement state = driver.findElement(By.xpath("//*[@id=\"inputState\"]"));
		        state.sendKeys("test");
		      
		        WebElement zip = driver.findElement(By.name("zip"));
		        zip.sendKeys("test");
		      
		        driver.findElement(By.xpath("//*[@id=\"seleniumform\"]/div[6]/button")).click();
			status = "passed";
			
			 WebElement exp = driver.findElement(By.xpath("//p[contains(text(),'Thanks for contacting us, we will get back to you shortly.')]"));
		        String ActualMsg = exp.getText();
		     String expectedMsg = "Thanks for contacting us, we will get back to you shortly.";
		        Assert.assertEquals(ActualMsg, expectedMsg);
			
			((JavascriptExecutor) driver).executeScript("lambda-status=" + status + "");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} catch (Error e) {
			System.out.println("Assert failed");
		}

	}
	
	
	@AfterTest
	public static void afterTest() {
		((JavascriptExecutor) driver).executeScript("lambda-status=" + status + "");
		driver.quit();
	}

}
