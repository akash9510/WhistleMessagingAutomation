package webelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginWebE {
	
	/** to enter value in Email address*/
	@FindBy(id = "login-email-form_input")
	public WebElement txt_EmailAdd;
	
	/** to enter value in Password */
	@FindBy(id = "login-password-form_input")
	public WebElement txt_Password;
	
	/** to click on Next button */
	@FindBy(xpath = "//button[@type='submit']")
	public WebElement btn_Next;
	
	/** to check username after login in the application */
	@FindBy(css = ".user-name-dropdown")
	public WebElement lbl_UserName;
	
	static loginWebE Instance = null;
	
	public static loginWebE getInstance(WebDriver driver)
	{
		if(Instance == null)
		{
			Instance = PageFactory.initElements(driver, loginWebE.class);
		}
		return Instance;
	}

}
