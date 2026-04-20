package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class loginPage extends BasePage{

    public loginPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id="input-email") WebElement email;
    @FindBy(id="input-password") WebElement password;
    @FindBy(xpath="//input[@type='submit']") WebElement loginbtn;

    public void setEmail(String mail){
        email.sendKeys(mail);
    }

    public void setPassword(String pass){
        password.sendKeys(pass);
    }

    public void pressLogin(){
        loginbtn.click();
    }


}
