package testscripts;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
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
import webelements.HomePageWebE;
import webelements.LoginWebE;

public class HomePage
{

	static WebDriver	driver			= null;
	static HomePageWebE	homePageWebE	= null;
	static LoginWebE	loginWebE		= null;
	static SoftAssert	softAssertion;

	@BeforeClass
	public static void beforeClassHomePage()
	{
		driver = ExecutionSetUp.getDriver();
		homePageWebE = HomePageWebE.getInstance(driver);
		loginWebE = LoginWebE.getInstance(driver);

		LoginLib.login();
	}

	/**
	 * This method is use for Start Conversation
	 * 
	 */
	@Test(description = "Start Conversation", priority = 0)
	public static void startConversation()
	{
		String strGuestName, strPhoneNo, strEmail, strArrivalDate, strDepartDate;
		boolean bstatus = false;
		ExtentTest StartConversation = MainMethod.extent.startTest("Test ID 4 : Start Conversation").assignCategory("Regression");
		try
		{

			Log4J.logp.info("**** Started : startConversation ****");

			softAssertion = new SoftAssert();
			//LoginLib.login();

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

			bstatus = CommonLib.selectDate(strArrivalDate, "Arrival");

			homePageWebE.txt_DepartureDate.click();
			Thread.sleep(4000);

			bstatus = CommonLib.selectDate(strDepartDate, "Depart");

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

			System.out.println(homePageWebE.lbl_Messages.get(intMessageSize - 1).getText());

			if (homePageWebE.lbl_Messages.get(intMessageSize - 1).getText().equals("Hello"))
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
			Log4J.logp.info("**** Ended : startConversation ****");
		}
		catch (Exception e)
		{
			// TODO: handle exception
			CommonLib.takeScreenshots();
			e.printStackTrace();
			softAssertion.assertTrue(false, "Test ID 4 : Start Conversation Failed");
		}
		finally
		{

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
			//LoginLib.logout();.
		}
	}

	/**
	 * This method is use for check Dropdown behavior and Toggle behavior of application
	 * 
	 * Dropdown Toolbar Turn Sound ON/OFF Turn Notification ON/OFF User dropdown
	 * 
	 */
	@Test(description = "To check Dropdown and Toggle behavior on Home Page", priority = 1)
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

			Log4J.logp.info("**** Started : checkDropdownToggleBehavior ****");

			Child1 = MainMethod.extent.startTest("Test ID 5 : Whistle Dropdown Toolbar");
			Child2 = MainMethod.extent.startTest("Test ID 6 : Turn Sound ON/OFF");
			Child3 = MainMethod.extent.startTest("Test ID 7 : Turn Notification ON/OFF");
			Child4 = MainMethod.extent.startTest("Test ID 8 : User dropdown");

			softAssertion = new SoftAssert();

			//LoginLib.login();

			// Test ID 5

			homePageWebE.lst_WhistleDropdown.click();
			Thread.sleep(2000);

			if (CommonLib.checkElementPresent(homePageWebE.lst_ActiveDropdown) == true)
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

			if (strBackground.trim().equalsIgnoreCase("rgba(189, 189, 189, 1)"))
			{
				Log4J.logp.info("By default Sound toggle is off");
				softAssertion.assertTrue(true);
				Child2.log(LogStatus.PASS, "By default sound toggle is off");

				homePageWebE.ico_SoundToggle.click();
				Thread.sleep(2000);

				strBackground = homePageWebE.lbl_ToggleBackground.getCssValue("background-color");
				Log4J.logp.info(strBackground);
				if (strBackground.trim().equalsIgnoreCase("rgba(0, 179, 134, 0.5)"))
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

			if (strBackground.trim().equalsIgnoreCase("rgba(189, 189, 189, 1)"))
			{
				Log4J.logp.info("By default Notification toggle is off");
				softAssertion.assertTrue(true);
				Child3.log(LogStatus.PASS, "By default Notification toggle is off");

				homePageWebE.ico_NotificationToggel.click();
				Thread.sleep(2000);

				strBackground = homePageWebE.lbl_NotificationBackground.getCssValue("background-color");
				Log4J.logp.info(strBackground);
				if (strBackground.trim().equalsIgnoreCase("rgba(0, 179, 134, 0.5)"))
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

			if (CommonLib.checkElementPresent(homePageWebE.lst_ActiveDropdown) == true)
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

			Log4J.logp.info("**** Ended : checkDropdownToggleBehavior ****");
		}
		catch (Exception e)
		{
			CommonLib.takeScreenshots();
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

			MainMethod.extent.endTest(checkDropdownToggleBehavior);

			softAssertion.assertAll();

			//LoginLib.logout();.
		}
	}

	/**
	 * 
	 * This method is use for Start Compaign
	 * 
	 */
	@Test(description = "Start Compaign", priority = 20)
	public static void startCompaign()
	{
		String strGuestName, strPhoneNo, strEmail;
		ExtentTest startCompaign = MainMethod.extent.startTest("Test ID 9 : Start Compaign").assignCategory("Regression");
		try
		{
			Log4J.logp.info("**** Started : startCompaign ****");

			softAssertion = new SoftAssert();

			//LoginLib.login();

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

			CommonLib.uploadFile("Campaigns-Sample.xlsx");
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

			if (CommonLib.checkElementPresent(homePageWebE.compaign_Message))
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

			Log4J.logp.info("**** Ended : startCompaign ****");
		}
		catch (Exception e)
		{
			// TODO: handle exception
			CommonLib.takeScreenshots();
			e.printStackTrace();
			softAssertion.assertTrue(false, "Test ID 9 : Start Compaign Failed");
		}
		finally
		{

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
			//LoginLib.logout();.
		}
	}

	/**
	 * 
	 * This method is use for Sent Survey using Survey Template
	 * 
	 */
	@Test(description = "Start Survey using Survey Template", priority = 3)
	public static void startSurveyUsingSurveyTemplate()
	{
		ExtentTest startSurveyUsingSurveyTemplate = MainMethod.extent.startTest("Test ID 10 : Start Survey using Survey Template").assignCategory("Regression");
		try
		{
			Log4J.logp.info("**** Started : startSurveyUsingSurveyTemplate ****");

			softAssertion = new SoftAssert();
			//LoginLib.login();
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

			if (homePageWebE.lbl_SurveySent.getText().trim().contains("Survey Sent!"))
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

			Log4J.logp.info("**** Ended : startSurveyUsingSurveyTemplate ****");
		}
		catch (Exception e)
		{
			// TODO: handle exception
			CommonLib.takeScreenshots();
			e.printStackTrace();
			softAssertion.assertTrue(false, "Test ID 10 : Start Survey using Survey Template Failed");
		}
		finally
		{

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
			//LoginLib.logout();.
		}
	}

	/**
	 * 
	 * This method is use for Start Survey Without Template
	 * 
	 */
	@Test(description = "Start Survey Without Template", priority = 4)
	public static void startSurveyWithoutTemplate()
	{
		ExtentTest startSurveyWithoutTemplate = MainMethod.extent.startTest("Test ID 12 :Start Survey Without Template").assignCategory("Regression");
		try
		{

			Log4J.logp.info("**** Started :startSurveyWithoutTemplate ****");

			softAssertion = new SoftAssert();
			//LoginLib.login();

			homePageWebE.ico_GuestChat.click();
			Thread.sleep(2000);

			homePageWebE.btn_StartConversation.click();
			CommonLib.waitForObject(homePageWebE.btn_StartSurvey, "visibility", 10);

			homePageWebE.btn_StartSurvey.click();
			CommonLib.waitForObject(homePageWebE.btn_CreateSurvey, "clickable", 10);

			homePageWebE.btn_CreateSurvey.click();
			Thread.sleep(2000);

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

			if (homePageWebE.lbl_SurveySent.getText().trim().contains("Survey Sent!"))
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

			Log4J.logp.info("**** Ended :startSurveyWithoutTemplate ****");
		}
		catch (Exception e)
		{
			// TODO: handle exception
			CommonLib.takeScreenshots();
			e.printStackTrace();
			softAssertion.assertTrue(false, "Test ID 10 : Start Survey without Template Failed");
		}
		finally
		{

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
			//LoginLib.logout();.
		}
	}

	/**
	 * 
	 * This method is use for open and delete conversation
	 * 
	 */
	@Test(description = "Open and delete conversation", priority = 5)
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
			Log4J.logp.info("**** Started :openDeleteConversation ****");

			child1 = MainMethod.extent.startTest("Test ID 12 : View Open Conversation");
			child2 = MainMethod.extent.startTest("Test ID 13 : Delete Open Conversation");
			child3 = MainMethod.extent.startTest("Test ID 14 : Archive Conversation");

			softAssertion = new SoftAssert();
			//LoginLib.login();

			homePageWebE.ico_GuestChat.click();
			Thread.sleep(2000);

			// Scroll Down
			bstatus = CommonLib.scroll_Page(homePageWebE.scrollbar_Conversation, 10);

			if (bstatus == true)
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
			bstatus = CommonLib.scroll_Page_Up(homePageWebE.scrollbar_Conversation, 10);

			if (bstatus == true)
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

			if (homePageWebE.lbl_Names.get(0).getText().trim().equals(strValue))
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

			bstatus = CommonLib.checkElementPresent(homePageWebE.lst_Archive.get(0));

			if (bstatus == true)
			{
				Log4J.logp.info("Archive Conversation displayed in Archived tab");
				softAssertion.assertTrue(true);
				child3.log(LogStatus.PASS, "Archive Conversation displayed in Archived tab");
			}
			else
			{
				Log4J.logp.info("Archived Conversation not found");
				softAssertion.assertTrue(false);
				child3.log(LogStatus.FAIL, "Archived Conversation not found");
			}

