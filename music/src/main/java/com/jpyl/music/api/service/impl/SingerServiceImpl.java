package com.jpyl.music.api.service.impl;

import com.jpyl.music.api.dao.def.SingerDao;
import com.jpyl.music.api.service.def.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/12/8.
 */
@Service
public class SingerServiceImpl implements SingerService {
    @Autowired
    private SingerDao singerDao;

    //一级页面获取热门歌手列表
    public Object getFenLeiSingerList(int type1, int type2, int page) throws Exception{
        return singerDao.getFenLeiSingerList(type1,type2,page);
    }
}
