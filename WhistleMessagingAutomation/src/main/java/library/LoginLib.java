package library;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import framework.ExecutionSetUp;
import webelements.LoginWebE;

public class LoginLib
{

	static WebDriver		driver		= null;
	public static LoginWebE	loginWebE	= null;

	/**
	 * 
	 * This method is use for login in the application
	 * 
	 * @since 07-07-2018
	 * 
	 */
	public static boolean login()
	{
		boolean bstatus = false;
		String strUserName, strPassword;
		try
		{
			Log4J.logp.info("Started :::: Login");

			Properties logProperties = new Properties();
			logProperties.load(new FileInputStream("src/main/resources/Properties/data.properties"));
			strUserName = logProperties.getProperty("username");
			strPassword = logProperties.getProperty("password");

			driver = ExecutionSetUp.getDriver();
			loginWebE = LoginWebE.getInstance(driver);

			loginWebE.txt_EmailAdd.clear();
			loginWebE.txt_EmailAdd.sendKeys(strUserName);
			Thread.sleep(2000);

			loginWebE.btn_Next.click();
			CommonLib.waitForObject(loginWebE.txt_Password, "visibility", 10);

			loginWebE.txt_Password.clear();
			loginWebE.txt_Password.sendKeys(strPassword);
			Thread.sleep(2000);

			loginWebE.btn_Next.click();
			CommonLib.waitForObject(loginWebE.lbl_UserName, "visibility", 20);

			bstatus = CommonLib.checkElementPresent(loginWebE.lbl_UserName);

			if (bstatus == true)
			{
				Log4J.logp.info("Login Successfully...");
				return true;
			}
			else
			{
				Log4J.logp.info("Login un- Successfully...");
				return false;
			}
		}
		catch (Exception e)
		{
			CommonLib.takeScreenshots();
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * This method is use for logout from the application
	 * 
	 * @since 12-07-208
	 * 
	 */
	public static boolean logout()
	{
		boolean bstatus = false;
		try
		{
			driver = ExecutionSetUp.getDriver();
			loginWebE = LoginWebE.getInstance(driver);

			loginWebE.lbl_UserName.click();
			CommonLib.waitForObject(loginWebE.lnk_Logout, "clickable", 10);

			loginWebE.lnk_Logout.click();
			CommonLib.waitForObject(loginWebE.txt_EmailAdd, "visibility", 20);

			if (CommonLib.checkElementPresent(loginWebE.txt_EmailAdd) == true)
			{
				bstatus = true;
				Log4J.logp.info("Logout successfully");
			}
			else
			{
				bstatus = false;
				Log4J.logp.info("Logout un - successfully");
			}
			return bstatus;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

}
