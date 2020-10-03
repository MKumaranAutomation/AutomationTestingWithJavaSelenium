package com.petclinic.ExtentReport;

import java.io.File;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentListener extends com.petclinic.base.TestBase implements IReporter, ITestListener {
	
    public ExtentReports extent;
    private String reportPath = System.getProperty("user.dir");
	public String browsersParam;
    public WebDriver driver;
    public String screenshotPath;
    public String message;
    public int totalTests = 0;
    public int i=1;
    public String testName;
    
    /*ExtentReports - Generate Reports for the Suites*/
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        for (ISuite suite : suites) {
        	
//            String suiteName = suite.getName();
            List<XmlTest> testClasses = suite.getXmlSuite().getTests();
            Map<String, ISuiteResult> result = suite.getResults();
            for(XmlTest testClass : testClasses) {
            	
            	testName = testClass.getName().toUpperCase();
            	
            	extent = new ExtentReports(reportPath +File.separator+"ExtentReports"+File.separator+testName+" - "+"ExtentReport.html", true, DisplayOrder.OLDEST_FIRST);
                extent.loadConfig(new File(System.getProperty("user.dir")+File.separator+"extent-config.xml"));
                extent.addSystemInfo("Environment",baseurl);
        		extent.addSystemInfo("Browser",browserName);

                for (ISuiteResult r : result.values()) {
                	ITestContext context = r.getTestContext();
                    buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
                    buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
                    buildTestNodes(context.getPassedTests(), LogStatus.PASS);
                }
                extent.flush();
                extent.close();
            }

        }
        
    }
	
    /*Extent Reports - Report the TestResults : PASS / FAIL / SKIP*/
    private void buildTestNodes(IResultMap tests, LogStatus status) {
        ExtentTest test;
  
        if (tests.size() > 0) {
        	 List<ITestResult> resultList = new LinkedList<ITestResult>(tests.getAllResults());
        	 
             class ResultComparator implements Comparator<ITestResult> {
                 public int compare(ITestResult r1, ITestResult r2) {
                     return getTime(r1.getStartMillis()).compareTo(getTime(r2.getStartMillis()));
                 }
             }

             Collections.sort(resultList , new ResultComparator());
             
             for (ITestResult result : tests.getAllResults()) {
                test = extent.startTest(result.getMethod().getMethodName());
                
                test.getTest().setStartedTime(getTime(result.getStartMillis()));
                test.getTest().setEndedTime(getTime(result.getEndMillis()));
                
                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);
  
                if(Arrays.toString(result.getParameters()) ==null){
                	message ="THE "+result.getName().toUpperCase()+" IS " + status.toString().toUpperCase() + "ED";
                	
                }else {
                	message ="THE "+result.getName().toUpperCase()+ " IS " + status.toString().toUpperCase() + "ED";
                }
                	
                if (result.getThrowable() != null){
                    message = result.getThrowable().toString();
                }
                
                test.log(status, message);
                if(result.getStatus() == ITestResult.FAILURE) {
                	if(System.getProperty("os.name").contains("Windows")) {
                		test.log(LogStatus.FAIL, test.addScreenCapture(System.getProperty("user.dir") +File.separator+"Screenshots"+File.separator+com.petclinic.base.TestBase.browserName+File.separator+result.getName()+".png"));
                	}else {
                		test.log(LogStatus.FAIL, test.addScreenCapture(".."+File.separator+"ws"+File.separator+"Screenshots"+File.separator+""+com.petclinic.base.TestBase.browserName+""+File.separator+""+result.getName()+".png"));
                	}
                }
                extent.endTest(test);
            }
        }
    }

	private Date getTime(long millis) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(millis);
    return calendar.getTime();        
}
	
	

	/*Test Execution Logs*/
	public void onTestStart(ITestResult result) {
		System.out.println("/*--------------------------------------------------------------------------------------*/");
		System.out.println("The Test "+i++ +" of "+totalTests);
		System.out.println(displayDate()+" : The "+result.getName()+" has been Started.");
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println(displayDate()+" : The "+result.getName()+" is Passed.");
		System.out.println("/*--------------------------------------------------------------------------------------*/");
	}

	public void onTestFailure(ITestResult result) {
		System.out.println(displayDate()+" : The "+result.getName()+" is Failed.");
		System.out.println("/*--------------------------------------------------------------------------------------*/");
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println(displayDate()+" : The "+result.getName()+" is Skipped.");
		System.out.println("/*--------------------------------------------------------------------------------------*/");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onStart(ITestContext context) {
		totalTests = context.getAllTestMethods().length;
		System.out.println("The Test has been Initiated.");
//		System.out.println("The Total Number of Tests: "+totalTests);
	}

	public void onFinish(ITestContext context) {
		String suiteName = context.getCurrentXmlTest().getName();
		totalTests = context.getAllTestMethods().length;
		int passedTests = context.getPassedTests().size();
		int failedTests = context.getFailedTests().size();
		int skippedTests = context.getSkippedTests().size();
		System.out.println(totalTests+"-"+passedTests+"-"+failedTests+"-"+skippedTests);
	}
	
}