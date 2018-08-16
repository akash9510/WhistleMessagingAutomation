package framework;

import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import library.CommonLib;
import library.Log4J;

public class ExecutionSetUp
{

	public static WebDriver driver = null;

	public static boolean launchBrowser(String strBrowserName)
	{
		Log4J.loadLogger();
		try
		{
			Log4J.logp.info("Started :::: LaunchBrowser");

			if (strBrowserName.equalsIgnoreCase("Chrome"))
			{
				Log4J.logp.info("This is Chrome Browser..");

				//ChromeOptions options = new ChromeOptions();

				//options.addArguments("--enable-notifications");

				System.setProperty("webdriver.chrome.driver", "src/main/resources/Drivers/chromedriver");

				//driver = new ChromeDriver();

				ChromeOptions options = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 1);
				//1-Allow, 2-Block, 0-default
				options.setExperimentalOption("prefs", prefs);
				driver = new ChromeDriver(options);

				//driver.manage().window().fullscreen();
				//maximizeBrowser();

				//driver.manage().window().maximize();

				/*ChromeOptions options = new ChromeOptions();
				options.addArguments("--start-maximized");
				driver = new ChromeDriver();*/

			}
			else if (strBrowserName.equals("firefox"))
			{
				Log4J.logp.info("This is Firefox browser");

				System.setProperty("webdriver.gecko.driver", "src/main/resources/Drivers/geckodriver");

				driver = new FirefoxDriver();

				driver.manage().window().maximize();
			}
			else if (strBrowserName.equals("safari"))
			{
				Log4J.logp.info("This is Safari browser");

				//System.setProperty("webdriver.gecko.driver", "src/main/resources/Drivers/geckodriver");

				driver = new SafariDriver();

				//driver.manage().window().maximize();
			}

			Log4J.logp.info("Ended :::: LaunchBrowser");

			return true;
		}
		catch (Exception e)
		{
			//CommonLib.takeScreenshots();
			e.printStackTrace();
			return false;
		}
	}

	public static boolean maximizeBrowser()
	{
		try
		{
			java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Point position = new Point(0, 0);
			driver.manage().window().setPosition(position);
			Dimension maximizedScreenSize = new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
			driver.manage().window().setSize(maximizedScreenSize);
			return true;
		}
		catch (Exception e)
		{
			CommonLib.takeScreenshots();
			e.printStackTrace();
			return false;
		}
	}

	public static WebDriver getDriver()
	{
		return driver;
	}

}