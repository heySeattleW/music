package com.jpyl.music.api.dao.impl;

import com.jpyl.music.api.dao.def.MvDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/12/8.
 */
@Repository
public class MvDaoImpl implements MvDao {
    @Autowired
    SqlSessionTemplate sqlSession;

    //获取热门mv
    public Object getHotMvList(int page)throws Exception{
        page=page-1;
        page = page*50;
        return sqlSession.selectList("getHotMvList",page);
    }
}
