package RahulShettyAcademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import RahulShettyAcademy.AbstractComponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents {

	WebDriver driver;

	public ProductCatalog(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// PageFactory

	// List<WebElement> Products = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css = ".mb-3")
	List<WebElement> product;
	
	
	
	
	
	//driver.findElement(By.cssSelector(".ng-animating")))
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	

	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By ToastMessgage  = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return product;
	}

	public WebElement getProductsByName(String productName) {

		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		return prod;

	}

	public void addPorductToCart(String productName) {

		WebElement prod = getProductsByName(productName);

		prod.findElement(addToCart).click();
		  
		waitForElementToAppear(ToastMessgage);
		
		waitforElementToDissappear(spinner);
		
		
		
	}

}
