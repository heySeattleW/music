package com.jpyl.music.api.service.impl;

import com.jpyl.music.api.dao.def.MvDao;
import com.jpyl.music.api.service.def.MvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/12/8.
 */
@Service
public class MvServiceImpl implements MvService {
    @Autowired
    private MvDao mvDao;

    //获取热门mv
    public Object getHotMvList(int page)throws Exception{
        return mvDao.getHotMvList(page);
    }
}
