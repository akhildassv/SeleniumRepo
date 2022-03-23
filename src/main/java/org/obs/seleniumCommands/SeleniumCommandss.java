package org.obs.seleniumCommands;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class SeleniumCommandss {
    WebDriver driver;

    public void testInitialize(String browser) {
        if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "D:\\Selenium Files\\downloads\\chromedriver.exe");
            driver = new ChromeDriver();

        } else if (browser.equals("Firefox")) {
            System.setProperty("webdriver.gecko.driver", "D:\\Selenium Files\\downloads\\geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browser.equals("Edge")) {
            System.setProperty("webdriver.edge.driver", "D:\\Selenium Files\\downloads\\msedgedriver.exe");
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
    public void setUp() {
        testInitialize("chrome");
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }


    @Test
    public void verifyHomepageTitle() {
        driver.get("http://demowebshop.tricentis.com/");
        String actualTitle = driver.getTitle();
        String expectedTitle = "Demo Web Shop";
        //Assert.assertEquals(actualTitle, expectedTitle, "invalid Page Title");
        Assert.assertEquals(actualTitle, expectedTitle, "invalid page title");
    }

    @Test
    public void verifylogin() {
        driver.get("http://demowebshop.tricentis.com/");
        WebElement login = driver.findElement(By.cssSelector("li>a[class='ico-login'"));
        login.click();
        WebElement email = driver.findElement(By.cssSelector("input#Email"));
        email.sendKeys("akhildas710@gmail.com");
        WebElement password = driver.findElement(By.cssSelector("input.password"));
        password.sendKeys("akhildas");
        WebElement check = driver.findElement(By.cssSelector("input[type='checkbox'"));
        check.click();
        WebElement submit = driver.findElement(By.cssSelector("div>input[type='submit'"));
        submit.click();
        WebElement account = driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
        String actualemailID = account.getText();
        String expectedemailID = "akhildas710@gmail.com";
        Assert.assertEquals(actualemailID,expectedemailID,"login failed");
    }

    @Test
    public void verifyClear() throws InterruptedException {
        driver.get("http://demowebshop.tricentis.com/");
        WebElement login = driver.findElement(By.cssSelector("li>a[class='ico-login'"));
        login.click();
        WebElement email = driver.findElement(By.cssSelector("input#Email"));
        email.sendKeys("akhildas710@gmail.com");
        WebElement password = driver.findElement(By.cssSelector("input.password"));
        password.sendKeys("akhildas");
        email.clear();
        WebElement check = driver.findElement(By.cssSelector("input[type='checkbox']"));
        check.click();
        WebElement submit = driver.findElement(By.cssSelector("div>input[type='submit']"));
        submit.click();
        WebElement text = driver.findElement(By.xpath("//div[@class='validation-summary-errors']//span"));
        String actualText=text.getText();
        String expectedText="Login was unsuccessful. Please correct the errors and try again.";
        Assert.assertEquals(actualText,expectedText,"Clear command is not working");
    }

    @Test
    public void verifyWebElementCommands()    {
        driver.get("http://demowebshop.tricentis.com/");
        WebElement login = driver.findElement(By.cssSelector("li>a[class='ico-login'"));
        login.click();
        WebElement submit = driver.findElement(By.cssSelector("div>input[type='submit'"));
        /*Dimension dimension =submit.getSize();
        int height=dimension.height;
        int width=dimension.width;
        System.out.println("Height="+height);
        System.out.println("Width="+width);*/
        Point point=submit.getLocation();
        int x=point.x;
        int y=point.y;
        System.out.println("X cordinate of login button="+x);
        System.out.println("Y cordinate of login button="+y);
        String ActualLoginButton=submit.getAttribute("value");
        String ExpectedLoginButton="Log in";
        Assert.assertEquals(ActualLoginButton,ExpectedLoginButton,"Not correct text");
    }

    @Test
    public void verifySearchButton(){
        driver.get("http://demowebshop.tricentis.com/");
        WebElement search = driver.findElement(By.xpath("//input[@class='button-1 search-box-button']"));
        Dimension dimension =search.getSize();
        int height=dimension.height;
        int width=dimension.width;
        System.out.println("Height="+height);
        System.out.println("Width="+width);
        Point point=search.getLocation();
        int x=point.x;
        int y=point.y;
        System.out.println("X cordinate of login button="+x);
        System.out.println("Y cordinate of login button="+y);
        String ActualLoginButton=search.getAttribute("value");
        String ExpectedLoginButton="Search";
        Assert.assertEquals(ActualLoginButton,ExpectedLoginButton,"Not correct text");
    }
}
