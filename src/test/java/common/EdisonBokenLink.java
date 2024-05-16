package common;

import java.awt.Color;
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

import com.edison.pages.EdsionBrokenlinkPage;

import modules.CommonBean;
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
//		CellStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		CellStyle1.setAlignment(HorizontalAlignment.LEFT);
		CellStyle1.setVerticalAlignment(VerticalAlignment.TOP);
		CellStyle1.setBorderBottom(BorderStyle.THIN);
		CellStyle1.setBorderTop(BorderStyle.THIN);
		CellStyle1.setBorderRight(BorderStyle.THIN);
		CellStyle1.setBorderLeft(BorderStyle.THIN);
		font1.setFontName("Calibri");
		font1.setBold(true);
		font1.setFontHeightInPoints((short) 11);
		CellStyle1.setFont(font1);
		
		// CellStyle2 for comparision status for green
		XSSFCellStyle CellStyle2 = workbook.createCellStyle();
		XSSFFont font2 = workbook.createFont();
		CellStyle2.setFillBackgroundColor(new XSSFColor(Color.white, new DefaultIndexedColorMap()));
		CellStyle2.setVerticalAlignment(VerticalAlignment.CENTER);
		CellStyle2.setAlignment(HorizontalAlignment.CENTER);
		CellStyle2.setBorderBottom(BorderStyle.THIN);
		CellStyle2.setBorderTop(BorderStyle.THIN);
		CellStyle2.setBorderRight(BorderStyle.THIN);
		CellStyle2.setBorderLeft(BorderStyle.THIN);
		font2.setFontName("Calibri");
		// font1.setBold(true);
		font2.setColor(new XSSFColor(Color.green.darker(), new DefaultIndexedColorMap()));
		font2.setFontHeightInPoints((short) 11);
		CellStyle2.setFont(font2);

		// For comparision status red color
		XSSFCellStyle CellStyle3 = workbook.createCellStyle();
		XSSFFont font3 = workbook.createFont();
		CellStyle3.setFillBackgroundColor(new XSSFColor( Color.white, new DefaultIndexedColorMap()));
		CellStyle3.setVerticalAlignment(VerticalAlignment.CENTER);
		CellStyle3.setAlignment(HorizontalAlignment.CENTER);
		CellStyle3.setBorderBottom(BorderStyle.THIN);
		CellStyle3.setBorderTop(BorderStyle.THIN);
		CellStyle3.setBorderRight(BorderStyle.THIN);
		CellStyle3.setBorderLeft(BorderStyle.THIN);
		font3.setFontName("Calibri");
		// font1.setBold(true);
		font3.setColor(new XSSFColor (Color.red, new DefaultIndexedColorMap()));
		font3.setFontHeightInPoints((short) 11);
		CellStyle3.setFont(font3);
		
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
		cell4.setCellValue("URL");
		cell4.setCellStyle(CellStyle);
		spreadsheet.autoSizeColumn(3);
		
		Cell cell5 = row.createCell(4);
		cell5.setCellValue("STATUS");
		cell5.setCellStyle(CellStyle);
		spreadsheet.autoSizeColumn(4);
		
		int rowcount = 1;
		System.out.println(EdsionBrokenlinkPage.brokenlinks.size());
		for (int i = 0; i + 5 <= EdsionBrokenlinkPage.brokenlinks.size(); i = i + 5) {
			Row row2 = spreadsheet.createRow(rowcount);
			
			//Course
			Cell cell6 = row2.createCell(0);
			cell6.setCellStyle(CellStyle1);
			cell6.setCellValue(EdsionBrokenlinkPage.brokenlinks.get(i));
			spreadsheet.autoSizeColumn(0);
			
			//course code
			Cell cell7 = row2.createCell(1);
			cell7.setCellStyle(CellStyle1);
			cell7.setCellValue(EdsionBrokenlinkPage.brokenlinks.get(i+1));
			spreadsheet.autoSizeColumn(1);
			//unit lessin
			Cell cell8 = row2.createCell(2);
			cell8.setCellStyle(CellStyle1);
			cell8.setCellValue(EdsionBrokenlinkPage.brokenlinks.get(i+2));
			spreadsheet.autoSizeColumn(2);
			//url
			Cell cell9 = row2.createCell(3);
			cell9.setCellStyle(CellStyle1);
			cell9.setCellValue(EdsionBrokenlinkPage.brokenlinks.get(i+3));
			spreadsheet.autoSizeColumn(3);
			
			Cell cell10 = row2.createCell(4);
			if(EdsionBrokenlinkPage.brokenlinks.get(i+4).contains("PASS")) {		
				cell10.setCellStyle(CellStyle2);
				cell10.setCellValue(EdsionBrokenlinkPage.brokenlinks.get(i+4));				
			}else {
				
				cell10.setCellStyle(CellStyle3);
				cell10.setCellValue(EdsionBrokenlinkPage.brokenlinks.get(i+4));	
			}
			rowcount++;
			FileOutputStream out = new FileOutputStream(new File(FileName));
			// write operation workbook using file out object
			workbook.write(out);
			out.close();
			
		}
		
		
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
