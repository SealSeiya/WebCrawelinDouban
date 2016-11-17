package com.zw.beans.excel;  

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import jxl.Workbook;  
import jxl.write.Label;
import jxl.write.WritableSheet;  
import jxl.write.WritableWorkbook;  
import jxl.write.DateTime;  
public class CreateExcel {  
    @SuppressWarnings("rawtypes")
	public static void Exp(List excel ) {  
        try {  
        	
        	//Date d=new Date();    
       	    //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
            // 打开文件  
            WritableWorkbook book = Workbook.createWorkbook(new File(  
                    "bookTop100.xls"));  
            // 生成名为“sheet1”的工作表，参数0表示这是第一页  
            WritableSheet sheet = book.createSheet("book", 0);  
            
            
            // 在Label对象的构造子中指名单元格位置是第一列第一行(0,0),单元格内容为string  
            Label label = new Label(0, 0, "书本 详情");  
            // 将定义好的单元格添加到工作表中  
            sheet.addCell(label); 
            for(int i = 0;i<100;i++){
            Label t = new Label(0, i+1, (String) excel.get(i));
            sheet.addCell(t);
            }  
            //sheet.addCell(number);  
            // 生成一个保存日期的单元格，单元格位置是第三列，第一行，单元格的内容为当前日期  
            Date now = Calendar.getInstance().getTime(); 
            //DateFormat customDateFormat = new DateFormat ("dd MMM yyyy hh:mm:ss"); 
            //WritableCellFormat dateFormat = new WritableCellFormat (customDateFormat); 
            DateTime dateCell = new DateTime(3, 0, now); 
            System.out.println(now);
            sheet.addCell(dateCell);  
            // 写入数据并关闭文件  
            book.write();  
            book.close();  
        } catch (Exception e) {  
            System.out.println(e);  
        }  
    }  
} 