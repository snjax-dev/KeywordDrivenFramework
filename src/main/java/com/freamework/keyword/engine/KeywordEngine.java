package com.freamework.keyword.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.framework.keyword.base.Base;


public class KeywordEngine {

	public WebDriver driver;
	public Properties prop;
	public static Workbook book;
	public static Sheet sheet;
	public Base base;
		
	public void StartExecution(String sheetname) {
		
		String locatorName=null;
		String locatorValue=null;
		FileInputStream file = null;
		
		
		try {
			file = new FileInputStream("C:\\SNJ\\kdfandgit\\src\\main\\java\\com\\framework\\keyword\\inputdata\\KeywordDrivenFramework.xlsx");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			book = WorkbookFactory.create(file);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sheet = book.getSheet(sheetname);
		int k = 0;
		for (int i=0; i<sheet.getLastRowNum(); i++) {
		try {
			String locatorColValue = sheet.getRow(i+1).getCell(k+1).toString().trim();
				if(!locatorColValue.equalsIgnoreCase("NA")) {
				locatorName = locatorColValue.split("=")[0].trim();
				locatorValue = locatorColValue.split("=")[1].trim();	
				}
			String keyword = sheet.getRow(i+1).getCell(k+2).toString().trim();
			String value = sheet.getRow(i+1).getCell(k+3).toString().trim();

			switch (keyword) {
			case "open browser":
				base = new Base();
				base.init_properties();
				if (value.isEmpty() || value.equals("NA")) {
					driver = base.init_driver(prop.getProperty("browser"));
				}else {
					driver = base.init_driver(value);
				}
				break;
				
			case "enter url":
				if (value.isEmpty() || value.equals("NA")) {
					driver.get(prop.getProperty("url"));
				}else {
					driver.get(value);
				}
				break;
				
			case "quit":
				driver.close();
				break;
				
			default:
				break;	
			}
			
			switch(locatorName) {
			
			case "id":
			WebElement element = driver.findElement(By.id(locatorValue));
			if(keyword.equalsIgnoreCase("sendkeys")) {
				element.sendKeys(value);
			}else if(keyword.equalsIgnoreCase("click")) {
				element.click();
			}
			locatorName = null;
			break;
			
			case "xpath":
				element = driver.findElement(By.xpath(locatorValue));
				element.sendKeys(value);
				locatorName = null;
				break;
				
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		
	}
}
