package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class myAccountPage extends BasePage{

    public myAccountPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath="//div[@id='content']/h2")
    WebElement accountHeading;

    @FindBy(linkText = "Logout") WebElement logoutbtn;

    public boolean MyAccountDisplayed(){
        try {
            return accountHeading.isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public void clickLogout(){
        logoutbtn.click();
    }
}
