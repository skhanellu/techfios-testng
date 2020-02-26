package test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NewDepositTest {
	
	WebDriver driver;
	@BeforeMethod
	public void LaunchBrowser() {
	System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
    driver = new ChromeDriver();
	//driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.get("http://techfios.com/test/billing/?ng=admin/");
		}
	@Test
	public void userShouldBeAbleToAddDeposit() throws InterruptedException {
		
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("techfiosdemo@gmail.com");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("abc123");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[contains(text(), 'Sign') and @name='login']")).click();
		driver.findElement(By.xpath("//div[@id='wrapper']/descendant::span[text()=('Bank & Cash')]")).click();
		
		driver.findElement(By.xpath("//div[@id='wrapper']/descendant::a[text()='New Account']")).click();
		
		Thread.sleep(3000);
		//driver.findElement(((Object) By.xpath("//label[text()='Account Title']")).sendKeys()
		//driver.findElement(By.xpath("//input[@id='account']")).sendKeys("Account123");
		Random rnd = new Random();
		//String expectedDescription = "CheAccount" + new Random().nextInt(999);
		String expectedDescription = "CheAccount" + String.valueOf(rnd.nextInt(9999));
		System.out.println("Expected: " + expectedDescription);
		driver.findElement(By.xpath("//input[@id='account']")).sendKeys(expectedDescription);
		
		driver.findElement(By.xpath("//input[@id='description']")).sendKeys(expectedDescription +" Today" );
		
		String expectedDescription1 = String.valueOf(rnd.nextInt(9999));
		
		driver.findElement(By.xpath("//input[@id='balance']")).sendKeys(expectedDescription1);
		System.out.println("Expected Balance: " + expectedDescription1);
		
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		
		//div[@class='alert alert-success fade in']
	    String expectedTitle =  "Accounts- TechFios Test Application - Billing";
		Assert.assertEquals(driver.getTitle(), expectedTitle, "New Account create ok Page did not display!");
	
		
		//List<WebElement> descriptionElements = driver.findElements(By.xpath("//table/descendant::a"));
		//JavascriptExecutor js = (JavascriptExecutor)driver;
		//js.executeScript("scroll(0,600)");
		
		
		//	By TRANSCACTIONS_MENU_LOCATOR = By.xpath("//ul[@id='side-menu']/descendant::span[text()='Transactions']");
	//	By NEW_DEPOSIT_PAGE_LOCATOR = By.linkText("New Deposit");
	//	driver.findElement(TRANSCACTIONS_MENU_LOCATOR).click();
	//	waitForElement(NEW_DEPOSIT_PAGE_LOCATOR, driver, 30);
	//	driver.findElement(NEW_DEPOSIT_PAGE_LOCATOR).click();
		//table/descendant::td
		
		////table/tbody/tr/td[contains(text(),'CheAccount')]
		//List<WebElement> descriptionElements = driver.findElements(By.xpath("//table/descendant::td"));
		
		List<WebElement> descriptionElements = driver.findElements(By.xpath("//table/tbody/tr/td[contains(text(),'CheAccount')]"));
		//JavascriptExecutor js = (JavascriptExecutor)driver;
		//js.executeScript("scroll(0,-600)");
	
		Assert.assertTrue(isDescriptionMatch(expectedDescription, descriptionElements), "Deposit unsucessfull!");
		Thread.sleep(10000);
		}

		private boolean isDescriptionMatch(String expectedDescription, List<WebElement> descriptionElements) {
		for (int i=0; i < descriptionElements.size(); i++) {
		if(expectedDescription.equalsIgnoreCase(descriptionElements.get(i).getText())) {
		return true;
		}
		}
		return false;
		}
		
		
	
	

	@AfterMethod
	public void closeEverything()
    {
    	
		//SCROLL ALL THE WAY DOWN TO THE PAGE
				((JavascriptExecutor) driver)
			     .executeScript("window.scrollTo(0, document.body.scrollHeight)");
		//driver.close();
    	//driver.quit();
    }
}
