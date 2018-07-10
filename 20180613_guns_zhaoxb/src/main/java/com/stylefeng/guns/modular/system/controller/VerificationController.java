package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.common.persistence.dao.ModulesMapper;
import com.stylefeng.guns.common.persistence.model.Modules;
import com.stylefeng.guns.core.util.RandomValidateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Verification")
public class VerificationController extends BaseController {

            private String PREFIX = "/system/Verification/";
            @Autowired
            private ModulesMapper modulesMapper;
            /**
             * 跳转到上传文件首页
             */
            @RequestMapping("")
            public String index() {
                return PREFIX + "Verification.html";
            }

            /**
             * 生成验证码
             */
            @RequestMapping(value = "/getVerify")
            public void getVerify(HttpServletRequest request, HttpServletResponse response) {
                try {
                    response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
                    response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
                    response.setHeader("Cache-Control", "no-cache");
                    response.setDateHeader("Expire", 0);
                    RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
                    randomValidateCode.getRandcode(request, response);//输出验证码图片方法
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            /**
             * 校验验证码
             */
            @RequestMapping(value = "/checkVerify", method = RequestMethod.POST, headers = "Accept=application/json")
            public boolean checkVerify(@RequestBody Map<String, Object> requestMap, HttpSession session) {
                try {
                    //从session中获取随机数
                    String inputStr = requestMap.get("inputStr").toString();
                    String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
                    if (random == null) {
                        return false;
                    }
                    if (random.equals(inputStr)) {
                        return true;
                    } else {
                        return false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }

//            @RequestMapping("/Modules")
//            @ResponseBody
//            public String buildJsonTree(HttpServletRequest request) {
//
//                // 获取全部目录节点
//                List<Modules> modules = modulesMapper.selectAll();
//                // 拼装树形json字符串
//                TreeBuilder json = new TreeBuilder().buildTree(modules);
//
//                System.out.println(json);
//                return String.valueOf(json);
//            }

            //modulesList 为需要返回结果
            //menuList 是你查出的全部菜单
            //MenuInfo 换成你的实体
        @RequestMapping("/Modules")
        @ResponseBody
        public List<Modules> setMenuList() {
            List<Modules> modulesList = modulesMapper.selectAll();
            List<Modules> menuList = new ArrayList<>();
            for (int i = 0; i < modulesList.size(); i++) {
                // 一级菜单没有parentId
                if (modulesList.get(i).getPid().equals(0)) {
                    menuList.add(modulesList.get(i));
                }
            }
            // 为一级菜单设置子菜单，getChild是递归调用的
            for (Modules menu : menuList) {
                menu.setChildren(getChild(menu.getId(), modulesList));
            }
            return menuList;
        }
        /**
         * 递归查找子菜单
         *
         * @param id       当前菜单id
         * @param rootMenu 要查找的列表
         * @return
         */
        private List<Modules> getChild(int id, List<Modules> rootMenu) {
            // 子菜单
            List<Modules> childList = new ArrayList<>();
            for (Modules menu : rootMenu) {
                // 遍历所有节点，将父菜单id与传过来的id比较
                    if (menu.getPid().equals(id)) {
                        childList.add(menu);
                }
            }
            // 把子菜单的子菜单再循环一遍
            for (Modules menu : childList) {// 没有url子菜单还有子菜单
                // 递归
                menu.setChildren(getChild(menu.getId(), rootMenu));
            } // 递归退出条件
            if (childList.size() == 0) {
                return childList;
            }
            return childList;
        }
        }