package org.obs.seleniumbasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class Demo99Registration {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\Selenium Files\\downloads\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //System.setProperty("webdriver.gecko.driver","D:\\Selenium Files\\downloads\\geckodriver.exe");
        //WebDriver driver=new FirefoxDriver();

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.navigate().to("https://demo.guru99.com/test/newtours/");
        driver.findElement(By.xpath("//a[@href='register.php']")).click();
        WebElement first = driver.findElement(By.xpath("//input[@name='firstName']"));
        first.sendKeys("Akhil");
        WebElement last = driver.findElement(By.xpath("//input[@name='lastName']"));
        last.sendKeys("Das");
        WebElement phone = driver.findElement(By.xpath("//input[@name='phone']"));
        phone.sendKeys("9513318407");
        WebElement email = driver.findElement(By.xpath("//input[@id='userName']"));
        email.sendKeys("akhildas710@gmail.com");
        WebElement address = driver.findElement(By.xpath("//input[@name='address1']"));
        address.sendKeys("S.V.Bhavan");
        WebElement city = driver.findElement(By.xpath("//input[@name='city']"));
        city.sendKeys("Thiruvananthapuram");
        WebElement sate = driver.findElement(By.xpath("//input[@name='state']"));
        sate.sendKeys("Kerala");
        WebElement pin = driver.findElement(By.xpath("//input[@name='postalCode']"));
        pin.sendKeys("629152");
        driver.findElement(By.xpath("//option[@value='INDIA']")).click();

        /*
        WebElement select=driver.findElement(By.xpath("//select[@name='country']"));
        List<WebElement> options =select.findElements(By.tagName("option"));
        for (WebElement option : options) {
            if("INDIA".equals(option.getText().trim()));{
                option.click();
            }
        }*/


        //email invoking
        Utility utility=new Utility();
        String randMail=utility.getUtility();
        WebElement username = driver.findElement(By.xpath("//input[@id='email']"));
        username.sendKeys(randMail);
        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        password.sendKeys("akhil123");
        WebElement password1 = driver.findElement(By.xpath("//input[@name='confirmPassword']"));
        password1.sendKeys("akhil123");
        driver.findElement(By.xpath("//input[@name='submit']")).click();
    }
}
