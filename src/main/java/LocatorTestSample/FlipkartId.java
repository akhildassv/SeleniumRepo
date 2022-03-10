package LocatorTestSample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FlipkartId {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\Selenium Files\\downloads\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        //driver.get("https://www.flipkart.com/");
        driver.navigate().to("https://www.flipkart.com/");

        //No ID
        //No Name
        //Not Run//WebElement email=driver.findElement(By.className("_2IX_2- VJZDxU"));

        //WebElement email=driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div/form/div[1]/input"));
        WebElement email = driver.findElement(By.cssSelector("body > div._2Sn47c > div > div > div > div > div._36HLxm.col.col-3-5 > div > form > div:nth-child(1) > input"));
        email.sendKeys("sv.akhildas@gmail.com");
    }
}
