package RahulShettyAcademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulShettyAcademy.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents{

	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	
	@FindBy(css="[placeholder='Select Country']")
	   WebElement country;
	
	
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	               //button[contains(@class,'ta-item')][2]
	private WebElement selectCountry;
	
	@FindBy(css=".action__submit")
	private	WebElement submit;
	
	private	By results= By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) throws InterruptedException  {
		
		Actions a = new Actions(driver);

		a.sendKeys(country,countryName).build().perform();
		
		Thread.sleep(2000);

		waitForElementToAppear(By.cssSelector(".ta-results"));
	
		selectCountry.click();
		
	}
	
	public ConfirmationPage submitOrder() {
		
		submit.click();
		
	return	new ConfirmationPage(driver);

		
	}
	

}
