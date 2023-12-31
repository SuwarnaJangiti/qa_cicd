package Demofor_qacicd;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestClass {
	WebDriver driver;
	ExtentReports extentReport;
	ExtentSparkReporter sparkReporter;
	ExtentTest test;
	@BeforeTest
	public void initializingReports() {
		extentReport=new ExtentReports();
		sparkReporter=new ExtentSparkReporter("report.html");
		extentReport.attachReporter(sparkReporter);
	}
	
	@BeforeMethod
	public void inintializingTest(ITestResult result) {
		test=extentReport.createTest(result.getMethod().getMethodName());
	}
	
	@Test
	public void verifyTitle() throws InterruptedException {
	//WebDriverManager.chromedriver().setup();
	ChromeOptions options=new ChromeOptions();
	options.addArguments("headless");
	WebDriverManager.chromedriver().setup();
	driver=new ChromeDriver(options);
//	driver.get("https://ananthjeevan.in/");
	driver.get("http://192.168.0.34:5173");
	String url=driver.getCurrentUrl();
	System.out.println("Current url is: " +driver.getCurrentUrl());	
	test.info("Current url is : "+ url);	
	System.out.println("Title is: " +driver.getTitle());
//	Assert.assertEquals(driver.getTitle(), "Ananth Jeevan - Life in its fullness");
	Assert.assertEquals(driver.getTitle(), "Bible Study");
	Thread.sleep(2000);
//	driver.findElement(By.xpath("//button[text()='Sign up/Sign in']")).click();
	Thread.sleep(2000);
	driver.quit();
	}
	
	@AfterMethod
	public void aftermethod(ITestResult result) {
		if(result.getStatus()==result.SUCCESS) {
			test.pass(result.getMethod().getMethodName()+" : Passed");
		}
		else {
			test.fail(result.getMethod().getMethodName()+" : Failed");
			test.log(Status.INFO, result.getThrowable());
		}
	}
	
	@AfterTest
	public void flushTheReport() {
		extentReport.flush();
	}

}
