package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistration;
import pageObjects.Homepage;
import testBase.baseClass;


public class testAccountReg extends baseClass {

    @Test(groups={"Regression","Master"})
    public void verifyAccountRegistration() {
        try {
           logger.info("*******Starting test1_AccountRegistration **********");

           Homepage hp = new Homepage(driver);
           hp.myAccountClick();
           hp.registerClick();
           logger.info("*******clicked on myAccount**********");
           logger.info("*******clicked on register link**********");

           AccountRegistration ar = new AccountRegistration(driver);

           logger.info("*******providing customer details**********");
           ar.FillFirstName("Virath");
           ar.FillLastName("Mahatma");
           ar.FillEmail(randomEmails() + "@gmail.com");

           //to generate random email for this we have to create a method
           String passwrd = randomPassword();
           ar.FillTelephone("8789896865");
           ar.setPassword(passwrd);
           ar.confirmPassword(passwrd);
           ar.ClickAgree();
           ar.ClickContinue();
           String str = ar.getConfirmMessage();
           Assert.assertEquals(str, "Your Account Has Been Created!");
           logger.info("*******Test case finished**********");
        }catch (Exception e){
           e.printStackTrace();
        }

     }


}
