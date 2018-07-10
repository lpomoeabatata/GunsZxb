package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.common.controller.BaseController;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.common.exception.BussinessException;
import com.stylefeng.guns.common.persistence.dao.VoteZxbMapper;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.shiro.ShiroUser;
import com.stylefeng.guns.modular.system.service.IVoteService;
import com.stylefeng.guns.modular.system.service.impl.RedisServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 投票控制器
 *
 * @author fengshuonan
 * @Date 2018-06-27 09:18:39
 */
@Controller
@RequestMapping("/vote")
public class VoteController extends BaseController {


    private String PREFIX = "/system/vote/";
    @Autowired
    private VoteZxbMapper voteZxbMapper;
    @Autowired
    private IVoteService voteService;
    /**
     * 跳转到投票首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "vote.html";
    }

    /**
     * 获取投票列表
     */
    @ApiOperation(value = "获取投票列表",httpMethod = "POST")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list() {
        return voteZxbMapper.selectList(null);
    }


    /**
     *获取来访用户的ip
     * @param request
     * @return
     */
    private String getIpAddr(HttpServletRequest request){
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress= inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    /**
     * 新增投票
     */
    @ApiOperation(value = "新增投票",httpMethod = "POST")
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object add(int vote) throws Exception {
//        EntityWrapper<VoteZxb> voteZxbEntityWrapper = new EntityWrapper<>();
//        Wrapper<VoteZxb> count = voteZxbEntityWrapper.eq("count", voteZxb.getCount());

        ShiroUser shiroUser = ShiroKit.getUser();
        String name =getIpAddr(getHttpServletRequest());
        Integer id = shiroUser.getId();
        //根据ip查询登录用户是否已经投过票，如果没有，加入缓存
        RedisServiceImpl redisService = new RedisServiceImpl();
        HashSet<String> set=new HashSet<String>();
        set.add(name);
        if (redisService.sismember(String.valueOf(id), name)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        } else {
            redisService.sadd(String.valueOf(id),set);
        }
         //在添加缓存用户后，定时删除以使再次投票
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                try {
                    redisService.srem(String.valueOf(id),set);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 10000);// 设定指定的时间time,此处为2000毫秒

        Integer update = voteService.updateVote(vote);
        if (update <0) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        return voteZxbMapper.selectList(null);
    }
}
