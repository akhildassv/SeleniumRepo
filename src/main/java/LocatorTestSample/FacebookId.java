package LocatorTestSample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FacebookId {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\Selenium Files\\downloads\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/login/");

        //WebElement email=driver.findElement(By.id("email"));
        //WebElement email=driver.findElement(By.name("email"));
        //NOT RUN USING CLASS NAME
        //WebElement email=driver.findElement(By.className("inputtext _55r1 inputtext _1kbt inputtext _1kbt"));

        //WebElement email=driver.findElement(By.xpath("//*[@id=\"email\"]"));
        WebElement email = driver.findElement(By.cssSelector("#email"));
        email.sendKeys("sv.akhildas@gmail.com");
    }

}
