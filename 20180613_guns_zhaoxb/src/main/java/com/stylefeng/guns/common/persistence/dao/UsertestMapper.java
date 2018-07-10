package com.stylefeng.guns.common.persistence.dao;

import com.stylefeng.guns.common.persistence.model.Usertest;

import java.util.List;

public interface UsertestMapper {
    void addUsertest(Usertest sysUser);

    int updateUsertestByName(Usertest sysUser);

    int selectByName(String name);
    List<Usertest> selectAll();
}
