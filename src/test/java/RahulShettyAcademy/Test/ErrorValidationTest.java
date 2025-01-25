package RahulShettyAcademy.Test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import RahulShettyAcademy.TestComponents.Retry;

import RahulShettyAcademy.TestComponents.BaseTest;
import RahulShettyAcademy.pageobjects.CartPage;
import RahulShettyAcademy.pageobjects.CheckoutPage;
import RahulShettyAcademy.pageobjects.ConfirmationPage;
import RahulShettyAcademy.pageobjects.ProductCatalog;

public class ErrorValidationTest extends BaseTest{

	@Test(groups= {"ErrorHanadling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException
	{

		String ProductName = "IPHONE 13 PRO";

		 landingPage.LoginApplication("vamsyaka@gmail.com", "Vams@0806");
		 
		 Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
     


		
	}

	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{

		String ProductName = "IPHONE 13 PRO";

		ProductCatalog productCatalog = landingPage.LoginApplication("vamsiyaka@gmail.com", "Vamsi@0806");

		List<WebElement> products = productCatalog.getProductList();

		productCatalog.addPorductToCart(ProductName);
		
		Thread.sleep(1000);

		CartPage cartPage = productCatalog.goToCartPage();

		Boolean match = cartPage.verifyProductsDisplay("Zara Coat 33");

		Assert.assertFalse(match);

		

		
	}
}
