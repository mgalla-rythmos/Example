package com.example.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateCase {

	public void navigateToAddCase(WebDriver driver) {
		try {

			driver.findElement(By.xpath("//*[@id='ext-gen46']")).click();
			driver.findElement(By.xpath("//*[@id='ext-gen53']/div[2]")).click();
			Thread.sleep(10000);

			Actions action = new Actions(driver);
			WebElement menu = driver.findElement(By.id("menuitem_design"));
			action.moveToElement(menu).perform();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='submenu_design']/span[3]")).click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void clickNewButton(WebDriver driver) throws InterruptedException{
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@class='x-btn-text']")).click();
		
		
	}
	
	public void addCaseTitle(WebDriver driver, String title) throws InterruptedException{
		driver.findElement(By.name("title")).sendKeys(title);
		WebElement element=driver.findElement(By.id("ext-comp-1057"));
		element.click();
		element.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		//driver.findElement(By.xpath("//div[35]/div/div[1]")).click();
		//driver.findElement(By.xpath("//div[33]/div/div[1]")).click();
	}
	
	public void addObjective(WebDriver driver, String objective){
		driver.findElement(By.name("objective")).sendKeys(objective);
	}
	
	public void clickSaveButton(WebDriver driver) throws InterruptedException{
		driver.findElement(By.xpath("//td[4]/table/tbody/tr/td[2]/em/button")).click();
		Thread.sleep(10000);
	}
	
	

}
