package org.obs.seleniumCommands;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.asynchttpclient.util.Assertions;
import org.obs.seleniumbasics.ExcelUtility;
import org.obs.seleniumbasics.TableUtility;
import org.obs.seleniumbasics.Utility;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.naming.ldap.Control;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class SeleniumCommandss {
    WebDriver driver;

    public void testInitialize(String browser) {
        if (browser.equals("chrome")) {
            //System.setProperty("webdriver.chrome.driver", "D:\\Selenium Files\\downloads\\chromedriver.exe");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        } else if (browser.equals("Firefox")) {
            //System.setProperty("webdriver.gecko.driver", "D:\\Selenium Files\\downloads\\geckodriver.exe");
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equals("Edge")) {
            //System.setProperty("webdriver.edge.driver", "D:\\Selenium Files\\downloads\\msedgedriver.exe");
            WebDriverManager.edgedriver().setup();
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
    public void tearDown(ITestResult result) throws IOException {
        if(ITestResult.FAILURE==result.getStatus()) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("./ScreenShots/" + result.getName() + ".png"));
        }
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

    @Test  //Page WAITING
    public void verifylogin() {
        driver.get("http://demowebshop.tricentis.com/");
        //Page load wait
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        WebElement login = driver.findElement(By.cssSelector("li>a[class='ico-login'"));
        login.click();
        WebElement email = driver.findElement(By.cssSelector("input#Email"));
        email.sendKeys("akhildas710@gmail.com");
        WebElement password = driver.findElement(By.cssSelector("input.password"));
        password.sendKeys("akhildas");
        WebElement check = driver.findElement(By.cssSelector("input[type='checkbox'"));
        check.click();

        //explicit wait
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[value='Log in']")));

        WebElement submit = driver.findElement(By.cssSelector("div>input[type='submit'"));
        submit.click();
        //implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
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
        String parentWindow = driver.getWindowHandle();
        System.out.println(parentWindow);
        WebElement text = driver.findElement(By.xpath("//p//a"));
        text.click();
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> iterator = windows.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            String newWindow = iterator.next();
            if (!newWindow.equals(parentWindow)) {
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

    @Test
    public void verifyFramesinSelenium() {
        driver.get("https://demoqa.com/frames");
        //driver.switchTo().frame(3);
        //driver.switchTo().frame("frame1");
        WebElement frameElement = driver.findElement(By.id("frame1"));
        driver.switchTo().frame(frameElement);
        WebElement heading = driver.findElement(By.id("sampleHeading"));
        String sampleHeading = heading.getText();
        System.out.println(sampleHeading);
    }

    public void singleColorSelection(String color) {
        WebElement dropdown = driver.findElement(By.id("single-input-field"));
        Select select = new Select(dropdown);
        select.selectByVisibleText(color);
    }

    @Test
    public void verifySelectedColor() {
        driver.get("https://selenium.obsqurazone.com/select-input.php");
        singleColorSelection("Yellow");
        String actualColor = "Selected Color : Yellow";
        WebElement printcolor = driver.findElement(By.id("message-one"));
        String expectedColor = printcolor.getText();
        Assert.assertEquals(actualColor, expectedColor, "Printed color is not same");
    }

    @Test
    public void verifyMultipleSelectedColor() {
        driver.get("https://selenium.obsqurazone.com/select-input.php");
        WebElement dropdown = driver.findElement(By.id("multi-select-field"));
        Select select = new Select(dropdown);
        List<WebElement> t = select.getOptions();
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.LEFT_CONTROL)
                .click(t.get(0))
                .click(t.get(1))
                .keyUp(Keys.LEFT_CONTROL)
                .build()
                .perform();
        WebElement allSelect = driver.findElement(By.id("button-all"));
        allSelect.click();
        WebElement allSelectMessage = driver.findElement(By.id("message-two"));
        String actualMessage = "All selected colors are : Red,Yellow";
        String expectedMessaage = allSelectMessage.getText();
        System.out.println(expectedMessaage);
        Assert.assertEquals(actualMessage, expectedMessaage, "Printed colors are not same");
    }


    @Test
    public void verifyFirstSelectedColor() {
        driver.get("https://selenium.obsqurazone.com/select-input.php");
        WebElement dropdown = driver.findElement(By.id("multi-select-field"));
        Select select = new Select(dropdown);
        List<WebElement> t = select.getOptions();
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.LEFT_CONTROL)
                .click(t.get(0))
                .click(t.get(1))
                .keyUp(Keys.LEFT_CONTROL)
                .build()
                .perform();
        WebElement firstSelect = driver.findElement(By.id("button-first"));
        firstSelect.click();
        WebElement allSelectMessage = driver.findElement(By.id("message-two"));
        String expectedMessaage = allSelectMessage.getText();
        WebElement first = select.getFirstSelectedOption();
        System.out.println(first.getText());
        String actualMessage = "First selected color is : " + first.getText();
        System.out.println(expectedMessaage);
        Assert.assertEquals(actualMessage, expectedMessaage, "Printed First color is not correct");
    }


    @Test
    public void verifyDeSelectedColor() throws InterruptedException {
        driver.get("https://selenium.obsqurazone.com/select-input.php");
        WebElement dropdown = driver.findElement(By.id("multi-select-field"));
        Select select = new Select(dropdown);
        select.selectByIndex(1);
        Thread.sleep(2000);
        //dropdown.sendKeys(Keys.CONTROL+"A");
        // select.selectByIndex(2);
        //Thread.sleep(2000);
        //select.deselectByIndex(1);
        WebElement allSelect = driver.findElement(By.id("button-all"));
        allSelect.click();
        String actualMessage = "All selected colors are : ";
        WebElement allSelectMessage = driver.findElement(By.id("message-two"));
        String expectedMessaage = allSelectMessage.getText();
        System.out.println(expectedMessaage);
        //  Assert.assertEquals(actualMessage,expectedMessaage,"Needs to press CTRL+click Key for Deselect");

    }

    @Test
    public void VerifyColorOptions() {
        driver.get("https://selenium.obsqurazone.com/select-input.php");
        WebElement dropdown = driver.findElement(By.id("multi-select-field"));
        Select select = new Select(dropdown);
        List<WebElement> selected = select.getOptions();
        List<String> expectedList = new ArrayList<>();
        for (WebElement i : selected) {
            expectedList.add(i.getText());
        }
        List<String> actualList = new ArrayList<>();
        actualList.add("Red");
        actualList.add("Yellow");
        actualList.add("Green");
        Assert.assertEquals(actualList, expectedList, "List is not Matching");
    }

    @Test
    public void verifyRightClick() {
        driver.get("https://demo.guru99.com/test/simple_context_menu.html");
        WebElement rightClick = driver.findElement(By.xpath("//*[@id=\"authentication\"]/span"));
        Actions action = new Actions(driver);
        action.contextClick(rightClick)
                .build()
                .perform();
    }

    @Test
    public void verifyDoubleClick() {
        driver.get("https://demo.guru99.com/test/simple_context_menu.html");
        WebElement doubleClick = driver.findElement(By.xpath("//*[@id=\"authentication\"]/button"));
        Actions action = new Actions(driver);
        action.doubleClick(doubleClick)
                .build()
                .perform();
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();
    }

    public void selectMainMenu(String menuItem) {
        //List<WebElement> menu= driver.findElements(By.xpath("//ul//a[contains(text(),'Main')]"));
        List<WebElement> menu = driver.findElements(By.xpath("//ul//a"));
        System.out.println(menu.size());
        for (WebElement option : menu) {
            String value = option.getText();
            if (value.equalsIgnoreCase(menuItem)) {
                Actions action = new Actions(driver);
                action.moveToElement(option).build().perform();
                //System.out.println(value);
            }
        }
    }

    public void selectSubMenu(String subItem) {
        List<WebElement> subMenu = driver.findElements(By.xpath("//ul//li//a[contains(text(),'Sub')]"));
        System.out.println(subMenu.size());
        for (WebElement option : subMenu) {
            String value = option.getText();
            if (value.equalsIgnoreCase(subItem)) {
                Actions action = new Actions(driver);
                action.moveToElement(option).build().perform();
                System.out.println(value);
            }
        }
    }

    @Test
    public void verifyMouseOver() {
        driver.get("https://demoqa.com/menu/");
        selectMainMenu("Main Item 2");

    }

    public void selectmenu(String item) {
        List<WebElement> options = driver.findElements(By.xpath("//tr[@class='mouseOut']//a"));
        for (WebElement option : options) {
            String value = option.getText();
            if (value.equalsIgnoreCase(item)) {
                Actions action = new Actions(driver);
                action.moveToElement(option).build().perform();
                //action.moveToElement(option,100,100).build().perform();
                //action.moveToElement(option).click().build().perform();
                System.out.println(value);
            }
        }
    }

    @Test
    public void verifyDemotoolMouseOver() {
        driver.get("https://demo.guru99.com/test/newtours/");
        selectmenu("Hotels");
    }

    @Test
    public void verifyDragAndDrop() {
        driver.get("https://demoqa.com/droppable");
        WebElement drag = driver.findElement(By.id("draggable"));
        WebElement drop = driver.findElement(By.id("droppable"));
        Actions action = new Actions(driver);
        action.dragAndDrop(drag, drop).build().perform();
    }

    @Test
    public void verifyDragAndDropBy() {
        driver.get("https://demoqa.com/dragabble");
        WebElement drag = driver.findElement(By.xpath("//div[@id='draggableExample-tabpane-simple']//div"));
        Actions action = new Actions(driver);
        action.dragAndDropBy(drag, 100, 60).build().perform();
    }

    @Test
    public void verifyKeyboardActions() {
        driver.get("https://demoqa.com/text-box");
        WebElement name = driver.findElement(By.id("userName"));
        name.sendKeys("Akhildas");
        WebElement email = driver.findElement(By.id("userEmail"));
        email.sendKeys("akhildas@gmail.com");
        WebElement currentAddress = driver.findElement(By.id("currentAddress"));
        currentAddress.sendKeys("S.V Bhavan,Thiruvananthapuram,Kerala");

        Actions action = new Actions(driver);
        /**select the current address**/
        action.keyDown(Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).build().perform();
        /**Copy the current address**/
        action.keyDown(Keys.CONTROL).sendKeys("C").keyUp(Keys.CONTROL).build().perform();
        /**Press tab key to switch to Permanent address**/
        action.sendKeys(Keys.TAB).build().perform();
        /**Pasting to Permanent address**/
        action.keyDown(Keys.CONTROL).sendKeys("V").keyUp(Keys.CONTROL).build().perform();

    }

    @Test
    public void verifyFileUpload() {
        driver.get("https://demo.guru99.com/test/upload/");
        WebElement upload = driver.findElement(By.id("uploadfile_0"));
        upload.sendKeys("C:\\Selenium\\Sample.txt");
        WebElement accept = driver.findElement(By.id("terms"));
        accept.click();
        WebElement submit = driver.findElement(By.id("submitbutton"));
        submit.click();
    }

    @Test
    public void verifyLoginNew() throws IOException {
        driver.get("http://demowebshop.tricentis.com");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        WebElement login = driver.findElement(By.className("ico-login"));
        login.click();
        ExcelUtility excel=new ExcelUtility();
        List<String> data = excel.readDataFromExcel("\\src\\main\\resources\\TestData.xlsx", "Login");
        WebElement loginEmail = driver.findElement(By.cssSelector("input#Email"));
        System.out.println(data);
        loginEmail.sendKeys(data.get(2));
        WebElement password = driver.findElement(By.cssSelector("input.password"));
        password.sendKeys(data.get(3));
        WebElement checkbox = driver.findElement(By.cssSelector("input[type='checkbox']"));
        checkbox.click();
        WebElement submit = driver.findElement(By.cssSelector("input[value='Log in']"));
        submit.click();
        WebElement account = driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
        String actualemailID = account.getText();
        String expectedemailID = "akhildas710@gmail.com";
        Assert.assertEquals(actualemailID, expectedemailID, "User login Failed");

    }

    @Test
    public void fileuploadUsingRobotclass() throws AWTException {
        driver.get("https://www.monsterindia.com/seeker/registration");
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        WebElement chooseFile=driver.findElement(By.xpath("//span[text()='Choose CV']"));
        chooseFile.click();
        StringSelection S=new StringSelection("C:\\Selenium\\Sample.txt");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(S,null);
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    @Test
    public void verifyJavaScriptExecuter(){
        driver.get("http://demowebshop.tricentis.com/");
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("document.getElementById('newsletter-email').value='suryasoma@gmi.com'");
        js.executeScript("document.getElementById('newsletter-subscribe-button').click()");
    }

    @Test
    public void verifyScrool(){
        driver.get("https://demo.guru99.com/test/guru99home/");
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,1000)");
    }

    @Test
    public void verifyTablesHeaders(){
        driver.get("https://www.w3schools.com/html/html_tables.asp");
        List<WebElement> heading=driver.findElements(By.xpath("//table[@id='customers']//th"));
        List<String> actualList = new ArrayList<>();
        for (WebElement i : heading) {
            actualList.add(i.getText());
        }
        List<String> expectedList=new ArrayList<>();
        expectedList.add("Company");
        expectedList.add("Contact");
        expectedList.add("Country");
        Assert.assertEquals(actualList,expectedList,"List not matching");
    }

    @Test
    public void verifyValuesInTable() {
        driver.get("https://www.w3schools.com/html/html_tables.asp");
        List<WebElement> rowElement=driver.findElements(By.xpath("//table[@id='customers']//tr"));
        List<String> actualData=new ArrayList<>();
        for(int i=0;i<=rowElement.size();i++){
            List<WebElement> rowValues=driver.findElements(By.xpath("//table[@id='customers']//tr["+i+"]//td"));
            if(rowValues.get(0).getText().equals("Island Trading")){
                for(int j=0;j<rowValues.size();j++){
                    actualData.add(rowValues.get(j).getText());
                }
            }
        }
        List<String> expectedData=new ArrayList<>();
        expectedData.add("Island Trading");
        expectedData.add("Helen Bennett");
        expectedData.add("UK");
    }

    @Test
    public void verifyTable() throws IOException {
        driver.get("https://www.w3schools.com/html/html_tables.asp");
        List<WebElement> rowElement=driver.findElements(By.xpath("//table[@id='customers']//tr"));
        for(int i=2;i<=rowElement.size();i++){
            List<WebElement> rowValues=driver.findElements(By.xpath("//table[@id='customers']//tr["+i+"]//td"));
                for(int j=0;j<rowValues.size();j++){
                    ExcelUtility excel=new ExcelUtility();
                    String actualCell=rowValues.get(j).getText();
                    String expectedCell=excel.table((i-1),j);
                    Assert.assertEquals(actualCell,expectedCell,"Not equal");
                }
        }
    }
    @Test
    public void  verifyDataFromExcel() throws IOException {
        driver.get("https://www.w3schools.com/html/html_tables.asp");
        ExcelUtility excelUtility = new ExcelUtility();
        List<ArrayList<String>> expected = excelUtility.readDatasFromExcel("\\src\\main\\resources\\TestData.xlsx", "Table");
         //System.out.println(expected);
        List<WebElement> rowElement = driver.findElements(By.xpath("//table[@id='customers']//tr"));
        List<WebElement> columnElement = driver.findElements(By.xpath("//table[@id='customers']//tr//td"));
        TableUtility tableUtility=new TableUtility();
        List<ArrayList<String>> actual =tableUtility.getGridData(rowElement,columnElement);
        //System.out.println(actual);
        Assert.assertEquals(actual,expected,"Not equal table");
    }

}

