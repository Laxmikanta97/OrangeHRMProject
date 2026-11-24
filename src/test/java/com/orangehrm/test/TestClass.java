package com.orangehrm.test;

import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.utilities.ExtentManager;

public class TestClass extends BaseClass {
	@Test
	public void testTest() {
		ExtentManager.starttest("DummyTest1 Test");
		String title = getDriver().getTitle();
		ExtentManager.logStep("verifying the title");
		assert title.equals("OrangeHRM2") : "test Failed - Title is not Matching";
		System.out.println("test failed - Intentialnally failing by changing Title text");
		//ExtentManager.logSkip("This case is skipped");
        //to skip the test, uncomment below line
       //throw new SkipException("Skipping the test as part of Testing"); 

	}
}
