package com.KongJian.util;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
private final static String excel2003L=".xls";//2003版本的excel文件格式
private final static String excel2007U=".xlsx";//2007+的excel

/*
 * 获取流文件名组装成List<List<Object>>对象,excel中的每一行都是一个list
 *@param in文件输入流，finaName文件名，
 */
public List<List<Object>> getBankListExcel(InputStream in,String fileName) throws Exception{
	List<List<Object>> list=null;
	//创建工作簿
	Workbook work=this.getWorkbook(in,fileName);  
	if(work==null) {
		throw new Exception("文件为空");
		
	}else {
		Sheet sheet=null;//页
		Row row=null;//行
		Cell cell=null;//列
		list=new ArrayList<List<Object>>();
		//遍历Excel中的所有sheet
		for(int i=0;i<work.getNumberOfSheets();i++) {
			sheet=work.getSheetAt(i);
			if(sheet==null) {
				continue;//如果页码为空则继续下一页
			}else {
				
				for(int j=sheet.getFirstRowNum();j<=sheet.getLastRowNum();j++) {
					List<Object> li=new ArrayList<Object>();
					row=sheet.getRow(j);
					if(row==null) {
						continue;
					      }else {
						
						//遍历所有列将列存进li中
						for(int y=row.getFirstCellNum();y<row.getLastCellNum();y++) {
							if(row.getCell(y)==null) {
								continue;
							}else {
							cell=row.getCell(y);
							li.add(cell);
							
							}
						}
						
						
					}
					if(li==null) {
						continue;
					}else {list.add(li);}
					
				}
				
				
			}
		}
	}
	
	
	return list;
}
/*
 * 根据文件后缀名创建自适应于版本的工作簿
 * @params in  文件输入流，fileName文件名
 */
private Workbook getWorkbook(InputStream in, String fileName) throws Exception {
   Workbook work=null;
   String fileType=fileName.substring(fileName.lastIndexOf("."));
   if(excel2003L.equals(fileType)) {
	   work=new HSSFWorkbook(in);
	   
   }else if(excel2007U.equals(fileType)) {
	   work=new XSSFWorkbook(in);
	   
   }else {
	   
	   throw new Exception("解析文件格式错误");
   }
	return work;
}
}
