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
	@FindBy(xpath = "(//div[@class='SendMessageButton'])[2]")
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
	@FindBy(xpath= "(//span[contains(text(),'Add Guest')])[2]")
	public WebElement btn_AddGuest;
	
	/** to click on Add Guest button of Campaign */
	@FindBy(xpath = "//span[contains(text(),'Add Guest')]")
	public WebElement btn_AddGuestCompaign;
	
	/** to check Compaign message sent or not */
	@FindBy(xpath = "//div[@mode='determinate']")
	public WebElement compaign_Message;
	
	/** to enter the values in tables */
	@FindBy(xpath = "//table//div[@class='col input-field s12']//input")
	public List<WebElement> txt_GuestValues;
	
	/** to enter message in Compaign messgae */
	@FindBy(id = "campaign_msg_box")
	public WebElement txt_CompaignMessage;
	
	/** to click omn Start Survey button */
	@FindBy(xpath = "//div[contains(text(),'Start Survey')]")
	public WebElement btn_StartSurvey;
	
	/** to click on Use Survey Template button */
	@FindBy(css= ".chip.selected.add")
	public WebElement btn_UseSurveyTemplate;
	
	/** to select first template from Template window */
	@FindBy(xpath = "(//div[@id='survey-template-modal']//span)[1]")
	public WebElement lnk_FIrstTemplate;
	
	/** to click on Next button */
	@FindBy(xpath = "//button[text()='Next']")
	public WebElement btn_NextSurvey;
	
	/** to click on Send Survey button */
	@FindBy(xpath = "//button[text()='Send Survey']")
	public WebElement btn_SendSurvey;
	
	/** to check Survey Sent label */
	@FindBy(css =".StartSurveyView__finished.animated.slideInRight")
	public WebElement lbl_SurveySent;
	
	/** to add Survey Question */
	@FindBy(id ="new-survey-template-question")
	public WebElement txt_Survey_Question;
	
	/** to click on Answer Type dropdown */
	@FindBy(id = "new-survey-template-answer-type")
	public WebElement lst_AnswerType;
	
	/** to Select Range option */
	@FindBy(xpath = "//div[text()='Range']")
	public WebElement lst_Range;
	
	/** to add value in Start Range */
	@FindBy(id ="new-survey-template-range-start")
	public WebElement txt_StartRange;
	
	/** to add value in End Range */
	@FindBy(id = "new-survey-template-range-end")
	public WebElement txt_EndRange;
	
	/** to click on Add Answer option  button */
	@FindBy(xpath = "//div[contains(text(),'Add Answer Option')]")
	public WebElement btn_AddAnswerQuestion;
	
	/** to enter value in new Choice */
	@FindBy(id = "new-survey-template-choice-2")
	public WebElement txt_Choice;
	
	/** to get the list of Open Conversation */
	@FindBy(css = ".ConversationList__convo")
	public List<WebElement> conversation_list;
	
	/** to scroll the conversation */
	@FindBy(css = ".GuestChatSidebar__department-container.has-segment")
	public WebElement scrollbar_Conversation;
	
	/** to click on Close chat option */
	@FindBy(css= ".ConversationInfoHeader__action")
	public WebElement ico_CloseChat;
	
	/** to get the list of conversartion name */
	@FindBy(css = ".PhoneNumber")
	public List<WebElement> lbl_Names;
	
	/** to click on Archived tab */
	@FindBy(xpath ="//div[text()='Archive']")
	public WebElement tab_Archived;
	
	/** to scroll the Archive Container */
	@FindBy(xpath = "//div[@class='ArchiveContainer z-depth-2']")
	public WebElement scrollBar_Archive;
	
	/** to click on Block/Unblock button */
	@FindBy(css = ".btn.waves-effect.red")
	public WebElement btn_BlockUnblock;
	
	/** to click on Confirm button */
	@FindBy(css = "#blockModal .confirm.delete")
	public WebElement btn_Confirm;
	
	/** to click on Blocked tab */
	@FindBy(xpath = "//div[text()='Blocked']")
	public WebElement tab_Blocked;
	
	/** to click on First row from Blocked Container */
	@FindBy(xpath = "(//table[contains(@class,'Table')]//tr[@class='ArchiveList__row']//td)[1]")
	public WebElement first_Blocked;
	
	/** to click on Live chat icon */
	@FindBy(id = "live-chat-tab")
	public WebElement ico_Live_Chat;
	
	/** to scroll Live Chat Conversartions */
	@FindBy(css = ".LiveChatLeftSidebarContainer__list.has-segment")
	public WebElement scrollbar_LiveChat;
	
	/** to get the names of Live Chat */
	@FindBy(css = ".info__name")
	public List<WebElement> lbl_LiveChatNames;
	
	/** to click on Team Chat icon */
	@FindBy(id = "team-chat-tab")
	public WebElement ico_TeamChat;
	
	/** to click on Create channel button */
	@FindBy(css = ".PrimaryButtonContainer__button.btn")
	public WebElement btn_CreateChannel;
	
	/** to enter Channel name */
	@FindBy(xpath = "//div[@id='editmodal_new']//input")
	public WebElement txt_ChannelName;
	
	/** to select Member name from list */
	@FindBy(css = ".List__item-content")
	public List<WebElement> lbl_TeamMembers;
	
	/** to click on Confirm button to Create Channel */
	@FindBy(css = "#editmodal_new .confirm.create")
	public WebElement btn_ConfirmChannel;
	
	/** to get the list of team members after create it */
	@FindBy(xpath = "//li[@class='TeamChatMemberPanel__list-item']//a[contains(@href,'direct-chat')]")
	public List<WebElement> lbl_ConfirmMembers;
	
	/** to click on Plus icon to add new members in channel */
	@FindBy(css = ".TeamChatMemberPanel__add.btn-flat.waves-effect")
	public WebElement ico_AddMembers;
	
	/** to click on Remove icon of Channel */
	@FindBy(css = ".TeamChatMemberPanel__remove.btn-flat.waves-effect")
	public WebElement ico_RemoveChannel;
	
	/** to add new team members */
	@FindBy(xpath = "//h4[text()='Add Users']/..//div[@class='List__item-content']")
	public List<WebElement> lbl_NewMembers;
	
	/** to click on Confirm button of New Team */
	@FindBy(css = "#add_users .confirm.create")
	public WebElement btn_NewConfirm;
	
	/** to click on Remove icon of Team members */
	@FindBy(css = ".material-icons.remove_participant")
	public List<WebElement> ico_Delete;
	
	/** to click on Confirm button of Remove Team mebers */
	@FindBy(css = "#removeParticipant .confirm.delete")
	public WebElement btn_ConfirmRemove;
	
	/** to get the channel name */
	@FindBy(xpath = "//ul[@class='TeamChatSidebar__list']//a[contains(@href,'team-chat')]")
	public List<WebElement> lbl_ChannelName;
	
	/** to click on Confirm button of Channel */
	@FindBy(css ="#deleteChannel .confirm.delete")
	public WebElement btn_ConfirmChannelRemove;
	
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
