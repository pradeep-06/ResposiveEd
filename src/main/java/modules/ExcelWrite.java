package modules;

import static org.testng.Assert.expectThrows;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.security.PublicKey;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.IndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import com.common.Global;

//import modules.$missing$;

public class ExcelWrite {

	static PropertyManager prop = new PropertyManager("execution.properties");
	static String ResultFileName = "";
	static XSSFWorkbook workbook;

	String filename = "";

	public static void createNewResultSheet(String Appname) throws IOException {
		String fileName = "";
		fileName = CommonBean.Appname;
		System.out.println("Created excel" + Appname);
		System.out.println(prop.getProperty("result.file.path"));
		ResultFileName = prop.getProperty("result.file.path") + fileName + ".xlsx";
		System.out.println("Resultfile : " + ResultFileName);
		FileOutputStream out = new FileOutputStream(new File(ResultFileName));
		// write operation workbook using file out object
		workbook = new XSSFWorkbook();
		
//		CreatenewExcelFile(ResultFileName);
//		CreatenewExcelSheet2(ResultFileName, "APISummary");
		CreatenewExcelSheet1(ResultFileName, "UISummary");
		workbook.write(out);		
		out.close();
		
		System.out.println("Total pass API " + CommonBean.TotalpassAPI);
		System.out.println("Total fail APi" + CommonBean.TotalfailAPI);
		System.out.println("Total cases API" + CommonBean.testcasecountAPI);
		System.out.println("Total pass UI" + CommonBean.TotalpassUI);
		System.out.println("Total fail UI" + CommonBean.TotalfailUI);
		System.out.println("Total cases UI" + CommonBean.testcasecountUI);
		System.out.println("Completed writing in excel");

		System.out.println(ResultFileName);

	}

