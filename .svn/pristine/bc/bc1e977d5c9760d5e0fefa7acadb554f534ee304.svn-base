package com.hhh.fund.waterwx.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


@SuppressWarnings("rawtypes")
public class ExcelUtil {

	public static void writeToExcelTemplet(String filePath, List<Map> exportDataList,
			OutputStream outputStream) throws IOException {
		FileInputStream fi = null;
		HSSFWorkbook hwb = null;
		HSSFRow row = null;
		HSSFCell cell = null;

		int insertRowNum = 0;

		HashMap<String, String> cellField = new HashMap<String, String>();
		fi = new FileInputStream(filePath);
		hwb = new HSSFWorkbook(fi);
		HSSFSheet sheet = hwb.getSheetAt(0);
		int rows = sheet.getLastRowNum();
		int cols = -1;

		for (int i = 0; i <= rows; i++) {
			row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			cols = row.getLastCellNum();
			for (int j = 0; j < cols; j++) {
				cell = row.getCell(j);
				String value = "";
				if (null != cell) {
					value = cell.getRichStringCellValue().toString();
					if (value.indexOf("#{") != -1) {
						cellField.put("" + j,
								value.substring(value.indexOf("{") + 1, value.length() - 1));
						insertRowNum = i;
					}
				}
			}
		}

		if ((exportDataList != null) && (exportDataList.size() > 0)) {
			Set cellIndex = cellField.keySet();
			for (int k = 0; k < exportDataList.size(); k++) {
				Map record = exportDataList.get(k);
				HSSFRow newRow = sheet.createRow(insertRowNum + k);
				HSSFCellStyle style1 = hwb.createCellStyle();
				style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
				style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
				Iterator iterator = cellIndex.iterator();
				while (iterator.hasNext()) {
					int cellNum = Integer.parseInt(iterator.next().toString());
					String key = cellField.get("" + cellNum);
					String exportData = record.get(key) == null ? "" : record.get(key).toString();
					
					// 判断当前数据是否是数字，是数字则使用double导出，否则使用String形式导出
					if (!exportData.matches("-?([1-9]\\d*|0)")
							&& !exportData.matches("-?([1-9]\\d*\\.\\d*|0\\.\\d*)")) {
						HSSFCell newCell = newRow.createCell(cellNum);
						newCell.setCellStyle(style1);
						newCell.setCellValue(exportData);
					} else {
						HSSFCell newCell = newRow.createCell(cellNum);
						newCell.setCellStyle(style1);
						newCell.setCellValue(Double.valueOf(exportData));
					}
				}
			}
		}
		hwb.write(outputStream);

		// 关闭流
		if (fi != null) {
			fi.close();
		}
	}

	/**
	 * 提取导入的数据列表
	 * 
	 * @param modelFilePath
	 *            数据导入时对应的数据模板文件
	 * @param stream
	 *            用户上传时的流文件
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static List<Map> getDataList(String modelFilePath, InputStream stream)
			throws IOException {
		List<Map> dataList = new ArrayList<Map>();
		FileInputStream fi = null;
		HSSFWorkbook hwb = null;
		HSSFRow row = null;
		HSSFCell cell = null;
		HashMap<String, String> cellField = new HashMap<String, String>();
		int dataRowStart = 0;
		// 获取绑定及对应列位置
		try {
			fi = new FileInputStream(modelFilePath);
			hwb = new HSSFWorkbook(fi);
			HSSFSheet sheet = hwb.getSheetAt(0);
			int rows = sheet.getLastRowNum();
			int cols = -1;
			for (int i = 0; i <= rows; i++) {
				row = sheet.getRow(i);
				if (row == null) {
					continue;
				}
				cols = row.getLastCellNum();
				for (int j = 0; j < cols; j++) {
					cell = row.getCell(j);
					String value = "";
					if (null != cell) {
						value = cell.getRichStringCellValue().toString();
						if (value.indexOf("#{") != -1) {
							// 数据位置
							dataRowStart = i;
							cellField.put("" + j,
									value.substring(value.indexOf("{") + 1, value.length() - 1));
						}
					}
				}
			}
		} catch (Exception e) {

		} finally {
			if (fi != null) {
				fi.close();
			}
		}
		// 根据绑定的字段获取数据列表
		if (cellField.keySet() != null) {
			hwb = new HSSFWorkbook(stream);
			HSSFSheet sheet = hwb.getSheetAt(0);
			int rows = sheet.getLastRowNum();
			int cols = -1;
			for (int i = dataRowStart; i <= rows; i++) {
				row = sheet.getRow(i);
				if (row == null) {
					continue;
				}
				cols = row.getLastCellNum();
				Map rowData = new HashMap();
				// 当前行是否所有列都为空
				boolean isAllNull = false;
				for (int j = 0; j < cols; j++) {
					cell = row.getCell(j);
					String cellValue = "";
					if (null != cell) {
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_NUMERIC:
							if (HSSFDateUtil.isCellDateFormatted(cell)) {
								double d = cell.getNumericCellValue();
								Date date = HSSFDateUtil.getJavaDate(d);
								SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
								cellValue = dformat.format(date);
							} else {
								NumberFormat nf = NumberFormat.getInstance();
								nf.setGroupingUsed(false);// true时的格式：1,234,567,890
								cellValue = nf.format(cell.getNumericCellValue());// 数值类型的数据为double，所以需要转换一下
							}
							break;
						case HSSFCell.CELL_TYPE_STRING:
							cellValue = cell.getStringCellValue();
							break;
						case HSSFCell.CELL_TYPE_BOOLEAN:
							cellValue = String.valueOf(cell.getBooleanCellValue());
							break;
						case HSSFCell.CELL_TYPE_FORMULA:
							cellValue = String.valueOf(cell.getCellFormula());
							break;

						default:
							cellValue = "";
							break;
						}
						// 获取对应绑定字段（包含表名标识）
						String fullField = cellField.get("" + j) == null ? "" : cellField.get(
								"" + j).toString();
						if (!"".equals(fullField)) {
							rowData.put(fullField, cellValue);
						}
						if (!"".equals(cellValue)) {
							isAllNull = true;
						}
					}
				}
				if (isAllNull) {
					dataList.add(rowData);
				}
				isAllNull = false;
			}
		}
		return dataList;

	}
	
	
	public static void main(String[] args) {
		try{
			String fileName = "D://book.xls";
	        File file=new File(fileName);

	        if (file.exists()) {
	        	InputStream inputStream = new FileInputStream(file);
	        }
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
