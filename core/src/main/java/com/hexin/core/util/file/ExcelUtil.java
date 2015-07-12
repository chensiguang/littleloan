package com.hexin.core.util.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hexin.core.util.DateUtil;


public class ExcelUtil {
	private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);
	public static Boolean objListToExcel(Map<String, String> head, List listData,String path, String fileName){
		try{
			File file =new File(path);
			if(!file.exists()&&!file.isDirectory()){       
			    file.mkdir();    
			}
			String filePath = path +File.separator+ fileName+".xls";
	        File newFile = new File(filePath);
	        newFile.createNewFile();
	        System.out.println("创建文件成功：" + filePath);
	        HSSFWorkbook wb = new HSSFWorkbook();
	        HSSFSheet sheet = wb.createSheet();
	        int rowNum = 0;
	        HSSFRow headrow = sheet.createRow(rowNum);
	        int j=0;
	        for(String key : head.keySet()){
	        	sheet.setColumnWidth(j, 6000);
	        	HSSFCell cell = headrow.createCell(j);
        		cell.setCellValue(new HSSFRichTextString(key));
        		j++;
	        }
	        for(int i = 0; i < listData.size(); i++){
	        	HSSFRow row = sheet.createRow(i+1);
	            Object obj = listData.get(i);
	            j = 0;
	            for(String key : head.keySet()){
	            	HSSFCell cell = row.createCell(j);
            		String fieldName = key;
            		fieldName = fieldName.replaceFirst(fieldName
    	                    .substring(0, 1), fieldName.substring(0, 1)
    	                    .toUpperCase());
            		fieldName = "get" + fieldName;
            		Class clazz = Class.forName(obj.getClass().getName());
                    Method[] methods = clazz.getMethods();
                    Pattern pattern = Pattern.compile(fieldName);
                    Matcher mat = null;
                    for (Method m : methods) {
                        mat = pattern.matcher(m.getName());
                        if (mat.find()) {
                        	Object attr = m.invoke(obj, null);
	                        if (attr != null) {
	                            cell.setCellValue(attr.toString());//这里可以做数据格式处理
	                        } else {
	                            cell.setCellValue("");
	                        }
	                        break;
                        }
                    }
	            	j++;
	            }
	        }
	        OutputStream out = new FileOutputStream(filePath);
		    wb.write(out);// 写入File
		    out.flush();
		    out.close();
		    return true;
		}catch (Exception e) {
	        logger.error(e.getMessage());
	        return false;
		}
	}
    
	public static Boolean objListToCSV(Map<String, String> head, List listData,
            String outPutPath, String filename, String spearator, String encoding) {

        File csvFile = null;
        BufferedWriter csvWriter = null;
        try {
            csvFile = new File(outPutPath + File.separator + filename + ".csv.tmp");
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();
            
            // GB2312使正确读取分隔符","
            csvWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                    csvFile), encoding), 1024);
            // 写入文件头部
            String rowHead="";
	        for(String key : head.keySet()){
	        	StringBuffer sb = new StringBuffer();
	        	String rowStr = sb.append(key).append(spearator).toString();
	        	rowHead += rowStr;
	        }
	        rowHead =  rowHead.substring(0,rowHead.lastIndexOf(spearator));
	        csvWriter.write(rowHead);
	        csvWriter.newLine();
            // 写入文件内容
	        for(int i = 0; i < listData.size(); i++){
	        	Object obj = listData.get(i);
	        	String rowContent = "";
	            for(String key : head.keySet()){
	            	String fieldName = key;
            		fieldName = fieldName.replaceFirst(fieldName
    	                    .substring(0, 1), fieldName.substring(0, 1)
    	                    .toUpperCase());
            		fieldName = "get" + fieldName;
            		Class clazz = Class.forName(obj.getClass().getName());
                    Method[] methods = clazz.getMethods();
                    for (Method m : methods) {
                        if (m.getName().equals(fieldName)) {
                        	Object attr = m.invoke(obj, null);
	                        if (attr != null) {
	                        	StringBuffer sb = new StringBuffer();
	                        	String s = "";
	                        	if(attr instanceof Date){
	                        		s = DateUtil.formatDateTime((Date) attr, "yyyy-MM-dd HH:mm:ss");
	                        	}
	                        	else{
	                        		s = attr.toString();
	                        	}
	                        	s = s.replace("\r\n", "<br />");
	                        	s = s.replace("\n", "<br />");
	            	        	String rowStr = sb.append(s).append(spearator).toString();
	            	        	rowContent += rowStr;
	                        } else {
	                        	StringBuffer sb = new StringBuffer();
	            	        	String rowStr = sb.append("").append(spearator).toString();
	            	        	rowContent += rowStr;
	                        }
	                        break;
                        }
                    }
	            }
	            rowContent = rowContent.substring(0,rowContent.lastIndexOf(spearator));
	            csvWriter.write(rowContent);
	            csvWriter.newLine();
	        }
	        csvWriter.flush();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        } finally {
            try {
            	csvWriter.close();
    	        File oldFile = new File(outPutPath + File.separator + filename + ".csv.tmp");
    	        File newFile = new File(outPutPath + File.separator + filename + ".csv");
    	        if(newFile.exists()){
    	        	newFile.delete();
    	        }
    	        oldFile.renameTo(newFile);
    	        System.out.println("create file success:：" + outPutPath + File.separator + filename + ".csv");
            } catch (IOException e) {
            	logger.error(e.getMessage());
            	return false;
            }
        }
        return true;
    }

}
