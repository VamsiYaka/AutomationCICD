package RahulShettyAcademy.stepDefintions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import RahulShettyAcademy.TestComponents.BaseTest;
import RahulShettyAcademy.pageobjects.CartPage;
import RahulShettyAcademy.pageobjects.CheckoutPage;
import RahulShettyAcademy.pageobjects.ConfirmationPage;
import RahulShettyAcademy.pageobjects.LandingPage;
import RahulShettyAcademy.pageobjects.ProductCatalog;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {
	
	
public LandingPage landingPage;
public ProductCatalog productCatalog;
public ConfirmationPage confirmationPage ;
	
	@Given("I landed on the Ecommerce page")
	public void I_landed_on_the_Ecommerce_page() throws IOException {
		
		
		landingPage=launchApplication();
		//code
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String userName, String password){
		
		   productCatalog = landingPage.LoginApplication(userName,password);
		}
	
	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) throws InterruptedException {
		
		List<WebElement> products = productCatalog.getProductList();

		productCatalog.addPorductToCart(productName);
		
	}
	@When("^Checkout  (.+) and submit the order$")
	public void checkout_submit_order(String productName ) throws InterruptedException {
		
		Thread.sleep(1000);

		CartPage cartPage = productCatalog.goToCartPage();
		
		Boolean match = cartPage.verifyProductsDisplay(productName);
		


       Assert.assertTrue(match);

		CheckoutPage checkoutPage = cartPage.goToCheckOut();

		checkoutPage.selectCountry("india");

	    confirmationPage = checkoutPage.submitOrder();

		
		
	}
	
	  @Then("{string}message is dispalyed on the confirmationPage") 
	  public void thankyou_for_the_order_message_is_dispalyed_on_the_confirmation_page(String string) {
	  
	  
	 String ConfirmationMessage = confirmationPage.getConfrimationMessage();
	  
	 Assert.assertTrue(ConfirmationMessage.equalsIgnoreCase(string));
	  
	  driver.close();
	  
	  }
	 
	//another method to implement
	/*@Then("\"THANKYOU FOR THE ORDER.\"message is dispalyed on the confirmationPage")
	public void thankyou_for_the_order_message_is_dispalyed_on_the_confirmation_page() {

		String ConfirmationMessage = confirmationPage.getConfrimationMessage();

		Assert.assertTrue(ConfirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	   
	    driver.close();
}*/
	  
	 @Then("^\"([^\"]*)\"message is displayed$")
	  public void incorrect_email_or_password_message_is_displayed(String strArg1) {
		  Assert.assertEquals(strArg1, landingPage.getErrorMessage());
		  driver.close();
	  }
	 


/*@Then("\"Incorrect email or password.\"message is displayed")
public void incorrect_email_or_password_message_is_displayed() {
	Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	driver.close();
  
}*/
}


