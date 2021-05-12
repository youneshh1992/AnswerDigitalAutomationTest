import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageElements extends PageObject {

    private final String USERNAME = "tomsmith";
    private final String PASSWORD = "SuperSecretPassword!";
    private final String WRONGUSERNAME = "tomstevens";
    private final String WRONGPASSWORD = "SuperPassword!";

    @FindBy (xpath = "//a[contains(text(),'Form Authentication')]")
    private WebElement formAuthentication_button;

    @FindBy (xpath = "//a[contains(text(),'Infinite Scroll')]")
    private WebElement infiniteScroll_button;

    @FindBy (xpath = "//a[contains(text(),'Key Presses')]")
    private WebElement keyPresses_button;

    @FindBy (id = "username")
    private WebElement username;

    @FindBy (id = "password")
    private WebElement password;

    @FindBy (xpath = "//i[contains(text(),'Login')]")
    private WebElement login_button;

    @FindBy (xpath = "//i[contains(text(),'Logout')]")
    private WebElement logout_button;

    @FindBy (xpath = "//div[@id='flash']")
    private WebElement loginMessage;

    @FindBy (xpath = "//h3[contains(text(),'Infinite Scroll')]")
    private WebElement infiniteScrollText;

    @FindBy (id = "target")
    private WebElement keyPressField;

    @FindBy (xpath = "//p[@id='result']")
    private WebElement keyPressText;


    public PageElements(WebDriver driver){
        super(driver);
    }

    public void clickFormAuthenticationButton(){
        this.formAuthentication_button.click();
    }

    public void clickInfiniteScrollButton(){
        this.infiniteScroll_button.click();
    }

    public void clickKeyPressesButton(){
        this.keyPresses_button.click();
    }

    public void enterUsername(){
        this.username.sendKeys(USERNAME);
    }

    public void enterPassword(){
        this.password.sendKeys(PASSWORD);
    }

    public void enterWrongUsername(){
        this.username.sendKeys(WRONGUSERNAME);
    }

    public void enterWrongPassword(){ this.password.sendKeys(WRONGPASSWORD); }

    public void clickLoginButton(){
        this.login_button.click();
    }

    public void enterAltButton() {this.keyPressField.sendKeys(Keys.ALT);}

    public void enterBackSpaceButton() {this.keyPressField.sendKeys(Keys.BACK_SPACE);}

    public void enterTabButton() {this.keyPressField.sendKeys(Keys.TAB);}

    public void enterSpaceButton() {this.keyPressField.sendKeys(Keys.SPACE);}

}