	public static void CreatenewExcelFile(String FileName) {
		// Create Blank Workbook
		
		// Create filesystem using specific name
		try {
			FileOutputStream out = new FileOutputStream(new File(FileName));
			// write operation workbook using file out object
			workbook = new XSSFWorkbook();
			XSSFSheet sheet =workbook.createSheet();
//			workbook.write(out);		
//			out.close();
			System.out.println("Excel created");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public static void CreatenewExcelSheet2(String FileName, String SheetName) throws IOException {
		System.out.println("Write in API sheet");
		FileInputStream fis = new FileInputStream(new File(FileName));
		// Create Blank Workbook
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		// Create a blank spreadsheet with proper sheet name
		XSSFSheet spreadsheet = workbook.createSheet(SheetName);
		workbook.setForceFormulaRecalculation(false);
		XSSFCellStyle CellStyle = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();

		// Header blue back ground
		CellStyle.setFillForegroundColor(new XSSFColor((IndexedColorMap) new Color(0, 112, 192)));
		CellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		CellStyle.setAlignment(HorizontalAlignment.CENTER);
		CellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		CellStyle.setBorderBottom(BorderStyle.THICK);
		CellStyle.setBorderTop(BorderStyle.THICK);
		CellStyle.setBorderRight(BorderStyle.THICK);
		CellStyle.setBorderLeft(BorderStyle.THICK);
		font.setFontName("Calibri");
		font.setBold(true);

		// font.setFontHeightInPoints((short) 11);
		font.setColor(new XSSFColor((IndexedColorMap) new Color(255, 255, 255)));
		font.setFontHeightInPoints((short) 11);
		CellStyle.setFont(font);
		XSSFCellStyle CellStyle1 = workbook.createCellStyle();
		XSSFFont font1 = workbook.createFont();

		// normal
		CellStyle1.setVerticalAlignment(VerticalAlignment.CENTER);
		CellStyle1.setBorderBottom(BorderStyle.THIN);
		CellStyle1.setBorderTop(BorderStyle.THIN);
		CellStyle1.setBorderRight(BorderStyle.THIN);
		CellStyle1.setBorderLeft(BorderStyle.THIN);
		font1.setFontName("Calibri");
		// font1.setBold(true);
		font1.setFontHeightInPoints((short) 11);
		CellStyle1.setFont(font1);

		// CellStyle2 for comparision status for green
		XSSFCellStyle CellStyle2 = workbook.createCellStyle();
		XSSFFont font2 = workbook.createFont();
		CellStyle2.setFillBackgroundColor(new XSSFColor((IndexedColorMap) Color.white));
		CellStyle2.setVerticalAlignment(VerticalAlignment.CENTER);
		CellStyle2.setAlignment(HorizontalAlignment.CENTER);
		CellStyle2.setBorderBottom(BorderStyle.THIN);
		CellStyle2.setBorderTop(BorderStyle.THIN);
		CellStyle2.setBorderRight(BorderStyle.THIN);
		CellStyle2.setBorderLeft(BorderStyle.THIN);
		font2.setFontName("Calibri");
		// font1.setBold(true);
		font2.setColor(new XSSFColor((IndexedColorMap) Color.green.darker()));
		font2.setFontHeightInPoints((short) 11);
		CellStyle2.setFont(font2);

		// For comparision status red color
		XSSFCellStyle CellStyle3 = workbook.createCellStyle();
		XSSFFont font3 = workbook.createFont();
		CellStyle3.setFillBackgroundColor(new XSSFColor((IndexedColorMap) Color.white));
		CellStyle3.setVerticalAlignment(VerticalAlignment.CENTER);
		CellStyle3.setAlignment(HorizontalAlignment.CENTER);
		CellStyle3.setBorderBottom(BorderStyle.THIN);
		CellStyle3.setBorderTop(BorderStyle.THIN);
		CellStyle3.setBorderRight(BorderStyle.THIN);
		CellStyle3.setBorderLeft(BorderStyle.THIN);
		font3.setFontName("Calibri");
		// font1.setBold(true);
		font3.setColor(new XSSFColor((IndexedColorMap) Color.red));
		font3.setFontHeightInPoints((short) 11);
		CellStyle3.setFont(font3);

		// Set Color Gold
		XSSFCellStyle CellStyle4 = workbook.createCellStyle();
		XSSFFont font4 = workbook.createFont();
//		CellStyle4.setFillForegroundColor(new XSSFColor((IndexedColorMap) new Color(251, 181, 26)));	
		CellStyle4.setFillForegroundColor(new XSSFColor((IndexedColorMap) new Color(0,0,255)));
		CellStyle4.setAlignment(HorizontalAlignment.CENTER);
		CellStyle4.setVerticalAlignment(VerticalAlignment.CENTER);
		CellStyle4.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		CellStyle4.setBorderBottom(BorderStyle.THIN);
		CellStyle4.setBorderTop(BorderStyle.THIN);
		CellStyle4.setBorderRight(BorderStyle.THIN);
		CellStyle4.setBorderLeft(BorderStyle.THIN);
		font4.setFontName("Calibri");
		font4.setBold(true);
		font4.setFontHeightInPoints((short) 14);
		CellStyle4.setFont(font4);

		System.out.println("Create in excel summary sheet");
		Row roww = spreadsheet.createRow((short) 0);
		Cell ssCell = roww.createCell(0);
		spreadsheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
		ssCell.setCellValue("TestCase Summary");
		ssCell.setCellStyle(CellStyle4);

		Row row = spreadsheet.createRow((short) 1);
		Cell cell = row.createCell(0);
		cell.setCellValue("Sl_No");
		cell.setCellStyle(CellStyle);
		Cell cell1 = row.createCell(1);
		cell1.setCellValue("TestcaseName");
		cell1.setCellStyle(CellStyle);
		Cell cell2 = row.createCell(2);
		cell2.setCellValue("ValidationPoints");
		cell2.setCellStyle(CellStyle);
		Cell cell3 = row.createCell(3);
		cell3.setCellValue("Passcount");
		cell3.setCellStyle(CellStyle);
		Cell cell4 = row.createCell(4);
		cell4.setCellValue("Failcount");
		cell4.setCellStyle(CellStyle);
		Cell cell11 = row.createCell(5);
		cell11.setCellValue("Comparision Status");
		cell11.setCellStyle(CellStyle);

		int rowcount = 0;

		for (int i = 0; i + 4 <= CommonBean.collectionAPI.size(); i = i + 4) {
			Row row2 = spreadsheet.createRow(rowcount);
			Cell cell9 = row2.createCell(0);
			cell9.setCellStyle(CellStyle1);
			cell9.setCellValue(rowcount - 1);

			Cell cell5 = row2.createCell(1);
			cell5.setCellStyle(CellStyle1);
			cell5.setCellValue(CommonBean.collectionAPI.get(i));
			spreadsheet.autoSizeColumn(1);

			Cell cell6 = row2.createCell(2);
			cell6.setCellStyle(CellStyle1);
			cell6.setCellValue(CommonBean.collectionAPI.get(i + 1));
			spreadsheet.autoSizeColumn(2);

			Cell cell7 = row2.createCell(3);
			cell7.setCellStyle(CellStyle1);
			cell7.setCellValue(CommonBean.collectionAPI.get(i + 2));
			spreadsheet.autoSizeColumn(3);

			Cell cell8 = row2.createCell(4);
			cell8.setCellStyle(CellStyle1);
			cell8.setCellValue(CommonBean.collectionAPI.get(i + 3));
			spreadsheet.autoSizeColumn(4);

			Cell cell10 = row2.createCell(5);

			if (Integer.parseInt(CommonBean.collectionAPI.get(i + 3)) >= 1) {
				cell10.setCellStyle(CellStyle3);
				cell10.setCellValue("FAILED");
				CommonBean.TotalfailAPI++;
			} else {
				cell10.setCellValue("PASSED");
				cell10.setCellStyle(CellStyle2);
				CommonBean.TotalpassAPI++;
			}
			spreadsheet.autoSizeColumn(5);

			rowcount++;
			System.out.println("Total pass " + CommonBean.TotalpassAPI);
			System.out.println("Total fail " + CommonBean.TotalfailAPI);
			System.out.println("Total cases " + CommonBean.testcasecountAPI);

			FileOutputStream out = new FileOutputStream(new File(FileName));
			// write operation workbook using file out object
			workbook.write(out);
			out.close();

		}

	}

	public static void CreatenewExcelSheet1(String FileName, String SheetName) throws IOException {
		try {
			
			System.out.println("Write in UI sheet");
			FileInputStream fis = new FileInputStream(new File(FileName));
			// Create Blank Workbook
//			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			// Create blank spreadsheet with proper sheet name
			XSSFSheet spreadsheet = workbook.createSheet(SheetName);
			workbook.setForceFormulaRecalculation(false);
			XSSFCellStyle CellStyle = workbook.createCellStyle();
			XSSFFont font = workbook.createFont();

			// Header blue back ground
			System.out.println("Created ExcelSheet");
			//new XSSFColor(new java.awt.Color(255, 255, 0), new DefaultIndexedColorMap())
			//new XSSFColor((IndexedColorMap) new Color(0, 112, 192))
			//(IndexedColorMap) new java.awt.Color(0, 112, 192)
//			CellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(255, 255, 0), new DefaultIndexedColorMap()));rgb(173, 216, 230)
			CellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(173, 216, 230), new DefaultIndexedColorMap()));
			CellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			CellStyle.setAlignment(HorizontalAlignment.CENTER);
			CellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			CellStyle.setBorderBottom(BorderStyle.THICK);
			CellStyle.setBorderTop(BorderStyle.THICK);
			CellStyle.setBorderRight(BorderStyle.THICK);
			CellStyle.setBorderLeft(BorderStyle.THICK);
			font.setFontName("Calibri");
		
			font.setBold(true);

			// font.setFontHeightInPoints((short) 11);
			font.setColor(new XSSFColor(new java.awt.Color(255, 255, 255), new DefaultIndexedColorMap()));
			font.setFontHeightInPoints((short) 11);
			CellStyle.setFont(font);
			XSSFCellStyle CellStyle1 = workbook.createCellStyle();
			XSSFFont font1 = workbook.createFont();

			// normal
			CellStyle1.setVerticalAlignment(VerticalAlignment.CENTER);
			CellStyle1.setBorderBottom(BorderStyle.THIN);
			CellStyle1.setBorderTop(BorderStyle.THIN);
			CellStyle1.setBorderRight(BorderStyle.THIN);
			CellStyle1.setBorderLeft(BorderStyle.THIN);
			font1.setFontName("Calibri");
			// font1.setBold(true);
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

			// Set Color Gold
			XSSFCellStyle CellStyle4 = workbook.createCellStyle();
			XSSFFont font4 = workbook.createFont();
//			CellStyle4.setFillForegroundColor(new XSSFColor(new java.awt.Color(251, 181, 26), new DefaultIndexedColorMap()));	
			CellStyle4.setFillForegroundColor(new XSSFColor(new java.awt.Color(0,0,139), new DefaultIndexedColorMap()));
			CellStyle4.setAlignment(HorizontalAlignment.CENTER);
			CellStyle4.setVerticalAlignment(VerticalAlignment.CENTER);
			CellStyle4.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			CellStyle4.setBorderBottom(BorderStyle.THIN);
			CellStyle4.setBorderTop(BorderStyle.THIN);
			CellStyle4.setBorderRight(BorderStyle.THIN);
			CellStyle4.setBorderLeft(BorderStyle.THIN);
			font4.setFontName("Calibri");
			font4.setBold(true);
			font4.setColor(new XSSFColor(Color.WHITE.darker(), new DefaultIndexedColorMap()));
			font4.setFontHeightInPoints((short) 14);
			CellStyle4.setFont(font4);

			// Create file system using specific name
			System.out.println("Create in excel summary sheet");
			Row roww = spreadsheet.createRow((short) 0);
			Cell ssCell = roww.createCell(0);
			spreadsheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
			ssCell.setCellValue("TestCase Summary");
			ssCell.setCellStyle(CellStyle4);

			Row row = spreadsheet.createRow((short) 1);
			Cell cell = row.createCell(0);
			cell.setCellValue("Sl_No");
			cell.setCellStyle(CellStyle);
			Cell cell1 = row.createCell(1);
			cell1.setCellValue("TestcaseName");
			cell1.setCellStyle(CellStyle);
			Cell cell2 = row.createCell(2);
			cell2.setCellValue("ValidationPoints");
			cell2.setCellStyle(CellStyle);
			Cell cell3 = row.createCell(3);
			cell3.setCellValue("Passcount");
			cell3.setCellStyle(CellStyle);
			Cell cell4 = row.createCell(4);
			cell4.setCellValue("Failcount");
			cell4.setCellStyle(CellStyle);
			Cell cell11 = row.createCell(5);
			cell11.setCellValue("Comparision Status");
			cell11.setCellStyle(CellStyle);

			int rowcount = 2;

			for (int i = 0; i + 4 <= CommonBean.collection.size(); i = i + 4) {
				Row row2 = spreadsheet.createRow(rowcount);

				Cell cell9 = row2.createCell(0);
				cell9.setCellStyle(CellStyle1);
				cell9.setCellValue(rowcount - 1);

				Cell cell5 = row2.createCell(1);
				cell5.setCellStyle(CellStyle1);
				cell5.setCellValue(CommonBean.collection.get(i));
				spreadsheet.autoSizeColumn(1);

				Cell cell6 = row2.createCell(2);
				cell6.setCellStyle(CellStyle1);
				cell6.setCellValue(CommonBean.collection.get(i + 1));
				spreadsheet.autoSizeColumn(2);

				Cell cell7 = row2.createCell(3);
				cell7.setCellStyle(CellStyle1);
				cell7.setCellValue(CommonBean.collection.get(i + 2));
				spreadsheet.autoSizeColumn(3);

				Cell cell8 = row2.createCell(4);
				cell8.setCellStyle(CellStyle1);
				cell8.setCellValue(CommonBean.collection.get(i + 3));
				spreadsheet.autoSizeColumn(4);

				Cell cell10 = row2.createCell(5);

				if (Integer.parseInt(CommonBean.collection.get(i + 3)) >= 1) {
					cell10.setCellStyle(CellStyle3);
					cell10.setCellValue("FAILED");
					CommonBean.TotalfailUI++;
				} else {
					cell10.setCellValue("PASSED");
					cell10.setCellStyle(CellStyle2);
					CommonBean.TotalpassUI++;
				}
				spreadsheet.autoSizeColumn(5);

				rowcount++;
				FileOutputStream out = new FileOutputStream(new File(FileName));
				// write operation workbook using file out object
				workbook.write(out);
				out.close();
			}
			System.out.println("Total pass : " + CommonBean.TotalpassUI);
			System.out.println("Total fail : " + CommonBean.TotalfailUI);
			System.out.println("Total cases: " + CommonBean.testcasecountUI);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}


	public static File getLastModifiedFile(String path) {
		File lastModifiedFile = null;
		try {
			
		
		File dir = new File(path);
		File[] files = dir.listFiles();
		if (files == null || files.length == 0) {
			return null;
		}
		 lastModifiedFile = files[0];
		for (int i = 0; i < files.length; i++) {
			if (lastModifiedFile.lastModified() < files[i].lastModified()) {
				lastModifiedFile = files[i];
			}

		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lastModifiedFile;


	}

	public static String getCurrentGitProject() {

		String project = "";
		if (System.getenv("CI_JOB_ID") == null) {// if running locally do not use ciToolName becuase it has been
													// initialized yet
			try {
				Process process = Runtime.getRuntime().exec("git config --get remote.origin.url");
				process.waitFor();
				BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
				project = reader.readLine();
				project = project.substring(project.lastIndexOf("/") + 1);
				project = project.replace("git", "");

				process = Runtime.getRuntime().exec("git rev-parse --abbrev-ref HEAD");
				process.waitFor();
				reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String branch = reader.readLine();

				Global.ciCommitBranch = branch;
				return project;
			} catch (Exception e) {
				project = System.getProperty("user.dir");
				if (e.getMessage().contains("Cannot run program \"git\"")) {
					System.out.println("getCurrentGitProject info,Please ensure git is innstalled on machine"
							+ e.getMessage().toString());
				} else {
					System.out.println(
							"getCurrentGitProject info,Unable to get gTI Project detaols" + e.getMessage().toString());

				}
			}

		}
		return project;
	}
}
