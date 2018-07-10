package com.stylefeng.guns.core.util;


import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetMessage {


    /**
     * 用户ID
     */
    public static final String ACCOUNT_SID = "196a38f0b07c41138321fd6909294593";//这里填写你在平台里的ACOUNT_SID

    /**
     * 密钥
     */
    public static final String AUTH_TOKEN = "e5b7f2965eac49b295080b0853229e3f";

    /**
     * 请求地址前半部分
     */
    public static final String BASE_URL = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";//请求地址是固定的不用改

    public static  String randNum = RandUtil.getRandNum();

    public  static String smsContent = "【咕咕咕】您的验证码为"+randNum+"，请于"+2+"分钟内正确输入，如非本人操作，请忽略此短信。";
    /**
     * (获取短信验证码)
     * @param to
     * @return String
     */
    public static String getResult(String to) {
        randNum = RandUtil.getRandNum();
        String smsContent = "【咕咕咕】您的验证码为"+randNum+"，请于"+2+"分钟内正确输入，如非本人操作，请忽略此短信。";            //这里的randNum 和 smsContent和上面的静态变量是一样的，可删除可保留
        String args = QueryUtil.queryArguments(ACCOUNT_SID, AUTH_TOKEN, smsContent, to);
        OutputStreamWriter out = null;
        InputStream in = null;
        BufferedReader br = null;
        StringBuffer sb = new StringBuffer();


        try {
            URL url = new URL(BASE_URL);
            URLConnection connection = url.openConnection(); //打开链接
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(5000);  //设置链接超时
            connection.setReadTimeout(10000);    //设置读取超时

            //提交数据
            out = new OutputStreamWriter(connection.getOutputStream(),"utf-8");
            out.write(args);
            out.flush();

            //读取返回数据
            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            while((line = br.readLine())!=null){
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (br!=null) {
                    br.close();
                }
                if (out!=null) {
                    out.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        JSONObject jsonObject = (JSONObject) JSONObject.parse(sb.toString());
        System.out.println(jsonObject);
        Object object = jsonObject.get("respCode");
        System.out.println("状态码："+object+"验证码："+randNum);
        System.out.println(!object.equals("00000"));
        if (!object.equals("00000")) {
            return object.toString();
        }else{

            return randNum;
        }
    }
    //汉诺塔
//    private static void moveDish(int level, char from, char inter, char to) {
//        if (level == 1) {
//            System.out.println("从" + from + " 移动盘子" + level + " 号到" + to);
//        } else {
//            moveDish(level - 1, from, to, inter);
//            System.out.println("从" + from + " 移动盘子" + level + " 号到" + to);
//            moveDish(level - 1, inter, from, to);
//        }
//    }
//  测试功能
  public static void main(String[] args) {
//      String result = getResult("15638908558");
//      System.out.println("验证码："+randNum+"\t"+result);

//      List<Integer> list=new ArrayList<>();
//      Map<Integer,Integer>map=new HashMap<>();
//      list.add(11);
//      list.add(213);
//      list.add(12);
//      list.add(12);
//      list.add(34);
//      list.add(767);
//      list.add(65);
//      list.add(34);
//      list.add(76);
//      list.add(34);
//      for (Integer number:list){
//        Integer count=map.get(number);
//        map.put(number,(count==null)?1:count+1);
//      }
//      return;


//    int []arr={9,7,8,5,4,2,1};
//    for (int i=0;i<arr.length;i++){
//        for (int j=0;j<arr.length-1-i;j++){
//                if (arr[j]>arr[j+1]){
//                    int temp=arr[j];
//                    arr[j]=arr[j+1];
//                    arr[j+1]=temp;
//                }
//        }
//    }
//    return;
      //汉诺塔
//          int nDisks = 3;
//          moveDish(nDisks, 'A', 'B', 'C');

  }
}