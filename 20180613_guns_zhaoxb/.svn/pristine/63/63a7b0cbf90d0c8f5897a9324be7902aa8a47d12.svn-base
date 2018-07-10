package com.stylefeng.guns.modular.system.service.impl;


import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.exception.BussinessException;
import com.stylefeng.guns.common.persistence.dao.UsertestMapper;
import com.stylefeng.guns.common.persistence.model.Usertest;
import com.stylefeng.guns.modular.system.service.IUserTestService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserTestServiceImpl implements IUserTestService {

    @Autowired
    private UsertestMapper usertestMapper;


    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public ModelAndView batchImport(String fileName, MultipartFile file) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        boolean notNull = false;
        List<Usertest> userList = new ArrayList<Usertest>();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if (sheet != null) {
            notNull = true;
        }
        Usertest usertest;
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }

            usertest = new Usertest();

            if (row.getCell(0).getCellType() != 1) {
               System.out.println("导入失败(第" + (r + 1) + "行,姓名请设为文本格式)");
                modelAndView.addObject("错误","导入失败(第" + (r + 1) + "行,姓名请设为文本格式)");
            }
            String name = row.getCell(0).getStringCellValue();

            if (name == null || name.isEmpty()) {
                System.out.println("导入失败(第" + (r + 1) + "行,姓名未填写)");
                modelAndView.addObject("错误","导入失败(第" + (r + 1) + "行,姓名未填写)");
            }

            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
            String phone = row.getCell(1).getStringCellValue();
            if (phone == null || phone.isEmpty()) {
                System.out.println("导入失败(第" + (r + 1) + "行,电话未填写)");
                modelAndView.addObject("错误","导入失败(第" + (r + 1) + "行,电话未填写)");
            }
            String add = row.getCell(2).getStringCellValue();
            if (add == null) {
                System.out.println("导入失败(第" + (r + 1) + "行,不存在此单位或单位未填写)");
                modelAndView.addObject("错误","导入失败(第" + (r + 1) + "行,不存在此单位或单位未填写)");
            }

            Date date = null;
            if (row.getCell(3).getCellType() != 0) {
                System.out.println("导入失败(第" + (r + 1) + "行,入职日期格式不正确或未填写)");
                modelAndView.addObject("错误","导入失败(第" + (r + 1) + "行,入职日期格式不正确或未填写)");
            } else {
                date = row.getCell(3).getDateCellValue();
            }

            String des = row.getCell(4).getStringCellValue();

            usertest.setName(name);
            usertest.setPhone(phone);
            usertest.setAddress(add);
            usertest.setEnrolDate(date);
            usertest.setDes(des);

            userList.add(usertest);
        }
        for (Usertest userResord : userList) {
            String name = userResord.getName();
            int cnt = usertestMapper.selectByName(name);
            if (cnt == 0) {
                usertestMapper.addUsertest(userResord);
                System.out.println(" 插入 " + userResord);
                modelAndView.addObject("是否完成","插入"+userResord);
            } else {
                usertestMapper.updateUsertestByName(userResord);
                System.out.println(" 更新 " + userResord);
                modelAndView.addObject("是否完成"," 更新 " + userResord);
            }
        }
        return modelAndView;
    }
}