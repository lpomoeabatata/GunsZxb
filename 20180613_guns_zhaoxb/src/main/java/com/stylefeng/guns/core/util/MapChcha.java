package com.stylefeng.guns.core.util;


import com.stylefeng.guns.common.persistence.model.BillZhaoxb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapChcha {
    private  Map<Integer,BillZhaoxb> cache = null;

    public Map<Integer, BillZhaoxb> getCache() {
        return cache;
    }

    public void setCache(Map<Integer, BillZhaoxb> cache) {
        this.cache = cache;
    }

    /**
     * 存缓存
     * @param billZhaoxbs
     * @return boolean
     */
    public boolean addCache(List<BillZhaoxb> billZhaoxbs){
        if (cache==null){
            cache=new HashMap<>();
            try {
                for (BillZhaoxb billZhaoxb : billZhaoxbs) {
                    cache.put(billZhaoxb.getId(),billZhaoxb);
                }
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    /**
     * 增加单个缓存
     * @param billZhaoxb
     * @return
     */
    public boolean addOnecaChe(BillZhaoxb billZhaoxb){
        try {
            cache.put(billZhaoxb.getId(),billZhaoxb);
            return true;
        }catch (Exception e){
            e.printStackTrace();

        }
        return false;
    }

    /**
     * 获取缓存
     * @param key
     * @return Object
     */
    public Object getValue(Integer key){
        Object ob = cache.get(key);
        if (ob!=null){
            return ob;
        }
        return null;
    }

    /**
     * 根据条件查询
     * @param name
     * @return
     */
    public List<BillZhaoxb> getByname(String name){
        List<BillZhaoxb> list = new ArrayList<>();
        cache.size();
        BillZhaoxb billZhaoxb;
        for (Map.Entry<Integer, BillZhaoxb> entry : cache.entrySet()) {
            if (entry.getValue().getBillApplicant().contains(name)) {
                billZhaoxb = entry.getValue();
                list.add(billZhaoxb);
            }
        }
        return list;
    }
    /**
     * 清楚缓存
     */
    public boolean clearChache(){
        try {
            cache.clear();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
}
