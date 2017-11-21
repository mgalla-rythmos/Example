package com.example.example;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Methods {
	public static WebDriver driver;
	public static CreateCase cc = new CreateCase();
	public static String sheetName = "Rewards";

	public static void logIn() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
		driver = new ChromeDriver();
		driver.navigate().to("http://192.168.2.250/");
		System.out.println("Navigating to Testia Taraantula");
		driver.manage().window().maximize();
		driver.findElement(By.id("login")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("admin");
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@class='x-btn-center'] ")).click();
	}
	public void readData(int start, int end, Sheet sheet, String moduleName) throws InterruptedException {
		for (int i = start; i < end; i++) {

			String description = sheet.getCell(2, i).getContents();
			String objective = sheet.getCell(3, i).getContents();
			cc.clickNewButton(driver);
			cc.addCaseTitle(driver, sheetName + "-" + moduleName + "-" + description);
			if (objective == "") {
				cc.addObjective(driver, description);
			} else {
				cc.addObjective(driver, objective);
			}
			Thread.sleep(1000);
			cc.clickSaveButton(driver);

			System.out.println("============== DONE ========================");
		}
		// System.err.println("========================");
	}
	public Map<String, String> getStartEndRows(String data, Sheet sheet, String startRowNum) {
		Map<String, String> map = new HashMap<>();
		try {

			int startRow = Integer.valueOf(startRowNum);
			int rows = sheet.getRows();
			System.out.println("ROWS : " + rows);
			for (int i = startRow; i < rows; i++) {
				String tm = sheet.getCell(0, i).getContents();
				System.out.println("module Name :" + tm);
				if (tm.equalsIgnoreCase(data)) {
					map.put("start", String.valueOf(i));

				}
				if (tm != "" && !tm.equalsIgnoreCase(data)) {
					map.put("data", tm);
					map.put("end", String.valueOf(i));
					return map;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) throws NumberFormatException, InterruptedException {
		String filePath = System.getProperty("user.dir") + "/data/RCX_Members.xls";
		Workbook workbook;
		try {
			workbook = Workbook.getWorkbook(new File(filePath));
			Sheet sheet1 = workbook.getSheet(sheetName);

			System.out.println(
					"Sub Modules	| Sno	| Scenarios	| Description	| Category	| Status	| Comments	| Build 1.1.0	| Manual Test	| UI Automation	| API | Automation |");
			System.out.println("=================== " + "Add rewards ============================");

			try {
				logIn();
				cc.navigateToAddCase(driver);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Map<String, String> mapper=new HashMap();
			for(int i=0;i<8;i++){
				if(i==0){
				 mapper = new Methods().getStartEndRows("Add rewards", sheet1, "3");
				System.out.println(mapper);
				new Methods().readData(Integer.valueOf(mapper.get("start")), Integer.valueOf(mapper.get("end")), sheet1,
						"Add rewards");
				System.out.println(mapper);
				}
				else{
					System.out.println("=================== " + mapper.get("data") + " ============================");
					String data = mapper.get("data");
					mapper = new Methods().getStartEndRows(mapper.get("data"), sheet1, mapper.get("end"));
					new Methods().readData(Integer.valueOf(mapper.get("start")), Integer.valueOf(mapper.get("end")), sheet1,
							data);
					System.out.println(mapper);
				}
			}
		}
			/*Map<String, String> mapper = new Methods().getStartEndRows("Add Members", sheet1, "3");
			System.out.println(mapper);
			new Methods().readData(Integer.valueOf(mapper.get("start")), Integer.valueOf(mapper.get("end")), sheet1,
					"Add Members");
			System.out.println(mapper);

			System.out.println("=================== " + mapper.get("data") + " ============================");
			String data = mapper.get("data");
			mapper = new Methods().getStartEndRows(mapper.get("data"), sheet1, mapper.get("end"));
			new Methods().readData(Integer.valueOf(mapper.get("start")), Integer.valueOf(mapper.get("end")), sheet1,
					data);
			System.out.println(mapper);

			data = mapper.get("data");
			mapper = new Methods().getStartEndRows(mapper.get("data"), sheet1, mapper.get("end"));
			new Methods().readData(Integer.valueOf(mapper.get("start")), Integer.valueOf(mapper.get("end")), sheet1,
					data);
			System.out.println(mapper);

			data = mapper.get("data");
			mapper = new Methods().getStartEndRows(mapper.get("data"), sheet1, mapper.get("end"));
			new Methods().readData(Integer.valueOf(mapper.get("start")), Integer.valueOf(mapper.get("end")), sheet1,
					mapper.get("data"));
			System.out.println(mapper);

			data = mapper.get("data");
			mapper = new Methods().getStartEndRows(mapper.get("data"), sheet1, mapper.get("end"));
			new Methods().readData(Integer.valueOf(mapper.get("start")), Integer.valueOf(mapper.get("end")), sheet1,
					mapper.get("data"));
			System.out.println(mapper);

			data = mapper.get("data");
			mapper = new Methods().getStartEndRows(mapper.get("data"), sheet1, mapper.get("end"));
			new Methods().readData(Integer.valueOf(mapper.get("start")), Integer.valueOf(mapper.get("end")), sheet1,
					mapper.get("data"));
			System.out.println(mapper);

			data = mapper.get("data");
			mapper = new Methods().getStartEndRows(mapper.get("data"), sheet1, mapper.get("end"));
			new Methods().readData(Integer.valueOf(mapper.get("start")), Integer.valueOf(mapper.get("end")), sheet1,
					mapper.get("data"));
			System.out.println(mapper);

			data = mapper.get("data");
			mapper = new Methods().getStartEndRows(mapper.get("data"), sheet1, mapper.get("end"));
			new Methods().readData(Integer.valueOf(mapper.get("start")), Integer.valueOf(mapper.get("end")), sheet1,
					mapper.get("data"));
			System.out.println(mapper);

		}*/ catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
