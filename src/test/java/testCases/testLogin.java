package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.loginPage;
import pageObjects.myAccountPage;
import testBase.baseClass;

public class testLogin extends baseClass {
    @Test(groups = {"Sanity","Master"})
    public void setLoginCredentials(){
        try{
            logger.info("*******Starting LoginTest **********");
            Homepage hp=new Homepage(driver);
            hp.myAccountClick();
            hp.loginClick();

            loginPage lp=new loginPage(driver);

            lp.setEmail(prop.getProperty("email"));
            lp.setPassword(prop.getProperty("password"));
            lp.pressLogin();

            myAccountPage mp=new myAccountPage(driver);
            logger.info("*********finish login test************");
            Assert.assertTrue(mp.MyAccountDisplayed());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
