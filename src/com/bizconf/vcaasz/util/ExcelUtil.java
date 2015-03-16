package com.bizconf.vcaasz.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtil {
	private final static Logger logger=Logger.getLogger(ExcelUtil.class);
	
	public static void main(String[] args) throws Exception {
		
//		FileInputStream in = new FileInputStream(new File("D:"+File.separator+"template_user.xlsx"));
//		List<Object[]> list = getDataByInputStream(in,"template_user.xlsx",2,0);
//		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
//			Object[] objects = (Object[]) iterator.next();
//			for (int i = 0; i < objects.length; i++) {
//				System.out.print(objects[i].toString());
//				System.out.print(" ");
//			}
//			System.out.println();
//		}
//	
		//String filePath="D:/工作日志/2013-02/导入用户模板.xlsx";
		//getDataByExcelFile(filePath,"Sheet1",0,100);
		
		ServletContext sc = null;
		sc.getRealPath("/");
		String siteSign="";
		String excelName = siteSign + ".xlsx";
//		String savePath = getResource("/").getPath();PropertiesFacoty
		String domain = "http://" + siteSign + ".confcloud.cn";
		String httpUrl = domain + "/excel/"+siteSign+"/export";
//		try {
//			
//			
//			ExcelUtil.writeExcel(savePath, exportList, excelTitle, 50000);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		
	}
	
	/**
	 * 
	 * 读取EXCEL文档，并将返回List<Object>
	 * 
	 * */
	
	public static List<Object[]> getDataByExcelFile(String filePathName, String sheetName, int beginRow,int endRow) throws Exception {
		List<Object[]> list = null;
		if(filePathName != null && !"".equals(filePathName) && filePathName.length() > 0){
			logger.info("filePathName---->>>>"+filePathName);
			String extName = FileUtil.getExtNameByPathName(filePathName);
			if (extName != null) {
				extName = extName.toLowerCase();
				int rowsCount = 0;
				int cellCount = 0;
				InputStream inputStream = new FileInputStream(filePathName);
				try {
					if ("xls".equals(extName)) {
						logger.info("ExcelType---->>>>Excel2003");
						if (inputStream != null) {
							HSSFWorkbook wb = new HSSFWorkbook(inputStream);
							HSSFSheet sheet = null;
							HSSFRow row = null;
							HSSFCell cell = null;
							if (wb != null) {
								sheet = wb.getSheet(sheetName);
							}
							if (sheet != null) {
								rowsCount = sheet.getLastRowNum() + 1;
							}
							logger.info("rowsCount---->>>>"+rowsCount);
							if (rowsCount > 0 && rowsCount >= beginRow) {
								list = new ArrayList<Object[]>();
								Object[] rowArr = null;
								int stopRow = rowsCount;
								if (endRow > 0 && rowsCount > endRow) {
									stopRow = endRow;
								}
								StringBuffer eachRowDataBuffer=null;
								for (int ii = beginRow - 1; ii < stopRow; ii++) {
									row = sheet.getRow(ii);
									if (row != null) {
										eachRowDataBuffer=new StringBuffer();
										cellCount = row.getLastCellNum();
										rowArr = new Object[cellCount];
										for (int jj = 0; jj < cellCount; jj++) {
											cell = row.getCell(jj);
											if (cell != null) {
												rowArr[jj] = getHssCellData(cell);
												eachRowDataBuffer.append(rowArr[jj]);
											}
											cell = null;
										}
										list.add(rowArr);
										logger.info("row:"+ii+";  data: "+eachRowDataBuffer.toString());
										rowArr = null;
									}
									row = null;
								}
							}
							sheet = null;
							wb = null;
						}
					}
					if ("xlsx".equals(extName)) {
						logger.info("ExcelType---->>>>Excel2007");
						XSSFWorkbook xwb = new XSSFWorkbook(inputStream);
						XSSFSheet sheet = null;
						XSSFRow row = null;
						XSSFCell cell = null;
						if (xwb != null) {
							sheet = xwb.getSheet(sheetName);
						}
						if (sheet != null) {
							rowsCount = sheet.getLastRowNum() + 1;
						}
						logger.info("rowsCount---->>>>"+rowsCount);
						if (rowsCount > 0 && rowsCount >= beginRow) {
							list = new ArrayList<Object[]>();
							Object[] rowArr = null;
							int stopRow = rowsCount;
							if (endRow > 0 && rowsCount > endRow) {
								stopRow = endRow;
							}
							StringBuffer eachRowDataBuffer=null;
							for (int ii = beginRow - 1; ii < stopRow; ii++) {
//							System.out.println("      "+ ii);
								row = sheet.getRow(ii);
								if (row != null) {
									eachRowDataBuffer=new StringBuffer();
									cellCount = row.getLastCellNum();
									rowArr = new Object[cellCount];
									for (int jj = 0; jj < cellCount; jj++) {
										cell = row.getCell(jj);
										if (cell != null) {
											rowArr[jj] = getXssCellData(cell);
											eachRowDataBuffer.append(rowArr[jj]);
										}
										cell = null;
									}
									list.add(rowArr);
									logger.info("row:"+ii+";  data: "+eachRowDataBuffer.toString());
									rowArr = null;
								}
								row = null;
							}
						}
						sheet = null;
						xwb = null;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{

					inputStream.close();
					inputStream=null;
				}
			}
			extName = null;
		}
		return list;
		
	}
	
	
	/**
	 * 
	 * @param inputStream
	 * @param fileName
	 * @param beginRow
	 * @param endRow
	 * @return
	 * @throws Exception
	 */
	public static List<Object[]> getDataByInputStream(InputStream inputStream, String fileName, int beginRow,int endRow) throws Exception {
		List<Object[]> list = null;
		if(inputStream != null){
			String extName = FileUtil.getExtNameByPathName(fileName);
			if (extName != null) {
				extName = extName.toLowerCase();
				int rowsCount = 0;
				int cellCount = 0;
				try {
					if ("xls".equals(extName)) {
						if (inputStream != null) {
							HSSFWorkbook wb = new HSSFWorkbook(inputStream);
							HSSFSheet sheet = null;
							HSSFRow row = null;
							HSSFCell cell = null;
							if (wb != null) {
								sheet = wb.getSheetAt(0);
							}
							if (sheet != null) {
								rowsCount = sheet.getLastRowNum() + 1;
							}
							if (rowsCount > 0 && rowsCount >= beginRow) {
								list = new ArrayList<Object[]>();
								Object[] rowArr = null;
								int stopRow = rowsCount;
								if (endRow > 0 && rowsCount > endRow) {
									stopRow = endRow;
								}
								StringBuffer eachRowDataBuffer=null;
								for (int ii = beginRow - 1; ii < stopRow; ii++) {
									row = sheet.getRow(ii);
									if (row != null) {
										eachRowDataBuffer=new StringBuffer();
										cellCount = row.getLastCellNum();
										rowArr = new Object[cellCount];
										for (int jj = 0; jj < cellCount; jj++) {
											cell = row.getCell(jj);
											if (cell != null) {
												rowArr[jj] = getHssCellData(cell);
												eachRowDataBuffer.append(rowArr[jj]+"   ");
											}
											cell = null;
										}
										list.add(rowArr);
										logger.info("row:"+ii+";  data: "+eachRowDataBuffer.toString());
										rowArr = null;
									}
									row = null;
								}
							}
							sheet = null;
							wb = null;
						}
					}
					if ("xlsx".equals(extName)) {
						XSSFWorkbook xwb = new XSSFWorkbook(inputStream);
						XSSFSheet sheet = null;
						XSSFRow row = null;
						XSSFCell cell = null;
						if (xwb != null) {
							sheet = xwb.getSheetAt(0);
						}
						if (sheet != null) {
							rowsCount = sheet.getLastRowNum() + 1;
						}
						if (rowsCount > 0 && rowsCount >= beginRow) {
							list = new ArrayList<Object[]>();
							Object[] rowArr = null;
							int stopRow = rowsCount;
							if (endRow > 0 && rowsCount > endRow) {
								stopRow = endRow;
							}
							StringBuffer eachRowDataBuffer=null;
							for (int ii = beginRow - 1; ii < stopRow; ii++) {
								row = sheet.getRow(ii);
								if (row != null) {
									eachRowDataBuffer=new StringBuffer();
									cellCount = row.getLastCellNum();
									rowArr = new Object[cellCount];
									for (int jj = 0; jj < cellCount; jj++) {
										cell = row.getCell(jj);
										if (cell != null) {
											rowArr[jj] = getXssCellData(cell);
											eachRowDataBuffer.append(rowArr[jj]+"   ");
										}
										cell = null;
									}
									list.add(rowArr);

									logger.info("row:"+ii+";  data: "+eachRowDataBuffer.toString());
									rowArr = null;
								}
								row = null;
							}
						}
						sheet = null;
						xwb = null;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					inputStream.close();
					inputStream=null;
				}
			}
			extName = null;
		}
		return list;
	}

	
	/**
	 * 取Excel2003中单元格数据
	 * */
	public static Object getHssCellData(Object cell){
		Object cellData=null;
		if(cell!=null){
			HSSFCell hssCell=(HSSFCell)cell;
			if (hssCell != null) {
				switch (hssCell.getCellType()) {
				case HSSFCell.CELL_TYPE_STRING:
					cellData = hssCell.getStringCellValue();
					if (cellData != null) {
						cellData = String.valueOf(cellData).trim();
					}
					break;
				case HSSFCell.CELL_TYPE_NUMERIC:
					if (HSSFDateUtil.isCellDateFormatted(hssCell)) {
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						cellData = df.format(hssCell.getDateCellValue());
						df = null;
					} else {
						DecimalFormat df = new DecimalFormat("0");
						cellData = df.format(hssCell.getNumericCellValue());
						df = null;
					}
					break;
				case HSSFCell.CELL_TYPE_BLANK:
					cellData = "";
					break;
				case HSSFCell.CELL_TYPE_BOOLEAN:
					cellData = String.valueOf(hssCell.getBooleanCellValue());
					break;
				case HSSFCell.CELL_TYPE_ERROR:
					break;
				}
			}
			hssCell = null;
			
		}
		return cellData;
	}
	

	/**
	 * 取Excel2007中单元格数据
	 * */

	public static Object getXssCellData(Object cell){
		Object cellData=null;
		if(cell!=null){
			XSSFCell xssCell = (XSSFCell) cell;
			if(xssCell != null){
				switch (xssCell.getCellType()) {
				case XSSFCell.CELL_TYPE_STRING:
					cellData = xssCell.getStringCellValue();
					if (cellData != null) {
						cellData = String.valueOf(cellData).trim();
					}
					break;
				case XSSFCell.CELL_TYPE_NUMERIC:
					if (DateUtil.isCellDateFormatted(xssCell)) {
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						cellData = df.format(xssCell.getDateCellValue());
						df = null;
					} else {
						DecimalFormat df = new DecimalFormat("0");
						cellData = df.format(xssCell.getNumericCellValue());
						df = null;
					}
					break;
				case XSSFCell.CELL_TYPE_BLANK:
					cellData = "";
					break;
				case XSSFCell.CELL_TYPE_BOOLEAN:
					cellData = String.valueOf(xssCell.getBooleanCellValue());
					break;
				case XSSFCell.CELL_TYPE_ERROR:
					break;
				default:
					break;
				}
			}
			xssCell = null;
			
		}
		return cellData;
	}

	

	
	
	/**
	 * 取所有的数据 包括Excel2003、Excel2007
	 * */
	
	public static Object getExcelCellData(Object cell){
		Object cellData=null;
		if(cell!=null){
			String className=cell.getClass().getSimpleName();
			if ("XSSFCell".equals(className)) {
				XSSFCell xssCell = (XSSFCell) cell;
				if(xssCell != null){
					switch (xssCell.getCellType()) {
					case XSSFCell.CELL_TYPE_STRING:
						cellData = xssCell.getStringCellValue();
						if (cellData != null) {
							cellData = String.valueOf(cellData).trim();
						}
						break;
					case XSSFCell.CELL_TYPE_NUMERIC:
						if (DateUtil.isCellDateFormatted(xssCell)) {
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							cellData = df.format(xssCell.getDateCellValue());
							df = null;
						} else {
							DecimalFormat df = new DecimalFormat("0");
							cellData = df.format(xssCell.getNumericCellValue());
							df = null;
						}
						break;
					case XSSFCell.CELL_TYPE_BLANK:
						cellData = "";
						break;
					case XSSFCell.CELL_TYPE_BOOLEAN:
						cellData = String.valueOf(xssCell.getBooleanCellValue());
						break;
					case XSSFCell.CELL_TYPE_ERROR:
						break;
					default:
						break;
					}
				}
				xssCell = null;
			}
			if ("HSSFCell".equals(className)) {
				HSSFCell hssCell=(HSSFCell)cell;
				if (hssCell != null) {
					switch (hssCell.getCellType()) {
					case HSSFCell.CELL_TYPE_STRING:
						cellData = hssCell.getStringCellValue();
						if (cellData != null) {
							cellData = String.valueOf(cellData).trim();
						}
						break;
					case HSSFCell.CELL_TYPE_NUMERIC:
						if (HSSFDateUtil.isCellDateFormatted(hssCell)) {
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							cellData = df.format(hssCell.getDateCellValue());
							df = null;
						} else {
							DecimalFormat df = new DecimalFormat("0");
							cellData = df.format(hssCell.getNumericCellValue());
							df = null;
						}
						break;
					case HSSFCell.CELL_TYPE_BLANK:
						cellData = "";
						break;
					case HSSFCell.CELL_TYPE_BOOLEAN:
						cellData = String.valueOf(hssCell.getBooleanCellValue());
						break;
					case HSSFCell.CELL_TYPE_ERROR:
						break;
					}
				}
				hssCell = null;
			}
			className = null;
		}
		return cellData;
	}
	
	
	
	

	/**
	 * 
	 * 创建Excel 2003
	 * 
	 * */
	public static void createExcel(String filePathName, List<Object[]> dataList) throws Exception {
		logger.info("Save filePathName=="+filePathName);

		if (dataList != null && dataList.size() > 0) {
			final int dataSize=dataList.size();
			
			logger.info("Excel  RowCount=="+dataList.size());
			// 建立新HSSFWorkbook对象
			HSSFWorkbook wb = new HSSFWorkbook();
			//创建Sheet对象
			HSSFSheet sheet = wb.createSheet("Sheet1");
			
			Object[] oneData=null;
			for(int ii=0;ii<dataSize;ii++){
				oneData=dataList.get(ii);
				createExcelRow(sheet,oneData, ii);
			}
			File newfile = new File(filePathName);  
			FileOutputStream fileOut =new FileOutputStream(newfile.getAbsolutePath());
			
			wb.write(fileOut);
			fileOut.close();
			wb = null;
			fileOut = null;
		}
	}
	
	/**
	 * 
	 * 获取Excel 操作对象
	 * 
	 * */
	public static HSSFWorkbook createExcelWorkbook(String sheetName,int defaultWith, List<Object[]> dataList){
		if (dataList != null && dataList.size() > 0) {
			final int dataSize=dataList.size();
			logger.info("Excel  RowCount=="+dataList.size());
			// 建立新HSSFWorkbook对象
			HSSFWorkbook wb = new HSSFWorkbook();
			//创建Sheet对象
			HSSFSheet sheet = wb.createSheet(sheetName);
			sheet.setDefaultColumnWidth(defaultWith);
			Object[] oneData=null;
			for(int ii=0;ii<dataSize;ii++){
				oneData=dataList.get(ii);
				createExcelRow(sheet,oneData, ii);
			}
			return wb;
		}
		return null;
	}
	
	public static HSSFWorkbook createExcelWorkbook(String sheetName, List<Object[]> dataList){
		return createExcelWorkbook(sheetName,16, dataList);
	}

	
	/**
	 * 
	 * 创建新行并将数据写入到相应的单元格中
	 * 
	 * */
	
	private static void createExcelRow(HSSFSheet sheet,Object[] rowData,int rowNum){
		if(rowData.length>0){
			//建立新行
			HSSFRow row = sheet.createRow((short)rowNum);
			final int dataLen=rowData.length;
			HSSFCell cell=null;
			String oneCellValue="";
			for(int ii=0;ii<dataLen;ii++){
				cell = row.createCell(ii);
				oneCellValue=String.valueOf(rowData[ii]);
				if(rowData[ii]==null){
					oneCellValue="";
				}
				cell.setCellValue(oneCellValue);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			}
			row=null;
			cell=null;
		}
	}

	

	
	/**
	 * 导出Excel，生成Excel文档,可以生成Excel2003格式的、也可以生成Excel2007格式的
	 * @param filePathName
	 * @param dataList
	 * @throws Exception
	 */
	public static void writeExcel(String filePathName, List<Object[]> dataList, Object[] excelTitle,int maxRows) throws Exception {
		logger.info("Save filePathName==" + filePathName);
		if (dataList != null && dataList.size() > 0) {
			final int dataSize = dataList.size();
			logger.info("Excel  RowCount==" + dataList.size());
			// 建立新HSSFWorkbook对象
			SXSSFWorkbook workbook = new SXSSFWorkbook();
			// HSSFWorkbook wb = new HSSFWorkbook();
			Sheet eachSheet = null;
			Object[] oneData = null;
			int sii = 0;
			int rowNum = 0;
			for (int ii = 0; ii < dataSize; ii++) {
				if (ii % maxRows == 0) {
					// 创建Sheet对象
					sii++;
					eachSheet = workbook.createSheet("Sheet" + sii);
				}
				oneData = dataList.get(ii);
				if (eachSheet != null) {
					rowNum = ii - maxRows * (sii - 1);
					rowNum += 1;
					if (rowNum == 1) {
						writeRow(eachSheet, excelTitle, 0);
					}
					writeRow(eachSheet, oneData, rowNum);
				}

			}
			File newfile = new File(filePathName);
			// 检查路径是否存在，如果不存在则创建新的
			String path = newfile.getParent();
			FileUtil.createDirs(path);

			FileOutputStream fileOut = new FileOutputStream(newfile.getAbsolutePath());
			workbook.write(fileOut);
			fileOut.close();
			workbook = null;
			fileOut = null;
		}

	}
	
	

	/**
	 * 
	 * 创建新行并将数据写入到相应的单元格中
	 * 
	 * */
	
	private static void writeRow(Sheet sheet,Object[] rowData,int rowNum){
		if(rowData.length>0){
			//建立新行
			Row row = sheet.createRow(rowNum);
			final int dataLen=rowData.length;
			Cell cell=null;
			String oneCellValue="";
			for(int ii=0;ii<dataLen;ii++){
				cell = row.createCell(ii);
				oneCellValue=String.valueOf(rowData[ii]);
				if(rowData[ii]==null){
					oneCellValue="";
				}
				cell.setCellValue(oneCellValue);
				cell.setCellType(Cell.CELL_TYPE_STRING);
			}
			row=null;
			cell=null;
		}
	}
	
}
