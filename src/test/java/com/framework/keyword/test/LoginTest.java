package com.framework.keyword.test;

import org.testng.annotations.Test;

import com.freamework.keyword.engine.KeywordEngine;

public class LoginTest {

	public KeywordEngine keywordengine;
	
	@Test
	public void logintest() {
		keywordengine = new KeywordEngine();
		keywordengine.StartExecution("Sheet1");
	}
	
}
