package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistration extends BasePage {
    WebDriver driver;
    public AccountRegistration(WebDriver driver){
        super(driver);
    }

    @FindBy(id="input-firstname") WebElement firstname;

    @FindBy(id="input-lastname") WebElement lastname;

    @FindBy(id="input-email") WebElement email;

    @FindBy(id="input-telephone") WebElement phone;


    @FindBy(id="input-password") WebElement password;
    @FindBy(id="input-confirm") WebElement passwordconfirm;

    @FindBy(xpath="//input[@name='agree' and @value='1']") WebElement agree_btn;

    @FindBy(xpath = "//input[@type='submit' and @value='Continue']") WebElement continue_btn;

    @FindBy(xpath="//div[@id='content']/h1") WebElement message;

    public void FillFirstName(String fname){
        firstname.sendKeys(fname);
    }

    public void FillLastName(String lname){
        lastname.sendKeys(lname);
    }

    public void FillEmail(String em){
        email.sendKeys(em);
    }

    public void FillTelephone(String tele){
        phone.sendKeys(tele);
    }

    public void setPassword(String pass){
        password.sendKeys(pass);
    }

    public void confirmPassword(String pass){
        passwordconfirm.sendKeys(pass);
    }

    public void ClickAgree(){
        agree_btn.click();
    }


    public void ClickContinue(){
        continue_btn.click();
    }

    public String getConfirmMessage(){
        try{
            return message.getText().trim();
        }catch(Exception e){
            return e.getMessage();
        }
    }
}
