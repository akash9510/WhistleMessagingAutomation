package testscripts;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
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
import webelements.HomePageWebE;
import webelements.LoginWebE;

public class HomePage {
	
	static WebDriver driver = null;
	static HomePageWebE homePageWebE = null;
	static LoginWebE loginWebE = null;
	static SoftAssert				softAssertion;
	
	@BeforeClass
	public static void beforeClassHomePage()
	{
		driver = ExecutionSetUp.getDriver();
		homePageWebE = HomePageWebE.getInstance(driver);
		loginWebE = LoginWebE.getInstance(driver);
	}
	
	
	/**
	 * This method is use for Start Conversation
	 * 
	 * */
	@Test(description = "Start Conversation",priority = 0)
	public static void startConversation()
	{
		String strGuestName, strPhoneNo, strEmail, strArrivalDate, strDepartDate;
		boolean bstatus = false;
		ExtentTest StartConversation = MainMethod.extent.startTest("Test ID 4 : Start Conversation").assignCategory("Regression");
		try
		{
			softAssertion = new SoftAssert();
			LoginLib.login();
			
			Properties properties = new Properties();
			properties.load(new FileInputStream("src/main/resources/Properties/data.properties"));
			strGuestName = properties.getProperty("guestname");
			strPhoneNo = properties.getProperty("phoneno");
			strEmail = properties.getProperty("username");
			strArrivalDate = properties.getProperty("arrivaldate");
			strDepartDate = properties.getProperty("departdate");
			
			//homePageWebE.txt_GuestName.clear();
			homePageWebE.txt_GuestName.sendKeys(strGuestName);
			Thread.sleep(5000);
			
			//homePageWebE.txt_PhoneNo.clear();
			homePageWebE.txt_PhoneNo.sendKeys(strPhoneNo);
			Thread.sleep(2000);
			
			homePageWebE.txt_ArrivalDate.click();
			Thread.sleep(3000);
			
			bstatus = CommonLib.selectDate(strArrivalDate,"Arrival");
			
			homePageWebE.txt_DepartureDate.click();
			Thread.sleep(3000);
			
			bstatus = CommonLib.selectDate(strDepartDate,"Depart");
			
			//homePageWebE.txt_Notes.clear();
			homePageWebE.txt_Notes.sendKeys("Test Notes");
			Thread.sleep(2000);
			
			//homePageWebE.txt_Email.clear();
			homePageWebE.txt_Email.sendKeys(strEmail);
			Thread.sleep(2000);
			
			//shomePageWebE.txt_Message.clear();
			homePageWebE.txt_Message.sendKeys("Hello");
			Thread.sleep(2000);
			
			homePageWebE.btn_Send.click();
			CommonLib.waitForObject(homePageWebE.lbl_Messages.get(0), "visibility", 20);
			
			int intMessageSize = homePageWebE.lbl_Messages.size();
			
			System.out.println(homePageWebE.lbl_Messages.get(intMessageSize-1).getText());
			
			if(homePageWebE.lbl_Messages.get(intMessageSize-1).getText().equals("Hello"))
			{
				Log4J.logp.info("Message Sent Successfully");
				softAssertion.assertTrue(true);
				StartConversation.log(LogStatus.PASS, "Message Sent Successfully");
			}
			else
			{
				Log4J.logp.info("Message not sent");
				softAssertion.assertTrue(true);
				StartConversation.log(LogStatus.FAIL, "Message not Sent");
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			softAssertion.assertTrue(false, "Test ID 4 : Start Conversation Failed");
		}
		finally {

			if (StartConversation.getRunStatus().toString().equalsIgnoreCase("unknown"))
				StartConversation.log(LogStatus.FAIL, "Test ID 4 : Failed for Unknown status.");
			MainMethod.extent.endTest(StartConversation);

			if (StartConversation.getRunStatus().toString().equalsIgnoreCase("FAIL"))
			{
				StartConversation.log(LogStatus.FAIL, "Test ID 4 : Start Conversation is failed.");
			}
			else
			{
				StartConversation.log(LogStatus.PASS, "Test ID 4 : Start Conversation is passed.");
			}
			
			softAssertion.assertAll();
			LoginLib.logout();
		}
	}
	
	/**
	 * This method is use for check Dropdown behavior and Toggle behavior of application
	 * 
	 * Dropdown Toolbar
	 * Turn Sound ON/OFF
	 * Turn Notification ON/OFF
	 * User dropdown
	 * 
	 * */
	@Test(description = "To check Dropdown and Toggle behavior on Home Page",priority = 1)
	public static void checkDropdownToggleBehavior()
	{
		ExtentTest checkDropdownToggleBehavior = MainMethod.extent.startTest("To Check Dropdown and Toggle behavior on Home Page").assignCategory("Regression");
		ExtentTest Child1 = null;
		ExtentTest Child2 = null;
		ExtentTest Child3 = null;
		ExtentTest Child4 = null;
		String strBackground;
		try
		{
			Child1 = MainMethod.extent.startTest("Test ID 5 : Whistle Dropdown Toolbar");
			Child2 = MainMethod.extent.startTest("Test ID 6 : Turn Sound ON/OFF");
			Child3 = MainMethod.extent.startTest("Test ID 7 : Turn Notification ON/OFF");
			Child4 = MainMethod.extent.startTest("Test ID 8 : User dropdown");
			
			softAssertion = new SoftAssert();
			
			LoginLib.login();
			
			// Test ID 5
			
			homePageWebE.lst_WhistleDropdown.click();
			Thread.sleep(2000);
			
			if(CommonLib.checkElementPresent(homePageWebE.lst_ActiveDropdown) == true)
			{
				Log4J.logp.info("Dropdown will appear after Click on Whistle dropwon");
				softAssertion.assertTrue(true);
				Child1.log(LogStatus.PASS, "Dropown will appear after click on Whistle dropdown");
			}
			else
			{
				Log4J.logp.error("Dropdpwn not opened");
				softAssertion.assertTrue(false);
				Child1.log(LogStatus.FAIL, "Dropdown not opened");
			}
			homePageWebE.lst_WhistleDropdown.click();
			Thread.sleep(2000);
			
			
			
			// Test ID 6
			
			strBackground = homePageWebE.lbl_ToggleBackground.getCssValue("background-color");
			Log4J.logp.info(strBackground);
			
			if(strBackground.trim().equalsIgnoreCase("rgba(189, 189, 189, 1)"))
			{
				Log4J.logp.info("By default Sound toggle is off");
				softAssertion.assertTrue(true);
				Child2.log(LogStatus.PASS, "By default sound toggle is off");
				
				homePageWebE.ico_SoundToggle.click();
				Thread.sleep(2000);
				
				strBackground = homePageWebE.lbl_ToggleBackground.getCssValue("background-color");
				Log4J.logp.info(strBackground);
				if(strBackground.trim().equalsIgnoreCase("rgba(0, 179, 134, 0.5)"))
				{
					Log4J.logp.info("After click on Sound toggle it will be on");
					softAssertion.assertTrue(true);
					Child2.log(LogStatus.PASS, "After click on Sound toggle it will be on");
					
					homePageWebE.ico_SoundToggle.click();
					Thread.sleep(2000);
				}
				else
				{
					Log4J.logp.info("Sound toggle does not on after click on it");
					softAssertion.assertTrue(false);
					Child2.log(LogStatus.FAIL, "Sound toggle does not on after click on it");
				}
			}
			else
			{
				Log4J.logp.info("Sound toggle not off by default");
				softAssertion.assertTrue(false);
				Child2.log(LogStatus.FAIL, "Sound toggle not off be default");
			}
			
			// Test ID 7
			
			strBackground = homePageWebE.lbl_NotificationBackground.getCssValue("background-color");
			Log4J.logp.info(strBackground);
			
			if(strBackground.trim().equalsIgnoreCase("rgba(189, 189, 189, 1)"))
			{
				Log4J.logp.info("By default Sound toggle is off");
				softAssertion.assertTrue(true);
				Child3.log(LogStatus.PASS, "By default sound toggle is off");
				
				homePageWebE.ico_NotificationToggel.click();
				Thread.sleep(2000);
				
				strBackground = homePageWebE.lbl_NotificationBackground.getCssValue("background-color");
				Log4J.logp.info(strBackground);
				if(strBackground.trim().equalsIgnoreCase("rgba(0, 179, 134, 0.5)"))
				{
					Log4J.logp.info("After click on Notification toggle it will be on");
					softAssertion.assertTrue(true);
					Child3.log(LogStatus.PASS, "After click on Notification toggle it will be on");
					
					homePageWebE.ico_NotificationToggel.click();
					Thread.sleep(2000);
				}
				else
				{
					Log4J.logp.info("Notification toggle does not on after click on it");
					softAssertion.assertTrue(false);
					Child3.log(LogStatus.FAIL, "Notification toggle does not on after click on it");
				}
			}
			else
			{
				Log4J.logp.info("Notification toggle not off by default");
				softAssertion.assertTrue(false);
				Child3.log(LogStatus.FAIL, "Notification toggle not off be default");
			}
			
			// Test ID 8
			
			loginWebE.lbl_UserName.click();
			Thread.sleep(2000);
			
			if(CommonLib.checkElementPresent(homePageWebE.lst_ActiveDropdown) == true)
			{
				Log4J.logp.info("Dropdown will appear after Click on User dropwon");
				softAssertion.assertTrue(true);
				Child4.log(LogStatus.PASS, "Dropown will appear after click on user dropdown");
			}
			else
			{
				Log4J.logp.error("Dropdpwn not opened");
				softAssertion.assertTrue(false);
				Child4.log(LogStatus.FAIL, "Dropdown not opened");
			}
			
			loginWebE.lbl_UserName.click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			softAssertion.assertTrue(false, "checkDropdownToggleBehavior Failed");
		}
		finally
		{
			if (Child1.getRunStatus().toString().equalsIgnoreCase("unknown"))
				Child1.log(LogStatus.FAIL, "Failed for Unknown status.");
			if (Child2.getRunStatus().toString().equalsIgnoreCase("unknown"))
				Child2.log(LogStatus.FAIL, "Failed for Unknown status.");
			if (Child3.getRunStatus().toString().equalsIgnoreCase("unknown"))
				Child3.log(LogStatus.FAIL, "Failed for Unknown status.");
			if (Child3.getRunStatus().toString().equalsIgnoreCase("unknown"))
				Child3.log(LogStatus.FAIL, "Failed for Unknown status.");

			checkDropdownToggleBehavior.appendChild(Child1);
			checkDropdownToggleBehavior.appendChild(Child2);
			checkDropdownToggleBehavior.appendChild(Child3);
			checkDropdownToggleBehavior.appendChild(Child4);

			MainMethod.extent.endTest(Child1);
			MainMethod.extent.endTest(Child2);
			MainMethod.extent.endTest(Child3);
			MainMethod.extent.endTest(Child4);

			if (Child1.getRunStatus().toString().equalsIgnoreCase("FAIL") || Child2.getRunStatus().toString().equalsIgnoreCase("FAIL") || Child3.getRunStatus().toString().equalsIgnoreCase("FAIL") || Child4.getRunStatus().toString().equalsIgnoreCase("FAIL"))
			{
				checkDropdownToggleBehavior.log(LogStatus.FAIL, "checkDropdownToggleBehavior is failed.");
				MainMethod.extent.flush();
			}
			else
			{
				checkDropdownToggleBehavior.log(LogStatus.PASS, "checkDropdownToggleBehavior is passed.");
			}
			//NewLandingPage.appendChild(actionOption);
			MainMethod.extent.endTest(checkDropdownToggleBehavior);

			softAssertion.assertAll();
			
			LoginLib.logout();
		}
	}
	
	/**
	 * 
	 * This method is use for Start Compaign
	 * 
	 * */
	@Test(description = "Start Compaign",priority = 2)
	public static void startCompaign()
	{
		try
		{
			softAssertion = new SoftAssert();
			
			LoginLib.login();
			
			homePageWebE.ico_GuestChat.click();
			Thread.sleep(2000);
			
			homePageWebE.btn_StartConversation.click();
			CommonLib.waitForObject(homePageWebE.btn_StartCompaign, "visibility", 10);
			
			homePageWebE.btn_StartCompaign.click();
			CommonLib.waitForObject(homePageWebE.txt_Description, "visibility", 10);
			
			homePageWebE.txt_Description.sendKeys("Test Description");
			Thread.sleep(2000);
			
			homePageWebE.btn_FileUpload.click();
			Thread.sleep(2000);
			
			CommonLib.uploadFile();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
