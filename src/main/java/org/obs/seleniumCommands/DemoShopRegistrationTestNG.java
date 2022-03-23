package org.obs.seleniumCommands;


import org.obs.seleniumbasics.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class DemoShopRegistrationTestNG {
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

    public void selectGender(String gender) {
        List<WebElement> options = driver.findElements(By.xpath("//label[contains(@for,'gender')]"));
        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(gender)) ;
            {
                option.click();
            }
        }
    }

    @BeforeMethod
    public void setUp() {
        testInitialize("chrome");
    }

    @AfterMethod
    public void tearDown() {
        //driver.close();
    }

    @Test
    public void verifyRegistration() {
        driver.get("http://demowebshop.tricentis.com/");
        WebElement login = driver.findElement(By.cssSelector("li>a[class='ico-register']"));
        login.click();
        selectGender("male");
        WebElement first = driver.findElement(By.xpath("//input[@id='FirstName']"));
        first.sendKeys("Akhil");
        WebElement last = driver.findElement(By.xpath("//input[@id='LastName']"));
        last.sendKeys("Das");

        //INVOKING Utility Method
        Utility utility = new Utility();
        String randMail = utility.getUtility();
        WebElement email = driver.findElement(By.xpath("//input[@id='Email']"));
        email.sendKeys(randMail);

        WebElement password = driver.findElement(By.xpath("//input[@id='Password']"));
        password.sendKeys("akhil123");
        WebElement password1 = driver.findElement(By.xpath("//input[@id='ConfirmPassword']"));
        password1.sendKeys("akhil123");
        WebElement register = driver.findElement(By.xpath("//input[@id='register-button']"));
        register.click();
        WebElement result = driver.findElement(By.xpath("//div[@class='result']"));
        String actualText = result.getText();
        String expectedText = "Your registration completed";
        Assert.assertEquals(actualText, expectedText, "Not Registered");
    }
}
