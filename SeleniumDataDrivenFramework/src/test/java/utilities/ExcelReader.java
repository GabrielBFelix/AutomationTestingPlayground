package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

// Class responsible for reading excel data
public class ExcelReader {

	// Get data from testdata.xlsx, format it, and return it as a 2d string array
	// Sheet *must* have the same name as the test function for it to get the test
	// data
	@DataProvider(name = "testdata")
	public String[][] getData(Method m) throws EncryptedDocumentException, IOException {
		String excelSheetName = m.getName(); // Get sheet name
		File f = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\testdata.xlsx");
		FileInputStream fis = new FileInputStream(f);
		Workbook wb = WorkbookFactory.create(fis);
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

}
