package Assignment;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

public class AmazonSearchStepDefinition {	
	protected WebDriver driver;
	
	 @Before
	    public void setup() {
	        driver = new FirefoxDriver();
	}
		
	@Given("^I launch \"([^\"]*)\"$")
	public void launchAmazon(String url) {
		//Launch Amazon
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(url);
	}
	
	@When("^I enter ([^\"]*) in search and sort by price High to Low$")
	public void searchNsort(String searchKey) {
		//Enter search term in Amazon text-box
		WebElement searchTextBox = driver.findElement(By.id("twotabsearchtextbox"));
		searchTextBox.sendKeys(searchKey);
		driver.findElement(By.className("nav-input")).click();
		
		//Sort by Price: High to Low
		Select dropdown = new Select(driver.findElement(By.id("sort")));
		dropdown.selectByValue("price-desc-rank");
			
	}
	
	@When("^I select ([^\"]*) product and click for details$")
	public void selectProduct(String orderOfItem) {
		String resultID = "result_0";
		
		//Choose the product
		if(orderOfItem.contains("second"))
			resultID = "result_1";

		//Click on the details page
		int count = 0;
		while(count<5)
		{
			try{
				By byElem = By.id(resultID);
				WebDriverWait wait = new WebDriverWait(driver, 20);
				wait.until(ExpectedConditions.presenceOfElementLocated(byElem));
				driver.findElement(byElem).click();
				System.out.println("inside try");
				break;
			} catch (StaleElementReferenceException e) {
			}
			count++;
		}
		
	}
	
	@Then("^I should verify if the product topic contains ([^\"]*)$")
	public void checkResult(String expectedResult) {		

		//Assert if the result contains Nikon D3X
		String productTitle = driver.findElement(By.id("productTitle")).getText();
		assertThat(productTitle, containsString(expectedResult));

	}
	

	@After
	public void closeBrowser() {
		driver.close();
	    driver.quit();
	 }
}
