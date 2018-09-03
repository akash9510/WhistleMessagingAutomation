package webelements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginWebE
{

	/** to enter value in Email address */
	@FindBy(id = "login-email-form_input")
	public WebElement	txt_EmailAdd;

	/** to enter value in Password */
	@FindBy(id = "login-password-form_input")
	public WebElement	txt_Password;

	/** to click on Next button */
	@FindBy(xpath = "//button[@type='submit']")
	//@FindBy(css = ".right-align .btn.waves-effect.waves-light")
	public WebElement	btn_Next;

	/** to check username after login in the application */
	@FindBy(css = ".user-name-dropdown")
	public WebElement	lbl_UserName;

	/** to click on Logout link option */
	@FindBy(xpath = "//a[text()='Logout']")
	public WebElement	lnk_Logout;

	/** to click on Forget Password button */
	@FindBy(xpath = "//button[contains(text(),'Forgot Password')]")
	public WebElement	btn_Forget_Password;

	/** to get the Forget Password message */
	@FindBy(css = ".animated.slideInRight.center-align")
	public WebElement	lbl_SuccessMessage;

	static LoginWebE	Instance	= null;

	public static LoginWebE getInstance(WebDriver driver)
	{
		if (Instance == null)
		{
			Instance = PageFactory.initElements(driver, LoginWebE.class);
		}
		return Instance;
	}

}
