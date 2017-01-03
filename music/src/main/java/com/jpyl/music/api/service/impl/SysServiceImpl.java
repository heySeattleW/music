package com.jpyl.music.api.service.impl;

import com.jpyl.music.api.dao.def.SysDao;
import com.jpyl.music.api.service.def.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by Administrator on 2016/12/8.
 */
@Service
public class SysServiceImpl implements SysService {
    @Autowired
    private SysDao sysDao;


    //管理员登录
    public Object sysLogin(String sysname,String syspass)throws Exception{
        return sysDao.sysLogin(sysname, syspass);
    }

    //管理员上传音乐
    public Object sysUploaMusic(Map parameters)throws Exception{
        return sysDao.sysUploaMusic(parameters);
    }
}
