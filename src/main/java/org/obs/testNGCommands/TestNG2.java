package org.obs.testNGCommands;

import org.testng.annotations.*;

public class TestNG2 {
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("This is from before Method");
    }
    @BeforeClass
    public void beforeClass(){
        System.out.println("This is from before TestNG2 Class");
    }
    @AfterMethod
    public void afterMethod(){
        System.out.println("This is from After Method");
    }
    @AfterClass
    public void afterClass(){
        System.out.println("This is from After TestNG2 Class");
    }

    @Test(priority = 3)
    public void testCase3(){
        System.out.println("Test case 3 executed");
    }
    @Test(priority = 4)
    public void testCase4(){
        System.out.println("Test case 4 executed");
    }

}
