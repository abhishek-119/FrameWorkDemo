package testBase;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.apache.logging.log4j.LogManager;  //Log4j
import org.apache.logging.log4j.Logger;  //Log4j
import org.testng.annotations.Parameters;

public class baseClass {
    public static WebDriver driver;
    public Logger logger; //log4rj
    public Properties prop;


    public String randomEmails(){
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomPassword(){
        return RandomStringUtils.randomAlphabetic(3)+RandomStringUtils.randomNumeric(3);
    }

    @BeforeClass(groups = {"Sanity","Regression","Master,","datadrivien"})
    @Parameters({"OS","browser"})
    public void setUp(String OP, String bro) throws IOException {
        //load
        FileInputStream fis=new FileInputStream("C:\\Users\\2478501\\IdeaProjects\\FrameWorkDemo\\src\\test\\resources\\config.properties");
        prop=new Properties();
        prop.load(fis);
        logger=  LogManager.getLogger(this.getClass());//code for log4j2


        //for selenium grid
        if(prop.getProperty("execution_env").equalsIgnoreCase("remote")){
            DesiredCapabilities capabilities=new DesiredCapabilities();

            //operatingSystem
            if(OP.equalsIgnoreCase("windows")){
                capabilities.setPlatform(Platform.WIN11);
            }else if(OP.equalsIgnoreCase("mac")){
                capabilities.setPlatform(Platform.MAC);
            }else{
                System.out.println("Invalid Operating System");
                return;
            }

            //Browser
            switch(bro.toLowerCase()){
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;
                case "edge":
                    capabilities.setBrowserName("MicrosoftEdge");
                    break;
                default:
                    System.out.println("Invalid Browser");
                    return;

            }
            driver=new RemoteWebDriver(new URL("http://10.187.145.9:4444"),capabilities);
        }else if(prop.getProperty("execution_env").equalsIgnoreCase("local")) {
            switch (bro.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                default:
                    System.out.println("Invalid Browser Name");
                    return;
            }
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(prop.getProperty("url"));
        driver.manage().window().maximize();
    }

    @AfterClass(groups = {"Sanity","Regression","Master","datadrivien"})
    public void tearDown(){
        if (driver != null) {
            try { driver.quit(); } catch (Exception e) { /* ignore */ }
        }
    }


    public String CaptureScreen(String tname){
        String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot=(TakesScreenshot) driver;
        File source=takesScreenshot.getScreenshotAs(OutputType.FILE);
        String targetname= STR."C:\\Users\\2478501\\IdeaProjects\\FrameWorkDemo\\screenshots\\\{tname}_\{timeStamp}.png";
        File target=new File(targetname);
        source.renameTo(target);
        return targetname;
    }


}
