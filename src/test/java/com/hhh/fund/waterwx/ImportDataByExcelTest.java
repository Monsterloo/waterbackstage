package com.hhh.fund.waterwx;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hhh.fund.config.AppContext;
import com.hhh.fund.waterwx.dao.RiverDao;
import com.hhh.fund.waterwx.entity.River;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppContext.class)
public class ImportDataByExcelTest {
	
	@Autowired
	private RiverDao dao;
	
	@Test
	public void printData() throws Exception{
		File file = new File("C:\\Users\\3hljl.HHHNETSERVER\\Desktop\\152黑臭水体名称.xlsx");
		getImport(file, 0);
	}
	
	public void getImport(File file , int tableIndex) throws Exception{
        /*Workbook rwb=Workbook.getWorkbook(file);
        Sheet st=rwb.getSheet(tableIndex);//读取文件
        int clos = st.getColumns();//所有列
        int rows = st.getRows();//所有行
        
        List<River> riversList = new ArrayList<River>();
        System.out.println("clos" + clos + " rows:" + rows);  
        for (int i = 1; i < row; i++) {  
            for (int j = 1; j < clos; j++) {  
                // 第一个是行数，第二个是列数 
                String subject = st.getCell(j++, i).getContents();// 默认最左边编号也算一列  
                // 所以这里得j++  
                String phon = st.getCell(j++, i).getContents();  
                System.out.println(" name:" + name + " phon:" + phon); 
            	River river = new River();
            	river.setRiverName(st.getCell(j, i).getContents());
            	riversList.add(river);
            }  
        }
        
        for(int i=1;i<riversList.size();++i){
        	System.out.println(i+"....."+riversList.get(i-1).getRiverName());
        }*/
		
		
		Workbook wb = new XSSFWorkbook(new FileInputStream(file));
		//得到Excel工作表对象  
		Sheet sheet = wb.getSheetAt(0);
		//总行数  
	    int trLength = sheet.getLastRowNum(); 
	    //得到Excel工作表的行  
        Row row = sheet.getRow(0);
        //总列数  
        int tdLength = row.getLastCellNum();
        
        List<River> riversList = new ArrayList<River>();
        for(int i=1;i<=trLength;i++){
        	 //得到Excel工作表的行
        	row = sheet.getRow(i);
        	
        	for(int j=1;j<tdLength;j++){
        		Cell cell = row.getCell(j);
        		
        		/** 
	             * 为了处理：Excel异常Cannot get a text value from a numeric cell 
	             * 将所有列中的内容都设置成String类型格式 
	             */  
	            if(cell!=null){  
	                cell.setCellType(Cell.CELL_TYPE_STRING);  
	            } 
	            River river = new River();
	            river.setRiverName(cell.getStringCellValue().trim());
            	riversList.add(river);
        	}
        }
        List<River> newRiverList = new ArrayList<River>();
        for(int i=0;i<riversList.size();++i){
        	System.out.println((i+1)+"....."+riversList.get(i).getRiverName());
        	List<River> riverList = dao.findIdByRiverName(riversList.get(i).getRiverName());
        	if(riverList != null){
        		for(River river : riverList){
        			System.out.println(river.getId());
        			river.setBelongPollRiver("2");
        			newRiverList.add(river);
        		}
        	}
        }
        System.out.println("---------------------------");
        for(River r : newRiverList){
        	System.out.println(r.getId()+"...."+r.getRiverCode()+"...."+r.getBelongPollRiver());
        	
        	dao.save(r);
        }
	}
}
