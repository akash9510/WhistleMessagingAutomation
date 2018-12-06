package framework;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;

import library.CommonLib;
import library.Log4J;
import webelements.LoginWebE;

public class MainMethod
{

	//static String				strBrowserName	= null;
	static WebDriver			driver		= null;
	static LoginWebE			LoginWebE	= null;
	public static ExtentReports	extent;

	@BeforeTest
	@Parameters({ "config", "environment" })
	public static void driverStart(String config, String environment)
	{
		String strURl, strFile;
		try
		{
			Log4J.logp.info("Execution Started");

			System.out.println(config + "  and " + environment);

			SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyHHmmss");
			Date date = new Date();

			strFile = format.format(date);

			extent = new ExtentReports("src/main/resources/AdvanceExtentReport/" + strFile + ".html", true);
			extent.loadConfig(new File("src/main/resources/AdvanceExtentReport/extent-config.xml"));

			Log4J.loadLogger();

			Properties logProperties = new Properties();

			logProperties.load(new FileInputStream("src/main/resources/Properties/data.properties"));

			//strBrowserName = logProperties.getProperty("browser");

			Log4J.logp.info("Browser is :::" + environment);

			strURl = logProperties.getProperty("url");

			ExecutionSetUp.launchBrowser(config, environment);

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

	@AfterTest
	public static void driverEnd()
	{
		try
		{
			extent.flush();
			extent.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@AfterSuite
	public static void endSuite()
	{
		try
		{
			driver.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
