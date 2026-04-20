package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.Homepage;
import pageObjects.loginPage;
import pageObjects.myAccountPage;
import testBase.baseClass;
import utilities.dataproviders;

public class testLoginD_Provider extends baseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = dataproviders.class, groups = "datadrivien")//data provider in different class
    public void verifyD_Provider(String email,String pass, String result){
        logger.info("*************Test Started**************");
        try {
            Homepage hp = new Homepage(driver);
            hp.myAccountClick();
            hp.loginClick();

            loginPage lp = new loginPage(driver);
            lp.setEmail(email);
            lp.setPassword(pass);
            lp.pressLogin();

            myAccountPage mp = new myAccountPage(driver);
            boolean bool = mp.MyAccountDisplayed();

            if (result.equalsIgnoreCase("valid")) {
                if (bool) {
                    mp.clickLogout();
                    Assert.assertTrue(true);
                } else {
                    Assert.fail();
                }
            } else if (result.equalsIgnoreCase("invalid")) {
                if (bool) {
                    mp.clickLogout();
                    Assert.fail();
                }
            } else {
                Assert.assertTrue(true);
            }
        }catch(Exception e){
            Assert.fail();
        }
        logger.info("*************Test Finish**************");

    }
}
