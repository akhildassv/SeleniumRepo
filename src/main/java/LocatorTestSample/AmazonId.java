package LocatorTestSample;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonId {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","D:\\Selenium Files\\downloads\\chromedriver.exe");
        WebDriver driver=new ChromeDriver();

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.in/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.in%2Fs%3Fk%3Dlogin%2Bamazon%2Baccount%26adgrpid%3D59671903835%26ext_vrnc%3Dhi%26gclid%3DCjwKCAiA4KaRBhBdEiwAZi1zzhHR-qR3J8Tz1l_x-2p-Y6CK3_nZFGQ9tfUeNr-yY1QpgqLz03FljhoCfTgQAvD_BwE%26hvadid%3D398134035836%26hvdev%3Dc%26hvlocphy%3D9299907%26hvnetw%3Dg%26hvqmt%3De%26hvrand%3D7423101871665005805%26hvtargid%3Dkwd-837441119212%26hydadcr%3D24542_1971411%26tag%3Dgooginhydr1-21%26ref%3Dnav_ya_signin&prevRID=GGNREEJDPZ320CD789KE&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=inflex&openid.mode=checkid_setup&failedSignInCount=0&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&pageId=inflex&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0");

        //WebElement email=driver.findElement(By.id("ap_email"));
        //WebElement email=driver.findElement(By.name("email"));
        //CAN NOT RUN IN CLASS NAME
        //WebElement email=driver.findElement(By.className("a-input-text a-span12 auth-autofocus auth-required-field"));

        //WebElement email=driver.findElement(By.xpath("//*[@id=\"ap_email\"]"));
        WebElement email = driver.findElement(By.cssSelector("#ap_email"));
        email.sendKeys("sv.akhildas@gmail.com");


    }


}