			Log4J.logp.info("**** Ended :openDeleteConversation ****");
		}
		catch (Exception e)
		{
			CommonLib.takeScreenshots();
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

			MainMethod.extent.endTest(openDeleteConversation);

			softAssertion.assertAll();

			//LoginLib.logout();.
		}
	}

	/**
	 * 
	 * This method is use for Block and Unblocked Conversation
	 * 
	 */
	@Test(description = "Block and Unblock Conversation", priority = 6)
	public static void blockUnblockConversation()
	{
		String strExpectedValue, strActualValue;
		ExtentTest blockUnblockConversation = MainMethod.extent.startTest("Block and Unblock Conversation").assignCategory("Regression");
		ExtentTest child1 = null;
		ExtentTest child2 = null;
		try
		{
			Log4J.logp.info("**** Started :blockUnblockConversation ****");

			child1 = MainMethod.extent.startTest("Test ID 15: Block Conversation");
			child2 = MainMethod.extent.startTest("Test ID 16 : Unblock User");

			softAssertion = new SoftAssert();
			//LoginLib.login();

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

			if (strActualValue.equals(strExpectedValue))
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

			if (homePageWebE.btn_BlockUnblock.getText().trim().contains("block"))
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

			Log4J.logp.info("**** Ended :blockUnblockConversation ****");
		}
		catch (Exception e)
		{
			CommonLib.takeScreenshots();
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

			MainMethod.extent.endTest(blockUnblockConversation);

			softAssertion.assertAll();

			//LoginLib.logout();.
		}
	}

	/**
	 * 
	 * This method is use for Live Chats
	 * 
	 */
	@Test(description = "Open Live chats", priority = 7)
	public static void openLiveChat()
	{
		boolean bstatus = false;
		//String strValue;
		ExtentTest openLiveChat = MainMethod.extent.startTest("Open Live Chats").assignCategory("Regression");
		ExtentTest child1 = null;
		ExtentTest child2 = null;
		try
		{
			Log4J.logp.info("**** Started :openLiveChat ****");

			child1 = MainMethod.extent.startTest("Test ID 17 : Live Open Conversation");
			child2 = MainMethod.extent.startTest("Test ID 13 : Live Archive Conversation");

			softAssertion = new SoftAssert();
			//LoginLib.login();

			homePageWebE.ico_Live_Chat.click();
			Thread.sleep(4000);

			// Scroll Down
			bstatus = CommonLib.scroll_Page(homePageWebE.scrollbar_LiveChat, 10);

			if (bstatus == true)
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
			bstatus = CommonLib.scroll_Page_Up(homePageWebE.scrollbar_LiveChat, 10);

			if (bstatus == true)
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

			// Scroll Down
			bstatus = CommonLib.checkElementPresent(homePageWebE.lst_Archive.get(0));

			if (bstatus == true)
			{
				Log4J.logp.info("Archive Conversation displayed");
				softAssertion.assertTrue(true);
				child2.log(LogStatus.PASS, "Archive Conversation displayed");
			}
			else
			{
				Log4J.logp.info("Archive Conversation not found");
				softAssertion.assertTrue(false);
				child2.log(LogStatus.FAIL, "Archive Conversation not found");
			}

			Log4J.logp.info("**** Ended :openLiveChat ****");
		}
		catch (Exception e)
		{
			Log4J.logp.error("Problem found in :: openLiveChat");
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

			MainMethod.extent.endTest(openLiveChat);

			softAssertion.assertAll();

			//LoginLib.logout();.
		}
	}

	/**
	 * 
	 * This method is use for Block and Unblocked Live Conversation
	 * 
	 */
	@Test(description = "Block Live Conversation", priority = 8)
	public static void blockLiveConversation()
	{
		String strExpectedValue, strActualValue;
		ExtentTest blockLiveConversation = MainMethod.extent.startTest("Test ID 19 : Live Block Conversation").assignCategory("Regression");

		try
		{
			Log4J.logp.info("**** Started :blockLiveConversation ****");

			softAssertion = new SoftAssert();
			//LoginLib.login();

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

			if (strActualValue.equals(strExpectedValue))
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
			Log4J.logp.info("**** Ended :blockLiveConversation ****");

		}
		catch (Exception e)
		{
			CommonLib.takeScreenshots();
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

			MainMethod.extent.endTest(blockLiveConversation);
			softAssertion.assertAll();
			//LoginLib.logout();.
		}
	}

	/**
	 * 
	 * This method is use for Create Channel, Add Members and Delete members with team members
	 * 
	 */
	@Test(description = "Create Channel with team members", priority = 9)
	public static void createDeleteChannel()
	{
		ArrayList<String> arrExpectedTeam = new ArrayList<String>();
		int intCount, intAfterCount;
		ExtentTest createDeleteChannel = MainMethod.extent.startTest("Create Channel, Add members and Delete Channel").assignCategory("Regression");
		ExtentTest child1 = null;
		ExtentTest child2 = null;
		ExtentTest child3 = null;
		ExtentTest child4 = null;
		try
		{
			Log4J.logp.info("**** Started :createDeleteChannel ****");

			child1 = MainMethod.extent.startTest("Test ID 20 :  Crate Team Chat");
			child2 = MainMethod.extent.startTest("Test ID 21 :  Add Members to Team Chat");
			child3 = MainMethod.extent.startTest("Test ID 22 :  Delete Team Chat");
			child4 = MainMethod.extent.startTest("Test ID 23 :  Delete Members to Team Chat");

			softAssertion = new SoftAssert();
			//LoginLib.login();

			homePageWebE.ico_TeamChat.click();
			CommonLib.waitForObject(homePageWebE.btn_CreateChannel, "clickable", 10);

			homePageWebE.btn_CreateChannel.click();
			CommonLib.waitForObject(homePageWebE.txt_ChannelName, "visibility", 10);

			homePageWebE.txt_ChannelName.sendKeys("Test Channel");
			Thread.sleep(2000);

			for (int i = 0; i < 3; i++)
			{
				arrExpectedTeam.add(homePageWebE.lbl_TeamMembers.get(i).getText());
				homePageWebE.lbl_TeamMembers.get(i).click();
			}

			homePageWebE.btn_ConfirmChannel.click();
			CommonLib.waitForObject(homePageWebE.lbl_ConfirmMembers.get(0), "visibility", 10);

			for (int i = 0; i < homePageWebE.lbl_ConfirmMembers.size(); i++)
			{
				if (arrExpectedTeam.contains(homePageWebE.lbl_ConfirmMembers.get(i).getText()))
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

			for (int i = 0; i < homePageWebE.lbl_ConfirmMembers.size(); i++)
			{
				if (arrExpectedTeam.contains(homePageWebE.lbl_ConfirmMembers.get(i).getText()))
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

			if (intAfterCount == intCount - 1)
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

			if (homePageWebE.lbl_ChannelName.get(0).getText().contains("Test Channel"))
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

			Log4J.logp.info("**** Ended :createDeleteChannel ****");

		}
		catch (Exception e)
		{
			CommonLib.takeScreenshots();
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

			MainMethod.extent.endTest(createDeleteChannel);

			softAssertion.assertAll();

			//LoginLib.logout();.
		}
	}

	/**
	 * 
	 * This method is use for Translate Team chat message
	 * 
	 */
	@Test(description = "Translate team message", priority = 10)
	public static void translateTeamMessage()
	{
		ArrayList<String> arrExpectedTeam = new ArrayList<String>();
		//int intSize;
		String strExpectedTranslate, strActualTranslate;
		ExtentTest translateTeamMessage = MainMethod.extent.startTest("Translate Team chat message").assignCategory("Regression");
		ExtentTest child1 = null;
		ExtentTest child2 = null;
		String[] strTranslateLangauges = { "Spanish", "French", "German", "Dutch", "Chinese (Simplified)" };
		//String[] strTranslateLangauges = {"Chinese (Simplified)"};
		try
		{

			Log4J.logp.info("**** Started :translateTeamMessage ****");

			Properties properties = new Properties();
			properties.load(new FileInputStream("src/main/resources/Properties/Translate.properties"));

			child1 = MainMethod.extent.startTest("Test ID 24 :  Translate Team chat message after has been sent");
			child2 = MainMethod.extent.startTest("Test ID 25 : Translate Team chat message before message has been sent");

			//String strValue = properties.getProperty("Translate.Amharic");

			softAssertion = new SoftAssert();
			//LoginLib.login();

			homePageWebE.ico_TeamChat.click();
			CommonLib.waitForObject(homePageWebE.btn_CreateChannel, "clickable", 10);

			homePageWebE.btn_CreateChannel.click();
			CommonLib.waitForObject(homePageWebE.txt_ChannelName, "visibility", 10);

			homePageWebE.txt_ChannelName.clear();
			homePageWebE.txt_ChannelName.sendKeys("Translate Channel");
			Thread.sleep(2000);

			for (int i = 0; i < 3; i++)
			{
				arrExpectedTeam.add(homePageWebE.lbl_TeamMembers.get(i).getText());
				homePageWebE.lbl_TeamMembers.get(i).click();
			}

			homePageWebE.btn_ConfirmChannel.click();
			CommonLib.waitForObject(homePageWebE.lbl_ConfirmMembers.get(0), "visibility", 10);

			homePageWebE.txt_DirectMessage.sendKeys("This is test message");
			Thread.sleep(2000);

			homePageWebE.ico_Translate.click();
			Thread.sleep(2000);

			homePageWebE.lst_Translte.click();
			Thread.sleep(2000);

			//intSize = homePageWebE.lbl_Languages.size();

			for (int i = 0; i < strTranslateLangauges.length; i++)
			{
				//strValue = homePageWebE.lbl_Languages.get(i).getText().trim();

				WebElement ele = driver.findElement(By.xpath("//span[@role='menuitem']//div[text()='" + strTranslateLangauges[i] + "']"));

				ele.click();
				Thread.sleep(2000);

				homePageWebE.btn_TranslateTo.click();
				Thread.sleep(4000);

				if (strTranslateLangauges[i].contains("Chinese"))
				{
					strExpectedTranslate = "这是测试消息";
				}
				else
				{
					strExpectedTranslate = properties.getProperty("Translate." + strTranslateLangauges[i]);
				}

				strActualTranslate = homePageWebE.txt_DirectMessage.getText().trim();

				System.out.println(strExpectedTranslate);
				System.out.println(strActualTranslate);

				if (strActualTranslate.equals(strExpectedTranslate))
				{
					Log4J.logp.info("Message Translate");
					softAssertion.assertTrue(true);
					child2.log(LogStatus.PASS, "Message translate in " + strTranslateLangauges[i] + " language before message has been sent");
				}
				else
				{
					Log4J.logp.info("Message not translate");
					softAssertion.assertTrue(false);
					child2.log(LogStatus.FAIL, "Message not translate in " + strTranslateLangauges[i] + " language before message has been sent");
				}

				homePageWebE.txt_DirectMessage.clear();
				homePageWebE.txt_DirectMessage.sendKeys("This is test message");
				Thread.sleep(2000);

				homePageWebE.lst_Translte.click();
				Thread.sleep(2000);
			}

			// Check After Send message

			driver.navigate().refresh();
			CommonLib.waitForObject(homePageWebE.btn_SendConversation, "clickable", 20);

			homePageWebE.lbl_ChannelName.get(0).click();
			Thread.sleep(2000);

			homePageWebE.txt_DirectMessage.sendKeys("This is test message");
			Thread.sleep(2000);

			homePageWebE.btn_SendConversation.click();
			Thread.sleep(2000);

			Actions builder = new Actions(driver);
			builder.moveToElement(homePageWebE.lbl_Templates.get(0)).build().perform();

			homePageWebE.ico_Setting.click();
			Thread.sleep(2000);

			homePageWebE.lst_Translte.click();
			Thread.sleep(2000);

			//intSize = homePageWebE.lbl_Languages.size();

			for (int i = 0; i < strTranslateLangauges.length; i++)
			{
				//strValue = homePageWebE.lbl_Languages.get(i).getText().trim();

				WebElement ele = driver.findElement(By.xpath("//span[@role='menuitem']//div[text()='" + strTranslateLangauges[i] + "']"));

				ele.click();
				Thread.sleep(2000);

				homePageWebE.btn_TranslateTo.click();
				Thread.sleep(4000);

				if (strTranslateLangauges[i].contains("Chinese"))
				{
					strExpectedTranslate = "这是测试消息";
				}
				else
				{
					strExpectedTranslate = properties.getProperty("Translate." + strTranslateLangauges[i]);
				}

				strActualTranslate = homePageWebE.lbl_Templates.get(0).getText().trim();

				System.out.println(strExpectedTranslate);
				System.out.println(strActualTranslate);

				if (strActualTranslate.equals(strExpectedTranslate))
				{
					Log4J.logp.info("Message Translate");
					softAssertion.assertTrue(true);
					child1.log(LogStatus.PASS, "Message translate in " + strTranslateLangauges[i] + " language After message has been sent");
				}
				else
				{
					Log4J.logp.info("Message not translate");
					softAssertion.assertTrue(false);
					child2.log(LogStatus.FAIL, "Message not translate in " + strTranslateLangauges[i] + " language After message has been sent");
				}

				homePageWebE.lst_Translte.click();
				Thread.sleep(2000);
			}

			driver.navigate().refresh();
			CommonLib.waitForObject(homePageWebE.btn_SendConversation, "clickable", 20);

			homePageWebE.lbl_ChannelName.get(0).click();
			Thread.sleep(2000);

			homePageWebE.ico_RemoveChannel.click();
			Thread.sleep(2000);

			homePageWebE.btn_ConfirmChannelRemove.click();
			Thread.sleep(2000);
			Log4J.logp.info("**** Ended :translateTeamMessage ****");
		}
		catch (Exception e)
		{
			CommonLib.takeScreenshots();
			e.printStackTrace();
			softAssertion.assertTrue(false, "createDeleteChannel Failed");
		}
		finally
		{
			if (child1.getRunStatus().toString().equalsIgnoreCase("unknown"))
				child1.log(LogStatus.FAIL, "Failed for Unknown status.");
			if (child2.getRunStatus().toString().equalsIgnoreCase("unknown"))
				child2.log(LogStatus.FAIL, "Failed for Unknown status.");

			translateTeamMessage.appendChild(child1);
			translateTeamMessage.appendChild(child2);

			MainMethod.extent.endTest(child1);
			MainMethod.extent.endTest(child2);

			if (child1.getRunStatus().toString().equalsIgnoreCase("FAIL") || child2.getRunStatus().toString().equalsIgnoreCase("FAIL"))
			{
				translateTeamMessage.log(LogStatus.FAIL, "translateTeamMessage is failed.");
			}
			else
			{
				translateTeamMessage.log(LogStatus.PASS, "translateTeamMessage is passed.");
			}

			MainMethod.extent.endTest(translateTeamMessage);

			softAssertion.assertAll();

			//LoginLib.logout();.
		}
	}

	/**
	 * 
	 * This method is use for Insert Template on channel
	 * 
	 */
	@Test(description = "Insert Template in Team Chat message ", priority = 11)
	public static void insertTemplate()
	{
		String strExpectedTemplateValue, strAcualTemplateValue;
		ExtentTest insertTemplate = MainMethod.extent.startTest("Test ID 26 : Insert Template in Team Chat Message").assignCategory("Regeression");
		try
		{

			Log4J.logp.info("**** Started :insertTemplate ****");

			softAssertion = new SoftAssert();
			//LoginLib.login();

			homePageWebE.ico_TeamChat.click();
			CommonLib.waitForObject(homePageWebE.btn_CreateChannel, "clickable", 10);

			homePageWebE.btn_CreateChannel.click();
			CommonLib.waitForObject(homePageWebE.txt_ChannelName, "visibility", 10);

			homePageWebE.txt_ChannelName.sendKeys("Template Insert Channel");
			Thread.sleep(2000);

			for (int i = 0; i < 3; i++)
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

			if (strAcualTemplateValue.equals(strExpectedTemplateValue))
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
			Log4J.logp.info("**** Ended :insertTemplate ****");
		}

		catch (Exception e)
		{
			CommonLib.takeScreenshots();
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

			MainMethod.extent.endTest(insertTemplate);
			softAssertion.assertAll();
			//LoginLib.logout();.
		}
	}

	/**
	 * 
	 * This method is use for check Direct Chat message
	 * 
	 */
	@Test(description = "Check Direct chat and clear search box", priority = 12)
	public static void checkDirectChat()
	{
		String strExpectedValue, strAttribute;
		int intSize;
		ExtentTest checkDirectChat = MainMethod.extent.startTest("Check Direct chat and clear search box").assignCategory("Regression");
		ExtentTest child1 = null;
		ExtentTest child2 = null;
		try
		{
			Log4J.logp.info("**** Started :checkDirectChat ****");

			child1 = MainMethod.extent.startTest("Test ID 27 : Sending a Direct Chat");
			child2 = MainMethod.extent.startTest("Test ID  28 : Clear Direct Chat Search box");

			softAssertion = new SoftAssert();
			//LoginLib.login();

			homePageWebE.ico_DirectChat.click();
			CommonLib.waitForObject(homePageWebE.lnk_TeamMember.get(0), "visibility", 10);

			homePageWebE.lnk_TeamMember.get(0).click();
			Thread.sleep(2000);

			homePageWebE.txt_DirectMessage.sendKeys("Test Direct Message");
			Thread.sleep(2000);

			homePageWebE.btn_SendConversation.click();
			Thread.sleep(2000);

			intSize = homePageWebE.lbl_DirectMessage.size();

			strExpectedValue = homePageWebE.lbl_DirectMessage.get(intSize - 1).getText().trim();

			if (strExpectedValue.equals("Test Direct Message"))
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

			if (strAttribute.isEmpty())
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

			Log4J.logp.info("**** Ended :checkDirectChat ****");
		}
		catch (Exception e)
		{
			CommonLib.takeScreenshots();
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

			MainMethod.extent.endTest(checkDirectChat);

			softAssertion.assertAll();

			//LoginLib.logout();.
		}
	}

	/**
	 * 
	 * This method is use for check Log sheet
	 * 
	 */
	@Test(description = "Check Log sheet", priority = 13)
	public static void checkLogSheet()
	{
		String strName;
		ExtentTest checkLogSheet = MainMethod.extent.startTest("Check Log Sheet").assignCategory("Regression");
		ExtentTest child1 = null;
		ExtentTest child2 = null;
		try
		{
			Log4J.logp.info("**** Started :checkLogSheet ****");

			child1 = MainMethod.extent.startTest("Test ID 30 :  Add Participants to Log Sheet");
			child2 = MainMethod.extent.startTest("Test ID 31 : Add Filter Log Sheet");
			softAssertion = new SoftAssert();
			//LoginLib.login();
			Properties properties = new Properties();
			properties.load(new FileInputStream("src/main/resources/Properties/data.properties"));
			strName = properties.getProperty("username");

			homePageWebE.ico_LogSheet.click();
			CommonLib.waitForObject(homePageWebE.btn_AddLogSheet, "clickable", 10);
			Thread.sleep(2000);

			homePageWebE.btn_AddLogSheet.click();
			CommonLib.waitForObject(homePageWebE.log_Sheet, "visibility", 10);
			Thread.sleep(2000);

			if (CommonLib.checkElementPresent(homePageWebE.log_Sheet))
			{
				Log4J.logp.info("Logsheet has been added successfully");
				softAssertion.assertTrue(true);
				child1.log(LogStatus.PASS, "Participants has been added in Log Sheet");
			}
			else
			{
				Log4J.logp.info("Logsheet not added");
				softAssertion.assertTrue(false);
				child1.log(LogStatus.FAIL, "Participants not added");
			}

			// Filter

			homePageWebE.btn_Filter.click();
			CommonLib.waitForObject(homePageWebE.txt_FilterSearch, "visibility", 10);

			homePageWebE.txt_FilterSearch.sendKeys(strName);
			Thread.sleep(2000);
			if (homePageWebE.log_Sheet.getText().trim().equals(strName))
			{
				Log4J.logp.info("Search functionality working proper");
				softAssertion.assertTrue(true);
				child2.log(LogStatus.PASS, "Search functionality working proper");
			}
			else
			{
				Log4J.logp.info("Search functionality not working");
				softAssertion.assertTrue(false);
				child2.log(LogStatus.FAIL, "Search Functionality not working");
			}

			Log4J.logp.info("**** Ended :checkLogSheet ****");
		}
		catch (Exception e)
		{
			CommonLib.takeScreenshots();
			e.printStackTrace();
			softAssertion.assertTrue(false, "checkLogSheet Failed");
		}
		finally
		{
			if (child1.getRunStatus().toString().equalsIgnoreCase("unknown"))
				child1.log(LogStatus.FAIL, "Failed for Unknown status.");
			if (child2.getRunStatus().toString().equalsIgnoreCase("unknown"))
				child2.log(LogStatus.FAIL, "Failed for Unknown status.");

			checkLogSheet.appendChild(child1);
			checkLogSheet.appendChild(child2);

			MainMethod.extent.endTest(child1);
			MainMethod.extent.endTest(child2);

			if (child1.getRunStatus().toString().equalsIgnoreCase("FAIL") || child2.getRunStatus().toString().equalsIgnoreCase("FAIL"))
			{
				checkLogSheet.log(LogStatus.FAIL, "checkLogSheet is failed.");
			}
			else
			{
				checkLogSheet.log(LogStatus.PASS, "checkLogSheet is passed.");
			}

			MainMethod.extent.endTest(checkLogSheet);

			softAssertion.assertAll();

			//LoginLib.logout();.
		}
	}

	/**
	 * 
	 * This method is use for add/delete/replace Columns in Log Sheet
	 * 
	 */
	@Test(description = "Add/delete/change/replace Columns in Log Sheet", priority = 14)
	public static void checkColumnsLogSheet()
	{
		int intBeforeColumns, intAfterColumns;
		String strValue;
		int intRemoveSize, intDelete;
		ExtentTest checkColumnsLogSheet = MainMethod.extent.startTest("Add/Deletechange/replace Columns in Log Sheet").assignCategory("Regression");
		ExtentTest child1 = null;
		ExtentTest child2 = null;
		ExtentTest child3 = null;
		ExtentTest child4 = null;
		try
		{
			Log4J.logp.info("**** Started :checkColumnsLogSheet ****");

			softAssertion = new SoftAssert();
			//LoginLib.login();

			child1 = MainMethod.extent.startTest("Test ID 33 :  Delete Log Sheet Columns");
			child2 = MainMethod.extent.startTest("Test ID 34 : Replace Deleted Log Sheet Columns");
			child3 = MainMethod.extent.startTest("Test ID 35 :  Add Log Sheet Columns");
			child4 = MainMethod.extent.startTest("Test ID 36 : Change Log Sheet Columns");

			homePageWebE.ico_LogSheet.click();
			CommonLib.waitForObject(homePageWebE.btn_AddLogSheet, "visibility", 10);
			Thread.sleep(2000);

			homePageWebE.btn_EditColumns.click();
			CommonLib.waitForObject(homePageWebE.btn_SaveColumnn, "clickable", 10);
			Thread.sleep(2000);

			intBeforeColumns = homePageWebE.columns_Size.size();

			System.out.println(intBeforeColumns);

			homePageWebE.btn_AddColumns.click();
			Thread.sleep(3000);

			intAfterColumns = homePageWebE.columns_Size.size();

			System.out.println(intAfterColumns);

			if (intAfterColumns == intBeforeColumns + 1)
			{
				Log4J.logp.info("New Column has been added");
				softAssertion.assertTrue(true);
				child3.log(LogStatus.PASS, "New Column has been added");

				CommonLib.scroll_PageHorizontally(homePageWebE.scroll_Horizontal, 7);

				//homePageWebE.txt_Columns.get(intAfterColumns -1).click();
				//Thread.sleep(2000);
				homePageWebE.txt_Columns.get(intAfterColumns - 1).clear();
				homePageWebE.txt_Columns.get(intAfterColumns - 1).sendKeys("Test Column");
				Thread.sleep(2000);

				homePageWebE.btn_SaveColumnn.click();
				CommonLib.waitForObject(homePageWebE.btn_EditColumns, "visibility", 10);

				strValue = homePageWebE.lbl_Columns.get(intAfterColumns - 1).getText().trim();

				System.out.println(strValue);

				if (strValue.contains("Test Column"))
				{
					Log4J.logp.info("New Column has been added with new name");
					softAssertion.assertTrue(true);
					child3.log(LogStatus.PASS, "New Column has been added with new name");
				}
				else
				{
					Log4J.logp.info("New Column not added with new name");
					softAssertion.assertTrue(false);
					child3.log(LogStatus.FAIL, "New Column not added with new name");
				}
			}
			else
			{
				Log4J.logp.info("New Column not added");
				softAssertion.assertTrue(false);
				child3.log(LogStatus.FAIL, "New Column not added");
			}

			// Edit Log Sheet Columns
			homePageWebE.btn_EditColumns.click();
			CommonLib.waitForObject(homePageWebE.btn_SaveColumnn, "clickable", 10);

			CommonLib.scroll_PageHorizontally(homePageWebE.scroll_Horizontal, 7);
			Thread.sleep(2000);

			homePageWebE.txt_Columns.get(intAfterColumns - 1).clear();
			homePageWebE.txt_Columns.get(intAfterColumns - 1).sendKeys("New Test Column");
			Thread.sleep(2000);

			homePageWebE.btn_SaveColumnn.click();
			CommonLib.waitForObject(homePageWebE.btn_EditColumns, "visibility", 10);

			strValue = homePageWebE.lbl_Columns.get(intAfterColumns - 1).getText().trim();

			System.out.println(strValue);

			if (strValue.contains("New Test Column"))
			{
				Log4J.logp.info("After Change Columns name New Column has been added with new name");
				softAssertion.assertTrue(true);
				child4.log(LogStatus.PASS, "After change columns name new column has been added with new name");
			}
			else
			{
				Log4J.logp.info("Column name not changed");
				softAssertion.assertTrue(false);
				child4.log(LogStatus.FAIL, "Column name not changed");
			}

			// Replace Log Sheet Columns
			homePageWebE.btn_EditColumns.click();
			CommonLib.waitForObject(homePageWebE.btn_SaveColumnn, "clickable", 10);

			CommonLib.scroll_PageHorizontally(homePageWebE.scroll_Horizontal, 7);
			Thread.sleep(2000);

			intRemoveSize = homePageWebE.ico_Remove.size();

			homePageWebE.ico_Remove.get(intRemoveSize - 1).click();
			Thread.sleep(2000);

			homePageWebE.txt_Columns.get(intAfterColumns - 1).clear();
			homePageWebE.txt_Columns.get(intAfterColumns - 1).sendKeys("Replace Test Column");
			Thread.sleep(2000);

			homePageWebE.btn_Replace.click();
			Thread.sleep(2000);

			homePageWebE.btn_SaveColumnn.click();
			CommonLib.waitForObject(homePageWebE.btn_EditColumns, "visibility", 10);

			strValue = homePageWebE.lbl_Columns.get(intAfterColumns - 1).getText().trim();

			System.out.println(strValue);

			if (strValue.contains("Replace Test Column"))
			{
				Log4J.logp.info("After Replace Columns name New Column has been added with new name");
				softAssertion.assertTrue(true);
				child2.log(LogStatus.PASS, "After Replace Columns name new column has been added with new name");
			}
			else
			{
				Log4J.logp.info("Column name not Replaceed");
				softAssertion.assertTrue(false);
				child2.log(LogStatus.FAIL, "Column name not Replaceed");
			}

			// Delete Column

			intAfterColumns = homePageWebE.lbl_Columns.size();

			homePageWebE.btn_EditColumns.click();
			CommonLib.waitForObject(homePageWebE.btn_SaveColumnn, "clickable", 10);

			CommonLib.scroll_PageHorizontally(homePageWebE.scroll_Horizontal, 7);
			Thread.sleep(2000);

			intRemoveSize = homePageWebE.ico_Remove.size();

			homePageWebE.ico_Remove.get(intRemoveSize - 1).click();
			Thread.sleep(2000);

			homePageWebE.btn_SaveColumnn.click();
			CommonLib.waitForObject(homePageWebE.btn_EditColumns, "visibility", 10);

			intDelete = homePageWebE.lbl_Columns.size();

			System.out.println(intAfterColumns + " Ands " + intDelete);

			//strValue = homePageWebE.lbl_Columns.get(intAfterColumns - 1).getText().trim();

			//System.out.println(strValue);

			if (intDelete == intAfterColumns - 1)
			{
				Log4J.logp.info("Column has been deleted");
				softAssertion.assertTrue(true);
				child1.log(LogStatus.PASS, "Column has been deleted");

			}
			else
			{
				Log4J.logp.info("Column not deleted");
				softAssertion.assertTrue(false);
				child1.log(LogStatus.FAIL, "Column not deleted");
			}

			Log4J.logp.info("**** Ended :checkColumnsLogSheet ****");
		}
		catch (Exception e)
		{
			CommonLib.takeScreenshots();
			e.printStackTrace();
			softAssertion.assertTrue(false, "checkColumnsLogSheet Failed");
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

			checkColumnsLogSheet.appendChild(child1);
			checkColumnsLogSheet.appendChild(child2);
			checkColumnsLogSheet.appendChild(child3);
			checkColumnsLogSheet.appendChild(child4);

			MainMethod.extent.endTest(child1);
			MainMethod.extent.endTest(child2);
			MainMethod.extent.endTest(child3);
			MainMethod.extent.endTest(child4);

			if (child1.getRunStatus().toString().equalsIgnoreCase("FAIL") || child2.getRunStatus().toString().equalsIgnoreCase("FAIL") || child3.getRunStatus().toString().equalsIgnoreCase("FAIL") || child4.getRunStatus().toString().equalsIgnoreCase("FAIL"))
			{
				checkColumnsLogSheet.log(LogStatus.FAIL, "checkColumnsLogSheet is failed.");
			}
			else
			{
				checkColumnsLogSheet.log(LogStatus.PASS, "checkColumnsLogSheet is passed.");
			}

			MainMethod.extent.endTest(checkColumnsLogSheet);

			softAssertion.assertAll();

			//LoginLib.logout();.
		}
	}

	/**
	 * 
	 * This method is check analytics page
	 * 
	 */
	@Test(description = "Check Analytics page", priority = 15)
	public static void checkAnalytics()
	{
		//String[] strTableContents = {"Message","Usage","Score","Positivity","Negativity"};
		String strDate;
		boolean bstatus = false;
		ExtentTest checkAnalytics = MainMethod.extent.startTest("Check Analytics Page").assignCategory("Regression");
		ExtentTest child1 = null;
		ExtentTest child2 = null;
		ExtentTest child3 = null;
		ExtentTest child4 = null;
		ExtentTest child5 = null;
		try
		{
			Log4J.logp.info("**** Started :checkAnalytics ****");

			softAssertion = new SoftAssert();

			child1 = MainMethod.extent.startTest("Test ID 37 : Choose Analyticd Start and End Dates");
			child2 = MainMethod.extent.startTest("Test ID 38 : Validate Message analysis for Aggregate");
			child3 = MainMethod.extent.startTest("Test ID 39 : Validate Message analysis for Inbound");
			child4 = MainMethod.extent.startTest("Test ID 40 : Validate Message analysis for Reply");
			child5 = MainMethod.extent.startTest("Test ID 41 : Validate Message analysis for Composition");

			homePageWebE.ico_Analytics.click();
			CommonLib.waitForObject(homePageWebE.txt_StartDate, "Visibility", 20);

			// Check with adding start Date and Edn Date

			homePageWebE.txt_StartDate.click();
			Thread.sleep(2000);

			CommonLib.selectDateAnalytics("07/22/2018", "");

			//JavascriptExecutor js = (JavascriptExecutor)driver; 

			//strDate = (String) js.executeScript("$('#startDateTimePicker').val()");

			strDate = homePageWebE.txt_StartDate.getAttribute("value");
			System.out.println(strDate);
			if (strDate.contains("07/22/2018"))
			{
				Log4J.logp.info("Start Date has been added");
				softAssertion.assertTrue(true);
				child1.log(LogStatus.PASS, "Start date has been added");
			}
			else
			{
				Log4J.logp.info("Start Date not added");
				softAssertion.assertTrue(false);
				child1.log(LogStatus.FAIL, "Start date not added");
			}

			homePageWebE.txt_EndDate.click();
			Thread.sleep(2000);

			CommonLib.selectDateAnalytics("07/25/2018", "End");

			//strDate = (String) js.executeScript("$('#endDateTimePicker').val()");

			strDate = homePageWebE.txt_EndDate.getAttribute("value");
			System.out.println(strDate);
			if (strDate.contains("07/25/2018"))
			{
				Log4J.logp.info("End Date has been added");
				softAssertion.assertTrue(true);
				child1.log(LogStatus.PASS, "End date has been added");
			}
			else
			{
				Log4J.logp.info("End Date not added");
				softAssertion.assertTrue(false);
				child1.log(LogStatus.FAIL, "End date not added");
			}

			CommonLib.scroll_Page(homePageWebE.analytics_Scroll, 1);
			Thread.sleep(2000);

			// Check aggregate option

			homePageWebE.rdo_Aggregate.click();
			Thread.sleep(2000);

			CommonLib.scroll_Page(homePageWebE.analytics_Scroll, 1);
			Thread.sleep(2000);

			bstatus = HomePageLib.checkTableContents();

			if (bstatus == true)
			{
				Log4J.logp.info("Table and Contents has been displayed in Aggregate");
				softAssertion.assertTrue(true);
				child2.log(LogStatus.PASS, "Table and content has been displayed in Aggregate");
			}
			else
			{
				Log4J.logp.info("Table and Contents not displayed in Aggregate");
				softAssertion.assertTrue(false);
				child2.log(LogStatus.FAIL, "Table and content not displayed in Aggregate");
			}

			// Check Inbound option

			CommonLib.scroll_Page_Up(homePageWebE.analytics_Scroll, 1);
			Thread.sleep(2000);

			homePageWebE.rdo_Inbound.click();
			Thread.sleep(2000);

			CommonLib.scroll_Page(homePageWebE.analytics_Scroll, 1);
			Thread.sleep(2000);

			bstatus = HomePageLib.checkTableContents();

			if (bstatus == true)
			{
				Log4J.logp.info("Table and Contents has been displayed in Inbound");
				softAssertion.assertTrue(true);
				child3.log(LogStatus.PASS, "Table and content has been displayed in Inbound");
			}
			else
			{
				Log4J.logp.info("Table and Contents not displayed in Inbound");
				softAssertion.assertTrue(false);
				child3.log(LogStatus.FAIL, "Table and content not displayed in Inbound");
			}

			// Check Reply option

			CommonLib.scroll_Page_Up(homePageWebE.analytics_Scroll, 1);
			Thread.sleep(2000);

			homePageWebE.rdo_Reply.click();
			Thread.sleep(2000);

			CommonLib.scroll_Page(homePageWebE.analytics_Scroll, 1);
			Thread.sleep(2000);

			bstatus = HomePageLib.checkTableContents();

			if (bstatus == true)
			{
				Log4J.logp.info("Table and Contents has been displayed in Reply");
				softAssertion.assertTrue(true);
				child4.log(LogStatus.PASS, "Table and content has been displayed in Reply");
			}
			else
			{
				Log4J.logp.info("Table and Contents not displayed in Reply");
				softAssertion.assertTrue(false);
				child4.log(LogStatus.FAIL, "Table and content not displayed in Reply");
			}

			// Check Compposition option

			CommonLib.scroll_Page_Up(homePageWebE.analytics_Scroll, 1);
			Thread.sleep(2000);

			homePageWebE.rdo_Composition.click();
			Thread.sleep(2000);

			CommonLib.scroll_Page(homePageWebE.analytics_Scroll, 1);
			Thread.sleep(2000);

			bstatus = HomePageLib.checkTableContents();

			if (bstatus == true)
			{
				Log4J.logp.info("Table and Contents has been displayed in Composition");
				softAssertion.assertTrue(true);
				child5.log(LogStatus.PASS, "Table and content has been displayed in Composition");
			}
			else
			{
				Log4J.logp.info("Table and Contents not displayed in Composition");
				softAssertion.assertTrue(false);
				child5.log(LogStatus.FAIL, "Table and content not displayed in Composition");

			}
			Log4J.logp.info("**** Ended :checkAnalytics ****");
		}
		catch (Exception e)
		{
			CommonLib.takeScreenshots();
			e.printStackTrace();
			softAssertion.assertTrue(false, "checkAnalytics Failed");
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
			if (child5.getRunStatus().toString().equalsIgnoreCase("unknown"))
				child5.log(LogStatus.FAIL, "Failed for Unknown status.");

			checkAnalytics.appendChild(child1);
			checkAnalytics.appendChild(child2);
			checkAnalytics.appendChild(child3);
			checkAnalytics.appendChild(child4);
			checkAnalytics.appendChild(child5);

			MainMethod.extent.endTest(child1);
			MainMethod.extent.endTest(child2);
			MainMethod.extent.endTest(child3);
			MainMethod.extent.endTest(child4);
			MainMethod.extent.endTest(child5);

			if (child1.getRunStatus().toString().equalsIgnoreCase("FAIL") || child2.getRunStatus().toString().equalsIgnoreCase("FAIL") || child3.getRunStatus().toString().equalsIgnoreCase("FAIL") || child4.getRunStatus().toString().equalsIgnoreCase("FAIL")
					|| child5.getRunStatus().toString().equalsIgnoreCase("FAIL"))
			{
				checkAnalytics.log(LogStatus.FAIL, "checkAnalytics is failed.");
			}
			else
			{
				checkAnalytics.log(LogStatus.PASS, "checkAnalytics is passed.");
			}

			MainMethod.extent.endTest(checkAnalytics);

			softAssertion.assertAll();

			//LoginLib.logout();.
		}
	}

	/**
	 * 
	 * This method is check Reservation tab
	 * 
	 */
	@Test(description = "Check Reservation", priority = 16)
	public static void checkReservation()
	{
		String strReservationId, strRoomNumber, strArrival, strDepart, strPhone, strEmail, strFirstName, strLastName;
		ExtentTest checkReservation = MainMethod.extent.startTest("Check Reservation").assignCategory("Regression");
		ExtentTest child1 = null;
		ExtentTest child2 = null;
		ExtentTest child3 = null;
		ExtentTest child4 = null;
		try
		{
			Log4J.logp.info("**** Started :checkReservation ****");

			softAssertion = new SoftAssert();

			child1 = MainMethod.extent.startTest("Test ID 42 : Validate Number Reservation");
			child2 = MainMethod.extent.startTest("Test ID 43 : Validate Add Reservation");
			child3 = MainMethod.extent.startTest("Test ID 49 : Validate Search Reservation");
			child4 = MainMethod.extent.startTest("Test ID 51 : Validate Delete Reservation");

			homePageWebE.ico_Reservation.click();
			CommonLib.waitForObject(homePageWebE.lbl_ReservationHeader, "visibility", 10);

			Properties properties = new Properties();
			properties.load(new FileInputStream("src/main/resources/Properties/data.properties"));
			strReservationId = properties.getProperty("ResevationId");
			strRoomNumber = properties.getProperty("RoomNumber");
			strFirstName = properties.getProperty("FirstName");
			strLastName = properties.getProperty("LastName");
			strDepart = properties.getProperty("depart");
			strArrival = properties.getProperty("arrival");
			strPhone = properties.getProperty("phone");
			strEmail = properties.getProperty("email");

			if (CommonLib.checkElementPresent(homePageWebE.lbl_ReservationHeader))
			{
				Log4J.logp.info("Reservation count has been displayed in Reservation page");
				softAssertion.assertTrue(true);
				child1.log(LogStatus.PASS, "Reservation cound has been displayed in Reservation page");
			}
			else
			{
				Log4J.logp.info("Resevration count not displayed");
				softAssertion.assertTrue(false);
				child1.log(LogStatus.FAIL, "Reservation cound not displayed");
			}

			homePageWebE.btn_Reservation.click();
			CommonLib.waitForObject(homePageWebE.btn_Save, "visibility", 10);

			homePageWebE.txt_ReservationId.sendKeys(strReservationId);
			Thread.sleep(2000);
			homePageWebE.txt_RoomNumber.sendKeys(strRoomNumber);
			Thread.sleep(2000);
			homePageWebE.txt_FirstName.sendKeys(strFirstName);
			Thread.sleep(2000);
			homePageWebE.txt_LastName.sendKeys(strLastName);
			Thread.sleep(2000);
			homePageWebE.txt_Arrival.click();
			Thread.sleep(2000);

			CommonLib.selectDateReservation(strArrival, "Arrival");
			Thread.sleep(2000);

			homePageWebE.txt_Departure.click();
			Thread.sleep(2000);

			CommonLib.selectDateReservation(strDepart, "Depart");
			Thread.sleep(2000);

			homePageWebE.txt_Phone.sendKeys(strPhone);
			Thread.sleep(2000);

			homePageWebE.txt_EmailID.sendKeys(strEmail);
			Thread.sleep(2000);

			homePageWebE.btn_Save.click();
			CommonLib.waitForObject(homePageWebE.lbl_ReservationID.get(0), "visibility", 20);

			if (homePageWebE.lbl_ReservationID.get(0).getText().trim().equals(strReservationId))
			{
				Log4J.logp.info("Reservation has been added in reservation table");
				softAssertion.assertTrue(true);
				child2.log(LogStatus.PASS, "Reservation has been added in reservation table");
			}
			else
			{
				Log4J.logp.info("Reservation not added");
				softAssertion.assertTrue(false);
				child2.log(LogStatus.FAIL, "Reservation not added");
			}

			// Search bar

			homePageWebE.txt_SearchReservation.clear();
			homePageWebE.txt_SearchReservation.sendKeys(strRoomNumber);
			Thread.sleep(4000);

			//strName = strFirstName + " " + strLastName;

			System.out.println(strRoomNumber);
			System.out.println(homePageWebE.lbl_RoomNumber.getText().trim());

			if (homePageWebE.lbl_RoomNumber.getText().trim().equals(strRoomNumber))
			{
				Log4J.logp.info("Search result displayed");
				softAssertion.assertTrue(true);
				child3.log(LogStatus.PASS, "Search Result displayed");
			}
			else
			{
				Log4J.logp.info("Not found search result");
				softAssertion.assertTrue(false);
				child3.log(LogStatus.FAIL, "Not found search result");
			}

			// Check Delete Reservation
			driver.navigate().refresh();
			CommonLib.waitForObject(homePageWebE.btn_Reservation, "visibility", 10);

			homePageWebE.chk_Reservation.get(0).click();
			Thread.sleep(2000);

			homePageWebE.btn_Delete.click();
			CommonLib.waitForObject(homePageWebE.btn_ConfirmDelete, "visibility", 10);

			homePageWebE.btn_ConfirmDelete.click();
			Thread.sleep(5000);
			CommonLib.waitForObject(loginWebE.lbl_UserName, "visibility", 30);

			if (homePageWebE.lbl_ReservationID.get(0).getText().trim().equals(strReservationId))
			{
				Log4J.logp.info("Reservation not deleted");
				softAssertion.assertTrue(false);
				child4.log(LogStatus.FAIL, "Reservation not deleted");
			}
			else
			{
				Log4J.logp.info("Reservation deleted");
				softAssertion.assertTrue(true);
				child4.log(LogStatus.PASS, "Reservation deleted");
			}

			Log4J.logp.info("**** Ended :checkReservation ****");
		}
		catch (Exception e)
		{
			CommonLib.takeScreenshots();
			e.printStackTrace();
			softAssertion.assertTrue(false, "checkLogSheet Failed");
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

			checkReservation.appendChild(child1);
			checkReservation.appendChild(child2);
			checkReservation.appendChild(child3);
			checkReservation.appendChild(child4);

			MainMethod.extent.endTest(child1);
			MainMethod.extent.endTest(child2);
			MainMethod.extent.endTest(child3);
			MainMethod.extent.endTest(child4);

			if (child1.getRunStatus().toString().equalsIgnoreCase("FAIL") || child2.getRunStatus().toString().equalsIgnoreCase("FAIL") || child3.getRunStatus().toString().equalsIgnoreCase("FAIL") || child4.getRunStatus().toString().equalsIgnoreCase("FAIL"))
			{
				checkReservation.log(LogStatus.FAIL, "checkReservation is failed.");
			}
			else
			{
				checkReservation.log(LogStatus.PASS, "checkReservation is passed.");
			}
			MainMethod.extent.endTest(checkReservation);
			softAssertion.assertAll();
			//LoginLib.logout();.
		}
	}

	/**
	 * 
	 * This method is check Validation of Add Reservation Page
	 * 
	 */
	@Test(description = "To check Validation of Add Reservation Page", priority = 17)
	public static void checkValidationReservation()
	{
		boolean bstatus = false;
		String strReservationId, strFirstName, strArrival, strDepart, strPhone, strAttribute;
		ExtentTest checkValidationReservation = MainMethod.extent.startTest("Check Validation of Add Reservation page").assignCategory("Regression");
		ExtentTest child1 = null;
		ExtentTest child2 = null;
		ExtentTest child3 = null;
		ExtentTest child4 = null;
		ExtentTest child5 = null;
		try
		{
			Log4J.logp.info("**** Started :checkValidationReservation ****");

			child1 = MainMethod.extent.startTest("Test ID 44 : Validate Reservation ID Required");
			child2 = MainMethod.extent.startTest("Test ID 45 : Validate FirstName Required");
			child3 = MainMethod.extent.startTest("Test ID 46 : Validate Arrival Required");
			child4 = MainMethod.extent.startTest("Test ID 47 : Validate Departure Required");
			child5 = MainMethod.extent.startTest("Test ID 48 : Validate Phone Required");

			softAssertion = new SoftAssert();

			Properties properties = new Properties();
			properties.load(new FileInputStream("src/main/resources/Properties/data.properties"));
			strReservationId = properties.getProperty("ResevationId");
			strFirstName = properties.getProperty("FirstName");
			strDepart = properties.getProperty("depart");
			strArrival = properties.getProperty("arrival");
			strPhone = properties.getProperty("phone");

			homePageWebE.ico_Reservation.click();
			CommonLib.waitForObject(homePageWebE.btn_Reservation, "clickable", 20);

			homePageWebE.btn_Reservation.click();
			CommonLib.waitForObject(homePageWebE.btn_Save, "visibility", 10);

			// Validate Reservation Id required

			bstatus = HomePageLib.addReservationDetails(null, strFirstName, strArrival, strDepart, strPhone);

			if (bstatus == true)
			{
				homePageWebE.btn_Save.click();
				Thread.sleep(3000);

				strAttribute = homePageWebE.txt_ReservationId.getAttribute("class");

				if (strAttribute.equalsIgnoreCase("Invalid"))
				{
					Log4J.logp.info("Reservation ID is Required field");
					softAssertion.assertTrue(true);
					child1.log(LogStatus.PASS, "Reservation ID is required field");
				}
				else
				{
					Log4J.logp.info("Reservation ID not validated");
					softAssertion.assertTrue(false);
					child1.log(LogStatus.FAIL, "Reservation ID not validated");
				}
			}

			// Validate First Name required
			driver.navigate().refresh();
			CommonLib.waitForObject(homePageWebE.btn_Save, "visibility", 10);

			bstatus = HomePageLib.addReservationDetails(strReservationId, null, strArrival, strDepart, strPhone);

			if (bstatus == true)
			{
				homePageWebE.btn_Save.click();
				Thread.sleep(3000);

				strAttribute = homePageWebE.txt_FirstName.getAttribute("class");

				if (strAttribute.equalsIgnoreCase("Invalid"))
				{
					Log4J.logp.info("First Name is Required field");
					softAssertion.assertTrue(true);
					child2.log(LogStatus.PASS, "First Name is required field");
				}
				else
				{
					Log4J.logp.info("First Name not validated");
					softAssertion.assertTrue(false);
					child2.log(LogStatus.FAIL, "First Name not validated");
				}
			}

			// Validate Arrival Date required
			driver.navigate().refresh();
			CommonLib.waitForObject(homePageWebE.btn_Save, "visibility", 10);

			bstatus = HomePageLib.addReservationDetails(strReservationId, strFirstName, null, strDepart, strPhone);

			if (bstatus == true)
			{
				homePageWebE.btn_Save.click();
				Thread.sleep(3000);

				//strAttribute = homePageWebE.txt_ReservationId.getAttribute("class");

				if (CommonLib.checkElementPresent(homePageWebE.txt_Arrival))
				{
					Log4J.logp.info("Arrival Date field is Required field");
					softAssertion.assertTrue(true);
					child3.log(LogStatus.PASS, "Arrival Date is required field");
				}
				else
				{
					Log4J.logp.info("Arrival Date not validated");
					softAssertion.assertTrue(false);
					child3.log(LogStatus.FAIL, "Arrival Date not validated");
				}
			}

			// Validate Depart Date required
			driver.navigate().refresh();
			CommonLib.waitForObject(homePageWebE.btn_Save, "visibility", 10);

			bstatus = HomePageLib.addReservationDetails(strReservationId, strFirstName, strArrival, null, strPhone);

			if (bstatus == true)
			{
				homePageWebE.btn_Save.click();
				Thread.sleep(3000);

				//strAttribute = homePageWebE.txt_ReservationId.getAttribute("class");

				if (CommonLib.checkElementPresent(homePageWebE.txt_Departure))
				{
					Log4J.logp.info("Departure Date field is Required field");
					softAssertion.assertTrue(true);
					child4.log(LogStatus.PASS, "Departure Date is required field");
				}
				else
				{
					Log4J.logp.info("Departure Date not validated");
					softAssertion.assertTrue(false);
					child4.log(LogStatus.FAIL, "Departure Date not validated");
				}
			}

			// Validate Phone Number required
			driver.navigate().refresh();
			CommonLib.waitForObject(homePageWebE.btn_Save, "visibility", 10);

			bstatus = HomePageLib.addReservationDetails(strReservationId, strFirstName, strArrival, strDepart, null);

			if (bstatus == true)
			{
				homePageWebE.btn_Save.click();
				Thread.sleep(3000);

				//strAttribute = homePageWebE.txt_Phone.getAttribute("class");

				if (CommonLib.checkElementPresent(homePageWebE.txt_Phone))
				{
					Log4J.logp.info("Phone number is Required field");
					softAssertion.assertTrue(true);
					child5.log(LogStatus.PASS, "Phone number is required field");
				}
				else
				{
					Log4J.logp.info("Phone number not validated");
					softAssertion.assertTrue(false);
					child5.log(LogStatus.FAIL, "Phone number not validated");
				}
			}

			Log4J.logp.info("**** Ended :checkValidationReservation ****");
		}
		catch (Exception e)
		{
			CommonLib.takeScreenshots();
			e.printStackTrace();
			softAssertion.assertTrue(false, "checkValidationReservation Failed");
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
			if (child5.getRunStatus().toString().equalsIgnoreCase("unknown"))
				child5.log(LogStatus.FAIL, "Failed for Unknown status.");

			checkValidationReservation.appendChild(child1);
			checkValidationReservation.appendChild(child2);
			checkValidationReservation.appendChild(child3);
			checkValidationReservation.appendChild(child4);
			checkValidationReservation.appendChild(child5);

			MainMethod.extent.endTest(child1);
			MainMethod.extent.endTest(child2);
			MainMethod.extent.endTest(child3);
			MainMethod.extent.endTest(child4);
			MainMethod.extent.endTest(child5);

			if (child1.getRunStatus().toString().equalsIgnoreCase("FAIL") || child2.getRunStatus().toString().equalsIgnoreCase("FAIL") || child3.getRunStatus().toString().equalsIgnoreCase("FAIL") || child4.getRunStatus().toString().equalsIgnoreCase("FAIL")
					|| child5.getRunStatus().toString().equalsIgnoreCase("FAIL"))
			{
				checkValidationReservation.log(LogStatus.FAIL, "checkValidationReservation is failed.");
			}
			else
			{
				checkValidationReservation.log(LogStatus.PASS, "checkValidationReservation is passed.");
			}
			MainMethod.extent.endTest(checkValidationReservation);
			softAssertion.assertAll();
			//LoginLib.logout();.
		}
	}

	/**
	 * 
	 * This method is use for Upload reservation
	 * 
	 */
	@Test(description = "Upload Reservation", priority = 18)
	public static void uploadReservation()
	{
		ExtentTest uploadReservation = MainMethod.extent.startTest("Test ID 51 : Validate Reservation Uploaded ");
		try
		{
			Log4J.logp.info("**** Started : uploadReservation ****");

			softAssertion = new SoftAssert();

			homePageWebE.ico_Reservation.click();
			CommonLib.waitForObject(homePageWebE.btn_Reservation, "visibility", 10);

			homePageWebE.btn_UploadReservation.click();
			Thread.sleep(2000);

			CommonLib.uploadFile("Reservations-Sample.xlsx");
			Thread.sleep(5000);

			if (homePageWebE.lbl_ReservationID.get(0).getText().trim().equals("1234567"))
			{
				Log4J.logp.info("Reservation added with uploading file");
				softAssertion.assertTrue(false);
				uploadReservation.log(LogStatus.PASS, "Reservation added with uploading file");
			}
			else
			{
				Log4J.logp.info("Reservation not added");
				softAssertion.assertTrue(true);
				uploadReservation.log(LogStatus.FAIL, "Reservation not added uploading file");
			}

			homePageWebE.chk_Reservation.get(0).click();
			Thread.sleep(2000);

			homePageWebE.btn_Delete.click();
			CommonLib.waitForObject(homePageWebE.btn_ConfirmDelete, "visibility", 10);

			homePageWebE.btn_ConfirmDelete.click();
			Thread.sleep(5000);
			CommonLib.waitForObject(loginWebE.lbl_UserName, "visibility", 30);

			Log4J.logp.info("**** Ended : uploadReservation ****");
		}
		catch (Exception e)
		{
			// TODO: handle exception
			CommonLib.takeScreenshots();
			e.printStackTrace();
			softAssertion.assertTrue(false, "Test ID 51 : Start Conversation Failed");
		}
		finally
		{

			if (uploadReservation.getRunStatus().toString().equalsIgnoreCase("unknown"))
				uploadReservation.log(LogStatus.FAIL, "Test ID 51 : Failed for Unknown status.");
			MainMethod.extent.endTest(uploadReservation);

			if (uploadReservation.getRunStatus().toString().equalsIgnoreCase("FAIL"))
			{
				uploadReservation.log(LogStatus.FAIL, "Test ID 51 : Upload Reservation is failed.");
			}
			else
			{
				uploadReservation.log(LogStatus.PASS, "Test ID 51 : Upload Reservation is passed.");
			}

			softAssertion.assertAll();
			//LoginLib.logout();.
		}
	}

	/**
	 * 
	 * This method is check menu of Left Navigation panel of Company tab
	 * 
	 */
	@Test(description = "Check Menu of Company tab", priority = 19)
	public static void checkCompanyMenu()
	{
		String[] strCompanyMenu = { "Settings", "Departments", "Templates", "Autoresponders", "Campaign History", "Surveys", "Messaging Services", "Reports", "Integrations", "Automated Messages" };
		ExtentTest checkCompanyMenu = MainMethod.extent.startTest("Test ID 52 : Validate Settings Tab");
		try
		{
			Log4J.logp.info("**** Started : checkCompanyMenu ****");

			softAssertion = new SoftAssert();

			homePageWebE.ico_Company.click();
			CommonLib.waitForObject(homePageWebE.lbl_CompanyMenu.get(0), "visibility", 10);

			for (int i = 0; i < strCompanyMenu.length; i++)
			{
				if (strCompanyMenu[i].equals(homePageWebE.lbl_CompanyMenu.get(i).getText().trim()))
				{
					Log4J.logp.info(strCompanyMenu[i] + " has been displayed in Left navigation panel of Company page");
					softAssertion.assertTrue(true);
					checkCompanyMenu.log(LogStatus.PASS, strCompanyMenu[i] + " has been displayed in Left navigation panel of Company page");
				}
				else
				{
					Log4J.logp.info(strCompanyMenu[i] + " Not found in Left navigation panel of Company page");
					softAssertion.assertTrue(false);
					checkCompanyMenu.log(LogStatus.PASS, strCompanyMenu[i] + " Not found in Left navigation panel of Company page");
				}
			}

			Log4J.logp.info("**** Ended : checkCompanyMenu ****");
		}
		catch (Exception e)
		{
			// TODO: handle exception
			CommonLib.takeScreenshots();
			e.printStackTrace();
			softAssertion.assertTrue(false, "Test ID 52 : checkCompanyMenu Failed");
		}
		finally
		{

			if (checkCompanyMenu.getRunStatus().toString().equalsIgnoreCase("unknown"))
				checkCompanyMenu.log(LogStatus.FAIL, "Test ID 52 : Failed for Unknown status.");
			MainMethod.extent.endTest(checkCompanyMenu);

			if (checkCompanyMenu.getRunStatus().toString().equalsIgnoreCase("FAIL"))
			{
				checkCompanyMenu.log(LogStatus.FAIL, "Test ID 52 : checkCompanyMenu is failed.");
			}
			else
			{
				checkCompanyMenu.log(LogStatus.PASS, "Test ID 52 : checkCompanyMenu is passed.");
			}

			softAssertion.assertAll();
			//LoginLib.logout();.
		}
	}

	/**
	 * 
	 * This method is check Setting menu of Left Navigation Panel in Company Page
	 * 
	 */
	@Test(description = "Check Setting menu in Company Page", priority = 21)
	public static void checkSettingMenu()
	{
		String strCompanyName, strActualTimeZone, strExpectedTimeZone;
		ExtentTest checkSettingMenu = MainMethod.extent.startTest("Check Setting menu in Company Page").assignCategory("Regeression");
		ExtentTest child1 = null;
		ExtentTest child2 = null;
		try
		{
			Log4J.logp.info("**** Started : checkSettingMenu ****");

			child1 = MainMethod.extent.startTest("Test ID 53 : Validate Changing Company Name");
			child2 = MainMethod.extent.startTest("Test ID : Validate Changing Timezone");

			softAssertion = new SoftAssert();
			strCompanyName = "New Company Name";

			homePageWebE.ico_Company.click();
			CommonLib.waitForObject(homePageWebE.lbl_LeftCompanyName, "visibility", 10);

			homePageWebE.lbl_CompanyMenu.get(0).click();
			CommonLib.waitForObject(homePageWebE.txt_CompanyDetails.get(0), "visibility", 10);

			// Enter Company Name
			homePageWebE.txt_CompanyDetails.get(0).click();
			Thread.sleep(2000);
			homePageWebE.txt_CompanyDetails.get(0).clear();
			homePageWebE.txt_CompanyDetails.get(0).sendKeys(strCompanyName);
			Thread.sleep(2000);

			if (homePageWebE.lbl_LeftCompanyName.getText().trim().equals(strCompanyName))
			{
				Log4J.logp.info("Company name has been changed and updated on Left Navigation panel");
				softAssertion.assertTrue(true);
				child1.log(LogStatus.PASS, "Comany name has been changes and updated on Left Navigation Panel");
			}
			else
			{
				Log4J.logp.info("Company name not updated on Left Navigation panel");
				softAssertion.assertTrue(false);
				child1.log(LogStatus.FAIL, "Company name not updated on Left Navigation Panel");
			}

			if (homePageWebE.lst_WhistleDropdown.getText().trim().equals(strCompanyName))
			{
				Log4J.logp.info("Company name has been changed and updated on Whistle Dropdown");
				softAssertion.assertTrue(true);
				child1.log(LogStatus.PASS, "Comany name has been changes and updated on Whistle Dropdown");
			}
			else
			{
				Log4J.logp.info("Company name not updated on Whistle Dropdown");
				softAssertion.assertTrue(false);
				child1.log(LogStatus.FAIL, "Company name not updated on Whistle Dropdown");
			}

			// Change TimeZone
			homePageWebE.txt_CompanyDetails.get(1).click();
			Thread.sleep(2000);

			strExpectedTimeZone = homePageWebE.lst_TimeZone.get(3).getText();

			homePageWebE.lst_TimeZone.get(3).click();
			Thread.sleep(2000);

			strActualTimeZone = homePageWebE.txt_CompanyDetails.get(1).getAttribute("value");
			strActualTimeZone = strActualTimeZone.replace("  ", " ");
			System.out.println(strActualTimeZone.trim());
			System.out.println(strExpectedTimeZone.trim());

			if (strActualTimeZone.trim().equals(strExpectedTimeZone.trim()))
			{
				Log4J.logp.info("TimeZone has been updated");
				softAssertion.assertTrue(true);
				child2.log(LogStatus.PASS, "TimeZone has been updated");
			}
			else
			{
				Log4J.logp.info("Timezone not updated");
				softAssertion.assertTrue(false);
				child2.log(LogStatus.FAIL, "Timezone not updated");
			}

			Log4J.logp.info("**** Ended : checkSettingMenu ****");
		}
		catch (Exception e)
		{
			CommonLib.takeScreenshots();
			e.printStackTrace();
			softAssertion.assertTrue(false, "checkSettingMenu Failed");
		}
		finally
		{
			if (child1.getRunStatus().toString().equalsIgnoreCase("unknown"))
				child1.log(LogStatus.FAIL, "Failed for Unknown status.");
			if (child2.getRunStatus().toString().equalsIgnoreCase("unknown"))
				child2.log(LogStatus.FAIL, "Failed for Unknown status.");

			checkSettingMenu.appendChild(child1);
			checkSettingMenu.appendChild(child2);

			MainMethod.extent.endTest(child1);
			MainMethod.extent.endTest(child2);

			if (child1.getRunStatus().toString().equalsIgnoreCase("FAIL") || child2.getRunStatus().toString().equalsIgnoreCase("FAIL"))
			{
				checkSettingMenu.log(LogStatus.FAIL, "checkSettingMenu is failed.");
			}
			else
			{
				checkSettingMenu.log(LogStatus.PASS, "checkSettingMenu is passed.");
			}

			MainMethod.extent.endTest(checkSettingMenu);

			softAssertion.assertAll();

			//LoginLib.logout();.
		}
	}

	/**
	 * 
	 * This method is check Escalation in Setting Page
	 * 
	 */
	@Test(description = "Check Escalation in Setting Page", priority = 22)
	public static void checkEscalation()
	{
		String strName, strPhoneNo, strValue;
		int intBefore, intAfter;
		ExtentTest checkEscalation = MainMethod.extent.startTest("Check Escalation in Setting Page").assignCategory("Regression");
		ExtentTest child1 = null;
		ExtentTest child2 = null;
		ExtentTest child3 = null;
		try
		{
			Log4J.logp.info("**** Started : checkEscalation ****");

			child1 = MainMethod.extent.startTest("Test Id 60 : Validate adding message escalation");
			child2 = MainMethod.extent.startTest("Test Id 61 : Validate editing message escalation");
			child3 = MainMethod.extent.startTest("Test Id 62 : Validate deleting message escalation");

			softAssertion = new SoftAssert();

			Properties properties = new Properties();
			properties.load(new FileInputStream("src/main/resources/Properties/data.properties"));
			strName = properties.getProperty("Name");
			strPhoneNo = properties.getProperty("Phone");

			homePageWebE.ico_Company.click();
			CommonLib.waitForObject(homePageWebE.lbl_LeftCompanyName, "visibility", 10);

			homePageWebE.lbl_CompanyMenu.get(0).click();
			CommonLib.waitForObject(homePageWebE.txt_CompanyDetails.get(0), "visibility", 10);

			CommonLib.scroll_Page(homePageWebE.scrollbar_Setting, 3);
			Thread.sleep(2000);

			intBefore = homePageWebE.lst_Escalation.size();

			homePageWebE.btn_Escalation.click();
			CommonLib.waitForObject(homePageWebE.txt_EscalationName, "visibility", 10);

			homePageWebE.txt_EscalationName.sendKeys(strName);
			Thread.sleep(2000);

			homePageWebE.txt_EscalationPhone.sendKeys(strPhoneNo);
			Thread.sleep(2000);

			homePageWebE.chk_Toggle.get(0).click();
			Thread.sleep(2000);

			homePageWebE.btn_EscalationConfirm.click();
			Thread.sleep(4000);

			intAfter = homePageWebE.lst_Escalation.size();

			System.out.println(intAfter);
			System.out.println(intBefore + 1);

			if (intAfter == intBefore + 1)
			{
				Log4J.logp.info("Escalation has been added in Eacalation table");
				softAssertion.assertTrue(true);
				child1.log(LogStatus.PASS, "Escalation has been added in Escalation table");
			}
			else
			{
				Log4J.logp.info("Escalation not added in Eacalation table");
				softAssertion.assertTrue(false);
				child1.log(LogStatus.FAIL, "Escalation not added in Escalation table");
			}

			// Edit Escalation

			CommonLib.scroll_Page(homePageWebE.scrollbar_Setting, 2);
			Thread.sleep(2000);

			strName = properties.getProperty("EditName");
			strPhoneNo = properties.getProperty("EditPhone");

			homePageWebE.btn_EscalationEdit.get(intAfter - 1).click();
			CommonLib.waitForObject(homePageWebE.txt_EscalationName, "visibility", 10);

			homePageWebE.txt_EscalationName.clear();
			homePageWebE.txt_EscalationName.sendKeys(strName);
			Thread.sleep(2000);

			homePageWebE.txt_EscalationPhone.clear();
			homePageWebE.txt_EscalationPhone.sendKeys(strPhoneNo);
			Thread.sleep(2000);

			homePageWebE.btn_EscalationConfirm.click();
			Thread.sleep(5000);

			WebElement ele = driver.findElement(By.xpath("(((//div[@class='Settings CompanyContainer']//table)[3]//tbody//tr)[" + intAfter + "]//td)[1]"));
			strValue = ele.getText().trim();

			System.out.println(strValue);
			System.out.println(strName);

			if (strValue.equals(strName))
			{
				Log4J.logp.info("Escalation has been edited");
				softAssertion.assertTrue(true);
				child2.log(LogStatus.PASS, "Escalation has been edited");
			}
			else
			{
				Log4J.logp.info("Escalation not edited");
				softAssertion.assertTrue(false);
				child2.log(LogStatus.FAIL, "Escalation not edited");
			}

			// Remove Escalation

			homePageWebE.btn_EscalationRemove.get(intAfter - 1).click();
			Thread.sleep(3000);

			homePageWebE.btn_EscalationRemoveConfirm.click();
			Thread.sleep(5000);

			intAfter = homePageWebE.lst_Escalation.size();

			if (intAfter == intBefore)
			{
				Log4J.logp.info("Escalation has been removed from Eacalation table");
				softAssertion.assertTrue(true);
				child3.log(LogStatus.PASS, "Escalation has been removed from Escalation table");
			}
			else
			{
				Log4J.logp.info("Escalation not removed from Eacalation table");
				softAssertion.assertTrue(false);
				child3.log(LogStatus.FAIL, "Escalation not removed from Escalation table");
			}

			Log4J.logp.info("**** Ended : checkEscalation ****");
		}
		catch (Exception e)
		{
			CommonLib.takeScreenshots();
			e.printStackTrace();
			softAssertion.assertTrue(false, "checkEscalation Failed");
		}
		finally
		{
			if (child1.getRunStatus().toString().equalsIgnoreCase("unknown"))
				child1.log(LogStatus.FAIL, "Failed for Unknown status.");
			if (child2.getRunStatus().toString().equalsIgnoreCase("unknown"))
				child2.log(LogStatus.FAIL, "Failed for Unknown status.");
			if (child3.getRunStatus().toString().equalsIgnoreCase("unknown"))
				child3.log(LogStatus.FAIL, "Failed for Unknown status.");

			checkEscalation.appendChild(child1);
			checkEscalation.appendChild(child2);
			checkEscalation.appendChild(child3);

			MainMethod.extent.endTest(child1);
			MainMethod.extent.endTest(child2);
			MainMethod.extent.endTest(child3);

			if (child1.getRunStatus().toString().equalsIgnoreCase("FAIL") || child2.getRunStatus().toString().equalsIgnoreCase("FAIL") || child3.getRunStatus().toString().equalsIgnoreCase("FAIL"))
			{
				checkEscalation.log(LogStatus.FAIL, "checkEscalation is failed.");
			}
			else
			{
				checkEscalation.log(LogStatus.PASS, "checkEscalation is passed.");
			}

			MainMethod.extent.endTest(checkEscalation);

			softAssertion.assertAll();

			//LoginLib.logout();.
		}
	}

	/**
	 * 
	 * This method is check Department in Setting Page
	 * 
	 */
	@Test(description = "Check Department", priority = 23)
	public static void checkDepartment()
	{
		String strDepartment;
		int intBeforeSize, intAfterSize, intSize;
		ExtentTest checkDepartment = MainMethod.extent.startTest("Check Department");
		ExtentTest child1 = null;
		ExtentTest child2 = null;
		try
		{
			Log4J.logp.info("**** Started : checkDepartment ****");

			child1 = MainMethod.extent.startTest("Test ID 63 : Validate setting default department to tag new message without a department");
			child2 = MainMethod.extent.startTest("Test ID 64 : Validate adding department name");

			softAssertion = new SoftAssert();

			homePageWebE.ico_Company.click();
			CommonLib.waitForObject(homePageWebE.lbl_LeftCompanyName, "visibility", 10);

			homePageWebE.lbl_CompanyMenu.get(1).click();
			CommonLib.waitForObject(homePageWebE.txt_Department, "visibility", 10);

			// Add Department

			strDepartment = "Test Department";

			intBeforeSize = homePageWebE.lst_Department.size();

			homePageWebE.txt_Department.clear();
			homePageWebE.txt_Department.sendKeys(strDepartment);
			Thread.sleep(2000);

			homePageWebE.btn_DepartmentSubmit.click();
			Thread.sleep(3000);

			intAfterSize = homePageWebE.lst_Department.size();

			System.out.println(intAfterSize);
			System.out.println(intBeforeSize);

			if (intAfterSize == intBeforeSize + 1)
			{
				Log4J.logp.info("Department has been added");
				softAssertion.assertTrue(true);
				child2.log(LogStatus.PASS, "Department has been added");
			}
			else
			{
				Log4J.logp.info("Department not added");
				softAssertion.assertTrue(false);
				child2.log(LogStatus.FAIL, "Department not added");
			}

			Thread.sleep(3000);

			// Set Default Department

			homePageWebE.lst_DepartmentDropdown.click();
			Thread.sleep(2000);

			WebElement ele = driver.findElement(By.xpath("//ul[contains(@id,'select-options')]//span[text()='" + strDepartment + "']"));
			ele.click();
			Thread.sleep(1000);

			System.out.println(homePageWebE.lbl_DefaultMessage.getText().trim());

			if (homePageWebE.lbl_DefaultMessage.getText().trim().equals(strDepartment + " set as default department."))
			{
				Log4J.logp.info("Department has been set as default");
				softAssertion.assertTrue(true);
				child1.log(LogStatus.PASS, "Department has been set as default");
			}
			else
			{
				Log4J.logp.info("Department not set as default");
				softAssertion.assertTrue(false);
				child1.log(LogStatus.FAIL, "Department not set as default");
			}

			homePageWebE.lst_DepartmentDropdown.click();
			Thread.sleep(2000);

			homePageWebE.lbl_DepartmentList.get(0).click();
			Thread.sleep(2000);

			ele = driver.findElement(By.xpath("//div[text()='" + strDepartment + "']//i"));
			ele.click();

			homePageWebE.btn_ConfirmDepartment.click();
			Thread.sleep(3000);

			Log4J.logp.info("**** Ended : checkDepartment ****");
		}
		catch (Exception e)
		{
			CommonLib.takeScreenshots();
			e.printStackTrace();
			softAssertion.assertTrue(false, "checkDepartment Failed");
		}
		finally
		{
			if (child1.getRunStatus().toString().equalsIgnoreCase("unknown"))
				child1.log(LogStatus.FAIL, "Failed for Unknown status.");
			if (child2.getRunStatus().toString().equalsIgnoreCase("unknown"))
				child2.log(LogStatus.FAIL, "Failed for Unknown status.");

			checkDepartment.appendChild(child1);
			checkDepartment.appendChild(child2);

			MainMethod.extent.endTest(child1);
			MainMethod.extent.endTest(child2);

			if (child1.getRunStatus().toString().equalsIgnoreCase("FAIL") || child2.getRunStatus().toString().equalsIgnoreCase("FAIL"))
			{
				checkDepartment.log(LogStatus.FAIL, "checkDepartment is failed.");
			}
			else
			{
				checkDepartment.log(LogStatus.PASS, "checkDepartment is passed.");
			}

			MainMethod.extent.endTest(checkDepartment);

			softAssertion.assertAll();

			//LoginLib.logout();.
		}
	}

	/**
	 * 
	 * This method is check Templates
	 * 
	 */
	@Test(description = "Check templates", priority = 24)
	public static void checkTemplates()
	{
		int intBefore, intAfter;
		try
		{
			Log4J.logp.info("**** Started : checkTemplates ****");

			softAssertion = new SoftAssert();

			homePageWebE.ico_Company.click();
			CommonLib.waitForObject(homePageWebE.lbl_LeftCompanyName, "visibility", 10);

			homePageWebE.lbl_CompanyMenu.get(2).click();
			CommonLib.waitForObject(homePageWebE.btn_NewTemplate, "visibility", 10);

			intBefore = homePageWebE.lbl_TemplateTitle.size();

			homePageWebE.btn_NewTemplate.click();
			CommonLib.waitForObject(homePageWebE.txt_TemplateTitle, "visibility", 10);

			homePageWebE.txt_TemplateTitle.clear();
			homePageWebE.txt_TemplateTitle.sendKeys("New Test Template");
			Thread.sleep(2000);

			homePageWebE.txt_TemplateMessage.clear();
			homePageWebE.txt_TemplateMessage.sendKeys("New Test Message");
			Thread.sleep(2000);

			homePageWebE.btn_EscalationConfirm.click();
			Thread.sleep(3000);

			intAfter = homePageWebE.lbl_TemplateTitle.size();

			if (intAfter == intBefore + 1)
			{
				Log4J.logp.info("Template has been added");
				softAssertion.assertTrue(true);
			}
			else
			{
				Log4J.logp.info("Template not added");
				softAssertion.assertTrue(false);
			}

			// Editing template

			CommonLib.scroll_Page(homePageWebE.scrollbar_Templates, 6);
			Thread.sleep(3000);

			homePageWebE.lbl_TemplateTitle.get(intAfter - 1).clear();
			homePageWebE.lbl_TemplateTitle.get(intAfter - 1).sendKeys("Edit New Template");
			Thread.sleep(2000);
			String strValue = homePageWebE.lbl_TemplateTitle.get(intAfter - 1).getAttribute("value");

			if (strValue.equals("Edit New Template"))
			{
				Log4J.logp.info("Template has been edited successfully");
				softAssertion.assertTrue(true);
			}
			else
			{
				Log4J.logp.info("Template not edited successfully");
				softAssertion.assertTrue(false);
			}

			// Remove template

			homePageWebE.ico_RemoveTemplate.get(intAfter - 1).click();
			Thread.sleep(2000);

			homePageWebE.btn_ConfirmDepartment.click();
			Thread.sleep(3000);

			intAfter = homePageWebE.lbl_TemplateTitle.size();

			if (intAfter == intBefore)
			{
				Log4J.logp.info("Template has been removed");
				softAssertion.assertTrue(true);
			}
			else
			{
				Log4J.logp.info("Template not removed");
				softAssertion.assertTrue(false);
			}

			Log4J.logp.info("**** Ended : checkTemplates ****");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void homePageAfterClass()
	{
		LoginLib.logout();
	}
}
