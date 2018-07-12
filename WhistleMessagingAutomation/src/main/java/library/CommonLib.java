package library;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.ExecutionSetUp;

public class CommonLib {
	
	static WebDriver driver = null;
	static WebDriverWait wait;
	
	public static boolean waitForObject(WebElement webe, String strProperty, long d)
	{
		try
		{
			Log4J.logp.info("---------------- Started - waitForObject ----------------");
			driver = ExecutionSetUp.getDriver();

			wait = new WebDriverWait(driver, d);

			if (strProperty.equalsIgnoreCase("visibility"))
			{
				wait.until(ExpectedConditions.visibilityOf(webe));
			}
			else if (strProperty.equalsIgnoreCase("clickable"))
			{
				wait.until(ExpectedConditions.elementToBeClickable(webe));
			}
			else if (strProperty.equalsIgnoreCase("selectable"))
			{

				wait.until(ExpectedConditions.elementToBeSelected(webe));
			}
			else
			{
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(strProperty));
			}

			wait = null;
			Log4J.logp.info("---------------- Ended - waitForObject ----------------");
			return true;

		}
		catch (Exception e)
		{
			Log4J.logp.error("Errors in waitForObject  ");
			return false;
		}
	}
	
	
	/**
	 * This method is to check for element is present or not
	 * 
	 * @param webe
	 * @return
	 */
	public static boolean checkElementPresent(WebElement webe)
	{
		try
		{
			Log4J.logp.info("---------------- Started - checkElementPresent  ----------------");

			driver = ExecutionSetUp.getDriver();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			webe.getTagName();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Log4J.logp.info("---------------- Ending - checkElementPresent True ----------------");

			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.info("---------------- Ending - checkElementPresent False ----------------");
			return false;
		}
	}

}
