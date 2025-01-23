package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.BaseClass;

public class ExtentReportManager implements ITestListener{
      public  ExtentSparkReporter sparkReporter;
      public  ExtentReports extent;
      public  ExtentTest test;
      
      String repName;
      
      public void onStart(ITestContext testcontext)
      {
    	  /*SimpleDateFormat df= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    	  Date dt= new Date();
    	  String currentdatetimestamp = df.format(dt);*/
    	 //simpledateformat is a class in java test package
    	  
    	String timestamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
    	
    	repName="Test-Report-" + timestamp + ".html";//good to have timestamp
    	sparkReporter= new ExtentSparkReporter(".\\reports\\" + repName);//specify location of the report
    	
    	sparkReporter.config().setDocumentTitle("opencart Automation Report");//Title of the report
    	sparkReporter.config().setReportName("opencart Functional Testing");//name of the report
    	sparkReporter.config().setTheme(Theme.DARK);
    	
    	extent= new ExtentReports();
    	extent.attachReporter(sparkReporter);
    	extent.setSystemInfo("Application", "opencart");
    	extent.setSystemInfo("Module", "Admin");
    	extent.setSystemInfo("Sub Module", "Customers");
    	extent.setSystemInfo("User Name", System.getProperty("user.name"));//name of the tester
    	extent.setSystemInfo("Environment", "QA");
    	
    	//values from xml file, os and browser
    	
    	String os= testcontext.getCurrentXmlTest().getParameter("os");
    	extent.setSystemInfo("Operating System", os);
    	
    	String browser=testcontext.getCurrentXmlTest().getParameter("browser");
    	extent.setSystemInfo("Browser", browser);
    	
    	//will display the included groups
    	List<String> includedGroups = testcontext.getCurrentXmlTest().getIncludedGroups();
    	if (!includedGroups.isEmpty())//! is not operator,if includedgroups is not empty 
    	{
    	
    		extent.setSystemInfo("Groups", includedGroups.toString());//to string we are printing the groups
    		
    	}
    	
    	 
    	  
     }
      
	public void onTestSuccess(ITestResult result)
	{
		
		test= extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());//to display groups in report
		
		test.log(Status.PASS, result.getName()+ "got successfullt executed");
		
	}
	
	
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName()+ "got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try
		{
		   String imgpath= new BaseClass().captureScreen(result.getName());//created object for baseclass
		   test.addScreenCaptureFromPath(imgpath);
			
		}
		
		catch (IOException e1)
		{
			
			e1.printStackTrace();//will display the warning message,predefined method
		}
		
		
	}
	
	public void onTestSkipped(ITestResult result)
	
	{
		test= extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.SKIP, result.getName()+"got skipped");
		test.log(Status.INFO,result.getThrowable().getMessage());
		
	
	}
	
	public void onFinish(ITestContext testcontext)
	{
		
		extent.flush();//consolidate all the info and generate
		
		//extra code for automatic generation of reports
		//automatic generation of reports
		String pathOfExtentReport= System.getProperty("user.dir")+"\\reports\\" +repName;
		File extentReport=new File(pathOfExtentReport);//create file object
		
		//we use try catch block if the code in try block doesn't work
		try 
		{
			
			Desktop.getDesktop().browse(extentReport.toURI());//will open the report in the browser automatically
		}
		
		catch(IOException e)
		{
			e.printStackTrace();
			
		}
		
		//code which sends email automatically , give your own email and password
		
		/*try {
			URL url=new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
		 
		//create the email message 
		ImageHtmlEmail email= new ImageHtmlEmail();
		
		email.setDataSourceResolver(new DataSourceUrlResolver(url));
		email.setHostName("smtp.google.com");//this keeps changing according to where you work
		email.setSmtpPort(465);//works only for google
		email.setAuthenticator(new DefaultAuthenticator("pavantraining@gmail.com","password"));
		email.setSSLOnConnect(true);
		email.setFrom("pavanoltraining@gmail.com");//sender
		email.setSubject("Test Results");
		email.setMsg("Please find Attached Report...");
		email.addTo("pavankumar.busyqa@gmail.com");//Receiver
		email.attach(url, "extent report","please check report..." );
		email.send();//send the email
		}
		 catch(Exception e)// this is used if try block doesn't work
		 {
			e.printStackTrace(); //prints the exception message in console
			 
		 }
		
		*/		
		
	}
}
