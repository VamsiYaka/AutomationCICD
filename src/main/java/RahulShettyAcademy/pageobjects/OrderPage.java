package RahulShettyAcademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulShettyAcademy.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents{

	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}


	//driver.findElement(By.cssSelector(".totalRow button")).click();
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;

	
	//driver.findElements(By.cssSelector(".cartSection h3"));
	@FindBy(css="tr td:nth-child(3)")
    List<WebElement> ProductNmaes;
	
	
	public  Boolean verifyOrderDisplay(String ProductName) {
		
		Boolean match = ProductNmaes.stream()
				.anyMatch(cartPorduct -> cartPorduct.getText().equalsIgnoreCase(ProductName));
		return match;
		
	}
	

}



