package RahulShettyAcademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import RahulShettyAcademy.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest  {

public	WebDriver driver;
public LandingPage landingPage;
	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Rahulshetty//resources//GlobalData.properties");

		prop.load(fis);
		
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
		//this bover command help to run from the terminal , if we give any value from the termainal command(ex: mvn test -PRegression)
		//mvn test -Dbroswer(D in this command  specifies the value of the bwowser)

		//String browserName = prop.getProperty("browser");
		
		

		if (browserName.contains("chrome")) {

			//WebDriverManager.chromedriver().setup();
			
			ChromeOptions options= new ChromeOptions();
			System.setProperty("Webdriver.chrome.driver", "C:/Users/vamsi/OneDrive/Documents/chromedriver-win32/chromedriver-win32/chromedriver.exe");
			
			if(browserName.contains("headless"))
			{
			options.addArguments("headless");
			}

			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));//full secreen mode

		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("Webdriver.gecko.driver", "C:/Users/vamsi/Downloads/geckodriver-v0.35.0-win32/gecodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().setSize(new Dimension(1440,900));
		}

		else if (browserName.equalsIgnoreCase("edge")) {
			
			System.setProperty("Webdriver.edge.driver", "C:/Users/vamsi/Downloads/edgedriver_win64/msedgedriver.exe");
			driver = new EdgeDriver();
			driver.manage().window().setSize(new Dimension(1440,900));
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
		return driver;
	}
	
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string
		
		String jsonContent=FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		//string to HashMap JacksonDataBind
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data =	mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
			
		});
		return data;
		
		
		
		
		
		
				
	}
	
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {

		   TakesScreenshot  ts=(TakesScreenshot)driver;
		File source=   ts.getScreenshotAs(OutputType.FILE);
		File file=new File(System.getProperty("user.dir")+"//reports//"+ testCaseName + ".ping");
		
		FileUtils.copyFile(source, file);
	return System.getProperty("user.dir")+"//reports//"+ testCaseName + ".ping";
	
	}
	
	
@BeforeMethod(alwaysRun=true)
	
public LandingPage launchApplication() throws IOException {
	
 driver=	initializeDriver();
  landingPage = new LandingPage(driver);

	landingPage.goTo();
	return landingPage;
	
}

@AfterMethod(alwaysRun=true)
public void tearDown(){
	driver.close();
 
	
}
}
