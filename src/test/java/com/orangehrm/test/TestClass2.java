package com.orangehrm.test;

import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;

public class TestClass2 extends BaseClass {
	@Test
	public void testTest() {
		String title = driver.getTitle();
		assert title.equals("OrangeHRM") : "test Failed - Title is not Matching";
		System.out.println("test passed - Title is matching");

	}
}
