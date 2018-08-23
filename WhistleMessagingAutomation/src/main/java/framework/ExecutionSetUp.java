package framework;

import java.awt.Toolkit;
import java.io.FileReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.browserstack.local.Local;

import library.CommonLib;
import library.Log4J;

public class ExecutionSetUp
{

	public static WebDriver		driver			= null;
	private static Local		l;
	public static String		strEnv			= null;
	public static final String	USERNAME		= "aakash9";
	public static final String	AUTOMATE_KEY	= "pqwx7T6AnWinRMJ5jicv";
	public static final String	URL				= "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void launchBrowser(String config_file, String environment)
	{
		Log4J.loadLogger();
		try
		{

			String FilePath = "/src/main/resources/Files/";
			String projectDir = System.getProperty("user.dir");
			String directory = projectDir + FilePath + config_file;

			JSONParser parser = new JSONParser();
			JSONObject config = (JSONObject) parser.parse(new FileReader(directory));
			JSONObject envs = (JSONObject) config.get("environments");

			strEnv = environment;

			DesiredCapabilities capabilities = new DesiredCapabilities();

			Map<String, String> envCapabilities = (Map<String, String>) envs.get(environment);
			Iterator it = envCapabilities.entrySet().iterator();
			while (it.hasNext())
			{
				Map.Entry pair = (Map.Entry) it.next();
				capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
			}

			Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
			it = commonCapabilities.entrySet().iterator();
			while (it.hasNext())
			{
				Map.Entry pair = (Map.Entry) it.next();
				if (capabilities.getCapability(pair.getKey().toString()) == null)
				{
					capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
				}
			}

			String username = System.getenv("BROWSERSTACK_USERNAME");
			if (username == null)
			{
				username = (String) config.get("user");
			}

			String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
			if (accessKey == null)
			{
				accessKey = (String) config.get("key");
			}

			String app = System.getenv("BROWSERSTACK_APP_ID");
			if (app != null && !app.isEmpty())
			{
				capabilities.setCapability("app", app);
			}

			if (capabilities.getCapability("browserstack.local") != null && capabilities.getCapability("browserstack.local") == "true")
			{
				l = new Local();
				Map<String, String> options = new HashMap<String, String>();
				options.put("key", accessKey);
				l.start(options);
			}

			if (environment.contains("chrome"))
			{
				ChromeOptions options = new ChromeOptions();
				Map<String, Object> prefs = new HashMap<String, Object>();
				prefs.put("profile.default_content_setting_values.notifications", 1);
				//1-Allow, 2-Block, 0-default
				options.setExperimentalOption("prefs", prefs);
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			}
			else if (environment.contains("firefox"))
			{
				FirefoxProfile profile = new FirefoxProfile();
				profile.setPreference("permissions.default.desktop-notification", 1);
				//DesiredCapabilities capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability(FirefoxDriver.PROFILE, profile);
				//driver = new FirefoxDriver(capabilities);
			}

			driver = new RemoteWebDriver(new URL("http://" + username + ":" + accessKey + "@" + config.get("server") + "/wd/hub"), capabilities);
		}
		catch (Exception e)
		{
			//CommonLib.takeScreenshots();
			e.printStackTrace();
		}
	}

	public static boolean maximizeBrowser()
	{
		try
		{
			java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Point position = new Point(0, 0);
			driver.manage().window().setPosition(position);
			Dimension maximizedScreenSize = new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
			driver.manage().window().setSize(maximizedScreenSize);
			return true;
		}
		catch (Exception e)
		{
			CommonLib.takeScreenshots();
			e.printStackTrace();
			return false;
		}
	}

	public static WebDriver getDriver()
	{
		return driver;
	}

}