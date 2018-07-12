package testscripts;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import framework.ExecutionSetUp;
import library.Log4J;
import library.LoginLib;
import webelements.loginWebE;
public class LoginPage {
	
	static WebDriver driver = null;
	static loginWebE LoginWebE = null;
	static SoftAssert				softAssertion;
	

	@BeforeClass
	public static void loginBeforeClass()
	{
		driver = ExecutionSetUp.getDriver();
		LoginWebE = loginWebE.getInstance(driver);
		
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
		String strUserName, strPassword,strActualUserName;
		boolean bstatus = false;
		//ExtentTest Login = MainMethod.extent.startTest("Login","User should be able to Login in the application").assignCategory("Regression").assignCategory("LoginPage");
		try
		{
			Log4J.logp.info("Started - Test ID 1 : Login");
			
			softAssertion = new SoftAssert();
			Properties logProperties = new Properties();
			logProperties.load(new FileInputStream("src/main/resources/Properties/data.properties"));
			strUserName = logProperties.getProperty("username");
			strPassword = logProperties.getProperty("password");
			
			bstatus = LoginLib.login(strUserName, strPassword);
			if(bstatus == true)
			{
				strActualUserName = LoginWebE.lbl_UserName.getText().trim();
				if(strActualUserName.equals(strUserName))
				{
					Log4J.logp.info("User will be successfully login in site");
					Assert.assertTrue(true);
					//Login.log(LogStatus.PASS, "User will be able to successfully in application");
				}
				else
				{
					Log4J.logp.info("Login un- successfully");
					Assert.assertTrue(false);
					//Login.log(LogStatus.FAIL, "Login Un - Successfull");
				}
			}
			else
			{
				Log4J.logp.info("Test ID 1 : Failed : Login un- successfully ");
				Assert.assertTrue(false);
				//Login.log(LogStatus.FAIL, "Login Un - Successfull");
			}
			
			Log4J.logp.info("Ended Test ID 1 : Login");
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			softAssertion.assertTrue(false, "Login Failed");
		}
		finally {

			/*if (Login.getRunStatus().toString().equalsIgnoreCase("unknown"))
				Login.log(LogStatus.FAIL, "Failed for Unknown status.");
			//NewLandingPage.appendChild(bulkSelection);
			MainMethod.extent.endTest(Login);

			if (Login.getRunStatus().toString().equalsIgnoreCase("FAIL"))
			{
				Login.log(LogStatus.FAIL, "Login is failed.");
				MainMethod.extent.flush();
				//ComMethods.sendMail("bulkSelection");
			}
			else
			{
				Login.log(LogStatus.PASS, "Login is passed.");
			}*/
			
			softAssertion.assertAll();
		}
	}
	
	//@Test(description = "test description",priority = 1)
	
	
	@AfterClass
	public static void loginAfterClass()
	{
		//Log4J.logp.info("In After Class of Login Page");
	}

}

