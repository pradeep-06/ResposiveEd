package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;

import modules.PropertyManager;

public class EdisonBokenLink {
	
	static XSSFWorkbook workbook;
	static PropertyManager prop = new PropertyManager("execution.properties");
	
	public void createSheet(String filepath,String filename) {
	 try {
		 String fileString=filepath+"\\"+filename+".xlsx";
		 FileOutputStream out = new FileOutputStream(new File(fileString));
		
		 workbook = new XSSFWorkbook();
		 createExeclSheet(fileString,"BrokenLink");
		 workbook.write(out);		
		 out.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
		
		
	}
	
	public static void createExeclSheet(String FileName, String SheetName) {
	 try {
		 
		XSSFSheet spreadsheet = workbook.createSheet(SheetName);
		workbook.setForceFormulaRecalculation(false);
		XSSFCellStyle CellStyle = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		System.out.println("Created ExcelSheet");
//		CellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		CellStyle.setAlignment(HorizontalAlignment.CENTER);
		CellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		CellStyle.setBorderBottom(BorderStyle.THIN);
		CellStyle.setBorderTop(BorderStyle.THIN);
		CellStyle.setBorderRight(BorderStyle.THIN);
		CellStyle.setBorderLeft(BorderStyle.THIN);
		font.setFontName("Calibri");
		font.setBold(true);
		font.setFontHeightInPoints((short) 11);
		CellStyle.setFont(font);
		
		XSSFCellStyle CellStyle1 = workbook.createCellStyle();
		XSSFFont font1 = workbook.createFont();
		CellStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		CellStyle1.setAlignment(HorizontalAlignment.CENTER);
		CellStyle1.setVerticalAlignment(VerticalAlignment.CENTER);
		CellStyle1.setBorderBottom(BorderStyle.THICK);
		CellStyle1.setBorderTop(BorderStyle.THICK);
		CellStyle1.setBorderRight(BorderStyle.THICK);
		CellStyle1.setBorderLeft(BorderStyle.THICK);
		font1.setFontName("Calibri");
		font1.setBold(true);
		font1.setFontHeightInPoints((short) 11);
		CellStyle1.setFont(font1);
		
		Row row = spreadsheet.createRow((short) 0);
		Cell cell = row.createCell(0);	
		cell.setCellValue("COURSE");
		cell.setCellStyle(CellStyle);
		spreadsheet.autoSizeColumn(0);
		
		
		Cell cell2 = row.createCell(1);
		cell2.setCellValue("COURSE_CODE");
		cell2.setCellStyle(CellStyle);
		spreadsheet.autoSizeColumn(1);
		
		
		Cell cell3 = row.createCell(2);
		cell3.setCellValue("UNIT_LESSON_PAGE");
		cell3.setCellStyle(CellStyle);
		spreadsheet.autoSizeColumn(2);
		
		
		Cell cell4 = row.createCell(3);
		cell4.setCellValue("SITE_ID");
		cell4.setCellStyle(CellStyle);
		spreadsheet.autoSizeColumn(3);
		
		
		Cell cell5 = row.createCell(4);
		cell5.setCellValue("URL");
		cell5.setCellStyle(CellStyle);
		spreadsheet.autoSizeColumn(4);
		
		
		FileOutputStream out = new FileOutputStream(new File(FileName));
		// write operation workbook using file out object
		workbook.write(out);
		out.close();
		
		
	  } catch (Exception e) {
		e.printStackTrace();
	 }
		
	}
	
	public void findBrokenLinks(List<WebElement> elements) {
	 try {
		for(WebElement element:elements) {
			String url=element.getAttribute("href").trim();
			URL link = new URL(url);
			HttpURLConnection httpURLConnection = (HttpURLConnection) link.openConnection();
			httpURLConnection.setConnectTimeout(3000);
			httpURLConnection.connect();

			if (httpURLConnection.getResponseCode() == 200) {
			System.out.println(url + " - " + httpURLConnection.getResponseMessage());
			} else {
			System.out.println(url + " - " + httpURLConnection.getResponseMessage() + " - " + "is a broken link");
			}
		}
	 } catch (Exception e) {
		e.printStackTrace();
	 }
		
	}

}
