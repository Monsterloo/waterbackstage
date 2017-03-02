package com.hhh.fund.waterwx.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class ExcelOperator {

   public List getBeanFromExcel(InputStream in,String filename) throws FileNotFoundException, IOException{ 
	   
	   	List list = new ArrayList();
        //1.得到Excel常用对象  
        
        //2.得到Excel工作簿对象  
        Workbook wb = getWorkBook(filename,in);
        //3.得到Excel工作表对象  
        Sheet sheet = wb.getSheetAt(0);  
        //总行数  
        int trLength = sheet.getLastRowNum();  
        //4.得到Excel工作表的行  
        Row row = sheet.getRow(0);  
        //总列数  
        int tdLength = row.getLastCellNum();  
        //5.得到Excel工作表指定行的单元格  
        Cell cell = row.getCell((short)1);  
        //6.得到单元格样式  
//	        CellStyle cellStyle = cell.getCellStyle();  
        for(int i=1;i<trLength;i++){
        	 //得到Excel工作表的行  
            Row row1 = sheet.getRow(i);  
            
        	for(int j=0;j<tdLength;j++){
        		Cell cell1 = row1.getCell(j);
	            /** 
	             * 为了处理：Excel异常Cannot get a text value from a numeric cell 
	             * 将所有列中的内容都设置成String类型格式 
	             */  
	            if(cell1!=null){  
	                  cell1.setCellType(Cell.CELL_TYPE_STRING);  
	            } 
        	}
           
            //得到Excel工作表指定行的单元格  
        	list.add(cellChangeToEntity(row1));
        } 
        return list;
    } 
	   
	   
	private Workbook getWorkBook(String filename,InputStream in) throws FileNotFoundException, IOException {
	   if(filename.endsWith(".xlsx")){
		   return  new XSSFWorkbook(in);  
	   }else if(filename.endsWith(".xls")){
		   return new HSSFWorkbook(in);  
	   }else{
		   return null;
	   }
	}

	public abstract Object cellChangeToEntity(Row row1); 
	   
	
}
