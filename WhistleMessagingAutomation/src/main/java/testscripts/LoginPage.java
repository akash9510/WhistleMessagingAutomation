package testscripts;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import framework.ExecutionSetUp;
import framework.MainMethod;
import library.CommonLib;
import library.Log4J;
import library.LoginLib;
import webelements.LoginWebE;
public class LoginPage {
	
	static WebDriver driver = null;
	static LoginWebE loginWebE = null;
	static SoftAssert				softAssertion;
	static String strUserName, strPassword;
	

	@BeforeClass
	public static void loginBeforeClass() throws FileNotFoundException, IOException
	{
		driver = ExecutionSetUp.getDriver();
		loginWebE = LoginWebE.getInstance(driver);
		
		Properties logProperties = new Properties();
		logProperties.load(new FileInputStream("src/main/resources/Properties/data.properties"));
		strUserName = logProperties.getProperty("username");
		strPassword = logProperties.getProperty("password");
		
		//Log4J.loadLogger();
		//Log4J.logp.info("In Before Class of Login Page");
	}
	
	/**
	 * 
	 * This method use for login in the application
	 * 
	 * @since 07-07-2018
	 * */
	@Test(description = "Check User should be able to login in the applicatin",priority= 0)
	public static void checkLogin()
	{
		String strActualUserName;
		boolean bstatus = false;
		ExtentTest Login = MainMethod.extent.startTest("Test ID 1 : Login","User should be able to Login in the application").assignCategory("Regression").assignCategory("LoginPage");
		try
		{
			Log4J.logp.info("Started - Test ID 1 : Login");
			
			softAssertion = new SoftAssert();
			
			bstatus = LoginLib.login();
			if(bstatus == true)
			{
				strActualUserName = loginWebE.lbl_UserName.getText().trim();
				if(strActualUserName.equals(strUserName))
				{
					Log4J.logp.info("User will be successfully login in site");
					Assert.assertTrue(true);
					Login.log(LogStatus.PASS, "User will be able to successfully in application");
				}
				else
				{
					Log4J.logp.info("Login un- successfully");
					Assert.assertTrue(false);
					Login.log(LogStatus.FAIL, "Login Un - Successfull");
				}
			}
			else
			{
				Log4J.logp.info("Test ID 1 : Failed : Login un- successfully ");
				Assert.assertTrue(false);
				Login.log(LogStatus.FAIL, "Login Un - Successfull");
			}
			
			Log4J.logp.info("Ended Test ID 1 : Login");
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			softAssertion.assertTrue(false, "Test ID 1 : Login Failed");
		}
		finally {

			if (Login.getRunStatus().toString().equalsIgnoreCase("unknown"))
				Login.log(LogStatus.FAIL, "Test ID 1 : Failed for Unknown status.");
			MainMethod.extent.endTest(Login);

			if (Login.getRunStatus().toString().equalsIgnoreCase("FAIL"))
			{
				Login.log(LogStatus.FAIL, "Test ID 1 : Login is failed.");
			}
			else
			{
				Login.log(LogStatus.PASS, "Test ID 1 : Login is passed.");
			}
			
			softAssertion.assertAll();
			LoginLib.logout();
		}
	}
	
	/**
	 * 
	 * This method is use for check Forget passowrd 
	 * 
	 * */
	@Test(description = "Check forget password functionality",priority = 1)
	public static void checkForgetPassword()
	{
		String strActualMessage, strExpectedMessage;
		ExtentTest ForgetPassword = MainMethod.extent.startTest("Test ID 3 : Forget Passowrd ?").assignCategory("Regression");
		try
		{
			Log4J.logp.info("Started - Test ID 3 : Forget Passowrd ?");
			
			softAssertion = new SoftAssert();
			
			strExpectedMessage = "We've sent you an email with a link to reset your password.";
			
			loginWebE.btn_Forget_Password.click();
			CommonLib.waitForObject(loginWebE.txt_EmailAdd, "visibility", 10);
			
			loginWebE.txt_EmailAdd.clear();
			loginWebE.txt_EmailAdd.sendKeys(strUserName);
			
			loginWebE.btn_Next.click();
			CommonLib.waitForObject(loginWebE.lbl_SuccessMessage, "visibility", 10);
			
			strActualMessage = loginWebE.lbl_SuccessMessage.getText().trim();
			
			if(strExpectedMessage.equals(strActualMessage))
			{
				Log4J.logp.info(strExpectedMessage + " :: has been displayed after entered valid email address");
				softAssertion.assertTrue(true);
				ForgetPassword.log(LogStatus.PASS, "Message has been displayed");
			}
			else
			{
				softAssertion.assertTrue(false);
				ForgetPassword.log(LogStatus.FAIL, "Not found any message");
			}
			
			Log4J.logp.info("Ended - Test ID 3 : Forget Passowrd ?");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Log4J.logp.info("Failed : Test ID 3 : Forget Passowrd ?");
			softAssertion.assertTrue(false,"Test ID 3 : Failed");
		}
		finally {

			if (ForgetPassword.getRunStatus().toString().equalsIgnoreCase("unknown"))
				ForgetPassword.log(LogStatus.FAIL, "Test ID 3 : Failed for Unknown status.");
			MainMethod.extent.endTest(ForgetPassword);

			if (ForgetPassword.getRunStatus().toString().equalsIgnoreCase("FAIL"))
			{
				ForgetPassword.log(LogStatus.FAIL, "Test ID 3 : Forget Passowrd ? :: Failed");
			}
			else
			{
				ForgetPassword.log(LogStatus.PASS, "Test ID 3 : Forget Passowrd ? :: Passed");
			}
			
			softAssertion.assertAll();
			driver.get("https://staging.v2whistle.com");
		}
	}
	
	/**
	 * 
	 * This method is check User can able to Logout from the system
	 * 
	 * */
	@Test(description = "Logout from the application",priority = 2)
	public static void checkLogout()
	{
		boolean bstatus = false;
		ExtentTest checkLogout = MainMethod.extent.startTest("Test ID 94 : Logout").assignCategory("Regression");
		try
		{
			softAssertion = new SoftAssert();
			LoginLib.login();
			Thread.sleep(2000);
			
			bstatus = LoginLib.logout();
			
			if(bstatus == true)
			{
				Log4J.logp.info("Logout Succesfully from the application");
				softAssertion.assertTrue(true);
				checkLogout.log(LogStatus.PASS, "Logout Successfully from the application");
			}
			else
			{
				Log4J.logp.info("Logout un-successfully");
				softAssertion.assertTrue(false);
				checkLogout.log(LogStatus.FAIL, "Logout un - successfully");
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			softAssertion.assertTrue(false,"checkLogout");
		}
		finally {

			if (checkLogout.getRunStatus().toString().equalsIgnoreCase("unknown"))
				checkLogout.log(LogStatus.FAIL, "Test ID 94 : Failed for Unknown status.");
			MainMethod.extent.endTest(checkLogout);

			if (checkLogout.getRunStatus().toString().equalsIgnoreCase("FAIL"))
			{
				checkLogout.log(LogStatus.FAIL, "Test ID 94 : Logout :: Failed");
			}
			else
			{
				checkLogout.log(LogStatus.PASS, "Test ID 94 : Logout :: Passed");
			}
			
			softAssertion.assertAll();
			driver.get("https://staging.v2whistle.com");
		}
	}
	
	@AfterClass
	public static void loginAfterClass()
	{
		//Log4J.logp.info("In After Class of Login Page");
	}

}

