package com.orangehrm.test;

import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;

public class TestClass extends BaseClass {
	@Test
	public void testTest() {
		String title = getDriver().getTitle();
		assert title.equals("OrangeHRM2") : "test Failed - Title is not Matching";
		System.out.println("test passed - Title is matching");

	}
}
