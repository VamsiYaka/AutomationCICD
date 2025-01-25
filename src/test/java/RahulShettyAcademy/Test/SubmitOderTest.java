package RahulShettyAcademy.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RahulShettyAcademy.TestComponents.BaseTest;
import RahulShettyAcademy.pageobjects.CartPage;
import RahulShettyAcademy.pageobjects.CheckoutPage;
import RahulShettyAcademy.pageobjects.ConfirmationPage;
import RahulShettyAcademy.pageobjects.LandingPage;
import RahulShettyAcademy.pageobjects.OrderPage;
import RahulShettyAcademy.pageobjects.ProductCatalog;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOderTest extends BaseTest {

	String ProductName = "IPHONE 13 PRO";

	
	
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {

		ProductCatalog productCatalog = landingPage.LoginApplication(input.get("email"),input.get("password"));

		List<WebElement> products = productCatalog.getProductList();

		productCatalog.addPorductToCart(input.get("Product"));

		Thread.sleep(1000);

		CartPage cartPage = productCatalog.goToCartPage();

		Boolean match = cartPage.verifyProductsDisplay(input.get("Product"));

		Assert.assertTrue(match);

		CheckoutPage checkoutPage = cartPage.goToCheckOut();

		checkoutPage.selectCountry("india");

		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		String ConfirmationMessage = confirmationPage.getConfrimationMessage();

		Assert.assertTrue(ConfirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		Thread.sleep(2000);
		
		
		
		

	}
	
	

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHisteoryTest() {

		ProductCatalog productCatalog = landingPage.LoginApplication("vamsiyaka@gmail.com", "Vamsi@0806");

		OrderPage orderPage = productCatalog.goToOrdersPage();

		Assert.assertTrue(orderPage.verifyOrderDisplay(ProductName));
	}

	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		
		//  List<HashMap<String , String>> Data=   getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
	//	  return new Object[][] {{Data.get(0)},{Data.get(1)}};
		 
		

		 List<HashMap<String , String>>  data=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//RahulShettyAcademy//data//PurchaseOrder.json");
		 
			
		return	new Object[][]  {{data.get(0)}, {data.get(1)}};
	
	
}
	/*
	 * HashMap<String,String> map=new HashMap<String,String>();
	 * 
	 * map.put("email", "anshika@gmail.com"); map.put("password", "Iamking@000");
	 * map.put("Product", "BANARSI SAREE");
	 * 
	 * HashMap<String,String> map1=new HashMap<String,String>();
	 * 
	 * map1.put("email", "vamsiyaka@gmail.com"); map1.put("password", "Vamsi@0806");
	 * map1.put("Product", "IPHONE 13 PRO");
	 */

	
}
