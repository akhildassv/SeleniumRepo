package org.obs.seleniumbasics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.sql.SQLOutput;

public class SeleniumBasics
{
    public static void main(String[] args)
    {

        //System.setProperty("webdriver.edge.driver","D:\\Selenium Files\\downloads\\msedgedriver.exe");
        //WebDriver driver = new EdgeDriver();

        //System.setProperty("webdriver.gecko.driver","D:\\Selenium Files\\downloads\\geckodriver.exe");
         //WebDriver driver=new FirefoxDriver();

        System.setProperty("webdriver.chrome.driver", "D:\\Selenium Files\\downloads\\chromedriver.exe");
        WebDriver driver=new ChromeDriver();

        //System.setProperty("webdriver.ie.driver", "D:\\Selenium Files\\downloads\\IEDriverServer.exe");
       // WebDriver driver=new InternetExplorerDriver();

        //to delete cookies
        driver.manage().deleteAllCookies();
        //To maximise the window
        driver.manage().window().maximize();

        //loading url
       //driver.get("http://demowebshop.tricentis.com/");
        driver.navigate().to("http://demowebshop.tricentis.com/");

       //get title of url
        String actualTitile=driver.getTitle();
        System.out.println(actualTitile);

        //To get curent URL of page
        String currentUrl=driver.getCurrentUrl();
        System.out.println(currentUrl);

        //String getPageSourse=driver.getPageSource();
       // System.out.println(getPageSourse);

        //Navigation Commands
        //driver.navigate().to("http://demowebshop.tricentis.com/");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();

        //To close the webpage
        driver.close();
       // driver.quit();
    }
}
