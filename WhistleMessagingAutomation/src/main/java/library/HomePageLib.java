package library;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import framework.ExecutionSetUp;
import webelements.HomePageWebE;

public class HomePageLib {
	
	static WebDriver driver = null;
	static HomePageWebE homePageWebE = null;
	
	/**
	 * 
	 * This method is use for Enter Guest Details
	 * 
	 * */
	public static boolean addGuestDetails()
	{
		String strGuestName, strPhoneNo, strEmail;
		try
		{
			
			driver = ExecutionSetUp.getDriver();
			homePageWebE = HomePageWebE.getInstance(driver);
			
			homePageWebE.btn_AddGuest.click();
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
			
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

}
