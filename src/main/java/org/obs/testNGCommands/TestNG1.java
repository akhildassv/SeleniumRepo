package org.obs.testNGCommands;

import org.testng.annotations.*;

public class TestNG1 {
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("This is from before Method");
    }
    @BeforeClass
    public void beforeClass(){
        System.out.println("This is from before TestNG1 Class");
    }
    @BeforeTest
    public void beforeTest(){
        System.out.println("This is from before Test");
    }
    @BeforeSuite
    public void beforeSuit(){
        System.out.println("This is from before suit");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("This is from After Method");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("This is from After TestNG1 Class");
    }
    @AfterTest
    public void afterTest(){
        System.out.println("This is from After Test");
    }
    @AfterSuite
    public void afterSuit(){
        System.out.println("This is from after suit");
    }

    @Test
    public void testCase1(){
        System.out.println("Test case 1 executed updated directli by repo");
    }
    @Test
    public void testCase2(){
        System.out.println("Test case 2 executed");
    }


}
