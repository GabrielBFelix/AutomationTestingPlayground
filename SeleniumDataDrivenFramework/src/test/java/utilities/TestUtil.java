package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;

import base.BaseTest;

public class TestUtil extends BaseTest {

	public static String screenshotPath;
	public static String screenshotName;

	public static void captureScreenshot() {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		Date d = new Date();

		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		try {
			FileUtils.copyFile(scrFile,
					new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenshotName));
		} catch (IOException e) {
			Reporter.log("Failed to capture screenshot");
			e.printStackTrace();
		}

	}

	@DataProvider(name = "testdata")
	public String[][] getData(Method m) {

		String excelSheetName = m.getName(); // Get sheet name
		File f = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\testdata.xlsx");
		Workbook wb = null;
		try {
			FileInputStream fis = new FileInputStream(f);
			wb = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sheet dataSheet = wb.getSheet(excelSheetName);

		int totalRows = dataSheet.getLastRowNum(); // Get number of rows
		Row rowCells = dataSheet.getRow(0);
		int totalCols = rowCells.getLastCellNum(); // Get number of cells

		DataFormatter format = new DataFormatter();

		String testData[][] = new String[totalRows][totalCols];
		// Iterate through testdata sheet (ignoring the first row), format cell values
		// and store them on "testData" 2d array variable
		for (int i = 1; i <= totalRows; i++)
			for (int j = 0; j < totalCols; j++)
				testData[i - 1][j] = format.formatCellValue(dataSheet.getRow(i).getCell(j));

		return testData;

	}

	public static boolean isTestRunnable(String testName, ExcelReader excel) {

		String sheetName = "test_suite";
		int rows = excel.getRowCount(sheetName);

		for (int rNum = 2; rNum <= rows; rNum++) {

			String testCase = excel.getCellData(sheetName, "TCID", rNum);

			if (testCase.equalsIgnoreCase(testName)) {

				String runmode = excel.getCellData(sheetName, "Runmode", rNum);

				if (runmode.equalsIgnoreCase("Y"))
					return true;
				else
					return false;
			}

		}
		return false;
	}

}
