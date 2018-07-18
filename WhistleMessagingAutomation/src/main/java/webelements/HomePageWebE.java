package webelements;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageWebE {
	
	/** Enter value in Gust Name textbox */
	@FindBy(id = "new-convo-guest-name")
	public WebElement txt_GuestName;
	
	/** to enter value in  Phone number */
	@FindBy(id = "phone_1")
	public WebElement txt_PhoneNo;
	
	/** to click on Arrival Date textbox */
	@FindBy(id = "new-convo-arrival")
	public WebElement txt_ArrivalDate;
	
	/** to click on Departure Date textbox */
	@FindBy(id = "new-convo-departure")
	public WebElement txt_DepartureDate;
	
	/** to click on year label in date calendar */
	@FindBy(xpath = "(//div[@direction='up']//div)[1]")
	public WebElement lnk_Year;
	
	/** to enter value in Notes */
	@FindBy(id = "new-convo-notes")
	public WebElement txt_Notes;
	
	/** to enter value in Email ID */
	@FindBy(id = "new-convo-email")
	public WebElement txt_Email;
	
	/** to enter value in message textbox */
	@FindBy(id = "main_start_convo_msg_box")
	public WebElement txt_Message;
	
	/** to click on Send button */
	@FindBy(xpath = "(//div[@class='SendMessageButton'])[1]")
	public WebElement btn_Send;
	
	/** to get the value of Month */
	@FindBy(xpath = "//div[@style='height: inherit; padding-top: 12px;']")
	public WebElement lbl_Month;
	
	/** to click on Left pointer of Calendar */
	@FindBy(xpath = "/html/body/div[13]/div/div[1]/div/div/div/div/div[2]/div[1]/div[1]/button[2]")
	public WebElement lnk_Left_Pointer;
	
	/** to click on Right pointer of Calendar */
	@FindBy(xpath = "/html/body/div[13]/div/div[1]/div/div/div/div/div[2]/div[1]/div[1]/button[2]")
	public WebElement lnk_Right_Pointer;
	
	/** to click on Left pointer of Calendar */
	@FindBy(xpath = "/html/body/div[14]/div/div[1]/div/div/div/div/div[2]/div[1]/div[1]/button[2]")
	public WebElement lnk_Left_Pointer_Depart;
	
	/** to click on Right pointer of Calendar */
	@FindBy(xpath = "/html/body/div[14]/div/div[1]/div/div/div/div/div[2]/div[1]/div[1]/button[2]")
	public WebElement lnk_Right_Pointer_Depart;
	
	/** to check Middle Panel after Click on Send button */
	@FindBy(css = ".ChatMessage__message--bubble.currentUser > span")
	public List<WebElement> lbl_Messages;
	
	/** to click on Whistle Hotel dropdown  */
	@FindBy(css =".brand-logo__company-name")
	public WebElement lst_WhistleDropdown;
	
	/** to check Whistle Dropdown open or not */
	@FindBy(css = ".dropdown-content.active")
	public WebElement lst_ActiveDropdown;
	
	/** to click on Sound toggle */
	@FindBy(id = "sound")
	public WebElement ico_SoundToggle;
	
	/** to click on NOtification toggle */
	@FindBy(id = "notifications")
	public WebElement ico_NotificationToggel;
	
	/** to get the background value of Sound Toggle */
	@FindBy(xpath = "((//div[@class='NotificationToggles__toggle'])[1]//div[contains(@style,'background-color')])[1]")
	public WebElement lbl_ToggleBackground;
	
	/** to get the background value of Notification Toggle */
	@FindBy(xpath ="((//div[@class='NotificationToggles__toggle'])[2]//div[contains(@style,'background-color')])[1]")
	public WebElement lbl_NotificationBackground;
	
	/** to click on Guest Chat left Side */
	@FindBy(id = "guest-chat-tab")
	public WebElement ico_GuestChat;
	
	/** to click on Start Conversation button */
	@FindBy(css = ".PrimaryButtonContainer__button.btn")
	public WebElement btn_StartConversation;
	
	/** to click on Start Compaign option */
	@FindBy(xpath = "//div[contains(text(),'Start Campaign')]")
	public WebElement btn_StartCompaign;
	
	/** to enter description */
	@FindBy(id = "textarea1")
	public WebElement txt_Description;
	
	/** to click on File Upload button */
	@FindBy(xpath ="//input[contains(@id,'FileUploader')]")
	public WebElement btn_FileUpload;
	
	/** to click on Add Guest button */
	@FindBy(xpath= "//span[contains(text(),'Add Guest')]")
	public WebElement btn_AddGuest;
	
	/** to enter the values in tables */
	@FindBy(xpath = "//table//div[@class='col input-field s12']//input")
	public List<WebElement> txt_GuestValues;
	
	/** to enter message in Compaign messgae */
	@FindBy(id = "campaign_msg_box")
	public WebElement txt_CompaignMessage;
	
	static HomePageWebE Instance = null;
	
	public static HomePageWebE getInstance(WebDriver driver)
	{
		if(Instance == null)
		{
			Instance = PageFactory.initElements(driver, HomePageWebE.class);
		}
		 
		return Instance;
	}

}
