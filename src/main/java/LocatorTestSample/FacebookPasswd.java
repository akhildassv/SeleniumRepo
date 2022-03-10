package LocatorTestSample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FacebookPasswd {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\Selenium Files\\downloads\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/login/");

        //WebElement password=driver.findElement(By.id("pass"));
        //WebElement password=driver.findElement(By.name("pass"));
        /*NOT RUN USING CLASS NAME
        WebElement password=driver.findElement(By.className("inputtext _55r1 inputtext _9npi inputtext _9npi"));
        */
        //WebElement password=driver.findElement(By.xpath("//*[@id=\"pass\"]"));
        WebElement password = driver.findElement(By.cssSelector("#pass"));
        password.sendKeys("akhildas");
    }

}
