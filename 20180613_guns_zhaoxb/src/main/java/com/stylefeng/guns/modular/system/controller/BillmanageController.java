package com.stylefeng.guns.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.Email.EmailTest;
import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.exception.BussinessException;
import com.stylefeng.guns.common.persistence.dao.BillZhaoxbMapper;
import com.stylefeng.guns.common.persistence.dao.ProcessMapper;
import com.stylefeng.guns.common.persistence.model.BillZhaoxb;
import com.stylefeng.guns.common.persistence.model.Process;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.stylefeng.guns.core.support.DateTime;
import com.stylefeng.guns.core.util.MapUtil;
import com.stylefeng.guns.core.util.ToolUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.security.GeneralSecurityException;
import java.util.*;

/**
 * 账单管理控制器
 *
 * @author fengshuonan
 * @Date 2018-06-19 09:11:02
 */
@Controller
@RequestMapping("/billmanage")

public class BillmanageController extends BaseController {
    private String PREFIX = "/system/billmanage/";
    @Autowired
    private BillZhaoxbMapper billZhaoxbMapper;
    @Autowired
    private ProcessMapper processMapper;

    /**
     * 跳转到账单管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "billmanage.html";
    }

    /**
     * 跳转到添加账单管理
     */
    @RequestMapping("/billmanage_add")
    public String billmanageAdd() {
        return PREFIX + "billmanage_add.html";
    }

    /**
     * 跳转到修改账单管理
     */
    @RequestMapping("/billmanage_update/{billmanageId}")
    public String billmanageUpdate(@PathVariable Integer billmanageId, Model model) {
        BillZhaoxb billZhaoxb = MapUtil.getBillByKey(billmanageId);
        if (billZhaoxb==null) {
            billZhaoxb=billZhaoxbMapper.selectById(billmanageId);
            MapUtil.putBill(billZhaoxb);
        }
        model.addAttribute(billZhaoxb);

        EntityWrapper<Process> processEntityWrapper = new EntityWrapper<>();
        Wrapper<Process> processId = processEntityWrapper.eq("process_Id", billmanageId);
        List<Process> processList = processMapper.selectList(processId);
        ShiroUser shiroUser = ShiroKit.getUser();
        assert shiroUser != null;
        billZhaoxb.setBillAuditor(shiroUser.getRoleNames().get(0));
        model.addAttribute("pro",processList);
        model.addAttribute("user",shiroUser.getName());
        LogObjectHolder.me().set(billZhaoxb);
        LogObjectHolder.me().set(processList);
        return PREFIX + "billmanage_edit.html";
    }

    /**
     * 获取账单管理列表
     */
    @ApiOperation(value = "获取账单管理列表",httpMethod = "POST")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) throws Exception {
//        if (ToolUtil.isNotEmpty(condition)) {
//            EntityWrapper<BillZhaoxb> billZhaoxbEntityWrapper = new EntityWrapper<>();
//            Wrapper<BillZhaoxb> applicant = billZhaoxbEntityWrapper.like("bill_Applicant", "%"+condition+"%");
//            return billZhaoxbMapper.selectList(applicant);
//        } else {
//            return billZhaoxbMapper.selectList(null);
//        }

//        RedisServiceImpl redisService = new RedisServiceImpl();
//        redisService.set("0","1111111122222222222222222222222222");
//        String s=redisService.get("4");
//        System.out.println(s);
        Map<Integer, Object> redisMap = MapUtil.getRedisMap();
        if(redisMap==null || redisMap.size()==0){
            List<BillZhaoxb> billZhaoxbs = billZhaoxbMapper.selectList(null);
            MapUtil.putBillList(billZhaoxbs);
        }
        LinkedList<BillZhaoxb> allBill = MapUtil.getAllBill();
        return allBill;
    }

    /**
     * 新增账单管理
     */
    @ApiOperation(value = "新增账单管理",httpMethod = "POST")
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BillZhaoxb billZhaoxb) throws GeneralSecurityException, MessagingException {
        if (ToolUtil.isOneEmpty(billZhaoxb)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        ShiroUser shiroUser = ShiroKit.getUser();
        billZhaoxb.setBillEntry(shiroUser.getName());
        billZhaoxb.setBillDate(new Date());
        billZhaoxbMapper.insert(billZhaoxb);
        MapUtil.putBill(billZhaoxb);
        EmailTest.sendEmail("779829518@qq.com","有一份新的账单申请");
        return super.SUCCESS_TIP;
    }

    /**
     * 删除账单管理
     */
    @ApiOperation(value = "删除账单管理",httpMethod = "POST")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(int billmanageId) {
        if (ToolUtil.isEmpty(billmanageId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        billZhaoxbMapper.deleteById(billmanageId);
        return SUCCESS_TIP;
    }


    /**
     * 修改账单管理
     */
    @ApiOperation(value = "修改账单管理",httpMethod = "POST")
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BillZhaoxb billZhaoxb) throws GeneralSecurityException, MessagingException {
        if (ToolUtil.isEmpty(billZhaoxb) || billZhaoxb.getId()== null){
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        billZhaoxb.setBillState(billZhaoxb.getBillAuditor()+billZhaoxb.getBillState());
        billZhaoxbMapper.updateById(billZhaoxb);
        MapUtil.putBill(billZhaoxb);
        Process process=new Process();
        process.setProcessAuditor(billZhaoxb.getBillAuditor());
        process.setProcessDate(new DateTime());
        process.setProcessId(billZhaoxb.getId());
        process.setProcessRemarks(billZhaoxb.getBillRemarks());
        process.setProcessState(billZhaoxb.getBillState());
        processMapper.insert(process);
        EmailTest.sendEmail("779829518@qq.com","账单已审核");
        return super.SUCCESS_TIP;
    }

    /**
     * 账单管理详情
     */
    @RequestMapping(value = "/detail")
    @ResponseBody
    public Object detail() {
        return null;
    }


    @RequestMapping(value = "/name")
    @ResponseBody
    public Object name() {
        List<BillZhaoxb> billZhaoxbs = billZhaoxbMapper.selectList(null);
        String[] names = new String[billZhaoxbs.size()];
        for (int i=0;i<billZhaoxbs.size();i++){
           names[i]=billZhaoxbs.get(i).getBillApplicant();
        }
        return names;
    }

}
