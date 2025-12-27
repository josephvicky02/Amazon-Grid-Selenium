package tests;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AmazonTest {

	@Parameters({ "browser" })
	@Test
	public void searchTest(String browser) throws Exception {

		WebDriver driver;

		if (browser.equalsIgnoreCase("chrome")) {
		    ChromeOptions options = new ChromeOptions();
		    options.setCapability("browserName", "chrome");
		    options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu", "--remote-allow-origins=*");
		    driver = new RemoteWebDriver(new URL("http://localhost:4444/"), options);
		}
		else if (browser.equalsIgnoreCase("firefox")) {
		    FirefoxOptions options = new FirefoxOptions();
		    options.setCapability("browserName", "firefox");
		    driver = new RemoteWebDriver(new URL("http://localhost:4444/"), options);
		}
		else if (browser.equalsIgnoreCase("edge")) {
		    EdgeOptions options = new EdgeOptions();
		    options.setCapability("browserName", "MicrosoftEdge");
		    options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--disable-gpu");
		    driver = new RemoteWebDriver(new URL("http://localhost:4444/"), options);
		}
		else {
			throw new Exception("Browser not supported!");
		}

		driver.get("https://www.amazon.in/");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));

		searchBox.sendKeys("iPhone");
		searchBox.submit();

		System.out.println("Browser: " + browser + " | Title: " + driver.getTitle());

		driver.quit();
	}
}
