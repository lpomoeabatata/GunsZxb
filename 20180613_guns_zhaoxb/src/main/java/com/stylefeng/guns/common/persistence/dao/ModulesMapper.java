package com.stylefeng.guns.common.persistence.dao;

import com.stylefeng.guns.common.persistence.model.Modules;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author modules
 * @since 2018-07-06
 */
public interface ModulesMapper extends BaseMapper<Modules> {

    List<Modules> selectAll();
}