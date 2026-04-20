package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage extends BasePage{
    WebDriver driver;
    public Homepage(WebDriver driver) {
        super(driver);
    }

    //this will initialize the constructor;


    @FindBy(linkText = "My Account")
    WebElement myacc;

    @FindBy(linkText = "Register") WebElement register;

    @FindBy(linkText = "Login") WebElement loginbtn;



    public  void myAccountClick(){
        myacc.click();
    }
    public void registerClick(){
        register.click();
    }
    public void loginClick(){
        loginbtn.click();
    }
}