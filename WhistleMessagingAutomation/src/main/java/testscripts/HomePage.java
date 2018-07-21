package testscripts;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import framework.ExecutionSetUp;
import framework.MainMethod;
import library.CommonLib;
import library.HomePageLib;
import library.Log4J;
import library.LoginLib;
import net.sourceforge.htmlunit.corejs.javascript.tools.debugger.Main;
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
			Thread.sleep(8000);
			
			homePageWebE.lst_SearchResults.get(0).click();
			Thread.sleep(4000);
			
			homePageWebE.txt_PhoneNo.clear();
			homePageWebE.txt_PhoneNo.sendKeys(strPhoneNo);
			Thread.sleep(4000);
			
			homePageWebE.txt_ArrivalDate.click();
			Thread.sleep(3000);
			
			bstatus = CommonLib.selectDate(strArrivalDate,"Arrival");
			
			homePageWebE.txt_DepartureDate.click();
			Thread.sleep(4000);
			
			bstatus = CommonLib.selectDate(strDepartDate,"Depart");
			
			//homePageWebE.txt_Notes.clear();
			homePageWebE.txt_Notes.sendKeys("Test Notes");
			Thread.sleep(2000);
			
			//homePageWebE.txt_Email.clear();
			homePageWebE.txt_Email.sendKeys(strEmail);
			Thread.sleep(2000);
			
			//shomePageWebE.txt_Message.clear();
			homePageWebE.txt_Message.sendKeys("Hello");
			Thread.sleep(4000);
			
			homePageWebE.btn_SendConversation.click();
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
			
			homePageWebE.ico_NotificationToggel.click();
			Thread.sleep(2000);
			
			strBackground = homePageWebE.lbl_NotificationBackground.getCssValue("background-color");
			Log4J.logp.info(strBackground);
			
			if(strBackground.trim().equalsIgnoreCase("rgba(189, 189, 189, 1)"))
			{
				Log4J.logp.info("By default Notification toggle is off");
				softAssertion.assertTrue(true);
				Child3.log(LogStatus.PASS, "By default Notification toggle is off");
				
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
		String strGuestName, strPhoneNo, strEmail;
		ExtentTest startCompaign = MainMethod.extent.startTest("Test ID 9 : Start Compaign").assignCategory("Regression");
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
			Thread.sleep(2000);
			
			homePageWebE.btn_AddGuestCompaign.click();
			Thread.sleep(2000);
			
			Properties properties = new Properties();
			properties.load(new FileInputStream("src/main/resources/Properties/data.properties"));
			strGuestName = properties.getProperty("guestname");
			strPhoneNo = properties.getProperty("phoneno");
			strEmail = properties.getProperty("username");
			
			homePageWebE.txt_GuestValues.get(0).sendKeys(strGuestName);
			homePageWebE.txt_GuestValues.get(1).sendKeys(strPhoneNo);
			homePageWebE.txt_GuestValues.get(2).sendKeys("Test Notes");
			homePageWebE.txt_GuestValues.get(3).sendKeys(strEmail);
			
			homePageWebE.txt_CompaignMessage.sendKeys("Test Message");
			Thread.sleep(2000);

			homePageWebE.btn_Send.click();
			CommonLib.waitForObject(homePageWebE.compaign_Message, "visibility", 20);
			
			if(CommonLib.checkElementPresent(homePageWebE.compaign_Message))
			{
				Log4J.logp.info("Compaign message sent successfully");
				softAssertion.assertTrue(true);
				startCompaign.log(LogStatus.PASS, "Compaign message sent successfully");
			}
			else
			{
				Log4J.logp.info("Compaign message not sent");
				softAssertion.assertTrue(false);
				startCompaign.log(LogStatus.FAIL, "Compaign message not sent");
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			softAssertion.assertTrue(false, "Test ID 9 : Start Compaign Failed");
		}
		finally {

			if (startCompaign.getRunStatus().toString().equalsIgnoreCase("unknown"))
				startCompaign.log(LogStatus.FAIL, "Test ID 9 : Failed for Unknown status.");
			MainMethod.extent.endTest(startCompaign);

			if (startCompaign.getRunStatus().toString().equalsIgnoreCase("FAIL"))
			{
				startCompaign.log(LogStatus.FAIL, "Test ID 9 : Start Compaign Failed");
			}
			else
			{
				startCompaign.log(LogStatus.PASS, "Test ID 9 : Start Compaign Passed");
			}
			
			softAssertion.assertAll();
			LoginLib.logout();
		}
	}
	
	/**
	 * 
	 * This method is use for Sent Survey using Survey Template
	 * 
	 */
	@Test(description = "Start Survey using Survey Template",priority = 3)
	public static void startSurveyUsingSurveyTemplate()
	{
		ExtentTest startSurveyUsingSurveyTemplate = MainMethod.extent.startTest("Test ID 10 : Start Survey using Survey Template").assignCategory("Regression");
		try
		{
			softAssertion = new SoftAssert();
			LoginLib.login();
			homePageWebE.ico_GuestChat.click();
			Thread.sleep(2000);
			
			homePageWebE.btn_StartConversation.click();
			CommonLib.waitForObject(homePageWebE.btn_StartSurvey, "visibility", 10);
			
			homePageWebE.btn_StartSurvey.click();
			CommonLib.waitForObject(homePageWebE.btn_UseSurveyTemplate, "clickable", 10);
			
			homePageWebE.btn_UseSurveyTemplate.click();
			CommonLib.waitForObject(homePageWebE.lnk_FIrstTemplate, "clickable", 10);
			
			homePageWebE.lnk_FIrstTemplate.click();
			Thread.sleep(2000);
			
			homePageWebE.btn_NextSurvey.click();
			Thread.sleep(2000);
			
			HomePageLib.addGuestDetails();
			
			homePageWebE.btn_NextSurvey.click();
			CommonLib.waitForObject(homePageWebE.btn_SendSurvey, "clickable", 10);
			
			homePageWebE.btn_SendSurvey.click();
			CommonLib.waitForObject(homePageWebE.lbl_SurveySent, "visibility", 20);
			
			System.out.println(homePageWebE.lbl_SurveySent.getText().trim());
			
			if(homePageWebE.lbl_SurveySent.getText().trim().contains("Survey Sent!"))
			{
				Log4J.logp.info("Survey Sent Successfully");
				softAssertion.assertTrue(true);
				startSurveyUsingSurveyTemplate.log(LogStatus.PASS, "Survey Sent Successfully");
			}
			else
			{
				Log4J.logp.error("Survey not sent");
				softAssertion.assertTrue(false);
				startSurveyUsingSurveyTemplate.log(LogStatus.FAIL, "Survey not Sent");
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			softAssertion.assertTrue(false, "Test ID 10 : Start Survey using Survey Template Failed");
		}
		finally {

			if (startSurveyUsingSurveyTemplate.getRunStatus().toString().equalsIgnoreCase("unknown"))
				startSurveyUsingSurveyTemplate.log(LogStatus.FAIL, "Test ID 10 : Failed for Unknown status.");
			MainMethod.extent.endTest(startSurveyUsingSurveyTemplate);

			if (startSurveyUsingSurveyTemplate.getRunStatus().toString().equalsIgnoreCase("FAIL"))
			{
				startSurveyUsingSurveyTemplate.log(LogStatus.FAIL, "Test ID 10 : Start Survey using Survey Template is failed.");
			}
			else
			{
				startSurveyUsingSurveyTemplate.log(LogStatus.PASS, "Test ID 10 : Start Survey using Survey Template is passed.");
			}
			
			softAssertion.assertAll();
			LoginLib.logout();
		}
	}
	
	/**
	 * 
	 * This method is use for Start Survey Without Template
	 * 
	 * */
	@Test(description = "Start Survey Without Template",priority = 4)
	public static void startSurveyWithoutTemplate()
	{
		ExtentTest startSurveyWithoutTemplate = MainMethod.extent.startTest("Test ID 12 :Start Survey Without Template").assignCategory("Regression");
		try
		{
			softAssertion = new SoftAssert();
			LoginLib.login();
			
			homePageWebE.ico_GuestChat.click();
			Thread.sleep(2000);
			
			homePageWebE.btn_StartConversation.click();
			CommonLib.waitForObject(homePageWebE.btn_StartSurvey, "visibility", 10);
			
			homePageWebE.btn_StartSurvey.click();
			CommonLib.waitForObject(homePageWebE.btn_UseSurveyTemplate, "clickable", 10);
			
			homePageWebE.txt_Survey_Question.sendKeys("Test Survey Questions ?");
			Thread.sleep(2000);
			
			/*homePageWebE.lst_AnswerType.click();
			CommonLib.waitForObject(homePageWebE.lst_Range, "visibility", 10);
			
			homePageWebE.lst_Range.click();
			CommonLib.waitForObject(homePageWebE.txt_StartRange, "visibility", 10);
			
			homePageWebE.txt_StartRange.clear();
			homePageWebE.txt_StartRange.sendKeys("5");
			Thread.sleep(2000);
			
			homePageWebE.txt_EndRange.clear();
			homePageWebE.txt_EndRange.sendKeys("10");
			Thread.sleep(2000);*/
			
			homePageWebE.btn_AddAnswerQuestion.click();
			Thread.sleep(2000);
			
			homePageWebE.txt_Choice.sendKeys("None");
			Thread.sleep(2000);
			
			homePageWebE.btn_NextSurvey.click();
			Thread.sleep(2000);
			
			HomePageLib.addGuestDetails();
			
			homePageWebE.btn_NextSurvey.click();
			CommonLib.waitForObject(homePageWebE.btn_SendSurvey, "clickable", 10);
			
			homePageWebE.btn_SendSurvey.click();
			CommonLib.waitForObject(homePageWebE.lbl_SurveySent, "visibility", 20);
			
			System.out.println(homePageWebE.lbl_SurveySent.getText().trim());
			
			if(homePageWebE.lbl_SurveySent.getText().trim().contains("Survey Sent!"))
			{
				Log4J.logp.info("Survey Sent Successfully without template");
				softAssertion.assertTrue(true);
				startSurveyWithoutTemplate.log(LogStatus.PASS, "Survey Sent Successfully without template");
			}
			else
			{
				Log4J.logp.error("Survey not sent");
				softAssertion.assertTrue(false);
				startSurveyWithoutTemplate.log(LogStatus.FAIL, "Survey not Sent");
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			softAssertion.assertTrue(false, "Test ID 10 : Start Survey without Template Failed");
		}
		finally {

			if (startSurveyWithoutTemplate.getRunStatus().toString().equalsIgnoreCase("unknown"))
				startSurveyWithoutTemplate.log(LogStatus.FAIL, "Test ID 10 : Failed for Unknown status.");
			MainMethod.extent.endTest(startSurveyWithoutTemplate);

			if (startSurveyWithoutTemplate.getRunStatus().toString().equalsIgnoreCase("FAIL"))
			{
				startSurveyWithoutTemplate.log(LogStatus.FAIL, "Test ID 10 : Start Survey without Template is failed.");
			}
			else
			{
				startSurveyWithoutTemplate.log(LogStatus.PASS, "Test ID 10 : Start Survey without Template is passed.");
			}
			
			softAssertion.assertAll();
			LoginLib.logout();
		}
	}
	
	/**
	 * 
	 * This method is use for open and delete conversation
	 * 
	 * */
	@Test(description = "Open and delete conversation",priority = 5)
	public static void openDeleteConversation()
	{
		boolean bstatus = false;
		String strValue;
		ExtentTest openDeleteConversation = MainMethod.extent.startTest("Open and delete Conversation").assignCategory("Regression");
		ExtentTest child1 = null;
		ExtentTest child2 = null;
		ExtentTest child3 = null;
		try
		{
			child1 = MainMethod.extent.startTest("Test ID 12 : View Open Conversation");
			child2 = MainMethod.extent.startTest("Test ID 13 : Delete Open Conversation");
			child3 = MainMethod.extent.startTest("Test ID 14 : Archive Conversation");
			
			softAssertion = new SoftAssert();
			LoginLib.login();
			
			homePageWebE.ico_GuestChat.click();
			Thread.sleep(2000);
			
			// Scroll Down
			bstatus = CommonLib.scroll_Page(homePageWebE.scrollbar_Conversation,10);
			
			if(bstatus == true)
			{
				Log4J.logp.info("Conversation Scroll down successfully");
				softAssertion.assertTrue(true);
				child1.log(LogStatus.PASS, "Conversation Scroll down successfully");
			}
			else
			{
				Log4J.logp.info("Conversation Scroll down un successfully");
				softAssertion.assertTrue(false);
				child1.log(LogStatus.FAIL, "Conversation Scroll down un successful");
			}
			
			// Scroll up
			bstatus = CommonLib.scroll_Page_Up(homePageWebE.scrollbar_Conversation,10);
			
			if(bstatus == true)
			{
				Log4J.logp.info("Conversation Scroll up successfully");
				softAssertion.assertTrue(true);
				child1.log(LogStatus.PASS, "Conversation Scroll up successfully");
			}
			else
			{
				Log4J.logp.info("Conversation Scroll up un successfully");
				softAssertion.assertTrue(false);
				child1.log(LogStatus.FAIL, "Conversation Scroll up un successful");
			}
			
			// Close the chat
			
			strValue = homePageWebE.lbl_Names.get(0).getText().trim();
			
			homePageWebE.conversation_list.get(0).click();
			CommonLib.waitForObject(homePageWebE.ico_CloseChat, "visibility", 10);
			
			Thread.sleep(2000);
			homePageWebE.ico_CloseChat.click();
			Thread.sleep(2000);
			
			if(homePageWebE.lbl_Names.get(0).getText().trim().equals(strValue))
			{
				Log4J.logp.info("Chat not deleted");
				softAssertion.assertTrue(false);
				child2.log(LogStatus.FAIL, "Chat not deleted");
			}
			else
			{
				Log4J.logp.info("Chat deleted successfully");
				softAssertion.assertTrue(true);
				child2.log(LogStatus.PASS, "Chat deleted successfully.");
			}
			
			// Archived Tab
			
			homePageWebE.tab_Archived.click();
			Thread.sleep(2000);
			
			// Scroll Down
			bstatus = CommonLib.scroll_Page(homePageWebE.scrollBar_Archive,1);
			
			if(bstatus == true)
			{
				Log4J.logp.info("Archive Conversation Scroll down successfully");
				softAssertion.assertTrue(true);
				child3.log(LogStatus.PASS, "Archive Conversation Scroll down successfully");
			}
			else
			{
				Log4J.logp.info("Conversation Scroll down un successfully");
				softAssertion.assertTrue(false);
				child3.log(LogStatus.FAIL, "Archive Conversation Scroll down un successful");
			}
			
			// Scroll up
			homePageWebE.tab_Archived.click();
			Thread.sleep(2000);
			
			bstatus = CommonLib.scroll_Page_Up(homePageWebE.scrollBar_Archive,1);
			
			if(bstatus == true)
			{
				Log4J.logp.info("Archive Conversation Scroll up successfully");
				softAssertion.assertTrue(true);
				child3.log(LogStatus.PASS, "Archive Conversation Scroll up successfully");
			}
			else
			{
				Log4J.logp.info("Conversation Scroll up un successfully");
				softAssertion.assertTrue(false);
				child3.log(LogStatus.FAIL, "Archive Conversation Scroll up un successful");
			}		
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			softAssertion.assertTrue(false, "checkDropdownToggleBehavior Failed");
		}
		finally
		{
			if (child1.getRunStatus().toString().equalsIgnoreCase("unknown"))
				child1.log(LogStatus.FAIL, "Failed for Unknown status.");
			if (child2.getRunStatus().toString().equalsIgnoreCase("unknown"))
				child2.log(LogStatus.FAIL, "Failed for Unknown status.");
			if (child3.getRunStatus().toString().equalsIgnoreCase("unknown"))
				child3.log(LogStatus.FAIL, "Failed for Unknown status.");
			

			openDeleteConversation.appendChild(child1);
			openDeleteConversation.appendChild(child2);
			openDeleteConversation.appendChild(child3);

			MainMethod.extent.endTest(child1);
			MainMethod.extent.endTest(child2);
			MainMethod.extent.endTest(child3);

			if (child1.getRunStatus().toString().equalsIgnoreCase("FAIL") || child2.getRunStatus().toString().equalsIgnoreCase("FAIL") || child3.getRunStatus().toString().equalsIgnoreCase("FAIL"))
			{
				openDeleteConversation.log(LogStatus.FAIL, "openDeleteConversation is failed.");
			}
			else
			{
				openDeleteConversation.log(LogStatus.PASS, "openDeleteConversation is passed.");
			}
			//NewLandingPage.appendChild(actionOption);
			MainMethod.extent.endTest(openDeleteConversation);

			softAssertion.assertAll();
			
			LoginLib.logout();
		}
	}
	
	/**
	 * 
	 * This method is use for Block and Unblocked Conversation
	 * 
	 * */
	@Test(description = "Block and Unblock Conversation",priority = 6)
	public static void blockUnblockConversation()
	{
		String strExpectedValue, strActualValue;
		ExtentTest blockUnblockConversation = MainMethod.extent.startTest("Block and Unblock Conversation").assignCategory("Regression");
		ExtentTest child1 = null;
		ExtentTest child2 = null;
		try
		{
			child1 = MainMethod.extent.startTest("Test ID 15: Block Conversation");
			child2 = MainMethod.extent.startTest("Test ID 16 : Unblock User");
			
			softAssertion = new SoftAssert();
			LoginLib.login();
			
			homePageWebE.ico_GuestChat.click();
			Thread.sleep(2000);
			
			homePageWebE.conversation_list.get(0).click();
			CommonLib.waitForObject(homePageWebE.ico_CloseChat, "clickable", 10);
			
			strExpectedValue = homePageWebE.lbl_Names.get(0).getText().trim();
			Thread.sleep(2000);
			
			homePageWebE.btn_BlockUnblock.click();
			CommonLib.waitForObject(homePageWebE.btn_Confirm, "clickable", 10);
			
			homePageWebE.btn_Confirm.click();
			Thread.sleep(2000);
			
			homePageWebE.tab_Blocked.click();
			CommonLib.waitForObject(homePageWebE.first_Blocked, "visibility", 10);
			
			strActualValue = homePageWebE.first_Blocked.getText().trim();
			
			//System.out.println(strActualValue);
			//System.out.println(strExpectedValue);
			
			if(strActualValue.equals(strExpectedValue))
			{
				Log4J.logp.info("Conversation has been displayed in Blocked tab after block it");
				softAssertion.assertTrue(true);
				child1.log(LogStatus.PASS, "Conversation has been displayed in Blocked tag after blcok it");
			}
			else
			{
				Log4J.logp.info("Conversation not blocked");
				softAssertion.assertTrue(false);
				child1.log(LogStatus.FAIL, "Conversation not blocked");
			}
			
			homePageWebE.first_Blocked.click();
			Thread.sleep(2000);
			
			homePageWebE.btn_BlockUnblock.click();
			CommonLib.waitForObject(homePageWebE.btn_Confirm, "clickable", 10);
			
			homePageWebE.btn_Confirm.click();
			Thread.sleep(4000);
			//System.out.println(homePageWebE.btn_BlockUnblock.getText().trim());
			
			if(homePageWebE.btn_BlockUnblock.getText().trim().contains("block")) 
			{
				Log4J.logp.info("User has been Unblocked successfully");
				softAssertion.assertTrue(true);
				child2.log(LogStatus.PASS, "User has been unblocked successfully");
			}
			else
			{
				Log4J.logp.info("User not un-blocked");
				softAssertion.assertTrue(false);
				child2.log(LogStatus.FAIL, "User not un-blocked");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			softAssertion.assertTrue(false, "blockUnblockConversation Failed");
		}
		finally
		{
			if (child1.getRunStatus().toString().equalsIgnoreCase("unknown"))
				child1.log(LogStatus.FAIL, "Failed for Unknown status.");
			if (child2.getRunStatus().toString().equalsIgnoreCase("unknown"))
				child2.log(LogStatus.FAIL, "Failed for Unknown status.");

			blockUnblockConversation.appendChild(child1);
			blockUnblockConversation.appendChild(child2);

			MainMethod.extent.endTest(child1);
			MainMethod.extent.endTest(child2);

			if (child1.getRunStatus().toString().equalsIgnoreCase("FAIL") || child2.getRunStatus().toString().equalsIgnoreCase("FAIL"))
			{
				blockUnblockConversation.log(LogStatus.FAIL, "blockUnblockConversation is failed.");
			}
			else
			{
				blockUnblockConversation.log(LogStatus.PASS, "blockUnblockConversation is passed.");
			}
			//NewLandingPage.appendChild(actionOption);
			MainMethod.extent.endTest(blockUnblockConversation);

			softAssertion.assertAll();
			
			LoginLib.logout();
		}
	}
	
	/**
	 * 
	 * This method is use for Live Chats
	 * 
	 * */
	@Test(description = "Open Live chats",priority = 7)
	public static void openLiveChat()
	{
		boolean bstatus = false;
		//String strValue;
		ExtentTest openLiveChat = MainMethod.extent.startTest("Open Live Chats").assignCategory("Regression");
		ExtentTest child1 = null;
		ExtentTest child2 = null;
		try
		{
			child1 = MainMethod.extent.startTest("Test ID 17 : Live Open Conversation");
			child2 = MainMethod.extent.startTest("Test ID 13 : Live Archive Conversation");
			
			softAssertion = new SoftAssert();
			LoginLib.login();
			
			homePageWebE.ico_Live_Chat.click();
			Thread.sleep(4000);
			
			// Scroll Down
			bstatus = CommonLib.scroll_Page(homePageWebE.scrollbar_LiveChat,10);
			
			if(bstatus == true)
			{
				Log4J.logp.info("Live Conversation Scroll down successfully");
				softAssertion.assertTrue(true);
				child1.log(LogStatus.PASS, "Live Conversation Scroll down successfully");
			}
			else
			{
				Log4J.logp.info("Live Conversation Scroll down un successfully");
				softAssertion.assertTrue(false);
				child1.log(LogStatus.FAIL, "Live Conversation Scroll down un successful");
			}
			
			// Scroll up
			bstatus = CommonLib.scroll_Page_Up(homePageWebE.scrollbar_LiveChat,10);
			
			if(bstatus == true)
			{
				Log4J.logp.info("Live Conversation Scroll up successfully");
				softAssertion.assertTrue(true);
				child1.log(LogStatus.PASS, "Live Conversation Scroll up successfully");
			}
			else
			{
				Log4J.logp.info("Live Conversation Scroll up un successfully");
				softAssertion.assertTrue(false);
				child1.log(LogStatus.FAIL, "Live Conversation Scroll up un successful");
			}
			
			
			// Archived Tab
			homePageWebE.tab_Archived.click();
			Thread.sleep(2000);
			
			//WebElement element = driver.findElement(By.id("my-id"));
			JavascriptExecutor je = ((JavascriptExecutor) driver);
			je.executeScript("$('.ArchiveContainer').scroll(0,400)");
			
			// Scroll Down
			bstatus = CommonLib.scroll_Page(homePageWebE.scrollBar_Archive,1);
			
			if(bstatus == true)
			{
				Log4J.logp.info("Archive Conversation Scroll down successfully");
				softAssertion.assertTrue(true);
				child2.log(LogStatus.PASS, "Archive Conversation Scroll down successfully");
			}
			else
			{
				Log4J.logp.info("Conversation Scroll down un successfully");
				softAssertion.assertTrue(false);
				child2.log(LogStatus.FAIL, "Archive Conversation Scroll down un successful");
			}
			
			// Scroll up
			homePageWebE.tab_Archived.click();
			Thread.sleep(2000);
			
			bstatus = CommonLib.scroll_Page_Up(homePageWebE.scrollBar_Archive,1);
			
			if(bstatus == true)
			{
				Log4J.logp.info("Archive Conversation Scroll up successfully");
				softAssertion.assertTrue(true);
				child2.log(LogStatus.PASS, "Archive Conversation Scroll up successfully");
			}
			else
			{
				Log4J.logp.info("Conversation Scroll up un successfully");
				softAssertion.assertTrue(false);
			}
		}
		catch(Exception e)
		{
			Log4J.logp.error("Problem found in :: checkLandingPage");
			CommonLib.takeScreenshots();
			e.printStackTrace();
			softAssertion.assertTrue(false, "openLiveChat Failed");
		}
		finally
		{
			if (child1.getRunStatus().toString().equalsIgnoreCase("unknown"))
				child1.log(LogStatus.FAIL, "Failed for Unknown status.");
			if (child2.getRunStatus().toString().equalsIgnoreCase("unknown"))
				child2.log(LogStatus.FAIL, "Failed for Unknown status.");

			openLiveChat.appendChild(child1);
			openLiveChat.appendChild(child2);

			MainMethod.extent.endTest(child1);
			MainMethod.extent.endTest(child2);

			if (child1.getRunStatus().toString().equalsIgnoreCase("FAIL") || child2.getRunStatus().toString().equalsIgnoreCase("FAIL"))
			{
				openLiveChat.log(LogStatus.FAIL, "openLiveChat is failed.");
			}
			else
			{
				openLiveChat.log(LogStatus.PASS, "openLiveChat is passed.");
			}
			//NewLandingPage.appendChild(actionOption);
			MainMethod.extent.endTest(openLiveChat);

			softAssertion.assertAll();
			
			LoginLib.logout();
		}
	}
	

	/**
	 * 
	 * This method is use for Block and Unblocked Live Conversation
	 * 
	 * */
	@Test(description = "Block Live Conversation",priority = 8)
	public static void blockLiveConversation()
	{
		String strExpectedValue, strActualValue;
		ExtentTest blockLiveConversation = MainMethod.extent.startTest("Test ID 19 : Live Block Conversation").assignCategory("Regression");
		
		try
		{
			softAssertion = new SoftAssert();
			LoginLib.login();
			
			homePageWebE.ico_Live_Chat.click();
			Thread.sleep(2000);
			
			homePageWebE.conversation_list.get(0).click();
			CommonLib.waitForObject(homePageWebE.ico_CloseChat, "clickable", 10);
			
			strExpectedValue = homePageWebE.lbl_LiveChatNames.get(0).getText().trim();
			Thread.sleep(2000);
			
			homePageWebE.btn_BlockUnblock.click();
			CommonLib.waitForObject(homePageWebE.btn_Confirm, "clickable", 10);
			
			homePageWebE.btn_Confirm.click();
			Thread.sleep(4000);
			
			homePageWebE.tab_Blocked.click();
			CommonLib.waitForObject(homePageWebE.first_Blocked, "visibility", 10);
			
			strActualValue = homePageWebE.first_Blocked.getText().trim();
			
			//System.out.println(strActualValue);
			//System.out.println(strExpectedValue);
			
			if(strActualValue.equals(strExpectedValue))
			{
				Log4J.logp.info("Live Conversation has been displayed in Blocked tab after block it");
				softAssertion.assertTrue(true);
				blockLiveConversation.log(LogStatus.PASS, " Live Conversation has been displayed in Blocked tag after blcok it");
			}
			else
			{
				Log4J.logp.info("Live Conversation not blocked");
				softAssertion.assertTrue(false);
				blockLiveConversation.log(LogStatus.FAIL, "Live Conversation not blocked");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			softAssertion.assertTrue(false, "blockLiveConversation Failed");
		}
		finally
		{
			if (blockLiveConversation.getRunStatus().toString().equalsIgnoreCase("unknown"))
				blockLiveConversation.log(LogStatus.FAIL, "Failed for Unknown status.");

			if (blockLiveConversation.getRunStatus().toString().equalsIgnoreCase("FAIL"))
			{
				blockLiveConversation.log(LogStatus.FAIL, "blockLiveConversation is failed.");
			}
			else
			{
				blockLiveConversation.log(LogStatus.PASS, "blockUnblockConversation is passed.");
			}
			//NewLandingPage.appendChild(actionOption);
			MainMethod.extent.endTest(blockLiveConversation);
			softAssertion.assertAll();
			LoginLib.logout();
		}
	}
	
	/**
	 * 
	 * This method is use for Create Channel, Add Members and Delete members with team members
	 * 
	 * */
	@Test(description = "Create Channel with team members", priority = 9)
	public static void createDeleteChannel()
	{
		ArrayList<String> arrExpectedTeam = new ArrayList<String>();
		int  intCount, intAfterCount;
		ExtentTest createDeleteChannel = MainMethod.extent.startTest("Create Channel, Add members and Delete Channel").assignCategory("Regression");
		ExtentTest child1 = null;
		ExtentTest child2 = null;
		ExtentTest child3 = null;
		ExtentTest child4 = null;
		try
		{
			
			child1 = MainMethod.extent.startTest("Test ID 20 :  Crate Team Chat");
			child2 = MainMethod.extent.startTest("Test ID 21 :  Add Members to Team Chat");
			child3 = MainMethod.extent.startTest("Test ID 22 :  Delete Team Chat");
			child4 = MainMethod.extent.startTest("Test ID 23 :  Delete Members to Team Chat");
			
			softAssertion = new SoftAssert();
			LoginLib.login();
			
			homePageWebE.ico_TeamChat.click();
			CommonLib.waitForObject(homePageWebE.btn_CreateChannel, "clickable", 10);
			
			homePageWebE.btn_CreateChannel.click();
			CommonLib.waitForObject(homePageWebE.txt_ChannelName, "visibility", 10);
			
			homePageWebE.txt_ChannelName.sendKeys("Test Channel");
			Thread.sleep(2000);
			
			for(int i = 0;i<3;i++)
			{
				arrExpectedTeam.add(homePageWebE.lbl_TeamMembers.get(i).getText());
				homePageWebE.lbl_TeamMembers.get(i).click();
			}
			
			homePageWebE.btn_ConfirmChannel.click();
			CommonLib.waitForObject(homePageWebE.lbl_ConfirmMembers.get(0), "visibility", 10);
			
			for(int i = 0;i< homePageWebE.lbl_ConfirmMembers.size(); i++)
			{
				if(arrExpectedTeam.contains(homePageWebE.lbl_ConfirmMembers.get(i).getText()))
				{
					Log4J.logp.info(homePageWebE.lbl_ConfirmMembers.get(i).getText() + " team memeber has been added in Channel");
					softAssertion.assertTrue(true);
					child1.log(LogStatus.PASS, homePageWebE.lbl_ConfirmMembers.get(i).getText() + " team memeber has been added in Channel");
				}
				else
				{
					Log4J.logp.info(homePageWebE.lbl_ConfirmMembers.get(i).getText() + " team memeber not added in Channel");
					softAssertion.assertTrue(false);
					child1.log(LogStatus.FAIL, homePageWebE.lbl_ConfirmMembers.get(i).getText() + " team memeber not added in Channel");
				}
			}
			
			// Add Member to Chat
			homePageWebE.ico_AddMembers.click();
			CommonLib.waitForObject(homePageWebE.lbl_NewMembers.get(0), "visibility", 10);
			
			arrExpectedTeam.add(homePageWebE.lbl_NewMembers.get(0).getText().trim());
			
			homePageWebE.lbl_NewMembers.get(0).click();
			Thread.sleep(2000);
			
			homePageWebE.btn_NewConfirm.click();
			CommonLib.waitForObject(homePageWebE.lbl_ConfirmMembers.get(0), "visibility", 10);
			Thread.sleep(2000);
			
			for(int i = 0;i< homePageWebE.lbl_ConfirmMembers.size(); i++)
			{
				if(arrExpectedTeam.contains(homePageWebE.lbl_ConfirmMembers.get(i).getText()))
				{
					Log4J.logp.info(homePageWebE.lbl_ConfirmMembers.get(i).getText() + " team memeber has been added in Channel");
					softAssertion.assertTrue(true);
					child2.log(LogStatus.PASS, homePageWebE.lbl_ConfirmMembers.get(i).getText() + " team memeber has been added in Channel");
				}
				else
				{
					Log4J.logp.info(homePageWebE.lbl_ConfirmMembers.get(i).getText() + " team memeber not added in Channel");
					softAssertion.assertTrue(false);
					child2.log(LogStatus.FAIL, homePageWebE.lbl_ConfirmMembers.get(i).getText() + " team memeber not added in Channel");
				}
			}
			
			// Delete Members
			
			intCount = homePageWebE.lbl_ConfirmMembers.size();
			
			homePageWebE.ico_Delete.get(0).click();
			Thread.sleep(2000);
			
			homePageWebE.btn_ConfirmRemove.click();
			Thread.sleep(2000);
			
			intAfterCount = homePageWebE.lbl_ConfirmMembers.size();
			
			if(intAfterCount == intCount-1)
			{
				Log4J.logp.info("Team member removed from channel");
				softAssertion.assertTrue(true);
				child4.log(LogStatus.PASS, "Team members has been removed from channel");
			}
			else
			{
				Log4J.logp.info("Team member not removed from Channel");
				softAssertion.assertTrue(false);
				child4.log(LogStatus.FAIL, "Team member not removed frm channel");
			}
			
			// Delete Channel
			
			homePageWebE.ico_RemoveChannel.click();
			Thread.sleep(2000);
			
			homePageWebE.btn_ConfirmChannelRemove.click();
			Thread.sleep(2000);
			
			if(homePageWebE.lbl_ChannelName.get(0).getText().contains("Test Channel"))
			{
				Log4J.logp.info("Channel Not removed");
				softAssertion.assertTrue(false);
				child3.log(LogStatus.FAIL, "Channel not removed");
			}
			else
			{
				Log4J.logp.info("Channel Removed");
				softAssertion.assertTrue(true);
				child3.log(LogStatus.PASS, "Channel Removed");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			softAssertion.assertTrue(false, "createDeleteChannel Failed");
		}
		finally
		{
			if (child1.getRunStatus().toString().equalsIgnoreCase("unknown"))
				child1.log(LogStatus.FAIL, "Failed for Unknown status.");
			if (child2.getRunStatus().toString().equalsIgnoreCase("unknown"))
				child2.log(LogStatus.FAIL, "Failed for Unknown status.");
			if (child3.getRunStatus().toString().equalsIgnoreCase("unknown"))
				child3.log(LogStatus.FAIL, "Failed for Unknown status.");
			if (child4.getRunStatus().toString().equalsIgnoreCase("unknown"))
				child4.log(LogStatus.FAIL, "Failed for Unknown status.");
			

			createDeleteChannel.appendChild(child1);
			createDeleteChannel.appendChild(child2);
			createDeleteChannel.appendChild(child3);
			createDeleteChannel.appendChild(child4);

			MainMethod.extent.endTest(child1);
			MainMethod.extent.endTest(child2);
			MainMethod.extent.endTest(child3);
			MainMethod.extent.endTest(child4);

			if (child1.getRunStatus().toString().equalsIgnoreCase("FAIL") || child2.getRunStatus().toString().equalsIgnoreCase("FAIL") || child3.getRunStatus().toString().equalsIgnoreCase("FAIL") || child4.getRunStatus().toString().equalsIgnoreCase("FAIL"))
			{
				createDeleteChannel.log(LogStatus.FAIL, "createDeleteChannel is failed.");
			}
			else
			{
				createDeleteChannel.log(LogStatus.PASS, "createDeleteChannel is passed.");
			}
			//NewLandingPage.appendChild(actionOption);
			MainMethod.extent.endTest(createDeleteChannel);

			softAssertion.assertAll();
			
			LoginLib.logout();
		}
	}
	
	/**
	 * 
	 * This method is use for Insert Template on channel
	 * 
	 * */
	@Test(description = "Insert Template in Team Chat message ", priority = 10)
	public static void insertTemplate()
	{
		String strExpectedTemplateValue, strAcualTemplateValue;
		ExtentTest insertTemplate = MainMethod.extent.startTest("Test ID 26 : Insert Template in Team Chat Message").assignCategory("Regeression");
		try
		{
			softAssertion = new SoftAssert();
			LoginLib.login();
			
			homePageWebE.ico_TeamChat.click();
			CommonLib.waitForObject(homePageWebE.btn_CreateChannel, "clickable", 10);
			
			homePageWebE.btn_CreateChannel.click();
			CommonLib.waitForObject(homePageWebE.txt_ChannelName, "visibility", 10);
			
			homePageWebE.txt_ChannelName.sendKeys("Template Insert Channel");
			Thread.sleep(2000);
			
			for(int i = 0;i<3;i++)
			{
				homePageWebE.lbl_TeamMembers.get(i).click();
			}
			
			homePageWebE.btn_ConfirmChannel.click();
			CommonLib.waitForObject(homePageWebE.lbl_ConfirmMembers.get(0), "visibility", 10);
			Thread.sleep(2000);
			
			homePageWebE.ico_InsertTemplate.click();
			Thread.sleep(2000);
			
			homePageWebE.btn_InsertTemlpate.click();
			Thread.sleep(2000);
			
			strExpectedTemplateValue = homePageWebE.list_Templates.get(2).getText().trim();
			
			homePageWebE.list_Templates.get(2).click();
			Thread.sleep(2000);
			
			homePageWebE.btn_SendConversation.click();
			Thread.sleep(2000);
			
			strAcualTemplateValue = homePageWebE.lbl_Templates.get(0).getText().trim();
			
			if(strAcualTemplateValue.equals(strExpectedTemplateValue))
			{
				Log4J.logp.info("Template has been addedd successfully");
				softAssertion.assertTrue(true);
				insertTemplate.log(LogStatus.PASS, "Template has been added successfully");
			}
			else
			{
				Log4J.logp.info("Template not added");
				softAssertion.assertTrue(false);
				insertTemplate.log(LogStatus.FAIL, "Template not added");
			}
			
			homePageWebE.ico_RemoveChannel.click();
			Thread.sleep(2000);
			
			homePageWebE.btn_ConfirmChannelRemove.click();
			Thread.sleep(2000);
			
		}
	
		catch(Exception e)
		{
			e.printStackTrace();
			softAssertion.assertTrue(false, "insertTemplate Failed");
		}
		finally
		{
			if (insertTemplate.getRunStatus().toString().equalsIgnoreCase("unknown"))
				insertTemplate.log(LogStatus.FAIL, "Failed for Unknown status.");
			
			if (insertTemplate.getRunStatus().toString().equalsIgnoreCase("FAIL"))
			{
				insertTemplate.log(LogStatus.FAIL, "insertTemplate is failed.");
			}
			else
			{
				insertTemplate.log(LogStatus.PASS, "insertTemplate is passed.");
			}
			//NewLandingPage.appendChild(actionOption);
			MainMethod.extent.endTest(insertTemplate);
			softAssertion.assertAll();
			LoginLib.logout();
		}
	}
	
	/**
	 * 
	 * This method is use for check Direct Chat message
	 * 
	 * */
	@Test(description = "Check Direct chat and clear search box", priority = 11)
	public static void checkDirectChat()
	{
		String strExpectedValue,strAttribute;
		int intSize;
		ExtentTest checkDirectChat = MainMethod.extent.startTest("Check Direct chat and clear search box").assignCategory("Regression");
		ExtentTest child1 = null;
		ExtentTest child2 = null;
		try
		{
			 child1 = MainMethod.extent.startTest("Test ID 27 : Sending a Direct Chat");
			 child2  = MainMethod.extent.startTest("Test ID  28 : Clear Direct Chat Search box");
			
			softAssertion = new SoftAssert();
			LoginLib.login();
			
			homePageWebE.ico_DirectChat.click();
			CommonLib.waitForObject(homePageWebE.lnk_TeamMember.get(0), "visibility", 10);
			
			homePageWebE.lnk_TeamMember.get(0).click();
			Thread.sleep(2000);
			
			homePageWebE.txt_DirectMessage.sendKeys("Test Direct Message");
			Thread.sleep(2000);
			
			homePageWebE.btn_SendConversation.click();
			Thread.sleep(2000);
			
			intSize = homePageWebE.lbl_DirectMessage.size();
			
			strExpectedValue = homePageWebE.lbl_DirectMessage.get(intSize-1).getText().trim();
			
			if(strExpectedValue.equals("Test Direct Message"))
			{
				Log4J.logp.info("Message has been sent successfuly");
				softAssertion.assertTrue(true);
				child1.log(LogStatus.PASS, "Message has been sent successfully");
			}
			else
			{
				Log4J.logp.info("Message not sent");
				softAssertion.assertTrue(false);
				child1.log(LogStatus.FAIL, "Message not sent");
			}
			
			// check Search box
			
			homePageWebE.ico_Search.click();
			Thread.sleep(2000);
			
			homePageWebE.txt_SearchBox.sendKeys("Test");
			Thread.sleep(2000);
			
			homePageWebE.ico_Clear.click();
			Thread.sleep(2000);
			
			strAttribute = homePageWebE.txt_SearchBox.getAttribute("class");
			
			if(strAttribute.isEmpty())
			{
				Log4J.logp.info("Search bar disappears after click on clear icon");
				softAssertion.assertTrue(true);
				child2.log(LogStatus.PASS, "Search bar disappears after click on clear icon");
			}
			else
			{
				Log4J.logp.info("Clear functionality not working ");
				softAssertion.assertTrue(false);
				child2.log(LogStatus.FAIL, "Clear functionality not working");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			softAssertion.assertTrue(false, "checkDirectChat Failed");
		}
		finally
		{
			if (child1.getRunStatus().toString().equalsIgnoreCase("unknown"))
				child1.log(LogStatus.FAIL, "Failed for Unknown status.");
			if (child2.getRunStatus().toString().equalsIgnoreCase("unknown"))
				child2.log(LogStatus.FAIL, "Failed for Unknown status.");
			
			

			checkDirectChat.appendChild(child1);
			checkDirectChat.appendChild(child2);

			MainMethod.extent.endTest(child1);
			MainMethod.extent.endTest(child2);

			if (child1.getRunStatus().toString().equalsIgnoreCase("FAIL") || child2.getRunStatus().toString().equalsIgnoreCase("FAIL"))
			{
				checkDirectChat.log(LogStatus.FAIL, "checkDirectChat is failed.");
			}
			else
			{
				checkDirectChat.log(LogStatus.PASS, "checkDirectChat is passed.");
			}
			//NewLandingPage.appendChild(actionOption);
			MainMethod.extent.endTest(checkDirectChat);

			softAssertion.assertAll();
			
			LoginLib.logout();
		}
	}
	
}
