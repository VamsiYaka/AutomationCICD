package RahulShettyAcademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RahulShettyAcademy.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements( driver,this);
	}
	
	  //PageFactory
	
	  //WebElement userEmail= driver.findElement(By.id("userEmail"));
	  @FindBy(id="userEmail")
	  WebElement userEmail;
	  
	  
	//  driver.findElement(By.id("userPassword")).sendKeys("Vamsi@0806");
	  
    @FindBy(id="userPassword")
	WebElement PasswordEle;
	
  // driver.findElement(By.id("login")).click();
    @FindBy(id="login")
    WebElement submit;
    
    

    @FindBy(css="[class*='flyInOut']")
    WebElement errorMessage;
    
    
    public ProductCatalog LoginApplication(String email,String password) {
    	
    	
    	userEmail.sendKeys(email);
    	PasswordEle.sendKeys(password);
    	submit.click();
    	
    	ProductCatalog productCatalog= new ProductCatalog(driver);
    	return productCatalog;
    	
    }
    
    public void goTo() {
    	
    	driver.get("https://rahulshettyacademy.com/client");
    }
    
    public String  getErrorMessage() {
    	
    	
    	waitForWebElementToAppear(errorMessage);
    	return errorMessage.getText();
    	 
    	
    	
    }
	

}
