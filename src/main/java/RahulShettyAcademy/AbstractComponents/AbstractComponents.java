package RahulShettyAcademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import RahulShettyAcademy.pageobjects.CartPage;
import RahulShettyAcademy.pageobjects.OrderPage;

public class AbstractComponents {
	
	
	
	WebDriver driver;
	
	
	
	
	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
      @FindBy(css="[routerlink*='cart']")
      WebElement cartHeader;
	
      
      @FindBy(css="[routerlink*='myorders']")
      WebElement OderHeader;
	
	
	
	public void waitForElementToAppear(By findBy) {
	
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	
	}
	
	public void waitForWebElementToAppear(WebElement findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOf(findBy));
		
		}
	
	public void waitforElementToDissappear(WebElement ele) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.invisibilityOf(ele));
		
	}
	
	public CartPage goToCartPage() {
		cartHeader.click();
		
		CartPage cartPage=new CartPage(driver);
		
		return cartPage;
	}
	
	public OrderPage goToOrdersPage() {
		OderHeader.click();
		
		OrderPage orderPage= new OrderPage(driver);
		
		return orderPage;
	}

	


}
