package LocatorTestSample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.List;

public class DemowebPasswd {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\Selenium Files\\downloads\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("http://demowebshop.tricentis.com/login");

        //WebElement password=driver.findElement(By.id("Password"));
        WebElement password=driver.findElement(By.name("Password"));
        //WebElement password=driver.findElement(By.className("password"));
        //WebElement password=driver.findElement(By.xpath("//*[@id=\"Password\"]"));
        //WebElement password = driver.findElement(By.cssSelector("#Password"));

        //webelement using link text
        //WebElement password=driver.findElement(By.linkText("log in"));
       // WebElement password=driver.findElement(By.partialLinkText(""));

        List<WebElement> tag= driver.findElements(By.tagName("a"));
        int size=tag.size();
        System.out.println(size);
        System.out.println(tag);
        password.sendKeys("akhildas");
        Iterator itr=tag.iterator();
        while (itr.hasNext())
        {
            System.out.println(itr.next());
        }

    }

}
