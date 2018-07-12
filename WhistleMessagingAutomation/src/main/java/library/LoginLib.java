package library;

import org.openqa.selenium.WebDriver;

import framework.ExecutionSetUp;
import webelements.loginWebE;

public class LoginLib {
	
	static WebDriver driver = null;
	public static loginWebE LoginWebE = null;
	
	/**
	 * 
	 * This method is use for login in the application
	 * @since 07-07-2018
	 * 
	 * */
	public static boolean login(String strUsername, String strPassword)
	{
		boolean bstatus = false;
		try
		{
			Log4J.logp.info("Started :::: Login");
			
			driver = ExecutionSetUp.getDriver();
			LoginWebE = loginWebE.getInstance(driver);
			
			LoginWebE.txt_EmailAdd.clear();
			LoginWebE.txt_EmailAdd.sendKeys(strUsername);
			Thread.sleep(2000);
			
			LoginWebE.btn_Next.click();
			CommonLib.waitForObject(LoginWebE.txt_Password, "visibility", 10);
			
			LoginWebE.txt_Password.clear();
			LoginWebE.txt_Password.sendKeys(strPassword);
			Thread.sleep(2000);
			
			LoginWebE.btn_Next.click();
			CommonLib.waitForObject(LoginWebE.lbl_UserName, "visibility", 20);
			
			bstatus = CommonLib.checkElementPresent(LoginWebE.lbl_UserName);
			
			if(bstatus == true)
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
		catch(Exception e)
		{
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
	 * */
	public static boolean logout()
	{
		boolean bstatus = false;
		try
		{
			driver = ExecutionSetUp.getDriver();
			LoginWebE = loginWebE.getInstance(driver);
			
			LoginWebE.lbl_UserName.click();
			CommonLib.waitForObject(LoginWebE.lnk_Logout, "clickable", 10);
			
			LoginWebE.lnk_Logout.click();
			CommonLib.waitForObject(LoginWebE.txt_EmailAdd, "visibility", 20);
			
			if(CommonLib.checkElementPresent(LoginWebE.txt_EmailAdd) == true)
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
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

}
