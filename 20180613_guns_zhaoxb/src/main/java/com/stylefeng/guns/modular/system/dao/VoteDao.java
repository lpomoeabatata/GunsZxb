package com.stylefeng.guns.modular.system.dao;

import org.apache.ibatis.annotations.Param;

/**
 * 投票Dao
 *
 * @author fengshuonan
 * @Date 2018-06-27 09:18:39
 */
public interface VoteDao {
/**
 * 更新投票信息
 */
int updateVote(@Param("id") Integer id);
}
