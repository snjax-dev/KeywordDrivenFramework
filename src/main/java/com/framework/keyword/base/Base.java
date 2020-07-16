package com.framework.keyword.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {

	public WebDriver driver;
	public Properties prop;
	
	public WebDriver init_driver(String browsername) {
		if(browsername.equals("chrome")) {
		System.setProperty("webdriver.chrome.driver", "H:\\chromedriver.exe");
		driver = new ChromeDriver();
		}
		return driver;
	}
	
	public Properties init_properties() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("C:\\SNJ\\kdfandgit\\src\\main\\java\\com\\framework\\keyword\\config\\config.properties");
		prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
}
