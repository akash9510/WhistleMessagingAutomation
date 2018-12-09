package testscripts;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import library.Log4J;
import webelements.LoginWebE;

public class Sample
{
	public static final String	USERNAME		= "chrish24";
	public static final String	AUTOMATE_KEY	= "jz2pYNumqrN7aQP3mMhh";
	public static final String	URL				= "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	static LoginWebE			loginWebE		= null;

	public static void main(String[] args) throws Exception
	{

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browser", "Safari");
		caps.setCapability("browser_version", "11.0");
		caps.setCapability("os", "OS X");
		caps.setCapability("os_version", "High Sierra");
		caps.setCapability("resolution", "1024x768");

		Log4J.loadLogger();

		WebDriver driver = new RemoteWebDriver(new java.net.URL(URL), caps);
		driver.get("https://staging.v2whistle.com");

		loginWebE = LoginWebE.getInstance(driver);

		loginWebE.txt_EmailAdd.clear();
		loginWebE.txt_EmailAdd.click();
		loginWebE.txt_EmailAdd.sendKeys("renee.pellum@gmail.com");
		Thread.sleep(5000);

		String bstatus = loginWebE.btn_Next.getTagName();

		System.out.println(bstatus);
		//System.out.println(CommonLib.checkElementPresent(loginWebE.btn_Next));
		//CommonLib.waitForObject(loginWebE.btn_Next, "visibility", 20);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", loginWebE.btn_Next);

		//loginWebE.btn_Next.click();
		//CommonLib.waitForObject(loginWebE.txt_Password, "visibility", 10);

		loginWebE.txt_Password.clear();
		loginWebE.txt_Password.sendKeys("reneewhistle1!");
		Thread.sleep(2000);

		//loginWebE.btn_Next.click();
		js.executeScript("arguments[0].click();", loginWebE.btn_Next);
		//CommonLib.waitForObject(loginWebE.lbl_UserName, "visibility", 20);
		driver.quit();
	}

}
