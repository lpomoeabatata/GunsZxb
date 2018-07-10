package com.stylefeng.guns.core.util;
import java.text.SimpleDateFormat;
import java.util.List;

import com.stylefeng.guns.common.persistence.model.Usertest;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class CommonUtil {

	/**
	 * 为导出的学生信息创建一个excel文件格式
	 */
	public static HSSFWorkbook createWb(String[] heads, List<Usertest> usertests) {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("Sheet");
		// 第三步，在sheet中添加表头第0行
		HSSFRow row = sheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		//style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		// 设置表头
		for (int i = 0; i < heads.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(heads[i]);
			cell.setCellStyle(style);
		}
		if (usertests == null || usertests.size() < 1) {
			return wb;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 第五步，写入实体数据
		for (int i = 0; i < usertests.size(); i++) {
			Usertest usertest = usertests.get(i);
			row = sheet.createRow(i + 1);
			row.setRowStyle(style);//内容居中
			row.createCell(0).setCellValue(usertest.getName()); // 姓名（咨询）
			row.createCell(1).setCellValue(usertest.getPhone()); //
			row.createCell(2).setCellValue(usertest.getAddress()); // 电话
			row.createCell(3).setCellValue(usertest.getDes()); // 电话
	   }
		 return wb;
	}

}
