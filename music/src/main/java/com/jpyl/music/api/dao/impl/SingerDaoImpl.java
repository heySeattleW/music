package com.jpyl.music.api.dao.impl;

import com.jpyl.music.api.dao.def.SingerDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sun.reflect.annotation.ExceptionProxy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/8.
 */
@Repository
public class SingerDaoImpl implements SingerDao {
    @Autowired
    SqlSessionTemplate sqlSession;

    //一级页面获取热门歌手列表
    public Object getFenLeiSingerList(int type1, int type2, int page) throws Exception {
        page = page - 1;
        page = page * 50;
        Map map = new HashMap();
        map.put("page", page);
        map.put("type1", type1);
        map.put("type2", type2);
        return sqlSession.selectList("getFenLeiSingerList", map);
    }

}