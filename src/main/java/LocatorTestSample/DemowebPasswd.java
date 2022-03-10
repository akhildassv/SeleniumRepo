package LocatorTestSample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DemowebPasswd {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\Selenium Files\\downloads\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("http://demowebshop.tricentis.com/login");

        //WebElement password=driver.findElement(By.id("Password"));
        //WebElement password=driver.findElement(By.name("Password"));
        //WebElement password=driver.findElement(By.className("password"));
        //WebElement password=driver.findElement(By.xpath("//*[@id=\"Password\"]"));
        WebElement password = driver.findElement(By.cssSelector("#Password"));
        password.sendKeys("akhildas");
    }

}