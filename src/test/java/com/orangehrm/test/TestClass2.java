package com.orangehrm.test;

import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.utilities.ExtentManager;

public class TestClass2 extends BaseClass {
	@Test
	public void testTest() {
		//ExtentManager.starttest("DummyTest2 Test"); --This has been implemented in TestListener
		String title = getDriver().getTitle();
		ExtentManager.logStep("verifying the title");
		assert title.equals("OrangeHRM2") : "test Failed - Title is not Matching";
		System.out.println("test passed - Title is matching");
		ExtentManager.logStep("Validation Successful");

	}
}
