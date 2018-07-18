package library;

import java.awt.Robot;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.ExecutionSetUp;
import webelements.HomePageWebE;

public class CommonLib {
	
	static WebDriver driver = null;
	static WebDriverWait wait;
	static HomePageWebE homePageWebE = null;
	
	public static boolean waitForObject(WebElement webe, String strProperty, long d)
	{
		try
		{
			Log4J.logp.info("---------------- Started - waitForObject ----------------");
			driver = ExecutionSetUp.getDriver();

			wait = new WebDriverWait(driver, d);

			if (strProperty.equalsIgnoreCase("visibility"))
			{
				wait.until(ExpectedConditions.visibilityOf(webe));
			}
			else if (strProperty.equalsIgnoreCase("clickable"))
			{
				wait.until(ExpectedConditions.elementToBeClickable(webe));
			}
			else if (strProperty.equalsIgnoreCase("selectable"))
			{

				wait.until(ExpectedConditions.elementToBeSelected(webe));
			}
			else
			{
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(strProperty));
			}

			wait = null;
			Log4J.logp.info("---------------- Ended - waitForObject ----------------");
			return true;

		}
		catch (Exception e)
		{
			Log4J.logp.error("Errors in waitForObject  ");
			return false;
		}
	}
	
	
	/**
	 * This method is to check for element is present or not
	 * 
	 * @param webe
	 * @return
	 */
	public static boolean checkElementPresent(WebElement webe)
	{
		try
		{
			Log4J.logp.info("---------------- Started - checkElementPresent  ----------------");

			driver = ExecutionSetUp.getDriver();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			webe.getTagName();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Log4J.logp.info("---------------- Ending - checkElementPresent True ----------------");

			return true;
		}
		catch (Exception e)
		{
			Log4J.logp.info("---------------- Ending - checkElementPresent False ----------------");
			return false;
		}
	}
	
	/** 
	 * 
	 * This method is use for Select Date from Calendar
	 * 
	 * */
	public static boolean selectDate(String strSampleDate,String strValue)
	{
		int intMonth;
		String strDate, strYear, strMonth;
		try
		{
			driver = ExecutionSetUp.getDriver();
			homePageWebE = HomePageWebE.getInstance(driver);
			
			homePageWebE.lnk_Year.click();
			Thread.sleep(2000);
			
			intMonth = Integer.parseInt(strSampleDate.split("\\/")[0]);
			strDate = strSampleDate.split("\\/")[1];
			strYear = strSampleDate.split("\\/")[2];
			
			WebElement webYear = driver.findElement(By.xpath("//span[text()='" + strYear + "']"));
			webYear.click();
			
			strMonth = Month.of(intMonth).name();
			strMonth = strMonth.substring(0,1).toUpperCase() + strMonth.substring(1).toLowerCase();
			
			for(int i = 1; i<=12;i++)
			{
				System.out.println(homePageWebE.lbl_Month.getText());
				if(homePageWebE.lbl_Month.getText().contains(strMonth))
				{
					driver.findElement(By.xpath("//span[text()='" + strDate + "']")).click();
					break;
				}
				else
				{
					Date date = new Date();
					LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					int month = localDate.getMonthValue();
					if(month < intMonth)
					{
						if(strValue.equals("Depart"))
						{
							homePageWebE.lnk_Right_Pointer_Depart.click();
						}
						else
						{
							homePageWebE.lnk_Right_Pointer.click();
						}
						Thread.sleep(2000);
						System.out.println(homePageWebE.lbl_Month.getText());
						if(homePageWebE.lbl_Month.getText().contains(strMonth))
						{
							driver.findElement(By.xpath("//span[text()='" + strDate + "']")).click();
							break;
						}
					}
					else
					{
						if(strValue.equals("Depart"))
						{
							homePageWebE.lnk_Left_Pointer_Depart.click();
						}
						else
						{
							homePageWebE.lnk_Left_Pointer.click();
						}
						Thread.sleep(2000);
						System.out.println(homePageWebE.lbl_Month.getText());
						if(homePageWebE.lbl_Month.getText().contains(strMonth))
						{
							driver.findElement(By.xpath("//span[text()='" + strDate + "']")).click();
							break;
						}
					}
				}
			}
			
			Thread.sleep(4000);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * 
	 * This method is use for get background Color of Element
	 * 
	 * */
	
	public static String getBackgroundColor(WebElement webe) throws InterruptedException
	{
		String[] arrstrColournum = null;
		String strColour = null;

		try
		{
			driver = ExecutionSetUp.getDriver();

			arrstrColournum = webe.getCssValue("background-color").toString().replace("rgba(", "").replace(")", "").split(",");

			System.out.println(Integer.parseInt(arrstrColournum[0].trim()));
			System.out.println(Integer.parseInt(arrstrColournum[1].trim()));
			System.out.println(Integer.parseInt(arrstrColournum[2].trim().split(" ")[0]));

			strColour = String.format("#%02x%02x%02x", Integer.parseInt(arrstrColournum[0].trim()), Integer.parseInt(arrstrColournum[1].trim()), Integer.parseInt(arrstrColournum[2].trim().split(" ")[0]));

			/*numbers = webe.getCssValue("background-color").toString().replace("rgb(", "").replace(")", "").split(",");
			strhex = String.format("#%02x%02x%02x", Integer.parseInt(numbers[0].trim()), Integer.parseInt(numbers[1].trim()), Integer.parseInt(numbers[2].trim().split(" ")[0]));*/
			return strColour;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * This method is use for Upload file
	 * 
	 * */
	public static boolean uploadFile()
	{
		try
		{
					File file = new File("/Users/admin/Desktop/Campaigns-Sample-XLSX (1).xlsx");
					 
					StringSelection stringSelection= new StringSelection(file.getAbsolutePath());
					 
					//Copy to clipboard Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
					 
					Robot robot = new Robot();
					 
					// Cmd + Tab is needed since it launches a Java app and the browser looses focus
					 
					robot.keyPress(KeyEvent.VK_META);
					 
					robot.keyPress(KeyEvent.VK_TAB);
					 
					robot.keyRelease(KeyEvent.VK_META);
					 
					robot.keyRelease(KeyEvent.VK_TAB);
					 
					robot.delay(500);
					 
					//Open Goto window
					 
					robot.keyPress(KeyEvent.VK_META);
					 
					robot.keyPress(KeyEvent.VK_SHIFT);
					 
					robot.keyPress(KeyEvent.VK_G);
					 
					robot.keyRelease(KeyEvent.VK_META);
					 
					robot.keyRelease(KeyEvent.VK_SHIFT);
					 
					robot.keyRelease(KeyEvent.VK_G);
					 
					//Paste the clipboard value
					 
					robot.keyPress(KeyEvent.VK_META);
					 
					robot.keyPress(KeyEvent.VK_V);
					 
					robot.keyRelease(KeyEvent.VK_META);
					 
					robot.keyRelease(KeyEvent.VK_V);
					 
					//Press Enter key to close the Goto window and Upload window
					 
					robot.keyPress(KeyEvent.VK_ENTER);
					 
					robot.keyRelease(KeyEvent.VK_ENTER);
					 
					robot.delay(500);
					 
					robot.keyPress(KeyEvent.VK_ENTER);
					 
					robot.keyRelease(KeyEvent.VK_ENTER);
			
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

}
