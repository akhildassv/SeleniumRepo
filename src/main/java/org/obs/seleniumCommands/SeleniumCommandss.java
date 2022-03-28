package org.obs.seleniumCommands;

import org.asynchttpclient.util.Assertions;
import org.obs.seleniumbasics.Utility;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;


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

    public void selectGender(String gender) {
        List<WebElement> options = driver.findElements(By.xpath("//label[contains(@for,'gender')]"));
        for (int i = 0; i < options.size(); i++) {
            if (options.get(i).getText().equalsIgnoreCase(gender)) {
                options.get(i).click();
            }
        }
        /*for (WebElement option : options) {
            String value=option.getText();
            if (value.equalsIgnoreCase(gender))
            {
                option.click();
            }
        }*/
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
        Assert.assertEquals(actualemailID, expectedemailID, "login failed");
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
        String actualText = text.getText();
        String expectedText = "Login was unsuccessful. Please correct the errors and try again.";
        Assert.assertEquals(actualText, expectedText, "Clear command is not working");
        Boolean enabled = text.isEnabled();
        System.out.println(enabled);
        Assert.assertTrue(enabled, "button not enabled");
    }

    @Test
    public void verifyWebElementCommands() {
        driver.get("http://demowebshop.tricentis.com/");
        WebElement login = driver.findElement(By.cssSelector("li>a[class='ico-login'"));
        login.click();
        WebElement submit = driver.findElement(By.cssSelector("div>input[type='submit'"));
        /*Dimension dimension =submit.getSize();
        int height=dimension.height;
        int width=dimension.width;
        System.out.println("Height="+height);
        System.out.println("Width="+width);*/
        Point point = submit.getLocation();
        int x = point.x;
        int y = point.y;
        System.out.println("X cordinate of login button=" + x);
        System.out.println("Y cordinate of login button=" + y);
        String ActualLoginButton = submit.getAttribute("value");
        String ExpectedLoginButton = "Log in";
        //Assert.assertEquals(ActualLoginButton,ExpectedLoginButton,"Not correct text");
        String tagname = submit.getTagName();
        System.out.println("Tag Name is " + tagname);
        String cssProperty = submit.getCssValue("color");
        System.out.println("Color is " + cssProperty);

    }

    @Test
    public void verifySearchButton() {
        driver.get("http://demowebshop.tricentis.com/");
        WebElement search = driver.findElement(By.xpath("//input[@class='button-1 search-box-button']"));
        Dimension dimension = search.getSize();
        int height = dimension.height;
        int width = dimension.width;
        System.out.println("Height=" + height);
        System.out.println("Width=" + width);
        Point point = search.getLocation();
        int x = point.x;
        int y = point.y;
        System.out.println("X cordinate of login button=" + x);
        System.out.println("Y cordinate of login button=" + y);
        String ActualLoginButton = search.getAttribute("value");
        String ExpectedLoginButton = "Search";
        Assert.assertEquals(ActualLoginButton, ExpectedLoginButton, "Not correct text");
    }

    @Test
    public void verifyRegistration() {
        driver.get("http://demowebshop.tricentis.com/");
        WebElement login = driver.findElement(By.cssSelector("li>a[class='ico-register']"));
        login.click();
        selectGender("Male");
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

    @Test
    public void verifyElementPresent() {
        driver.get("http://demowebshop.tricentis.com/");
        WebElement login = driver.findElement(By.cssSelector("li>a[class='ico-login'"));
        login.click();
        WebElement submit = driver.findElement(By.cssSelector("div>input[type='submit']"));
        Boolean result = submit.isDisplayed();
        System.out.println(result);
        Assert.assertTrue(result, "Submit button not displayed");
    }

    @Test
    public void verifyElementEnabled() {
        //driver.get("http://demowebshop.tricentis.com/");
        //WebElement login = driver.findElement(By.cssSelector("li>a[class='ico-login'"));
        //login.click();
        //WebElement submit = driver.findElement(By.cssSelector("div>input[type='submit']"));
        //Boolean enabledStatus=submit.isEnabled();
        //System.out.println(enabledStatus);
        //Assert.assertTrue(enabledStatus,"login button not enabled");
        driver.get("https://www.facebook.com/");
        WebElement email = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div/div[1]/h2"));
        Boolean enabledStatus1 = email.isEnabled();
        System.out.println(enabledStatus1);
        Assert.assertTrue(enabledStatus1, "button not enabled");
    }

    @Test
    public void verifyCheckbox() {
        driver.get("http://demowebshop.tricentis.com/");
        WebElement login = driver.findElement(By.cssSelector("li>a[class='ico-login'"));
        login.click();
        WebElement check = driver.findElement(By.cssSelector("input[type='checkbox']"));
        Boolean isselect1 = check.isSelected();
        System.out.println(isselect1);
        Assert.assertFalse(isselect1, "button is selected");
        check.click();
        Boolean isselect = check.isSelected();
        System.out.println(isselect);
        Assert.assertTrue(isselect, "Button is not selected");
    }

    @Test
    public void verifyPromtAlert() {
        driver.get("https://demoqa.com/alerts");
        WebElement alert4 = driver.findElement(By.id("promtButton"));
        alert4.click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println(alertText);
        alert.sendKeys("Akhil");
        alert.accept();
    }

    @Test
    public void verifySimpleAlert() {
        driver.get("https://demoqa.com/alerts");
        WebElement alert1 = driver.findElement(By.id("alertButton"));
        alert1.click();
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();
        //alert.dismiss();
    }

    @Test
    public void verifyConfirmationAlert() {
        driver.get("https://demoqa.com/alerts");
        WebElement alert1 = driver.findElement(By.id("confirmButton"));
        alert1.click();
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        //alert.accept();
        alert.dismiss();
    }


    @Test
    public void verifyDropdown() {
        driver.navigate().to("https://demo.guru99.com/test/newtours/");
        List<WebElement> menu = driver.findElements(By.xpath("//td[@class='mouseOut']//a"));
        for (WebElement option : menu) {
            String value = option.getText();
            if (value.equalsIgnoreCase("REGISTER")) {
                option.click();
                break;
            }
        }
        WebElement dropdown = driver.findElement(By.xpath("//select[@name='country']"));
        Select select = new Select(dropdown);
        // select.selectByVisibleText("INDIA");
        //select.selectByValue("INDIA");
        //select.selectByIndex(20);
        List<WebElement> dropdownValues = select.getOptions();
        System.out.println(dropdownValues.size());
        for (int i = 0; i < dropdownValues.size(); i++) {
            //System.out.println(dropdownValues.get(i).getText());
        }
    }

    @Test
    public void demoDeleteAlert() {
        driver.get("https://demo.guru99.com/test/delete_customer.php");
        WebElement text = driver.findElement(By.name("cusid"));
        text.sendKeys("Akhil");
        WebElement submit = driver.findElement(By.name("submit"));
        submit.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        System.out.println(alert.getText());
        alert.accept();
    }

    @Test
    public void verifyMultipleWindow() {
        driver.get("https://demo.guru99.com/popup.php");
        String parentWindow=driver.getWindowHandle();
        System.out.println(parentWindow);
        WebElement text = driver.findElement(By.xpath("//p//a"));
        text.click();
        Set<String> windows=driver.getWindowHandles();
        Iterator<String> iterator=windows.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
            String newWindow=iterator.next();
            if(! newWindow.equals(parentWindow)){
                driver.switchTo().window(newWindow);
                WebElement email = driver.findElement(By.name("emailid"));
                email.sendKeys("akhildas710@gmail.com");
                WebElement submit = driver.findElement(By.name("btnLogin"));
                submit.click();
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
    }
}

