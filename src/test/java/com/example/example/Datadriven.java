package com.example.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Datadriven {
	public static WebDriver driver;

	public void logIn() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
		driver = new ChromeDriver();
		driver.navigate().to("http://192.168.2.250/");
		System.out.println("Navigating to Testia Taraantula");
		driver.manage().window().maximize();
		driver.findElement(By.id("login")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("admin");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@class='x-btn-center'] ")).click();
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id='ext-gen46']")).click();
		driver.findElement(By.xpath("//*[@id='ext-gen53']/div[4]")).click();
		Thread.sleep(10000);
		Actions action = new Actions(driver);
		WebElement menu = driver.findElement(By.id("menuitem_design"));
		action.moveToElement(menu).perform();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//*[@id='submenu_design']/span[3]")).click();
		Thread.sleep(10000);
	}

	public void dataDriven() throws InterruptedException {
		InputStream XlsxFileToRead = null;
		XSSFWorkbook workbook = null;
		try {
			XlsxFileToRead = new FileInputStream("D:/Sample.xlsx");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			workbook = new XSSFWorkbook(XlsxFileToRead);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheetAt(0);
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		try {
			System.out.println("rowCount----" + rowCount);

			for (int i = 3; i < rowCount; i++) {
				Row row = sheet.getRow(i);
				int j = 2;
				int j1 = 0;
				driver.findElement(By.xpath("//*[@class='x-btn-text']")).click();
				driver.findElement(By.name("title")).sendKeys(row.getCell(j1).getStringCellValue());
				driver.findElement(By.name("title")).sendKeys("-");
				driver.findElement(By.name("title")).sendKeys(row.getCell(j).getStringCellValue());
				driver.findElement(By.xpath("//div[5]/div/div/img")).click();
				driver.findElement(By.xpath("//div[31]/div/div[1]")).click();
				driver.findElement(By.name("objective")).sendKeys(row.getCell(j).getStringCellValue());
				driver.findElement(By.xpath("//td[4]/table/tbody/tr/td[2]/em/button")).click();
				Thread.sleep(10000);
				System.out.println("First Sheet Test Cases are enrolling");
			}

			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.close();
		System.exit(0);
	}
}