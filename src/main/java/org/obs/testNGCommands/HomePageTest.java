package org.obs.testNGCommands;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class HomePageTest {
    WebDriver driver;

    public void testInitialize(String browser) {
        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        } else if (browser.equals("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equals("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            try {
                throw new Exception("invalid browsername");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    @Parameters({"browser","url"})
    public void setUp(String browserName,String baseUrl) {
        testInitialize(browserName);
        driver.get(baseUrl);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if(ITestResult.FAILURE==result.getStatus()) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("./ScreenShots/" + result.getName() + ".png"));
        }
        driver.close();
    }


    @Test
    public void verifyHomepageTitle() {
        String actualTitle = driver.getTitle();
        String expectedTitle = "Demo Web Shop";
        Assert.assertEquals(actualTitle, expectedTitle, "Invalid Page Title");
    }
    @Test(dataProvider = "loginData")
    public void verifylogin(String uName,String pWord) {
        WebElement login = driver.findElement(By.cssSelector("a.ico-login"));
        login.click();
        WebElement email = driver.findElement(By.cssSelector("input#Email"));
        email.sendKeys(uName);
        WebElement password = driver.findElement(By.cssSelector("input[type='password']"));
        password.sendKeys(pWord);
        WebElement checkin = driver.findElement(By.cssSelector("input[id='RememberMe']"));
        checkin.click();
        WebElement loginbutton = driver.findElement(By.cssSelector("input[value='Log in']"));
        loginbutton.click();
        WebElement account = driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
        String actualemailID = account.getText();
        String expectedemailID = uName;
        Assert.assertEquals(actualemailID, expectedemailID, "User login Failed");
    }
    @DataProvider(name = "loginData")
    public Object[][] userCredentials(){
        Object[][] data = new Object[2][2];
        data[0][0]= "jackbatson@jackbatson.com";
        data[0][1]= "123456";
        data[1][0]="akhildas710@gmail.com";
        data[1][1]="akhildas";
        return data;
    }

}
