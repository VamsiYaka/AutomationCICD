package RahulShettyAcademy.Test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import RahulShettyAcademy.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		// WebDriverManager.chromedriver().setup();
//comments
		String ProductName = "IPHONE 13 PRO";

		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://rahulshettyacademy.com/client");
		
		LandingPage landingPage=new LandingPage(driver);

		driver.manage().window().maximize();

		driver.findElement(By.id("userEmail")).sendKeys("vamsiyaka@gmail.com");

		driver.findElement(By.id("userPassword")).sendKeys("Vamsi@0806");

		driver.findElement(By.id("login")).click();
		
		
		

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> Products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = Products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst()
				.orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[routerlink*='cart']")));

	 Thread.sleep(2000);

		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

		Boolean match = cartProducts.stream()
				.anyMatch(cartPorduct -> cartPorduct.getText().equalsIgnoreCase(ProductName));

		Assert.assertTrue(match);

		driver.findElement(By.cssSelector(".totalRow button")).click();

		Actions a = new Actions(driver);

		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));

		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();

		driver.findElement(By.cssSelector(".action__submit")).click();

		String ConfirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();

		Assert.assertTrue(ConfirmMessage.equals("THANKYOU FOR THE ORDER."));

		driver.close();

	}


}
