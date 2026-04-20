package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.baseClass;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportManager implements ITestListener{
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest tst;

    String repName;

    public void onStart(ITestContext testContext) {//execute only once before test starts

//        SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.mm.ss");
//        Date dt=new Date();
//        String currentDateTimeStamp=df.format(dt);

        String timeStamp=new SimpleDateFormat("yyyy.MM.dd.mm.ss").format(new Date());
        repName="Test-Report-"+timeStamp+".html";
        sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);
        //above we are givin report name manually but if we update something the same report got rewrite and we dont have history of previous report
        //To overcome this we use timestamp to generate a new report with new name
        sparkReporter.config().setDocumentTitle("OpenCart Report");
        sparkReporter.config().setReportName("OpenCart Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);// we are combining the UI with the common data
        extent.setSystemInfo("WebSite","OpenCart");
        extent.setSystemInfo("Module","Admin");
        extent.setSystemInfo("Sub Module","Customber");
        extent.setSystemInfo("User Name","Ladla Ashish");
        extent.setSystemInfo("Environment","QA");

        String os= testContext.getCurrentXmlTest().getParameter("OS");
        extent.setSystemInfo("OS",os);

        String brow= testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser",brow);

        List<String> includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
        if(!includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups",includedGroups.toString());
        }

    }

    public void onTestSuccess(ITestResult result) {
        //in the dashboardd we are passing only method name which is hard to identify the test if have multiple test methods we can use test id in frameworks
        tst=extent.createTest(result.getTestClass().getName());
        tst.assignCategory(result.getMethod().getGroups());
        tst.log(Status.PASS,result.getName()+"-> Test Passed :)");
    }

    public void onTestFailure(ITestResult result) {
        tst=extent.createTest(result.getTestClass().getName());
        tst.assignCategory(result.getMethod().getGroups());

        tst.log(Status.FAIL,result.getName()+"-> Test Failed :(");
        tst.log(Status.FAIL,"Test Failed cause is: "+result.getThrowable().getMessage());
        try{
            String imgPath=new baseClass().CaptureScreen(result.getName());
            tst.addScreenCaptureFromPath(imgPath);
        }catch (Exception e){
            e.printStackTrace();;
        }
    }

    public void onTestSkipped(ITestResult result) {
        tst=extent.createTest(result.getTestClass().getName());
        tst.assignCategory(result.getMethod().getGroups());
        tst.log(Status.SKIP,result.getName()+"-> Test Skipped :|");
        tst.log(Status.INFO,result.getThrowable().getMessage());
    }


    public void onFinish(ITestContext context) {//execute only once after all test finished
        extent.flush();
        String pathOfReport=System.getProperty("user.dir")+"\\reports\\"+repName;
        File extntp=new File(pathOfReport);
        try{
            Desktop.getDesktop().browse(extntp.toURI());//open the report automatically
        }catch(Exception e){
            e.printStackTrace();
        }

        /*
            try{
                URL(".\\reports\\"+repName);
                ImageHtmlEmail email=new ImageHtmlEmail();
                email.setDataSourceResolver(new DataSourceUrlResolver(url));
                email.setHostName("smtp.googlemail.com");
                email.setSmtpPort(465);
                email.setAuthenticator(new DefaultAuthenticator("xyz#gmail.com","password");
                email.setSSLOnConnect(true);
                email.setFrom("xyz@gmail.com");//sender
                email.setSubject("Test Result");
                email.setMsg("plesasas sajd adj aj ");
                email.addTo("abc@gmail.com);//receiver
                email.attach(url,"extent report","please check report...");
                email.send()
             }catch(Exception e){
             }

             //add dependencies of Apache common email

         */

        System.out.println("Test execution is Completed.................");
    }

}
