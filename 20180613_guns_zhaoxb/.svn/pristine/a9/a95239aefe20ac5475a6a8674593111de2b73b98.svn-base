package com.stylefeng.guns.modular.system.service.impl;

import com.stylefeng.guns.modular.system.dao.VoteDao;
import com.stylefeng.guns.modular.system.service.IVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 投票Dao
 *
 * @author fengshuonan
 * @Date 2018-06-27 09:18:39
 */
@Service
public class VoteServiceImpl implements IVoteService {
    @Autowired
    private VoteDao voteDao;


    @Override
    public int updateVote(Integer id) {
        return voteDao.updateVote(id);
    }
}
