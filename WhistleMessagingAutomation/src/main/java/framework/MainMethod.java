package framework;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;

import library.CommonLib;
import library.Log4J;
import webelements.LoginWebE;

public class MainMethod
{

	static String				strBrowserName	= null;
	static WebDriver			driver			= null;
	static LoginWebE			LoginWebE		= null;
	public static ExtentReports	extent;

	@BeforeSuite
	public static void driverStart()
	{
		String strURl;
		try
		{
			Log4J.logp.info("Execution Started");

			extent = new ExtentReports("src/main/resources/AdvanceExtentReport/ExtentReportMaven.html", true);
			extent.loadConfig(new File("src/main/resources/AdvanceExtentReport/extent-config.xml"));

			Log4J.loadLogger();

			Properties logProperties = new Properties();

			logProperties.load(new FileInputStream("src/main/resources/Properties/data.properties"));

			strBrowserName = logProperties.getProperty("browser");

			Log4J.logp.info("Browser is :::" + strBrowserName);

			strURl = logProperties.getProperty("url");

			ExecutionSetUp.launchBrowser(strBrowserName);

			driver = ExecutionSetUp.getDriver();
			LoginWebE = LoginWebE.getInstance(driver);

			driver.get(strURl);
			CommonLib.waitForObject(LoginWebE.txt_EmailAdd, "visibility", 20);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@AfterSuite
	public static void driverEnd()
	{
		try
		{
			extent.flush();
			extent.close();
			driver.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
