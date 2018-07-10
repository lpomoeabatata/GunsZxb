package com.stylefeng.guns.modular.system.controller;

import com.baomidou.mybatisplus.activerecord.Model;
import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.common.persistence.dao.UsertestMapper;
import com.stylefeng.guns.common.persistence.model.Usertest;
import com.stylefeng.guns.core.util.CommonUtil;
import com.stylefeng.guns.modular.system.service.IUserTestService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * 文件上传控制器
 *
 * @author fengshuonan
 * @Date 2018-06-29 13:43:12
 */



@Controller
@RequestMapping("/FileImport")
public class FileImportController extends BaseController {

    private String PREFIX = "/system/FileImport/";

        @Autowired
        private IUserTestService testService;
        @Autowired
        private UsertestMapper usertestMapper;

    /**
     * 跳转到上传文件首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "FileImport.html";
    }



    @PostMapping("/import")
    @ResponseBody
        public String addUser(@RequestParam("file") MultipartFile file) {
            ModelAndView a = new ModelAndView();
            String fileName = file.getOriginalFilename();
            try {
                a = testService.batchImport(fileName, file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        String s1 = a.getModel().get("错误").toString();
            if (s1==null) {
                return a.getModel().get("是否完成").toString().substring(0, 3) + "完成\n" ;
            }
            else {
                return a.getModel().get("是否完成").toString().substring(0, 3) + "完成\n"+s1;
            }
//          String s=a.getModel().toString();
        }


    private static final Logger logger = LoggerFactory.getLogger(FileImportController.class);
    //文件上传相关代码
    @RequestMapping(value = "upload")
    @ResponseBody
    public String upload(@RequestParam("test") MultipartFile file) {
        if (file.isEmpty()) {
            return "文件为空";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        String filePath = "E://test//";
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return "上传成功";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    //文件下载相关代码
    @RequestMapping("/download")
    public String downloadFile(org.apache.catalina.servlet4preview.http.HttpServletRequest request, HttpServletResponse response){
        String fileName = "FileUploadTests.java";
        if (fileName != null) {
            //当前是从该工程的WEB-INF//File//下获取文件(该目录可以在下面一行代码配置)然后下载到C:\\users\\downloads即本机的默认下载的目录
            String realPath = request.getServletContext().getRealPath("//java//");
            File file = new File(realPath, fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition",
                        "attachment;fileName=" +  fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }
    //多文件上传
    @RequestMapping(value = "/batch/upload", method = RequestMethod.POST)
    @ResponseBody
    public String handleFileUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(file.getOriginalFilename())));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    return "You failed to upload " + i + " => "
                            + e.getMessage();
                }
            } else {
                return "You failed to upload " + i
                        + " because the file was empty.";
            }
        }
        return "upload successful";
    }

    /**
     * 导出Execl
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/Down")
    @ResponseBody
    public void export(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        OutputStream ouputStream = null;
        try {
            List<Usertest> usertestList =usertestMapper.selectAll();
            if (usertestList != null && usertestList.size() > 0) {
                /* for(Excl exc : studentList){
                     //把用户Id转为业务需要的用户名称
                     exc.setAskerId(getUserName(stu.getAskerId()));
                 }
             }*/
                String fileName = "学生信息.xls";
                String columnStr = "序号,姓名,电话,地址,备注";
                String[] heads = columnStr.split(",");
                HSSFWorkbook wb = CommonUtil.createWb(heads, usertestList);
                response.reset();// 清空输出流
                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-disposition", "attachment;filename="
                        + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
                ouputStream = response.getOutputStream();
                wb.write(ouputStream);
            }
            ouputStream.flush();
            ouputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
