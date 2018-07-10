package com.stylefeng.guns.core.util;

import com.stylefeng.guns.common.persistence.model.BillZhaoxb;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MapUtil {
    private static  MapUtil instance;

    private static Map<Integer,Object> redisMap;

    static {
        instance = new MapUtil();
        if(redisMap==null){
            redisMap =  new HashMap<Integer,Object>();
        }
    }

    public static synchronized MapUtil getInstance(){
        if (instance == null) {
            instance = new MapUtil();
        }
        if(redisMap==null){
            redisMap =  new HashMap<Integer,Object>();
        }
        return instance;
    }




    public static void setInstance(MapUtil instance) {
        MapUtil.instance = instance;
    }

    public static void setRedisMap(Map<Integer, Object> redisMap) {
        MapUtil.redisMap = redisMap;
    }

    private MapUtil(){}


    /**
     * 存入一个实体
     * @param billZhaoxb
     */
        public static void putBill(BillZhaoxb billZhaoxb){
        redisMap.put(billZhaoxb.getId(),billZhaoxb);
    }
    /**
     * 存入一个实体
     * @param
     */
        public static void putName(int id,String name){
        redisMap.put(id,name);
    }

    /**
     * 更新缓存
     * @param billZhaoxb
     */
    public static void updateBillToMap(BillZhaoxb billZhaoxb){
        redisMap.put(billZhaoxb.getId(),billZhaoxb);
    }
    /**
     * 返回本类中的Map
     * @return
     */
    public static Map<Integer,Object> getRedisMap(){
        return redisMap;
    }

    /**
     * 通过id来向map中查找数据，如果查找到的数据为null就从数据库中查询,查询之后再放入map中
     * @param key
     * @return
     */
    public static BillZhaoxb getBillByKey(Integer key){
        BillZhaoxb billZhaoxb = (BillZhaoxb) redisMap.get(key);
        return billZhaoxb;
    }


    /**
     * 把从数据库中存入的集合放入LinkedList集合中
     * @param list List<BillLuqz>
     * @return LinkedList<BillLuqz>
     */
    public static void putBillList(List<BillZhaoxb> list){
        for(BillZhaoxb billZhaoxb :list){
            MapUtil.putBill(billZhaoxb);
        }
    }


    /**
     * 从map集合中得到所有的实体
     * @return
     */
    public static LinkedList<BillZhaoxb> getAllBill(){
        LinkedList<BillZhaoxb> billLuqz_List = new LinkedList<>();
        for(Integer in :MapUtil.getRedisMap().keySet()){
            BillZhaoxb billZhaoxb = (BillZhaoxb)getRedisMap().get(in);
            billLuqz_List.add(billZhaoxb);
        }
        return billLuqz_List;
    }


    /**
     * 数据库中删除一实体，同时更新mMap缓存
     * @param billZhaoxb
     */
    public static void removeBillToMap(BillZhaoxb billZhaoxb){

        MapUtil.getRedisMap().remove(billZhaoxb.getId());
    }

    /**
     *清空缓存	 */
    public static void removeAllBill(){
        MapUtil.getRedisMap().clear();
    }


    public static void removeBill(Integer key){
        MapUtil.getRedisMap().remove(key);
    }



}
