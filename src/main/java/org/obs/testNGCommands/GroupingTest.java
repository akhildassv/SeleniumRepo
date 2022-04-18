package org.obs.testNGCommands;

import org.testng.annotations.*;

public class GroupingTest {
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
    @Test(groups = {"sanity"})
    public void testCase1()
    {
        System.out.println("Test case 1 executed");
    }
    @Test(groups = {"sanity"})
    public void testCase2()
    {
        System.out.println("Test case 2 executed");
    }
    @Test(groups = {"sanity","smoke"})
    public void testCase3()
    {
        System.out.println("Test case 3 executed");
    }
    @Test(groups = {"smoke"})
    public void testCase4()
    {
        System.out.println("Test case 4 executed");
    }
}
