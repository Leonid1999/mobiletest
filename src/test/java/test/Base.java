package test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import page.BasePage;
import page.MessagePage;

import java.io.File;
import java.net.URL;



public class Base {
    public BasePage basePage1;
    public BasePage basePage2;

    public MessagePage messagePage1;
    public MessagePage messagePage2;

    AppiumDriver driver1;
    AppiumDriver driver2;

    WebDriverWait wait1;
    WebDriverWait wait2;


    public void createDevice(DesiredCapabilities capabilities, String platformName, String platformVersion, String deviceName, String udid){
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        capabilities.setCapability(MobileCapabilityType.UDID, udid);
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("appPackage", "com.dstarlab.icommunicator");
        capabilities.setCapability("appActivity", "org.thoughtcrime.securesms.RoutingActivity");
    }

    @BeforeSuite
    public void setUp() throws Exception{
        //create first device
        URL url1 = new URL("http://127.0.0.1:4723/wd/hub");
        DesiredCapabilities capabilities1 = new DesiredCapabilities();
        createDevice(capabilities1, "Android", "7", "Galaxy A3", "5203f7f6c0d49357");
        driver1 = new AndroidDriver(url1, capabilities1);
        wait1 = new WebDriverWait(driver1, 30);
        basePage1 = PageFactory.initElements(driver1, BasePage.class);
        messagePage1= PageFactory.initElements(driver1,MessagePage.class);


        //create second device
        URL url2 = new URL("http://127.0.0.1:5723/wd/hub");
        DesiredCapabilities capabilities2 = new DesiredCapabilities();
        createDevice(capabilities2, "Android", "8", "Galaxy S7", "ce091719c8adc72903");
        driver2 = new AndroidDriver(url2, capabilities2);
        wait2 = new WebDriverWait(driver2, 30);
        basePage2 = PageFactory.initElements(driver2, BasePage.class);
        messagePage2 = PageFactory.initElements(driver2, MessagePage.class);
    }

    @AfterSuite
    public void close(){
        if(driver1!=null)
            driver1.quit();
        if(driver2!=null)
            driver2.quit();
    }

}
